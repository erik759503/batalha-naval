package batalha_naval;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;
import java.awt.*;

/**
 * Testes unitários para a classe TelaBase.
 * 
 * @author ERIK
 * @version 1.0.2
 */
public class TelaBaseTest {
    
    // Classe concreta para testar TelaBase
    private static class TelaBaseConcreta extends TelaBase {
        public TelaBaseConcreta() {
            super("Tela Teste");
        }
        
        @Override
        protected void criarComponentes() {
            // Implementação vazia para teste
        }
        
        @Override
        protected void adicionarComponentes() {
            // Implementação vazia para teste
        }
        
        @Override
        protected void configurarEventos() {
            // Implementação vazia para teste
        }
    }
    
    private TelaBaseConcreta telaBase;
    
    @BeforeEach
    public void setUp() {
        telaBase = new TelaBaseConcreta();
    }
    
    @Test
    public void testConstrutor() {
        assertNotNull(telaBase);
        assertEquals("Tela Teste", telaBase.getTitle());
        assertFalse(telaBase.isResizable());
    }
    
    @Test
    public void testCriarBotao() {
        JButton botao = telaBase.criarBotao("Teste");
        assertNotNull(botao);
        assertEquals("Teste", botao.getText());
        assertEquals(TelaBase.FONTE_BOTAO, botao.getFont());
        assertEquals(TelaBase.COR_BOTAO, botao.getBackground());
        assertEquals(TelaBase.COR_TEXTO_BOTAO, botao.getForeground());
    }
    
    @Test
    public void testCriarBotaoGrande() {
        JButton botao = telaBase.criarBotaoGrande("Teste Grande");
        assertNotNull(botao);
        assertEquals("Teste Grande", botao.getText());
        assertEquals(TelaBase.FONTE_BOTAO_GRANDE, botao.getFont());
        assertEquals(new Dimension(200, 50), botao.getPreferredSize());
    }
    
    @Test
    public void testCriarTitulo() {
        JLabel titulo = telaBase.criarTitulo("Título Teste");
        assertNotNull(titulo);
        assertEquals("Título Teste", titulo.getText());
        assertEquals(TelaBase.FONTE_TITULO, titulo.getFont());
        assertEquals(TelaBase.COR_TEXTO_BRANCO, titulo.getForeground());
        assertEquals(SwingConstants.CENTER, titulo.getHorizontalAlignment());
    }
    
    @Test
    public void testCriarTituloGrande() {
        JLabel titulo = telaBase.criarTituloGrande("Título Grande");
        assertNotNull(titulo);
        assertEquals("Título Grande", titulo.getText());
        assertEquals(TelaBase.FONTE_TITULO_GRANDE, titulo.getFont());
        assertEquals(TelaBase.COR_TEXTO_BRANCO, titulo.getForeground());
        assertEquals(SwingConstants.CENTER, titulo.getHorizontalAlignment());
    }
    
    @Test
    public void testCriarPainelPrincipal() {
        JPanel painel = telaBase.criarPainelPrincipal();
        assertNotNull(painel);
        assertEquals(TelaBase.COR_FUNDO, painel.getBackground());
        assertTrue(painel.getLayout() instanceof GridBagLayout);
    }
    
    @Test
    public void testCriarPainelPrincipalComLayout() {
        FlowLayout layout = new FlowLayout();
        JPanel painel = telaBase.criarPainelPrincipal(layout);
        assertNotNull(painel);
        assertEquals(TelaBase.COR_FUNDO, painel.getBackground());
        assertEquals(layout, painel.getLayout());
    }
    
    @Test
    public void testCriarGridBagConstraints() {
        GridBagConstraints gbc = telaBase.criarGridBagConstraints();
        assertNotNull(gbc);
        assertEquals(GridBagConstraints.REMAINDER, gbc.gridwidth);
        assertEquals(GridBagConstraints.HORIZONTAL, gbc.fill);
        assertNotNull(gbc.insets);
    }
    
    @Test
    public void testCriarLabelResultado() {
        JLabel resultadoVitoria = telaBase.criarLabelResultado("Vitória!", true);
        assertNotNull(resultadoVitoria);
        assertEquals("Vitória!", resultadoVitoria.getText());
        assertEquals(TelaBase.FONTE_RESULTADO, resultadoVitoria.getFont());
        assertEquals(TelaBase.COR_VERDE, resultadoVitoria.getForeground());
        
        JLabel resultadoDerrota = telaBase.criarLabelResultado("Derrota!", false);
        assertNotNull(resultadoDerrota);
        assertEquals("Derrota!", resultadoDerrota.getText());
        assertEquals(TelaBase.COR_VERMELHO, resultadoDerrota.getForeground());
    }
} 