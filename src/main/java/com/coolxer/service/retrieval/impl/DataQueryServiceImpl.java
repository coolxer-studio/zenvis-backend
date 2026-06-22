package com.coolxer.service.retrieval.impl;

import com.coolxer.model.retrieval.query.ColumnCriteria;
import com.coolxer.model.retrieval.query.DataQuery;
import com.coolxer.model.retrieval.query.DataQueryContext;
import com.coolxer.model.retrieval.query.DisplayColumn;
import com.coolxer.model.retrieval.rule.RetrievalCriteria;
import com.coolxer.model.retrieval.rule.RetrievalPageable;
import com.coolxer.model.retrieval.rule.RetrievalRule;
import com.coolxer.service.retrieval.DataQueryService;
import com.coolxer.service.retrieval.QueryEngine;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DataQueryServiceImpl implements DataQueryService {

    @Autowired
    QueryEngine queryEngine;

    @Override
    public DataQueryContext query(RetrievalRule retrievalRule) {
        DataQueryContext context = generateQueryContext(retrievalRule);
        executeQuery(context);
        return context;
    }

    private DataQueryContext generateQueryContext(RetrievalRule retrievalRule) {
        DataQueryContext dataQueryContext = new DataQueryContext();
        dataQueryContext.setContextId(UUID.randomUUID().toString());
        List<DataQuery> dataQueryList;
        if (Objects.nonNull(retrievalRule.getRetrievalSql())) {
            dataQueryList = generateSqlDataQueryList(retrievalRule);

        } else {
            dataQueryList = generateDataQueryList(retrievalRule);
        }
        dataQueryContext.setQueryChain(dataQueryList);
        RetrievalPageable pageable = retrievalRule.getRetrievalPageable();
        if (Objects.nonNull(pageable)) {
            dataQueryContext.setPageable(pageable);
        }
        return dataQueryContext;
    }

    private List<DataQuery> generateSqlDataQueryList(RetrievalRule retrievalRule) {
        DataQuery dataQuery = new DataQuery();
        dataQuery.setTableName(retrievalRule.getRetrievalSql().getEntity().getTableName());
        dataQuery.setSql(retrievalRule.getRetrievalSql().getSql());
        List<DisplayColumn> displayColumnList = retrievalRule.getDisplayAttributes().get(0).getAttributeList()
                .stream().map(attribute -> new DisplayColumn().fromDisplayColumn(attribute)).toList();
        dataQuery.setDisplayColumnList(displayColumnList);
        return Collections.singletonList(dataQuery);
    }

    private List<DataQuery> generateDataQueryList(RetrievalRule retrievalRule) {
        Map<String, List<DisplayColumn>> displayColumnNameMap = new HashMap<>();
        retrievalRule.getDisplayAttributes().forEach(table -> {
            List<DisplayColumn> displayColumnList = table.getAttributeList().stream()
                    .map(attribute -> new DisplayColumn().fromDisplayColumn(attribute))
                    .toList();
            displayColumnNameMap.put(table.getEntity().getTableName(), displayColumnList);
        });
        List<DataQuery> dataQueryList = retrievalRule.getRetrievalCriteria().stream()
                .map(this::toColumnCriteria)
                .collect(Collectors.groupingBy(ColumnCriteria::getTableName))
                .entrySet().stream()
                .map(entry -> {
                    DataQuery dataQuery = new DataQuery();
                    dataQuery.setTableName(entry.getKey());
                    dataQuery.setColumnCriteria(entry.getValue());
                    dataQuery.setDisplayColumnList(displayColumnNameMap.get(dataQuery.getTableName()));
                    return dataQuery;
                })
                .collect(Collectors.toList());
        Collection<String> extraDisplayTable = CollectionUtils.subtract(displayColumnNameMap.keySet(), dataQueryList.stream().map(DataQuery::getTableName).toList());
        if (CollectionUtils.isNotEmpty(extraDisplayTable)) {
            extraDisplayTable.forEach(table -> {
                DataQuery dataQuery = new DataQuery();
                dataQuery.setDisplayColumnList(displayColumnNameMap.get(table));
                dataQuery.setTableName(table);
                dataQueryList.add(dataQuery);
            });
        }
        return dataQueryList;
    }

    private ColumnCriteria toColumnCriteria(RetrievalCriteria retrievalCriteria) {
        ColumnCriteria columnCriteria = new ColumnCriteria();
        columnCriteria.setTableName(retrievalCriteria.getEntity().getTableName());
        columnCriteria.setColumnName(retrievalCriteria.getAttribute().getColumnName());
        columnCriteria.setOperatorName(retrievalCriteria.getOperator().getName());
        columnCriteria.setValueList(retrievalCriteria.getValueList());
        columnCriteria.setRetrievalType(retrievalCriteria.getAttribute().getRetrievalType());
        return columnCriteria;
    }

    private void executeQuery(DataQueryContext context) {
        if (CollectionUtils.size(context.getQueryChain()) > 1) {
            chainQuery(context);
        } else {
            singleQuery(context);
        }
//    Integer resultLimit = context.getResultLimit();
//    List<Map<String, Object>> resultList = context.getResultList();
//    while (CollectionUtils.size(resultList) < resultLimit) {
//      DataQuery previousQuery = null;
//      for (DataQuery currentQuery : context.getQueryChain()) {
//        if (needQuery(currentQuery)) {
//
//        } else {
//          updateCursor(currentQuery);
//        }
//      }
//    }
//    return buildResult(context);
        // comit


//    Map<String, Object> record1 = new HashMap<>();
//    record1.put("model", "xiaomi");
//    record1.put("mac", "abcd");
//    Map<String, Object> record2 = new HashMap<>();
//    record2.put("model", "huawei");
//    record2.put("mac", "bdcd");
//    List<Map<String, Object>> resultList = new ArrayList<>();
//    resultList.add(record1);
//    resultList.add(record2);
//    context.setResultList(resultList);
    }

    private void chainQuery(DataQueryContext context) {

    }

    private void singleQuery(DataQueryContext context) {

        DataQuery dataQuery = context.getQueryChain().get(0);
        RetrievalPageable pageable = context.getPageable();
        Map<String, Object> resultMap = queryEngine.queryWithRetrieval(dataQuery, pageable);

        context.setResultList((List<Map<String, Object>>) resultMap.get("data"));
        context.setTotal((BigDecimal) resultMap.get("total"));
    }

    private boolean needQuery(DataQuery query) {
        return true;
    }

    private void updateCursor(DataQuery query) {

    }

    private Map<String, Object> buildResult(DataQueryContext context) {
        return null;
    }
}
