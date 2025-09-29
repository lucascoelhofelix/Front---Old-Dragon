package com.olddragon.front.model

data class Classe(
    val nome: String,
    val descricao: String,
    // Outros detalhes da classe...
)

// Definição das classes
object Classes {
    val GUERREIRO = Classe("Guerreiro", "Mestre em combate corpo a corpo.")
    val MAGO = Classe("Mago", "Conjurador de magias arcanas.")
    val CLERIGO = Classe("Clérigo", "Conjurador de magias divinas e suporte.")
    val TODAS = listOf(GUERREIRO, MAGO, CLERIGO)
}