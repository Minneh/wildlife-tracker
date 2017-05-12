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

  @Test
  public void getId_animalInstantiatesWithId(){
    testAnimal.save();
    assertTrue(testAnimal.getId > 0);
  }

  @Test
  public void equals_returnsTrueIfNamesAreTheSame() {
    Animal anotherAnimal = new Animal("Impala");
    assertTrue(testAnimal.equals(anotherAnimal));
    }

  @Test
  public void save_assignsIdToObject() {
    testAnimal.save();
    Animal savedAnimal = Animal.all().get(0);
    assertEquals(testAnimal.getId(), savedAnimal.getId());
  }

  @Test
    public void save_insertsObjectIntoDatabase() {
        testAnimal.save();
        assertTrue(Animal.all().get(0).equals(testAnimal));
    }
}
