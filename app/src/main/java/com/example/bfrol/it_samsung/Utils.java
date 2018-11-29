package com.example.bfrol.it_samsung;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
abstract class Utils {
    static String getRequest(String targetUrl) throws IOException {
        URL url = new URL(targetUrl);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            if(urlConnection.getResponseCode()!=HttpURLConnection.HTTP_OK)
            {
                throw new IOException(urlConnection.getResponseMessage());
            }
            InputStreamReader reader = new InputStreamReader(urlConnection.getInputStream());
            BufferedReader r = new BufferedReader(reader);
            String line;
            StringBuilder s = new StringBuilder();
            while ((line = r.readLine()) != null) {
                s.append(line);
            }
            return s.toString();
        } finally {
            urlConnection.disconnect();
        }
    }
}

