import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class AnimalTest{

  @Rule DatabaseRule database = new DatabaseRule();

  private Animal testAnimal;

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
    assertTrue(testAnimal.getId() > 0);
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

  @Test
  public void all_returnsAllInstancesOfAnimal() {
    testAnimal.save();
    Animal savedAnimal = Animal.all().get(0);
    assertEquals(testAnimal.getId(), savedAnimal.getId());
  }

  @Test
   public void find_returnsAnimalWithSameId() {
     testAnimal.save();
     Animal anotherAnimal = new Animal("Baboon");
     anotherAnimal.save();
     assertEquals(Animal.find(anotherAnimal.getId()), anotherAnimal);
   }

   @Test
   public void delete_deletesAnimal() {
       testAnimal.save();
       testAnimal.delete();
       assertEquals(0, Animal.all().size());
   }
}
