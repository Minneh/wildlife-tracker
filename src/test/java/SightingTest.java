import org.sql2o.*;
import org.junit.*;
import java.sql.Timestamp;
import java.util.Date;
import java.text.DateFormat;
import static org.junit.Assert.*;

public class SightingTest{

  @Rule
  public DatabaseRule database = new DatabaseRule();

  private Sighting testSighting;

  @Before
  public void setUp() {
    Timestamp now = new Timestamp(new Date().getTime());
    testSighting = new Sighting(1, "Zone A", "Kamau", now);
  }

  @Test
  public void Sighting_instantiatesCorrectly() {
    assertTrue(testSighting instanceof Sighting);
  }

  @Test
  public void getId_sightingInstantiatesWithId(){
    testSighting.save();
    assertTrue(testSighting.getId() > 0);
  }

  @Test
  public void getAnimalId_sightingInstantiatesWithAnimalId(){
    testSighting.save();
    assertEquals(1, testSighting.getAnimalId());
  }

  @Test
  public void getLocation_sightingInstantiatesWithLocation(){
    testSighting.save();
    assertEquals("Zone A", testSighting.getLocation());
  }

  @Test
  public void getRangerName_sightingInstantiatesWithRangerName(){
    testSighting.save();
    assertEquals("Kamau", testSighting.getRangerName());
  }
  @Test
  public void equals_returnsTrueIfAllPropertiesAreTheSame() {
    Timestamp rightNow = new Timestamp(new Date().getTime());
    Sighting anotherSighting = new Sighting(1, "Zone A", "Kamau", rightNow);
    assertEquals(true, testSighting.equals(anotherSighting));
    }

  @Test
  public void save_assignsIdToObject() {
    testSighting.save();
    Sighting savedSighting = Sighting.all().get(0);
    assertEquals(testSighting.getId(), savedSighting.getId());
  }

  @Test
  public void save_insertsObjectIntoDatabase() {
    testSighting.save();
    assertTrue(Sighting.all().get(0).equals(testSighting));
  }

  @Test
  public void all_returnsAllInstancesOfSighting_false() {
    testSighting.save();
    Timestamp rightNow = new Timestamp(new Date().getTime());
    Sighting otherSighting = new Sighting(1, "Zone B", "Ole Nkrumah", rightNow);
    otherSighting.save();
    assertEquals(true, Sighting.all().get(0).equals(testSighting));
    assertEquals(true, Sighting.all().get(1).equals(otherSighting));
  }

  @Test
  public void find_returnsSightingWithSameId_secondSighting() {
    testSighting.save();
    Timestamp rightNow = new Timestamp(new Date().getTime());
    Sighting otherSighting = new Sighting(1, "Zone B", "Ole Nkrumah", rightNow);
    otherSighting.save();
    assertEquals(Sighting.find(otherSighting.getId()), otherSighting);
  }

  @Test
  public void delete_deletesSighting() {
      testSighting.save();
      testSighting.delete();
      assertEquals(0, Sighting.all().size());
  }

}
