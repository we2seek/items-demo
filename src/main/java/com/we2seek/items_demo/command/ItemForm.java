package com.we2seek.items_demo.command;

import java.util.StringJoiner;

public class ItemForm {
    private Integer id;
    private String name;
    private boolean active = true;

    public ItemForm() {
    }

    public ItemForm(Integer id, String name, boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ItemForm.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("active=" + active)
                .toString();
    }
}
