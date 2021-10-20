package org.serverct.pay.deposit

enum class DepositType(val key: String, val typeName: String) {
    WX("wxpay", "微信"), ALIPAY("alipay", "支付宝")
}