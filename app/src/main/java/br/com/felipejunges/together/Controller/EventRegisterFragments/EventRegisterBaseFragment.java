package br.com.felipejunges.together.Controller.EventRegisterFragments;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import br.com.felipejunges.together.Model.Event;
import br.com.felipejunges.together.R;

public class EventRegisterBaseFragment extends Fragment {

    private EditText txtEventName;
    private EditText txtEventAbout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_event_register_base, container, false);

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
        txtEventName.setText(event.getName());
        txtEventAbout.setText(event.getDescription());
    }

    private void carregaCampos(View view) {
        txtEventName = view.findViewById(R.id.txtEventName_Create);
        txtEventAbout = view.findViewById(R.id.txtEventAbout_Create);
    }


    public EditText getTxtEventName() {
        return txtEventName;
    }

    public EditText getTxtEventAbout() {
        return txtEventAbout;
    }


}
