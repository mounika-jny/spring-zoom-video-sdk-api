package com.mb.apps.zvsdk.service;

import com.mb.apps.zvsdk.util.ZoomTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class ZoomService {

    @Autowired
    private ZoomTokenUtil zoomTokenUtil;

    public String getToken(String userId) {
        return zoomTokenUtil.generateVideoSdkJwt(userId);
    }

    public void logProbeResult(Map<String, Object> result) {
        log.info("📋 Received Zoom Probe Data: " + result);
    }
}
