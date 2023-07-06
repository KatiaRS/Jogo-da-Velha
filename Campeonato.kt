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
                    vitoriaJogador1++
                } else {
                   vitoriaJogador2++
                }
                println("Veja o placar")
            }
            println()
            placar()
            println()
            if (vitoriaJogador1 == 2) {
                println("$nomeJogador1 você ganhou o campeonato")
            } else {
                if (vitoriaJogador2 == 2) {
                    println("$nomeJogador2 você ganhou o campeonato")
                    break
                }
            }
         resultado = jogoDaVelha.rodarJogo()
        }

    }
    fun placar(){
        println("Empate $empate")
        println("Vitória do $nomeJogador1, $vitoriaJogador1")
        println("Vitória do $nomeJogador2, $vitoriaJogador2")
    }
}

//o melhor de três
//Break??