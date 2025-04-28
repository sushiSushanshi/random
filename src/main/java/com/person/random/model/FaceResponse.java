package com.person.random.model;

import lombok.Data;

import java.util.List;

@Data
public class FaceResponse {
    private List<String> fc;

    public List<String> getFc() {
        return fc;
    }

    public void setFc(List<String> fc) {
        this.fc = fc;
    }
}
