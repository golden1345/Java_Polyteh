import java.io.Serializable;

public class Book implements Serializable {

  private static final long serialVersionUID = 1L;

  private int id;
  private String author;
  private String name;
  private int year;

  Book(int id, String author, String name, int year)  {
    this.id = id;
    this.author = author;
    this.name = name;
    this.year = year;
  }

  public Book() {
  }

public int getId() {
    return id;
  }

  public String getAuthor() {
    return author;
  }

  public String getName() {
    return name;
  }

  public int getYear() {
    return year;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setYear(int year) {
    this.year = year;
  }
}
