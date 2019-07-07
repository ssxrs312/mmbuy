package study.mmbuytradeservice.trade.handler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

@Configuration
public class OrderKeyExpriedHandler {

    @Bean
    public RedisMessageListenerContainer configRedisMessageListenerContainer(RedisConnectionFactory redisConnectionFactory){
        RedisMessageListenerContainer listenerContainer = new RedisMessageListenerContainer();
        listenerContainer.setConnectionFactory(redisConnectionFactory);
        listenerContainer.addMessageListener((message,listener)->{
            //处理key过期事件逻辑,new String(message.getBody())就是设置进去的orderNo
            System.out.println("------redis过期事件"+new String(message.getBody()));

        }, new PatternTopic("__keyevent@*__:expired"));
        return listenerContainer;
    }
}
