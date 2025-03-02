package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.model.Course;

import java.util.ArrayList;

import static io.javalin.rendering.template.TemplateUtil.model;

public class HelloWorld {
    public static void main(String[] args) {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        Course HTTP = new Course(1,"HTTP", "description");
        Course CSS = new Course(2,"CSS", "description");
        Course HTML = new Course(3,"HTML", "description");
        var courses = new ArrayList<Course>();
        courses.add(HTTP);
        courses.add(CSS);
        courses.add(HTML);

        app.get("/courses", ctx -> {
            var header = "Курсы по программированию";
            var page = new CoursesPage(courses, header);
            ctx.render("courses/index.jte", model("page", page));
        });

        app.get("/courses/{id}", ctx -> {
            var id = ctx.pathParamAsClass("id", Integer.class).getOrDefault(0);
            var header = "Курсы по программированию";
            ArrayList<Course> courseId = new ArrayList<>();

            courses.forEach((course) -> {
                if (id == course.getId()) {
                    courseId.add(course);
                }
            });

            var page = new CoursesPage(courseId, header);
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
