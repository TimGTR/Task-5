package hiber;

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

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("Lada", 2107)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("Lada", 2108)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("Lada", 2109)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("Lada", 2110)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      String model = "Lada";
      int series = 2107;
      List<?> carUsers = userService.getUserForCarModel(model, series);

      for (Object carUser : carUsers) {
         Object[] row = (Object[]) carUser;
         User user = (User) row[0];
         Car car = (Car) row[1];
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Car model = " + car.getModel() + ", series = " + car.getSeries());
         System.out.println();
      }


      context.close();
   }
}
