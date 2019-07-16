import com.google.gson.GsonBuilder

class API_консоль: API_ответ() {

    var console_log = ""

    override fun toString(): String {
        if (успех()) return console_log
        else return message
    }

    companion object {

        fun fromJSON(JSON: String): API_консоль {
            val builder = GsonBuilder()
            val gson = builder.create()
            return gson.fromJson(JSON, API_консоль::class.java)
        }
    }
}
