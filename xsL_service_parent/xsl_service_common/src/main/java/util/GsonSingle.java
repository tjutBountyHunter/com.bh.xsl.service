package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import contentApi.TimestampTypeAdapter;

import java.sql.Timestamp;

public class GsonSingle {
	private volatile static Gson gson;

	private GsonSingle(){}


	public static Gson getGson(){
		if(gson == null){
			synchronized (GsonSingle.class){
				if(gson == null){
					gson = new GsonBuilder().registerTypeAdapter(Timestamp.class,new TimestampTypeAdapter()).setDateFormat("yyyy-MM-dd HH:mm:ss").create();
				}
			}
		}
		return gson;
	}

}
