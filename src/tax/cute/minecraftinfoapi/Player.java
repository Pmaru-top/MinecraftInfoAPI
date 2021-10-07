package tax.cute.minecraftinfoapi;

import com.alibaba.fastjson.JSONObject;
import tax.cute.minecraftinfoapi.utils.*;

import java.io.IOException;
import java.net.HttpURLConnection;

public class Player {
    private final String name;
    private final String uuid;

    public Player(
            String name,
            String uuid
    ) {
        this.name = name;
        this.uuid = uuid;
    }

    public static Player getPlayer(String name) throws IOException,CommonException {
        if (name == null || name.isEmpty() || !Util.isLegalUsername(name))throw new CommonException("Illegal username");
        Http http = Http.getHttp(ApiUrl.PLAYER
                .replace("%name",name)
        );

        if (http.getCode() != HttpURLConnection.HTTP_OK)throw new CommonException("No this player");

        JSONObject json = JSONObject.parseObject(http.getTextString());
        return new Player(json.getString("name"),json.getString("id"));
    }

    public String getName() {
        return name;
    }

    public String getUuid() {
        return uuid;
    }
}