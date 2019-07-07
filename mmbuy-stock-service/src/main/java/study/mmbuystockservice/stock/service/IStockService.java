package study.mmbuystockservice.stock.service;

import study.mmbuystockservice.stock.entity.StockReduce;

import java.util.List;
import java.util.Map;

public interface IStockService {

    int queryStock(long skuId);

    Map<Long, Integer> reduceStock(List<StockReduce> stockReduceList);
}
