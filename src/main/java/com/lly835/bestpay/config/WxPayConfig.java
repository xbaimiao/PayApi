package com.lly835.bestpay.config;

import org.bukkit.craftbukkit.libs.org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;

/**
 * Created by 廖师兄
 * 2017-07-02 13:58
 */
public class WxPayConfig extends PayConfig {

    /**
     * 公众号appId
     */
    public String appId;

    /**
     * 公众号appSecret
     */
    public String appSecret;

    /**
     * 小程序appId
     */
    public String miniAppId;

    /**
     * app应用appid
     */
    public String appAppId;

    /**
     * 商户号
     */
    public String mchId;

    /**
     * 商户密钥
     */
    public String mchKey;

    /**
     * 商户证书路径
     */
    public String keyPath;

    /**
     * 证书内容
     */
    public SSLContext sslContext;

    public String getAppId() {
        return appId;
    }

    public String getMiniAppId() {
        return miniAppId;
    }

    public String getMchKey() {
        return mchKey;
    }

    public String getMchId() {
        return mchId;
    }

    public String getKeyPath() {
        return keyPath;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public String getAppAppId() {
        return appAppId;
    }

    @Override
    public String getNotifyUrl() {
        return super.getNotifyUrl();
    }

    @Override
    public String getReturnUrl() {
        return super.getReturnUrl();
    }

    public SSLContext getSslContext() {
        return sslContext;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setMiniAppId(String miniAppId) {
        this.miniAppId = miniAppId;
    }

    public void setMchKey(String mchKey) {
        this.mchKey = mchKey;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public void setKeyPath(String keyPath) {
        this.keyPath = keyPath;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public void setAppAppId(String appAppId) {
        this.appAppId = appAppId;
    }

    @Override
    public void setNotifyUrl(String notifyUrl) {
        super.setNotifyUrl(notifyUrl);
    }

    public void setSslContext(SSLContext sslContext) {
        this.sslContext = sslContext;
    }

    /**
     * 初始化证书
     * @return
     */
    public SSLContext initSSLContext() {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(this.keyPath);
        } catch (IOException e) {
            throw new RuntimeException("读取微信商户证书文件出错", e);
        }

        try {
            KeyStore keystore = KeyStore.getInstance("PKCS12");
            char[] partnerId2charArray = mchId.toCharArray();
            keystore.load(inputStream, partnerId2charArray);
            this.sslContext = SSLContexts.custom().loadKeyMaterial(keystore, partnerId2charArray).build();
            return this.sslContext;
        } catch (Exception e) {
            throw new RuntimeException("证书文件有问题，请核实！", e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
