import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;
import java.sql.Timestamp;

public class Animal implements DatabaseManagement{
  private int id;
  private String name;
  private String age;
  private String health;
  private String type;

  public Animal(String name, String age, String health, String type){
    this.name = name;
    this.age = age;
    this.health = health;
    this.type = type;
  }

  public String getName(){
    return name;
  }

  public int getId() {
    return id;
  }

  public String getAge() {
    return age;
  }

  public String getHealth() {
    return health;
  }

  public String getType() {
    return type;
  }

  @Override
  public boolean equals(Object otherAnimal) {
      if (otherAnimal instanceof Animal) {
          Animal newAnimal = (Animal) otherAnimal;
          return (this.getName().equals(newAnimal.getName()));
      }

      return false;
  }

  @Override
  public void save() {
    String sql = "INSERT INTO animals (name) VALUES (:name);";

    try (Connection con = DB.sql2o.open()) {
        this.id = (int) con.createQuery(sql, true)
            .addParameter("name", this.name)
            .executeUpdate()
            .getKey();
    }
  }
  @Override
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

}
