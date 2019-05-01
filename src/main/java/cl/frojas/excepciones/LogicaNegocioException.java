package cl.frojas.excepciones;

public class LogicaNegocioException extends Exception {
    public LogicaNegocioException(String errorMessage) {
        super(errorMessage);
    }
}
