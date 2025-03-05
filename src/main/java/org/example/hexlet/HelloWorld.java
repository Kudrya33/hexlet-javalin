package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.model.Course;
import org.example.hexlet.model.User;
import org.example.hexlet.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static io.javalin.rendering.template.TemplateUtil.model;

public class HelloWorld {
    public static void main(String[] args) {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get("/users/build", ctx -> {
            ctx.render("users/build.jte");
        });

        app.post("/users", ctx -> {
            var name = ctx.formParam("name").trim();
            var email = ctx.formParam("email").trim().toLowerCase();
            var password = ctx.formParam("password");
            var passwordConfirmation = ctx.formParam("passwordConfirmation");

            var user = new User(name, email, password);
            UserRepository.save(user);
            ctx.redirect("/users");
        });

        app.get("/layout", ctx -> {
            ctx.render("layout/page.jte");
        });

        app.get("/users", ctx -> {
           List<User> users = UserRepository.getEntities();
           ctx.render("users/user.jte", model("users", users));
        });

        Course HTTP = new Course(1,"HTTP", "Все о запросах");
        Course CSS = new Course(2,"CSS", "Теперь ты художник");
        Course HTML = new Course(3,"HTML", "Как же тебя разметить");
        var courses = new ArrayList<Course>();
        courses.add(HTTP);
        courses.add(CSS);
        courses.add(HTML);

        app.get("/courses", ctx -> {
            var header = "Курсы по программированию";
            var term = ctx.queryParam("term");

            List<Course> coursesSort;

            if (term != null && !term.isEmpty()) {
                coursesSort = courses.stream()
                        .filter(course -> course.getName().toLowerCase().contains(term.toLowerCase()) ||
                                course.getDescription().toLowerCase().contains(term.toLowerCase()))
                        .collect(Collectors.toList());
            } else {
                coursesSort = courses;
            }

            var page = new CoursesPage(coursesSort, header, term);
            ctx.render("courses/index.jte", model("page", page));
        });

        app.get("/courses/{id}", ctx -> {
            var id = ctx.pathParamAsClass("id", Integer.class).getOrDefault(0);
            var header = "Курсы по программированию";
            ArrayList<Course> courseId = new ArrayList<>();
            var term = ctx.queryParam("term");

            courses.forEach((course) -> {
                if (id == course.getId()) {
                    courseId.add(course);
                }
            });

            var page = new CoursesPage(courseId, header, term);
            ctx.render("courses/index.jte", model("page", page));
        });

        app.get("/hello", ctx -> {
            String name = ctx.queryParam("name");
            if (name == null || name.isEmpty()) {
                ctx.result("Hello, World!");
            } else {
                ctx.result("Hello, " + name + "!");
            }

        });

        app.get("/users/{id}/post/{postId}", ctx -> {
            var id = ctx.pathParam("id");
            var postId = ctx.pathParam("postId");

            ctx.result("Users: " + id + " Post ID: " + postId);

        });

        app.start(7070);
    }
}
