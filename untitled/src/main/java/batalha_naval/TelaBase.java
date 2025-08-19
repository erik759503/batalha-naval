package batalha_naval;

import javax.swing.*;
import java.awt.*;

/**
 * Classe base abstrata para todas as telas do jogo Batalha Naval.
 * Implementa funcionalidades comuns a todas as telas para evitar duplicação de código.
 * 
 * @author ERIK
 * @version 1.0.2
 */
public abstract class TelaBase extends JFrame {
    
    /** Cores padrão do jogo */
    protected static final Color COR_FUNDO = new Color(0, 105, 148);
    protected static final Color COR_BOTAO = new Color(255, 255, 255);
    protected static final Color COR_TEXTO_BOTAO = new Color(0, 105, 148);
    protected static final Color COR_TEXTO_BRANCO = Color.WHITE;
    protected static final Color COR_VERDE = new Color(0, 255, 0);
    protected static final Color COR_VERMELHO = new Color(255, 0, 0);
    
    /** Fontes padrão do jogo */
    protected static final Font FONTE_TITULO = new Font("Arial", Font.BOLD, 32);
    protected static final Font FONTE_TITULO_GRANDE = new Font("Arial", Font.BOLD, 48);
    protected static final Font FONTE_BOTAO = new Font("Arial", Font.BOLD, 16);
    protected static final Font FONTE_BOTAO_GRANDE = new Font("Arial", Font.BOLD, 18);
    protected static final Font FONTE_RESULTADO = new Font("Arial", Font.BOLD, 36);
    
    /** Painel principal da tela */
    protected JPanel painelPrincipal;

    /**
     * Construtor da classe TelaBase.
     * 
     * @param titulo Título da janela
     */
    public TelaBase(String titulo) {
        configurarJanela(titulo);
        inicializarTela();
    }

    /**
     * Configura as propriedades básicas da janela.
     * 
     * @param titulo Título da janela
     */
    private void configurarJanela(String titulo) {
        setTitle(titulo);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    /**
     * Inicializa a tela chamando os métodos abstratos.
     */
    private void inicializarTela() {
        criarComponentes();
        adicionarComponentes();
        configurarEventos();
    }

    /**
     * Cria um botão padronizado com as cores e fontes do jogo.
     * 
     * @param texto Texto do botão
     * @return JButton configurado
     */
    protected JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setFont(FONTE_BOTAO);
        botao.setBackground(COR_BOTAO);
        botao.setForeground(COR_TEXTO_BOTAO);
        return botao;
    }

    /**
     * Cria um botão grande padronizado.
     * 
     * @param texto Texto do botão
     * @return JButton configurado
     */
    protected JButton criarBotaoGrande(String texto) {
        JButton botao = criarBotao(texto);
        botao.setFont(FONTE_BOTAO_GRANDE);
        botao.setPreferredSize(new Dimension(200, 50));
        return botao;
    }

    /**
     * Cria um label de título padronizado.
     * 
     * @param texto Texto do título
     * @return JLabel configurado
     */
    protected JLabel criarTitulo(String texto) {
        JLabel titulo = new JLabel(texto);
        titulo.setFont(FONTE_TITULO);
        titulo.setForeground(COR_TEXTO_BRANCO);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        return titulo;
    }

    /**
     * Cria um label de título grande padronizado.
     * 
     * @param texto Texto do título
     * @return JLabel configurado
     */
    protected JLabel criarTituloGrande(String texto) {
        JLabel titulo = new JLabel(texto);
        titulo.setFont(FONTE_TITULO_GRANDE);
        titulo.setForeground(COR_TEXTO_BRANCO);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        return titulo;
    }

    /**
     * Cria um painel principal padronizado.
     * 
     * @return JPanel configurado
     */
    protected JPanel criarPainelPrincipal() {
        JPanel painel = new JPanel();
        painel.setLayout(new GridBagLayout());
        painel.setBackground(COR_FUNDO);
        return painel;
    }

    /**
     * Cria um painel principal com layout específico.
     * 
     * @param layout Layout do painel
     * @return JPanel configurado
     */
    protected JPanel criarPainelPrincipal(LayoutManager layout) {
        JPanel painel = new JPanel(layout);
        painel.setBackground(COR_FUNDO);
        return painel;
    }

    /**
     * Cria um GridBagConstraints padronizado para centralizar componentes.
     * 
     * @return GridBagConstraints configurado
     */
    protected GridBagConstraints criarGridBagConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 50, 10, 50);
        return gbc;
    }

    /**
     * Adiciona um componente com espaçamento ao painel principal.
     * 
     * @param componente Componente a ser adicionado
     * @param altura Altura do espaçamento
     */
    protected void adicionarComEspacamento(JComponent componente, int altura) {
        if (painelPrincipal.getLayout() instanceof GridBagLayout) {
            GridBagConstraints gbc = criarGridBagConstraints();
            painelPrincipal.add(componente, gbc);
            if (altura > 0) {
                painelPrincipal.add(Box.createRigidArea(new Dimension(0, altura)), gbc);
            }
        }
    }

    /**
     * Cria um label de resultado (vitória/derrota).
     * 
     * @param texto Texto do resultado
     * @param vitoria true para cor verde, false para cor vermelha
     * @return JLabel configurado
     */
    protected JLabel criarLabelResultado(String texto, boolean vitoria) {
        JLabel label = new JLabel(texto);
        label.setFont(FONTE_RESULTADO);
        label.setForeground(vitoria ? COR_VERDE : COR_VERMELHO);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }

    /**
     * Método abstrato para criar os componentes específicos da tela.
     * Deve ser implementado pelas classes filhas.
     */
    protected abstract void criarComponentes();

    /**
     * Método abstrato para adicionar os componentes ao painel principal.
     * Deve ser implementado pelas classes filhas.
     */
    protected abstract void adicionarComponentes();

    /**
     * Método abstrato para configurar os eventos dos componentes.
     * Deve ser implementado pelas classes filhas.
     */
    protected abstract void configurarEventos();

    /**
     * Método para navegar para outra tela.
     * 
     * @param novaTela Nova tela a ser exibida
     */
    protected void navegarPara(JFrame novaTela) {
        novaTela.setVisible(true);
        dispose();
    }

    /**
     * Método para voltar ao menu inicial.
     */
    protected void voltarAoMenu() {
        TelaInicial telaInicial = new TelaInicial();
        navegarPara(telaInicial);
    }

    /**
     * Método para sair do jogo.
     */
    protected void sairDoJogo() {
        System.exit(0);
    }

    /**
     * Método para mostrar uma mensagem de erro.
     * 
     * @param mensagem Mensagem de erro
     */
    protected void mostrarErro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Método para mostrar uma mensagem de informação.
     * 
     * @param mensagem Mensagem de informação
     * @param titulo Título da janela
     */
    protected void mostrarInformacao(String mensagem, String titulo) {
        JOptionPane.showMessageDialog(this, mensagem, titulo, JOptionPane.INFORMATION_MESSAGE);
    }
} 