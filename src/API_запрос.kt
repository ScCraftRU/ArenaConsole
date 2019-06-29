open class API_запрос(internal var querty: String, internal var token: String) {

    open fun toHTTPs(): String {
        return "https://www.myarena.ru/api.php?query=$querty&token=$token"
    }

    override fun toString(): String {
        return toHTTPs()
    }
}
