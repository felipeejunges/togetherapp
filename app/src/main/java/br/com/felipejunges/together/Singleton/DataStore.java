package br.com.felipejunges.together.Singleton;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.felipejunges.together.Dao.DatabaseHelper;
import br.com.felipejunges.together.Model.Event;
import br.com.felipejunges.together.R;

public class DataStore {

    private static DataStore instance = null;

    private DatabaseHelper db;
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
        db = new DatabaseHelper(context);
        events = db.getAllEvents();
    }

    public void addEvent(Event event) {
        String id = db.addOrUpdateEvent(event);
        if(id == null || id.isEmpty()) {
            Toast.makeText(context, "Não foi possível salvar o evento: " + event.getName(), Toast.LENGTH_LONG).show();
        } else {
            events.add(event);
        }

    }

    public void removeEvent(int position) {
        Event event = events.get(position);
        if(db.deleteEvent(event)) {
            events.remove(position);
        } else {
            Toast.makeText(context, "Não foi possível remover o evento: " + event.getName(), Toast.LENGTH_LONG).show();
        }

    }

    public void editEvent(Event event, int position) {
        String id = db.addOrUpdateEvent(event);
        if(id == null || id.isEmpty()) {
            Toast.makeText(context, "Não foi possível alterar o evento: " + event.getName(), Toast.LENGTH_LONG).show();
        } else {
            events.set(position, event);
        }

    }

    public void clearEvents() {

        //events.forEach(x -> db.deleteEvent(x));
        for(Event event : events) {
            db.deleteEvent(event);
        }
        events.clear();
    }

    public List<Event> getEvents() {
        return events;
    }

    public Event getEvent(int position) {
        return events.get(position);
    }

    public  List<Event> search(String result) {
        events = db.getSearchableEvents(result);
        return events;
    }

    public List<Event> showAllEvents() {
        events = db.getAllEvents();
        return events;
    }
}
