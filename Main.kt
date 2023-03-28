/**
Jogo da velha é um jogo simples para dois jogadores que utilizam os símbolos X e O para
marcar suas jogadas em uma matriz 3x3. O jogador que colocar o seu símbolo 3 vezes seguidas na horizontal,
na vertical ou na diagonal vence. Seu objetivo é implementar um jogo da velha utilizando a liguagem de
programação Kotlin e conceitos de orientação a objeto.

Requisitos funcionais do sistema:
Deve ser possível nomear os dois jogadores, o nome do jogador deve ser exibido antes de cada jogada;

O jogo deve ser jogado via console da aplicação, onde a cada jogada o jogador escolhe em qual posição
deseja marcar seu respectivo símbolo no tabuleiro;

O jogo é finalizado quando algum jogador colocar 3 símbolos seguidos na horizontal, na vertical ou na
diagonal. Pode ocorrer também um empate, que acontece quando o tabuleiro é preenchido totalmente e nenhum
dos dois jogadores conseguiu ter 3 símbolos consecutivos no tabuleiro;

O tabuleiro deve ser impresso no console em seu estado atual após cada jogada;

Deve ser mantido o número de vitórias, derrotas e empates de cada jogador;

Deve ser possível recomeçar o jogo com novos jogadores, limpando o histórico de partidas dos antigos
jogadores;

O projeto deve ser disponibilizado no github, deve ser implementado em uma branch separada da master,
com commits atômicos e com mensagens sucintas descrevendo o que foi feito em cada commit. Ao fim do projeto
deve ser aberta uma pull request para a master para que os outros desenvolvedores do time possam revisar o
projeto;
 */

class JogoDaVelha {
    //Método Imprimir Tabuleiro
    fun imprimirTabuleiro(tabuleiro: Array<CharArray>) {

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

    //Metodo Jogar
    fun jogar(linha: Int, coluna: Int, tabuleiro: Array<CharArray>): Boolean {
        if (linha < 0 || linha >= 3 || coluna < 0 || coluna >= 3) {      //validando a posição se existe ou não
            return false
        }
        if (tabuleiro[linha][coluna] != ' ') {   //validar se está preenchido ou não
            return false
        }

        return true
    }

    //verifica se algum jogador ganhou nas linhas
    fun verificarGanhadorLinha(tabuleiro: Array<CharArray>): Boolean {
        for (linha in 0 until 3) {
            if (tabuleiro[linha][0] == tabuleiro[linha][1] && tabuleiro[linha][1] == tabuleiro[linha][2]
                && tabuleiro[linha][0] != ' '
            ) {
                return true
            }
        }
        return false
    }

    fun verificarGanhadorColuna(tabuleiro: Array<CharArray>): Boolean {
        for (coluna in 0 until 3) {
            if (tabuleiro[0][coluna] == tabuleiro[1][coluna] && tabuleiro[1][coluna] == tabuleiro[2][coluna]
                && tabuleiro[0][coluna] != ' '
            ) {
                return true
            }
        }
        return false
    }

    fun verificarGanhadorDiagonal(tabuleiro: Array<CharArray>): Boolean {
        if (tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][2]
            && tabuleiro[0][0] != ' '
        ) {
            return true
        }
        if (tabuleiro[0][2] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][0]
            && tabuleiro[0][2] != ' '
        ) {
            return true
        }
        return false
    }

    fun verificarVencedores(tabuleiro: Array<CharArray>): Boolean {
        return verificarGanhadorColuna(tabuleiro) || verificarGanhadorDiagonal(tabuleiro) ||
                verificarGanhadorLinha(tabuleiro)
    }


    fun novoJogo(tabuleiro: Array<CharArray>) {
        println("Deseja uma nova jogada?")
        var resposta = readln()
        if (resposta.equals("Sim", true)) {
            println("Iniciando o jogo com novos jogadores")
            val tabuleiro = Array(3) { CharArray(3) { ' ' } }
            imprimirTabuleiro(tabuleiro)
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

    //Imprimindo Tabuleiro
    val tabuleiro = Array(3) { CharArray(3) { ' ' } }
    jogo.imprimirTabuleiro(tabuleiro)

    //Primeira jogada

    var jogadorAtual = jogador1

    while (jogada < 9) {

        println("$jogadorAtual sua vez de jogar")
        println("Digite a posição da linha de 0 a 2")
        var linha = readLine()!!.toInt()

        println("Digite a posição da Coluna de 0 a 2")
        var coluna = readLine()!!.toInt()

        //validar a entrada do jogador
        //enquanto a posição estiver errada, pedir para o jogador inserir a entrada novamente.
        while (jogo.jogar(linha, coluna, tabuleiro) == false) {
            println("Opção Inválida, tente novamente.")
            println("Digite a posição da linha de 0 a 2")
            linha = readLine()!!.toInt()
            println("Digite a posição da Coluna de 0 a 2")
            coluna = readLine()!!.toInt()
        }

        tabuleiro[linha][coluna] = if (jogadorAtual == jogador1) 'X' else 'O'

        jogo.imprimirTabuleiro(tabuleiro)


        //verificar de existe ganhador ou não, se houver ganhador parar o jogo.

        if (jogo.verificarVencedores(tabuleiro) == true) {
            //vai parar o jogo
            jogada = 9
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
    if (jogo.verificarVencedores(tabuleiro) == false) {
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








