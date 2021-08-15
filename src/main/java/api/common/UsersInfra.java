package api.common;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONException;
import org.testng.Assert;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class UsersInfra {
    public static String usersJsonPath = "testData/users/";

    public static String listUsersPath = "/api/users?page=";
    public static String userPath = "/api/users/";
    public static String delayedListUsersPath = "/api/users?delay=";

    public static Date currentDate;

    public static void checkJsonFileResponse(String response, String jsonFileName) throws FileNotFoundException, JSONException {
        Methods.checkJsonFileResponse(response, usersJsonPath, jsonFileName);
    }

    public static void checkJsonStringResponse(String response, String expectedJsonResponse) throws JSONException {
        Methods.checkJsonStringResponse(response, expectedJsonResponse);
    }

    public static String getListOfUsers(String page) throws IOException {
        return Methods.get(listUsersPath + page);
    }

    public static String getUser(String user) throws IOException {
        return Methods.get(userPath + user);
    }

    public static String getErrorUser(String user, String responseCode) throws IOException {
        return Methods.getError(userPath + user, responseCode);
    }

    public static String getDelayedListOfUsers(String delay) throws IOException {
        return Methods.get(delayedListUsersPath + delay);
    }

    public static String createUser(JsonObject request) throws IOException {
        return Methods.postCreate(userPath, request);
    }

    public static String updateUser(String user, JsonObject request) throws IOException {
        return Methods.put(userPath + user, request);
    }

    public static String deleteUser(String user) throws IOException {
        return Methods.delete(userPath + user);
    }

    public static JsonObject createRequest(String name, String job) {
        return Methods.createNameJobRequest(name, job);
    }

    public static void checkCreateUserJsonFileResponse(String response, String jsonFileName) throws FileNotFoundException, JSONException, ParseException {
        Methods.checkJsonFileResponse(response, usersJsonPath, jsonFileName);
        JsonObject actualResponse = JsonParser.parseString(response).getAsJsonObject();
        checkId(actualResponse.get("id").getAsInt());
        checkDate(actualResponse.get("createdAt").getAsString());
    }

    public static void checkUpdateUserJsonFileResponse(String response, String jsonFileName) throws FileNotFoundException, JSONException, ParseException {
        Methods.checkJsonFileResponse(response, usersJsonPath, jsonFileName);
        JsonObject actualResponse = JsonParser.parseString(response).getAsJsonObject();
        checkDate(actualResponse.get("updatedAt").getAsString());
    }

    private static void checkId(int id) {
        boolean correctId = (id >= 0) && (id <= 999);
        Assert.assertTrue(correctId, "The actual id: " + id + " is not in the range");
    }

    private static void checkDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date requestDate = format.parse(date);
        Assert.assertTrue(requestDate.after(currentDate), "The actual request date: " + requestDate + " is not correct");
    }
}
