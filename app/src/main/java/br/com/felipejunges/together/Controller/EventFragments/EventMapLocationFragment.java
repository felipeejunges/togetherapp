package br.com.felipejunges.together.Controller.EventFragments;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import br.com.felipejunges.together.Model.Event;
import br.com.felipejunges.together.R;

public class EventMapLocationFragment extends SupportMapFragment implements OnMapReadyCallback {
        private GoogleMap mapa;
        Event evento;

        @Override
        public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        evento = new Event();
        getMapAsync(this);
        }

        @Override
        public void onMapReady (GoogleMap googleMap){
        pegaParametros();
        mapa = googleMap;
        LatLng posicao = null;
        if (evento != null && evento.getLocation() != null) {
            posicao = pegaCoodernadaDoEndereco(evento.getLocation());
        }
        if (posicao != null) {
            centralizaEm(posicao);
            marcar(posicao);
        }

        //new Localizador(getContext(), googleMap);
    }

        private void marcar (LatLng posicao){
        MarkerOptions marcador = new MarkerOptions();
        marcador.position(posicao);
        marcador.title(evento.getName());
            marcador.snippet(String.valueOf(evento.getPrimaryCategory()));
        mapa.addMarker(marcador);
    }

        private void pegaParametros () {
        Bundle parametros = getArguments();
        if (parametros != null) {
            evento = (Event) parametros.getSerializable("event");
        }
    }

        private LatLng pegaCoodernadaDoEndereco (String endereco){
        try {
            Geocoder geocoder = new Geocoder(getContext());
            List<Address> resultados = geocoder.getFromLocationName(endereco, 1);
            if (!resultados.isEmpty()) {
                LatLng posicao = new LatLng(resultados.get(0).getLatitude(), resultados.get(0).getLongitude());
                return posicao;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

        public void centralizaEm (LatLng coordenada){
        if (mapa != null) {
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(coordenada, 17);
            mapa.moveCamera(update);
        }
    }
}