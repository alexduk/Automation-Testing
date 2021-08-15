package api.common;

import com.google.gson.JsonObject;
import org.json.JSONException;

import java.io.FileNotFoundException;
import java.io.IOException;

public class LoginInfra {
    public static String loginJsonPath = "testData/login/";
    public static String loginPath = "/api/login/";

    public static void checkJsonFileResponse(String response, String jsonFileName) throws FileNotFoundException, JSONException {
        Methods.checkJsonFileResponse(response, loginJsonPath, jsonFileName);
    }

    public static String postLogin(JsonObject request) throws IOException {
        return Methods.post(loginPath, request);
    }

    public static String postErrorLogin(JsonObject request, String responseCode) throws IOException {
        return Methods.postError(loginPath, request, responseCode);
    }

    public static JsonObject createRequest(String email, String password) {
        return Methods.createEmailPasswordRequest(email, password);
    }
}
