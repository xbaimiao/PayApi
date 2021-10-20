package com.lly835.bestpay.config;

import java.util.Objects;

/**
 * Created by this on 2019/9/8 16:31
 */
public class AliPayConfig extends PayConfig {
    /**
     * appId
     */
    public String appId;
    /**
     * 商户私钥
     */
    public String privateKey;
    /**
     * 支付宝公钥
     */
    public String aliPayPublicKey;

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setAliPayPublicKey(String aliPayPublicKey) {
        this.aliPayPublicKey = aliPayPublicKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getAppId() {
        return appId;
    }

    public String getAliPayPublicKey() {
        return aliPayPublicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void check() {
        Objects.requireNonNull(appId, "config param 'appId' is null.");
        Objects.requireNonNull(privateKey, "config param 'publicKey' is null.");
        Objects.requireNonNull(aliPayPublicKey, "config param 'aliPayPublicKey' is null.");

        if (appId.length() > 32) {
            throw new IllegalArgumentException("config param 'appId' is incorrect: size exceeds 32.");
        }
    }
}
