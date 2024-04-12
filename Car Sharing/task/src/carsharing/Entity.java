package carsharing;

public interface Entity {
    public int getId();
    public void setId(int id);
    public String getName();
    public void setName(String name);
    public int getForeignId();
    public void setForeignId(int foreignId);
}
