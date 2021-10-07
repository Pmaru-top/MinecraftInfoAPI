package tax.cute.minecraftinfoapi.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.regex.Pattern;

public class Util {
    public static final String COMMON_KEY = "Content-Type";
    public static final String COMMON_VALUE = "application/json";

    public static String toTime(long timestamp) {
        return (timestamp != -1) ? new SimpleDateFormat("yyyy-MM-dd hh:mm").format(new Date(timestamp)) : "";
    }

    public static String toString(String base64) {
        return (base64 == null || base64.isEmpty()) ? null : new String(Base64.getDecoder().decode(base64.replace("\n","")));
    }

    public static boolean isLegalUsername(String name) {
        return Pattern.matches("[a-zA-Z0-9_]{3,16}", name);
    }

    public static String sendPost(String url,String key,String value,byte[] data) throws IOException {
        HttpURLConnection http = (HttpURLConnection) new URL(url).openConnection();
        http.setDoInput(true);
        http.setDoOutput(true);

        http.addRequestProperty(key, value);

        try (OutputStream out = http.getOutputStream()) {
            out.write(data);
        }

        StringBuilder text = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(http.getInputStream()))) {
            String s;
            while ((s = reader.readLine()) != null) {
                text.append(s);
            }
        }

        return text.toString();
    }

    public static int notNull(Object object) {
        return (object instanceof Integer || object instanceof Long || object instanceof Double) ? (int)object : -1;
    }

    public static long notNullLong(Object object) {
        return (object instanceof Integer || object instanceof Long || object instanceof Double) ? (long)object : -1;
    }

    public static double notNullDouble(Object object) {
        return (object instanceof Integer || object instanceof Long || object instanceof Double) ? (double)object : -1;
    }

    public static boolean isUuid(String s) {
        return s.replace("-","").length() == 32;
    }
}