package com.students.webappwithsecurity.controller;

import com.students.webappwithsecurity.dto.UserDto;
import com.students.webappwithsecurity.entity.User;
import com.students.webappwithsecurity.service.LogMessageService;
import com.students.webappwithsecurity.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class SecurityController {

    private final UserService userService;
    private final LogMessageService makeLogMessageService;

    public SecurityController(UserService userService, LogMessageService makeLogMessageService) {
        this.userService = userService;
        this.makeLogMessageService = makeLogMessageService;
    }

    @GetMapping("/index")
    public String home() {
        makeLogMessageService.makeMessage("/index", "Перещёл на главную страницу");
        return "/index";
    }

    @GetMapping("/login")
    public String login() {
        makeLogMessageService.makeMessage("/login", "Перещёл на страницу входа");
        return "/login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        makeLogMessageService.makeMessage("/register", "Перещёл на страницу регистрации");
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("register/save") UserDto userDto,
                               BindingResult result,
                               Model model) {
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null,
                    "На этот адрес электронной уже зарегистрирована учётная запись.");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "/register";
        }

        userService.saveUser(userDto);
        makeLogMessageService.makeMessage("/users", "Отправил данные для регистрации");
        return "redirect:/register?success";
    }

    @GetMapping("/users")
    public String users(Model model) {
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        makeLogMessageService.makeMessage("/users", "Перещёл к списку пользователей");
        return "users";
    }
    @GetMapping("/user/action")
    public String action(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.findUserByEmail(userDetails.getUsername());
        model.addAttribute("user", user);
        return "user-action";
    }
}
