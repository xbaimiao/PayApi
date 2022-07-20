package com.xbaimiao.pay.utils

import com.lly835.bestpay.config.AliPayConfig
import com.lly835.bestpay.config.WxPayConfig
import com.lly835.bestpay.service.impl.BestPayServiceImpl
import com.xbaimiao.pay.PayApi

object Service {

    lateinit var wxPayConfig: WxPayConfig

    lateinit var aliPayConfig: AliPayConfig

    fun load() {
        wxPayConfig = object : WxPayConfig() {
            init {
                val section = PayApi.key.getConfigurationSection("pay_wx")!!
                appId = section.getString("appid")
                mchId = section.getString("mchid")
                mchKey = section.getString("mchKey")
                appSecret = section.getString("appSecret")
                notifyUrl = section.getString("notifyUrl")
            }
        }
        aliPayConfig = object : AliPayConfig() {
            init {
                val section = PayApi.key.getConfigurationSection("pay_ali")!!
                appId = section.getString("appid")
                privateKey = section.getString("privateKey")
                aliPayPublicKey = section.getString("aliPayPublicKey")
            }
        }
        bestPayService.setWxPayConfig(wxPayConfig)
        bestPayService.setAliPayConfig(aliPayConfig)
    }

    val bestPayService = BestPayServiceImpl()

}