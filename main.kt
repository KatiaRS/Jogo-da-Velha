enum class Jogador(var simbolo: String) {
    X("X"),
    O("O"),
    VAZIO(" ");

    override fun toString(): String {
        return simbolo
    }
}

class JogoDaVelha {
    fun imprimirTabuleiro(tabuleiro: Array<Array<Jogador>>) {

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

//    fun reiniciarJogo(tabuleiro: Array<Array<Char?>>, jogadorAtual,) {
//        for (linha in 0 until 3) {
//            for (coluna in 0 until 3){
//                imprimirTabuleiro(tabuleiro)
//            }
//        }
//        jogadorAtual = when (jogadorAtual){
//            Jogador.X == Jogador.O
//            Jogador.O == Jogador.X
//        }
//        println("O jogo foi reiniciado!")
//        println("Iniciando pelo Jogador ${jogadorAtual.simbolo}")
//    }


    fun novoJogo(tabuleiro: Array<Array<Jogador>>) {
        println("Deseja uma nova jogada?")
        var resposta = readln()
        if (resposta.equals("Sim", true)) {

            println("Iniciando o jogo com novos jogadores")
            val tabuleiro = Array(3) { Array<Jogador>(3) { Jogador.VAZIO } }
            main()
        }
    }

}


fun main() {

    val jogo = JogoDaVelha()
    var jogada = 0
    var vitoriaJogador1 = 0
    var vitoriaJogador2 = 0
    var empate = 0

//- Deve ser possível nomear os dois jogadores, o nome do jogador deve ser exibido antes de cada jogada;

    println("Bem vindos Jogadores! Iremos iniciar nosso jogo!")
    println("Digite o nome do primeiro jogador.")
    var jogador1 = readLine()

    println("Digite o nome do segundo jogador.")
    var jogador2 = readLine()

    println("Iniciando o jogo com os jogadores $jogador1 e $jogador2!!")
    println("Analise o tabuleiro abaixo:")


    //val tabuleiro = Array(3, { Array<Jogador>(3, {Jogador.VAZIO})})
    val tabuleiro = arrayOf(
        arrayOf(Jogador.VAZIO, Jogador.VAZIO, Jogador.VAZIO),
        arrayOf(Jogador.VAZIO, Jogador.VAZIO, Jogador.VAZIO),
        arrayOf(Jogador.VAZIO, Jogador.VAZIO, Jogador.VAZIO)
    )
    jogo.imprimirTabuleiro(tabuleiro)

    //Primeira jogada

    var jogadorAtual = jogador1

    while (jogada < 9 && !jogo.existeVencedor(tabuleiro)) {

        println("$jogadorAtual sua vez de jogar")
        println("Digite a posição da linha de 0 a 2")
        var linha = readLine()!!.toInt()

        println("Digite a posição da Coluna de 0 a 2")
        var coluna = readLine()!!.toInt()

        //validar a entrada do jogador
        //enquanto a posição estiver errada, pedir para o jogador inserir a entrada novamente.
        while (jogo.validaJogada(linha, coluna, tabuleiro) == false) {
            println("Opção Inválida, tente novamente.")
            println("Digite a posição da linha de 0 a 2")
            linha = readLine()!!.toInt()
            println("Digite a posição da Coluna de 0 a 2")
            coluna = readLine()!!.toInt()
        }

          tabuleiro[linha][coluna] = if (jogadorAtual == jogador1) Jogador.X else Jogador.O

        jogo.imprimirTabuleiro(tabuleiro)


        //verificar de existe ganhador ou não, se houver ganhador parar o jogo.

        if (jogo.existeVencedor(tabuleiro) == true) {
            //vai parar o jogo
            println("$jogadorAtual você ganhou o jogo!!")
            if (jogadorAtual == jogador1) {
                vitoriaJogador1++
            } else {
                vitoriaJogador2++
            }


        }

        if (jogadorAtual == jogador1) {
            jogadorAtual = jogador2
        } else {
            jogadorAtual = jogador1
        }

        jogada++ //jogo acabou ou não?

    }
    //qual a resposta do jogo, houve ganhador ou empate?
    //se o resultado da função verificar Vencedores for falso,ele me mostra um empate.
    if (jogo.existeVencedor(tabuleiro) == false) {
        println("Houve um empate no jogo!")
        empate++
    }

//    Deve ser mantido o número de vitórias, derrotas e empates de cada jogador;

    println("Placar dos Jogadores:")
    println("$jogador1")
    println("Empates $empate")
    println("Vitórias $vitoriaJogador1")
    println("Derrotas $vitoriaJogador2")
    println()
    println("$jogador2")
    println("Empates $empate")
    println("Vitórias $vitoriaJogador2")
    println("Derrotas $vitoriaJogador1")


//    Deve ser possível recomeçar o jogo com novos jogadores, limpando o histórico de partidas dos antigos
//    jogadores;
    jogo.novoJogo(tabuleiro)


}


