package com.george.cfopapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "oll")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OllModel {
    @Id
    private ObjectId id;
    private String caseId;
    private List<String> names;
    private String group;
    private List<AlgorithmModel> algs;
}
