import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class NetGet {
    public static String getOneLine(String webAdress) {
        URL url;
        HttpsURLConnection conn = null;
        BufferedReader rd;
        String line;
        StringBuilder result = new StringBuilder();
        try {
            url = new URL(webAdress);
            conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            int responseCode = conn.getResponseCode();
            System.out.println("Код ответа сервера (RESPONSE CODE) = " + responseCode);
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.disconnect();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        return result.toString();
    }
}
