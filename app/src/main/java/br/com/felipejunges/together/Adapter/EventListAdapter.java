package br.com.felipejunges.together.Adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.felipejunges.together.Singleton.DataStore;
import br.com.felipejunges.together.Model.Event;
import br.com.felipejunges.together.R;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventHolder>{

    private List<Event> events = DataStore.sharedInstance().getEvents();

    @Override
    public EventHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_eventlist, parent, false);

        return new EventHolder(view);
    }

    @Override
    public void onBindViewHolder(EventHolder holder, int position) {

        Event event = events.get(position);

        holder.txtEventName.setText(event.getName());
        holder.txtEventCategory.setText(event.getPrimaryCategory());
        if (!event.getProfileImage().isEmpty() && event.getProfileImage() != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(event.getProfileImage());
           // Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
            holder.imgEventPicture.setImageBitmap(bitmap);
            //holder.imgEventPicture.setBackgroundResource(event.getProfileImage());
            //holder.imgEventPicture.setScaleType(ImageView.ScaleType.FIT_XY);
        }


    }

    public void setEvents(List<Event> events) {
       this.events = events;
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class EventHolder extends RecyclerView.ViewHolder {
        TextView txtEventName;
        TextView txtEventCategory;
        ImageView imgEventPicture;

        public  EventHolder(@NonNull View itemView) {
            super(itemView);

            txtEventName = itemView.findViewById(R.id.txtEventName_List);
            txtEventCategory = itemView.findViewById(R.id.txtPrimaryCategory_List);
            imgEventPicture = itemView.findViewById(R.id.imgEventPicture_List);
        }


    }


}
