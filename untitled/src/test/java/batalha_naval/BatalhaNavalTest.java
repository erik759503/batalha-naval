package batalha_naval;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para a classe BatalhaNaval.
 * 
 * @author ERIK
 * @version 1.0.2
 */
public class BatalhaNavalTest {
    
    private BatalhaNaval batalhaNaval;
    
    @BeforeEach
    public void setUp() {
        batalhaNaval = new BatalhaNaval();
    }
    
    @Test
    public void testConstrutor() {
        assertNotNull(batalhaNaval);
        assertNotNull(batalhaNaval.getTabuleiroJogador());
        assertNotNull(batalhaNaval.getTabuleiroComputador());
        assertNotNull(batalhaNaval.getJogador());
        assertNotNull(batalhaNaval.getComputador());
    }
    
    @Test
    public void testGetTabuleiroJogador() {
        Tabuleiro tabuleiro = batalhaNaval.getTabuleiroJogador();
        assertNotNull(tabuleiro);
        assertEquals(10, tabuleiro.getMatriz().length);
    }
    
    @Test
    public void testGetTabuleiroComputador() {
        Tabuleiro tabuleiro = batalhaNaval.getTabuleiroComputador();
        assertNotNull(tabuleiro);
        assertEquals(10, tabuleiro.getMatriz().length);
    }
    
    @Test
    public void testGetJogador() {
        Jogador jogador = batalhaNaval.getJogador();
        assertNotNull(jogador);
        assertEquals("Jogador", jogador.getNome());
    }
    
    @Test
    public void testGetComputador() {
        Computador computador = batalhaNaval.getComputador();
        assertNotNull(computador);
        assertEquals("Computador", computador.getNome());
    }
} 