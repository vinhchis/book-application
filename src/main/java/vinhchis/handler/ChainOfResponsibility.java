package vinhchis.handler;

import com.tvd12.ezyhttp.core.response.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class ChainOfResponsibility {
    private final List<Handler> handlers = new ArrayList<>();

    private static final GlobalExceptionHandler GLOBAL_EXCEPTION_HANDLER = new GlobalExceptionHandler();

    // 1. adding (4 type)
    // Handler<T, R> - logic(A->B ->C)
    // FirstVoidHandler<Void, Void> : check token,...
    // VoidHandler<T, Void> : logger
    // FirstHandler<Void, R> : parse to DTO,..

    public <T, R> ChainOfResponsibility addLogicHandler(Handler<T,R> handler) {
        handlers.add(handler);
        return this;
    }

    public ChainOfResponsibility addPreProcessor(FirstVoidHandler handler) {
        handlers.add(handler);
        return this;
    }

    public <T> ChainOfResponsibility addPostProcessor(VoidHandler<T> handler) {
        handlers.add(handler);
        return this;
    }

    public <R> ChainOfResponsibility addDataCreator(FirstHandler<R> handler) {
        handlers.add(handler);
        return this;
    }

    // 2. handing
    public ResponseEntity handle() throws Exception {
        try {
            Object result = null;
            for (Handler handler : handlers) {
                result = handler.handle(result);
            }

            if (result instanceof ResponseEntity) {
                return (ResponseEntity) result;
            }else{
                return ResponseEntity.ok(result);
            }
        }catch (Exception e) {
            return GLOBAL_EXCEPTION_HANDLER.handle(e);
        }
    }


}
