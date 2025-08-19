package batalha_naval;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para demonstrar a herança entre Participante, Jogador e Computador.
 * 
 * @author ERIK
 * @version 1.0.2
 */
public class ParticipanteTest {
    
    private Tabuleiro tabuleiroJogador;
    private Tabuleiro tabuleiroComputador;
    private Jogador jogador;
    private Computador computador;

    @BeforeEach
    public void setUp() {
        tabuleiroJogador = new Tabuleiro();
        tabuleiroComputador = new Tabuleiro();
        jogador = new Jogador("João", tabuleiroJogador);
        computador = new Computador(tabuleiroComputador);
    }

    @Test
    public void testHerancaParticipante() {
        // Verifica se Jogador e Computador são instâncias de Participante
        assertTrue(jogador instanceof Participante);
        assertTrue(computador instanceof Participante);
        
        // Verifica se são instâncias de suas respectivas classes
        assertTrue(jogador instanceof Jogador);
        assertTrue(computador instanceof Computador);
    }

    @Test
    public void testConstrutorParticipante() {
        // Verifica se os atributos da classe pai foram inicializados corretamente
        assertEquals("João", jogador.getNome());
        assertEquals("Computador", computador.getNome());
        assertEquals(tabuleiroJogador, jogador.getTabuleiro());
        assertEquals(tabuleiroComputador, computador.getTabuleiro());
        assertEquals(0, jogador.getPontuacao());
        assertEquals(0, computador.getPontuacao());
        assertEquals(0, jogador.getNaviosDestruidos());
        assertEquals(0, computador.getNaviosDestruidos());
    }

    @Test
    public void testMetodosComunsParticipante() {
        // Testa métodos comuns herdados da classe Participante
        
        // Adicionar pontos
        jogador.adicionarPontos(10);
        computador.adicionarPontos(20);
        assertEquals(10, jogador.getPontuacao());
        assertEquals(20, computador.getPontuacao());
        
        // Navio destruído
        jogador.navioDestruido();
        computador.navioDestruido();
        assertEquals(1, jogador.getNaviosDestruidos());
        assertEquals(1, computador.getNaviosDestruidos());
        
        // Resetar estatísticas
        jogador.resetarEstatisticas();
        computador.resetarEstatisticas();
        assertEquals(0, jogador.getPontuacao());
        assertEquals(0, computador.getPontuacao());
        assertEquals(0, jogador.getNaviosDestruidos());
        assertEquals(0, computador.getNaviosDestruidos());
    }

    @Test
    public void testVerificacaoVitoria() {
        // Testa verificação de vitória/derrota
        
        // Inicialmente, nenhum participante venceu ou perdeu
        assertFalse(jogador.venceu(tabuleiroComputador));
        assertFalse(computador.venceu(tabuleiroJogador));
        assertFalse(jogador.perdeu());
        assertFalse(computador.perdeu());
    }

    @Test
    public void testMetodosAbstratos() {
        // Verifica se os métodos abstratos podem ser chamados
        // (não devem lançar exceção)
        // Nota: posicionarNavios() requer entrada do usuário, então não testamos aqui
        // Testamos apenas métodos que não requerem entrada
        
        // Testa realizarJogada com tabuleiro vazio (não deve lançar exceção)
        assertDoesNotThrow(() -> computador.realizarJogada(tabuleiroJogador));
        
        // Testa realizarJogada com coordenadas específicas
        assertDoesNotThrow(() -> jogador.realizarJogada(tabuleiroComputador, 0, 0));
        assertDoesNotThrow(() -> computador.realizarJogada(tabuleiroJogador, 0, 0));
    }

    @Test
    public void testToString() {
        // Testa o método toString herdado
        String jogadorStr = jogador.toString();
        String computadorStr = computador.toString();
        
        assertTrue(jogadorStr.contains("João"));
        assertTrue(jogadorStr.contains("0")); // pontuação inicial
        assertTrue(computadorStr.contains("Computador"));
        assertTrue(computadorStr.contains("0")); // pontuação inicial
    }

    @Test
    public void testEstatisticas() {
        // Testa métodos de estatísticas específicos
        String estatisticasJogador = jogador.getEstatisticas();
        String estatisticasComputador = computador.getEstatisticas();
        
        assertTrue(estatisticasJogador.contains("Jogador: João"));
        assertTrue(estatisticasComputador.contains("Computador: Computador"));
        assertTrue(estatisticasComputador.contains("Estratégia: EstrategiaAleatoria"));
    }

    @Test
    public void testPolimorfismo() {
        // Testa polimorfismo - tratando Jogador e Computador como Participante
        Participante[] participantes = new Participante[2];
        participantes[0] = jogador;
        participantes[1] = computador;
        
        // Verifica se todos os participantes têm os métodos comuns
        for (Participante participante : participantes) {
            assertNotNull(participante.getNome());
            assertNotNull(participante.getTabuleiro());
            assertEquals(0, participante.getPontuacao());
            assertEquals(0, participante.getNaviosDestruidos());
            
            // Testa métodos que não requerem entrada do usuário
            if (participante instanceof Computador) {
                assertDoesNotThrow(() -> ((Computador) participante).realizarJogada(tabuleiroJogador));
            } else if (participante instanceof Jogador) {
                assertDoesNotThrow(() -> ((Jogador) participante).realizarJogada(tabuleiroComputador, 0, 0));
            }
        }
    }

    @Test
    public void testEstrategiaComputador() {
        // Testa a estratégia do computador
        Computador.EstrategiaAtaque estrategia = computador.getEstrategia();
        assertNotNull(estrategia);
        assertTrue(estrategia instanceof Computador.EstrategiaAleatoria);
        
        // Testa escolha de posição
        int[] posicao = estrategia.escolherPosicao(tabuleiroJogador);
        assertNotNull(posicao);
        assertEquals(2, posicao.length);
        assertTrue(posicao[0] >= 0 && posicao[0] < Tabuleiro.TAMANHO);
        assertTrue(posicao[1] >= 0 && posicao[1] < Tabuleiro.TAMANHO);
    }

    @Test
    public void testJogadaComCoordenadas() {
        // Testa jogada com coordenadas específicas
        boolean resultado = jogador.realizarJogada(tabuleiroComputador, 0, 0);
        // O resultado depende do estado do tabuleiro, mas não deve lançar exceção
        assertDoesNotThrow(() -> jogador.realizarJogada(tabuleiroComputador, 0, 0));
        
        boolean resultadoComputador = computador.realizarJogada(tabuleiroJogador, 0, 0);
        assertDoesNotThrow(() -> computador.realizarJogada(tabuleiroJogador, 0, 0));
    }

    @Test
    public void testJogadaInvalida() {
        // Testa jogada com coordenadas inválidas
        boolean resultado = jogador.realizarJogada(tabuleiroComputador, -1, 0);
        assertFalse(resultado);
        
        resultado = jogador.realizarJogada(tabuleiroComputador, 0, -1);
        assertFalse(resultado);
        
        resultado = jogador.realizarJogada(tabuleiroComputador, 10, 0);
        assertFalse(resultado);
        
        resultado = jogador.realizarJogada(tabuleiroComputador, 0, 10);
        assertFalse(resultado);
    }
} 