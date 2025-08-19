package batalha_naval;

/**
 * Classe base abstrata que representa um participante no jogo Batalha Naval.
 * Define funcionalidades comuns entre jogador humano e computador.
 * 
 * @author ERIK
 * @version 1.0.2
 */
public abstract class Participante {
    
    /** Nome do participante */
    protected String nome;
    
    /** Tabuleiro do participante */
    protected Tabuleiro tabuleiro;
    
    /** Pontuação do participante */
    protected int pontuacao;
    
    /** Número de navios destruídos */
    protected int naviosDestruidos;

    /**
     * Construtor da classe Participante.
     * 
     * @param nome Nome do participante
     * @param tabuleiro Tabuleiro do participante
     */
    public Participante(String nome, Tabuleiro tabuleiro) {
        this.nome = nome;
        this.tabuleiro = tabuleiro;
        this.pontuacao = 0;
        this.naviosDestruidos = 0;
    }

    /**
     * Método abstrato para posicionar navios no tabuleiro.
     * Deve ser implementado pelas classes filhas.
     */
    public abstract void posicionarNavios();

    /**
     * Método abstrato para realizar uma jogada no tabuleiro do oponente.
     * Deve ser implementado pelas classes filhas.
     * 
     * @param tabuleiroOponente Tabuleiro do oponente
     * @return true se acertou um navio, false caso contrário
     */
    public abstract boolean realizarJogada(Tabuleiro tabuleiroOponente);

    /**
     * Verifica se o participante venceu (todos os navios do oponente destruídos).
     * 
     * @param tabuleiroOponente Tabuleiro do oponente
     * @return true se venceu, false caso contrário
     */
    public boolean venceu(Tabuleiro tabuleiroOponente) {
        return tabuleiroOponente.todosNaviosDestruidos();
    }

    /**
     * Verifica se o participante perdeu (todos os seus navios foram destruídos).
     * 
     * @return true se perdeu, false caso contrário
     */
    public boolean perdeu() {
        return tabuleiro.todosNaviosDestruidos();
    }

    /**
     * Incrementa a pontuação do participante.
     * 
     * @param pontos Pontos a serem adicionados
     */
    public void adicionarPontos(int pontos) {
        this.pontuacao += pontos;
    }

    /**
     * Incrementa o contador de navios destruídos.
     */
    public void navioDestruido() {
        this.naviosDestruidos++;
    }

    /**
     * Retorna o nome do participante.
     * 
     * @return Nome do participante
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna o tabuleiro do participante.
     * 
     * @return Tabuleiro do participante
     */
    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    /**
     * Retorna a pontuação do participante.
     * 
     * @return Pontuação do participante
     */
    public int getPontuacao() {
        return pontuacao;
    }

    /**
     * Retorna o número de navios destruídos pelo participante.
     * 
     * @return Número de navios destruídos
     */
    public int getNaviosDestruidos() {
        return naviosDestruidos;
    }

    /**
     * Retorna uma representação em string do participante.
     * 
     * @return String representando o participante
     */
    @Override
    public String toString() {
        return "Participante{" +
                "nome='" + nome + '\'' +
                ", pontuacao=" + pontuacao +
                ", naviosDestruidos=" + naviosDestruidos +
                '}';
    }

    /**
     * Método para obter estatísticas do participante.
     * 
     * @return String com as estatísticas
     */
    public String getEstatisticas() {
        return String.format("Nome: %s | Pontuação: %d | Navios Destruídos: %d", 
                           nome, pontuacao, naviosDestruidos);
    }

    /**
     * Reseta as estatísticas do participante.
     */
    public void resetarEstatisticas() {
        this.pontuacao = 0;
        this.naviosDestruidos = 0;
    }
} 