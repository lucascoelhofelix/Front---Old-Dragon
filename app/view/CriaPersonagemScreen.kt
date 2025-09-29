package com.olddragon.front.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.olddragon.front.controller.CriaPersonagemViewModel
import com.olddragon.front.model.Classes
import com.olddragon.front.model.MetodoDistribuicao
import com.olddragon.front.model.Racas

@Composable
fun CriaPersonagemScreen(viewModel: CriaPersonagemViewModel = viewModel()) {

    // Observa o estado do ViewModel
    val metodo by viewModel.metodoEscolhido.collectAsState()
    val atributosFinais by viewModel.atributosFinais.collectAsState()
    val valoresParaDistribuir by viewModel.valoresAtributos.collectAsState()
    val raca by viewModel.racaSelecionada.collectAsState()
    val classe by viewModel.classeSelecionada.collectAsState()

    Scaffold(topBar = { TopAppBar(title = { Text("Front - Old Dragon") }) }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // 1. SELEÇÃO DO MÉTODO DE DISTRIBUIÇÃO
            Text(text = "1. Método de Atributos", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                MetodoDistribuicao.entries.forEach { m ->
                    FilterChip(
                        selected = metodo == m,
                        onClick = { viewModel.setMetodo(m) },
                        label = { Text(m.name) }
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // 2. ATRIBUTOS
            Text(text = "2. Atributos Gerados", style = MaterialTheme.typography.titleMedium)

            // Exibir valores gerados/distribuídos
            if (metodo == MetodoDistribuicao.CLASSICO) {
                // Exibição em ordem
                AtributosLista(atributos = atributosFinais)
            } else {
                // Exibição para distribuição (simplificada)
                Text(text = "Valores para distribuir: ${valoresParaDistribuir.joinToString()}", Modifier.padding(bottom = 8.dp))
                Text(text = "Atributos alocados (Aventureiro/Heróico):")
                // Você precisaria de lógica complexa aqui para permitir arrastar/soltar ou selecionar/alocar.
                AtributosLista(atributos = atributosFinais)
            }
            Spacer(modifier = Modifier.height(16.dp))

            // 3. SELEÇÃO DE RAÇA
            Text(text = "3. Seleção de Raça", style = MaterialTheme.typography.titleMedium)
            RacaClasseSelector(
                itens = Racas.TODAS.map { it.nome },
                selecionado = raca.nome,
                onSelect = { nome -> viewModel.setRaca(Racas.TODAS.first { it.nome == nome }) }
            )
            Spacer(modifier = Modifier.height(16.dp))

            // 4. SELEÇÃO DE CLASSE
            Text(text = "4. Seleção de Classe", style = MaterialTheme.typography.titleMedium)
            RacaClasseSelector(
                itens = Classes.TODAS.map { it.nome },
                selecionado = classe.nome,
                onSelect = { nome -> viewModel.setClasse(Classes.TODAS.first { it.nome == nome }) }
            )
            Spacer(modifier = Modifier.height(16.dp))

            // BOTÃO DE ROLAR/GERAR
            Button(onClick = { viewModel.gerarAtributosIniciais() }) {
                Text("Gerar Novos Atributos")
            }
        }
    }
}

// Componentes Reutilizáveis (poderia estar em ComponentesUI.kt)

@Composable
fun RacaClasseSelector(itens: List<String>, selecionado: String, onSelect: (String) -> Unit) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        itens.forEach { item ->
            FilterChip(
                selected = selecionado == item,
                onClick = { onSelect(item) },
                label = { Text(item) }
            )
        }
    }
}

@Composable
fun AtributosLista(atributos: com.olddragon.front.model.Atributos) {
    Column(horizontalAlignment = Alignment.Start) {
        Text("FOR: ${atributos.forca}")
        Text("DES: ${atributos.destreza}")
        Text("CON: ${atributos.constituicao}")
        Text("INT: ${atributos.inteligencia}")
        Text("SAB: ${atributos.sabedoria}")
        Text("CAR: ${atributos.carisma}")
    }
}