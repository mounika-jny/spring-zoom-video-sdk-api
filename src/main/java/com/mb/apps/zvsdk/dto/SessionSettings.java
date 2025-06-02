package com.mb.apps.zvsdk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SessionSettings {
    @JsonProperty("auto_recording")
    private String autoRecording;
}
