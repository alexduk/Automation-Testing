package api.test.resources;

import api.common.ResourcesInfra;
import org.json.JSONException;
import org.testng.annotations.Test;

import java.io.IOException;

@Test(dataProvider = "testData", dataProviderClass = common.GetData.class)
public class ListResources {

    public void listResources(String testID, String description, String jsonFileName) throws IOException, JSONException {
        String response = ResourcesInfra.getListOfResources();
        ResourcesInfra.checkJsonFileResponse(response, jsonFileName);
    }

    public void singleResource(String testID, String description, String resource, String jsonFileName) throws IOException, JSONException {
        String response = ResourcesInfra.getResource(resource);
        ResourcesInfra.checkJsonFileResponse(response, jsonFileName);
    }

    public void resourceNotFound(String testID, String description, String resource, String responseCode, String expectedJsonResponse) throws IOException, NumberFormatException, JSONException {
        String response = ResourcesInfra.getErrorResource(resource, responseCode);
        ResourcesInfra.checkJsonStringResponse(response, expectedJsonResponse);
    }
}

