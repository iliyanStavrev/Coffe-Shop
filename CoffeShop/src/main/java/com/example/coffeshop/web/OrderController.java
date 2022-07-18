package com.example.coffeshop.web;

import com.example.coffeshop.model.dto.AddOrderDto;
import com.example.coffeshop.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @ModelAttribute
    public AddOrderDto initOrder(){
        return new AddOrderDto();
    }

    @GetMapping("/orders/add")
    public String addOrderPage(){

        return "order-add";
    }

    @PostMapping("/orders/add")
    public String addOrder(@Valid AddOrderDto addOrderDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            redirectAttributes
                    .addFlashAttribute("addOrderDto",addOrderDto);
            redirectAttributes.addFlashAttribute
                    ("org.springframework.validation.BindingResult.addOrderDto",bindingResult);

            return "redirect:/orders/add";
        }

        orderService.addOrder(addOrderDto);

        return "redirect:/home";
    }
}
