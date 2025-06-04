package com.mb.apps.zvsdk.service;

import com.mb.apps.zvsdk.dto.*;
import com.mb.apps.zvsdk.gateway.ZoomSessionGateway;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZoomSessionService {

    private final ZoomSessionGateway gateway;

    public ZoomSessionService(ZoomSessionGateway gateway) {
        this.gateway = gateway;
    }

    public SessionResponse createSession(SessionRequest request) {
        return gateway.createSession(request);
    }

    public List<SessionResponse> getAllSessions() {
        return gateway.getAllSessions();
    }

    public SessionResponse getSession(String sessionId) {
        return gateway.getSession(sessionId);
    }

    public SessionResponse updateSession(String sessionId, SessionUpdateRequest request) {
        return gateway.updateSession(sessionId, request);
    }

    public void deleteSession(String sessionId) {
        gateway.deleteSession(sessionId);
    }

    public SessionParticipantsResponse fetchParticpiantsInformation(String sessionId) {
        return gateway.fetchParticipantsInformation(sessionId);
    }
}
