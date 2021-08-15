package api.common;

import com.google.gson.JsonObject;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.Assert;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;

public class Methods extends Infra {

    public static String get(String path) throws IOException {
        HttpURLConnection connection = connection(path, "GET");
        checkResponseCode(connection, 200);
        return jsonResponse(connection);
    }

    public static String getError(String path, String responseCode) throws IOException {
        HttpURLConnection connection = connection(path, "GET");
        checkResponseCode(connection, Integer.parseInt(responseCode));
        return jsonResponse(connection);
    }

    public static String delete(String path) throws IOException {
        HttpURLConnection connection = connection(path, "DELETE");
        checkResponseCode(connection, 204);
        return jsonResponse(connection);
    }

    public static String post(String path, JsonObject request) throws IOException {
        HttpURLConnection connection = connection(path, "POST");
        postData(connection, request);
        checkResponseCode(connection, 200);
        return jsonResponse(connection);
    }

    public static String postCreate(String path, JsonObject request) throws IOException {
        HttpURLConnection connection = connection(path, "POST");
        postData(connection, request);
        checkResponseCode(connection, 201);
        return jsonResponse(connection);
    }

    public static String postError(String path, JsonObject request, String responseCode) throws IOException {
        HttpURLConnection connection = connection(path, "POST");
        postData(connection, request);
        checkResponseCode(connection, Integer.parseInt(responseCode));
        return jsonResponse(connection);
    }

    public static String put(String path, JsonObject request) throws IOException {
        HttpURLConnection connection = connection(path, "PUT");
        putData(connection, request);
        checkResponseCode(connection, 200);
        return jsonResponse(connection);
    }

    public static void putData(HttpURLConnection connection, JsonObject request) throws IOException {
        write(connection, request);
    }

    public static void postData(HttpURLConnection connection, JsonObject request) throws IOException {
        write(connection, request);
    }

    public static void checkResponseCode(HttpURLConnection connection, int expectedResponseCode) throws IOException {
        int actualResponseCode = connection.getResponseCode();
        Assert.assertEquals(actualResponseCode, expectedResponseCode);
    }

    public static void checkJsonFileResponse(String actualJsonResponse, String jsonPath, String jsonFileName) throws FileNotFoundException, JSONException {
        JsonObject expectedJsonResponse = readJson(jsonPath, jsonFileName);
        JSONAssert.assertEquals(expectedJsonResponse.toString(), actualJsonResponse, JSONCompareMode.LENIENT);
    }

    public static void checkJsonStringResponse(String actualJsonResponse, String expectedJsonResponse) throws JSONException {
        JSONAssert.assertEquals(expectedJsonResponse, actualJsonResponse, JSONCompareMode.LENIENT);
    }

    public static JsonObject createEmailPasswordRequest(String email, String password) {
        JsonObject request = new JsonObject();
        request.addProperty("email", email);
        request.addProperty("password", password);
        return request;
    }

    public static JsonObject createNameJobRequest(String name, String job) {
        JsonObject request = new JsonObject();
        request.addProperty("name", name);
        request.addProperty("job", job);
        return request;
    }
}
