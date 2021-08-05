package tax.cute.minecraftinfoapi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.*;

public class Status {
    private String minecraft_net;
    private String session_minecraft_net;
    private String account_mojang_com;
    private String authserver_mojang_com;
    private String sessionserver_mojang_com;
    private String api_mojang_com;
    private String textures_minecraft_net;
    private String mojang_com;

    public Status(
            String minecraft_net,
            String session_minecraft_net,
            String account_mojang_com,
            String authserver_mojang_com,
            String sessionserver_mojang_com,
            String api_mojang_com,
            String textures_minecraft_net,
            String mojang_com
    ) {
        this.minecraft_net = minecraft_net;
        this.session_minecraft_net = session_minecraft_net;
        this.account_mojang_com = account_mojang_com;
        this.authserver_mojang_com = authserver_mojang_com;
        this.sessionserver_mojang_com = sessionserver_mojang_com;
        this.api_mojang_com = api_mojang_com;
        this.textures_minecraft_net = textures_minecraft_net;
        this.mojang_com = mojang_com;
    }

    public static Status getStatus() throws IOException {
        //get json string
        String jsonText = Util.getWebText(ApiUrl.STATUS_API);
        if(jsonText == null) return null;
        JSONArray array = JSON.parseArray(jsonText);
        if(array == null) return null;

        String minecraft_net = null;
        String session_minecraft_net = null;
        String account_mojang_com = null;
        String authserver_mojang_com = null;
        String sessionserver_mojang_com = null;
        String api_mojang_com = null;
        String textures_minecraft_net = null;
        String mojang_com = null;

        //Traverse the array and get the data
        for (int i = 0; i < array.size(); i++) {
            JSONObject json = array.getJSONObject(i);
            if(json.isEmpty()) return null;
            Set<String> set = json.keySet();
            List<String> list = new ArrayList<>(set);

            if (list.get(0).equals("minecraft.net"))
                minecraft_net = json.getString("minecraft.net");

            if(list.get(0).equals("session.minecraft.net"))
                session_minecraft_net = json.getString("session.minecraft.net");

            if(list.get(0).equals("account.mojang.com"))
                account_mojang_com = json.getString("account.mojang.com");

            if(list.get(0).equals("authserver.mojang.com"))
                authserver_mojang_com = json.getString("authserver.mojang.com");

            if(list.get(0).equals("sessionserver.mojang.com"))
                sessionserver_mojang_com = json.getString("sessionserver.mojang.com");

            if(list.get(0).equals("api.mojang.com"))
                api_mojang_com = json.getString("api.mojang.com");

            if(list.get(0).equals("textures.minecraft.net"))
                textures_minecraft_net = json.getString("textures.minecraft.net");

            if(list.get(0).equals("mojang.com"))
                mojang_com = json.getString("mojang.com");
        }
        return new Status(
                minecraft_net,
                session_minecraft_net,
                account_mojang_com,
                authserver_mojang_com,
                sessionserver_mojang_com,
                api_mojang_com,
                textures_minecraft_net,
                mojang_com
                );
    }

    public String getMinecraft_net() {
        return this.minecraft_net;
    }

    public String getSession_minecraft_net() {
        return this.session_minecraft_net;
    }

    public String getAccount_mojang_com() {
        return this.account_mojang_com;
    }

    public String getAuthserver_mojang_com() {
        return this.authserver_mojang_com;
    }

    public String getSessionserver_mojang_com() {
        return this.sessionserver_mojang_com;
    }

    public String getApi_mojang_com() {
        return this.api_mojang_com;
    }

    public String getTextures_minecraft_net() {
        return this.textures_minecraft_net;
    }

    public String getMojang_com() {
        return this.mojang_com;
    }
}
