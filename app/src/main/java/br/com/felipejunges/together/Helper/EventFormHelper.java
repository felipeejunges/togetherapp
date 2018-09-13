package br.com.felipejunges.together.Helper;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.widget.ImageView;

import java.io.File;

import br.com.felipejunges.together.Controller.EventRegisterActivity;
import br.com.felipejunges.together.Controller.EventRegisterFragments.EventRegisterBaseFragment;
import br.com.felipejunges.together.Controller.EventRegisterFragments.EventRegisterCategoriesFragment;
import br.com.felipejunges.together.Controller.EventRegisterFragments.EventRegisterDetailsFragment;
import br.com.felipejunges.together.Controller.EventRegisterFragments.EventRegisterImageFragment;
import br.com.felipejunges.together.Controller.EventRegisterFragments.EventRegisterMapFragment;
import br.com.felipejunges.together.R;

public class EventFormHelper {


    private ImageView imageView;

    public EventFormHelper(ImageView imageView) {
        this.imageView = imageView;
    }

    public float getCameraPhotoOrientation(String imagePath){
        float rotate = 0;
        try {
            File imageFile = new File(imagePath);

            ExifInterface exif = new ExifInterface(imageFile.getAbsolutePath());
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate = 270;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate = 90;
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rotate;
    }

    public void carregaImagem(String caminhoFoto) {
        if(caminhoFoto!= null) {

            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
            //  Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
            //imageView.setImageBitmap(bitmapReduzido);
            //imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            float rotate = getCameraPhotoOrientation(caminhoFoto);
            Bitmap rotatedBitmap =  rotateImage(bitmap, rotate);
            imageView.setImageBitmap(rotatedBitmap);
            imageView.setTag(caminhoFoto);
        }
    }
    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }
}
