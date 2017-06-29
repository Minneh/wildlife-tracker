import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;
import java.sql.Timestamp;

public class Animal{
  public int id;
  public String name;
  public String type;
  public static final String ANIMAL_TYPE = "Non-endangered";

  public Animal(String name){
    if (name.equals("")){
      throw new IllegalArgumentException("Please enter an animal name.");
    }
    this.name = name;
    type = ANIMAL_TYPE;
  }

  public String getName(){
    return name;
  }

  public int getId() {
    return id;
  }

  public String getType() {
    return type;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object otherAnimal) {
      if (otherAnimal instanceof Animal) {
          Animal newAnimal = (Animal) otherAnimal;
          return (this.getName().equals(newAnimal.getName()));
      }

      return false;
  }

  public void save() {
    String sql = "INSERT INTO animals (name, type) VALUES (:name. :type);";

    try (Connection con = DB.sql2o.open()) {
        this.id = (int) con.createQuery(sql, true)
            .addParameter("name", this.name)
            .executeUpdate()
            .getKey();
    }
  }

    public void delete() {
        String sql = "DELETE FROM animals WHERE id = :id;";

        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                .addParameter("id", id)
                .executeUpdate();
        }
    }

  public static List<Animal> all() {
    String sql = "SELECT * FROM animals;";

    try (Connection con = DB.sql2o.open()) {
        return con.createQuery(sql)
            .throwOnMappingFailure(false)
            .executeAndFetch(Animal.class);
    }
  }

  public static Animal find(int id) {
    String sql = "SELECT * FROM animals WHERE id = :id;";

    try (Connection con = DB.sql2o.open()) {
        return con.createQuery(sql)
            .addParameter("id", id)
            .throwOnMappingFailure(false)
            .executeAndFetchFirst(Animal.class);
    }
  }

  public void update() {
    String sql = "UPDATE animals SET name = :name WHERE id = :id";

      try(Connection con = DB.sql2o.open()) {
        con.createQuery(sql)
          .addParameter("name", name)
          .addParameter("id", id)
          .throwOnMappingFailure(false)
          .executeUpdate();
      }
    }

}
