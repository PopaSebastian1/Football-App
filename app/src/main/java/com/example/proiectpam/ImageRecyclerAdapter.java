package com.example.proiectpam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proiectpam.Models.Player;

import java.util.List;

public class ImageRecyclerAdapter extends RecyclerView.Adapter<ImageRecyclerAdapter.ViewHolder> {
    private Context context;
    private int layout;
    private String [] names;
    private String [] positions;
    private String [] clubs;
    private int [] ages;
    private int [] ids;
    private String [] playerImages;
    private RecyclerViewInterface viewInterface;

    public ImageRecyclerAdapter(Context context, int layout, String [] names, String[] positions, String [] clubs, int[] ages,int [] ids, String[] playerImages,RecyclerViewInterface viewInterface) {
        this.context = context;
        this.layout = layout;
        this.names = names;
        this.positions = positions;
        this.clubs = clubs;
        this.ages = ages;
        this.ids = ids;
        this.playerImages = playerImages;
        this.viewInterface = viewInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create ViewHolder by inflating the layouit
        View view = LayoutInflater.from(this.context).inflate(layout,parent,false);

        return new ImageRecyclerAdapter.ViewHolder(view, viewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // populate holder components with data
        Picasso.get().load(playerImages[position]).into(holder.imageView); // Încărcați imaginea din URL
        holder.playerNameTV.setText(names[position]);
        holder.playerPositionTV.setText(positions[position]);
        holder.playerClubTV.setText(clubs[position]);
        holder.playerAgeTV.setText(String.valueOf(ages[position]));

    }
    @Override
    public int getItemCount() {
        return names.length;
    }

    public void setData(List<Player> animals) {
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView playerNameTV, playerPositionTV, playerClubTV, playerAgeTV;

        public ViewHolder(View view, RecyclerViewInterface viewInterface){
            super(view);
            // extract from the view those 3 compoments
            imageView = view.findViewById(R.id.playerImageView);
            playerNameTV = view.findViewById(R.id.playerNameTextView);
            playerPositionTV = view.findViewById(R.id.playerPositionTextView);
            playerAgeTV = view.findViewById(R.id.playerAgeTextView);
            playerClubTV = view.findViewById(R.id.playerClubTextView);

            // set listener
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    viewInterface.onItemClick(position);

                }
            });

        }

    }

}
