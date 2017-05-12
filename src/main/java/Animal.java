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

}
