package vinhchis.entity;

public class Author implements Cloneable {
    private long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private String name;

    @Override
    public Author clone(){
        final Author clone = new Author();
        clone.name = name;
        return clone;
    }
}
