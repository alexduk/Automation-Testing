package api.test.users;

import api.common.Infra;
import api.common.UsersInfra;
import org.json.JSONException;
import org.testng.annotations.Test;

import java.io.IOException;

@Test(dataProvider = "testData", dataProviderClass = common.GetData.class)
public class ListUsers extends Infra {

    public void listUsers(String testID, String description, String page, String jsonFileName) throws IOException, JSONException {
        String response = UsersInfra.getListOfUsers(page);
        UsersInfra.checkJsonFileResponse(response, jsonFileName);
    }

    public void singleUser(String testID, String description, String user, String jsonFileName) throws IOException, JSONException {
        String response = UsersInfra.getUser(user);
        UsersInfra.checkJsonFileResponse(response, jsonFileName);
    }

    public void userNotFound(String testID, String description, String user, String responseCode, String expectedJsonResponse) throws IOException, JSONException {
        String response = UsersInfra.getErrorUser(user, responseCode);
        UsersInfra.checkJsonStringResponse(response, expectedJsonResponse);
    }

    public void delayedListOfUsers(String testID, String description, String delay, String jsonFileName) throws IOException, JSONException {
        String response = UsersInfra.getDelayedListOfUsers(delay);
        UsersInfra.checkJsonFileResponse(response, jsonFileName);
    }
}
