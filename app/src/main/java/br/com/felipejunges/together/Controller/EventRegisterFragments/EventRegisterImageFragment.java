package br.com.felipejunges.together.Controller.EventRegisterFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import br.com.felipejunges.together.Helper.EventFormHelper;
import br.com.felipejunges.together.Model.Event;
import br.com.felipejunges.together.R;

public class EventRegisterImageFragment extends Fragment {

    private String photoPath;
    private ImageView imageView;
    private EventFormHelper helper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_event_register_image, container, false);

        helper = new EventFormHelper(getActivity());

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
       helper.carregaImagem(event.getProfileImage());
    }

    private void carregaCampos(View view) {
        imageView = view.findViewById(R.id.txtEventPrice_Create);
    }

    public ImageView getImageView() {
        return imageView;
    }


}
