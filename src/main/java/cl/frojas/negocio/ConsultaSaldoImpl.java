package cl.frojas.negocio;

import cl.frojas.excepciones.LogicaNegocioException;
import cl.frojas.pojo.RespuestaServicio;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.impl.client.BasicResponseHandler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static cl.frojas.constantes.Constantes.*;

public class ConsultaSaldoImpl implements ConsultaSaldo {

    private final static Logger log = LoggerFactory.getLogger(ConsultaSaldoImpl.class);

    public RespuestaServicio consultarSaldo(String tarjeta) throws IOException,LogicaNegocioException {
        if(tarjeta == null || tarjeta.length() != LARGO_MAXIMO_NUMERO_TARJETA){
            throw new LogicaNegocioException("numero invalido");
        }

        log.info("Numero tarjeta "+ tarjeta);

        HttpResponse respuesta = Request.Post(URL_SERVICIO)
                .bodyForm(
                Form.form().add(PARAM_NUMEROTARJETA, tarjeta)
                        .build())
                .execute().returnResponse();

        if(respuesta.getStatusLine().getStatusCode()!= HttpStatus.SC_OK){
            throw new LogicaNegocioException("error en la respuesta del servidor");
        }

        String respuestaString = new BasicResponseHandler().handleResponse(respuesta);

        if(respuestaString.contains(NO_EXISTEN_TRANSACCIONES)){
            return new RespuestaServicio(NO_EXISTEN_TRANSACCIONES);
        }


        Document document = Jsoup.parse(respuestaString);
        String monto = document.child(0).child(1).child(0).getElementsByTag("strong").text();
        return new RespuestaServicio(monto);
    }
}
