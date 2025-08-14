package vinhchis.handler;

import com.tvd12.ezyhttp.core.response.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class ChainOfReposibility {
    private final List<Handler> handlers = new ArrayList<>();

    private static final GlobalExceptionHandler GLOBAL_EXCEPTION_HANDLER = new GlobalExceptionHandler();

    public <T, R> ChainOfReposibility addHandler(Handler<T,R> handler) {
        handlers.add(handler);
        return this;
    }


    public ChainOfReposibility addFirstVoidHandler(FirstVoidHandler handler) {
        handlers.add(handler);
        return this;
    }

    public <T> ChainOfReposibility addVoidHandler(VoidHandler<T> handler) {
        handlers.add(handler);
        return this;
    }

    public <R> ChainOfReposibility addFirstHandler(FirstHandler<R> handler) {
        handlers.add(handler);
        return this;
    }

    public ResponseEntity handle(){
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
