package tax.cute.minecraftinfoapi;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import tax.cute.minecraftinfoapi.utils.ApiUrl;
import tax.cute.minecraftinfoapi.utils.Http;
import tax.cute.minecraftinfoapi.utils.Util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class NameHistory {
    private final List<NameHistoryData> nameHistoryData;
    private final String uuid;

    public NameHistory(
            List<NameHistoryData> nameHistoryData,
            String uuid
    ) {
        this.nameHistoryData = nameHistoryData;
        this.uuid = uuid;
    }

    public static NameHistory getNameHistory(String uuid) throws IOException,CommonException {
        if (uuid == null || uuid.isEmpty() || !Util.isUuid(uuid))
            throw new CommonException("Input error");
        Http http = Http.getHttp(ApiUrl.NAME_HISTORY
                .replace("%uuid", uuid)
        );
        if (http.getCode() != HttpURLConnection.HTTP_OK)
            throw new CommonException("no this player");

        List<NameHistoryData> nameHistoryData = new ArrayList<>();

        JSONArray array = JSONArray.parseArray(http.getTextString());
        array.forEach(object -> {
            JSONObject json = (JSONObject) object;
            nameHistoryData.add(new NameHistoryData(json.getString("name"), Util.notNullLong(json.getLong("changedToAt"))));
        });

        return new NameHistory(nameHistoryData,uuid);
    }

    public List<NameHistoryData> getNameHistoryData() {
        return nameHistoryData;
    }

    public String getInitName() {
        return nameHistoryData.get(0).getName();
    }

    public String getCurrentName() {
        return nameHistoryData.get(nameHistoryData.size() - 1).getName();
    }

    public String getUuid() {
        return uuid;
    }
}