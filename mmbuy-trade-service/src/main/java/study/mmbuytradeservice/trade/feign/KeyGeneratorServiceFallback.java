package study.mmbuytradeservice.trade.feign;

public class KeyGeneratorServiceFallback implements KeyGenServiceClient {

    @Override
    public String keyGenerator() {
        return null;
    }
}
