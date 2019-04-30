import cl.frojas.negocio.ConsultaSaldo;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import provider.ConsultaSaldoProvider;

import static org.junit.Assert.assertEquals;

public class ConsultaSaldoTest {
    private static ConsultaSaldo consultaSaldo;

    @BeforeClass
    public static void init() {
        consultaSaldo = ConsultaSaldoProvider.traerConsultaSaldo();
    }

    @Before
    public void antesDelTest() {
        System.out.println("Iniciamos la prueba");
    }

    @After
    public void despuesDelTest() {
        System.out.println("terminamos la prueba");
    }

    @Test
    public void probarMiTarjeta() throws Exception {
        assertEquals(consultaSaldo.consultarSaldo("36087067817689860"), "$272");
    }
}
