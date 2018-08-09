package br.com.felipejunges.together.Controller.EventFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import br.com.felipejunges.together.Model.Event;
import br.com.felipejunges.together.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventBaseInfoFragment extends Fragment {
    private static final String TAG = "Tab1Fragment";
    View view;


    TextView txtLocation;
    TextView txtPrimaryCategory;
    TextView txtAbout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_event_base_info,container,false);

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
        txtLocation.setText(event.getLocation());
        txtPrimaryCategory.setText(event.getPrimaryCategory());
        txtAbout.setText(event.getDescription());

    }

    private void carregaCampos(View view) {
        txtLocation = view.findViewById(R.id.txtEventWhereIs);
        txtPrimaryCategory =  view.findViewById(R.id.txtEventPrimaryCategory);
        txtAbout = view.findViewById(R.id.txtEventAbout);
    }

}
