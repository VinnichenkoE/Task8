package com.vinnichenko.task8.model.entity;

public class CustomBook {

    private int id;
    private String title;
    private String authors;
    private int numberPages;
    private String typography;

    public CustomBook(String title, String authors,
                      int numberPages, String typography) {
        this.title = title;
        this.authors = authors;
        this.numberPages = numberPages;
        this.typography = typography;
    }

    public CustomBook(int id, String title, String authors,
                      int numberPages, String typography) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.numberPages = numberPages;
        this.typography = typography;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthors() {
        return authors;
    }

    public int getNumberPages() {
        return numberPages;
    }

    public String getTypography() {
        return typography;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CustomBook that = (CustomBook) o;
        if (id != that.id) {
            return false;
        }
        if (numberPages != that.numberPages) {
            return false;
        }
        if (title != null ? !title.equals(that.title) : that.title != null) {
            return false;
        }
        if (authors != null ? !authors.equals(that.authors) : that.authors != null) {
            return false;
        }
        return typography != null ? typography.equals(that.typography) : that.typography == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (authors != null ? authors.hashCode() : 0);
        result = 31 * result + numberPages;
        result = 31 * result + (typography != null ? typography.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CustomBook{");
        sb.append("id='").append(id).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", authors='").append(authors).append('\'');
        sb.append(", numberPages=").append(numberPages);
        sb.append(", typography='").append(typography).append('\'');
        sb.append('}');
        return sb.toString();
    }
}