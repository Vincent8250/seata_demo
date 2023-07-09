package cn.vincent.controller;

import cn.vincent.entity.Order;
import cn.vincent.entity.Stock;
import cn.vincent.feign.StockFeignService;
import cn.vincent.service.IOrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Value("${user.name}")
    String userName;

    @Autowired
    IOrderService iOrderService;

    @Autowired
    StockFeignService stockFeignService;

    @GetMapping("/userName")
    public String getUserName() {
        return userName;
    }

    @GetMapping("/add")
    public String addOrder() {
        Order build = Order.builder()
                .productId(1)
                .productName("帽子")
                .quantity(2)
                .price(new BigDecimal(30))
                .totalPrice(new BigDecimal(60))
                .build();
        Boolean save = iOrderService.save(build);
        stockFeignService.deductionInventory(build);
        return save.toString();
    }

    @GetMapping("/getStock")
    public String getStock() throws JsonProcessingException {
        List<Stock> stock = stockFeignService.getStock();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(stock);
    }
}
