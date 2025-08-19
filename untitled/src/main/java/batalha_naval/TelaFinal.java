package batalha_naval;

import javax.swing.*;
import java.awt.*;

/**
 * Classe que representa a tela final do jogo.
 * Exibe o resultado da partida e opções para continuar.
 * 
 * @author ERIK
 * @version 1.0.2
 */
public class TelaFinal extends JFrame {
    /** Botão para jogar novamente */
    private JButton btnJogarNovamente;
    
    /** Botão para voltar ao menu */
    private JButton btnVoltarMenu;
    
    /** Botão para sair do jogo */
    private JButton btnSair;
    
    /** Painel principal da tela */
    private JPanel painelPrincipal;
    
    /** Label que mostra o resultado */
    private JLabel lblResultado;

    /**
     * Construtor da classe TelaFinal.
     * 
     * @param vitoria true se o jogador venceu, false se perdeu
     */
    public TelaFinal(boolean vitoria) {
        configurarJanela();
        criarComponentes(vitoria);
        adicionarComponentes();
        configurarEventos();
    }

    /**
     * Configura as propriedades da janela.
     */
    private void configurarJanela() {
        setTitle("Fim de Jogo - Batalha Naval");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    /**
     * Cria os componentes da interface.
     * 
     * @param vitoria true se o jogador venceu, false se perdeu
     */
    private void criarComponentes(boolean vitoria) {
        painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new GridBagLayout());
        painelPrincipal.setBackground(new Color(0, 105, 148));

        // Configura o texto do resultado
        if (vitoria) {
            lblResultado = new JLabel("PARABÉNS! VOCÊ VENCEU!");
            lblResultado.setForeground(new Color(0, 255, 0)); // Verde para vitória
        } else {
            lblResultado = new JLabel("GAME OVER! VOCÊ PERDEU!");
            lblResultado.setForeground(new Color(255, 0, 0)); // Vermelho para derrota
        }
        lblResultado.setFont(new Font("Arial", Font.BOLD, 36));
        lblResultado.setHorizontalAlignment(SwingConstants.CENTER);

        btnJogarNovamente = new JButton("Jogar Novamente");
        btnJogarNovamente.setFont(new Font("Arial", Font.BOLD, 18));
        btnJogarNovamente.setBackground(new Color(255, 255, 255));
        btnJogarNovamente.setForeground(new Color(0, 105, 148));
        btnJogarNovamente.setPreferredSize(new Dimension(200, 50));

        btnVoltarMenu = new JButton("Voltar ao Menu");
        btnVoltarMenu.setFont(new Font("Arial", Font.BOLD, 18));
        btnVoltarMenu.setBackground(new Color(255, 255, 255));
        btnVoltarMenu.setForeground(new Color(0, 105, 148));
        btnVoltarMenu.setPreferredSize(new Dimension(200, 50));

        btnSair = new JButton("Sair do Jogo");
        btnSair.setFont(new Font("Arial", Font.BOLD, 18));
        btnSair.setBackground(new Color(255, 255, 255));
        btnSair.setForeground(new Color(0, 105, 148));
        btnSair.setPreferredSize(new Dimension(200, 50));
    }

    /**
     * Adiciona os componentes ao painel principal.
     */
    private void adicionarComponentes() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 50, 20, 50);

        // Título do jogo
        JLabel titulo = new JLabel("BATALHA NAVAL");
        titulo.setFont(new Font("Arial", Font.BOLD, 48));
        titulo.setForeground(Color.WHITE);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        painelPrincipal.add(titulo, gbc);

        // Espaço
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 50)), gbc);

        // Resultado
        painelPrincipal.add(lblResultado, gbc);

        // Espaço
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 80)), gbc);

        // Botões
        painelPrincipal.add(btnJogarNovamente, gbc);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)), gbc);
        painelPrincipal.add(btnVoltarMenu, gbc);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)), gbc);
        painelPrincipal.add(btnSair, gbc);

        add(painelPrincipal);
    }

    /**
     * Configura os eventos dos componentes.
     */
    private void configurarEventos() {
        btnJogarNovamente.addActionListener(e -> jogarNovamente());
        btnVoltarMenu.addActionListener(e -> voltarAoMenu());
        btnSair.addActionListener(e -> System.exit(0));
    }

    /**
     * Inicia um novo jogo.
     */
    private void jogarNovamente() {
        TelaPosicionamento telaPosicionamento = new TelaPosicionamento();
        telaPosicionamento.setVisible(true);
        dispose();
    }

    /**
     * Volta para o menu inicial.
     */
    private void voltarAoMenu() {
        TelaInicial telaInicial = new TelaInicial();
        telaInicial.setVisible(true);
        dispose();
    }

    /**
     * Método principal para testar a tela final.
     * 
     * @param args Argumentos da linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaFinal telaFinal = new TelaFinal(true); // true para vitória, false para derrota
            telaFinal.setVisible(true);
        });
    }
} 