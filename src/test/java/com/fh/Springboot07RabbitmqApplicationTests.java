package com.fh;

import com.fh.rabbitmqspring.domain.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot07RabbitmqApplicationTests {

	// 这是自动为我们创建的一个类，用于操作rabbitmq
	@Autowired
	RabbitTemplate rabbitTemplate;

	@Test
	public void contextLoads() {
	}

	@Test
	public void sendMsgToDirect(){
		// 这个方法需要我们自定义创建一个Message类，这个类中有消息内容和消息头
//		rabbitTemplate.send(exchange, routingkey, messs);

		// 这个方法会将object对象自动序列化然后发送出去
		// 第一个参数指定交换机
		// 第二个参数指定路由键
		// 第三个参数指定要发送的内容
		rabbitTemplate.convertAndSend("exchange:direct", "atguigu",
				new Student("曾磊","20","江苏扬州"));
	}

	@Test
	public void getMsgFromDirect(){
		// 指定要获取哪一个消息队列的消息
		Object o = rabbitTemplate.receiveAndConvert("guilixueyuan.news");
		System.out.println(o);
	}

	// 测试fanout
	@Test
	public void sendMsgToFanout() {
		rabbitTemplate.convertAndSend("exchange:fanout", "",
				new Student("吴兴玉","20","江苏连云港"));
	}

	@Test
	public void getMsgFromFanout(){
		// 指定要获取哪一个消息队列的消息
		Object o = rabbitTemplate.receiveAndConvert("atguigu.news");
		System.out.println(o);
	}

	// 测试topic
	@Test
	public void sendMsgToTopic() {
		rabbitTemplate.convertAndSend("exchange:topic", "a.news",
				new Student("吴兴玉","20","江苏连云港"));
	}

	@Test
	public void getMsgFromTopict(){
		// 指定要获取哪一个消息队列的消息
		Object o = rabbitTemplate.receiveAndConvert("atguigu.news");
		System.out.println(o);
	}

	@Autowired
	AmqpAdmin amqpAdmin;

	@Test
	public void testAmqpAdmin(){
		// 创建交换机 DirectExchange表示点对点交换机 如果想设置广播或选择，可以选择对应的java类
		amqpAdmin.declareExchange(new DirectExchange("amqpAdmin:direct"));
		// 创建消息队列
		amqpAdmin.declareQueue(new Queue("amqpAdmin.queue"));
		// 绑定
		//String destination 目的地，即输出到哪
		// Binding.DestinationType destinationType 绑定的类型 这是个枚举类型:QUEUE和EXCHANGE
		// String exchange 交换机的名字
		// String routingKey 路由键
		// Map<String, Object> arguments 一些消息参数，如果没有则为null
		amqpAdmin.declareBinding(
				new Binding("amqpAdmin.queue", Binding.DestinationType.QUEUE,
						"amqpAdmin:direct","aaa.queue",null));
	}
}
