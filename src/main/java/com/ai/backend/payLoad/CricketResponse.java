package com.ai.backend.payLoad;

import lombok.Data;

@Data
public class CricketResponse {
    private String content ;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
