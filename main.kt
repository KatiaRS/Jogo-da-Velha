enum class Jogador(var simbolo: String) {
    X("X"),
    O("O"),
    VAZIO(" ");

    override fun toString(): String {
        return simbolo
    }
}

class JogoDaVelha(
    var jogador1: String,
    var jogador2: String
) {

    private var tabuleiro: Array<Array<Jogador>> = criarTabuleiro()
    private var linha: Int? = null
    private var coluna: Int? = null
    private var jogadorAtual = jogador1
    private var jogada = 0

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

    fun rodarJogo(): String{
        tabuleiro = criarTabuleiro()
        jogada = 0
        while (jogada < 9 && !existeVencedor()) {

            println("${jogadorAtual} sua vez de jogar")
            validaPosição()
            tabuleiro[linha!!][coluna!!] = if (jogadorAtual == jogador1) Jogador.X else Jogador.O
            imprimirTabuleiro()

            //verificar se existe ganhador ou não, se houver ganhador parar o jogo.
            if (existeVencedor() == true) {
                println("${jogadorAtual} você ganhou o jogo!!")
                return jogadorAtual
            }
            trocaJogadores()

            jogada++ //jogo acabou ou não?

        }
        return "Empate"
    }

}

fun main() {

    println("Bem vindos Jogadores!Iremos iniciar nosso jogo!")
    println("Digite o nome do primeiro jogador.")
    val jogador1 = readLine()!!

    println("Digite o nome do segundo jogador.")
    val jogador2 = readLine()!!

    println("Iniciando o jogo com os jogadores $jogador1 e $jogador2!!")
    val campeonato: Campeonato = Campeonato(nomeJogador1 = jogador1, nomeJogador2 = jogador2)
    campeonato.iniciarCampeonato()
}


