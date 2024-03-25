package model;

import java.time.LocalDateTime;

public class Note {
    private String title;
    private String text;
    private LocalDateTime time;

    public Note(String title, String text) {
        this.title = title;
        this.text = text;
        this.time = LocalDateTime.now();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        this.time = LocalDateTime.now();
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return title + " - "
                + time.getDayOfMonth()
                + "."
                + time.getMonthValue()
                + "."
                + time.getYear()
                + "\n"
                + text;

    }
}
