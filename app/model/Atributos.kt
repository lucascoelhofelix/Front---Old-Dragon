package com.olddragon.front.model

data class Atributos(
    val forca: Int = 0,
    val destreza: Int = 0,
    val constituicao: Int = 0,
    val inteligencia: Int = 0,
    val sabedoria: Int = 0,
    val carisma: Int = 0
)

enum class MetodoDistribuicao {
    CLASSICO, AVENTUREIRO, HEROICO
}

// Lógica simulada de geração (GeradorAtributos.kt pode ser uma classe com estas funções)
fun gerarValoresSimulados(metodo: MetodoDistribuicao): List<Int> {
    // Apenas valores de exemplo para não precisar simular rolagens de dados
    return when (metodo) {
        MetodoDistribuicao.CLASSICO -> listOf(12, 10, 8, 14, 11, 9)
        MetodoDistribuicao.AVENTUREIRO -> listOf(15, 13, 11, 9, 7, 5) // Valores para distribuir
        MetodoDistribuicao.HEROICO -> listOf(18, 16, 14, 12, 10, 8)    // Valores para distribuir
    }
}