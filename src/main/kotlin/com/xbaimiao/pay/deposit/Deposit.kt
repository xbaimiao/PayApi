package com.xbaimiao.pay.deposit

import com.google.zxing.BarcodeFormat
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter
import com.lly835.bestpay.model.PayResponse
import org.bukkit.Bukkit
import org.bukkit.scheduler.BukkitRunnable
import com.xbaimiao.pay.events.CreateOrderEvent
import com.xbaimiao.pay.events.PaySuccessEvent
import com.xbaimiao.pay.utils.Trade
import taboolib.common.platform.function.submit
import taboolib.module.nms.NMSMap
import taboolib.module.nms.sendMap
import taboolib.platform.BukkitPlugin
import taboolib.platform.util.sendLang
import java.awt.image.BufferedImage

class Deposit(
    val info: DepositInfo,
    var time: Int = 300,
    val timeout: Deposit.() -> Unit = {},
    val back: Deposit.() -> Unit
) {

    /**
     * 订单号
     */
    lateinit var orderId: String

    /**
     * 返回的订单数据
     */
    lateinit var response: PayResponse

    val player = info.player

    init {
        submit(async = true) {
            Bukkit.getPluginManager().callEvent(CreateOrderEvent(this@Deposit))
            response = when (info.type) {
                DepositType.ALIPAY -> Trade.aliNative(info.name, info.amount)
                DepositType.WX -> Trade.wxNative(info.name, info.amount)
            }
            if (response.codeUrl == null) {
                player.sendLang("fail")
                return@submit
            }
            orderId = response.orderId
            object : BukkitRunnable() {
                var timer = 0
                var isPay = false
                override fun run() {
                    if ((timer++ * 2) > time) {
                        timeout.invoke(this@Deposit)
                        cancel()
                    }
                    if (query() && !isPay) {
                        isPay = true
                        Bukkit.getPluginManager().callEvent(PaySuccessEvent(this@Deposit))
                        player.updateInventory()
                        back.invoke(this@Deposit)
                        cancel()
                    }
                }
            }.runTaskTimerAsynchronously(BukkitPlugin.getInstance(), 40, 40)
            player.sendMap(codeQR(), hand = NMSMap.Hand.MAIN)
        }
    }

    /**
     * 查询订单支付状态
     */
    private fun query(): Boolean {
        return when (info.type) {
            DepositType.ALIPAY -> Trade.aliQuery(orderId)
            DepositType.WX -> Trade.wxQuery(orderId)
        }
    }

    /**
     * 生成的二维码图片
     */
    fun codeQR(): BufferedImage {
        val qrCodeWriter1 = QRCodeWriter()
        val bitMatrix1: BitMatrix = qrCodeWriter1.encode(this.response.codeUrl, BarcodeFormat.QR_CODE, 128, 128)
        val width = bitMatrix1.width
        val height = bitMatrix1.height
        val image = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
        for (i in 0 until width) {
            for (j in 0 until height) {
                image.setRGB(i, j, if (bitMatrix1[i, j]) -0x1000000 else 0XFFFFFF)
            }
        }
        return image
    }

}