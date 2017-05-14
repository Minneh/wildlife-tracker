import java.sql.Timestamp;

public class Sighting {
    private int id;
    private int animal_id;
    private String location;
    private String ranger;
    private Timestamp seen_at;

    public Sighting(int animal_id, String location, String ranger, Timestamp seen_at) {
        this.animal_id = animal_id;
        this.location = location;
        this.ranger = ranger;
        this.seen_at = seen_at;
    }

    
}
