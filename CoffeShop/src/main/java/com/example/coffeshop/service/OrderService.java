package com.example.coffeshop.service;

import com.example.coffeshop.model.dto.AddOrderDto;
import com.example.coffeshop.model.dto.OrdersDto;
import com.example.coffeshop.model.entity.OrderEntity;
import com.example.coffeshop.repository.OrderRepository;
import com.example.coffeshop.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;
    private final CategoryService categoryService;
    private final UserService userService;


    public OrderService(OrderRepository orderRepository,
                        ModelMapper modelMapper, CurrentUser currentUser,
                        CategoryService categoryService, UserService userService) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    public void addOrder(AddOrderDto addOrderDto){

        OrderEntity order = modelMapper
                .map(addOrderDto, OrderEntity.class);
        order.setCategory(categoryService.findByName(addOrderDto.getCategory()));
        order.setEmployee(userService.findById(currentUser.getId()));

        orderRepository.save(order);
    }
    public List<OrdersDto> getOrdersByPrice(){
        return orderRepository
                .findByOrderByPriceDesc()
                .stream()
                .map(o -> modelMapper
                        .map(o, OrdersDto.class))
                .collect(Collectors.toList());
    }

    public void readyOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
