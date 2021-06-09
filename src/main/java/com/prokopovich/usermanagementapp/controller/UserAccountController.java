package com.prokopovich.usermanagementapp.controller;

import com.prokopovich.usermanagementapp.entity.UserAccount;
import com.prokopovich.usermanagementapp.service.UserAccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping
public class UserAccountController {

    private static final Logger LOGGER = LogManager.getLogger(UserAccountController.class);

    private final UserAccountService userService;

    @Autowired
    public UserAccountController(UserAccountService userService) {
        this.userService = userService;
    }

    @GetMapping(value = {"/user"})
    public ModelAndView userList(Model model) {
        List<UserAccount> userList = userService.getAllUser();
        LOGGER.trace("user List" + userList);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user");
        model.addAttribute("userList", userList);
        //model.addAttribute("currentUser", MainController.getCurrentUser(userService));
        LOGGER.trace("/user was called");
        return modelAndView;
    }

}
