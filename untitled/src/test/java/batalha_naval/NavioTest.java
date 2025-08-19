package batalha_naval;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para a classe Navio.
 * 
 * @author ERIK
 * @version 1.0.2
 */
public class NavioTest {
    
    private Navio navio;
    
    @BeforeEach
    public void setUp() {
        navio = new Navio(3);
    }
    
    @Test
    public void testConstrutor() {
        assertEquals(3, navio.getTamanho());
        assertEquals(-1, navio.getLinha());
        assertEquals(-1, navio.getColuna());
    }
    
    @Test
    public void testSetPosicao() {
        navio.setPosicao(2, 3, true);
        assertEquals(2, navio.getLinha());
        assertEquals(3, navio.getColuna());
        assertTrue(navio.isHorizontal());
        
        navio.setPosicao(5, 1, false);
        assertEquals(5, navio.getLinha());
        assertEquals(1, navio.getColuna());
        assertFalse(navio.isHorizontal());
    }
    
    @Test
    public void testPosicaoAtingidaHorizontal() {
        navio.setPosicao(2, 3, true);
        
        // Testa posições dentro do navio
        assertFalse(navio.posicaoAtingida(2, 3));
        assertFalse(navio.posicaoAtingida(2, 4));
        assertFalse(navio.posicaoAtingida(2, 5));
        
        // Testa posições fora do navio
        assertFalse(navio.posicaoAtingida(2, 2));
        assertFalse(navio.posicaoAtingida(2, 6));
        assertFalse(navio.posicaoAtingida(1, 3));
        assertFalse(navio.posicaoAtingida(3, 3));
    }
    
    @Test
    public void testPosicaoAtingidaVertical() {
        navio.setPosicao(2, 3, false);
        
        // Testa posições dentro do navio
        assertFalse(navio.posicaoAtingida(2, 3));
        assertFalse(navio.posicaoAtingida(3, 3));
        assertFalse(navio.posicaoAtingida(4, 3));
        
        // Testa posições fora do navio
        assertFalse(navio.posicaoAtingida(1, 3));
        assertFalse(navio.posicaoAtingida(5, 3));
        assertFalse(navio.posicaoAtingida(2, 2));
        assertFalse(navio.posicaoAtingida(2, 4));
    }
    
    @Test
    public void testReceberTiroHorizontal() {
        navio.setPosicao(2, 3, true);
        
        // Testa tiros válidos
        assertTrue(navio.receberTiro(2, 3));
        assertTrue(navio.posicaoAtingida(2, 3));
        
        assertTrue(navio.receberTiro(2, 4));
        assertTrue(navio.posicaoAtingida(2, 4));
        
        assertTrue(navio.receberTiro(2, 5));
        assertTrue(navio.posicaoAtingida(2, 5));
        
        // Testa tiros inválidos
        assertFalse(navio.receberTiro(2, 2));
        assertFalse(navio.receberTiro(2, 6));
        assertFalse(navio.receberTiro(1, 3));
        assertFalse(navio.receberTiro(3, 3));
    }
    
    @Test
    public void testReceberTiroVertical() {
        navio.setPosicao(2, 3, false);
        
        // Testa tiros válidos
        assertTrue(navio.receberTiro(2, 3));
        assertTrue(navio.posicaoAtingida(2, 3));
        
        assertTrue(navio.receberTiro(3, 3));
        assertTrue(navio.posicaoAtingida(3, 3));
        
        assertTrue(navio.receberTiro(4, 3));
        assertTrue(navio.posicaoAtingida(4, 3));
        
        // Testa tiros inválidos
        assertFalse(navio.receberTiro(1, 3));
        assertFalse(navio.receberTiro(5, 3));
        assertFalse(navio.receberTiro(2, 2));
        assertFalse(navio.receberTiro(2, 4));
    }
    
    @Test
    public void testEstaDestruido() {
        navio.setPosicao(2, 3, true);
        
        // Inicialmente, o navio não está destruído
        assertFalse(navio.estaDestruido());
        
        // Ataca uma parte
        navio.receberTiro(2, 3);
        assertFalse(navio.estaDestruido());
        
        // Ataca segunda parte
        navio.receberTiro(2, 4);
        assertFalse(navio.estaDestruido());
        
        // Ataca terceira parte (navio destruído)
        navio.receberTiro(2, 5);
        assertTrue(navio.estaDestruido());
    }
    
    @Test
    public void testGetters() {
        navio.setPosicao(5, 7, false);
        
        assertEquals(3, navio.getTamanho());
        assertEquals(5, navio.getLinha());
        assertEquals(7, navio.getColuna());
        assertFalse(navio.isHorizontal());
    }
    
    @Test
    public void testNavioDiferentesTamanhos() {
        Navio navio2 = new Navio(5);
        assertEquals(5, navio2.getTamanho());
        
        Navio navio3 = new Navio(2);
        assertEquals(2, navio3.getTamanho());
        
        Navio navio4 = new Navio(4);
        assertEquals(4, navio4.getTamanho());
    }
} 