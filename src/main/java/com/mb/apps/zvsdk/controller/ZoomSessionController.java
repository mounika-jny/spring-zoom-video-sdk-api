package com.mb.apps.zvsdk.controller;

import com.mb.apps.zvsdk.dto.*;
import com.mb.apps.zvsdk.service.ZoomSessionService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/video/sessions")
public class ZoomSessionController {

    private final ZoomSessionService sessionService;

    public ZoomSessionController(ZoomSessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping
    public ResponseEntity<SessionResponse> createSession(@Valid @RequestBody SessionRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sessionService.createSession(request));
    }

    @GetMapping
    public ResponseEntity<List<SessionResponse>> getAllSessions() {
        return ResponseEntity.ok(sessionService.getAllSessions());
    }

    @GetMapping("/{sessionId}")
    public ResponseEntity<SessionResponse> getSession(@PathVariable String sessionId) {
        return ResponseEntity.ok(sessionService.getSession(sessionId));
    }

    @PatchMapping("/{sessionId}")
    public ResponseEntity<SessionResponse> updateSession(
            @PathVariable String sessionId,
            @RequestBody SessionUpdateRequest updateRequest) {
        return ResponseEntity.ok(sessionService.updateSession(sessionId, updateRequest));
    }

    @DeleteMapping("/{sessionId}")
    public ResponseEntity<Void> deleteSession(@PathVariable String sessionId) {
        sessionService.deleteSession(sessionId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{sessionId}/participants-info")
    public ResponseEntity<SessionParticipantsResponse> getParticipantsInfo(@PathVariable String sessionId) {
        return ResponseEntity.ok(sessionService.fetchParticpiantsInformation(sessionId));
    }

}
