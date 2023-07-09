package cn.vincent.entity;

import lombok.Builder;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 何树寒
 * @since 2023-07-09
 */
@Builder
public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 产品总数
     */
    private Integer total;

    /**
     * 产品id
     */
    private Integer productId;

    /**
     * 产品名
     */
    private String productName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "Stock{" +
            "id = " + id +
            ", total = " + total +
            ", productId = " + productId +
            ", productName = " + productName +
        "}";
    }
}
