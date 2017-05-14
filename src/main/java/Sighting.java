import java.sql.Timestamp;

public class Sighting {
    private int id;
    private int animal_id;
    private String location;
    private String ranger;
    private Timestamp time_seen;

    public Sighting(int animal_id, String location, String ranger, Timestamp time_seen) {
        this.animal_id = animal_id;
        this.location = location;
        this.ranger = ranger;
        this.time_seen = time_seen;
    }


}
