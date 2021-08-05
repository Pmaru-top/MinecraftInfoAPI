package tax.cute.minecraftinfoapi;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NameHistory {
    private List<NameHistoryList> data;
    private List<String> nameList;
    public NameHistory(List<NameHistoryList> data, List<String> nameList) {
        this.data = data;
        this.nameList = nameList;
    }

    public static NameHistory getNameHistory(String uuid){
        String jsonText;
        try {
            jsonText = Util.getWebText(ApiUrl.UUID_TO_NAME_HISTORY.replace("%id", uuid));
        } catch (IOException e) {
            return new NameHistory(null,null);
        }

        JSONArray array = JSONArray.parseArray(jsonText);

        List<NameHistoryList> data = new ArrayList<>();
        List<String> nameList = new ArrayList<>();

        for (int i = 0; i < array.size(); i++) {
            JSONObject jsonObject = array.getJSONObject(i);
            String name;
            if(jsonObject.containsKey("name"))
            name = jsonObject.getString("name");
            else
                name = null;

            long timestamp;
            if(jsonObject.containsKey("changedToAt"))
            timestamp = jsonObject.getLong("changedToAt");
            else
                timestamp = -1;
            
            nameList.add(name);

            NameHistoryList list = new NameHistoryList(name,timestamp);

            data.add(list);
        }
        return new NameHistory(data,nameList);
    }

    public List<NameHistoryList> getData() {
        return this.data;
    }

    public List<String> getNameList() {
        return this.nameList;
    }

    public String getInitName() {
        return this.nameList.get(0);
    }

    public String getNowName() {
        return nameList.get(nameList.size() - 1);
    }

    public static class NameHistoryList {
        private String name;
        private long timestamp;

        public NameHistoryList(String name, long timestamp) {
            this.name = name;
            this.timestamp = timestamp;
        }

        public String getName() {
            return this.name;
        }

        public long getTimestamp() {
            return this.timestamp;
        }

        public String getTime() {
            return Util.timestampToTime(timestamp);
        }
    }
}


