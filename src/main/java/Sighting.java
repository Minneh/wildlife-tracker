import java.sql.Timestamp;
import java.util.Date;
import java.text.SimpleDateFormat;
import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class Sighting implements DatabaseManagement{
  private int id;
  private int animal_id;
  private String location;
  private String ranger_name;
  private Timestamp time_seen;

  public Sighting(int animal_id, String location, String ranger_name, Timestamp time_seen) {
      this.animal_id = animal_id;
      this.location = location;
      this.ranger_name = ranger_name;
      this.time_seen = time_seen;
  }

  public int getId(){
    return id;
  }

  public int getAnimalId(){
    return animal_id;
  }

  public String getLocation(){
    return location;
  }

  public String getRangerName(){
    return ranger_name;
  }

  public Timestamp getTimeSeen(){
    return time_seen;
  }

  @Override
  public void save() {
    String sql = "INSERT INTO sightings (animal_id, location, ranger_name, time_seen) VALUES (:animal_id, :location, :ranger_name, :time_seen);";

    try (Connection con = DB.sql2o.open()) {
        this.id = (int) con.createQuery(sql, true)
            .addParameter("animal_id", this.animal_id)
            .addParameter("location", this.location)
            .addParameter("ranger_name", this.ranger_name)
            .addParameter("time_seen",this.time_seen)
            .executeUpdate()
            .getKey();
    }
  }

  public static List<Sighting> all() {
    String sql = "SELECT * FROM sightings;";

    try (Connection con = DB.sql2o.open()) {
        return con.createQuery(sql)
            .throwOnMappingFailure(false)
            .executeAndFetch(Sighting.class);
    }
  }

  public boolean equals(Object otherSighting){
    if(!(otherSighting instanceof Sighting)){
      return false;
    }else{
      Sighting newSighting = (Sighting) otherSighting;
      return this.getAnimalId()==newSighting.getAnimalId() && this.getRangerName().equals(newSighting.getRangerName());
    }
  }

  public static Sighting find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM sightings WHERE id=:id;";
      Sighting sighting = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Sighting.class);
      return sighting;
    } catch (IndexOutOfBoundsException exception) {
      return null;
    }
  }

  public void delete(){
    try(Connection con = DB.sql2o.open()) {
      con.createQuery("DELETE FROM sightings WHERE id=:id")
        .addParameter("id",id)
        .executeUpdate();
    }
  }

}
