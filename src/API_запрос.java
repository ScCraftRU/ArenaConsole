public class API_запрос {
    String querty;
    String token;

    public API_запрос(String тип, String токен) {
        querty = тип;
        token = токен;
    }

    public String toHTTPs() {
        return "https://www.myarena.ru/api.php?query=" + querty + "&token=" + token;
    }

    @Override
    public String toString() {
        return toHTTPs();
    }
}
