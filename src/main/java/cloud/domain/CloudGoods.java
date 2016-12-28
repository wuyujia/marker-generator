package cloud.domain;

import java.math.BigDecimal;

/**
 * Created by apple on 16/12/23.
 */
public class CloudGoods {

    private String goodsName;
    private BigDecimal price;
    private Integer createTime;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "CloudGoods{" +
                "goodsName='" + goodsName + '\'' +
                ", price=" + price +
                ", createTime=" + createTime +
                '}';
    }
}
