package study.mmbuytradeservice.trade.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "key-generator",fallback = KeyGeneratorServiceFallback.class)
public interface KeyGenServiceClient {

    @RequestMapping("/keygen")
    String keyGenerator();
}
