package shift.sextiarysector3.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;

import shift.sextiarysector3.SextiarySector3;

public class ItemJsonUtil {

    public static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void generationItemGson(File file, String itemName, String parent) {

        if (!file.isDirectory()) file.getParentFile().mkdirs();

        SextiarySector3.log.info("ItemJsonUtil#generationItemGson(), " + file);

        if (parent == null) parent = "item/generated";

        JsonObject root = new JsonObject();
        root.addProperty("parent", parent);//親Model
        JsonObject textures = new JsonObject();
        textures.addProperty("layer0", "sextiarysector3:items/" + itemName);
        root.add("textures", textures);

        String jsonS = gson.toJson(root);

        OutputStreamWriter osw = null;
        try {
            osw = new OutputStreamWriter(new FileOutputStream(file));
            JsonWriter jsw = new JsonWriter(osw);
            jsw.setIndent("    ");
            gson.toJson(root, root.getClass(), jsw);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (osw != null)
                try {
                osw.close();
                } catch (IOException e) {
                e.printStackTrace();
                }
        }

    }

    public static void generationAnimationItemGson(File file, File itemModel, String itemName, int size) {

        if (!file.isDirectory()) file.getParentFile().mkdirs();

        SextiarySector3.log.info("ItemJsonUtil#generationItemGson(), " + file);

        float count = 1.0f / size;

        String parent = "item/generated";

        JsonObject root = new JsonObject();
        root.addProperty("parent", parent);//親Model
        JsonObject textures = new JsonObject();
        textures.addProperty("layer0", "sextiarysector3:items/" + itemName);
        root.add("textures", textures);

        JsonArray overrides = new JsonArray();
        root.add("overrides", overrides);
        float dataNumber = 0;
        for (int i = 0; i < size; i++) {

            JsonObject oRoot = new JsonObject();
            overrides.add(oRoot);

            JsonObject predicate = new JsonObject();
            oRoot.add("predicate", predicate);

            predicate.addProperty("data", dataNumber);

            oRoot.addProperty("model", "sextiarysector3:item/" + itemName + "_" + String.format("%02d", i));

            dataNumber += count;
        }

        String jsonS = gson.toJson(root);

        OutputStreamWriter osw = null;
        try {
            osw = new OutputStreamWriter(new FileOutputStream(file));
            JsonWriter jsw = new JsonWriter(osw);
            jsw.setIndent("    ");
            gson.toJson(root, root.getClass(), jsw);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (osw != null)
                try {
                osw.close();
                } catch (IOException e) {
                e.printStackTrace();
                }
        }

        //テクスチャ指定用
        for (int i = 0; i < size; i++) {

            File f = new File(itemModel, itemName + "_" + String.format("%02d", i) + ".json");
            generationItemGson(f, itemName + "_" + String.format("%02d", i), null);

        }

    }

}
