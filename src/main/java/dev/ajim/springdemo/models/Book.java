package dev.ajim.springdemo.models;

public class Book {
    private int id;
    private String tile;
    private String author;

    public Book(String tile, String author) {
        this.tile = tile;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTile() {
        return tile;
    }

    public void setTile(String tile) {
        this.tile = tile;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
