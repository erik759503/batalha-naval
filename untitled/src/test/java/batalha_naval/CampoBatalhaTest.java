package batalha_naval;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para a classe CampoBatalha.
 * 
 * @author ERIK
 * @version 1.0.2
 */
public class CampoBatalhaTest {
    
    private Tabuleiro tabuleiroJogador;
    private CampoBatalha campoBatalha;
    
    @BeforeEach
    public void setUp() {
        tabuleiroJogador = new Tabuleiro();
        // Posiciona um navio no tabuleiro do jogador
        Navio navio = new Navio(3);
        tabuleiroJogador.posicionarNavio(navio, 0, 0, true);
        
        campoBatalha = new CampoBatalha(tabuleiroJogador);
    }
    
    @Test
    public void testConstrutor() {
        assertNotNull(campoBatalha);
        assertEquals("Campo de Batalha", campoBatalha.getTitle());
        assertFalse(campoBatalha.isResizable());
    }
} 