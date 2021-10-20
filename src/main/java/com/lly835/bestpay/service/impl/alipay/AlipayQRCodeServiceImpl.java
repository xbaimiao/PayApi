package com.lly835.bestpay.service.impl.alipay;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import com.lly835.bestpay.constants.AliPayConstants;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.alipay.AliPayApi;
import com.lly835.bestpay.model.alipay.request.AliPayTradeCreateRequest;
import com.lly835.bestpay.model.alipay.response.AliPayOrderCreateResponse;
import com.lly835.bestpay.utils.JsonUtil;
import com.lly835.bestpay.utils.MapUtil;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Created by 廖师兄
 */
public class AlipayQRCodeServiceImpl extends AliPayServiceImpl {

    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(AliPayConstants.ALIPAY_GATEWAY_OPEN)
            .addConverterFactory(GsonConverterFactory.create(
                    //下划线驼峰互转
                    new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
            ))
            .client(new OkHttpClient.Builder()
//                    .addInterceptor((new HttpLoggingInterceptor()
//                            .setLevel(HttpLoggingInterceptor.Level.BODY)))
                    .build()
            )
            .build();

    private final Retrofit devRetrofit = new Retrofit.Builder()
            .baseUrl(AliPayConstants.ALIPAY_GATEWAY_OPEN_DEV)
            .addConverterFactory(GsonConverterFactory.create(
                    //下划线驼峰互转
                    new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
            ))
            .client(new OkHttpClient.Builder()
//                    .addInterceptor((new HttpLoggingInterceptor()
//                            .setLevel(HttpLoggingInterceptor.Level.BODY)))
                    .build()
            )
            .build();

    @Override
    public PayResponse pay(PayRequest request) {
        AliPayTradeCreateRequest aliPayOrderQueryRequest = new AliPayTradeCreateRequest();
        aliPayOrderQueryRequest.setMethod(AliPayConstants.ALIPAY_TRADE_QRCODE_PAY);
        aliPayOrderQueryRequest.setAppId(aliPayConfig.getAppId());
        aliPayOrderQueryRequest.setTimestamp(LocalDateTime.now().format(formatter));
        aliPayOrderQueryRequest.setNotifyUrl(aliPayConfig.getNotifyUrl());
        AliPayTradeCreateRequest.BizContent bizContent = new AliPayTradeCreateRequest.BizContent();
        bizContent.setOutTradeNo(request.getOrderId());
        bizContent.setTotalAmount(request.getOrderAmount());
        bizContent.setSubject(request.getOrderName());

        aliPayOrderQueryRequest.setBizContent(JsonUtil.toJsonWithUnderscores(bizContent).replaceAll("\\s*", ""));
        aliPayOrderQueryRequest.setSign(AliPaySignature.sign(MapUtil.object2MapWithUnderline(aliPayOrderQueryRequest), aliPayConfig.getPrivateKey()));

        Call<AliPayOrderCreateResponse> call;
        if (aliPayConfig.isSandbox()) {
            call = devRetrofit.create(AliPayApi.class).tradeCreate((MapUtil.object2MapWithUnderline(aliPayOrderQueryRequest)));
        } else {
            call = retrofit.create(AliPayApi.class).tradeCreate((MapUtil.object2MapWithUnderline(aliPayOrderQueryRequest)));
        }
        Response<AliPayOrderCreateResponse> retrofitResponse = null;
        try {
            retrofitResponse = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert retrofitResponse != null;
        if (!retrofitResponse.isSuccessful()) {
            throw new RuntimeException("【支付宝创建订单】网络异常. alipay.trade.precreate");
        }
        assert retrofitResponse.body() != null;
        AliPayOrderCreateResponse.AlipayTradeCreateResponse response = retrofitResponse.body().getAlipayTradePrecreateResponse();
        if (!response.getCode().equals(AliPayConstants.RESPONSE_CODE_SUCCESS)) {
            throw new RuntimeException("【支付宝创建订单】alipay.trade.precreate. code=" + response.getCode() + ", returnMsg=" + response.getMsg() + String.format("|%s|%s", response.getSubCode(), response.getSubMsg()));
        }

        PayResponse payResponse = new PayResponse();
        payResponse.setOutTradeNo(response.getTradeNo());
        payResponse.setOrderId(response.getOutTradeNo());
        payResponse.setCodeUrl(response.getQrCode());
        return payResponse;
    }
}
