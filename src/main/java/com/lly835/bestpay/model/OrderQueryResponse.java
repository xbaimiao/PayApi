package com.lly835.bestpay.model;

import com.lly835.bestpay.enums.OrderStatusEnum;
import lombok.Builder;
import lombok.Data;

/**
 * 订单查询结果
 * Created by 廖师兄
 * 2018-06-04 16:52
 */
@Data
@Builder
public class OrderQueryResponse {

    /**
     * 订单状态
     */
    public OrderStatusEnum orderStatusEnum;

    /**
     *第三方支付的流水号
     */
    public String outTradeNo;

    /**
     * 附加内容，发起支付时传入
     */
    public String attach;

    /**
     * 错误原因
     */
    public String resultMsg;

    /**
     * 订单号
     */
    public String orderId;

    /**
     * 支付完成时间
     */
    public String finishTime;
}
