import java.util.Scanner
import kotlin.system.exitProcess

private const val version = 1.1f
private var ввод = Scanner(System.`in`)
var токен = ""
private var консоль = ""

    fun main() {
        println("ArenaConsole $version (C) ScCraft 2015-2019")
        println("##################################")
        print("Введите токен от MyArena API\n>>>")
        токен = ввод.next()
        println("Авторизация завершена!")
        println("##################################")
        вывести_консоль()
        меню()
    }

    private fun обновить() {
        val запрос = API_запрос("getconsole", токен)
        консоль = "" + API_консоль.fromJSON(getOneLine(запрос.toHTTPs()))
    }

    private fun меню() {
        println("update - обновить")
        println("exit - выход")
        val комманда = ввод.nextLine()
        println("##################################")
        println("# ВАШ ЗАПРОС ОБРАБАТЫВАЕТСЯ...   #")
        println("##################################")
        when (комманда) {
            "update" -> вывести_консоль()
            "exit" -> exitProcess(0)
            else -> {
                выполнить_комманду(комманда)
                вывести_консоль()
            }
        }
        меню()
    }

    private fun вывести_консоль() {
        обновить()
        println(консоль)
    }

    private fun выполнить_комманду(комманда: String) {
        val api_cmd = API_cmd(токен)
        api_cmd.cmd = комманда
        getOneLine(api_cmd.toHTTPs())
    }