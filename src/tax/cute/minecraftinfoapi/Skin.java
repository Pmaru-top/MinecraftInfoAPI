package tax.cute.minecraftinfoapi;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

public class Skin {
    private String id;
    private String name;
    private long timestamp;
    private String skinUrl;
    private String capeUrl;

    public Skin(String id, String name,long timestamp, String skinUrl,String capeUrl) {
        this.id = id;
        this.name = name;
        this.timestamp = timestamp;
        this.skinUrl = skinUrl;
        this.capeUrl = capeUrl;
    }

    public static Skin getSkin(String uuid) throws IOException {
        String jsonString = Util.getWebText(ApiUrl.SKIN_API + uuid);
        JSONObject json = JSONObject.parseObject(jsonString);

        if(json == null)
            return new Skin(null,null,-1,null,null);

        JSONArray properties_array = json.getJSONArray("properties");
        JSONObject properties_object = properties_array.getJSONObject(0);
        String value = properties_object.getString("value");

        String properties_text = Util.base64toString(value);
        json = JSONObject.parseObject(properties_text);

        long timestamp = json.getLong("timestamp");
        String id = json.getString("profileId");
        String name = json.getString("profileName");

        JSONObject textures = json.getJSONObject("textures");
        JSONObject textures_skin = textures.getJSONObject("SKIN");
        String skinUrl = textures_skin.getString("url");

        JSONObject textures_cape = textures.getJSONObject("CAPE");

        String capeUrl;
        if (textures_cape == null) {
            capeUrl = null;
        } else {
            capeUrl = textures_cape.getString("url");
        }

        return new Skin(id,name,timestamp,skinUrl,capeUrl);
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public String getTime() {
        return Util.timestampToTime(getTimestamp());
    }

    public String getSkinUrl() {
        return this.skinUrl;
    }

    public byte[] getSkinBytes() throws IOException{
        return Util.getWebBytes(this.skinUrl);
    }

    public String getCapeUrl() {
        return this.capeUrl;
    }

    public byte[] getCapeBytes() throws IOException{
        return Util.getWebBytes(this.capeUrl);
    }
}
