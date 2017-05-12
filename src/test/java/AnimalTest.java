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

  @Test
  public void animal_instantiatesCorrectly_true() {
    assertEquals(true, testAnimal instanceof Animal);
  }

  @Test
  public void getName_animalInstantiatesWithName_Orangutan() {
    assertEquals("Orangutan", testAnimal.getName());
  }


}
