package cl.frojas.pojo;

import java.util.List;

import static cl.frojas.constantes.Constantes.NO_EXISTEN_TRANSACCIONES;

public class RespuestaServicio {

    private String respuesta;
    private List<Viaje> viajesAnteriores;

    public RespuestaServicio(String respuesta){
        this.respuesta = respuesta;
    }

    public RespuestaServicio(String respuesta,List<Viaje> viajesAnteriores){
        this.respuesta = respuesta;this.viajesAnteriores = viajesAnteriores;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public Boolean isPoseeMonto(){
        return respuesta != null && !respuesta.equals(NO_EXISTEN_TRANSACCIONES);
    }

    public List<Viaje> getViajesAnteriores() {
        return viajesAnteriores;
    }

    @Override
    public String toString() {
        return "RespuestaServicio{" +
                "respuesta='" + respuesta + '\'' +
                '}';
    }
}
