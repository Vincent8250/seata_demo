package cn.vincent.handler;

import com.alibaba.csp.sentinel.slots.block.AbstractRule;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Component;

/**
 * blockHandlerClass
 * sentinel 限流异常处理类
 * 处理方法需要匹配原方法的参数和返回值 PS：方法必须时静态的
 */
@Component
public class BlockExceptionHandler {

    public static String GlobalBlockExceptionHandler(BlockException blockException) {
        AbstractRule rule = blockException.getRule();
        String resource = rule.getResource();
        return resource + "限流了";
    }
}