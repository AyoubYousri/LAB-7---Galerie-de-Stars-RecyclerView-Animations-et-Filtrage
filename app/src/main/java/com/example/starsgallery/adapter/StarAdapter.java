package com.example.starsgallery.adapter;

import android.content.Context;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog; // Ajouté pour le pop-up
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.starsgallery.R;
import com.example.starsgallery.beans.Star;
import java.util.ArrayList;
import java.util.List;

public class StarAdapter extends RecyclerView.Adapter<StarAdapter.StarViewHolder> implements Filterable {
    private List<Star> stars;
    private List<Star> starsFilter;
    private Context context;

    public StarAdapter(Context context, List<Star> stars) {
        this.context = context;
        this.stars = stars;
        this.starsFilter = new ArrayList<>(stars);
    }

    @NonNull
    @Override
    public StarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.star_item, parent, false);
        return new StarViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StarViewHolder holder, int position) {
        // On utilise 'final' pour pouvoir accéder à la star dans l'écouteur de clic
        final Star s = starsFilter.get(position);

        holder.name.setText(s.getName().toUpperCase());
        holder.rating.setRating(s.getStar());
        Glide.with(context).load(s.getImg()).into(holder.img);

        // --- LOGIQUE DU CLIC POUR LA NOTATION ---
        holder.itemView.setOnClickListener(v -> {
            // On réutilise ton layout star_item pour le contenu du pop-up
            View dialogView = LayoutInflater.from(context).inflate(R.layout.star_item, null);

            ImageView imgEdit = dialogView.findViewById(R.id.img);
            RatingBar ratingEdit = dialogView.findViewById(R.id.stars);
            TextView nameEdit = dialogView.findViewById(R.id.name);
            TextView idEdit = dialogView.findViewById(R.id.ids);

            // On remplit les données
            nameEdit.setText(s.getName());
            idEdit.setText(String.valueOf(s.getId()));
            idEdit.setVisibility(View.VISIBLE); // Affiche l'ID comme sur ta capture
            ratingEdit.setRating(s.getStar());

            // IMPORTANT : Permettre la modification des étoiles dans le pop-up
            ratingEdit.setIsIndicator(false);

            Glide.with(context).load(s.getImg()).into(imgEdit);

            // Création et affichage de l'AlertDialog
            new AlertDialog.Builder(context)
                    .setTitle("Notez :")
                    .setMessage("Donner une note entre 1 et 5 :")
                    .setView(dialogView)
                    .setPositiveButton("VALIDER", (dialog, which) -> {
                        // Mise à jour de la note
                        s.setStar(ratingEdit.getRating());
                        // Rafraîchissement de l'affichage
                        notifyDataSetChanged();
                    })
                    .setNegativeButton("ANNULER", null)
                    .show();
        });
    }

    @Override
    public int getItemCount() { return starsFilter.size(); }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                List<Star> filtered = new ArrayList<>();
                if (charSequence == null || charSequence.length() == 0) {
                    filtered.addAll(stars);
                } else {
                    String pattern = charSequence.toString().toLowerCase().trim();
                    for (Star item : stars) {
                        if (item.getName().toLowerCase().contains(pattern)) filtered.add(item);
                    }
                }
                FilterResults results = new FilterResults();
                results.values = filtered;
                return results;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                starsFilter = (List<Star>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class StarViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name;
        RatingBar rating;

        public StarViewHolder(@NonNull View v) {
            super(v);
            img = v.findViewById(R.id.img);
            name = v.findViewById(R.id.name);
            rating = v.findViewById(R.id.stars);
        }
    }
}