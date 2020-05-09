package com.example.pokedex.modele;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedex.R;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<Pokemon> values;

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtHeader;
        TextView txtFooter;
        View layout;

        ViewHolder(View v)
        {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
        }
    }

    public void add(int position, Pokemon item)
    {
        values.add(position, item);
        notifyItemInserted(position);
    }

    private void remove(int position)
    {
        values.remove(position);
        notifyItemRemoved(position);
    }

    ListAdapter(List<Pokemon> myDataset) {
        values = myDataset;
    }
    // affichage en oncreate
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.raw_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // charge des elements de la list en scrollant
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Pokemon poke = values.get(position);
        holder.txtHeader.setText(poke.getImg());
        holder.txtHeader.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                remove(position);
            }
        });

        holder.txtFooter.setText(poke.getName());
    }
    // retourne le nombre d'element de la list
    @Override
    public int getItemCount() {
        return values.size();
    }
}

