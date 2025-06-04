package com.mb.apps.zvsdk.gateway;

import com.mb.apps.zvsdk.dto.*;
import com.mb.apps.zvsdk.util.ZoomTokenUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@Component
public class ZoomSessionGateway {

    @Value("${zoom.api.base-url}")
    private String baseUrl;

    private final ZoomTokenUtil zoomTokenUtil;

    private final RestTemplate restTemplate;

    public ZoomSessionGateway(ZoomTokenUtil zoomTokenUtil, RestTemplate restTemplate) {
        this.zoomTokenUtil = zoomTokenUtil;
        this.restTemplate = restTemplate;
    }

    public SessionResponse createSession(SessionRequest request) {
        return exchange(HttpMethod.POST, "/videosdk/sessions", request, SessionResponse.class);
    }

    public List<SessionResponse> getAllSessions() {
        ResponseEntity<List<SessionResponse>> response = restTemplate.exchange(
                baseUrl + "/videosdk/sessions",
                HttpMethod.GET,
                buildEntity(null),
                new ParameterizedTypeReference<>() {});
        return response.getBody();
    }

    public SessionResponse getSession(String sessionId) {
        return exchange(HttpMethod.GET, "/videosdk/sessions/" + sessionId, null, SessionResponse.class);
    }

    public SessionResponse updateSession(String sessionId, SessionUpdateRequest request) {
        return exchange(HttpMethod.PATCH, "/videosdk/sessions/" + sessionId, request, SessionResponse.class);
    }

    public void deleteSession(String sessionId) {
        restTemplate.exchange(
                baseUrl + "/videosdk/sessions/" + sessionId,
                HttpMethod.DELETE,
                buildEntity(null),
                Void.class);
    }

    private <T> T exchange(HttpMethod method, String path, Object body, Class<T> responseType) {
        return restTemplate.exchange(baseUrl + path, method, buildEntity(body), responseType).getBody();
    }

    private HttpEntity<Object> buildEntity(Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(zoomTokenUtil.generateVideoSdkJwt("zoom-test"));
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(body, headers);
    }


    public SessionParticipantsResponse fetchParticipantsInformation(String sessionId) {
        return exchange(HttpMethod.GET, "/videosdk/sessions/" + sessionId+"/users", null, SessionParticipantsResponse.class);

    }
}
