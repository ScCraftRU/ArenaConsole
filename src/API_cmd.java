import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class API_cmd extends API_запрос {
    String cmd;

    API_cmd(String токен) {
        super("consolecmd", токен);
    }

    @Override
    public String toHTTPs() {
        String cmd = this.cmd;
        try {
            cmd = URLEncoder.encode(this.cmd,"UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "https://www.myarena.ru/api.php?query=" + querty + "&cmd=" + cmd + "&token=" +  token;
    }
}
