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

public class EventRegisterCategoriesFragment extends Fragment {

    private EditText txtPrimaryCategory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_event_register_categories, container, false);
       // txtPrimaryCategory = rootView.findViewById(R.id.txtPrimaryCategory_Create);
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
        txtPrimaryCategory.setText(event.getPrimaryCategory());
    }

    private void carregaCampos(View view) {
        txtPrimaryCategory = view.findViewById(R.id.txtPrimaryCategory_Create);
    }

    public EditText getTxtPrimaryCategory() {
        return txtPrimaryCategory;
    }
}
