package com.example.accentureandroidtask.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.accentureandroidtask.R;
import com.example.accentureandroidtask.roomdatabase.entity.WeatherDataEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<WeatherDataEntity> data;
    private RecyclerViewAdapter.ClickListener clickListener;

    @Inject
    public RecyclerViewAdapter(ClickListener clickListener) {
        this.clickListener = clickListener;
        data = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.temp_raw, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        double  temp= Math.round(data.get(position).getTemperature())-273.15;
        String x= String.format(Locale.getDefault(), "%.0f°", temp);
        holder.txtName.setText("Temperature: "+x+"C");
        holder.txtBirthYear.setText("Date: "+data.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName;
        private TextView txtBirthYear;
        private CardView cardView;

        ViewHolder(View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.AR_TitleTextView);
            txtBirthYear = itemView.findViewById(R.id.AR_SubjectTextView);
            cardView = itemView.findViewById(R.id.AR_CardView);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.launchIntent(data.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface ClickListener {
        void launchIntent(WeatherDataEntity weatherDataEntity);
    }

    public void setData(List<WeatherDataEntity> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }
}

