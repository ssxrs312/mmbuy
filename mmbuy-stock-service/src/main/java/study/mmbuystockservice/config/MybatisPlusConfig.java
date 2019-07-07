package study.mmbuystockservice.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScans({ @MapperScan(basePackages = "study.mmbuytradeservice.product.mapper"),
        @MapperScan(basePackages = "study.mmbuytradeservice.trade.mapper"),
        @MapperScan(basePackages = "study.mmbuystockservice.stock.mapper")})
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        // 开启 count 的 join 优化,只针对 left join !!!
//        return new PaginationInterceptor().setCountSqlParser(new JsqlParserCountOptimize(true));
        return new PaginationInterceptor();
    }

}
