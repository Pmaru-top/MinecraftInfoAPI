package tax.cute.minecraftinfoapi;

import com.alibaba.fastjson.JSONObject;
import tax.cute.minecraftinfoapi.utils.ApiUrl;
import tax.cute.minecraftinfoapi.utils.Http;
import tax.cute.minecraftinfoapi.utils.Util;

import java.io.IOException;
import java.net.HttpURLConnection;

public class Profile {
    private final String uuid;
    private final String name;
    private final String skinUrl;
    private final String capeUrl;
    private final String model;

    public Profile(
            String uuid,
            String name,
            String skinUrl,
            String capeUrl,
            String model
    ) {
        this.uuid = uuid;
        this.name = name;
        this.skinUrl = skinUrl;
        this.capeUrl = capeUrl;
        this.model = model;
    }

    public static Profile getProfile(String uuid) throws IOException,CommonException {
        if (uuid == null || uuid.isEmpty() || !Util.isUuid(uuid))
            throw new CommonException("Input error");
        Http http = Http.getHttp(ApiUrl.PROFILE
                .replace("%uuid", uuid)
        );
        if (http.getCode() != HttpURLConnection.HTTP_OK)
            throw new CommonException("no this player");

        JSONObject json = JSONObject.parseObject(http.getTextString());

        String base64Str = json.getJSONArray("properties").getJSONObject(0).getString("value");
        json = JSONObject.parseObject(Util.toString(base64Str));

        uuid = json.getString("profileId");
        String name = json.getString("profileName");

        json = json.getJSONObject("textures");
        JSONObject data = json.getJSONObject("SKIN");
        String skinUrl = (data != null) ? data.getString("url") : null;

        data = (data != null) ? data.getJSONObject("metadata") : null;
        String model = (data != null) ? data.getString("model") : null;

        data = json.getJSONObject("CAPE");
        String capeUrl = (data == null) ? null : data.getString("url");

        return new Profile(uuid,name,skinUrl,capeUrl,model);
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getCapeUrl() {
        return capeUrl;
    }

    public String getSkinUrl() {
        return skinUrl;
    }

    public String getModel() {
        return model;
    }

    public byte[] getSkinBytes() throws IOException{
        return (skinUrl == null) ? null : Http.getHttp(skinUrl).getBytes();
    }

    public byte[] getCapeBytes() throws IOException{
        return (capeUrl == null) ? null : Http.getHttp(capeUrl).getBytes();
    }
}