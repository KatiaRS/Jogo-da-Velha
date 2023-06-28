class Campeonato(
    private val nomeJogador1: String,
    private val nomeJogador2: String
){
    private var empate: Int = 0
    private var vitoriaJogador1: Int = 0
    private var vitoriaJogador2: Int = 0
    private val jogoDaVelha: JogoDaVelha = JogoDaVelha(nomeJogador1, nomeJogador2)

    fun iniciarCampeonato() {
        var resultado: String = jogoDaVelha.rodarJogo()

        for (jogo in 0 until 3) {
            if (resultado == "Empate") {
                println("O jogo empatou!")
                empate++

            } else {
                if (resultado == nomeJogador1) {
                    println("$nomeJogador1 ponto pra você!!")
                    vitoriaJogador1++
                } else {
                    println("$nomeJogador2 ponto pra você!!")
                    vitoriaJogador2++
                }
                println("Veja o placar")
            }
            println()
            placar()
            println()
            resultado = jogoDaVelha.rodarJogo()


        }
    }
    fun placar(){
        println("Empate $empate")
        println("Vitória do $nomeJogador1, $vitoriaJogador1")
        println("Vitória do $nomeJogador2, $vitoriaJogador2")
    }
}

//resultado é o nome do jogador que venceu, entao preciso alimentar o ganhador.
// testar a condição de campeonato
