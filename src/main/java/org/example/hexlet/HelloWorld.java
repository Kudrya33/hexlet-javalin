package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
import io.javalin.validation.ValidationException;
import org.example.hexlet.controller.CarController;
import org.example.hexlet.controller.SessionsController;
import org.example.hexlet.controller.UsersController;
import org.example.hexlet.dto.MainPage;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.dto.users.BuildUserPage;
import org.example.hexlet.model.Course;
import org.example.hexlet.model.User;
import org.example.hexlet.repository.BaseRepository;
import org.example.hexlet.repository.UserRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import static io.javalin.rendering.template.TemplateUtil.model;

public class HelloWorld {
        public static void main(String[] args) throws Exception {

            var hikariConfig = new HikariConfig();
            hikariConfig.setJdbcUrl("jdbc:h2:mem:project;DB_CLOSE_DELAY=-1;");

            var dataSource = new HikariDataSource(hikariConfig);

            var url = HelloWorld.class.getClassLoader().getResourceAsStream("schema.sql");
            var sql = new BufferedReader(new InputStreamReader(url))
                    .lines().collect(Collectors.joining("\n"));

            try (var connection = dataSource.getConnection();
                 var statement = connection.createStatement()) {
                statement.execute(sql);
            }
            BaseRepository.dataSource = dataSource;

            var app = Javalin.create(config -> {
                config.bundledPlugins.enableDevLogging();
            });


        /*var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });*/

        app.get("/sessions/build", SessionsController::build);
        app.post("/sessions", SessionsController::create);
        app.delete("/sessions", SessionsController::destroy);


        app.get("/", ctx -> {
            var page = new MainPage(ctx.sessionAttribute("currentUser"));
            ctx.render("index.jte", model("page", page));
        });

        /*app.get("/", ctx -> {
            var visited = Boolean.valueOf(ctx.cookie("visited"));
            var page = new MainPage(visited);
            ctx.render("layout/mainPage.jte", model("page", page));
            ctx.cookie("visited", String.valueOf(true));
        });*/

        app.before(ctx -> {
            System.out.println("Date and time of receipt of the request: " + new Date());
        });

        /*app.get("/", ctx -> {
            ctx.result("Hello from middleware!");
        });*/

        app.after(ctx -> {
            System.out.println("Response has been sent");
        });

        app.get(NamedRoutes.buildUserPath(), ctx -> {
            var page = new BuildUserPage();
            ctx.render("users/build.jte", model("page", page));
        });

        app.post("/users", UsersController::create);

        /*app.post(NamedRoutes.usersPath(), ctx -> {
            var name = ctx.formParam("name").trim();
            var email = ctx.formParam("email").trim().toLowerCase();
            try {
                var passwordConfirmation = ctx.formParam("passwordConfirmation");
                var password = ctx.formParamAsClass("password", String.class)
                        .check(value -> value.equals(passwordConfirmation), "Пароли не совпадают")
                        .check(value -> value.length() > 6, "У пароля недостаточная длина")
                        .get();
                var user = new User(name, email, password);
                UserRepository.save(user);
                ctx.redirect(NamedRoutes.usersPath());
            } catch (ValidationException e) {
                var page = new BuildUserPage(name, email, e.getErrors());
                ctx.render("users/build.jte", model("page", page));
            }
        });*/

        app.get("/layout", ctx -> {
            ctx.render("layout/page.jte");
        });

        app.get("/users", ctx -> {

            String flash = ctx.consumeSessionAttribute("flash");

            List<User> users = UserRepository.getEntities();
            ctx.render("users/user.jte", model("users", users, "flash", flash));
        });

        Course HTTP = new Course(1,"HTTP", "Все о запросах");
        Course CSS = new Course(2,"CSS", "Теперь ты художник");
        Course HTML = new Course(3,"HTML", "Как же тебя разметить");
        var courses = new ArrayList<Course>();
        courses.add(HTTP);
        courses.add(CSS);
        courses.add(HTML);

        app.get(NamedRoutes.coursesPath(), ctx -> {
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
