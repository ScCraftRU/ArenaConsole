import javax.net.ssl.HttpsURLConnection
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

    fun getOneLine(webAdress: String): String {
        val url: URL
        var conn: HttpsURLConnection? = null
        val rd: BufferedReader
        var line: String
        val result = StringBuilder()
        try {
            url = URL(webAdress)
            conn = url.openConnection() as HttpsURLConnection
            conn.requestMethod = "GET"
            val responseCode = conn.responseCode
            println("Код ответа сервера (RESPONSE CODE) = $responseCode")
            rd = BufferedReader(InputStreamReader(conn.inputStream))
            line = rd.readLine()
            while (line != null) {
                result.append(line)
                line = rd.readLine()
            }
            rd.close()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                conn!!.disconnect()
            } catch (e: NullPointerException) {
                e.printStackTrace()
            }

        }
        return result.toString()
    }
