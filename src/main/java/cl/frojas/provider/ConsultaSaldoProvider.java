package cl.frojas.provider;

import cl.frojas.negocio.ConsultaSaldo;
import cl.frojas.negocio.ConsultaSaldoImpl;

public final class ConsultaSaldoProvider {

    public static ConsultaSaldo traerConsultaSaldo(){
        return new ConsultaSaldoImpl();
    }

}
