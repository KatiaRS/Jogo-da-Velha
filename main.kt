enum class Jogador(var simbolo: String) {
    X("X"),
    O("O"),
    VAZIO(" ");

    override fun toString(): String {
        return simbolo
    }
}

class JogoDaVelha {
    var jogador1: String? = null
    var jogador2: String? = null
    var tabuleiro = criarTabuleiro()
    var linha = readLine()!!.toInt()
    var coluna = readLine()!!.toInt()

    fun imprimirTabuleiro(){

        for (linha in 0 until 3) {
            for (coluna in 0 until 3) {
                print(tabuleiro[linha][coluna])
                if (coluna < 2) {
                    print("|")
                }
            }
            println()
            if (linha < 2) {
                println("-+-+-")
            }
        }
    }

    fun validaJogada(linha: Int, coluna: Int, tabuleiro: Array<Array<Jogador>>): Boolean {
        if (linha < 0 || linha >= 3 || coluna < 0 || coluna >= 3) {      //validando a posição se existe ou não
            return false
        }
        if (tabuleiro[linha][coluna] != Jogador.VAZIO) {   //validar se está preenchido ou não
            return false
        }

        return true
    }

    //verifica se algum jogador ganhou nas linhas
    fun verificaGanhadorLinha(tabuleiro: Array<Array<Jogador>>): Boolean {
        for (linha in 0 until 3) {
            if (tabuleiro[linha][0] == tabuleiro[linha][1] && tabuleiro[linha][1] == tabuleiro[linha][2]
                && tabuleiro[linha][0] != Jogador.VAZIO
            ) {
                return true
            }
        }
        return false
    }

    fun verificaGanhadorColuna(tabuleiro: Array<Array<Jogador>>): Boolean {
        for (coluna in 0 until 3) {
            if (tabuleiro[0][coluna] == tabuleiro[1][coluna] && tabuleiro[1][coluna] == tabuleiro[2][coluna]
                && tabuleiro[0][coluna] != Jogador.VAZIO
            ) {
                return true
            }
        }
        return false
    }

    fun verificaGanhadorDiagonal(tabuleiro: Array<Array<Jogador>>): Boolean {
        if (tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][2]
            && tabuleiro[0][0] != Jogador.VAZIO
        ) {
            return true
        }
        if (tabuleiro[0][2] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][0]
            && tabuleiro[0][2] != Jogador.VAZIO
        ) {
            return true
        }
        return false
    }

    fun existeVencedor(tabuleiro: Array<Array<Jogador>>): Boolean {
        return verificaGanhadorColuna(tabuleiro) || verificaGanhadorDiagonal(tabuleiro) ||
                verificaGanhadorLinha(tabuleiro)
    }

    fun novoJogo(tabuleiro: Array<Array<Jogador>>) {
        println("Deseja uma nova jogada?")
        var resposta = readln()
        if (resposta.equals("Sim", true)) {
            println("Iniciando um novo jogo")
            val tabuleiro = Array(3) { Array<Jogador>(3) { Jogador.VAZIO } }
            main()
        } else if (resposta.equals("Não", true)){
          println("Jogo encerrado")
        }
    }

    fun nomearJogadores (jogador1: String?, jogador2: String?): String{

        println("Bem vindos Jogadores! Iremos iniciar nosso jogo!")
        println("Digite o nome do primeiro jogador.")
        jogador1 = readLine()

        println("Digite o nome do segundo jogador.")
        jogador2 = readLine()

        println("Iniciando o jogo com os jogadores $jogador1 e $jogador2!!")
        println("Analise o tabuleiro abaixo:")
        return String()
    }

    fun criarTabuleiro(): Array<Array<Jogador>>{
        val tabuleiro = arrayOf(
            arrayOf(Jogador.VAZIO, Jogador.VAZIO, Jogador.VAZIO),
            arrayOf(Jogador.VAZIO, Jogador.VAZIO, Jogador.VAZIO),
            arrayOf(Jogador.VAZIO, Jogador.VAZIO, Jogador.VAZIO)
        )
        return tabuleiro
    }

    fun validaPosição() {
        while (validaJogada(linha, coluna,tabuleiro) == false) {
            println("Opção Inválida, tente novamente.")
            println("Digite a posição da linha de 0 a 2")
            linha = readLine()!!.toInt()
            println("Digite a posição da Coluna de 0 a 2")
            coluna = readLine()!!.toInt()
        }
    }
}
fun main() {
    val jogo = JogoDaVelha()
    var jogada = 0
    var vitoriaJogador1 = 0
    var vitoriaJogador2 = 0
    var empate = 0

    jogo.nomearJogadores()
    jogo.imprimirTabuleiro()

    var jogadorAtual = jogo.jogador1
    while (jogada < 9 && !jogo.existeVencedor(jogo.tabuleiro)) {

        println("${jogadorAtual} sua vez de jogar")
        println("Digite a posição da linha de 0 a 2")
        jogo.linha

        println("Digite a posição da Coluna de 0 a 2")
        jogo.coluna

        //validar a entrada do jogador
        //enquanto a posição estiver errada, pedir para o jogador inserir a entrada novamente.

        jogo.validaPosição()
          jogo.tabuleiro[jogo.linha][jogo.coluna] = if (jogadorAtual == jogo.jogador1) Jogador.X
          else Jogador.O

        jogo.imprimirTabuleiro()


        //verificar de existe ganhador ou não, se houver ganhador parar o jogo.

        if (jogo.existeVencedor(jogo.tabuleiro) == true) {
            //vai parar o jogo
            println("${jogadorAtual} você ganhou o jogo!!")
            if (jogadorAtual == jogo.jogador1) {
                vitoriaJogador1++
            } else {
                vitoriaJogador2++
            }

        }

        if (jogadorAtual == jogo.jogador1) {
            jogadorAtual = jogo.jogador2
        } else {
            jogadorAtual = jogo.jogador1
        }

        jogada++ //jogo acabou ou não?

    }
    //qual a resposta do jogo, houve ganhador ou empate?
    //se o resultado da função verificar Vencedores for falso,ele me mostra um empate.
    if (jogo.existeVencedor(jogo.tabuleiro) == false) {
        println("Houve um empate no jogo!")
        empate++
    }

//    Deve ser mantido o número de vitórias, derrotas e empates de cada jogador;

    println("Placar dos Jogadores:")
    println("${jogo.jogador1}")
    println("Empates $empate")
    println("Vitórias $vitoriaJogador1")
    println("Derrotas $vitoriaJogador2")
    println()
    println("${jogo.jogador2}")
    println("Empates $empate")
    println("Vitórias $vitoriaJogador2")
    println("Derrotas $vitoriaJogador1")


//    Deve ser possível recomeçar o jogo com novos jogadores, limpando o histórico de partidas dos antigos
//    jogadores;
    jogo.novoJogo(jogo.tabuleiro)
}


