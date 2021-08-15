package api.common;

import org.json.JSONException;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ResourcesInfra {
    public static String resourcesJsonPath = "testData/resources/";

    public static String resourcesPath = "/api/unknown/";

    public static void checkJsonFileResponse(String response, String jsonFileName) throws FileNotFoundException, JSONException {
        Methods.checkJsonFileResponse(response, resourcesJsonPath, jsonFileName);
    }

    public static void checkJsonStringResponse(String response, String expectedJsonResponse) throws JSONException {
        Methods.checkJsonStringResponse(response, expectedJsonResponse);
    }

    public static String getListOfResources() throws IOException {
        return Methods.get(resourcesPath);
    }

    public static String getResource(String resource) throws IOException {
        return Methods.get(resourcesPath + resource);
    }

    public static String getErrorResource(String resource, String responseCode) throws IOException {
        return Methods.getError(resourcesPath + resource, responseCode);
    }
}
