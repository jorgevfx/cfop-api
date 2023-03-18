package com.george.cfopapi.model;

import lombok.Data;

@Data
public class AlgorithmModel {
    private String moves;
    private int votes;

    public AlgorithmModel(String moves) {
        this.moves = moves;
        this.votes = 0;
    }
}
