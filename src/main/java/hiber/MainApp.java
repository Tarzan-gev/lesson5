package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.sql.SQLException;
import java.util.List;


public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car car1 = new Car();
        car1.setModel("Bmw");
        car1.setSeries(3);
        User user1 = new User();
        user1.setFirstName("Adrey");
        user1.setLastName("Badrov");
        user1.setEmail("adrey@mail.ru");
        user1.setCar(car1);

        Car car2 = new Car();
        car2.setModel("Audi");
        car2.setSeries(1);

        User user2 = new User();
        user2.setFirstName("Peter");
        user2.setLastName("Emeplyanenko");
        user2.setEmail("petr@mial.ru");
        user2.setCar(car2);


        userService.add(user1);
        userService.add(user2);


        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = "+user.getId());
            System.out.println("First Name = "+user.getFirstName());
            System.out.println("Last Name = "+user.getLastName());
            System.out.println("Email = "+user.getEmail());
            System.out.println();
        }
        User usss  = userService.getUserCar("Bmw", 3);
        if (usss != null) {
            System.out.println("Найден: " + usss.getFirstName() + " " + usss.getLastName());
        } else {
            System.out.println("Не найден");
        }

        context.close();
    }
}
