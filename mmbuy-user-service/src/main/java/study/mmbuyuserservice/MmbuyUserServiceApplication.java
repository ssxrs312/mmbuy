package study.mmbuyuserservice;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sun.awt.SunHints;

@SpringBootApplication
@EnableDiscoveryClient
public class MmbuyUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MmbuyUserServiceApplication.class, args);
	}

	/**
	 * FastJsonHttpMessageConverter
	 * @return
	 */
	@Bean
	public HttpMessageConverters fastJsonHttpMessageConverters() {
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
		HttpMessageConverter<?> converter = fastConverter;
		return new HttpMessageConverters(converter);
	}

	/**
	 * 用于properties文件占位符解析
	 * @return
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	/**
	 *
	 * @Description   密码加密/校验
	 * @Author JackWang<coder520.com>
	 * @Date 2018/3/11 17:37
	 * @Param []
	 * @Return org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
	 */
	@Bean
	public BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}


}
