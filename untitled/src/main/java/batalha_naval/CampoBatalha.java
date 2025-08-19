package batalha_naval;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

/**
 * Classe que representa o campo de batalha do jogo.
 * Gerencia a interface gráfica do jogo, incluindo os tabuleiros e as jogadas.
 * 
 * @author ERIK
 * @version 1.0.2
 */
public class CampoBatalha extends JFrame {
    /** Tabuleiro do jogador */
    private Tabuleiro tabuleiroJogador;
    
    /** Tabuleiro do computador */
    private Tabuleiro tabuleiroComputador;
    
    /** Matriz de botões do tabuleiro do jogador */
    private JButton[][] botoesJogador;
    
    /** Matriz de botões do tabuleiro do computador */
    private JButton[][] botoesComputador;
    
    /** Painel que contém o tabuleiro do jogador */
    private JPanel painelTabuleiroJogador;
    
    /** Painel que contém o tabuleiro do computador */
    private JPanel painelTabuleiroComputador;
    
    /** Label que mostra o status do jogo */
    private JLabel lblStatus;
    
    /** Botão para reiniciar o jogo */
    private JButton btnReiniciar;
    
    /** Botão para voltar ao menu */
    private JButton btnVoltar;
    
    /** Indica se é a vez do jogador */
    private boolean vezJogador;

    /**
     * Construtor da classe CampoBatalha.
     * 
     * @param tabuleiroJogador Tabuleiro do jogador
     */
    public CampoBatalha(Tabuleiro tabuleiroJogador) {
        this.tabuleiroJogador = tabuleiroJogador;
        this.tabuleiroComputador = new Tabuleiro();
        this.vezJogador = true;
        
        configurarJanela();
        criarComponentes();
        adicionarComponentes();
        configurarEventos();
        
        Computador computador = new Computador(tabuleiroComputador);
        computador.posicionarNavios();
        
        // Atualiza a visualização inicial do tabuleiro do jogador
        atualizarTabuleiroJogador();
    }

