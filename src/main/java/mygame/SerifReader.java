package mygame;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

public class SerifReader {
    public static List<Serif> load(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Serif>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

