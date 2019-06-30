import java.net.URLEncoder
import java.io.UnsupportedEncodingException

class API_cmd internal constructor(токен: String) : API_запрос("consolecmd", токен) {
    internal var cmd = ""

    override fun toHTTPs(): String {
        var cmd = this.cmd
        try {
            cmd = URLEncoder.encode(this.cmd, "UTF8")
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }

        return "https://www.myarena.ru/api.php?query=$querty&cmd=$cmd&token=$токен"
    }
}
