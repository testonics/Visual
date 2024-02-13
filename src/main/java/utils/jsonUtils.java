package utils;

import io.restassured.path.json.JsonPath;

import java.io.File;
import java.util.Map;

public class jsonUtils {

     public Map<Object, Object> jsonToMap(File file){
         return new JsonPath(file).getMap("");
     }

    public Map<Object, Object> jsonToMap(String path){
        return new JsonPath(new File(path)).getMap("");
    }

//    public Map<Object, Object> jsonStringToMap(String jsonString){
////         return new JsonPath(file).getMap("");
//    }
}

