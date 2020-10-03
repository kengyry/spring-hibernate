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

      //cars
      Car ferrari = new Car ("Ferrari", "Testarossa");
      Car lambo = new Car("Lamborghini", "Aventador");
      Car tesla = new Car("Tesla", "model S");
      Car zhiguli = new Car("WAZ", "2101");

      //users
      User nicolasCage = new User("Nicolas", "Cage", "firstAvenue@hollywood.us");
      User scarlettJohansson = new User("Scarlett", "Johansson", "secondAvenue@hollywood.us");
      User musk = new User("Elon", "Musk", "thirdAvenue@california.ca");
      User leonid = new User("Lenya", "Golubkov", "lenya010101@mail.ru");

      nicolasCage.setCar(ferrari);
      scarlettJohansson.setCar(lambo);
      musk.setCar(tesla);
      leonid.setCar(zhiguli);

      userService.add(nicolasCage);
      userService.add(scarlettJohansson);
      userService.add(musk);
      userService.add(leonid);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("\nId = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Model = " + user.getCar().getModel());
         System.out.println("Series = " + user.getCar().getSeries());
      }

      // Получение пользователя по модели и серии авто
      User user = userService.getUser("Ferrari", "Testarossa");
      System.out.println("\nId = " + user.getId());
      System.out.println("First Name = " + user.getFirstName());
      System.out.println("Last Name = " + user.getLastName());
      System.out.println("Email = " + user.getEmail());

      context.close();
   }
}
