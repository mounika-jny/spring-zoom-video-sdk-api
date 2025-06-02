package com.mb.apps.zvsdk.dto;

import lombok.Data;

@Data
public class SessionResponse {

    private String sessionId;
    private String sessionName;
    private String createdAt;
    private SessionSettings settings;
}
