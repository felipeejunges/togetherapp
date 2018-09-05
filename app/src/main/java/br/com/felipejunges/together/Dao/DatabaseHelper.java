package br.com.felipejunges.together.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.felipejunges.together.Model.Event;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Together";
    private static final int DB_VERSION = 1;

    private static final String TB_EVENTS = "events";

    private Context context;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TB_EVENTS + "(" +
                "id CHAR(36) PRIMARY KEY, " +
                "name VARCHAR(100) DEFAULT '', " +
                "description TEXT DEFAULT '', " +
                "unactive BIT DEFAULT 0, " +
                "minAge INT DEFAULT 0, " +
                "maxAge INT DEFAULT 0, " +
                "minParticipation INT DEFAULT 0, " +
                "maxParticipation INT DEFAULT 0, " +
                "price DOUBLE DEFAULT 0, " +
                "location TEXT DEFAULT '', " +
                "primaryCategory TEXT DEFAULT '', " +
                "profileImage TEXT DEFAULT '');";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    private String generateUUID() {
        return UUID.randomUUID().toString();
    }

    private void newIdIfNeed(Event event) {
        if(event.getId() == null || event.getId().isEmpty()) {
            event.setId(generateUUID());
        }
    }

    private boolean eventExist(Event event) {
        int quantidade = 0;
        if(event.getId() != null) {
            SQLiteDatabase db = getReadableDatabase();
            String existe = "SELECT id FROM " + TB_EVENTS + " WHERE id=? LIMIT 1";
            Cursor cursor = db.rawQuery(existe, new String[]{event.getId()});
            quantidade = cursor.getCount();
            cursor.close();
        }
        return quantidade > 0;
    }

    private String updateEvent(Event event) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {event.getId().toString()};
        ContentValues dados = getDatesFromEvent(event);
        db.update(TB_EVENTS, dados, "id = ?", params);
        return event.getId();
    }

    private String addEvent(Event event) {
        SQLiteDatabase db = getWritableDatabase();
        newIdIfNeed(event);
        ContentValues dados = getDatesFromEvent(event);
        db.insert(TB_EVENTS, null, dados);
        return getEvent(event.getId()).getId();
    }

    private ContentValues getDatesFromEvent(Event event) {
        ContentValues dados = new ContentValues();
        dados.put("id", event.getId());
        dados.put("name", event.getName());
        dados.put("description", event.getDescription());
        dados.put("unactive", event.isUnactive());
        dados.put("minAge", event.getMinAge());
        dados.put("maxAge", event.getMaxAge());
        dados.put("minParticipation", event.getMinParticipation());
        dados.put("maxParticipation", event.getMaxParticipation());
        dados.put("price", event.getPrice());
        dados.put("location", event.getLocation());
        dados.put("primaryCategory", event.getPrimaryCategory());
        dados.put("profileImage", event.getProfileImage());
        return dados;
    }

    private List<Event> fillWithEvents(Cursor c) {
        List<Event> events = new ArrayList<Event>();
        while(c.moveToNext()) {
            Event event = new Event();

            event.setId(c.getString(c.getColumnIndex("id")));
            event.setName(c.getString(c.getColumnIndex("name")));
            event.setDescription(c.getString(c.getColumnIndex("description")));
            event.setUnactive(c.getInt(c.getColumnIndex("unactive")) == 1);
            event.setMinAge(c.getInt(c.getColumnIndex("minAge")));
            event.setMaxAge(c.getInt(c.getColumnIndex("maxAge")));
            event.setMinParticipation(c.getInt(c.getColumnIndex("minParticipation")));
            event.setMaxParticipation(c.getInt(c.getColumnIndex("maxParticipation")));
            event.setPrice(c.getDouble(c.getColumnIndex("price")));
            event.setLocation(c.getString(c.getColumnIndex("location")));
            event.setPrimaryCategory(c.getString(c.getColumnIndex("primaryCategory")));
            event.setProfileImage(c.getString(c.getColumnIndex("profileImage")));

            events.add(event);
        }
        c.close();
        return events;
    }

    public List<Event> getAllEvents() {
        String sql = "SELECT * FROM " + TB_EVENTS + " WHERE unactive = 0 ORDER BY name ASC;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);
        return fillWithEvents(c);
    }

    public Event getEvent(String id) {
        String sql = "SELECT * FROM " + TB_EVENTS + " WHERE id = '" + id + "'";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);
        return fillWithEvents(c).get(0);
    }

    public boolean deleteEvent(Event event) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            String[] params =  {event.getId().toString()};
            if(event.isUnactive()) {
                db.delete(TB_EVENTS, "id = ?", params);
                return true;
            } else {
                event.setUnactive(true);
                updateEvent(event);
                return true;
            }
        } catch(Exception e) {
            return false;
        }
    }

    public String addOrUpdateEvent(Event event) {
        if(eventExist(event)) {
            return updateEvent(event);
        } else {
            return addEvent(event);
        }
    }


}
