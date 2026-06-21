package com.coolxer.service.retrieval;

import com.coolxer.model.retrieval.meta.DataAttribute;
import com.coolxer.model.retrieval.meta.DataEntity;
import com.coolxer.model.retrieval.meta.DataOperator;
import com.coolxer.model.retrieval.meta.MetaData;

import java.util.List;

public interface MetaDataService {

    MetaData loadMetaData();

    DataEntity getDataEntityById(Integer entityId);

    DataEntity getDataEntityByName(String name);

    DataAttribute getDataAttributeById(Integer attributeId);

    DataAttribute getDataAttributeByName(String entity, String attribute);

    List<DataEntity> getAllDataEntity();

    List<DataAttribute> getAllDataAttribute();

    List<DataAttribute> getAllDataAttributeByEntity(DataEntity dataEntity);

    DataOperator getDataOperatorByName(String name);
}
