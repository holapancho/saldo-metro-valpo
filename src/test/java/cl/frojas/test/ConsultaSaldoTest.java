package cl.frojas.test;

import cl.frojas.constantes.Constantes;
import cl.frojas.excepciones.LogicaNegocioException;
import cl.frojas.negocio.ConsultaSaldo;
import cl.frojas.provider.ConsultaSaldoProvider;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConsultaSaldoTest {
    private static ConsultaSaldo consultaSaldo;

    @BeforeClass
    public static void init() {
        consultaSaldo = ConsultaSaldoProvider.traerConsultaSaldo();
    }

    @Test
    public void probarMiTarjetaOK() throws Exception {
        assertEquals(consultaSaldo.consultarSaldo("36087067817689860").getRespuesta(), "$272");
    }

    @Test
    public void probarMiTarjetaAnual() throws Exception {
        assertEquals(consultaSaldo.consultarSaldo("36127663984830212").getRespuesta(), "$0");
    }

    @Test
    public void probarTarjetaNoExiste() throws Exception {
        assertEquals(consultaSaldo.consultarSaldo("36087067817689861").getRespuesta(), Constantes.NO_EXISTEN_TRANSACCIONES);
    }


    @Test(expected = LogicaNegocioException.class)
    public void probarErrorNumeroInvalido() throws Exception {
        consultaSaldo.consultarSaldo("123");
    }
}
