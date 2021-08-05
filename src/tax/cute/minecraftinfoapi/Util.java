package tax.cute.minecraftinfoapi;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class Util {
    public static byte[] getWebBytes(String url) throws IOException{
        URL u = new URL(url);
        URLConnection uc = u.openConnection();
        InputStream in = uc.getInputStream();
        byte[] bytes = new byte[in.available()];
        in.read(bytes);
        in.close();
        return bytes;
    }
    public static String getWebText(String url) throws IOException{
        return getWebText(url,"UTF-8");
    }

    public static String getWebText(String url,String charsets) throws IOException {
        return new String(getWebBytes(url), charsets);
    }

    public static String timestampToTime(long timestamp) {
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        return simple.format(new Date(timestamp));
    }

    public static String base64toString(String base64) {
        byte[] bytes = Base64.getDecoder().decode(base64);
        return new String(bytes);
    }
}
