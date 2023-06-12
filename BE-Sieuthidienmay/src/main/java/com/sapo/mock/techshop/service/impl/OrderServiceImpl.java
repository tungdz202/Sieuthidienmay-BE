package com.sapo.mock.techshop.service.impl;

import com.sapo.mock.techshop.common.exception.BusinessException;
import com.sapo.mock.techshop.common.utils.DTOValidator;
import com.sapo.mock.techshop.common.utils.RandomStringGenerator;
import com.sapo.mock.techshop.dto.request.OrderRequestDTO;
import com.sapo.mock.techshop.entity.AuthUser;
import com.sapo.mock.techshop.entity.Order;
import com.sapo.mock.techshop.mapper.OrderMapper;
import com.sapo.mock.techshop.repository.AuthUserRepo;
import com.sapo.mock.techshop.repository.OrderRepo;
import com.sapo.mock.techshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.sapo.mock.techshop.common.constant.HttpStatusConstant.NOT_FOUND_CODE;
import static com.sapo.mock.techshop.common.constant.HttpStatusConstant.ORDER_NOT_FOUND_MESSAGE;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    DTOValidator dtoValidator;

    @Autowired
    AuthUserRepo authUserRepo;

    @Autowired
    OrderMapper orderMapper;

    @Override
    public List<Order> getAll() {
        return orderRepo.findAll();
    }

    @Override
    public Order getById(Integer id) {
        return orderRepo.findById(id)
                .orElseThrow((() -> new BusinessException(NOT_FOUND_CODE, ORDER_NOT_FOUND_MESSAGE)));
    }

    @Override
    public Order create(OrderRequestDTO orderRequestDTO) {
        dtoValidator.validate(orderRequestDTO);

        Optional<AuthUser> authUser = authUserRepo.findById(orderRequestDTO.getUserId());

        String code = RandomStringGenerator.getAlphaNumericString(10);

        Order order = Order.builder()
                .code(code)
                .authUser(authUser.get())
                .name(authUser.get().getName())
                .phone(authUser.get().getPhone())
                .address(authUser.get().getAddress())
                .status(1)
                .build();

        if (!authUser.get().getName().equals(orderRequestDTO.getName())) {
            order.setName(orderRequestDTO.getName());
        }
        if (!authUser.get().getPhone().equals(orderRequestDTO.getPhone())) {
            order.setName(orderRequestDTO.getPhone());
        }
        if (!authUser.get().getAddress().equals(orderRequestDTO.getAddress())) {
            order.setName(orderRequestDTO.getAddress());
        }

        return orderRepo.save(order);
    }

    @Override
    public Order updateById(Integer id, OrderRequestDTO orderRequestDTO) {
        dtoValidator.validate(orderRequestDTO);

        Order order = getById(id);

        orderMapper.updateEntity(order, orderRequestDTO);

        return orderRepo.save(order);
    }

    @Override
    public void deleteById(Integer id) {
        getById(id);
        orderRepo.deleteById(id);
    }
}
