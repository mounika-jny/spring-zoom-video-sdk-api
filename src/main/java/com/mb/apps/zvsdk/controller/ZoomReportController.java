package com.mb.apps.zvsdk.controller;

import com.mb.apps.zvsdk.dto.ZoomSessionDetails;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@RestController
@RequestMapping("/api/zoom/sessions")
public class ZoomReportController {

    private final Map<String, SseEmitter> sessionEmitters = new ConcurrentHashMap<>();
    private final Map<String, ZoomSessionDetails> sessionDetails = new ConcurrentHashMap<>();

    @PostMapping
    public ResponseEntity<ZoomSessionDetails> initializeSession(@RequestBody ZoomSessionDetails session) {
        sessionDetails.put(session.getId(), session);
        return ResponseEntity.ok(session);
    }

    @GetMapping("/{sessionId}")
    public ResponseEntity<ZoomSessionDetails> getSessionDetails(@PathVariable String sessionId) {
        ZoomSessionDetails session = sessionDetails.get(sessionId);
        if (session == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(session);
    }

    @GetMapping(path = "/{sessionId}/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter streamSession(@PathVariable String sessionId) {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        sessionEmitters.put(sessionId, emitter);

        emitter.onCompletion(() -> sessionEmitters.remove(sessionId));
        emitter.onTimeout(() -> sessionEmitters.remove(sessionId));

        try {
            ZoomSessionDetails currentSession = sessionDetails.get(sessionId);
            if (currentSession != null) {
                emitter.send(SseEmitter.event()
                        .name("session_update")
                        .data(currentSession));
            }
        } catch (IOException e) {
            emitter.complete();
        }

        return emitter;
    }

    @PutMapping("/{sessionId}")
    public ResponseEntity<ZoomSessionDetails> updateSession(
            @PathVariable String sessionId,
            @RequestBody ZoomSessionDetails updatedSession) {

        if (!sessionDetails.containsKey(sessionId)) {
            return ResponseEntity.notFound().build();
        }

        sessionDetails.put(sessionId, updatedSession);
        notifySessionUpdate(sessionId, updatedSession);

        return ResponseEntity.ok(updatedSession);
    }

    private void notifySessionUpdate(String sessionId, ZoomSessionDetails session) {
        SseEmitter emitter = sessionEmitters.get(sessionId);
        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event()
                        .name("session_update")
                        .data(session));
            } catch (IOException e) {
                sessionEmitters.remove(sessionId);
                emitter.complete();
            }
        }
    }
}

