package br.com.felipejunges.together.Controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import br.com.felipejunges.together.Adapter.EventListAdapter;
import br.com.felipejunges.together.Model.Event;
import br.com.felipejunges.together.Singleton.DataStore;
import br.com.felipejunges.together.R;

public class MainActivity extends AppCompatActivity {

    private RecyclerView eventRecycler;
    private EventListAdapter adapter;
    private GestureDetector gestureDetector;

    private int p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataStore.sharedInstance().setContext(this);

        eventRecycler = findViewById(R.id.listEvents);
        adapter = new EventListAdapter();
        eventRecycler.setAdapter(adapter);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Lista de Eventos - Together");

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        eventRecycler.setLayoutManager(manager);

        gestureDetector = new GestureDetector(new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent motionEvent) {
                // Toast.makeText(MainActivity.this, "onDown", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public void onShowPress(MotionEvent motionEvent) {
                //Toast.makeText(MainActivity.this, "onShowPress", Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                //  Toast.makeText(MainActivity.this, "onSingleTapUp", Toast.LENGTH_SHORT).show();
                View view = eventRecycler.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                int position = eventRecycler.getChildAdapterPosition(view);

                Intent intentTest = new Intent(MainActivity.this, EventActivity.class);
                Intent intent = new Intent(MainActivity.this, EventRegisterActivity.class);
                intent.putExtra("event", DataStore.sharedInstance().getEvent(position));
                intent.putExtra("position", position);
                startActivity(intentTest);
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                // Toast.makeText(MainActivity.this, "onScroll", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public void onLongPress(MotionEvent motionEvent) {
                View view = eventRecycler.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                int position = eventRecycler.getChildAdapterPosition(view);

                Intent intent = new Intent(MainActivity.this, EventRegisterActivity.class);
                intent.putExtra("event", DataStore.sharedInstance().getEvent(position));
                intent.putExtra("position", position);
                startActivity(intent);

            }

            @Override
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                View view = eventRecycler.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                /*
                Animation fadeOut = new AlphaAnimation(1, 0);
                fadeOut.setInterpolator(new AccelerateInterpolator()); //and this
                fadeOut.setStartOffset(1000);
                fadeOut.setDuration(1000);

                AnimationSet animation = new AnimationSet(false); //change to false
                animation.addAnimation(fadeOut);
                view.setAnimation(animation);
                */

                p = eventRecycler.getChildAdapterPosition(view);

                AlertDialog.Builder message = new AlertDialog.Builder(view.getContext());
                message.setTitle("Tem certeza que deseja excluir o evento?");
                message.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        DataStore.sharedInstance().removeEvent(p);

                        adapter.notifyDataSetChanged();
                    }
                });
                message.setNegativeButton("Não", null);
                message.show();
                return false;
            }
        });


            eventRecycler.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

                View view = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

                return (view != null && gestureDetector.onTouchEvent(motionEvent));
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b) {

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menuAdd:
                /*int items = DataStore.sharedInstance().getEvents().size() + 1;
                DataStore.sharedInstance().addEvent(
                        new EventActivity(items, "Evento " + items, R.drawable.beach, "Categoria X" + items)
                );
                adapter.notifyDataSetChanged(); */
                Intent intent = new Intent(MainActivity.this, EventRegisterActivity.class);
                startActivity(intent);
                break;

            case R.id.menuClear:
                AlertDialog.Builder message = new AlertDialog.Builder(this);
                message.setTitle("Tem certeza que deseja limpar os eventos?");
                message.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        DataStore.sharedInstance().clearCities();
                        adapter.notifyDataSetChanged();
                    }
                });
                message.setNegativeButton("Não", null);
                message.show();
                break;

            case R.id.menu4Test:
                Intent intentTest = new Intent(MainActivity.this, EventActivity.class);
                startActivity(intentTest);
                break;

        }

        return true;
    }
}
