package batalha_naval;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TelaFinalTest {
    @Test
    public void testConstrutorVitoria() {
        assertDoesNotThrow(() -> {
            TelaFinal tela = new TelaFinal(true);
            assertNotNull(tela);
        });
    }

    @Test
    public void testConstrutorDerrota() {
        assertDoesNotThrow(() -> {
            TelaFinal tela = new TelaFinal(false);
            assertNotNull(tela);
        });
    }
} 