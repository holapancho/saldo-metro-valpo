package cl.frojas.negocio;

import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.impl.client.BasicResponseHandler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class ConsultaSaldoImpl implements ConsultaSaldo {

    public String consultarSaldo(String tarjeta) throws IOException {

        HttpResponse respuesta = Request.Post("https://www.metrovalparaiso.cl/saldonuevo.php")
                .bodyForm(
                Form.form().add("numerotarjeta", tarjeta)
                        .build())
                .execute().returnResponse();

        String respuestaString = new BasicResponseHandler().handleResponse(respuesta);

        Document document = Jsoup.parse(respuestaString);
        String resultado = document.child(0).child(1).child(0).getElementsByTag("strong").text();
        return resultado;
    }
}
