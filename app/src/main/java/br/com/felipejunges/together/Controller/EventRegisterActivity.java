package br.com.felipejunges.together.Controller;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.felipejunges.together.Adapter.CustomViewPager;
import br.com.felipejunges.together.Adapter.ScreenSlidePagerAdapter;
import br.com.felipejunges.together.Controller.EventRegisterFragments.EventRegisterBaseFragment;
import br.com.felipejunges.together.Controller.EventRegisterFragments.EventRegisterCategoriesFragment;
import br.com.felipejunges.together.Controller.EventRegisterFragments.EventRegisterDetailsFragment;
import br.com.felipejunges.together.Controller.EventRegisterFragments.EventRegisterMapFragment;
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

    private EventRegisterBaseFragment base;
    private EventRegisterDetailsFragment details;
    private EventRegisterCategoriesFragment categories;
    private EventRegisterMapFragment map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_register);

        mPager = findViewById(R.id.containerEventRegister);
        fragments = new ArrayList<>();

        base = new EventRegisterBaseFragment();
        details = new EventRegisterDetailsFragment();
        categories = new EventRegisterCategoriesFragment();
        map = new EventRegisterMapFragment();

        fragments.add(base);
        fragments.add(details);
        fragments.add(categories);
        fragments.add(map);
        mPagerAdapter = new ScreenSlidePagerAdapter(this, getSupportFragmentManager(), fragments);
        mPager.setPagingEnabled(false);
        mPager.setAdapter(mPagerAdapter);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Cadastrar Eventos");

    }

    public void onEventRegisterNextBase(View v) {

        txtEventName = base.getTxtEventName();
        txtEventAbout = base.getTxtEventAbout();

        String name = txtEventName.getText().toString();
        String about = txtEventAbout.getText().toString();

        if(name.isEmpty() || about.isEmpty()) {
            Toast.makeText(this, R.string.fill_all_blanks, Toast.LENGTH_SHORT).show();
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() + 1, true);
        }


    }

    public void onEventRegisterNextCategories(View v) {

        txtPrimaryCategory = categories.getTxtPrimaryCategory();

        String category = txtPrimaryCategory.getText().toString();

        if(category.isEmpty()) {
            Toast.makeText(this, R.string.fill_all_blanks, Toast.LENGTH_SHORT).show();
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() + 1, true);
        }
    }

    public void onEventRegisterNextDetails(View v) {
        txtEventName = base.getTxtEventName();
        txtEventAbout = base.getTxtEventAbout();

        txtPrice = details.getTxtPrice();
        txtEventMinAge = details.getTxtEventMinAge();
        txtEventMaxAge = details.getTxtEventMaxAge();
        txtEventMinPart = details.getTxtEventMinPart();
        txtEventMaxPart = details.getTxtEventMaxPart();

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

        txtEventName = base.getTxtEventName();
        txtEventAbout = base.getTxtEventAbout();
        txtPrice = details.getTxtPrice();
        txtEventMinAge = details.getTxtEventMinAge();
        txtEventMaxAge = details.getTxtEventMaxAge();
        txtEventMinPart = details.getTxtEventMinPart();
        txtEventMaxPart = details.getTxtEventMaxPart();
        txtPrimaryCategory = categories.getTxtPrimaryCategory();

        String name = txtEventName.getText().toString();
        String about = txtEventAbout.getText().toString();
        double price = Double.parseDouble(txtPrice.getText().toString());
        int minage = Integer.parseInt(txtEventMinAge.getText().toString());
        int maxage = Integer.parseInt(txtEventMaxAge.getText().toString());
        int minpar = Integer.parseInt(txtEventMinPart.getText().toString());
        int maxpar = Integer.parseInt(txtEventMaxPart.getText().toString());
        String category = txtPrimaryCategory.getText().toString();

        txtLocation = map.getTxtLocation();

        String location = txtLocation.getText().toString();
        if(location.isEmpty()) {
            Toast.makeText(this, R.string.fill_all_blanks, Toast.LENGTH_SHORT).show();
        } else {
            DataStore.sharedInstance().addEvent(new Event(name, about, false, price, minage, maxage, minpar, maxpar, category));
            finish();
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