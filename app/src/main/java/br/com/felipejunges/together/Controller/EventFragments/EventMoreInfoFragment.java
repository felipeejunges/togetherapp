package br.com.felipejunges.together.Controller.EventFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import br.com.felipejunges.together.Model.Event;
import br.com.felipejunges.together.R;

public class EventMoreInfoFragment extends Fragment {
    private static final String TAG = "Tab1Fragment";
    View view;

    TextView txtPrice;
    TextView txtMinAge;
    TextView txtMaxAge;
    TextView txtMinPart;
    TextView txtMaxPart;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_event_more_info,container,false);

        carregaCampos(view);

        pegaParametros();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        pegaParametros();
    }

    private void pegaParametros() {
        Bundle parametros = getArguments();
        if(parametros != null) {
            Event event = (Event) parametros.getSerializable("event");
            populaCamposCom(event);
        }
    }

    private void populaCamposCom(Event event) {
        txtPrice.setText(String.valueOf(event.getPrice()));
        txtMinAge.setText(String.valueOf(event.getMinAge()));
        txtMaxAge.setText(String.valueOf(event.getMaxAge()));
        txtMinPart.setText(String.valueOf(event.getMinParticipation()));
        txtMaxPart.setText(String.valueOf(event.getMaxParticipation()));

    }

    private void carregaCampos(View view) {
        txtPrice = view.findViewById(R.id.txtEventPrice);
        txtMinAge =  view.findViewById(R.id.txtEventMinAge);
        txtMaxAge = view.findViewById(R.id.txtEventMaxAge);
        txtMinPart =  view.findViewById(R.id.txtEventMinParticipation);
        txtMaxPart = view.findViewById(R.id.txtEventMaxParticipation);
    }

}
