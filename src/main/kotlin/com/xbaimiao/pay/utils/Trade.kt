package com.xbaimiao.pay.utils

import com.lly835.bestpay.enums.BestPayPlatformEnum
import com.lly835.bestpay.enums.BestPayTypeEnum
import com.lly835.bestpay.enums.OrderStatusEnum
import com.lly835.bestpay.model.OrderQueryRequest
import com.lly835.bestpay.model.PayRequest
import com.lly835.bestpay.model.PayResponse
import java.util.Calendar.*

object Trade {

    @JvmStatic
    fun createOutTradeNo(): String {
        val calendar = getInstance()
        return "${calendar.get(YEAR)}${calendar.get(MONTH) + 1}${calendar.get(DATE)}" + (System.currentTimeMillis() + (Math.random() * 10000000L).toLong()).toString()
    }

    /**
     * 调起微信支付
     */
    fun wxNative(orderName: String, amount: Double): PayResponse {
        val request = PayRequest()
        val orderId = createOutTradeNo()
        request.payTypeEnum = BestPayTypeEnum.WXPAY_NATIVE
        request.orderId = orderId
        request.orderName = orderName
        request.orderAmount = amount
        val response = Service.bestPayService.pay(request)
        response.orderId = orderId
        response.outTradeNo = orderId
        response.orderAmount = amount
        return response
    }

    /**
     * 调起支付宝支付
     */
    fun aliNative(orderName: String, amount: Double): PayResponse {
        val request = PayRequest()
        val orderId = createOutTradeNo()
        request.payTypeEnum = BestPayTypeEnum.ALIPAY_QRCODE
        request.orderId = orderId
        request.orderName = orderName
        request.orderAmount = amount
        val response = Service.bestPayService.pay(request)
        response.orderId = orderId
        response.outTradeNo = orderId
        response.orderAmount = amount
        return response
    }

    /**
     * 查询支付宝订单
     */
    fun aliQuery(orderId: String): Boolean {
        val request = OrderQueryRequest()
        request.platformEnum = BestPayPlatformEnum.ALIPAY
        request.orderId = orderId
        val response = Service.bestPayService.query(request) ?: return false
        return response.orderStatusEnum == OrderStatusEnum.SUCCESS
    }

    /**
     * 查询微信订单
     */
    fun wxQuery(orderId: String): Boolean {
        val request = OrderQueryRequest()
        request.platformEnum = BestPayPlatformEnum.WX
        request.orderId = orderId
        val response = Service.bestPayService.query(request) ?: return false
        return response.orderStatusEnum == OrderStatusEnum.SUCCESS
    }

}