package com.mb.apps.zvsdk.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class ZoomSessionDetails {
    private String id;

    @JsonProperty("session_number")
    private Long sessionNumber;

    @JsonProperty("session_name")
    private String sessionName;

    private String passcode;

    @JsonProperty("start_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private ZonedDateTime startTime;

    @JsonProperty("end_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private ZonedDateTime endTime;

    private String duration;

    @JsonProperty("user_count")
    private Integer userCount;

    @JsonProperty("has_voip")
    private Boolean hasVoip;

    @JsonProperty("has_video")
    private Boolean hasVideo;

    @JsonProperty("has_screen_share")
    private Boolean hasScreenShare;

    @JsonProperty("has_recording")
    private Boolean hasRecording;

    @JsonProperty("has_pstn")
    private Boolean hasPstn;

    @JsonProperty("session_key")
    private String sessionKey;

    @JsonProperty("has_session_summary")
    private Boolean hasSessionSummary;

    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private ZonedDateTime createdAt;

    @JsonProperty("audio_quality")
    private String audioQuality;

    @JsonProperty("video_quality")
    private String videoQuality;

    @JsonProperty("screen_share_quality")
    private String screenShareQuality;

}
