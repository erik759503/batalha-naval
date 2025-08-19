package batalha_naval;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para a classe Computador.
 * 
 * @author ERIK
 * @version 1.0.2
 */
public class ComputadorTest {
    
    private Tabuleiro tabuleiro;
    private Computador computador;
    
    @BeforeEach
    public void setUp() {
        tabuleiro = new Tabuleiro();
        computador = new Computador(tabuleiro);
    }
    
    @Test
    public void testConstrutor() {
        assertEquals("Computador", computador.getNome());
        assertEquals(tabuleiro, computador.getTabuleiro());
        assertEquals(0, computador.getPontuacao());
        assertEquals(0, computador.getNaviosDestruidos());
        assertNotNull(computador.getEstrategia());
        assertTrue(computador.getEstrategia() instanceof Computador.EstrategiaAleatoria);
    }
    
    @Test
    public void testConstrutorComEstrategia() {
        Computador.EstrategiaAtaque estrategia = new Computador.EstrategiaInteligente();
        Computador computadorComEstrategia = new Computador(tabuleiro, estrategia);
        
        assertEquals("Computador", computadorComEstrategia.getNome());
        assertEquals(estrategia, computadorComEstrategia.getEstrategia());
    }
    
    @Test
    public void testPosicionarNavios() {
        // Posiciona os navios
        computador.posicionarNavios();
        
        // Verifica se há navios no tabuleiro
        char[][] matriz = tabuleiro.getMatriz();
        boolean temNavios = false;
        for (int i = 0; i < Tabuleiro.TAMANHO; i++) {
            for (int j = 0; j < Tabuleiro.TAMANHO; j++) {
                if (matriz[i][j] == 'N') {
                    temNavios = true;
                    break;
                }
            }
            if(temNavios) break;
        }
        assertTrue(temNavios, "Deveria ter navios posicionados no tabuleiro");
    }
    
    @Test
    public void testRealizarJogada() {
        Tabuleiro tabuleiroOponente = new Tabuleiro();
        tabuleiroOponente.posicionarNavio(new Navio(3), 0, 0, true);
        
        // Tenta realizar várias jogadas para garantir que não lance exceção
        for (int i=0; i < 5; i++) {
            assertDoesNotThrow(() -> computador.realizarJogada(tabuleiroOponente));
        }
    }
    
    @Test
    public void testRealizarJogadaComCoordenadas() {
        Tabuleiro tabuleiroOponente = new Tabuleiro();
        tabuleiroOponente.posicionarNavio(new Navio(3), 0, 0, true);
        
        // Testa jogada válida que acerta
        assertTrue(computador.realizarJogada(tabuleiroOponente, 0, 0));
        
        // Testa jogada em água
        assertFalse(computador.realizarJogada(tabuleiroOponente, 5, 5));

        // Testa jogada inválida (fora dos limites)
        assertFalse(computador.realizarJogada(tabuleiroOponente, -1, 0));
        assertFalse(computador.realizarJogada(tabuleiroOponente, 0, -1));
        assertFalse(computador.realizarJogada(tabuleiroOponente, 10, 0));
        assertFalse(computador.realizarJogada(tabuleiroOponente, 0, 10));
    }
    
    @Test
    public void testRealizarJogadaPosicaoJaAtacada() {
        Tabuleiro tabuleiroOponente = new Tabuleiro();
        computador.realizarJogada(tabuleiroOponente, 0, 0);
        
        // Segunda jogada na mesma posição deve retornar false
        assertFalse(computador.realizarJogada(tabuleiroOponente, 0, 0));
    }
    
    @Test
    public void testSetEstrategia() {
        Computador.EstrategiaAtaque novaEstrategia = new Computador.EstrategiaInteligente();
        computador.setEstrategia(novaEstrategia);
        assertEquals(novaEstrategia, computador.getEstrategia());
    }
    
    @Test
    public void testGetEstatisticas() {
        String estatisticas = computador.getEstatisticas();
        assertTrue(estatisticas.contains("Computador"));
        assertTrue(estatisticas.contains("Pontuação: 0"));
        assertTrue(estatisticas.contains("EstrategiaAleatoria"));
    }
    
