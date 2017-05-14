import org.sql2o.*;

public class DB {

  public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker", null, null);
//   public static Sql2o sql2o = new Sql2o("jdbc:postgresql://ec2-23-21-76-49.compute-1.amazonaws.com:5432/df2ubtmuhc32s7","stdhhdzdeynsis", "43a1b82999c0f772dbbd8f7602f0fa50c75b0c3e0f7b0c2caa36637a9569de10");
}
