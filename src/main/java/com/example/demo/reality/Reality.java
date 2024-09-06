package com.example.demo.reality;

import java.util.Arrays;

public class Reality {
    private Long id;
    private String type;
    private String location;
    private int price;
    private int rooms;
    private int area;
    private String description;
    private String[] images;

    public Reality() {
    }

    // constructor for all parameters
    public Reality(Long id, String type, String location, int price, int rooms, int area, String description, String[] images) {
        this.id = id;
        this.type = type;
        this.location = location;
        this.price = price;
        this.rooms = rooms;
        this.area = area;
        this.description = description;
        this.images = images;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "Reality{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", location='" + location + '\'' +
                ", price=" + price +
                ", rooms=" + rooms +
                ", area=" + area +
                ", description='" + description + '\'' +
                ", images=" + Arrays.toString(images) +
                '}';
    }
}
