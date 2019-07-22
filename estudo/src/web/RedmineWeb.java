package web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class RedmineWeb {

	public static void main(String[] args) {

		String[] ids = {"18"};
		obterTickets(ids);

	}
	
	public static void obterTickets(String[] users){
		
		String base64login = "MDM5MDYzMzI0MDM6aDI2NDU3ODA="; // MDM5MDYzMzI0MDM6aDI2NDU3ODA=
		
		//String url = "http://redmine.tjpb.jus.br/issues.json?assigned_to_id="+users[0]; //+"&sort=category:desc,updated_on"
		String url = "http://redmine.tjpb.jus.br/activity.atom?key=4036929d14dab482e7ae362dd5683c8754f81c78&user_id="+users[0];
		
		Document doc;
		try {
			doc = Jsoup.connect(url)
					.header("Authorization", "Basic " + base64login)
					.timeout(1000000) 
					.ignoreContentType(true)
                    .get();
			
			//System.out.println(doc.getElementsByTag("entry"));
			
			doc.getElementsByTag("entry").forEach(e -> 
				System.out.println(e.getElementsByIndexEquals(0).get(0))
			);
			
			
			
			/*
			 * Gson gson = new Gson();
			 * 
			 * JsonObject jobj = gson.fromJson(doc.body().text(), JsonObject.class);
			 * 
			 * 
			 * jobj.get("issues").getAsJsonArray().forEach(i -> {
			 * 
			 * JsonObject j = i.getAsJsonObject(); Issue issue = new
			 * Issue(j.get("id").getAsInt(),
			 * j.get("project").getAsJsonObject().get("name").getAsString(),
			 * j.get("subject").getAsString(),
			 * j.get("fixed_version").getAsJsonObject().get("name").getAsString());
			 * System.out.println(issue); });
			 */
			

			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

}
