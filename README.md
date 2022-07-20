# PayApi 快速开始

- [ ] 配置key.yml

 ```yml
# 微信密匙
pay_wx:
  # 微信appid
  appid: "wx5exxxxxxxxx"
  # 商户号
  mchid: "12453452"
  # 商户密匙
  mchKey: ""
  # 开发密匙
  appSecret: ""
  # 回调url 如果你没有服务器就填百度即可
  notifyUrl: "http://www.baidu.com"
# 支付宝密匙
pay_ali:
  # 支付宝appid
  appid: ""
  #商户私有key
  privateKey: ""
  #支付宝共有key
  aliPayPublicKey: ""

```

# 简单的支付接口 API

## 使用示例

```kotlin

fun test() {
    val player = Bukkit.getPlayer("player")
    // 创建订单时的商品名
    val name = "礼包购买"
    // 金额
    val price = 100
    buildDeposit(
        player = player,
        name = name,
        price,
        DepositType.WX
    ) {
        //玩家支付成功后执行的代码
        //TODO
    }
}

```

```java 
public static void test() {
        Player player = Bukkit.getPlayer("xbaimiao");
        // 商品名称
        String name = "测试商品";
        // 价格
        int price = 100;
        // 超时未支付时间
        int time = 300;
        // 订单号
        String orderId = Trade.createOutTradeNo();

        BuildDepositKt.buildDeposit(player, name, price, DepositType.WX, time, orderId, deposit -> {
            // 超时未支付代码
            return null;
        }, deposit -> {
            // 支付成功代码
            return null;
        });
    }
```

# 配置文件

```yaml 
#测试商品 商品名
test:
  # 价格
  amount: 0.01
  # 支付成功后执行的命令
  commands:
    - "say 支付成功"
```

- 这是一个测试的商品 输入命令 /payapi %player_name% test 即可购买

# 命令

- /payapi reload 重载商品
- /payapi %player% %商品名% 创建一个购买商品的订单

# 下载

[GitHub](https://github.com/)