    /**
     * Configura as propriedades da janela.
     */
    private void configurarJanela() {
        setTitle("Campo de Batalha");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    /**
     * Cria os componentes da interface.
     */
    private void criarComponentes() {
        painelTabuleiroJogador = new JPanel(new GridLayout(Tabuleiro.TAMANHO, Tabuleiro.TAMANHO));
        painelTabuleiroJogador.setBorder(BorderFactory.createTitledBorder("Seu Tabuleiro"));
        botoesJogador = new JButton[Tabuleiro.TAMANHO][Tabuleiro.TAMANHO];

        painelTabuleiroComputador = new JPanel(new GridLayout(Tabuleiro.TAMANHO, Tabuleiro.TAMANHO));
        painelTabuleiroComputador.setBorder(BorderFactory.createTitledBorder("Tabuleiro do Computador"));
        botoesComputador = new JButton[Tabuleiro.TAMANHO][Tabuleiro.TAMANHO];

        for (int i = 0; i < Tabuleiro.TAMANHO; i++) {
            for (int j = 0; j < Tabuleiro.TAMANHO; j++) {
                botoesJogador[i][j] = new JButton();
                botoesJogador[i][j].setPreferredSize(new Dimension(Tabuleiro.TAMANHO_CELULA, Tabuleiro.TAMANHO_CELULA));
                botoesJogador[i][j].setBackground(Tabuleiro.COR_AGUA);

                botoesComputador[i][j] = new JButton();
                botoesComputador[i][j].setPreferredSize(new Dimension(Tabuleiro.TAMANHO_CELULA, Tabuleiro.TAMANHO_CELULA));
                botoesComputador[i][j].setBackground(Tabuleiro.COR_AGUA);

                painelTabuleiroJogador.add(botoesJogador[i][j]);
                painelTabuleiroComputador.add(botoesComputador[i][j]);
            }
        }

        lblStatus = new JLabel("Sua vez de atacar!");
        lblStatus.setFont(new Font("Arial", Font.BOLD, 16));
        lblStatus.setHorizontalAlignment(SwingConstants.CENTER);

        btnReiniciar = new JButton("Reiniciar Jogo");
        btnVoltar = new JButton("Voltar ao Menu");
    }

    /**
     * Adiciona os componentes ao painel principal.
     */
    private void adicionarComponentes() {
        JPanel painelSuperior = new JPanel(new BorderLayout());
        painelSuperior.add(lblStatus, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        painelBotoes.add(btnReiniciar);
        painelBotoes.add(btnVoltar);
        painelSuperior.add(painelBotoes, BorderLayout.SOUTH);

        JPanel painelTabuleiros = new JPanel(new GridLayout(1, 2, 20, 0));
        painelTabuleiros.add(painelTabuleiroJogador);
        painelTabuleiros.add(painelTabuleiroComputador);

        setLayout(new BorderLayout());
        add(painelSuperior, BorderLayout.NORTH);
        add(painelTabuleiros, BorderLayout.CENTER);
    }

    /**
     * Configura os eventos dos componentes.
     */
    private void configurarEventos() {
        btnReiniciar.addActionListener(e -> reiniciarJogo());
        btnVoltar.addActionListener(e -> voltarAoMenu());

        for (int i = 0; i < Tabuleiro.TAMANHO; i++) {
            for (int j = 0; j < Tabuleiro.TAMANHO; j++) {
                final int linha = i;
                final int coluna = j;
                botoesComputador[i][j].addActionListener(e -> realizarJogada(linha, coluna));
            }
        }
    }

    /**
     * Realiza uma jogada no tabuleiro do computador.
     * 
     * @param linha Linha do ataque
     * @param coluna Coluna do ataque
     */
    private void realizarJogada(int linha, int coluna) {
        if (!vezJogador) {
            return;
        }

        // Verifica se a posição já foi atacada
        char posicaoAtual = tabuleiroComputador.getMatriz()[linha][coluna];
        if (posicaoAtual == 'X' || posicaoAtual == 'O') {
            // Posição já foi atacada
            lblStatus.setText("Você já atirou nesta posição! Escolha outra.");
            return;
        }

        if (tabuleiroComputador.atacar(linha, coluna)) {
            botoesComputador[linha][coluna].setBackground(Tabuleiro.COR_ACERTO);
            lblStatus.setText("Você acertou um navio! Jogue novamente.");
        } else {
            botoesComputador[linha][coluna].setBackground(Tabuleiro.COR_ERRO);
            lblStatus.setText("Você atingiu a água! Vez do computador.");
            vezJogador = false;
            ataqueComputador();
        }

        // Verifica se o jogador venceu
        if (tabuleiroComputador.todosNaviosDestruidos()) {
            System.out.println("Jogador venceu! Todos os navios do computador foram destruídos.");
            TelaFinal telaFinal = new TelaFinal(true); // true = vitória do jogador
            telaFinal.setVisible(true);
            dispose();
            return;
        }
    }

    /**
     * Realiza o ataque do computador no tabuleiro do jogador.
     */
    private void ataqueComputador() {
        Computador computador = new Computador(tabuleiroComputador);
        
        // Gera uma posição aleatória que não foi atacada
        int linha, coluna;
        do {
            linha = (int) (Math.random() * Tabuleiro.TAMANHO);
            coluna = (int) (Math.random() * Tabuleiro.TAMANHO);
        } while (tabuleiroJogador.getMatriz()[linha][coluna] == 'X' || 
                 tabuleiroJogador.getMatriz()[linha][coluna] == 'O');

        if (tabuleiroJogador.atacar(linha, coluna)) {
            botoesJogador[linha][coluna].setBackground(Tabuleiro.COR_ACERTO);
            lblStatus.setText("O computador acertou seu navio! Vez do computador.");
            
            // Verifica se o computador venceu
            if (tabuleiroJogador.todosNaviosDestruidos()) {
                System.out.println("Computador venceu! Todos os navios do jogador foram destruídos.");
                TelaFinal telaFinal = new TelaFinal(false); // false = derrota do jogador
                telaFinal.setVisible(true);
                dispose();
                return;
            }
            
            ataqueComputador();
        } else {
            botoesJogador[linha][coluna].setBackground(Tabuleiro.COR_ERRO);
            lblStatus.setText("O computador atingiu a água! Sua vez.");
            vezJogador = true;
        }

        // Atualiza a visualização do tabuleiro do jogador após o ataque
        atualizarTabuleiroJogador();
    }

    /**
     * Reinicia o jogo.
     */
    private void reiniciarJogo() {
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
     * Atualiza a visualização do tabuleiro do jogador mostrando os navios.
     */
    private void atualizarTabuleiroJogador() {
        for (int i = 0; i < Tabuleiro.TAMANHO; i++) {
            for (int j = 0; j < Tabuleiro.TAMANHO; j++) {
                char posicao = tabuleiroJogador.getMatriz()[i][j];
                if (posicao == 'N') {
                    // Navio não atingido
                    botoesJogador[i][j].setBackground(Tabuleiro.COR_NAVIO);
                } else if (posicao == 'X') {
                    // Navio atingido
                    botoesJogador[i][j].setBackground(Tabuleiro.COR_ACERTO);
                } else if (posicao == 'O') {
                    // Água atingida
                    botoesJogador[i][j].setBackground(Tabuleiro.COR_ERRO);
                } else {
                    // Água
                    botoesJogador[i][j].setBackground(Tabuleiro.COR_AGUA);
                }
            }
        }
    }
} 