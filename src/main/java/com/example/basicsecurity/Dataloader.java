package com.example.basicsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.activation.CommandObject;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class Dataloader implements CommandLineRunner {

    /////////////////////////////////////////////// SECURITY ///////////////////////////////////////////////
    /////////////////////////////////////////////// SECURITY ///////////////////////////////////////////////
    /////////////////////////////////////////////// SECURITY ///////////////////////////////////////////////

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

//    @Override
//    public void run(String... strings) throws Exception {
//        roleRepository.save(new Role("USER"));
//        roleRepository.save(new Role("ADMIN"));
//        Role adminRole = roleRepository.findByRole("ADMIN");
//        Role userRole = roleRepository.findByRole("USER");
//
//        User user = new User("jim@jim.com", "password", "Jim", "Jimmerson", true, "jim");
//        user.setRoles(Arrays.asList(userRole));
//        userRepository.save(user);
//
//        user = new User("admin@admin.com", "password", "Admin", "User", true, "admin");
//        user.setRoles(Arrays.asList(adminRole));
//        userRepository.save(user);
//    }

    ///////////////////////////////////////////// END SECURITY /////////////////////////////////////////////
    ///////////////////////////////////////////// END SECURITY /////////////////////////////////////////////
    ///////////////////////////////////////////// END SECURITY /////////////////////////////////////////////

//    @Autowired
//    UserRepository userRepository;

    @Autowired
    MessageRepository messageRepository;

    @Override
    public void run(String... strings) throws Exception {

        /////////////////////////////////////////////// SECURITY ///////////////////////////////////////////////
        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("ADMIN"));
        Role adminRole = roleRepository.findByRole("ADMIN");
        Role userRole = roleRepository.findByRole("USER");

        User user = new User("admin@admin.com", "password", "Admin", "User", true, "admin");
        user.setRoles(Arrays.asList(adminRole));
        userRepository.save(user);

        User user2 = new User("jim@jim.com", "password", "Jim", "Jimmerson", true, "jim");
        user2.setRoles(Arrays.asList(userRole));
        userRepository.save(user2);

        User user3 = new User("test@test.com", "test", "Testy", "Tester", true, "test");
        user3.setRoles(Arrays.asList(userRole));
        userRepository.save(user3);

        User user4 = new User("aang@avatarsonly.com", "password", "Aang", "The Last Airbender", true, "aang");
        user4.setRoles(Arrays.asList(userRole));
        userRepository.save(user4);

        User user5 = new User("sokka@watertribe.net", "password", "Sokka", "\"Captain Boomerang\"", true, "sokka");
        user5.setRoles(Arrays.asList(userRole));
        userRepository.save(user5);

        User user6 = new User("katara@watertribe.net", "password", "Katara", "Water Bender", true, "katara");
        user6.setRoles(Arrays.asList(userRole));
        userRepository.save(user6);


        ///////////////////////////////////////////// END SECURITY /////////////////////////////////////////////

        //create "message"
        Message message = new Message();
        message.setTitle("Boomerangs > Bending");
        message.setContent("Bending the elements is great and all, but it takes Real skill to master control over an inanimate object. Like a boomerang. A piece of wood.");
        message.setDate("Jan 1, 2020");
        message.setImage("https://res.cloudinary.com/dwsdggfi5/image/upload/v1583432051/Images/Avatar/boomerang_sokka_ip67rp.webp");
        message.setUser(user5);

        //create empty set "messages", add "message", and add the set to "user2"
        Set<Message> messages = new HashSet<>();
        messages.add(message);
        user5.setMessages(messages);
        userRepository.save(user5);

        //define and save user for "message"
        message.setUser(user5);
        messageRepository.save(message);

        //////////////////////////////////////////////////////////////////////

        message = new Message();
        message.setTitle("Fire Nation Sucks");
        message.setContent("Quit messing around near our shores, candleboys!  -- WATER TRIBEEEEEEEE");
        message.setDate("June 19, 2016");
        message.setImage("https://res.cloudinary.com/dwsdggfi5/image/upload/v1583432051/Images/Avatar/watertribe_iqg8gn.png");
        message.setUser(user6);

        messages = new HashSet<>();
        messages.add(message);
        user6.setMessages(messages);
        userRepository.save(user6);

        message.setUser(user6);
        messageRepository.save(message);

        //////////////////////////////////////////////////////////////////////

        message = new Message();
        message.setTitle("Hey Guys, Check this out!");
        message.setContent("PRETTY COOL EHHH????");
        message.setDate("June 19, 2016");
        message.setImage("https://res.cloudinary.com/dwsdggfi5/image/upload/v1583432051/Images/Avatar/aang_marbles_fokzuc.gif");
        message.setUser(user4);

        messages = new HashSet<>();
        messages.add(message);
        user4.setMessages(messages);
        userRepository.save(user4);

        message.setUser(user4);
        messageRepository.save(message);




//        category = new Category();
//        category.setName("SUV");
//
//        Car car1 = new Car();
//        car1.setName("Land Rover Discovery");
//        car1.setYear("2018");
//        car1.setPrice("58,399");
//        car1.setImage("https://res.cloudinary.com/dwsdggfi5/image/upload/v1582924913/Images/vehicle/landrover_ehtzmc.jpg");
//        car1.setUser(user2);
//
//
//        Car car2 = new Car();
//        car2.setName("Porsche Cayenne Turbo S");
//        car2.setYear("2019");
//        car2.setPrice("$94,899");
//        car2.setImage("https://res.cloudinary.com/dwsdggfi5/image/upload/v1582924913/Images/vehicle/cayenne_lerhuo.jpg");
//        car2.setUser(user2);
//
//
//        Car car3 = new Car();
//        car3.setName("Mercedes-Benz GLS63 AMG");
//        car3.setYear("2019");
//        car3.setPrice("$78,299");
//        car3.setImage("https://res.cloudinary.com/dwsdggfi5/image/upload/v1582924913/Images/vehicle/gls_amg_oy9ful.jpg");
//        car3.setUser(user2);
//
//
//        cars = new HashSet<>();
//        cars.add(car1);
//        cars.add(car2);
//        cars.add(car3);
//
//        category.setCars(cars);
//        categoryRepository.save(category);
//
//        car1.setCategory(category);
//        car2.setCategory(category);
//        car3.setCategory(category);
//        carRepository.save(car1);
//        carRepository.save(car2);
//        carRepository.save(car3);
//
//        //////////////////////////////////////////////////////////////////////
//
//
//        category = new Category();
//        category.setName("Commercial Fleet");
//        car = new Car();
//        car.setName("Chevrolet Silverado Z71");
//        car.setYear("2019");
//        car.setPrice("$47,899");
//        car.setImage("https://res.cloudinary.com/dwsdggfi5/image/upload/v1582924913/Images/vehicle/silverado_ehheig.jpg");
//        car.setUser(user2);
//
//        cars = new HashSet<>();
//        cars.add(car);
//        category.setCars(cars);
//        categoryRepository.save(category);
//
//        car.setCategory(category);
//        carRepository.save(car);
//
//        //////////////////////////////////////////////////////////////////////
//
//        category = new Category();
//        category.setName("Car");
//        car = new Car();
//        car.setName("BMW M3");
//        car.setYear("2020");
//        car.setPrice("$67,999");
//        car.setImage("https://res.cloudinary.com/dwsdggfi5/image/upload/v1582924914/Images/vehicle/m3_vmeqbn.jpg");
//        car.setUser(user2);
//
//        cars = new HashSet<>();
//        cars.add(car);
//        category.setCars(cars);
//        categoryRepository.save(category);
//
//        car.setCategory(category);
//        carRepository.save(car);
//
//        //////////////////////////////////////////////////////////////////////
//
//        category = new Category();
//        category.setName("Motorcycle");
//        car = new Car();
//        car.setName("Ducati Panigale V4 S");
//        car.setYear("2020");
//        car.setPrice("$18,699");
//        car.setImage("https://res.cloudinary.com/dwsdggfi5/image/upload/v1583350039/Images/vehicle/panigale_dot1ud.jpg");
//        car.setUser(user2);
//
//        cars = new HashSet<>();
//        cars.add(car);
//        category.setCars(cars);
//        categoryRepository.save(category);
//
//        car.setCategory(category);
//        carRepository.save(car);
//
//        //////////////////////////////////////////////////////////////////////

    }

}
