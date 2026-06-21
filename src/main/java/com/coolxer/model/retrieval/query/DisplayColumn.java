package com.coolxer.model.retrieval.query;

import com.coolxer.model.retrieval.meta.DataAttribute;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class DisplayColumn {

    private String columnName;

    private String columnType;

    private String displayName;

    private String displayType;

    public DisplayColumn fromDisplayColumn(DataAttribute dataAttribute) {
        this.setColumnName(dataAttribute.getColumnName());
        this.setColumnType(dataAttribute.getColumnType());
        this.setDisplayName(StringUtils.isEmpty(dataAttribute.getDisplayName()) ? dataAttribute.getColumnName() : dataAttribute.getDisplayName());
        this.setDisplayType(StringUtils.isEmpty(dataAttribute.getDisplayType()) ? dataAttribute.getColumnType() : dataAttribute.getDisplayType());
        return this;
    }

}
