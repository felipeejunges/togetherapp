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

        Event event1 = new Event(1, "Evento Um - Universidade", R.drawable.beach, "Categoria Um");
        Event event2 = new Event(2, "Evento Dois - Avião", R.drawable.zyg2c, "Categoria Um");
        Event event3 = new Event(3, "Evento Três - Onibus", R.drawable.night, "Categoria Três");

        event1.setLocation("PUC Paraná Curitiba");
        event1.setDescription("Evento Um da Categoria Um com descrição e localização na PUC Paraná Curitiba");
        event1.setMinAge(18);

        event2.setLocation("Aeroporto de Curitiba");
        event2.setDescription("Evento Dois da Categoria Um com descrição e localização no Aeroporto");

        event3.setLocation("Rodoviaria de Curitiba");
        event3.setDescription("Evento Três da Categoria Três com descrição e localização na Rodoviaria");

        addEvent(event1);
        addEvent(event2);
        addEvent(event3);
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
