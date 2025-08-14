package vinhchis.handler;

public interface FirstVoidHandler extends Handler<Void, Void> {

    @Override
    default Void handle(Void input) throws Exception {
        voidHandle();
        return null;
    }

    void voidHandle() throws Exception;
}