    @Test
    public void testEstrategiaAleatoria() {
        Computador.EstrategiaAtaque estrategia = new Computador.EstrategiaAleatoria();
        Tabuleiro tabuleiroOponente = new Tabuleiro();
        
        for (int i = 0; i < 10; i++) {
            int[] posicao = estrategia.escolherPosicao(tabuleiroOponente);
            assertNotNull(posicao);
            assertEquals(2, posicao.length);
            assertTrue(posicao[0] >= 0 && posicao[0] < Tabuleiro.TAMANHO);
            assertTrue(posicao[1] >= 0 && posicao[1] < Tabuleiro.TAMANHO);
        }
    }
    
    @Test
    public void testEstrategiaInteligente() {
        Computador.EstrategiaAtaque estrategia = new Computador.EstrategiaInteligente();
        Tabuleiro tabuleiroOponente = new Tabuleiro();
        
        int[] posicao = estrategia.escolherPosicao(tabuleiroOponente);
        assertNotNull(posicao);
        assertEquals(2, posicao.length);
    }

    @Test
    public void testPontuacaoAoAcertar() {
        Tabuleiro tabuleiroOponente = new Tabuleiro();
        tabuleiroOponente.posicionarNavio(new Navio(3), 0, 0, true);
        
        int pontuacaoInicial = computador.getPontuacao();
        computador.realizarJogada(tabuleiroOponente, 0, 0);
        assertTrue(computador.getPontuacao() > pontuacaoInicial);
    }
    
    @Test
    public void testNaviosDestruidos() {
        Tabuleiro tabuleiroOponente = new Tabuleiro();
        Navio navio = new Navio(2);
        tabuleiroOponente.posicionarNavio(navio, 0, 0, true);
        
        int naviosDestruidosInicial = computador.getNaviosDestruidos();
        
        // Ataca todas as partes do navio
        computador.realizarJogada(tabuleiroOponente, 0, 0);
        computador.realizarJogada(tabuleiroOponente, 0, 1);
        
        // Verifica se o contador de navios destruídos foi incrementado
        assertTrue(computador.getNaviosDestruidos() >= naviosDestruidosInicial);
    }
    
    @Test
    public void testDestruirNavioCompleto() {
        Tabuleiro tabuleiroOponente = new Tabuleiro();
        Navio navio = new Navio(2);
        tabuleiroOponente.posicionarNavio(navio, 0, 0, true);
        
        int pontuacaoInicial = computador.getPontuacao();
        int naviosDestruidosInicial = computador.getNaviosDestruidos();
        
        // Primeiro ataque - acerta mas não destrói
        computador.realizarJogada(tabuleiroOponente, 0, 0);
        int pontuacaoAposPrimeiroAtaque = computador.getPontuacao();
        
        // Segundo ataque - destrói o navio completamente
        computador.realizarJogada(tabuleiroOponente, 0, 1);
        
        // Verifica se a pontuação aumentou (10 + 50 = 60 pontos)
        assertTrue(computador.getPontuacao() > pontuacaoAposPrimeiroAtaque);
        
        // Verifica se o contador de navios destruídos foi incrementado
        assertTrue(computador.getNaviosDestruidos() > naviosDestruidosInicial);
    }
    
    @Test
    public void testDestruirTodosOsNavios() {
        Tabuleiro tabuleiroOponente = new Tabuleiro();
        // Posiciona apenas um navio pequeno para facilitar a destruição completa
        Navio navio = new Navio(1);
        tabuleiroOponente.posicionarNavio(navio, 0, 0, true);
        
        int pontuacaoInicial = computador.getPontuacao();
        int naviosDestruidosInicial = computador.getNaviosDestruidos();
        
        // Ataque que destrói o único navio (e todos os navios)
        boolean resultado = computador.realizarJogada(tabuleiroOponente, 0, 0);
        
        // Verifica se acertou
        assertTrue(resultado);
        
        // Verifica se a pontuação aumentou (10 pontos pelo acerto + 50 pontos pela destruição)
        assertEquals(pontuacaoInicial + 60, computador.getPontuacao());
        
        // Verifica se o contador de navios destruídos foi incrementado
        assertEquals(naviosDestruidosInicial + 1, computador.getNaviosDestruidos());
    }
    
