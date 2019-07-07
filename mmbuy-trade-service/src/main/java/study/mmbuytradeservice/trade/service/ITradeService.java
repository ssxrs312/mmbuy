package study.mmbuytradeservice.trade.service;

import study.mmbuytradeservice.trade.entity.TradeItem;

import java.util.List;

public interface ITradeService {

    List<TradeItem> createOrder(List<TradeItem> tradeItemList);
}
