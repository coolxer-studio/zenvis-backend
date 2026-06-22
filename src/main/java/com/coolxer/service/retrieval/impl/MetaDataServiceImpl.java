package com.coolxer.service.retrieval.impl;

import com.coolxer.configuration.CustomWebConfig;
import com.coolxer.model.retrieval.meta.DataAttribute;
import com.coolxer.model.retrieval.meta.DataEntity;
import com.coolxer.model.retrieval.meta.DataOperator;
import com.coolxer.model.retrieval.meta.MetaData;
import com.coolxer.service.retrieval.MetaDataService;
import com.coolxer.utils.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Service
@Slf4j
public class MetaDataServiceImpl implements MetaDataService {

    private static MetaData metaData;
    private static Map<String, Map<String, Object>> metaDataMap;

    @Autowired
    private CustomWebConfig customWebConfig;

    @Override
    public MetaData loadMetaData() {
        MetaData metaData = new MetaData();
        try {
            try (Stream<Path> paths = Files.walk(Paths.get(customWebConfig.getRetrievalMetaFilePath()))) {
                paths.filter(Files::isRegularFile) // 过滤出文件
                        .filter(path -> path.toString().endsWith(".json")) // 过滤出 .json 文件
                        .forEach(path -> {
                            String str = null;
                            try {
                                str = FileUtils.readFileToString(path.toFile(), StandardCharsets.UTF_8);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            MetaData metaDataTmp = JacksonUtil.toObject(str, MetaData.class);
                            // 合并
                            metaData.merge(metaDataTmp);
                        });
            }
            MetaDataServiceImpl.metaData = metaData;
            Map<String, Map<String, Object>> metaDataMap = new HashMap<>();
            Map<String, Object> entityMap = new HashMap<>();
            Map<String, Object> attributeMap = new HashMap<>();
            Map<String, Object> operatorMap = new HashMap<>();
            metaData.getEntity().forEach(dataEntity -> entityMap.put(dataEntity.getName(), dataEntity));
            metaData.getAttribute()
                    .forEach(dataAttribute -> attributeMap.put(attributeMapKey(dataAttribute.getEntity(), dataAttribute.getName()), dataAttribute));
            metaData.getOperator()
                    .forEach(dataOperator -> operatorMap.put(dataOperator.getName(), dataOperator));
            metaDataMap.put("entity", entityMap);
            metaDataMap.put("attribute", attributeMap);
            metaDataMap.put("operator", operatorMap);
            MetaDataServiceImpl.metaDataMap = metaDataMap;
            return metaData;
        } catch (Exception ex) {
            log.error("read meta.json fail, {}", ex);
        }
        return null;
    }

    private String attributeMapKey(String entity, String attribute) {
        return entity + attribute;
    }

    @Override
    public DataEntity getDataEntityById(Integer id) {

        return new DataEntity();
    }

    @Override
    public DataEntity getDataEntityByName(String name) {
        return (DataEntity) metaDataMap.get("entity").get(name);
    }

    @Override
    public DataAttribute getDataAttributeById(Integer id) {
        return null;
    }

    @Override
    public DataAttribute getDataAttributeByName(String entity, String attribute) {
        return (DataAttribute) metaDataMap.get("attribute").get(attributeMapKey(entity, attribute));
    }

    @Override
    public List<DataEntity> getAllDataEntity() {
        if (metaData == null) {
            return Collections.emptyList();
        }
        return metaData.getEntity();
    }

    @Override
    public List<DataAttribute> getAllDataAttribute() {
        return null;
    }

    @Override
    public List<DataAttribute> getAllDataAttributeByEntity(DataEntity dataEntity) {
        return metaData.getAttribute().stream().filter(e -> StringUtils.equals(e.getEntity(), dataEntity.getName())).toList();
    }

    @Override
    public DataOperator getDataOperatorByName(String name) {
        return (DataOperator) metaDataMap.get("operator").get(name);
    }

}
