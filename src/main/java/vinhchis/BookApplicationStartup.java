package vinhchis;

import com.tvd12.ezyhttp.server.boot.EzyHttpApplicationBootstrap;
import com.tvd12.ezyhttp.server.core.annotation.ComponentsScan;

@ComponentsScan({"vinhchis.controller"})
public final class BookApplicationStartup {
    public static void main(String[] args) throws Exception {
        EzyHttpApplicationBootstrap
                .start(BookApplicationStartup.class
        );
    }
}
