package com.fh.rabbitmqdemo.util;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;;

/**
 * RabbitMQ连接工具，用于获取连接RabbitMQ的connection
 */
public class RabbitConnectionUtil {
    public static Connection getConnection() throws Exception {
        //定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置服务地址
        factory.setHost("192.168.33.128");
        //端口
        factory.setPort(5672);
        //设置账号信息，用户名、密码、vhost
        factory.setVirtualHost("myVirtualHost");
        factory.setUsername("myrabbitmq");
        factory.setPassword("123456");
        // 通过工程获取连接
        Connection connection = factory.newConnection();
        return connection;
    }
}
