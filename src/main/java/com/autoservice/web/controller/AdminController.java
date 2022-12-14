package com.autoservice.web.controller;

import com.autoservice.dto.AdminLoginDto;
import com.autoservice.dto.MasterDto;
import com.autoservice.dto.OperationDto;
import com.autoservice.dto.PreparedOrderInfoDto;
import com.autoservice.entity.Admin;
import com.autoservice.entity.Operation;
import com.autoservice.entity.Order;
import com.autoservice.service.AdminService;
import com.autoservice.service.MasterService;
import com.autoservice.service.OperationService;
import com.autoservice.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    private final MasterService masterService;
    private final OrderService orderService;
    private final OperationService operationService;
    private static final Logger log = LogManager.getLogger(AdminController.class);

    public AdminController(AdminService adminService, MasterService masterService, OrderService orderService, OperationService operationService) {
        this.adminService = adminService;
        this.masterService = masterService;
        this.orderService = orderService;
        this.operationService = operationService;
    }


    @GetMapping("/login")
    public String adminLogin(@ModelAttribute("adminLoginDto") AdminLoginDto adminLoginDto) {
        return "admin/login";
    }

    @PostMapping("/login")
    public String adminLogin(@Valid @ModelAttribute("adminLoginDto") AdminLoginDto adminLoginDto, BindingResult bindingResult, HttpSession session, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/login";
        }
        Optional<Admin> admin = adminService.checkCredentials(adminLoginDto);
        if (admin.isPresent()) {
            session.setAttribute("currentAdmin", admin.get());
            log.info("Admin logged in");
            return "redirect:/";
        } else {
            model.addAttribute("message", "Wrong credentials");
            return "admin/login";
        }
    }

    @GetMapping("/profile")
    public String adminProfile(Model model) {
        model.addAttribute("allMasters", masterService.findAllMasters());
        model.addAttribute("allOrders", orderService.findAllOrders());
        return "admin/adminProfile";
    }

    @GetMapping("/profile/addMaster")
    public String addMaster(@ModelAttribute("newMaster") MasterDto masterDto) {
        return "/admin/addMaster";
    }

    @PostMapping("/profile/addMaster")
    public String addMaster(@Valid @ModelAttribute("newMaster") MasterDto masterDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/addMaster";
        }
        if (masterService.masterExists(masterDto.getPhoneNumber())) {
            model.addAttribute("message", "Master already exists");
            return "admin/addMaster";
        }
        masterService.save(masterDto);
        log.info("Master added");
        return "redirect:/admin/profile";
    }

    @GetMapping("/profile/deleteMaster")
    public String deleteMaster(long id) {
        masterService.deleteById(id);
        return "redirect:/admin/profile";
    }

    @GetMapping("/profile/manageOrder/{orderID}")
    public String manageOrder(@PathVariable("orderID") long orderID, Model model) {
        Order orderByID = orderService.findById(orderID);
        model.addAttribute("orderByID", orderByID);
        return "admin/orderPage";
    }

    @GetMapping("/profile/manageOrder/{orderID}/addOperation")
    public String addOperation(@ModelAttribute("newOperation") OperationDto operationDto, @PathVariable("orderID") long orderID) {
        return "admin/addOperation";
    }

    @PostMapping("/profile/manageOrder/{orderID}/addOperation")
    public String addOperation(@Valid @ModelAttribute("newOperation") OperationDto operationDto, BindingResult bindingResult, @PathVariable("orderID") long orderID) {
        if (bindingResult.hasErrors()) {
            return "admin/addOperation";
        }
        Operation operation = operationService.mapOperationDtoToOperation(operationDto, orderService.findById(orderID));
        operationService.save(operation);
        Order order = orderService.addOperationToList(orderService.findById(orderID), operation);
        orderService.update(order);
        log.info("Operation added");
        return "redirect:/admin/profile/manageOrder/" + orderID;
    }

    @GetMapping("/profile/manageOrder/{orderID}/editOrder")
    public String editOrder(@PathVariable("orderID") long orderID, Model model) {
        Order byId = orderService.findById(orderID);
        model.addAttribute("preparedOrderInfo", orderService.prepareOrderInfo(byId));
        return "admin/editOrder";
    }

    @PostMapping("/profile/manageOrder/{orderID}/editOrder")
    public String editOrder(@Valid @ModelAttribute("preparedOrderInfo") PreparedOrderInfoDto preparedOrderInfoDto, BindingResult bindingResult, @PathVariable("orderID") long orderID) {
        if (bindingResult.hasErrors()) {
            return "admin/editOrder";
        }
        Order byId = orderService.findById(orderID);
        orderService.editOrderInfo(byId, preparedOrderInfoDto);
        log.info("Order edited");
        return "redirect:/admin/profile/manageOrder/" + orderID;
    }
}
