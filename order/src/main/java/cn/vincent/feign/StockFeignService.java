package cn.vincent.feign;

import cn.vincent.entity.Order;
import cn.vincent.entity.Stock;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
@FeignClient(value = "store-service", path = "/stock")
public interface StockFeignService {

    @PostMapping("/deductionInventory")
    String deductionInventory(@RequestBody Order order);

    @PostMapping("/getStock")
    List<Stock> getStock();
}
