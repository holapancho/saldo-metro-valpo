package cl.frojas.pojo;

public class Viaje {
    private String operacion;
    private String estacion;
    private String fecha;

    public Viaje(String operacion, String estacion, String fecha) {
        this.operacion = operacion;
        this.estacion = estacion;
        this.fecha = fecha;
    }

    public String getOperacion() {
        return operacion;
    }

    public String getEstacion() {
        return estacion;
    }

    public String getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return "Viaje{" +
                "operacion='" + operacion + '\'' +
                ", estacion='" + estacion + '\'' +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}
