package com.coolxer.model.retrieval.meta;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DataEntity {

    private int id;

    private String name;

    private String description;

    private String label;

    private String tableName;

    private String dataSource;

    private String sortColumn;

    private DbCreate autoCreate;

    @Data
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public class DbCreate {
        private String engine;
        private List<String> orderBy;
        private String partitionBy;
    }

}
