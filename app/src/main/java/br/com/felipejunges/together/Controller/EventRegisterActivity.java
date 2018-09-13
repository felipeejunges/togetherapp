package br.com.felipejunges.together.Controller;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import br.com.felipejunges.together.Adapter.CustomViewPager;
import br.com.felipejunges.together.Adapter.ScreenSlidePagerAdapter;
import br.com.felipejunges.together.Controller.EventRegisterFragments.EventRegisterBaseFragment;
import br.com.felipejunges.together.Controller.EventRegisterFragments.EventRegisterCategoriesFragment;
import br.com.felipejunges.together.Controller.EventRegisterFragments.EventRegisterDetailsFragment;
import br.com.felipejunges.together.Controller.EventRegisterFragments.EventRegisterImageFragment;
import br.com.felipejunges.together.Controller.EventRegisterFragments.EventRegisterMapFragment;
import br.com.felipejunges.together.Helper.EventFormHelper;
import br.com.felipejunges.together.Model.Event;
import br.com.felipejunges.together.R;
import br.com.felipejunges.together.Singleton.DataStore;

public class EventRegisterActivity extends AppCompatActivity {

    private CustomViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private List<Fragment> fragments;

    private EditText txtEventName;
    private EditText txtEventAbout;
    private EditText txtPrimaryCategory;
    private EditText txtPrice;
    private EditText txtLocation;
    private EditText txtEventMinAge;
    private EditText txtEventMaxAge;
    private EditText txtEventMinPart;
    private EditText txtEventMaxPart;
    private ImageView ivEventImage;
    private String photoPath;

    private EventRegisterBaseFragment base;
    private EventRegisterDetailsFragment details;
    private EventRegisterImageFragment images;
    private EventRegisterCategoriesFragment categories;
    private EventRegisterMapFragment map;

    private EventFormHelper helper;

    private int position;
    Event oldEvent;

    public static final int CODIGO_CAMERA = 567;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_register);

        helper = new EventFormHelper(this);

        mPager = findViewById(R.id.containerEventRegister);
        fragments = new ArrayList<>();

        base = new EventRegisterBaseFragment();
        details = new EventRegisterDetailsFragment();
        images = new EventRegisterImageFragment();
        categories = new EventRegisterCategoriesFragment();
        map = new EventRegisterMapFragment();

        fragments.add(base);
        fragments.add(details);
        fragments.add(images);
        fragments.add(categories);
        fragments.add(map);

        mPagerAdapter = new ScreenSlidePagerAdapter(this, getSupportFragmentManager(), fragments);
        mPager.setPagingEnabled(false);
        mPager.setAdapter(mPagerAdapter);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Cadastrar Eventos - Together");

        intializeEditText();

        try {

            Intent intent = getIntent();
            oldEvent = (Event) intent.getSerializableExtra("event");
            position = (int) intent.getSerializableExtra("position");

        } catch(Exception e) {
            oldEvent = null;
        }


        if(oldEvent != null) {
            commitEvento(getSupportFragmentManager(), base);
            commitEvento(getSupportFragmentManager(), details);
            commitEvento(getSupportFragmentManager(), images);
            commitEvento(getSupportFragmentManager(), categories);
            commitEvento(getSupportFragmentManager(), map);
        }

    }

    private void commitEvento(FragmentManager manager, Fragment fragmento) {
        FragmentTransaction tx = manager.beginTransaction();
        Bundle parametros = new Bundle();
        parametros.putSerializable("event", oldEvent);
        fragmento.setArguments(parametros);

        tx.commit();
    }

    public void onEventRegisterNextBase(View v) {

        intializeEditText();

        String name = txtEventName.getText().toString();
        String about = txtEventAbout.getText().toString();

        if(name.isEmpty() || about.isEmpty()) {
            Toast.makeText(this, R.string.fill_all_blanks, Toast.LENGTH_SHORT).show();
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() + 1, true);
        }

    }

    public void onEventRegisterSelectImage(View v) {
        intializeEditText();

        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        photoPath = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
        File file = new File(photoPath);
        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        startActivityForResult(intentCamera, CODIGO_CAMERA);

    }


    public void onEventRegisterNextCategories(View v) {

        intializeEditText();

        String category = txtPrimaryCategory.getText().toString();

        if(category.isEmpty()) {
            Toast.makeText(this, R.string.fill_all_blanks, Toast.LENGTH_SHORT).show();
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() + 1, true);
        }
    }

    public void onEventRegisterNextImage(View view) {
        mPager.setCurrentItem(mPager.getCurrentItem() + 1, true);
    }

    public void onEventRegisterNextDetails(View v) {
        intializeEditText();


        String price = txtPrice.getText().toString();
        String minage = txtEventMinAge.getText().toString();
        String maxage = txtEventMaxAge.getText().toString();
        String minpar = txtEventMinPart.getText().toString();
        String maxpar = txtEventMaxPart.getText().toString();

        if(price.isEmpty() || minage.isEmpty() || maxage.isEmpty() || minpar.isEmpty() || maxpar.isEmpty()) {
            Toast.makeText(this, R.string.fill_all_blanks, Toast.LENGTH_SHORT).show();
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() + 1, true);
        }
    }

    public void onEventRegisterSave(View v) {

        intializeEditText();

        String name = txtEventName.getText().toString();
        String about = txtEventAbout.getText().toString();
        double price = Double.parseDouble(txtPrice.getText().toString());
        int minage = Integer.parseInt(txtEventMinAge.getText().toString());
        int maxage = Integer.parseInt(txtEventMaxAge.getText().toString());
        int minpar = Integer.parseInt(txtEventMinPart.getText().toString());
        int maxpar = Integer.parseInt(txtEventMaxPart.getText().toString());
        String category = txtPrimaryCategory.getText().toString();

        String profileImage = String.valueOf(ivEventImage.getTag());

        String location = txtLocation.getText().toString();
        if(location.isEmpty()) {
            Toast.makeText(this, R.string.fill_all_blanks, Toast.LENGTH_SHORT).show();
        } else {
            Event event = new Event(name, about, false, price, minage, maxage,
                    minpar, maxpar, category, location, profileImage);
            if(oldEvent == null) {  DataStore.sharedInstance().addEvent(event); }
            else { DataStore.sharedInstance().editEvent(event, position);  }

            finish();
        }
    }

    private void intializeEditText() {
        txtEventName = base.getTxtEventName();
        txtEventAbout = base.getTxtEventAbout();
        txtPrice = details.getTxtPrice();
        txtEventMinAge = details.getTxtEventMinAge();
        txtEventMaxAge = details.getTxtEventMaxAge();
        txtEventMinPart = details.getTxtEventMinPart();
        txtEventMaxPart = details.getTxtEventMaxPart();
        txtPrimaryCategory = categories.getTxtPrimaryCategory();
        ivEventImage = images.getImageView();
        txtLocation = map.getTxtLocation();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK) {
            if (requestCode == CODIGO_CAMERA) {
                ImageView foto = images.getImageView();
                helper.carregaImagem(photoPath);

            }
        }
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }
}