package com.example.basicsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.cloudinary.utils.ObjectUtils;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    CloudinaryConfig cloudc;
    @Autowired
    private UserService userService;

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    //USER REGISTRATION
    @GetMapping("/register")
    public String showRegistrationPage(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/register")
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user, BindingResult result, Model model){
        model.addAttribute("user", user);
        if (result.hasErrors()){
            return "registration";
        } else {
            userService.saveUser(user);
            model.addAttribute("message", "User Account Created");
        }
        return "index";
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping("/secure")
    public String secure(Principal principal, Model model){
        String username = principal.getName();
        model.addAttribute("user", userRepository.findByUsername(username));
        return "secure";
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping("/")
    public String index(Model model, Principal principal, Authentication authentication){
        //pull all users and messages from repositories --> template
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("messages", messageRepository.findAll());

        String username = null;
        try {
            username = principal.getName();
            model.addAttribute("car_user_id", userRepository.findByUsername(principal.getName()).getId());
            return "index";
        } catch (Exception e){
            model.addAttribute("car_user_id", 0);
            return "index";
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/addMessage")
    public String formMessage(Model model, Principal principal){
        model.addAttribute("message", new Message());
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("user_id",userRepository.findByUsername(principal.getName()).getId());
        return "formMessage";
    }

    @PostMapping("/processMessage")
    public String processForm(@Valid @ModelAttribute Message message, BindingResult result, /*@RequestParam("pic") String pic,*/
                              /*@RequestParam("user") long id,*/ @RequestParam("file") MultipartFile file,
                              @RequestParam("message_user_id") long message_user_id, Principal principal){
        if (result.hasErrors()){
            return "formMessage";
        }

        //upload image to Cloudinary
        if (file.isEmpty()){
//            car.setImage(pic);
        }
        else {
            try {
                Map uploadResult = cloudc.upload(file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
                message.setImage(uploadResult.get("url").toString());
                messageRepository.save(message);
            } catch (IOException e) {
                e.printStackTrace();
                return "redirect:/addMessage";
            }
        }

        User user = userRepository.findById(message_user_id).get();
        message.setUser(user);
        messageRepository.save(message);
        return "redirect:/";

/////////////////////////////////////////
//        if (message_user_id == 0) {
//            String username = principal.getName();
//            User user1 = userRepository.findByUsername(username);
//            message.setUser(user1);
//        }
//
//        else{
//            User user1 = userRepository.findById(message_user_id).get();
//            message.setUser(user1);
//            messageRepository.save(message);
//            return "redirect:/";
//        }
/////////////////////////////////////////

//        Category category = categoryRepository.findById(id).get();
//        car.setCategory(category);
//        carRepository.save(car);
//        return "redirect:/";

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping("/detail/{id}")
    public String showCar(@PathVariable("id") long id, Model model, Principal principal, Authentication authentication) {
        model.addAttribute("message", messageRepository.findById(id).get());
        model.addAttribute("users", userRepository.findAll());
//        model.addAttribute("car_user_id", userRepository.findByUsername(principal.getName()).getId());

        String username = null;
        try {
            username = principal.getName();
            model.addAttribute("message_user_id", userRepository.findByUsername(principal.getName()).getId());
            return "show";
        } catch (Exception e){
            model.addAttribute("message_user_id", 0);
            return "show";
        }

//        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateCar(@PathVariable("id") long id, Model model, Principal principal) {
        model.addAttribute("message", messageRepository.findById(id).get());
        model.addAttribute("user_id", userRepository.findByUsername(principal.getName()).getId());

        Message message = messageRepository.findById(id).get();
//        String pic=car.getImage();                //THE HARD WAY USING REQUESTPARAM
//        model.addAttribute("pic",pic);            //THE HARD WAY USING REQUESTPARAM
        model.addAttribute("message", message);

        model.addAttribute("users", userRepository.findAll());
        return "formMessage";
    }

    @RequestMapping("/delete/{id}")
    public String delCar(@PathVariable("id") long id, Model model) {
        messageRepository.deleteById(id);
        return "redirect:/";
    }

}
