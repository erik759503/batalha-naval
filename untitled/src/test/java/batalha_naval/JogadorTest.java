package batalha_naval;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Scanner;

/**
 * Testes unitários para a classe Jogador.
 * 
 * @author ERIK
 * @version 1.0.2
 */
public class JogadorTest {
    
    private Tabuleiro tabuleiro;
    private Jogador jogador;
    
    @BeforeEach
    public void setUp() {
        tabuleiro = new Tabuleiro();
        jogador = new Jogador("João", tabuleiro);
    }
    
    @Test
    public void testConstrutor() {
        assertEquals("João", jogador.getNome());
        assertEquals(tabuleiro, jogador.getTabuleiro());
        assertEquals(0, jogador.getPontuacao());
        assertEquals(0, jogador.getNaviosDestruidos());
        assertNotNull(jogador.getScanner());
    }

    @Test
    public void testRealizarJogadaComCoordenadas() {
        Tabuleiro tabuleiroOponente = new Tabuleiro();
        tabuleiroOponente.posicionarNavio(new Navio(3), 0, 0, true);
        
        // Acertar um navio
        assertTrue(jogador.realizarJogada(tabuleiroOponente, 0, 0));
        
        // Atirar na água
        assertFalse(jogador.realizarJogada(tabuleiroOponente, 5, 5));
        
        // Posição já atacada
        assertFalse(jogador.realizarJogada(tabuleiroOponente, 0, 0));

        // Posição inválida
        assertFalse(jogador.realizarJogada(tabuleiroOponente, -1, 0));
    }
    
    @Test
    public void testPontuacaoAoAcertar() {
        Tabuleiro tabuleiroOponente = new Tabuleiro();
        tabuleiroOponente.posicionarNavio(new Navio(3), 0, 0, true);
        
        int pontuacaoInicial = jogador.getPontuacao();
        jogador.realizarJogada(tabuleiroOponente, 0, 0);
        assertTrue(jogador.getPontuacao() > pontuacaoInicial);
    }

    @Test
    public void testGetEstatisticas() {
        String estatisticas = jogador.getEstatisticas();
        assertTrue(estatisticas.contains("Jogador: João"));
        assertTrue(estatisticas.contains("Pontuação: 0"));
    }

    @Test
    public void testSetScanner() {
        Scanner novoScanner = new Scanner(System.in);
        jogador.setScanner(novoScanner);
        assertEquals(novoScanner, jogador.getScanner());
    }
} 