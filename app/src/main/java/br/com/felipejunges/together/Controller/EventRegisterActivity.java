package br.com.felipejunges.together.Controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.felipejunges.together.Adapter.CustomViewPager;
import br.com.felipejunges.together.Adapter.ScreenSlidePagerAdapter;
import br.com.felipejunges.together.Controller.EventRegisterFragments.EventRegisterBaseFragment;
import br.com.felipejunges.together.Controller.EventRegisterFragments.EventRegisterCategoriesFragment;
import br.com.felipejunges.together.Controller.EventRegisterFragments.EventRegisterDetailsFragment;
import br.com.felipejunges.together.Controller.EventRegisterFragments.EventRegisterMapFragment;
import br.com.felipejunges.together.R;

public class EventRegisterActivity extends FragmentActivity {

    private CustomViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private List<Fragment> fragments;

    private Button btnNextBase;
    private Button btnNextCategories;
    private Button btnNextDetails;
    private Button btnSalvar;
    private boolean enabled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_register);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = findViewById(R.id.containerEventRegister);
        fragments = new ArrayList<>();
        fragments.add(new EventRegisterBaseFragment());
        fragments.add(new EventRegisterDetailsFragment());
        fragments.add(new EventRegisterCategoriesFragment());
        fragments.add(new EventRegisterMapFragment());
        mPagerAdapter = new ScreenSlidePagerAdapter(this, getSupportFragmentManager(), fragments);
        mPager.setPagingEnabled(false);
        mPager.setAdapter(mPagerAdapter);

    }

    public void onEventRegisterNextBase(View v) {
        mPager.setCurrentItem(mPager.getCurrentItem() + 1, true);
    }

    public void onEventRegisterNextCategories(View v) {
        mPager.setCurrentItem(mPager.getCurrentItem() + 1, true);
    }

    public void onEventRegisterNextDetails(View v) {
        mPager.setCurrentItem(mPager.getCurrentItem() + 1, true);
    }

    public void onEventRegisterSave(View v) {
        mPager.setCurrentItem(mPager.getCurrentItem() + 1, true);
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