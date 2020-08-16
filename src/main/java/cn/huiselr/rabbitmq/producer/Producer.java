package cn.huiselr.rabbitmq.producer;

import cn.huiselr.utils.MqUtils;
import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author HusielR
 * @Date 2020/8/8
 * @Description
 */
public class Producer {

    @Test
    public void producer() throws IOException, TimeoutException {
        Connection connection = MqUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("huiselr-pr", false, false, false, null);
        channel.basicPublish("","huiselr-pr",null,"hello world!!".getBytes());
        MqUtils.closeChannelAndConnection(channel,connection);
    }

    @Test
    public void consumer() throws IOException, TimeoutException {
        Connection connection = MqUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("huiselr-pr", false, false, false, null);
        channel.basicConsume("huiselr-pr",true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("comsumer messgae -> " + new String(body).toString());
            }
        });
    }

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = MqUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("huiselr-pr", false, false, false, null);
        channel.basicConsume("huiselr-pr",true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("comsumer messgae -> " + new String(body).toString());
            }
        });
    }
}
