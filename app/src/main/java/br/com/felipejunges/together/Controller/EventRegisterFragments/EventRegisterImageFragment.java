package br.com.felipejunges.together.Controller.EventRegisterFragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;

import br.com.felipejunges.together.Helper.EventFormHelper;
import br.com.felipejunges.together.Model.Event;
import br.com.felipejunges.together.R;

public class EventRegisterImageFragment extends Fragment {

    private String photoPath;
    private ImageView imageView;
    private EventFormHelper helper;

    public static final int CODIGO_CAMERA = 567;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_event_register_image, container, false);



        carregaCampos(rootView);
        helper = new EventFormHelper(getImageView());
        pegaParametros();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onEventRegisterSelectImage(view);
            }
        });

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
        imageView = view.findViewById(R.id.imgEventPictureRegister);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void onEventRegisterSelectImage(View v) {

        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        photoPath = getContext().getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
        File file = new File(photoPath);
        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        startActivityForResult(intentCamera, CODIGO_CAMERA);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK) {
            if (requestCode == CODIGO_CAMERA) {
                ImageView foto = getImageView();
                helper.carregaImagem(photoPath);

            }
        }
    }

}
