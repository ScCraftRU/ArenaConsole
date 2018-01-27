import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class API_ответ {

    String status;
    String console_log;
    String message;

    public static API_ответ fromJSON(String JSON) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.fromJson(JSON, API_ответ.class);
    }

    boolean успех() {
        return status.equals("OK");
    }

    @Override
    public String toString() {
        if (успех()) {
            return console_log;
        }
        return message;
    }
}
