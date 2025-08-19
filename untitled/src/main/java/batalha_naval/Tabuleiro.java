package batalha_naval;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa o tabuleiro do jogo Batalha Naval.
 * Gerencia a matriz do tabuleiro, navios e suas posições.
 * 
 * @author ERIK
 * @version 1.0.2
 */
public class Tabuleiro {
    /** Tamanho do tabuleiro (10x10) */
    public static final int TAMANHO = 10;
    
    /** Tamanho de cada célula do tabuleiro em pixels */
    public static final int TAMANHO_CELULA = 40;
    
    /** Cores do tabuleiro */
    public static final java.awt.Color COR_AGUA = new java.awt.Color(0, 105, 148);
    public static final java.awt.Color COR_NAVIO = new java.awt.Color(128, 128, 128);
    public static final java.awt.Color COR_ACERTO = new java.awt.Color(255, 0, 0);
    public static final java.awt.Color COR_ERRO = new java.awt.Color(255, 255, 255);
    
    /** Matriz que representa o tabuleiro */
    private char[][] matriz;
    
    /** Lista de navios posicionados no tabuleiro */
    private List<Navio> navios;

    /**
     * Construtor da classe Tabuleiro.
     * Inicializa a matriz do tabuleiro e a lista de navios.
     */
    public Tabuleiro() {
        matriz = new char[TAMANHO][TAMANHO];
        navios = new ArrayList<>();
        inicializarTabuleiro();
    }

    /**
     * Inicializa o tabuleiro com água em todas as posições.
     */
    private void inicializarTabuleiro() {
        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                matriz[i][j] = '~';
            }
        }
    }

    /**
     * Verifica se uma posição está dentro dos limites do tabuleiro.
     * 
     * @param linha Linha a ser verificada
     * @param coluna Coluna a ser verificada
     * @return true se a posição é válida, false caso contrário
     */
    public boolean posicaoValida(int linha, int coluna) {
        return linha >= 0 && linha < TAMANHO && coluna >= 0 && coluna < TAMANHO;
    }

    /**
     * Verifica se uma posição está ocupada por um navio.
     * 
     * @param linha Linha a ser verificada
     * @param coluna Coluna a ser verificada
     * @return true se a posição está ocupada, false caso contrário
     */
    public boolean posicaoOcupada(int linha, int coluna) {
        return matriz[linha][coluna] != '~';
    }

    /**
     * Posiciona um navio no tabuleiro.
     * 
     * @param navio Navio a ser posicionado
     * @param linha Linha inicial do navio
     * @param coluna Coluna inicial do navio
     * @param horizontal true se o navio deve ser posicionado horizontalmente
     * @return true se o navio foi posicionado com sucesso, false caso contrário
     */
    public boolean posicionarNavio(Navio navio, int linha, int coluna, boolean horizontal) {
        if (!posicaoValida(linha, coluna)) {
            return false;
        }

        int tamanho = navio.getTamanho();
        if (horizontal) {
            if (coluna + tamanho > TAMANHO) {
                return false;
            }
            for (int i = 0; i < tamanho; i++) {
                if (posicaoOcupada(linha, coluna + i)) {
                    return false;
                }
            }
            for (int i = 0; i < tamanho; i++) {
                matriz[linha][coluna + i] = 'N';
            }
        } else {
            if (linha + tamanho > TAMANHO) {
                return false;
            }
            for (int i = 0; i < tamanho; i++) {
                if (posicaoOcupada(linha + i, coluna)) {
                    return false;
                }
            }
            for (int i = 0; i < tamanho; i++) {
                matriz[linha + i][coluna] = 'N';
            }
        }

        navio.setPosicao(linha, coluna, horizontal);
        navios.add(navio);
        return true;
    }

    /**
     * Realiza um ataque em uma posição do tabuleiro.
     * 
     * @param linha Linha do ataque
     * @param coluna Coluna do ataque
     * @return true se o ataque acertou um navio, false caso contrário
     */
    public boolean atacar(int linha, int coluna) {
        if (!posicaoValida(linha, coluna)) {
            return false;
        }

        if (matriz[linha][coluna] == 'N') {
            matriz[linha][coluna] = 'X';
            // Registra o tiro no navio correspondente
            for (Navio navio : navios) {
                if (navio.receberTiro(linha, coluna)) {
                    break; // Encontrou o navio, pode parar
                }
            }
            return true;
        } else if (matriz[linha][coluna] == '~') {
            matriz[linha][coluna] = 'O';
        }
        return false;
    }

    /**
     * Verifica se todos os navios foram destruídos.
     * 
     * @return true se todos os navios foram destruídos, false caso contrário
     */
    public boolean todosNaviosDestruidos() {
        if (navios.isEmpty()) {
            return false;
        }
        for (Navio navio : navios) {
            if (!navio.estaDestruido()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Mostra o tabuleiro no console.
     * 
     * @param esconderNavios true para esconder os navios não atingidos, false para mostrar
     */
    public void mostrarTabuleiro(boolean esconderNavios) {
        System.out.print("  ");
        for (int i = 0; i < TAMANHO; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < TAMANHO; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < TAMANHO; j++) {
                char c = matriz[i][j];
                if (esconderNavios && c == 'N') {
                    System.out.print("~ ");
                } else {
                    System.out.print(c + " ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Retorna a matriz do tabuleiro.
     * 
     * @return Matriz do tabuleiro
     */
    public char[][] getMatriz() {
        return matriz;
    }
} 