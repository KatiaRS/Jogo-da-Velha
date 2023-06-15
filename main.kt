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
    private var tabuleiro: Array<Array<Jogador>> = criarTabuleiro()
    private var linha: Int? = null
    private var coluna: Int? = null
    var vitoriaJogador1 = 0
    var vitoriaJogador2 = 0
    private var jogadorAtual = jogador1
    private var jogada = 0
    var empate = 0
    private fun imprimirTabuleiro() {

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

    private fun validaJogada(linha: Int, coluna: Int): Boolean {
        if (linha < 0 || linha >= 3 || coluna < 0 || coluna >= 3) {      //validando a posição se existe ou não
            return false
        }
        if (tabuleiro[linha][coluna] != Jogador.VAZIO) {   //validar se está preenchido ou não
            return false
        }

        return true
    }

    //verifica se algum jogador ganhou nas linhas
    private fun verificaGanhadorLinha(): Boolean {
        for (linha in 0 until 3) {
            if (tabuleiro[linha][0] == tabuleiro[linha][1] && tabuleiro[linha][1] == tabuleiro[linha][2]
                && tabuleiro[linha][0] != Jogador.VAZIO
            ) {
                return true
            }
        }
        return false
    }

    private fun verificaGanhadorColuna(): Boolean {
        for (coluna in 0 until 3) {
            if (tabuleiro[0][coluna] == tabuleiro[1][coluna] && tabuleiro[1][coluna] == tabuleiro[2][coluna]
                && tabuleiro[0][coluna] != Jogador.VAZIO
            ) {
                return true
            }
        }
        return false
    }

    private fun verificaGanhadorDiagonal(): Boolean {
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

    fun existeVencedor(): Boolean {
        return verificaGanhadorColuna() || verificaGanhadorDiagonal() ||
                verificaGanhadorLinha()
    }

    fun nomearJogadores() {
        println("Digite o nome do primeiro jogador.")
        jogador1 = readLine()

        println("Digite o nome do segundo jogador.")
        jogador2 = readLine()

        println("Iniciando o jogo com os jogadores $jogador1 e $jogador2!!")
        println("Analise o tabuleiro abaixo:")
        imprimirTabuleiro()
        jogadorAtual = jogador1
    }

    private fun criarTabuleiro(): Array<Array<Jogador>> {
        var tabuleiro = arrayOf(
            arrayOf(Jogador.VAZIO, Jogador.VAZIO, Jogador.VAZIO),
            arrayOf(Jogador.VAZIO, Jogador.VAZIO, Jogador.VAZIO),
            arrayOf(Jogador.VAZIO, Jogador.VAZIO, Jogador.VAZIO)
        )
        return tabuleiro
    }

    private fun validaPosição() {
        println("Digite a posição da linha de 0 a 2")
        linha = readLine()!!.toInt()
        println("Digite a posição da Coluna de 0 a 2")
        coluna = readLine()!!.toInt()
        while (validaJogada(linha!!, coluna!!) == false) {
            println("Opção Inválida, tente novamente.")
            validaPosição()

        }
    }

    private fun validaGanhador() {
        //verificar se existe ganhador ou não, se houver ganhador parar o jogo.
        if (existeVencedor() == true) {
            println("${jogadorAtual} você ganhou o jogo!!")
            validaVitoria()
        }
    }

    private fun validaVitoria() {

        if (jogadorAtual == jogador1) {
            vitoriaJogador1++
        } else {
            vitoriaJogador2++
        }
    }

    private fun trocaJogadores() {
        if (jogadorAtual == jogador1) {
            jogadorAtual = jogador2
        } else {
            jogadorAtual = jogador1
        }
    }

    fun novoJogo() {
        println("Deseja uma nova jogada?")
        var resposta = readln()
        if (resposta.equals("Sim", true)) {
            println("Com os mesmos jogadores?")
            var resposta = readln()
            if (resposta.equals("Sim", true)) {
                println("Iniciando um novo jogo com os mesmos jogadores")
                tabuleiro = criarTabuleiro()
                jogada = 0
                imprimirTabuleiro()
                rodarJogo()
            } else if (resposta.equals("Não", true)) {
                println("Ok, iremos iniciar com novos jogadores")
                println("Iniciando um novo jogo")
                val tabuleiro = Array(3) { Array<Jogador>(3) { Jogador.VAZIO } }
                main()
            }
        } else if (resposta.equals("Não", true)) {
            println("Jogo encerrado")
        }
    }

    fun rodarJogo(){
        while (jogada < 9 && !existeVencedor()) {

            println("${jogadorAtual} sua vez de jogar")
            validaPosição()
            tabuleiro[linha!!][coluna!!] = if (jogadorAtual == jogador1) Jogador.X else Jogador.O
            imprimirTabuleiro()

            //verificar se existe ganhador ou não, se houver ganhador parar o jogo.
            validaGanhador()
            trocaJogadores()

            jogada++ //jogo acabou ou não?

        }
    }

}

fun main() {

    println("Bem vindos Jogadores!Iremos iniciar nosso jogo!")
    val jogo = JogoDaVelha()
    jogo.nomearJogadores()
    jogo.rodarJogo()

    //mostrar a resposta do jogo, se houve ganhador ou empate.
    //se o resultado da função verificar Vencedores for falso,ele me mostra um empate.
    if (jogo.existeVencedor() == false) {
        println("Houve um empate no jogo!")
        jogo.empate++
    }

    //    Deve ser mantido o número de vitórias, derrotas e empates de cada jogador;

    println("Placar dos Jogadores:")
    println("${jogo.jogador1}")
    println("Empates ${jogo.empate}")
    println("Vitórias ${jogo.vitoriaJogador1}")
    println("Derrotas ${jogo.vitoriaJogador2}")
    println()
    println("${jogo.jogador2}")
    println("Empates ${jogo.empate}")
    println("Vitórias ${jogo.vitoriaJogador2}")
    println("Derrotas ${jogo.vitoriaJogador1}")


    //    Deve ser possível recomeçar o jogo com novos jogadores, limpando o histórico de partidas dos antigos
    //    jogadores;
    jogo.novoJogo()
}


