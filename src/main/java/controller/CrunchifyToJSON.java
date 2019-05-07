package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class CrunchifyToJSON {

    public String arrayListToJSON(ArrayList list) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();

        return gson.toJson(list);
    }

    public String listToJSON(List list) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();

        return gson.toJson(list);
    }
}
