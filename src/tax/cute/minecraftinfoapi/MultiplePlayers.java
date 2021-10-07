package tax.cute.minecraftinfoapi;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import tax.cute.minecraftinfoapi.utils.ApiUrl;
import tax.cute.minecraftinfoapi.utils.Util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiplePlayers {
    public Map<String,String> getPlayers(List<String> players) throws IOException,CommonException {
        if(players.isEmpty())
            throw new CommonException("Players is empty");
        if(players.size() > 10)
            throw new CommonException("Players too big,It cannot be greater than 10");
        JSONArray array = new JSONArray();
        array.addAll(players);

        array = JSONArray.parseArray(Util.sendPost(ApiUrl.PLAYERS,"Content-Type","application/json",array.toJSONString().getBytes()));

        Map<String,String> data = new HashMap<>();
        array.forEach(object -> {
            JSONObject jsonObject = (JSONObject)object;
            data.put(jsonObject.getString("id"),jsonObject.getString("name"));
        });

        return data;
    }
}
