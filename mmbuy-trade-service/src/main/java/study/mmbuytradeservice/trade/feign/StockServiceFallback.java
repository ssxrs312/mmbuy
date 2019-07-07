package study.mmbuytradeservice.trade.feign;

import study.mmbuytradeservice.common.constants.Constants;
import study.mmbuytradeservice.common.resp.ApiResult;
import study.mmbuytradeservice.trade.entity.StockReduce;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class StockServiceFallback implements StockServiceClient {
    @Override
    public ApiResult<Map<Long, Integer>> reduceStock(List<StockReduce> stockReduceList) {
        ApiResult<Map<Long, Integer>> result = new ApiResult(Constants.RESP_STATUS_INTERNAL_ERROR,"请求失败,请稍后再试");
        result.setData(Collections.EMPTY_MAP);
        return result;
    }
}
