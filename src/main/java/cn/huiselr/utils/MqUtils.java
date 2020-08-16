package cn.huiselr.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author HusielR
 * @Date 2020/8/8
 * @Description mq工具类
 */
public class MqUtils {

    /**
     * 类加载时初始化mq连接工厂
     */
    private static ConnectionFactory connectionFactory;
    static {
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("47.97.212.150");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/huiselr");
        connectionFactory.setUsername("pr");
        connectionFactory.setPassword("592513");
    }

    /**
     * 获取mq的连接
     * @return
     * @throws IOException
     * @throws TimeoutException
     */
    public static Connection getConnection() throws IOException, TimeoutException {
        return connectionFactory.newConnection();
    }

    /**
     * 关闭通道和连接
     * @param channel
     * @param connection
     * @throws IOException
     * @throws TimeoutException
     */
    public static void closeChannelAndConnection(Channel channel,Connection connection) throws IOException, TimeoutException {
        try {}finally{
            if (null != channel){
                channel.close();
            }
            if (null != connection){
                connection.close();
            }
        }
    }
}
