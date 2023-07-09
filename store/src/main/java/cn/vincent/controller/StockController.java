package cn.vincent.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.vincent.entity.Order;
import cn.vincent.entity.Stock;
import cn.vincent.service.IStockService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 何树寒
 * @since 2023-07-09
 */
@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    IStockService iStockService;

    @PostMapping("/deductionInventory")
    public String deductionInventory(@RequestBody Order order) {
        Boolean flag = false;
        QueryWrapper<Stock> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", order.getId());
        Stock stock = iStockService.getOne(queryWrapper);
        if (ObjectUtil.isNotEmpty(stock)) {
            UpdateWrapper updateWrapper = new UpdateWrapper();
            updateWrapper.eq("id", order.getId());
            Stock build = Stock.builder()
                    .total(stock.getTotal() - order.getQuantity())
                    .build();
            flag = iStockService.update(build, updateWrapper);
        }
        return flag.toString();
    }

    @PostMapping("/getStock")
    public List<Stock> getStock() {
        List<Stock> stock = iStockService.list();
        return stock;
    }
}
