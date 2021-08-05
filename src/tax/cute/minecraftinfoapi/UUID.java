package tax.cute.minecraftinfoapi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

public class UUID {
    private String name;
    private String id;

    public UUID(String name,String id) {
        this.name = name;
        this.id = id;
    }

    public static UUID getId(String userName) throws IOException {
        String jsonText = Util.getWebText(ApiUrl.USER_NAME_TO_UUID_API + userName);
        JSONObject jsonObject = JSON.parseObject(jsonText);

        if (jsonObject == null)
            return new UUID(null,null);

        String name = jsonObject.getString("name");
        String id = jsonObject.getString("id");
        return new UUID(name,id);
    }

    public String getName() {
        return this.name;
    }

    public String getId() {
        return this.id;
    }
}
