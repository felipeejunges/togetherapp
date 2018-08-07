package br.com.felipejunges.together.Controller.EventRegisterFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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
        txtPrice = rootView.findViewById(R.id.txtEventPrice_Create);
        txtEventMinAge = rootView.findViewById(R.id.txtEventMinAge_Create);
        txtEventMaxAge = rootView.findViewById(R.id.txtEventMaxAge_Create);
        txtEventMinPart = rootView.findViewById(R.id.txtEventMinParticipation_Create);
        txtEventMaxPart = rootView.findViewById(R.id.txtEventMaxParticipation_Create);

        return rootView;
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
