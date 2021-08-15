package api.test.login;

import api.common.LoginInfra;
import com.google.gson.JsonObject;
import org.json.JSONException;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.io.IOException;

@Test(dataProvider = "testData", dataProviderClass = common.GetData.class)
public class Login {

    public void successfulLogin(String testID, String description, String email, String password, String jsonFileName) throws IOException, JSONException {
        JsonObject request = LoginInfra.createRequest(email, password);
        String response = LoginInfra.postLogin(request);
        LoginInfra.checkJsonFileResponse(response, jsonFileName);
        throw new SkipException("Skipping this exception");
    }

    public void unSuccessfulLogin(String testID, String description, String email, String password, String responseCode, String jsonFileName) throws IOException, JSONException {
        JsonObject request = LoginInfra.createRequest(email, password);
        String response = LoginInfra.postErrorLogin(request, responseCode);
        LoginInfra.checkJsonFileResponse(response, jsonFileName);
    }
}
