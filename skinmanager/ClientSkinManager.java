package yourclient.skinmanager;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;

import net.minecraft.client.resources.SkinManager.SkinAvailableCallback;

public class ClientSkinManager {

	public static ClientSkinManager instance = new ClientSkinManager();
	
	private static JsonObject skinJson;
	private static JsonParser jsonParser = new JsonParser();
	
	public void getSkin(GameProfile gameprofile, Map skinMap, SkinAvailableCallback callback) {
		if(skinJson == null) {
			try {
				HttpsURLConnection connection = (HttpsURLConnection) new URL("Verilerin Alınacağı Site").openConnection();
				connection.connect();
				skinJson = jsonParser.parse(new InputStreamReader(connection.getInputStream())).getAsJsonObject();
			}catch(Exception e) {
				System.out.println("Veriler alınamadı.");
				e.printStackTrace();
				return;
			}
			try {
				if(skinJson.has(gameprofile.getName())) {
					skinMap.put(Type.SKIN, new MinecraftProfileTexture(skinJson.get(gameprofile.getName()).getAsString(), null));
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
