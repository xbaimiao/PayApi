package com.lly835.bestpay.model;

import com.lly835.bestpay.enums.BestPayPlatformEnum;
import lombok.Data;

import java.net.URI;

/**
 * 支付时的同步/异步返回参数
 */
@Data
public class PayResponse {

    /**
     * 以下参数只有微信支付会返回 (在微信付款码支付使用)
     * returnCode returnMsg resultCode errCode errCodeDes
     */
    public String returnCode;

    public String returnMsg;

    public String resultCode;

    public String errCode;

    public String errCodeDes;

    public String prePayParams;

    public URI payUri;

    /** 以下字段仅在微信h5支付返回. */
    public String appId;

    public String timeStamp;

    public String nonceStr;

    public String signType;

    public String paySign;

    /**
     * 以下字段在微信异步通知下返回.
     */
    public Double orderAmount;

    public String orderId;

    /**
     * 第三方支付的流水号
     */
    public String outTradeNo;

    /**
     * 以下支付是h5支付返回
     */
    public String mwebUrl;

    /**
     * AliPay  pc网站支付返回的body体，html 可直接嵌入网页使用
     */
    public String body;

    /**
     * 扫码付模式二用来生成二维码
     */
    public String codeUrl;

    /**
     * 附加内容，发起支付时传入
     */
    public String attach;

    public BestPayPlatformEnum payPlatformEnum;

    public String prepayId;

    /**
     * 支付宝App支付返回的请求参数信息
     */
    public String orderInfo;
}
