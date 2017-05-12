import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;
import java.sql.Timestamp;

public class Animal{
  private int id;
  private String name;

  public Animal(String name){
    this.name = name;
  }

  public String getName(){
    return name;
  }

  public int getId() {
    return id;
  }

  @Override
  public boolean equals(Object otherAnimal) {
      if (otherAnimal instanceof Animal) {
          Animal newAnimal = (Animal) otherAnimal;
          return (this.getName().equals(newAnimal.getName()));
      }

      return false;
  }

  Override
      public void save() {
          String sql = "INSERT INTO animals (name) VALUES (:name);";

          try (Connection con = DB.sql2o.open()) {
              this.id = (int) con.createQuery(sql, true)
                  .addParameter("name", this.name)
                  .executeUpdate()
                  .getKey();
          }
      }

}
