package cn.vincent.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.vincent.entity.Order;
import cn.vincent.entity.Product;
import cn.vincent.entity.Stock;
import cn.vincent.handler.BlockExceptionHandler;
import cn.vincent.service.IProductService;
import cn.vincent.service.IStockService;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.AbstractRule;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import io.seata.spring.annotation.GlobalTransactional;
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

    @Autowired
    IProductService iProductService;

    /**
     * 扣减库存
     *
     * @param order
     * @return
     */
    @PostMapping("/deductionInventory")
    @SentinelResource(value = "deductionInventory")
    public Boolean deductionInventory(@RequestBody Order order) throws Exception {
        Boolean flag = false;
        QueryWrapper<Stock> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", order.getProductId());
        Stock stock = iStockService.getOne(queryWrapper);
        if (ObjectUtil.isNotEmpty(stock)) {
            UpdateWrapper updateWrapper = new UpdateWrapper();
            updateWrapper.eq("product_id", order.getProductId());
            Stock build = Stock.builder()
                    .total(stock.getTotal() - order.getQuantity())
                    .build();
            flag = iStockService.update(build, updateWrapper);
        }
        return flag;
    }

    @PostMapping("/getStock")
    public List<Stock> getStock() {
        List<Stock> stock = iStockService.list();
        return stock;
    }

    //region sentinel 测试

    /**
     * sentinel 测试
     *
     * @return
     */
    @GetMapping("/getStock")
    @SentinelResource(value = "sentinelTest", blockHandlerClass = BlockExceptionHandler.class, blockHandler = "GlobalBlockExceptionHandler")
    public String sentinelTest() {
        return "正常请求";
    }

    /**
     * 限流处理
     *
     * @param blockException
     * @return
     */
    public String GlobalBlockExceptionHandler(BlockException blockException) {
        AbstractRule rule = blockException.getRule();
        String resource = rule.getResource();
        return resource + "限流了";
    }

    //endregion


    //region seata 测试

    /**
     * 添加库存
     *
     * @param proName
     * @param total
     * @return
     */
    @GlobalTransactional
    @GetMapping("/addStock/{proName}/{total}")
    public String addStock(@PathVariable("proName") String proName, @PathVariable("total") Integer total) {
        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.eq("name", proName);
        Product product = iProductService.getOne(productQueryWrapper);
        if (ObjectUtil.isNotEmpty(product)) {
            Stock stock = Stock.builder()
                    .productId(product.getId())
                    .productName(product.getName())
                    .total(total)
                    .build();
            Boolean flag = iStockService.save(stock);
            return flag.toString();
        } else
            return Boolean.FALSE.toString();
    }
    //endregion
}
