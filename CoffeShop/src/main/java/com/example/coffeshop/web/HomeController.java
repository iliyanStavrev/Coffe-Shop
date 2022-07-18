package com.example.coffeshop.web;

import com.example.coffeshop.model.dto.OrdersDto;
import com.example.coffeshop.service.OrderService;
import com.example.coffeshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HomeController {

    private final OrderService orderService;
    private final UserService userService;

    public HomeController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("/home")
    public String homePage(Model model){

        List<OrdersDto> ordersDto = orderService.getOrdersByPrice();
        model.addAttribute("ordersDto",ordersDto);
        model.addAttribute("neededTime",ordersDto
                .stream()
                .map(o-> o.getCategory().getNeededTime())
                .reduce(Integer::sum)
                .orElse(0));

        model.addAttribute("users", userService.getAllUsers());

        return "home";
    }
    @GetMapping("orders/ready/{id}")
    public String readyOrder(@PathVariable Long id){

        orderService.readyOrder(id);

        return "redirect:/home";
    }

}
