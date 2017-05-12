import org.junit.*;
import static org.junit.Assert.*;

public class SightingTest{

  @Rule
  public DatabaseRule database = new DatabaseRule();

  private Sighting testSighting;

  @Before
  public void setUp() {
    Timestamp now = new Timestamp(new Date().getTime());
    testSighting = new Sighting(1, "By the lower pond", "Ole Nkrumah", now);
  }


}
