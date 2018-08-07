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

public class EventRegisterMapFragment extends Fragment {
    private EditText txtLocation;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_event_register_map, container, false);
        txtLocation = rootView.findViewById(R.id.txtEventLocation_Create);
        return rootView;
    }

    public EditText getTxtLocation() {
        return txtLocation;
    }
}