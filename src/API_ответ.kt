import com.google.gson.GsonBuilder

open class API_ответ() {

    var status = ""
    var message = ""

    override fun toString(): String {
        return message
    }

    companion object {

        fun fromJSON(JSON: String): API_ответ {
            val builder = GsonBuilder()
            val gson = builder.create()
            return gson.fromJson(JSON, API_ответ::class.java)
        }
    }
}