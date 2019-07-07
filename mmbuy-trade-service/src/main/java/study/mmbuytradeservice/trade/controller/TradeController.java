package study.mmbuytradeservice.trade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.mmbuytradeservice.common.constants.Constants;
import study.mmbuytradeservice.common.resp.ApiResult;
import study.mmbuytradeservice.trade.entity.TradeItem;
import study.mmbuytradeservice.trade.service.ITradeService;

import java.util.List;

@RestController
@RequestMapping("trade")
public class TradeController {

    @Autowired
    private ITradeService tradeService;

    @RequestMapping("/order")
    public ApiResult<List<TradeItem>> createOrder(@RequestBody List<TradeItem> tradeItemList){

        ApiResult<List<TradeItem>> result = new ApiResult(Constants.RESP_STATUS_OK,"订单提交成功");

        List<TradeItem> tradeItemSuccResult =tradeService.createOrder(tradeItemList);
        result.setData(tradeItemSuccResult);

        return  result;
    }
}
