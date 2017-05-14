import java.sql.Timestamp;
import java.util.Date;
import java.text.SimpleDateFormat;
import org.sql2o.*;

public class Sighting implements DatabaseManagement{
  private int id;
  private int animal_id;
  private String location;
  private String ranger;
  private Timestamp time_seen;

  public Sighting(int animal_id, String location, String ranger) {
      this.animal_id = animal_id;
      this.location = location;
      this.ranger = ranger;
      this.time_seen = new Timestamp(new Date().getTime());
  }

  public int getId(){
    return id;
  }

  @Override
  public void save() {
    String sql = "INSERT INTO sightings (animal_id, location, ranger, time_seen) VALUES (:animal_id, :location, :ranger, :time_seen);";

    try (Connection con = DB.sql2o.open()) {
        this.id = (int) con.createQuery(sql, true)
            .addParameter("animal_id", this.animal_id)
            .addParameter("location", this.location)
            .addParameter("ranger", this.ranger)
            .addParameter("time_seen",this.time_seen)
            .executeUpdate()
            .getKey();
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
