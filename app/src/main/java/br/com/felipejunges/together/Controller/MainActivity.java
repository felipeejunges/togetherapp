package br.com.felipejunges.together.Controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import br.com.felipejunges.together.Adapter.EventListAdapter;
import br.com.felipejunges.together.Model.Event;
import br.com.felipejunges.together.Singleton.DataStore;
import br.com.felipejunges.together.R;

public class MainActivity extends AppCompatActivity {

    private RecyclerView eventRecycler;
    private EventListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataStore.sharedInstance().setContext(this);

        eventRecycler = findViewById(R.id.listEvents);
        adapter = new EventListAdapter();
        eventRecycler.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        eventRecycler.setLayoutManager(manager);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Lista de Eventos");

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
                        new Event(items, "Evento " + items, R.drawable.beach, "Categoria X" + items)
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
        }

        return true;
    }
}
