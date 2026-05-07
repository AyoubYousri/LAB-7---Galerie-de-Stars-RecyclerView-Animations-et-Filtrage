package com.example.starsgallery.service;

import com.example.starsgallery.beans.Star;
import com.example.starsgallery.dao.IDao;
import java.util.ArrayList;
import java.util.List;
import com.example.starsgallery.R;

public class StarService implements IDao<Star> {
    private List<Star> stars;
    private static StarService instance;

    private StarService() {
        this.stars = new ArrayList<>();
        // On utilise les ressources drawable locales
        stars.add(new Star("Emma Watson", R.drawable.emma, 4.5f));
        stars.add(new Star("Tom Cruise", R.drawable.tom, 4.0f));
        stars.add(new Star("Amy Winehouse", R.drawable.amy, 5.0f));
        stars.add(new Star("Brad Pitt", R.drawable.brad, 3.5f));
        stars.add(new Star("Selena Gomez", R.drawable.selena, 2.0f));
    }

    public static StarService getInstance() {
        if (instance == null) instance = new StarService();
        return instance;
    }

    @Override public boolean create(Star o) { return stars.add(o); }
    @Override public boolean update(Star o) { return true; } // À implémenter si besoin
    @Override public boolean delete(Star o) { return stars.remove(o); }
    @Override public Star findById(int id) {
        for(Star s : stars) { if(s.getId() == id) return s; }
        return null;
    }
    @Override public List<Star> findAll() { return stars; }
}