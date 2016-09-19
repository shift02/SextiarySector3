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

public class BlockJsonUtil {

	public static Gson gson = new GsonBuilder().setPrettyPrinting().create();

	public static void generationBlockStateGson(File file, String itemName, String model) {

		if (!file.isDirectory()) file.getParentFile().mkdirs();

		SextiarySector3.log.info("BlockJsonUtil#generationBlockStateGson(), " + file);

		if (model == null) model = "cube_all";

		JsonObject root = new JsonObject();
		root.addProperty("forge_marker", 1);//Forge

		JsonObject defaults = new JsonObject();
		root.add("defaults", defaults);
		defaults.addProperty("model", model);//親Model
		JsonObject textures = new JsonObject();
		textures.addProperty("all", "sextiarysector3:blocks/" + itemName);
		defaults.add("textures", textures);
		defaults.addProperty("transform", "forge:default-block");
		defaults.addProperty("uvlock", false);

		JsonObject variants = new JsonObject();
		root.add("variants", variants);
		JsonArray normal = new JsonArray();
		JsonObject normalN = new JsonObject();
		normal.add(normalN);
		variants.add("normal", normal);
		JsonArray inventory = new JsonArray();
		JsonObject inventoryN = new JsonObject();
		inventory.add(inventoryN);
		variants.add("inventory", inventory);

		String jsonS = gson.toJson(root);

		OutputStreamWriter osw = null;
		try {
			osw = new OutputStreamWriter(new FileOutputStream(file));
			JsonWriter jsw = new JsonWriter(osw);
			jsw.setIndent("  ");
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
