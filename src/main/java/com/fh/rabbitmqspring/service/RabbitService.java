package com.fh.rabbitmqspring.service;

import com.fh.rabbitmqspring.domain.Student;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitService {

    // 开启监听，queues表示监听哪一个消息队列
    // 方法中的参数表示当只有消息为student时才能监听到
    @RabbitListener(queues = "atguigu")
    public void testRabbit(Student student){
        System.out.println("收到信息:"+student);
    }
}
