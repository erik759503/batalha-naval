package batalha_naval;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para a classe Tabuleiro.
 * 
 * @author ERIK
 * @version 1.0.2
 */
public class TabuleiroTest {
    
    private Tabuleiro tabuleiro;
    
    @BeforeEach
    public void setUp() {
        tabuleiro = new Tabuleiro();
    }
    
    @Test
    public void testPosicaoValida() {
        // Testa posições válidas
        assertTrue(tabuleiro.posicaoValida(0, 0));
        assertTrue(tabuleiro.posicaoValida(9, 9));
        assertTrue(tabuleiro.posicaoValida(5, 5));
        
        // Testa posições inválidas
        assertFalse(tabuleiro.posicaoValida(-1, 0));
        assertFalse(tabuleiro.posicaoValida(0, -1));
        assertFalse(tabuleiro.posicaoValida(10, 0));
        assertFalse(tabuleiro.posicaoValida(0, 10));
        assertFalse(tabuleiro.posicaoValida(10, 10));
    }
    
    @Test
    public void testPosicaoOcupada() {
        // Inicialmente, nenhuma posição deve estar ocupada
        assertFalse(tabuleiro.posicaoOcupada(0, 0));
        assertFalse(tabuleiro.posicaoOcupada(5, 5));
        
        // Após posicionar um navio, a posição deve estar ocupada
        Navio navio = new Navio(3);
        tabuleiro.posicionarNavio(navio, 0, 0, true);
        assertTrue(tabuleiro.posicaoOcupada(0, 0));
        assertTrue(tabuleiro.posicaoOcupada(0, 1));
        assertTrue(tabuleiro.posicaoOcupada(0, 2));
        assertFalse(tabuleiro.posicaoOcupada(0, 3));
    }
    
    @Test
    public void testPosicionarNavioHorizontal() {
        Navio navio = new Navio(3);
        
        // Testa posicionamento válido
        assertTrue(tabuleiro.posicionarNavio(navio, 0, 0, true));
        
        // Testa posicionamento inválido (fora dos limites)
        Navio navio2 = new Navio(3);
        assertFalse(tabuleiro.posicionarNavio(navio2, 0, 8, true));
        
        // Testa posicionamento inválido (sobrepondo navio existente)
        Navio navio3 = new Navio(2);
        assertFalse(tabuleiro.posicionarNavio(navio3, 0, 1, true));
    }
    
    @Test
    public void testPosicionarNavioVertical() {
        Navio navio = new Navio(3);
        
        // Testa posicionamento válido
        assertTrue(tabuleiro.posicionarNavio(navio, 0, 0, false));
        
        // Testa posicionamento inválido (fora dos limites)
        Navio navio2 = new Navio(3);
        assertFalse(tabuleiro.posicionarNavio(navio2, 8, 0, false));
        
        // Testa posicionamento inválido (sobrepondo navio existente)
        Navio navio3 = new Navio(2);
        assertFalse(tabuleiro.posicionarNavio(navio3, 1, 0, false));
    }
    
    @Test
    public void testAtacar() {
        // Posiciona um navio
        Navio navio = new Navio(3);
        tabuleiro.posicionarNavio(navio, 0, 0, true);
        
        // Testa ataque que acerta
        assertTrue(tabuleiro.atacar(0, 0));
        assertTrue(tabuleiro.atacar(0, 1));
        assertTrue(tabuleiro.atacar(0, 2));
        
        // Testa ataque que erra
        assertFalse(tabuleiro.atacar(1, 0));
        assertFalse(tabuleiro.atacar(0, 3));
        
        // Testa ataque em posição já atingida
        assertFalse(tabuleiro.atacar(0, 0));
    }
    
    @Test
    public void testTodosNaviosDestruidos() {
        // Inicialmente, não há navios, então não há navios destruídos
        assertFalse(tabuleiro.todosNaviosDestruidos());
        
        // Posiciona um navio
        Navio navio = new Navio(2);
        tabuleiro.posicionarNavio(navio, 0, 0, true);
        
        // Antes de atacar, o navio não está destruído
        assertFalse(tabuleiro.todosNaviosDestruidos());
        
        // Ataca todas as partes do navio
        tabuleiro.atacar(0, 0);
        tabuleiro.atacar(0, 1);
        
        // Agora todos os navios estão destruídos
        assertTrue(tabuleiro.todosNaviosDestruidos());
    }
    
    @Test
    public void testGetMatriz() {
        char[][] matriz = tabuleiro.getMatriz();
        
        // Verifica se a matriz tem o tamanho correto
        assertEquals(10, matriz.length);
        assertEquals(10, matriz[0].length);
        
        // Verifica se todas as posições inicialmente contêm água
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                assertEquals('~', matriz[i][j]);
            }
        }
    }
} 