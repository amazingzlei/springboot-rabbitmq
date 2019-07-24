package com.fh.rabbitmqdemo.pubsub;

import com.fh.rabbitmqdemo.util.RabbitConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;


public class Send {
    // 定义交换机的名字
    private static final String EXCHANGE_NAME = "test_exchange_fanout";

    public static void main(String[] args) throws Exception {
        // 获取连接以及mq的通道
        Connection connection = RabbitConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 创建交换机 第二个参数表示交互机的类型
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        // 向交换机发送信息
        String message = "Hello World!";
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        // 关闭连接
        channel.close();
        connection.close();
    }
}
