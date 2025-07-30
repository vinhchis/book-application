package vinhchis.connection;

import vinhchis.BookApplication;

import java.sql.Connection;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectionPool {
    private final LinkedBlockingDeque<Connection> connectionQueue = new LinkedBlockingDeque<>();
    private final AtomicInteger numberOfCreatedConnections = new AtomicInteger(0);
    private static final int MAX_CONNECTIONS = 16;

    private final ConnectionFactory connectionFactory = new ConnectionFactoryImpl();

    public Connection provide() throws Exception{
        synchronized (this){
            if(numberOfCreatedConnections.get() < MAX_CONNECTIONS){
                final Connection connection = connectionFactory.newConnection();
                numberOfCreatedConnections.incrementAndGet();
                return connection;
            }
        }
        // đạt max - chờ lấy ra từ
        return connectionQueue.take(); // Blocking call nếu queue rỗng
    }

    public void pushBack(Connection connection){
        connectionQueue.offer(connection);
    }
}

/*
* LinkedBlockingDeque<T> - buộc luồng gọi phải chờ cho đến khi nào một connection trong hàng đợi
*  - take() : lấy ra từ Queue (take first)
*  - offer() : trả về Queue (add)
*
* numberOfCreatedConnections : số lượng connection đã được tạo ra, không giảm vì trả về pool để dùng lại mà thôi
*
* */
