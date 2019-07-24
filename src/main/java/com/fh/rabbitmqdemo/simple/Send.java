package com.fh.rabbitmqdemo.simple;

import com.fh.rabbitmqdemo.util.RabbitConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 简单队列 即一个生产者 一个消费者 两者通过一个队列进行交互
 */
// 生产者
public class Send {
    private final static String QUEUE_NAME = "q_test_01";

    public static void main(String[] argv) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = RabbitConnectionUtil.getConnection();
        // 从连接中创建通道
        Channel channel = connection.createChannel();

        // 声明（创建）队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 消息内容
        String message = "Hello World!";
        // 发送信息
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
        //关闭通道和连接
        channel.close();
        connection.close();
    }
}
