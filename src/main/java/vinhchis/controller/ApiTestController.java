package vinhchis.controller;

import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;

@Controller("/api/v1")
public class ApiTestController {
    @DoGet("/hello")
    public ResponseEntity sayHello() {
        return ResponseEntity.ok("Hello from EzyHTTP!");
    }
}
