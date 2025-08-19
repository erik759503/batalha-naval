package batalha_naval;

import java.util.Scanner;

/**
 * Classe que representa o jogador humano no jogo Batalha Naval.
 * Herda de Participante e implementa funcionalidades específicas do jogador humano.
 * 
 * @author ERIK
 * @version 1.0.2
 */
public class Jogador extends Participante {
    
    /** Scanner para entrada do usuário */
    private Scanner scanner;

    /**
     * Construtor da classe Jogador.
     * 
     * @param nome Nome do jogador
     * @param tabuleiro Tabuleiro do jogador
     */
    public Jogador(String nome, Tabuleiro tabuleiro) {
        super(nome, tabuleiro);
        this.scanner = new Scanner(System.in);
    }

    /**
     * Construtor da classe Jogador com scanner personalizado.
     * 
     * @param nome Nome do jogador
     * @param tabuleiro Tabuleiro do jogador
     * @param scanner Scanner para entrada do usuário
     */
    public Jogador(String nome, Tabuleiro tabuleiro, Scanner scanner) {
        super(nome, tabuleiro);
        this.scanner = scanner;
    }

    /**
     * Implementação do método abstrato para posicionar navios.
     * O jogador humano posiciona os navios através de entrada do console.
     */
    @Override
    public void posicionarNavios() {
        int[] tamanhos = {5, 4, 3, 3, 2};
        String[] nomes = {"Porta-aviões", "Encouraçado", "Cruzador", "Submarino", "Destruidor"};

        for (int i = 0; i < tamanhos.length; i++) {
            boolean posicionado = false;
            while (!posicionado) {
                System.out.println("\nPosicione seu " + nomes[i] + " (tamanho " + tamanhos[i] + ")");
                System.out.println("Digite a linha (0-9):");
                int linha = scanner.nextInt();
                System.out.println("Digite a coluna (0-9):");
                int coluna = scanner.nextInt();
                System.out.println("Digite a orientação (H para horizontal, V para vertical):");
                char orientacao = scanner.next().toUpperCase().charAt(0);

                Navio navio = new Navio(tamanhos[i]);
                posicionado = tabuleiro.posicionarNavio(navio, linha, coluna, orientacao == 'H');
                if (!posicionado) {
                    System.out.println("Posição inválida! Tente novamente.");
                }
            }
            tabuleiro.mostrarTabuleiro(false);
        }
    }

    /**
     * Implementação do método abstrato para realizar uma jogada.
     * O jogador humano escolhe a posição através de entrada do console.
     * 
     * @param tabuleiroOponente Tabuleiro do oponente
     * @return true se acertou um navio, false caso contrário
     */
    @Override
    public boolean realizarJogada(Tabuleiro tabuleiroOponente) {
        boolean jogadaValida = false;
        int linha = 0, coluna = 0;
        
        while (!jogadaValida) {
            System.out.println("\nSua vez de atacar!");
            System.out.println("Digite a linha (0-9):");
            linha = scanner.nextInt();
            System.out.println("Digite a coluna (0-9):");
            coluna = scanner.nextInt();

            if (linha >= 0 && linha < Tabuleiro.TAMANHO && coluna >= 0 && coluna < Tabuleiro.TAMANHO) {
                // Verifica se a posição já foi atacada
                char posicaoAtual = tabuleiroOponente.getMatriz()[linha][coluna];
                if (posicaoAtual == 'X' || posicaoAtual == 'O') {
                    System.out.println("Você já atirou nesta posição! Escolha outra.");
                    continue;
                }
                jogadaValida = true;
            } else {
                System.out.println("Posição inválida! Tente novamente.");
            }
        }
        
        boolean acertou = tabuleiroOponente.atacar(linha, coluna);
        if (acertou) {
            System.out.println("Você acertou um navio!");
            adicionarPontos(10);
            
            // Verifica se destruiu um navio
            if (verificarNavioDestruido(tabuleiroOponente, linha, coluna)) {
                navioDestruido();
                System.out.println("Navio destruído! +50 pontos");
                adicionarPontos(50);
            }
        } else {
            System.out.println("Você atingiu a água!");
        }
        
        return acertou;
    }

    /**
     * Método para realizar jogada com coordenadas específicas (usado pela interface gráfica).
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
        // Esta é uma implementação simplificada
        // Em uma versão mais completa, você verificaria se todas as partes do navio foram atingidas
        return false; // Placeholder
    }

    /**
     * Retorna o scanner usado pelo jogador.
     * 
     * @return Scanner do jogador
     */
    public Scanner getScanner() {
        return scanner;
    }

    /**
     * Define o scanner usado pelo jogador.
     * 
     * @param scanner Novo scanner
     */
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Método para obter estatísticas específicas do jogador.
     * 
     * @return String com estatísticas do jogador
     */
    @Override
    public String getEstatisticas() {
        return String.format("Jogador: %s | Pontuação: %d | Navios Destruídos: %d", 
                           nome, pontuacao, naviosDestruidos);
    }
} 