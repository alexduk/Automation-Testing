package api.common;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Infra {

    public static String urlStr = "https://reqres.in";

    public static HttpURLConnection connection(String path, String method) throws IOException {
        URL url = new URL(urlStr + path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod(method);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");

        return connection;
    }

    public static void write(HttpURLConnection connection, JsonObject request) throws IOException {
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = request.toString().getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
    }

    public static String jsonResponse(HttpURLConnection connection) throws IOException {
        BufferedReader br;
        if (100 <= connection.getResponseCode() && connection.getResponseCode() <= 399) {
            br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
        } else {
            br = new BufferedReader(new InputStreamReader(connection.getErrorStream(), StandardCharsets.UTF_8));
        }
        StringBuilder response = new StringBuilder();
        String responseLine;
        while ((responseLine = br.readLine()) != null) {
            response.append(responseLine.trim());
        }
        System.out.println(response);
        return response.toString();
    }

    public static JsonObject readJson(String jsonPath, String jsonFileName) throws FileNotFoundException {
        FileReader reader = new FileReader(jsonPath + jsonFileName);
        return JsonParser.parseReader(reader).getAsJsonObject();
    }
}
