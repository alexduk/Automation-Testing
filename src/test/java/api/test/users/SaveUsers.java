package api.test.users;

import api.common.Infra;
import api.common.UsersInfra;
import com.google.gson.JsonObject;
import org.json.JSONException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

@Test(dataProvider = "testData", dataProviderClass = common.GetData.class)
public class SaveUsers extends Infra {

    public static Date currentDate;

    @BeforeMethod
    public void getCurrentDate() {
        UsersInfra.currentDate = Calendar.getInstance().getTime();
    }

    public void createUser(String testID, String description, String name, String job, String jsonFileName) throws IOException, JSONException, ParseException {
        JsonObject request = UsersInfra.createRequest(name, job);
        String response = UsersInfra.createUser(request);
        UsersInfra.checkCreateUserJsonFileResponse(response, jsonFileName);
    }

    public void updateUser(String testID, String description, String user, String name, String job, String jsonFileName) throws IOException, JSONException, ParseException {
        JsonObject request = UsersInfra.createRequest(name, job);
        String response = UsersInfra.updateUser(user, request);
        UsersInfra.checkUpdateUserJsonFileResponse(response, jsonFileName);
    }

    public void deleteUser(String testID, String description, String user) throws IOException {
        UsersInfra.deleteUser(user);
    }
}
