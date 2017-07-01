import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  static int getHerokuAssignedPort() {
    ProcessBuilder processBuilder = new ProcessBuilder();
    if (processBuilder.environment().get("PORT") != null) {
      return Integer.parseInt(processBuilder.environment().get("PORT"));
    }
    return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
  }
  public static void main(String[] args) {

    port(getHerokuAssignedPort());
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("animals", Animal.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/animals", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("animals", Animal.all());
      model.put("template", "templates/animals.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/animals/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/animal-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/animals/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      try {
        Animal animal = new Animal(name);
        animal.save();
      } catch (IllegalArgumentException exception) {
        System.out.println("Please enter an animal name.");
      }
      response.redirect("/animals");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/endangered/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/endangered-animal-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/endangered/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      String health = request.queryParams("health");
      String age = request.queryParams("age");
      try {
        Endangered endangered = new Endangered(name, health, age);
        endangered.save();
      } catch (IllegalArgumentException exception) {
        System.out.println("Please enter all input fields.");
      }
      response.redirect("/animals");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/sightings/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("animals", Animal.all());
      model.put("template", "templates/sighting-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/sightings/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int animalId = Integer.parseInt(request.queryParams("animal"));
      String location = request.queryParams("location");
      String rangerName = request.queryParams("rangerName");
      try {
        Sighting sighting = new Sighting(animalId, location, rangerName);
      } catch (IllegalArgumentException exception) {
        System.out.println("Please enter Ranger name.");
      }
      response.redirect("/sightings");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/sightings", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("sightings", Sighting.all());
    model.put("Animal", Animal.class);
    model.put("template", "templates/sightings.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("/animals/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("animal", Animal.find(Integer.parseInt(request.params(":id"))));
      model.put("endangered", Endangered.find(Integer.parseInt(request.params(":id"))));
      model.put("Sighting", Sighting.class);
      model.put("template", "templates/animal.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
