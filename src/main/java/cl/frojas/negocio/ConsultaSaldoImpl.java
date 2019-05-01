package cl.frojas.negocio;

import cl.frojas.excepciones.LogicaNegocioException;
import cl.frojas.pojo.RespuestaServicio;
import cl.frojas.pojo.Viaje;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.impl.client.BasicResponseHandler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

        List<Viaje> viajes = null;
        Elements elementsViajes = document.child(0).child(1).getElementsByTag("ul");
        if(!((Elements) elementsViajes).isEmpty()){
            viajes = new ArrayList<>();
            for(Element viajeElement: elementsViajes){
                Viaje viaje = new Viaje(
                        viajeElement.child(1).getElementsByTag("strong").text(),
                        viajeElement.child(2).getElementsByTag("strong").text(),
                        viajeElement.child(0).getElementsByTag("strong").text()
                );
                viajes.add(viaje);
            }
        }

        return new RespuestaServicio(monto,viajes);
    }
}
