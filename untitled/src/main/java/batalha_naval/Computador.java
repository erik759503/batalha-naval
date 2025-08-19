package batalha_naval;

import java.util.Random;

/**
 * Classe que representa o computador no jogo Batalha Naval.
 * Herda de Participante e implementa funcionalidades específicas do computador.
 * 
 * @author ERIK
 * @version 1.0.2
 */
public class Computador extends Participante {
    
    /** Gerador de números aleatórios */
    private Random random;
    
    /** Estratégia de ataque do computador */
    private EstrategiaAtaque estrategia;

    /**
     * Construtor da classe Computador.
     * 
     * @param tabuleiro Tabuleiro do computador
     */
    public Computador(Tabuleiro tabuleiro) {
        super("Computador", tabuleiro);
        this.random = new Random();
        this.estrategia = new EstrategiaAleatoria();
    }

    /**
     * Construtor da classe Computador com estratégia personalizada.
     * 
     * @param tabuleiro Tabuleiro do computador
     * @param estrategia Estratégia de ataque do computador
     */
    public Computador(Tabuleiro tabuleiro, EstrategiaAtaque estrategia) {
        super("Computador", tabuleiro);
        this.random = new Random();
        this.estrategia = estrategia;
    }

    /**
     * Implementação do método abstrato para posicionar navios.
     * O computador posiciona os navios aleatoriamente.
     */
    @Override
    public void posicionarNavios() {
        int[] tamanhos = {5, 4, 3, 3, 2};
        String[] nomes = {"Porta-aviões", "Encouraçado", "Cruzador", "Submarino", "Destruidor"};

        for (int i = 0; i < tamanhos.length; i++) {
            boolean posicionado = false;
            while (!posicionado) {
                int linha = random.nextInt(Tabuleiro.TAMANHO);
                int coluna = random.nextInt(Tabuleiro.TAMANHO);
                boolean horizontal = random.nextBoolean();

                Navio navio = new Navio(tamanhos[i]);
                posicionado = tabuleiro.posicionarNavio(navio, linha, coluna, horizontal);
            }
        }
    }

    /**
     * Implementação do método abstrato para realizar uma jogada.
     * O computador escolhe a posição usando sua estratégia de ataque.
     * 
     * @param tabuleiroOponente Tabuleiro do oponente
     * @return true se acertou um navio, false caso contrário
     */
    @Override
    public boolean realizarJogada(Tabuleiro tabuleiroOponente) {
        int[] posicao = estrategia.escolherPosicao(tabuleiroOponente);
        int linha = posicao[0];
        int coluna = posicao[1];
        
        boolean acertou = tabuleiroOponente.atacar(linha, coluna);
        if (acertou) {
            System.out.println("O computador acertou um navio!");
            adicionarPontos(10);
            
            // Verifica se destruiu um navio
            if (verificarNavioDestruido(tabuleiroOponente, linha, coluna)) {
                navioDestruido();
                System.out.println("O computador destruiu um navio! +50 pontos");
                adicionarPontos(50);
            }
        } else {
            System.out.println("O computador atingiu a água!");
        }
        
        return acertou;
    }

    /**
     * Método para realizar jogada com coordenadas específicas.
     * 
     * @param tabuleiroOponente Tabuleiro do oponente
     * @param linha Linha do ataque
     * @param coluna Coluna do ataque
     * @return true se acertou um navio, false caso contrário
     */
    public boolean realizarJogada(Tabuleiro tabuleiroOponente, int linha, int coluna) {
        if (linha < 0 || linha >= Tabuleiro.TAMANHO || coluna < 0 || coluna >= Tabuleiro.TAMANHO) {
            return false;
        }
        
        // Verifica se a posição já foi atacada
        char posicaoAtual = tabuleiroOponente.getMatriz()[linha][coluna];
        if (posicaoAtual == 'X' || posicaoAtual == 'O') {
            return false;
        }
        
        boolean acertou = tabuleiroOponente.atacar(linha, coluna);
        if (acertou) {
            adicionarPontos(10);
            
            // Verifica se destruiu um navio
            if (verificarNavioDestruido(tabuleiroOponente, linha, coluna)) {
                navioDestruido();
                adicionarPontos(50);
            }
        }
        
        return acertou;
    }

    /**
     * Verifica se um navio foi destruído após o ataque.
     * 
     * @param tabuleiroOponente Tabuleiro do oponente
     * @param linha Linha do ataque
     * @param coluna Coluna do ataque
     * @return true se um navio foi destruído, false caso contrário
     */
    private boolean verificarNavioDestruido(Tabuleiro tabuleiroOponente, int linha, int coluna) {
        // Verifica se todos os navios no tabuleiro foram destruídos
        return tabuleiroOponente.todosNaviosDestruidos();
    }

    /**
     * Retorna a estratégia de ataque do computador.
     * 
     * @return Estratégia de ataque
     */
    public EstrategiaAtaque getEstrategia() {
        return estrategia;
    }

    /**
     * Define a estratégia de ataque do computador.
     * 
     * @param estrategia Nova estratégia de ataque
     */
    public void setEstrategia(EstrategiaAtaque estrategia) {
        this.estrategia = estrategia;
    }

    /**
     * Método para obter estatísticas específicas do computador.
     * 
     * @return String com estatísticas do computador
     */
    @Override
    public String getEstatisticas() {
        return String.format("Computador: %s | Pontuação: %d | Navios Destruídos: %d | Estratégia: %s", 
                           nome, pontuacao, naviosDestruidos, estrategia.getClass().getSimpleName());
    }

    /**
     * Interface para estratégias de ataque do computador.
     */
    public interface EstrategiaAtaque {
        /**
         * Escolhe uma posição para atacar.
         * 
         * @param tabuleiroOponente Tabuleiro do oponente
         * @return Array com [linha, coluna] da posição escolhida
         */
        int[] escolherPosicao(Tabuleiro tabuleiroOponente);
    }

    /**
     * Estratégia de ataque aleatório.
     */
    public static class EstrategiaAleatoria implements EstrategiaAtaque {
        private Random random = new Random();
        
        @Override
        public int[] escolherPosicao(Tabuleiro tabuleiroOponente) {
            int linha, coluna;
            do {
                linha = random.nextInt(Tabuleiro.TAMANHO);
                coluna = random.nextInt(Tabuleiro.TAMANHO);
            } while (posicaoJaAtacada(tabuleiroOponente, linha, coluna));
            
            return new int[]{linha, coluna};
        }
        
        private boolean posicaoJaAtacada(Tabuleiro tabuleiro, int linha, int coluna) {
            char posicao = tabuleiro.getMatriz()[linha][coluna];
            return posicao == 'X' || posicao == 'O';
        }
    }

    /**
     * Estratégia de ataque inteligente (futura implementação).
     */
    public static class EstrategiaInteligente implements EstrategiaAtaque {
        @Override
        public int[] escolherPosicao(Tabuleiro tabuleiroOponente) {
            // Implementação futura de estratégia inteligente
            // Por enquanto, usa estratégia aleatória
            return new EstrategiaAleatoria().escolherPosicao(tabuleiroOponente);
        }
    }
} 