package com.lly835.bestpay.service.impl.wx;

import lombok.Data;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lly835@163.com
 * 2018-05-16 20:35
 */
public class WxPaySandboxKey {

    @Data
    @Root(name = "xml")
    static class SandboxParam {

        @Element(name = "mch_id")
        private String mchId;

        @Element(name = "nonce_str")
        private String nonceStr;

        @Element(name = "sign")
        private String sign;

        public Map<String, String> buildMap() {
            Map<String, String> map = new HashMap<>();
            map.put("mch_id", this.mchId);
            map.put("nonce_str", this.nonceStr);
            return map;
        }
    }
}
