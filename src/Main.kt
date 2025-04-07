//Gilberto Mota de Oliveira Junior
//Iago Henrique Schlemper
//Arthur Abreu

import kotlin.random.Random

fun main() {
    val reset = "\u001B[0m"
    val vermelho = "\u001B[31m"
    val verde = "\u001B[32m"
    val amarelo = "\u001B[33m"
    val azul = "\u001B[34m"

    val apresentacao = 0
    val resolucao = 1

    val Mapa = Array(10) { Array(10) { Array(2) { " " } } }

    fun mapinha() {
        print("-------------------------------------------------------------")
        for (i in Mapa.indices) {
            print("\n|")
            for (j in Mapa[i].indices) {
                print("  ${Mapa[i][j][apresentacao]}  |")
            }
            print(" $amarelo$i$reset\n------------------------------------------------------------- ")
        }
        print("\n   ${amarelo}0     1     2     3     4     5     6     7     8     9 $reset \n")
    }

    //funcao que gera um mapa
    mapinha()

    // Define as coodenadas dos barcos
    // P -> Porta avioes
    // R -> Rebocador
    // C -> Cruzador
    var a = 0
    while (a < 10) {
        val x = Random.nextInt(0, 10)
        val y = Random.nextInt(0, 10)
        if (Mapa[x][y][resolucao] == " ") {
            Mapa[x][y][resolucao] = "${azul}P${reset}"
            a++
        }
    }

    a = 0
    while (a < 2) {
        val x = Random.nextInt(0, 10)
        val y = Random.nextInt(0, 10)
        if (Mapa[x][y][resolucao] == " ") {
            Mapa[x][y][resolucao] = "${azul}R${reset}"
            a++
        }
    }
    a = 0
    while (a < 1) {
        val x = Random.nextInt(0, 10)
        val y = Random.nextInt(0, 10)
        if (Mapa[x][y][resolucao] == " ") {
            Mapa[x][y][resolucao] = "${azul}C${reset}"
            a++
        }
    }
    //Pede as respostas para  usuario e carrega o mapa atualizado
    var b = 0
    var pontuacao = 0
    while (b < 15) {
        //Pede as coordenadas para o usuario
        println("\ntentativa ${++b}")

        print("\nDigite uma linha: ")
        val linha = readln().toInt()

        print("Digite uma coluna: ")
        val coluna = readln().toInt()

        //valida a resposta do usuario
        if (linha < 0 || linha > 9 || coluna < 0 || coluna > 9) {
            print("\nInsira um valor entre 0 e 9")
            b--
            continue
        }

        //verifica se o tiro é repetido
        if (Mapa[linha][coluna][apresentacao] != " ") {
            print("Coordenada repetida, tente novamente")
            b--
            continue
        }

        //atualiza se acertou
        if (Mapa[linha][coluna][resolucao] == "${azul}P${reset}") {
            Mapa[linha][coluna][apresentacao] = "${vermelho}P${reset}"
            Mapa[linha][coluna][resolucao] = "${vermelho}P${reset}"
            mapinha()
            pontuacao += 5
            println("\nVoce acertou um porta avioes")
        } else if (Mapa[linha][coluna][resolucao] == "${azul}R${reset}") {
            Mapa[linha][coluna][apresentacao] = "${vermelho}R${reset}"
            Mapa[linha][coluna][resolucao] = "${vermelho}R${reset}"
            mapinha()
            pontuacao += 10
            println("\nVoce acertou um rebocador")
        } else if (Mapa[linha][coluna][resolucao] == "${azul}C${reset}") {
            Mapa[linha][coluna][apresentacao] = "${vermelho}C${reset}"
            Mapa[linha][coluna][resolucao] = "${vermelho}C${reset}"
            mapinha()
            pontuacao += 15
            println("\nVoce acertou um cruzador")
        }
        //Distancia proximo barco
        else if (linha > 0 && Mapa[linha - 1][coluna][resolucao] != " " ||
            coluna > 0 && Mapa[linha][coluna - 1][resolucao] != " " ||
            linha < 9 && Mapa[linha + 1][coluna][resolucao] != " " ||
            coluna < 9 && Mapa[linha][coluna + 1][resolucao] != " "
        ) {
            Mapa[linha][coluna][apresentacao] = "${verde}1${reset}"
            mapinha()
        } else if (linha > 1 && Mapa[linha - 2][coluna][resolucao] != " " ||
            coluna > 1 && Mapa[linha][coluna - 2][resolucao] != " " ||
            linha < 8 && Mapa[linha + 2][coluna][resolucao] != " " ||
            coluna < 8 && Mapa[linha][coluna + 2][resolucao] != " "
        ) {
            Mapa[linha][coluna][apresentacao] = "${verde}2${reset}"
            mapinha()
        } else if (linha > 2 && Mapa[linha - 3][coluna][resolucao] != " " ||
            coluna > 2 && Mapa[linha][coluna - 3][resolucao] != " " ||
            linha < 7 && Mapa[linha + 3][coluna][resolucao] != " " ||
            coluna < 7 && Mapa[linha][coluna + 3][resolucao] != " "
        ) {
            Mapa[linha][coluna][apresentacao] = "${verde}3${reset}"
            mapinha()
        }

        //caso erro
        else {
            Mapa[linha][coluna][apresentacao] = "${verde}M${reset}"
            mapinha()
            print("\nVoce errou")
        }
    }

    //Setar barcos nao acertados
    for (i in Mapa.indices) {
        for (j in Mapa[i].indices) {
            if (Mapa[i][j][resolucao] == "${azul}P${reset}" && Mapa[i][j][apresentacao] == " ")
                Mapa[i][j][apresentacao] = "${azul}P${reset}"
            if (Mapa[i][j][resolucao] == "${azul}R${reset}" && Mapa[i][j][apresentacao] == " ")
                Mapa[i][j][apresentacao] = "${azul}R${reset}"
            if (Mapa[i][j][resolucao] == "${azul}C${reset}" && Mapa[i][j][apresentacao] == " ")
                Mapa[i][j][apresentacao] = "${azul}C${reset}"
        }
    }

    mapinha()

    println("\nVoce fez $pontuacao pontos!")
    print("\nDigite 1 se deseja jogar novamente \n")
    val resposta = readln().toIntOrNull()

    if (resposta == 1)
        main()
    else
        print("Atè a próxima!")
}