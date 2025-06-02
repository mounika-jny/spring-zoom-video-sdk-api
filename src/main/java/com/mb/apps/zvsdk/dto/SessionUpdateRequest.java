package com.mb.apps.zvsdk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SessionUpdateRequest {

    @JsonProperty("session_name")
    private String sessionName;
    private String sessionPassword;
    private SessionSettings settings;
}
