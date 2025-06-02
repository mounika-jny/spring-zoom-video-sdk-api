package com.mb.apps.zvsdk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SessionRequest {

    @NotBlank
    @JsonProperty("session_name")
    private String sessionName;

    @NotBlank
    @JsonProperty("session_password")
    private String sessionPassword;

    @NotNull
    private SessionSettings settings;
}
