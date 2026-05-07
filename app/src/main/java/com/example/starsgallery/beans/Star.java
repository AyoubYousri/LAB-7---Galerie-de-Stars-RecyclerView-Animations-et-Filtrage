package com.example.starsgallery.beans;

/**
 * Classe représentant une Star de la galerie.
 */
public class Star {
    private int id;
    private String name;
    private int img;
    private float star;    // Note (rating)
    private static int counter = 0;

    /**
     * Constructeur pour créer une nouvelle Star.
     * L'ID est généré automatiquement.
     */
    public Star(String name, int img, float star) {
        this.id = ++counter;
        this.name = name;
        this.img = img;
        this.star = star;
    }

    // --- GETTERS ---

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getImg() {
        return img;
    }

    /**
     * Cette méthode doit s'appeler getStar() pour être cohérente
     * avec l'appel s.getStar() dans ton Adapter.
     */
    public float getStar() {
        return star;
    }

    // --- SETTERS ---

    public void setName(String name) {
        this.name = name;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public void setStar(float star) {
        this.star = star;
    }

    // Optionnel : utile pour le débogage dans le Logcat
    @Override
    public String toString() {
        return "Star{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + star +
                '}';
    }
}