package tax.cute.minecraftinfoapi.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Http {
    private final int code;
    private final byte[] bytes;

    public Http(
            int code,
            byte[] bytes
    ) {
        this.code = code;
        this.bytes = bytes;
    }

    public static Http getHttp(String url) throws IOException {
        HttpURLConnection http = (HttpURLConnection)new URL(url).openConnection();
        int code = http.getResponseCode();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try(InputStream in = (code == HttpURLConnection.HTTP_OK) ? http.getInputStream() : http.getErrorStream()) {
            if(in == null)return new Http(code,null);
            int i;
            while ((i = in.read()) != -1) {
                out.write(i);
            }
        }

        return new Http(code,out.toByteArray());
    }

    public int getCode() {
        return code;
    }

    public String getTextString(){
        return (bytes != null) ? new String(bytes) : null;
    }

    public byte[] getBytes(){
        return bytes;
    }
}