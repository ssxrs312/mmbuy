package study.mmbuyuserservice.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.ShardedJedis;
import study.mmbuyuserservice.common.constants.Parameters;

import java.security.Key;

@Configuration
@EnableRedisHttpSession //(maxInactiveIntervalInSeconds = 604800)//session超时
public class HttpSessionConfig {

    @Autowired
    private Parameters parameters;

    @Bean
    public HttpSessionStrategy httpSessionStrategy(){
        return  new HeaderHttpSessionStrategy();
    }

    @Bean
    public JedisConnectionFactory connectionFactory(){

        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();

        String redisHost = parameters.getRedisNode().split(":")[0];
        int redisPort = Integer.valueOf(parameters.getRedisNode().split(":")[1]);

        connectionFactory.setTimeout(2000);
        connectionFactory.setHostName(redisHost);
        connectionFactory.setPort(redisPort);
//        connectionFactory.setPassword(parameters.getRedisAuth());

        return connectionFactory;
    }







}
