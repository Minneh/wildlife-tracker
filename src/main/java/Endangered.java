import org.sql2o.*;
import java.util.List;

public class Endangered extends Animal {
  public String health;
  public String age;
  public static final String ANIMAL_TYPE = "Endangered";

  public Endangered(String name, String health, String age) {
    super(name);
    if (name.equals("") || health.equals("") || age.equals("")){
      throw new IllegalArgumentException("Please enter all input fields.");
    }
    this.health = health;
    this.age = age;
    type = ANIMAL_TYPE;
  }

  public String getHealth() {
    return health;
  }

  public String getAge() {
    return age;
  }

  public void setHealth(String health) {
    this.health = health;
  }

  public void setAge(String age) {
    this.age = age;
  }
}
