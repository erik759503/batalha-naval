package batalha_naval;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.*;

public class TelaInicialTest {
    @Test
    public void testConstrutor() {
        TelaInicial tela = new TelaInicial();
        assertNotNull(tela);
        assertTrue(tela instanceof JFrame);
    }
} 