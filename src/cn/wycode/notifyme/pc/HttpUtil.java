package cn.wycode.notifyme.pc;

import sun.misc.IOUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;

/**
 * Created by wWX383516 on 2016/10/31.
 */
public class HttpUtil {

    public static StringBuilder Get(String urlString) {
        try {
            URL url = new URL(urlString);

            InetSocketAddress addr = new InetSocketAddress("172.19.6.47", 8080);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, addr);
            URLConnection conn = url.openConnection(proxy);


            String headerKey = "Proxy-Authorization";
            String headerValue = "Basic ejAwMTkxNDg3OlRpZ2VyMiFKSkVISkpFSA==";
            conn.setRequestProperty(headerKey, headerValue);

            InputStream in = conn.getInputStream();
            StringBuilder response = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
            response.append(reader.readLine());

            System.out.println(response.toString());
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return new StringBuilder(e.toString());
        }

    }
}
