package com.fh.rabbitmqdemo.route;

import com.fh.rabbitmqdemo.util.RabbitConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Send {

    // 定义交换机的名称
    private static final String EXCHANGE_NAME = "test_exchange_direct";

    public static void main(String[] args) throws Exception{
        // 获取通道和连接
        Connection connection = RabbitConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 创建交换机
        channel.exchangeDeclare(EXCHANGE_NAME,"direct" );

        // 发送消息
        String message = "Hello world";
        // 第二个参数为路由键 当绑定的消息队列的路由键也是这个时，才能收到信息
        channel.basicPublish(EXCHANGE_NAME, "delete", null, message.getBytes());

        // 关闭连接
        channel.close();
        connection.close();
    }
}
