package com.mb.apps.zvsdk.dto;

import lombok.Data;

import java.util.List;

@Data
public class SessionUser {
    private String id;
    private String name;
    private String device;
    private String ip_address;
    private String location;
    private String network_type;
    private String microphone;
    private String speaker;
    private String camera;
    private String data_center;
    private String connection_type;
    private String join_time;
    private String leave_time;
    private String user_key;
    private List<AudioCall> audio_call;
    private String participant_uuid;
    private String client;
    private String os;
    private String os_version;
    private String browser_name;
    private String browser_version;
    private String audio_quality;
    private String video_quality;
    private String screen_share_quality;
}
