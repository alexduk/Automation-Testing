package api.test.register;

import api.common.RegisterInfra;
import com.google.gson.JsonObject;
import org.json.JSONException;
import org.testng.annotations.Test;

import java.io.IOException;

@Test(dataProvider = "testData", dataProviderClass = common.GetData.class)
public class Register {

    public void successfulRegister(String testID, String description, String email, String password, String jsonFileName) throws IOException, JSONException {
        JsonObject request = RegisterInfra.createRequest(email, password);
        String response = RegisterInfra.postRegister(request);
        RegisterInfra.checkJsonFileResponse(response, jsonFileName);
    }

    public void unsuccessfulRegister(String testID, String description, String email, String password, String responseCode, String jsonFileName) throws IOException, JSONException {
        JsonObject request = RegisterInfra.createRequest(email, password);
        String response = RegisterInfra.postErrorRegister(request, responseCode);
        RegisterInfra.checkJsonFileResponse(response, jsonFileName);
    }
}
