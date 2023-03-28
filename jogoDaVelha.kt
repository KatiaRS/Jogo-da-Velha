

class jogoDaVelha {

    var jogadorAtual = 'X' // começa com o jogador X
    val tabuleiro = Array(3) { CharArray(3) { ' ' } }


   //Metodo Jogar
    fun jogar(linha: Int, coluna: Int): Boolean {
        if (linha < 0 || linha >= 3 || coluna < 0 || coluna >= 3 || tabuleiro[linha][coluna] != ' ') {
            // posição inválida ou já ocupada
            return false
        }
        tabuleiro[linha][coluna] = jogadorAtual // marca a posição com o jogador atual
        jogadorAtual = if (jogadorAtual == 'X') 'O' else 'X' // alterna o jogador atual
        return true
    }
    fun verificarGanhador(): Char {
        // verifica se algum jogador ganhou nas linhas
        for (linha in 0 until 3) {
            if (tabuleiro[linha][0] == tabuleiro[linha][1] && tabuleiro[linha][1] == tabuleiro[linha][2] && tabuleiro[linha][0] != ' ') {
               return tabuleiro[linha][0]
            }
        }
        // verifica se algum jogador ganhou nas colunas
        for (coluna in 0 until 3) {
            if (tabuleiro[0][coluna] == tabuleiro[1][coluna] && tabuleiro[1][coluna] == tabuleiro[2][coluna] && tabuleiro[0][coluna] != ' ') {
                return tabuleiro[0][coluna]
            }
        }
        // verifica se algum jogador ganhou nas diagonais
        if (tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][2] && tabuleiro[0][0] != ' ') {
            return tabuleiro[0][0]
        }
        if (tabuleiro[0][2] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][0] && tabuleiro[0][2] != ' ') {
            return tabuleiro[0][2]
        }
        // nenhum ganhador encontrado
        return ' '
    }
    fun imprimirTabuleiro() {
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
}
