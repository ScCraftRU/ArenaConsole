import java.util.Scanner;

public class Main {

    private static Scanner in;
    private static String токен;
    private static String консоль;

    public static void main(String[] args) {
        System.out.println("ArenaConsole 0.1 (C) ScCraft 2015-2019");
        in = new Scanner(System.in);
        System.out.println("##################################");
        System.out.print("Введите токен от MyArena API\n>>>");
        токен = in.next();
        System.out.println("Авторизация завершена!");
        System.out.println("##################################");
        вывести_консоль();
        меню();
    }

    private static void обновить() {
        API_запрос запрос = new API_запрос("getconsole", токен);
        консоль = "" + API_ответ.fromJSON(NetGet.getOneLine(запрос.toHTTPs()));
    }

    private static void меню() {
        System.out.println("update - обновить");
        System.out.println("exit - выход");
        String комманда = in.nextLine();
        System.out.println("##################################");
        System.out.println("# ВАШ ЗАПРОС ОБРАБАТЫВАЕТСЯ...   #");
        System.out.println("##################################");
        if (комманда.equals("update")) {
            вывести_консоль();
        } else if (комманда.equals("exit")) {
            System.exit(0);
        } else {
            выполнить_комманду(комманда);
            вывести_консоль();
        }
        меню();
    }

    private static void вывести_консоль() {
        обновить();
        System.out.println(консоль);
    }

    private static void выполнить_комманду(String комманда) {
        API_cmd api_cmd = new API_cmd(токен);
        api_cmd.cmd = комманда;
        NetGet.getOneLine(api_cmd.toHTTPs());
    }
}