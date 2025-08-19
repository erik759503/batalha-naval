package batalha_naval;

/**
 * Classe que representa um navio no jogo Batalha Naval.
 * Gerencia o estado do navio, como posição, orientação e partes atingidas.
 * 
 * @author ERIK
 * @version 1.0.2
 */
public class Navio {
    /** Tamanho do navio */
    private int tamanho;
    
    /** Linha inicial do navio */
    private int linha;
    
    /** Coluna inicial do navio */
    private int coluna;
    
    /** Orientação do navio (true para horizontal, false para vertical) */
    private boolean horizontal;
    
    /** Array que indica quais partes do navio foram atingidas */
    private boolean[] partesAtingidas;

    /**
     * Construtor da classe Navio.
     * 
     * @param tamanho Tamanho do navio
     */
    public Navio(int tamanho) {
        this.tamanho = tamanho;
        this.partesAtingidas = new boolean[tamanho];
        this.linha = -1;
        this.coluna = -1;
    }

    /**
     * Define a posição e orientação do navio.
     * 
     * @param linha Linha inicial do navio
     * @param coluna Coluna inicial do navio
     * @param horizontal Orientação do navio (true para horizontal, false para vertical)
     */
    public void setPosicao(int linha, int coluna, boolean horizontal) {
        this.linha = linha;
        this.coluna = coluna;
        this.horizontal = horizontal;
    }

    /**
     * Verifica se uma posição específica do navio foi atingida.
     * 
     * @param linha Linha da posição
     * @param coluna Coluna da posição
     * @return true se a posição foi atingida, false caso contrário
     */
    public boolean posicaoAtingida(int linha, int coluna) {
        if (horizontal) {
            if (linha != this.linha || coluna < this.coluna || coluna >= this.coluna + tamanho) {
                return false;
            }
            return partesAtingidas[coluna - this.coluna];
        } else {
            if (coluna != this.coluna || linha < this.linha || linha >= this.linha + tamanho) {
                return false;
            }
            return partesAtingidas[linha - this.linha];
        }
    }

    /**
     * Registra um tiro em uma posição específica do navio.
     * 
     * @param linha Linha do tiro
     * @param coluna Coluna do tiro
     * @return true se o tiro acertou o navio, false caso contrário
     */
    public boolean receberTiro(int linha, int coluna) {
        if (horizontal) {
            if (linha != this.linha || coluna < this.coluna || coluna >= this.coluna + tamanho) {
                return false;
            }
            partesAtingidas[coluna - this.coluna] = true;
        } else {
            if (coluna != this.coluna || linha < this.linha || linha >= this.linha + tamanho) {
                return false;
            }
            partesAtingidas[linha - this.linha] = true;
        }
        return true;
    }

    /**
     * Verifica se o navio foi completamente destruído.
     * 
     * @return true se todas as partes do navio foram atingidas, false caso contrário
     */
    public boolean estaDestruido() {
        for (boolean parte : partesAtingidas) {
            if (!parte) {
                return false;
            }
        }
        return true;
    }

    /**
     * Retorna o tamanho do navio.
     * 
     * @return Tamanho do navio
     */
    public int getTamanho() {
        return tamanho;
    }

    /**
     * Retorna a linha inicial do navio.
     * 
     * @return Linha inicial do navio
     */
    public int getLinha() {
        return linha;
    }

    /**
     * Retorna a coluna inicial do navio.
     * 
     * @return Coluna inicial do navio
     */
    public int getColuna() {
        return coluna;
    }

    /**
     * Retorna a orientação do navio.
     * 
     * @return true se o navio está na horizontal, false se está na vertical
     */
    public boolean isHorizontal() {
        return horizontal;
    }
} 