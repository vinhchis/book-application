package vinhchis.handler;

import com.tvd12.ezyhttp.core.response.ResponseEntity;

public class GlobalExceptionHandler implements Handler<Exception, ResponseEntity> {
    @Override
    public ResponseEntity handle(Exception input) throws Exception {
        return ResponseEntity.badRequest(input.getMessage());
    }
}
