package cn.vincent.service.impl;

import cn.vincent.entity.Product;
import cn.vincent.mapper.ProductMapper;
import cn.vincent.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 何树寒
 * @since 2023-07-15
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

}
