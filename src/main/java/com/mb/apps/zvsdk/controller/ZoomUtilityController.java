package com.mb.apps.zvsdk.controller;

import com.mb.apps.zvsdk.service.ZoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/util")
public class ZoomUtilityController {

    @Autowired
    private ZoomService zoomService;

    @GetMapping("/token")
    public ResponseEntity<Map<String, String>> getToken(@RequestParam String userId) {
        String token = zoomService.getToken(userId);
        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/probe")
    public ResponseEntity<Void> receiveProbeResult(@RequestBody Map<String, Object> result) {
        zoomService.logProbeResult(result);
        return ResponseEntity.ok().build();
    }
}
