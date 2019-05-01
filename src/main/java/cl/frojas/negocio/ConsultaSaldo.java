package cl.frojas.negocio;

import cl.frojas.pojo.RespuestaServicio;

public interface ConsultaSaldo {

    RespuestaServicio consultarSaldo (String tarjeta) throws Exception;

}
