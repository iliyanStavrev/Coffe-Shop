package com.example.coffeshop.web;

import com.example.coffeshop.model.dto.LoginUserDto;
import com.example.coffeshop.model.dto.RegisterUserDto;
import com.example.coffeshop.model.entity.UserEntity;
import com.example.coffeshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("registerUserDto")
    public RegisterUserDto init(){
        return new RegisterUserDto();
    }

    @ModelAttribute("loginUserDto")
    public LoginUserDto initLogin(){
        return new LoginUserDto();
    }


    @GetMapping("/register")
    public String registerPage(){

        return "register";
    }
    @PostMapping("/register")
    public String register(@Valid RegisterUserDto registerUserDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors() || !registerUserDto.getPassword()
                .equals(registerUserDto.getConfirmPassword())){

            redirectAttributes
                    .addFlashAttribute("registerUserDto",registerUserDto);
            redirectAttributes.addFlashAttribute
                    ("org.springframework.validation.BindingResult.registerUserDto",bindingResult);

            return "redirect:/register";
        }

        userService.register(registerUserDto);

      return "redirect:/";
    }

    @GetMapping("/login")
    public String loginPage(Model model){

        if (!model.containsAttribute("isFound")){
            model.addAttribute("isFound",true);
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@Valid LoginUserDto loginUserDto,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){

            redirectAttributes
                    .addFlashAttribute("loginUserDto",loginUserDto);
            redirectAttributes.addFlashAttribute
                    ("org.springframework.validation.BindingResult.loginUserDto",bindingResult);

            return "redirect:/login";
        }

        UserEntity user = userService
                .findByUsernameAndPassword(loginUserDto.getUsername(),
                        loginUserDto.getPassword());

        if (user == null){
            redirectAttributes
                    .addFlashAttribute("loginUserDto",loginUserDto);
            redirectAttributes.addFlashAttribute
                    ("isFound",false);

            return "redirect:/login";
        }

        userService
                .loginUser(user.getId(),user.getUsername());

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession){

        httpSession.invalidate();
//        userService.logout();

        return "redirect:/";
    }
}
