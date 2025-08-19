package batalha_naval;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Classe que representa a tela inicial do jogo Batalha Naval.
 * Gerencia a interface gráfica inicial e a transição para o jogo.
 * 
 * @author ERIK
 * @version 1.0.2
 */
public class TelaInicial extends JFrame {
    /** Botão para iniciar o jogo */
    private JButton btnIniciar;
    
    /** Botão para sair do jogo */
    private JButton btnSair;
    
    /** Botão para mostrar as regras do jogo */
    private JButton btnRegras;
    
    /** Painel principal da tela */
    private JPanel painelPrincipal;

    /**
     * Construtor da classe TelaInicial.
     * Configura a janela e seus componentes.
     */
    public TelaInicial() {
        configurarJanela();
        criarComponentes();
        adicionarComponentes();
        configurarEventos();
    }

    /**
     * Configura as propriedades da janela.
     */
    private void configurarJanela() {
        setTitle("Batalha Naval");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    /**
     * Cria os componentes da interface.
     */
    private void criarComponentes() {
        painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new GridBagLayout());
        painelPrincipal.setBackground(new Color(0, 105, 148));

        btnIniciar = new JButton("Iniciar Jogo");
        btnIniciar.setFont(new Font("Arial", Font.BOLD, 16));
        btnIniciar.setBackground(new Color(255, 255, 255));
        btnIniciar.setForeground(new Color(0, 105, 148));

        btnRegras = new JButton("Regras");
        btnRegras.setFont(new Font("Arial", Font.BOLD, 16));
        btnRegras.setBackground(new Color(255, 255, 255));
        btnRegras.setForeground(new Color(0, 105, 148));

        btnSair = new JButton("Sair");
        btnSair.setFont(new Font("Arial", Font.BOLD, 16));
        btnSair.setBackground(new Color(255, 255, 255));
        btnSair.setForeground(new Color(0, 105, 148));


    }

    /**
     * Adiciona os componentes ao painel principal.
     */
    private void adicionarComponentes() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 50, 10, 50);

        JLabel titulo = new JLabel("BATALHA NAVAL");
        titulo.setFont(new Font("Arial", Font.BOLD, 32));
        titulo.setForeground(Color.WHITE);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        painelPrincipal.add(titulo, gbc);

        painelPrincipal.add(btnIniciar, gbc);
        painelPrincipal.add(btnRegras, gbc);
        painelPrincipal.add(btnSair, gbc);

        add(painelPrincipal);
    }

    /**
     * Configura os eventos dos componentes.
     */
    private void configurarEventos() {
        btnIniciar.addActionListener(e -> iniciarJogo());
        btnSair.addActionListener(e -> System.exit(0));
        btnRegras.addActionListener(e -> mostrarRegras());
    }

    /**
     * Inicia o jogo, criando uma nova instância de TelaPosicionamento.
     */
    private void iniciarJogo() {
        TelaPosicionamento telaPosicionamento = new TelaPosicionamento();
        telaPosicionamento.setVisible(true);
        dispose();
    }

    /**
     * Exibe as regras do jogo em um JOptionPane.
     */
    private void mostrarRegras() {
        String regras = "Como Jogar:\n" +
                "1. Inicie o jogo clicando em 'JOGAR'\n" +
                "2. Posicione seus navios no tabuleiro:\n" +
                "   - Clique em uma posição para posicionar o navio\n" +
                "   - Use o botão 'GIRAR NAVIO' para mudar a orientação\n" +
                "   - Navios não podem se sobrepor ou ultrapassar os limites do tabuleiro\n" +
                "3. Após posicionar todos os navios, clique em 'INICIAR JOGO'\n" +
                "4. Na fase de batalha:\n" +
                "   - Clique em uma posição no tabuleiro do computador para atirar\n" +
                "   - Se acertar um navio, joga novamente\n" +
                "   - Se errar, passa a vez para o computador\n" +
                "5. Vence quem destruir todos os navios do oponente primeiro.";
        JOptionPane.showMessageDialog(this, regras, "Regras do Jogo", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Método principal que inicia a aplicação.
     * 
     * @param args Argumentos da linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaInicial telaInicial = new TelaInicial();
            telaInicial.setVisible(true);
        });
    }
} 