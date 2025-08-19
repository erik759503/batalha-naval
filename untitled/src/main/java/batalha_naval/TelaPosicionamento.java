package batalha_naval;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

/**
 * Classe que representa a tela de posicionamento dos navios.
 * Gerencia a interface gráfica para posicionar os navios no tabuleiro.
 * 
 * @author ERIK
 * @version 1.0.2
 */
public class TelaPosicionamento extends JFrame {
    /** Tabuleiro do jogador */
    private Tabuleiro tabuleiro;
    
    /** Matriz de botões que representa o tabuleiro */
    private JButton[][] botoes;
    
    /** Navio atual sendo posicionado */
    private Navio navioAtual;
    
    /** Índice do navio atual na lista de navios */
    private int indiceNavioAtual;
    
    /** Lista de navios a serem posicionados */
    private Navio[] navios;
    
    /** Botão para girar o navio */
    private JButton btnGirar;
    
    /** Botão para confirmar o posicionamento */
    private JButton btnConfirmar;
    
    /** Botão para reiniciar o posicionamento */
    private JButton btnReiniciar;
    
    /** Painel que contém o tabuleiro */
    private JPanel painelTabuleiro;
    
    /** Painel que contém os controles */
    private JPanel painelControles;
    
    /** Label que mostra o navio atual */
    private JLabel lblNavioAtual;

    /**
     * Construtor da classe TelaPosicionamento.
     * Configura a janela e seus componentes.
     */
    public TelaPosicionamento() {
        configurarJanela();
        inicializarNavios();
        criarComponentes();
        adicionarComponentes();
        configurarEventos();
    }

