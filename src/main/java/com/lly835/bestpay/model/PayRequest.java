package com.lly835.bestpay.model;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.alipay.request.AliPayTradeCreateRequest;
import lombok.Data;

/**
 * 支付时请求参数
 */
@Data
public class PayRequest {

    /**
     * 支付方式.
     */
    public BestPayTypeEnum payTypeEnum;

    /**
     * 订单号.
     */
    public String orderId;

    /**
     * 订单金额.
     */
    public Double orderAmount;

    /**
     * 订单名字.
     */
    public String orderName;

    /**
     * 微信openid, 仅微信公众号/小程序支付时需要
     */
    public String openid;

    /**
     * 客户端访问Ip  外部H5支付时必传，需要真实Ip
     * 20191015测试，微信h5支付已不需要真实的ip
     */
    public String spbillCreateIp;

    /**
     * 附加内容，发起支付时传入
     */
    public String attach;

    /**
     * 支付后跳转（支付宝PC网站支付）
     * 优先级高于PayConfig.returnUrl
     */
    public String returnUrl;

    /**
     * 买家支付宝账号
     * {@link AliPayTradeCreateRequest.BizContent}
     */
    public String buyerLogonId;

    /**
     * 买家的支付宝唯一用户号（2088开头的16位纯数字）
     * {@link AliPayTradeCreateRequest.BizContent}
     */
    public String buyerId;

    /**
     * 付款码
     */
    public String authCode;
}
