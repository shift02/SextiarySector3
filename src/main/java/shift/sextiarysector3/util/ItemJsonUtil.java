package shift.sextiarysector3.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

}
