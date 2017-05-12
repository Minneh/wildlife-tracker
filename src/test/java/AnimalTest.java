import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;
import java.util.List;

public class AnimalTest{

  Private Animal testAnimal;

  @Before
  public void setup(){
    Animal testAnimal = new Animal("Orangutan");
  }

}