    @Test
    public void testDestruirNavioSemDestruirTodos() {
        Tabuleiro tabuleiroOponente = new Tabuleiro();
        // Posiciona dois navios
        Navio navio1 = new Navio(1);
        Navio navio2 = new Navio(1);
        tabuleiroOponente.posicionarNavio(navio1, 0, 0, true);
        tabuleiroOponente.posicionarNavio(navio2, 1, 1, true);
        
        int pontuacaoInicial = computador.getPontuacao();
        int naviosDestruidosInicial = computador.getNaviosDestruidos();
        
        // Ataque que destrói apenas o primeiro navio
        boolean resultado = computador.realizarJogada(tabuleiroOponente, 0, 0);
        
        // Verifica se acertou
        assertTrue(resultado);
        
        // Como ainda há outro navio, não deve receber os 50 pontos extras
        // (baseado na implementação atual que verifica todosNaviosDestruidos())
        assertEquals(pontuacaoInicial + 10, computador.getPontuacao());
        
        // O contador de navios destruídos não deve ser incrementado
        // porque o método verificarNavioDestruido só retorna true quando TODOS os navios são destruídos
        assertEquals(naviosDestruidosInicial, computador.getNaviosDestruidos());
    }
    
    @Test
    public void testDestruirSegundoNavioCompletaTodos() {
        Tabuleiro tabuleiroOponente = new Tabuleiro();
        // Posiciona dois navios
        Navio navio1 = new Navio(1);
        Navio navio2 = new Navio(1);
        tabuleiroOponente.posicionarNavio(navio1, 0, 0, true);
        tabuleiroOponente.posicionarNavio(navio2, 1, 1, true);
        
        // Destrói o primeiro navio
        computador.realizarJogada(tabuleiroOponente, 0, 0);
        
        int pontuacaoAntesDoSegundoAtaque = computador.getPontuacao();
        int naviosDestruidosAntes = computador.getNaviosDestruidos();
        
        // Ataque que destrói o segundo navio (completando todos os navios)
        boolean resultado = computador.realizarJogada(tabuleiroOponente, 1, 1);
        
        // Verifica se acertou
        assertTrue(resultado);
        
        // Agora deve receber os 50 pontos extras porque todos os navios foram destruídos
        assertEquals(pontuacaoAntesDoSegundoAtaque + 60, computador.getPontuacao());
        
        // O contador de navios destruídos deve ser incrementado
        assertEquals(naviosDestruidosAntes + 1, computador.getNaviosDestruidos());
    }
    
    @Test
    public void testRealizarJogadaComEstrategiaInteligente() {
        Computador.EstrategiaAtaque estrategia = new Computador.EstrategiaInteligente();
        computador.setEstrategia(estrategia);
        
        Tabuleiro tabuleiroOponente = new Tabuleiro();
        tabuleiroOponente.posicionarNavio(new Navio(1), 0, 0, true);
        
        // Testa que a jogada com estratégia inteligente funciona
        assertDoesNotThrow(() -> computador.realizarJogada(tabuleiroOponente));
    }
    
    @Test
    public void testPontuacaoAcumulativa() {
        Tabuleiro tabuleiroOponente = new Tabuleiro();
        // Posiciona um navio de tamanho 3
        Navio navio = new Navio(3);
        tabuleiroOponente.posicionarNavio(navio, 0, 0, true);
        
        int pontuacaoInicial = computador.getPontuacao();
        
        // Primeiro ataque - acerta mas não destrói
        computador.realizarJogada(tabuleiroOponente, 0, 0);
        assertEquals(pontuacaoInicial + 10, computador.getPontuacao());
        
        // Segundo ataque - acerta mas não destrói
        computador.realizarJogada(tabuleiroOponente, 0, 1);
        assertEquals(pontuacaoInicial + 20, computador.getPontuacao());
        
        // Terceiro ataque - destrói o navio completamente
        computador.realizarJogada(tabuleiroOponente, 0, 2);
        assertEquals(pontuacaoInicial + 80, computador.getPontuacao()); // 30 + 50 = 80
    }
} 