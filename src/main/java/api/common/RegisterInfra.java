package api.common;

import com.google.gson.JsonObject;
import org.json.JSONException;

import java.io.FileNotFoundException;
import java.io.IOException;

public class RegisterInfra {
    public static String registerJsonPath = "testData/register/";
    public static String registerPath = "/api/register/";

    public static void checkJsonFileResponse(String response, String jsonFileName) throws FileNotFoundException, JSONException {
        Methods.checkJsonFileResponse(response, registerJsonPath, jsonFileName);
    }

    public static String postRegister(JsonObject request) throws IOException {
        return Methods.post(registerPath, request);
    }

    public static String postErrorRegister(JsonObject request, String responseCode) throws IOException {
        return Methods.postError(registerPath, request, responseCode);
    }

    public static JsonObject createRequest(String email, String password) {
        return Methods.createEmailPasswordRequest(email, password);
    }
}
