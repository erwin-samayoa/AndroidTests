package tools;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JsonReader {
    public static Object[][] getJsonData(String path) throws IOException, ParseException {
        JSONObject object = (JSONObject) new JSONParser().parse(new FileReader(path));
        JSONArray array = (JSONArray) object.get("SMS Data");

        Object[][] returnObject = new String[array.size()][2];

        for (int i = 0; i < array.size(); i++) {
            JSONObject tmpObject = (JSONObject) array.get(i);
            //Next assignaments couldnt be done straight from the array
            //Maybe get method for nested element belong to JSONObject not to array
            returnObject[i][0] =tmpObject.get("Number");
            returnObject[i][1] =tmpObject.get("Message");
        }

        return returnObject;

    }
}
