package ua.sample.list.entity;

import java.util.UUID;

public class TextItem {

    private UUID id;
    private boolean isDone;
    private String text;

    public TextItem() {
    }

    public TextItem(String text) {
        id = UUID.randomUUID();
        this.text = text;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
