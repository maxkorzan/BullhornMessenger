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

        //////////////////////////////////////////////////////////////////////
        Message message = new Message();
        message.setTitle("Now, Check this out!");
        message.setContent("PRETTY COOL EHHH????");
        message.setDate("March 6, 2020");
        message.setImage("https://res.cloudinary.com/dwsdggfi5/image/upload/v1583432051/Images/Avatar/aang_marbles_fokzuc.gif");
        message.setUser(user4);
        messageRepository.save(message);

        Set<Message> messages = new HashSet<>();
        messages.add(message);
        user4.setMessages(messages);
        userRepository.save(user4);


        //////////////////////////////////////////////////////////////////////
        //create "message"
        message = new Message();
        message.setTitle("Boomerangs > Bending");
        message.setContent("Bending the elements is great and all, but it takes Real skill to master control over an inanimate object. Like a boomerang. A piece of wood.");
        message.setDate("Jan 9, 2020");
        message.setImage("https://res.cloudinary.com/dwsdggfi5/image/upload/v1583432051/Images/Avatar/boomerang_sokka_ip67rp.webp");
        message.setUser(user5);
        messageRepository.save(message);

        //create empty set "messages", add "message", and add the set to "user2"
        messages = new HashSet<>();
        messages.add(message);
        user5.setMessages(messages);
        userRepository.save(user5);


        //////////////////////////////////////////////////////////////////////
        message = new Message();
        message.setTitle("Fire Nation Sucks");
        message.setContent("Quit messing around near our shores, candleboys!  -- WATER TRIBEEEEEEEE");
        message.setDate("December 19, 2019");
        message.setImage("https://res.cloudinary.com/dwsdggfi5/image/upload/v1583432051/Images/Avatar/watertribe_iqg8gn.png");
        message.setUser(user6);
        messageRepository.save(message);

        messages = new HashSet<>();
        messages.add(message);
        user6.setMessages(messages);
        userRepository.save(user6);


        //////////////////////////////////////////////////////////////////////
    }

}
