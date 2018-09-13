package br.com.felipejunges.together.Helper;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import br.com.felipejunges.together.R;

public class EventFormHelper {


    private ImageView imageView;

    public EventFormHelper(Activity activity) {
        this.imageView = activity.findViewById(R.id.imgEventPicture);
    }

    public void carregaImagem(String caminhoFoto) {
        if(caminhoFoto!= null) {

            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
            imageView.setImageBitmap(bitmapReduzido);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setTag(caminhoFoto);
        }
    }
}
