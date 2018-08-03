package br.com.felipejunges.together.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by desen on 07/11/2017.
 */

public class RetrofitInicializador {

    private final Retrofit retrofit;
    private final String protocolo;
    private final String ip;
    private final String porta;
    private final String caminhoPadrao;
    private final String enderecoCompleto;

    public RetrofitInicializador() {
        this.protocolo = "http://";
        this.ip = "127.0.0.1";
        this.porta = "8080";
        this.caminhoPadrao = "Together/webapi/";
        this.enderecoCompleto = protocolo + ip + ":" + porta + "/" + caminhoPadrao;

        retrofit = new Retrofit.Builder().baseUrl(enderecoCompleto)
                .addConverterFactory(JacksonConverterFactory.create()).build();
    }

}
