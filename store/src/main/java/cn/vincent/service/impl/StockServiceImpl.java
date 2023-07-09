package cn.vincent.service.impl;

import cn.vincent.entity.Stock;
import cn.vincent.mapper.StockMapper;
import cn.vincent.service.IStockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 何树寒
 * @since 2023-07-09
 */
@Service
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements IStockService {

}
