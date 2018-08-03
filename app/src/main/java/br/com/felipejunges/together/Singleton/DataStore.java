package br.com.felipejunges.together.Singleton;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.com.felipejunges.together.Model.Event;
import br.com.felipejunges.together.R;

public class DataStore {

    private static DataStore instance = null;

    private List<Event> events;
    private Context context;

    protected DataStore() {}

    public static DataStore sharedInstance() {

        if (instance == null)
            instance = new DataStore();

        return instance;
    }

    public void setContext(Context context) {

        this.context = context;
        events = new ArrayList<>();

        addEvent(new Event(1, "Evento Um", R.drawable.beach, "Categoria Um"));
        addEvent(new Event(2, "Evento Dois", R.drawable.zyg2c, "Categoria Um"));
        addEvent(new Event(3, "Evento Três", R.drawable.night, "Categoria Três"));
    }

    public void addEvent(Event event) {

        events.add(event);
    }

    public void removeEvent(int position) {

        events.remove(position);
    }

    public void editEvent(Event event, int position) {

        events.set(position, event);
    }

    public void clearCities() {

        events.clear();
    }

    public List<Event> getEvents() {

        return events;
    }

    public Event getEvent(int position) {

        return events.get(position);
    }
}
