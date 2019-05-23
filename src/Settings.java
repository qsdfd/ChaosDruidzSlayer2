import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class Settings {
	public static void main(String[] args) throws Exception {
		System.out.println(getMule());
	}
	
	private static final String CLOUDANT_URL = "https://kaisumaro.cloudant.com/druidz/settings";

	
	public static String getMule() throws Exception{
		return getFromDoc("mule", CLOUDANT_URL).split("\"")[1];
	}
	
	private static String getFromDoc(String key, String url) throws Exception {
		String response = readUrl(url);
		response = response.substring(1, response.length()-2);
		
		List<String> splits = Arrays.asList(response.split(","));
		
		return splits
				.stream()
				.filter(
						s -> s.contains(key)
						)
				.findAny().get().split(":")[1];
	}
	
	private static String readUrl(String urlString) throws Exception {
	    BufferedReader reader = null;
	    try {
	        URL url = new URL(urlString);
	        reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        StringBuffer buffer = new StringBuffer();
	        int read;
	        char[] chars = new char[1024];
	        while ((read = reader.read(chars)) != -1)
	            buffer.append(chars, 0, read); 

	        return buffer.toString();
	    } finally {
	        if (reader != null)
	            reader.close();
	    }
	}
}
