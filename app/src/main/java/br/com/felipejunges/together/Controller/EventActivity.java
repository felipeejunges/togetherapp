package br.com.felipejunges.together.Controller;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TabHost;

import br.com.felipejunges.together.Adapter.SectionsPageAdapter;
import br.com.felipejunges.together.Controller.EventFragments.EventBaseInfoFragment;
import br.com.felipejunges.together.Controller.EventFragments.EventMoreInfoFragment;
import br.com.felipejunges.together.Model.Event;
import br.com.felipejunges.together.R;

public class EventActivity extends AppCompatActivity {

    Event event;

    TabHost tabHostWindow = null;

    private static final String TAG = "MainActivity";

    private SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;

    EventBaseInfoFragment eventBaseInfoFragment;
    EventMoreInfoFragment eventMoreInfoFragment;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.containerEvento);
        setupViewPager(mViewPager);

        TabLayout tabLayout = findViewById(R.id.tabsEvento);
        tabLayout.setupWithViewPager(mViewPager);

        FragmentManager manager = getSupportFragmentManager();
        Intent intent = getIntent();
        event = (Event) intent.getSerializableExtra("event");

        toolbar = findViewById(R.id.toolbarEvent);

        if(event != null && toolbar != null) {
            toolbar.setTitle(String.valueOf(event.getName()));
        }

        commitEvento(manager, eventBaseInfoFragment);
        commitEvento(manager, eventMoreInfoFragment);

        NestedScrollView nsv = findViewById(R.id.scroll);
        nsv.setFillViewport(true);

    }

    private void commitEvento(FragmentManager manager, Fragment fragmento) {
        FragmentTransaction tx = manager.beginTransaction();
        Bundle parametros = new Bundle();
        parametros.putSerializable("event", event);
        fragmento.setArguments(parametros);

        tx.commit();
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());

        eventBaseInfoFragment = new EventBaseInfoFragment();
        eventMoreInfoFragment = new EventMoreInfoFragment();
        adapter.addFragment(eventBaseInfoFragment, "Resumo");
        adapter.addFragment(eventMoreInfoFragment, "Detalhes");
        viewPager.setAdapter(adapter);
    }
}
