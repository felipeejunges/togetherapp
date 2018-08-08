package br.com.felipejunges.together.Controller.EventRegisterFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import br.com.felipejunges.together.Model.Event;
import br.com.felipejunges.together.R;

public class EventRegisterDetailsFragment extends Fragment {

    private EditText txtPrice;
    private EditText txtEventMinAge;
    private EditText txtEventMaxAge;
    private EditText txtEventMinPart;
    private EditText txtEventMaxPart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_event_register_details, container, false);


        carregaCampos(rootView);
        pegaParametros();
        return rootView;
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
        txtEventMinAge.setText(String.valueOf(event.getMinAge()));
        txtEventMaxAge.setText(String.valueOf(event.getMaxAge()));
        txtEventMinPart.setText(String.valueOf(event.getMinParticipation()));
        txtEventMaxPart.setText(String.valueOf(event.getMaxParticipation()));
    }

    private void carregaCampos(View view) {
        txtPrice = view.findViewById(R.id.txtEventPrice_Create);
        txtEventMinAge = view.findViewById(R.id.txtEventMinAge_Create);
        txtEventMaxAge = view.findViewById(R.id.txtEventMaxAge_Create);
        txtEventMinPart = view.findViewById(R.id.txtEventMinParticipation_Create);
        txtEventMaxPart = view.findViewById(R.id.txtEventMaxParticipation_Create);
    }

    public EditText getTxtPrice() {
        return txtPrice;
    }

    public EditText getTxtEventMinAge() {
        return txtEventMinAge;
    }

    public EditText getTxtEventMaxAge() {
        return txtEventMaxAge;
    }

    public EditText getTxtEventMinPart() {
        return txtEventMinPart;
    }

    public EditText getTxtEventMaxPart() {
        return txtEventMaxPart;
    }
}