    /**
     * Configura as propriedades da janela.
     */
    private void configurarJanela() {
        setTitle("Posicionar Navios");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    /**
     * Inicializa a lista de navios a serem posicionados.
     */
    private void inicializarNavios() {
        tabuleiro = new Tabuleiro();
        navios = new Navio[] {
            new Navio(5), // Porta-aviões
            new Navio(4), // Encouraçado
            new Navio(3), // Cruzador
            new Navio(3), // Submarino
            new Navio(2)  // Destruidor
        };
        indiceNavioAtual = 0;
        navioAtual = navios[indiceNavioAtual];
    }

    /**
     * Cria os componentes da interface.
     */
    private void criarComponentes() {
        painelTabuleiro = new JPanel(new GridLayout(Tabuleiro.TAMANHO, Tabuleiro.TAMANHO));
        painelTabuleiro.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        botoes = new JButton[Tabuleiro.TAMANHO][Tabuleiro.TAMANHO];

        for (int i = 0; i < Tabuleiro.TAMANHO; i++) {
            for (int j = 0; j < Tabuleiro.TAMANHO; j++) {
                botoes[i][j] = new JButton();
                botoes[i][j].setPreferredSize(new Dimension(Tabuleiro.TAMANHO_CELULA, Tabuleiro.TAMANHO_CELULA));
                botoes[i][j].setBackground(Tabuleiro.COR_AGUA);
                painelTabuleiro.add(botoes[i][j]);
            }
        }

        painelControles = new JPanel();
        painelControles.setLayout(new BoxLayout(painelControles, BoxLayout.Y_AXIS));
        painelControles.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        btnGirar = new JButton("Girar Navio");
        btnConfirmar = new JButton("Confirmar Posição");
        btnReiniciar = new JButton("Reiniciar");
        lblNavioAtual = new JLabel("Posicione seu " + getNomeNavioAtual());
        lblNavioAtual.setFont(new Font("Arial", Font.BOLD, 16));
    }

    /**
     * Adiciona os componentes ao painel principal.
     */
    private void adicionarComponentes() {
        painelControles.add(lblNavioAtual);
        painelControles.add(Box.createRigidArea(new Dimension(0, 20)));
        painelControles.add(btnGirar);
        painelControles.add(Box.createRigidArea(new Dimension(0, 10)));
        painelControles.add(btnConfirmar);
        painelControles.add(Box.createRigidArea(new Dimension(0, 10)));
        painelControles.add(btnReiniciar);

        setLayout(new BorderLayout());
        add(painelTabuleiro, BorderLayout.CENTER);
        add(painelControles, BorderLayout.EAST);
    }

    /**
     * Configura os eventos dos componentes.
     */
    private void configurarEventos() {
        btnGirar.addActionListener(e -> girarNavio());
        btnConfirmar.addActionListener(e -> confirmarPosicao());
        btnReiniciar.addActionListener(e -> reiniciarPosicionamento());

        for (int i = 0; i < Tabuleiro.TAMANHO; i++) {
            for (int j = 0; j < Tabuleiro.TAMANHO; j++) {
                final int linha = i;
                final int coluna = j;
                botoes[i][j].addActionListener(e -> selecionarPosicao(linha, coluna));
            }
        }
    }

    /**
     * Retorna o nome do navio atual.
     * 
     * @return Nome do navio atual
     */
    private String getNomeNavioAtual() {
        String[] nomes = {"Porta-aviões", "Encouraçado", "Cruzador", "Submarino", "Destruidor"};
        return nomes[indiceNavioAtual];
    }

    /**
     * Gira o navio atual, alternando entre orientação horizontal e vertical.
     */
    private void girarNavio() {
        navioAtual.setPosicao(navioAtual.getLinha(), navioAtual.getColuna(), !navioAtual.isHorizontal());
        atualizarTabuleiro();
    }

    /**
     * Confirma a posição atual do navio e passa para o próximo.
     */
    private void confirmarPosicao() {
        if (navioAtual.getLinha() == -1 || navioAtual.getColuna() == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma posição válida para o navio!");
            return;
        }

        if (tabuleiro.posicionarNavio(navioAtual, navioAtual.getLinha(), navioAtual.getColuna(), navioAtual.isHorizontal())) {
            indiceNavioAtual++;
            if (indiceNavioAtual < navios.length) {
                navioAtual = navios[indiceNavioAtual];
                lblNavioAtual.setText("Posicione seu " + getNomeNavioAtual());
                atualizarTabuleiro();
            } else {
                iniciarJogo();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Posição inválida! Tente novamente.");
        }
    }

    /**
     * Reinicia o posicionamento dos navios.
     */
    private void reiniciarPosicionamento() {
        tabuleiro = new Tabuleiro();
        indiceNavioAtual = 0;
        navioAtual = navios[indiceNavioAtual];
        lblNavioAtual.setText("Posicione seu " + getNomeNavioAtual());
        atualizarTabuleiro();
    }

    /**
     * Seleciona uma posição no tabuleiro para o navio atual.
     * 
     * @param linha Linha selecionada
     * @param coluna Coluna selecionada
     */
    private void selecionarPosicao(int linha, int coluna) {
        navioAtual.setPosicao(linha, coluna, navioAtual.isHorizontal());
        atualizarTabuleiro();
    }

    /**
     * Atualiza a interface do tabuleiro.
     */
    private void atualizarTabuleiro() {
        for (int i = 0; i < Tabuleiro.TAMANHO; i++) {
            for (int j = 0; j < Tabuleiro.TAMANHO; j++) {
                botoes[i][j].setBackground(Tabuleiro.COR_AGUA);
                botoes[i][j].setIcon(null);
            }
        }

        // Desenha os navios já posicionados
        for (int i = 0; i < indiceNavioAtual; i++) {
            Navio navio = navios[i];
            int linha = navio.getLinha();
            int coluna = navio.getColuna();
            int tamanho = navio.getTamanho();

            if (navio.isHorizontal()) {
                for (int j = 0; j < tamanho; j++) {
                    botoes[linha][coluna + j].setBackground(Tabuleiro.COR_NAVIO);
                }
            } else {
                for (int j = 0; j < tamanho; j++) {
                    botoes[linha + j][coluna].setBackground(Tabuleiro.COR_NAVIO);
                }
            }
        }

        // Desenha o navio atual
        if (navioAtual.getLinha() != -1 && navioAtual.getColuna() != -1) {
            int linha = navioAtual.getLinha();
            int coluna = navioAtual.getColuna();
            int tamanho = navioAtual.getTamanho();

            if (navioAtual.isHorizontal()) {
                for (int j = 0; j < tamanho; j++) {
                    if (coluna + j < Tabuleiro.TAMANHO) {
                        botoes[linha][coluna + j].setBackground(Tabuleiro.COR_NAVIO);
                    }
                }
            } else {
                for (int j = 0; j < tamanho; j++) {
                    if (linha + j < Tabuleiro.TAMANHO) {
                        botoes[linha + j][coluna].setBackground(Tabuleiro.COR_NAVIO);
                    }
                }
            }
        }
    }

    /**
     * Inicia o jogo após o posicionamento dos navios.
     */
    private void iniciarJogo() {
        CampoBatalha campoBatalha = new CampoBatalha(tabuleiro);
        campoBatalha.setVisible(true);
        dispose();
    }
} 