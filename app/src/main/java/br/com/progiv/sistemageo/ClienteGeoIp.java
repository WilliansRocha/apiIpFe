package br.com.progiv.sistemageo;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONException;
import java.io.IOException;
import org.json.JSONObject;

public class ClienteGeoIp {
    private static String readStream(InputStream in){
        BufferedReader r = new BufferedReader(new InputStreamReader(in));
        StringBuilder total = new StringBuilder();
        String line;

        try {
            while ((line = r.readLine()) != null) {
                total.append(line).append('n');
            }
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return total.toString();
    }

    private static String request(String stringUrl) throws IOException{
        URL url = null;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(stringUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            return readStream(in);
        }  finally {
            urlConnection.disconnect();
        }
    }
    public static Localizacao localizar(String ip) throws JSONException, IOException{
        String resp = request("http://freegeoip.net/json/" + ip);
        JSONObject obj = new JSONObject(resp);
        String code = obj.getString("country_code");
        String name = obj.getString("country_name");
        String state = obj.getString("country_state");
        return new Localizacao(code, name, state);

    }
}

