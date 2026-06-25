package com.coolxer.service.retrieval.impl;

import com.coolxer.model.retrieval.meta.DataAttribute;
import com.coolxer.model.retrieval.query.ColumnCriteria;
import com.coolxer.model.retrieval.query.DataQuery;
import com.coolxer.model.retrieval.query.DisplayColumn;
import com.coolxer.model.retrieval.rule.RetrievalPageable;
import com.coolxer.service.retrieval.QueryEngine;
import com.coolxer.utils.JacksonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class QueryEngineImpl implements QueryEngine {

    /**
     * entityManager实现原生查询，unitName是通过clickHouseEntityManagerFactoryBean注入时候指定的名字
     */
    @PersistenceContext(unitName = "clickhouse", type = PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;

    @Transactional
    public void save(String tableName, List<String> columnList, List<String> valueList) {
        // 构建插入SQL
        String insertSql = "insert into " + tableName + " (" + StringUtils.join(columnList, ",") + ") values (" + StringUtils.join(valueList, ",") + ")";
        Query query = entityManager.createNativeQuery(insertSql);
        query.executeUpdate();
    }

    @Transactional
    public void update(String tableName, Map<String, String> mapData, String keyColumn, String keyValue) {
        // 构建更新SQL
        List<String> setList = mapData.entrySet().stream().map(entry -> entry.getKey() + " = " + entry.getValue()).toList();
        String updateSql = "update " + tableName + " set " +
                StringUtils.join(setList, " , ")
                + " where " + keyColumn + " = " + "'%s'".formatted(keyValue);
        Query query = entityManager.createNativeQuery(updateSql);
        query.executeUpdate();
    }

    @Transactional
    public void delete(String tableName, String keyColumn, String keyValue) {
        String deleteSql = "delete from " + tableName + " where " + keyColumn + " = " + "'%s'".formatted(keyValue);
        Query query = entityManager.createNativeQuery(deleteSql);
        query.executeUpdate();
    }

    @Transactional
    public void deleteIn(String tableName, String keyColumn, List<String> keyValueList) {
        String deleteSql = "delete from " + tableName +
                " where " + keyColumn + " in (" + StringUtils.join("'%s'".formatted(keyValueList), ",") + ")";
        Query query = entityManager.createNativeQuery(deleteSql);
        query.executeUpdate();
    }

    @Transactional
    public Map<String, Object> findById(String tableName, String id, List<DataAttribute> dataAttributes) {
        List<DisplayColumn> displayColumnList = dataAttributes.stream().map(attribute -> new DisplayColumn().fromDisplayColumn(attribute)).toList();
        List<String> selectColumnList = displayColumnList.stream().map(this::convertDisplayColumn).toList();
        List<String> columnList = displayColumnList.stream().map(DisplayColumn::getColumnName).toList();
        String columnSelectSql = StringUtils.join(selectColumnList, ",");

        String selectSql = "select " + columnSelectSql + " from " + tableName + " where id = " + "'%s'".formatted(id);
        Query query = entityManager.createNativeQuery(selectSql);
        // 执行查询
        List<Object[]> result = query.getResultList();
        if (result == null || result.size() == 0) {
            return null;
        } else if (result.size() > 1) {
            throw new RuntimeException("查询结果有多条");
        } else {
            Object[] row = result.get(0);
            Map<String, Object> resultMap = new HashMap<>();
            for (int i = 0; i < columnList.size(); i++) {
                resultMap.put(columnList.get(i), row[i]);
            }
            return resultMap;
        }
    }

    @Transactional
    public BigDecimal count(String tableName, Map<String, Object> searchMap) {
        String whereClause = " where 1=1";
        if (MapUtils.isNotEmpty(searchMap)) {
            whereClause = " where " + searchMap.entrySet().stream()
                    .map(entry -> entry.getKey() + " = " + entry.getValue())
                    .collect(Collectors.joining(" and "));
        }
        return queryCount(tableName, whereClause);
    }

    @Transactional
    public BigDecimal countToday(String tableName, Map<String, Object> searchMap) {
        String whereClause = " where 1=1";
        if (MapUtils.isNotEmpty(searchMap)) {
            whereClause = " where " + searchMap.entrySet().stream()
                    .map(entry -> entry.getKey() + " = " + entry.getValue())
                    .collect(Collectors.joining(" and "));
        }
        // 补充时间条件
        whereClause += " and insert_time >= toStartOfDay(now())";
        return queryCount(tableName, whereClause);
    }

    @Override
    @Transactional
    public Map<String, Object> countByDateOfWeek(String tableName, String timeField) {
        String countSql = "SELECT toStartOfDay(insert_time) AS group_key, COUNT(*) AS count FROM " + tableName + " WHERE " + timeField + " >= today() - INTERVAL 1 WEEK GROUP BY toStartOfDay(" + timeField + ") ORDER BY group_key";
        Query query = entityManager.createNativeQuery(countSql);
        // 执行查询
        Map<String, Object> resultMap = new HashMap<>();
        List<Object[]> result = query.getResultList();
        result.forEach(row -> {
            if (row.length < 2) {
                // 日志记录或抛出自定义异常
                throw new IllegalArgumentException("查询结果字段数量不足，期望至少2个字段");
            }
            resultMap.put(String.valueOf(row[0]), row[1]);
        });
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> countByField(String tableName, String field) {
        String countSql = "select " + field + " as group_key, count(*) as count from " + tableName + " GROUP BY " + field;
        Query query = entityManager.createNativeQuery(countSql);
        // 执行查询
        Map<String, Object> resultMap = new HashMap<>();
        List<Object[]> result = query.getResultList();
        result.forEach(row -> {
            if (row.length < 2) {
                // 日志记录或抛出自定义异常
                throw new IllegalArgumentException("查询结果字段数量不足，期望至少2个字段");
            }
            resultMap.put(String.valueOf(row[0]), row[1]);
        });
        return resultMap;
    }

    public Map<String, Object> findByPage(String tableName, Map<String, Object> searchMap, RetrievalPageable pageable, List<DataAttribute> dataAttributes) {
        List<DisplayColumn> displayColumnList = dataAttributes.stream().map(attribute -> new DisplayColumn().fromDisplayColumn(attribute)).toList();
        List<String> selectColumnList = displayColumnList.stream().map(this::convertDisplayColumn).toList();
        List<String> columnList = displayColumnList.stream().map(DisplayColumn::getColumnName).toList();
        String columnSelectSql = StringUtils.join(selectColumnList, ",");
        // 获取有效的查询参数
        Map<String, DisplayColumn> displayColumnMap = displayColumnList.stream().collect(Collectors.toMap(DisplayColumn::getColumnName, column -> column));
        List<String> validSearchItemList = searchMap.entrySet().stream()
                .filter(entry -> columnList.contains(entry.getKey()) &&
                        entry.getValue() != null &&
                        StringUtils.isNotEmpty(entry.getValue().toString()))
                .map(entry -> {
                    // 不同类型的where语法也不一样，先适配array：has(label, :label))
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    DisplayColumn displayColumn = displayColumnMap.get(entry.getKey());
                    if (displayColumn.getColumnType() != null && displayColumn.getColumnType().startsWith("Array")) {
                        return " has(%s, '%s') ".formatted(key, value);
                    } else {
                        return " %s = '%s' ".formatted(key, value);
                    }
                })
                .toList();
        String whereClause = " where 1=1";
        if (CollectionUtils.isNotEmpty(validSearchItemList)) {
            whereClause = " where " + validSearchItemList.stream().collect(Collectors.joining(" and "));
        }
        String pageClause = buildPage(pageable);
        String querySql = "select " + columnSelectSql + " from " + tableName + whereClause + pageClause;
        log.info("get sql {}", querySql);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", queryCount(tableName, whereClause));
        resultMap.put("data", queryResultList(querySql, displayColumnList));
        return resultMap;
    }

    @Transactional
    public List<String> getDistinct(String tableName, String attribute) {
        String selectSql = "select DISTINCT " + attribute + " from " + tableName;
        Query query = entityManager.createNativeQuery(selectSql);
        // 执行查询
        List<String> resultList = new ArrayList<>();
        List<String> result = query.getResultList();
        result.forEach(row -> {
            resultList.add(row);
        });
        return resultList;
    }

    @Transactional
    public List<String> getDistinctForArray(String tableName, String attribute) {
        String selectSql = "select DISTINCT arrayJoin(%s) from %s WHERE %s IS NOT NULL AND %s != []".formatted(attribute, tableName, attribute, attribute);
        Query query = entityManager.createNativeQuery(selectSql);
        // 执行查询
        List<String> resultList = new ArrayList<>();
        List<String> result = query.getResultList();
        result.forEach(row -> {
            resultList.add(row);
        });
        return resultList;
    }

    @Transactional
    public List<String> getLike(String tableName, String attribute, String searchTerm) {
        String selectSql = "select DISTINCT " + attribute + " from " + tableName + " where " + attribute + " like '%" + searchTerm + "%'";
        Query query = entityManager.createNativeQuery(selectSql);
        // 执行查询
        List<String> resultList = new ArrayList<>();
        List<String> result = query.getResultList();
        result.forEach(row -> {
            resultList.add(row);
        });
        return resultList;
    }

    @Override
    public Map<String, Object> queryWithRetrieval(DataQuery dataQuery, RetrievalPageable pageable) {

        String tableName = dataQuery.getTableName();
        List<ColumnCriteria> columnCriteria = dataQuery.getColumnCriteria();
        String whereClause = "";
        String sql = dataQuery.getSql();
        if (StringUtils.isNotBlank(sql) && sqlValidate(sql)) {
            whereClause += " where " + dataQuery.getSql();
        } else if (CollectionUtils.isNotEmpty(columnCriteria)) {
            whereClause += " where " + columnCriteria.stream().map(this::buildCriteriaSql)
                    .collect(Collectors.joining(" and "));
        }
        String pageClause = buildPage(pageable);
        List<String> displayColumnList = dataQuery.getDisplayColumnList().stream().map(DisplayColumn::getColumnName).toList();
        List<String> selectColumnList = dataQuery.getDisplayColumnList().stream().map(this::convertDisplayColumnWithAs).toList();

        String columnSelectSql = StringUtils.join(selectColumnList, ",");
        String querySql = "select " + columnSelectSql + " from " + tableName + whereClause + pageClause;
        log.info("get sql {}", querySql);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", queryCount(tableName, whereClause));
        resultMap.put("data", queryResultList(querySql, dataQuery.getDisplayColumnList()));
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> groupAgendaTagsWithWhereClause(String tableName, String whereClause) {
        if (StringUtils.isNotEmpty(whereClause)) {
            whereClause += " and length(agenda_tags) > 0";
        }
        String sql = "select arrayStringConcat(groupArray(arrayStringConcat(agenda_tags,',')),',') as agenda_tags_array from " + tableName + " ";
        String querySql = sql + whereClause;
        Query query = entityManager.createNativeQuery(querySql);
        // 执行查询
        List<String> result = query.getResultList();
        String agendaTagsArrayString = "";
        if (result.size() > 0) {
            agendaTagsArrayString = result.get(0);
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("agenda_tags_array", agendaTagsArrayString);
        return resultMap;
    }

    @Override
    public List<Map<String, Object>> countTypeByHourWithWhereClause(String tableName, String whereClause) {
        String querySql = "select count(*) as msg_count, fact_type as group_key, toHour(server_time) as time from " +
                tableName +
                " " +
                whereClause +
                " group by fact_type ,time order by fact_type ,time";
        return countTypeWithWhereClause(querySql);
    }

    @Override
    public List<Map<String, Object>> countTypeByDayWithWhereClause(String tableName, String whereClause) {
        String querySql = "select count(*) as msg_count, fact_type as group_key, toDate(server_time) as time from " +
                tableName +
                " " +
                whereClause +
                " group by fact_type ,time order by fact_type ,time";
        return countTypeWithWhereClause(querySql);
    }

    @Transactional
    private List<Map<String, Object>> countTypeWithWhereClause(String querySql) {
        Query query = entityManager.createNativeQuery(querySql);
        // 执行查询
        List<Map<String, Object>> resultMapList = new ArrayList<>();
        List<Object[]> result = query.getResultList();
        result.forEach(row -> {
            if (row.length < 3) {
                // 日志记录或抛出自定义异常
                throw new IllegalArgumentException("查询结果字段数量不足，期望至少3个字段");
            }
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("msg_count", row[0]);
            resultMap.put("group_key", row[1]);
            resultMap.put("time", row[2]);
            resultMapList.add(resultMap);
        });
        return resultMapList;
    }

    @Transactional
    private BigDecimal queryCount(String tableName, String whereClause) {
        String countSql = "select count(*) from " + tableName + whereClause;
        BigDecimal total = BigDecimal.valueOf(0);
        Query query = entityManager.createNativeQuery(countSql);
        // 执行查询
        List<BigDecimal> result = query.getResultList();
        if (result.size() > 0) {
            total = result.get(0);
        }
        return total;
    }

    private boolean sqlValidate(String sql) {
        String s = sql.toLowerCase();//统一转为小写
        String badStr =
                "select|update|and|or|delete|insert|truncate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute|table|" +
                        "char|declare|sitename|xp_cmdshell|like|from|grant|use|group_concat|column_name|" +
                        "information_schema.columns|table_schema|union|where|order|by|" +
                        "'\\*|\\;|\\-|\\--|\\+|\\,|\\//|\\/|\\%|\\#";//过滤掉的sql关键字，特殊字符前面需要加\\进行转义
        //使用正则表达式进行匹配
        boolean res = s.matches(badStr);
        if (res) {
            log.error("sql inject {}", sql);
        }
        return !res;
    }

    private String convertDisplayColumnWithAs(DisplayColumn displayColumn) {
        String columnName = displayColumn.getDisplayName();
        String displayName = displayColumn.getDisplayName();
        String displayType = displayColumn.getDisplayType();
        if (StringUtils.isBlank(displayType)) {
            return displayColumn.getDisplayName();
        }
        return switch (displayType) {
            case "json" -> "toJSONString(" + columnName + ") as " + displayName;
            case "array" -> "arrayStringConcat(" + columnName + ",',') as " + displayName;
            default -> columnName + " as " + displayName;
        };
    }

    private String convertDisplayColumn(DisplayColumn displayColumn) {
        String columnName = displayColumn.getDisplayName();
        String displayType = displayColumn.getDisplayType();
        if (StringUtils.isBlank(displayType)) {
            return displayColumn.getDisplayName();
        }
        return switch (displayType) {
            case "json" -> "toJSONString(" + columnName + ")";
            case "array" -> "arrayStringConcat(" + columnName + ",',') ";
            default -> columnName;
        };
    }

    private String buildPage(RetrievalPageable pageable) {
        int page = Objects.nonNull(pageable.getPage()) ? pageable.getPage() : 1;
        int size = Objects.nonNull(pageable.getSize()) ? pageable.getSize() : 10;

        String pageStr = "";
        if (Objects.nonNull(pageable.getSortBy())) {
            String sortBy = pageable.getSortBy();
            String order = pageable.getOrder();
            String orderStr = StringUtils.equalsIgnoreCase(order, "asc") ? "asc" : "desc";
            pageStr = " order by " + sortBy + " " + orderStr;
        }
        pageStr += " limit " + (page - 1) * size + "," + size;
        return pageStr;
    }

    private String buildCriteriaSql(ColumnCriteria columnCriteria) {
        String columnName = columnCriteria.getColumnName();
        String operatorName = columnCriteria.getOperatorName();
        List<String> valueList = columnCriteria.getValueList();
        if (StringUtils.isNotBlank(columnCriteria.getRetrievalType())) {
            valueList = valueList.stream().filter(this::sqlValidate).map(value -> convertValueList(value, columnCriteria.getRetrievalType())).toList();
        }
        return switch (operatorName) {
            case "equal" -> columnName + " = " + addQuote(valueList.get(0));
            case "notequal" -> columnName + " != " + addQuote(valueList.get(0));
            case "match" -> columnName + " like " + addLikeQuote(valueList.get(0));
            case "greatthan" -> columnName + " > " + valueList.get(0);
            case "lessthan" -> columnName + " < " + valueList.get(0);
            case "greatequalthan" -> columnName + " >= " + valueList.get(0);
            case "lessequalthan" -> columnName + " <= " + valueList.get(0);
            case "between" -> columnName + " between " + valueList.get(0) + " and " + valueList.get(1);
            case "in" -> columnName + " in " + "(" + StringUtils.join(valueList.stream().map(this::addQuote).toList(), ",") + ")";
            default -> null;
        };
    }

    private String convertValueList(String origin, String retrievalType) {
        switch (retrievalType) {
            case "date":
                long epoch = LocalDateTime.parse(origin, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toEpochSecond(ZoneOffset.UTC) * 1000;
                return Long.toString(epoch);
            default:
                return null;
        }
    }

    private String addQuote(String name) {
        return "'" + name + "'";
    }

    private String addLikeQuote(String name) {
        return "'%" + name + "%'";
    }

    @Transactional
    private List<Map<String, Object>> queryResultList(String sql, List<DisplayColumn> columnList) {
        List<Map<String, Object>> resultMapList = new ArrayList<>();
        Query query = entityManager.createNativeQuery(sql);
        // 执行查询
        List<Object[]> result = query.getResultList();
        result.forEach(row -> {
            Map<String, Object> resultMap = new HashMap<>();
            for (int i = 0; i < columnList.size(); i++) {
                DisplayColumn displayColumn = columnList.get(i);
                if ("json".equals(displayColumn.getDisplayType())) {
                    String jsonString = row[i].toString();
                    if (jsonString.startsWith("[") && jsonString.endsWith("]")) {
                        List<Object> jsonList = JacksonUtil.toList(jsonString, new TypeReference<List<Object>>() {
                        });
                        resultMap.put(displayColumn.getDisplayName(), jsonList);
                    } else {
                        Map<String, Object> jsonMap = JacksonUtil.toMap(jsonString, new TypeReference<>() {
                        });
                        resultMap.put(displayColumn.getDisplayName(), jsonMap);
                    }
                } else {
                    resultMap.put(displayColumn.getDisplayName(), row[i]);
                }
            }
            resultMapList.add(resultMap);
        });
        return resultMapList;
    }

}
