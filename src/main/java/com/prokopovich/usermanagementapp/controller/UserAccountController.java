package com.prokopovich.usermanagementapp.controller;

import com.prokopovich.usermanagementapp.dto.UserAccountDto;
import com.prokopovich.usermanagementapp.entity.UserAccount;
import com.prokopovich.usermanagementapp.enumeration.UserRole;
import com.prokopovich.usermanagementapp.enumeration.UserStatus;
import com.prokopovich.usermanagementapp.service.UserAccountService;
import com.prokopovich.usermanagementapp.util.security.CustomUserDetailsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@EnableGlobalMethodSecurity(securedEnabled = true)
public class UserAccountController {

    private static final Logger LOGGER = LogManager.getLogger(UserAccountController.class);

    private final UserAccountService userService;
    private final CustomUserDetailsService userDetailsService;

    @Autowired
    public UserAccountController(UserAccountService userService, CustomUserDetailsService userDetailsService) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping(value = {""})
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ModelAndView userList(Model model) {
        UserAccountDto filterFieldUser = new UserAccountDto();

        List<UserAccount> userList = userService.getAllUser();
        LOGGER.info("user List" + userList);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userList");
        model.addAttribute("userList", userList);
        model.addAttribute("filterFieldUser", filterFieldUser);
        addEnumAndCurrentUserInModel(model);
        LOGGER.info("/user was called");
        return modelAndView;
    }

    @PostMapping(value = {""})
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ModelAndView filterUserList(Model model,
                                       @ModelAttribute("filterFieldUser") UserAccountDto filterFieldUser) {
        List<UserAccount> userList = userService.filterUser(
                filterFieldUser.getUsername(),
                filterFieldUser.getRole(),
                filterFieldUser.getStatus());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userList");
        model.addAttribute("userList", userList);
        model.addAttribute("filterFieldUser", filterFieldUser);
        addEnumAndCurrentUserInModel(model);
        LOGGER.info("/user was called");
        return modelAndView;
    }

    @GetMapping(value = {"/{id}"})
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ModelAndView userDetail(@PathVariable("id") int id) {
        UserAccount userAccount = userService.getByUserId(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userDetail");
        modelAndView.addObject("userAccount", userAccount);
        modelAndView.addObject("changedUser", "");
        modelAndView.addObject("currentUser", userDetailsService.getCurrentUser());
        LOGGER.info("userDetail was called");
        return modelAndView;
    }

    @PostMapping(value = "/{id}")
    @Secured("ROLE_ADMIN")
    public ModelAndView changeStatus(@PathVariable("id") int id,
                                     @RequestParam("changedUser") String newStatus) {
        LOGGER.info("/changeStatus - POST was called - new status: " + newStatus);
        ModelAndView modelAndView = new ModelAndView();
        userService.changeUserStatus(id, newStatus);
        modelAndView.setViewName("redirect:/user/" + id);
        return modelAndView;
    }

    @GetMapping(value = "/new")
    @Secured("ROLE_ADMIN")
    public ModelAndView addUserPage(Model model) {
        ModelAndView modelAndView = new ModelAndView("addUser");
        UserAccountDto userForm = new UserAccountDto();
        model.addAttribute("userForm", userForm);
        addEnumAndCurrentUserInModel(model);
        LOGGER.info("addUser - GET was called");
        return modelAndView;
    }

    @PostMapping(value = "/new")
    @Secured("ROLE_ADMIN")
    public ModelAndView saveUser(Model model,
                                 @Valid @ModelAttribute("userForm") UserAccountDto userDto, Errors errors) {
        LOGGER.info("addUser - POST was called");
        ModelAndView modelAndView = new ModelAndView();
        if (errors.hasErrors()) {
            modelAndView.setViewName("addUser");
            addEnumAndCurrentUserInModel(model);
        }
        else {
            modelAndView.setViewName("userList");
            UserAccount newUser = new UserAccount(
                    0,
                    userDto.getUsername(),
                    userDto.getPassword(),
                    userDto.getFirstName(),
                    userDto.getLastName(),
                    userDto.getRole(),
                    userDto.getStatus());
            newUser = userService.addNewUser(newUser);
            modelAndView.setViewName("redirect:/user/" + newUser.getId());
            return modelAndView;
        }
        return modelAndView;
    }

    @GetMapping(value = "/{id}/edit")
    @Secured("ROLE_ADMIN")
    public ModelAndView editPage(@PathVariable("id") int id) {
        UserAccount user = userService.getByUserId(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editUser");
        modelAndView.addObject("user", user);
        modelAndView.addObject("roleList", UserRole.getAllTitle());
        modelAndView.addObject("currentUser", userDetailsService.getCurrentUser());
        LOGGER.info("/edit - GET was called");
        return modelAndView;
    }

    @PostMapping(value = "/{id}/edit")
    @Secured("ROLE_ADMIN")
    public ModelAndView editUser(@PathVariable("id") int id,
                                 @Valid @ModelAttribute("user") UserAccountDto userDto, Errors errors) {
        LOGGER.info("/edit - POST was called");
        ModelAndView modelAndView = new ModelAndView();
        if (errors.hasErrors()) {
            modelAndView.setViewName("editUser");
        }
        UserAccount userAccount = new UserAccount(
                id,
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getRole(),
                userDto.getStatus());
        userService.updateUser(userAccount);
        modelAndView.setViewName("redirect:/user/" + userAccount.getId());
        return modelAndView;
    }

    private void addEnumAndCurrentUserInModel(Model model) {
        model.addAttribute("roleList", UserRole.getAllTitle());
        model.addAttribute("statusList", UserStatus.getAllTitle());
        model.addAttribute("currentUser", userDetailsService.getCurrentUser());
    }
}
