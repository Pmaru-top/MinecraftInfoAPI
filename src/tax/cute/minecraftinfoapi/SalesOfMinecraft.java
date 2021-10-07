package tax.cute.minecraftinfoapi;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import tax.cute.minecraftinfoapi.utils.ApiUrl;
import tax.cute.minecraftinfoapi.utils.Util;

import java.io.IOException;
import java.util.List;

public class SalesOfMinecraft {
    public static final String ITEM_SOLD_MINECRAFT = "item_sold_minecraft";
    public static final String PREPAID_CARD_REDEEMED_MINECRAFT = "prepaid_card_redeemed_minecraft";
    public static final String ITEM_SOLD_COBALT = "item_sold_cobalt";
    public static final String ITEM_SOLD_SCROLLS = "item_sold_scrolls";
    public static final String PREPAID_CARD_REDEEMED_COBALT = "prepaid_card_redeemed_cobalt";
    public static final String ITEM_SOLD_DUNGEONS = "item_sold_dungeons";

    private final long total;
    private final int last24h;
    private final double saleVelocityPerSeconds;

    public SalesOfMinecraft(
            long total,
            int last24h,
            double saleVelocityPerSeconds
    ) {
        this.total = total;
        this.last24h = last24h;
        this.saleVelocityPerSeconds = saleVelocityPerSeconds;
    }

    public static SalesOfMinecraft getSales(List<String> options) throws IOException {
        JSONArray array = new JSONArray();
        array.addAll(options);

        JSONObject json = new JSONObject();
        json.put("metricKeys",array);

        json = JSONObject.parseObject(Util.sendPost(ApiUrl.SALES,Util.COMMON_KEY,Util.COMMON_VALUE,json.toJSONString().getBytes()));

        long total = Util.notNullLong(json.getLong("total"));
        int last24h = Util.notNull(json.getInteger("last24h"));
        double saleVelocityPerSeconds = Util.notNullDouble(json.getDouble("saleVelocityPerSeconds"));

        return new SalesOfMinecraft(total,last24h,saleVelocityPerSeconds);
    }

    public double getSaleVelocityPerSeconds() {
        return saleVelocityPerSeconds;
    }

    public int getLast24h() {
        return last24h;
    }

    public long getTotal() {
        return total;
    }
}