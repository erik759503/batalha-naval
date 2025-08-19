# Batalha Naval

Jogo Batalha Naval implementado em Java com interface gráfica Swing.

## Requisitos

- Java 11 ou superior
- Apache Maven 3.6 ou superior

## Como Executar

### Opção 1: Execução Direta (Recomendado)
1. Execute o arquivo `jogar.bat` com duplo clique
2. O script irá compilar e executar o jogo automaticamente

### Opção 2: Criar Executável
1. Execute o arquivo `criar-executavel.bat`
2. Isso irá gerar um JAR executável em `target/batalha-naval-1.0.2.jar`
3. Para executar posteriormente, use: `java -jar target/batalha-naval-1.0.2.jar`

### Opção 3: Execução via Maven
1. Abra o terminal na pasta do projeto
2. Execute: `mvn exec:java`

### Opção 4: Compilação Manual
1. Abra o terminal na pasta do projeto
2. Execute: `mvn clean package`
3. Execute: `java -jar target/batalha-naval-1.0.2.jar`

## Como Jogar

1. **Início**: Clique em "Iniciar Jogo" na tela inicial
2. **Posicionamento**: Posicione seus navios no tabuleiro
   - Clique em uma posição para posicionar o navio
   - Use "Girar Navio" para mudar a orientação
   - Navios não podem se sobrepor
3. **Batalha**: Clique no tabuleiro do computador para atirar
   - Acertos permitem jogar novamente
   - Erros passam a vez para o computador
4. **Vitória**: Destrua todos os navios do oponente primeiro

## Arquivos de Execução

- `jogar.bat` - Executa o jogo diretamente (recomendado)
- `criar-executavel.bat` - Cria o JAR executável
- `executar-jogo.bat` - Execução via Maven

## Estrutura do Projeto

```
src/main/java/batalha_naval/
├── TelaInicial.java      # Tela inicial do jogo
├── TelaPosicionamento.java # Posicionamento dos navios
├── CampoBatalha.java     # Lógica da batalha
├── Tabuleiro.java        # Representação do tabuleiro
├── Navio.java           # Classe dos navios
├── Jogador.java         # Lógica do jogador humano
├── Computador.java      # IA do computador
└── ...
```

## Desenvolvimento

Para executar os testes:
```bash
mvn test
```

Para gerar relatório de cobertura:
```bash
mvn jacoco:report
```

## Autor

ERIK - Versão 1.0.2 