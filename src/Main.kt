import java.util.Scanner
import kotlin.system.exitProcess

private const val version = 0.2f
private var ввод = Scanner(System.`in`)
var токен = ""
private var консоль = ""

fun main(args: Array<String>) {
    println("ArenaConsole $version (C) ScCraft 2015-2019")
    println("##################################")
    print("Введите токен от MyArena API\n>>>")
    if (args.size == 0) {
        токен = ввод.next()
    } else {
        токен = args[0]
    }
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
        println("Для вывода справки ArenaConsole введите \"справка\"")
        val комманда = ввод.nextLine()
        println("##################################")
        println("# ВАШ ЗАПРОС ОБРАБАТЫВАЕТСЯ...   #")
        println("##################################")
    when (комманда) {
        "обновить"  -> вывести_консоль()
        "выход"     -> exitProcess(0)
        "стоп"      -> остановить_сервер()
        "старт"     -> запустить_сервер()
        "рестарт"   -> перезагрузить_сервер()
        "инфо"      -> инфо()
        "игроки"    -> вывести_список_игроков()
        "справка"   -> справка()
        else -> {
            выполнить_комманду(комманда)
            вывести_консоль()
        }
    }
    меню()
}

private fun справка() {
    println("########################################### СПРАВКА ###########################################")
    println("#---------------------------------------------------------------------------------------------#")
    println("# Чтобы выполнить комманду на вашем игровом сервере просто введите её и нажмите клавишу ENTER #")
    println("#---------------------------------------------------------------------------------------------#")
    println("# В программе есть зарезервированные комманды (они пишутся русскими буквами):                 #")
    println("# 1) справка    --> Вывод этой справки                                                        #")
    println("# 2) выход      --> Выход из программы                                                        #")
    println("# 3) обновить   --> Вывод консоли без запроса на выполнение комманды                          #")
    println("# 4) старт      --> Запустить сервер (если он выключен)                                       #")
    println("# 5) стоп       --> Остановить сервер (если он работает или завис)                            #")
    println("# 6) рестарт    --> Перезагрузить сервер                                                      #")
    println("# 7) инфо       --> Информация о сервере                                                      #")
    println("# 8) игроки     --> Вывод списка игроков                                                      #")
    println("################################################################################################")
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

private fun запустить_сервер() {
    val api_запрос = API_запрос("start", токен)
    println(API_ответ.fromJSON(getOneLine(api_запрос.toHTTPs())))
    println("Запрос на запуск сервера отправлен")
}

private fun остановить_сервер() {
    val api_запрос = API_запрос("stop", токен)
    println(API_ответ.fromJSON(getOneLine(api_запрос.toHTTPs())))
    println("Запрос на остановку сервера отправлен")
}

private fun перезагрузить_сервер() {
    val api_запрос = API_запрос("restart", токен)
    println(API_ответ.fromJSON(getOneLine(api_запрос.toHTTPs())))
    println("Запрос на остановку сервера отправлен")
}

private fun инфо() {
    val JSON = getOneLine(API_запрос("status", токен).toHTTPs())
    val api_info = API_info.fromJSON(JSON)
    if (api_info.успех()) {
        println("Статус    :: ${статус_на_русском(api_info.online.toString())}")
        println("IP и порт :: ${api_info.server_address}")
        println("Слотов    :: ${api_info.server_maxslots}")
        println("Плагины   :: ${api_info.data!!.e!!.plugins}")
    } else {
        println("ОШИБКА $api_info")
        println("Проверьте правильность вашего токена.")
    }
}

fun статус_на_русском(статус: String) = when(статус) {
    "0" -> "Сервер выключен"
    "1" -> "Работает"
    "2" -> "Запускается, выключается или завис"
    else -> "Ошибка!!! Напишите сообщение на GitHub"
}

fun вывести_список_игроков() {
    val JSON = getOneLine(API_запрос("status", токен).toHTTPs())
    val api_info = API_info.fromJSON(JSON)
    if (api_info.data!!.p != null) {
        if (api_info.data!!.p!!.size > 0) {
            println("--------------------------------")
            println("|   №   |    Ник    |   Счёт   |")
            println("--------------------------------")
            for (i in 0..api_info.data!!.p!!.size)
                println("|${i.toString().format(7)}|${api_info.data!!.p!![i].name.format(11)}|${api_info.data!!.p!![i].score.toString().format(10)}")
            println("--------------------------------")
        }
    } else {
        println("ОШИБКА: Не удалось получить список игроков. Возможно, сервер выключен.")
    }
}