package djinons.weatherapptry;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by djinons on 30.10.2017..
 */

public class WeatherApi {
    private static String API_URL = "http://api.openweathermap.org/data/2.5/weather?q={novi sad}&appid=b0dd972e07b66d7c7c646fdf8b805b2c";
    private static String ICON_URL = "http://openweathermap.org/img/w/";


    public String getWeather() {
        HttpURLConnection conn = null ;
        InputStream istream = null;

        try {
            conn = (HttpURLConnection) ( new URL(API_URL)).openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.connect();

            StringBuffer buffer = new StringBuffer();
            istream = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(istream));
            String line ;
            while (  (line = br.readLine()) != null )
                buffer.append(line + "\r\n");

            istream.close();
            conn.disconnect();
            return buffer.toString();
        }
        catch(Throwable t) {
            t.printStackTrace();
        }
        finally {
            try { istream.close(); } catch(Throwable t) {}
            try { conn.disconnect(); } catch(Throwable t) {}
        }
        return null;
    }

    public byte[] getWeatherIcon(String code) {
        HttpURLConnection conn = null ;
        InputStream istream = null;
        try {
            conn = (HttpURLConnection) ( new URL(ICON_URL + code + ".png")).openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.connect();

            InputStream stream = new URL(ICON_URL + code + ".png").openStream();
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            while ( stream.read(buffer) != -1)
                bos.write(buffer);

            return bos.toByteArray();
        }
        catch(Throwable t) {
            t.printStackTrace();
        }
        finally {
            try { istream.close(); } catch(Throwable t) {}
            try { conn.disconnect(); } catch(Throwable t) {}
        }

        return null;

    }
}

