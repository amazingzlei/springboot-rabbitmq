package com.fh.rabbitmqdemo.route;

import com.fh.rabbitmqdemo.util.RabbitConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

public class Recv2 {
    // 定义消息队列和交换机名称
    private static final String QUEUE_NAME = "test_queue_direct2";
    private static final String EXCHANGE_NAME = "test_exchange_direct";

    public static void main(String[] args) throws Exception{
        // 获取管道和连接
        Connection connection = RabbitConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 创建队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 绑定交换机
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "update");
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "delete");
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "insert");

        // 创建消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        // 监听队列，手动返回完成
        channel.basicConsume(QUEUE_NAME, false, queueingConsumer);

        // 获取消息
        while (true) {
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [Recv] Received '" + message + "'");
            Thread.sleep(10);

            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
    }
}
