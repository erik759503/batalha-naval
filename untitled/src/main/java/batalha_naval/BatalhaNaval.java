package batalha_naval;

import java.util.Scanner;

/**
 * Classe principal do jogo Batalha Naval.
 * Gerencia o fluxo do jogo, incluindo a inicialização dos tabuleiros,
 * posicionamento dos navios e as jogadas dos jogadores.
 * 
 * @author ERIK
 * @version 1.0.2
 */
public class BatalhaNaval {
    /** Tabuleiro do jogador humano */
    private Tabuleiro tabuleiroJogador;
    
    /** Tabuleiro do computador */
    private Tabuleiro tabuleiroComputador;
    
    /** Instância do jogador humano */
    private Jogador jogador;
    
    /** Instância do computador */
    private Computador computador;
    
    /** Scanner para entrada do usuário */
    private Scanner scanner;

    /**
     * Construtor da classe BatalhaNaval.
     * Inicializa os tabuleiros, jogadores e o scanner para entrada.
     */
    public BatalhaNaval() {
        tabuleiroJogador = new Tabuleiro();
        tabuleiroComputador = new Tabuleiro();
        scanner = new Scanner(System.in);
        jogador = new Jogador("Jogador", tabuleiroJogador, scanner);
        computador = new Computador(tabuleiroComputador);
    }

    /**
     * Inicia o jogo Batalha Naval.
     * Gerencia o fluxo do jogo, incluindo:
     * - Posicionamento dos navios
     * - Alternância entre jogadas do jogador e computador
     * - Verificação de vitória
     */
    public void iniciarJogo() {
        System.out.println("Bem-vindo à Batalha Naval!");
        System.out.println("Posicione seus navios:");
        
        jogador.posicionarNavios();
        computador.posicionarNavios();

        while (true) {
            // Vez do jogador
            System.out.println("\nSeu tabuleiro:");
            tabuleiroJogador.mostrarTabuleiro(false);
            System.out.println("\nTabuleiro do computador:");
            tabuleiroComputador.mostrarTabuleiro(true);

            jogador.realizarJogada(tabuleiroComputador);
            
            if (jogador.venceu(tabuleiroComputador)) {
                System.out.println("Parabéns! Você venceu!");
                System.out.println("Sua pontuação: " + jogador.getPontuacao());
                System.out.println("Navios destruídos: " + jogador.getNaviosDestruidos());
                break;
            }

            // Vez do computador
            computador.realizarJogada(tabuleiroJogador);
            
            if (computador.venceu(tabuleiroJogador)) {
                System.out.println("O computador venceu!");
                System.out.println("Pontuação do computador: " + computador.getPontuacao());
                System.out.println("Navios destruídos pelo computador: " + computador.getNaviosDestruidos());
                break;
            }
        }
        scanner.close();
    }

    /**
     * Método principal que inicia o jogo.
     * 
     * @param args Argumentos da linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        BatalhaNaval jogo = new BatalhaNaval();
        jogo.iniciarJogo();
    }

    public Tabuleiro getTabuleiroJogador() {
        return tabuleiroJogador;
    }

    public Tabuleiro getTabuleiroComputador() {
        return tabuleiroComputador;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public Computador getComputador() {
        return computador;
    }
} 