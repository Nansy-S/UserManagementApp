package com.prokopovich.usermanagementapp.controller;

import com.prokopovich.usermanagementapp.dao.UserAccountDao;
import com.prokopovich.usermanagementapp.dto.UserAccountDto;
import com.prokopovich.usermanagementapp.entity.UserAccount;
import com.prokopovich.usermanagementapp.enumeration.UserRole;
import com.prokopovich.usermanagementapp.enumeration.UserStatus;
import com.prokopovich.usermanagementapp.service.UserAccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserAccountController {

    private static final Logger LOGGER = LogManager.getLogger(UserAccountController.class);

    private final UserAccountService userService;

    @Autowired
    public UserAccountController(UserAccountService userService) {
        this.userService = userService;
    }

    @GetMapping(value = {""})
    public ModelAndView userList(Model model) {
        UserAccountDto filterFieldUser = new UserAccountDto();

        List<UserAccount> userList = userService.getAllUser();
        LOGGER.info("user List" + userList);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userList");
        model.addAttribute("filterFieldUser", filterFieldUser);
        model.addAttribute("userList", userList);
        setModelForListUser(model);
        LOGGER.info("/user was called");
        return modelAndView;
    }

    @PostMapping(value = {""})
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
        setModelForListUser(model);
        LOGGER.info("/user was called");
        return modelAndView;
    }

    private void setModelForListUser(Model model) {
        List<String> roleList = UserRole.getAllTitle();
        List<String> statusList = UserStatus.getAllTitle();
        model.addAttribute("roleList", roleList);
        model.addAttribute("statusList", statusList);
        //model.addAttribute("currentUser", MainController.getCurrentUser(userService));
    }

    @GetMapping(value = {"/{id}"})
    public ModelAndView userDetail(@PathVariable("id") int id) {
        UserAccount userAccount = userService.getByUserId(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userDetail");
        modelAndView.addObject("userAccount", userAccount);
        //modelAndView.addObject("currentUser", MainController.currentUser);
        LOGGER.info("userDetail was called");
        return modelAndView;
    }

    @GetMapping(value = "/new")
    public  ModelAndView addUserPage(Model model) {
        ModelAndView modelAndView = new ModelAndView("addUser");
        UserAccountDto userForm = new UserAccountDto();
        model.addAttribute("userForm", userForm);
        model.addAttribute("roleList", UserRole.getAllTitle());
        model.addAttribute("statusList", UserStatus.getAllTitle());
        LOGGER.info("addUser - GET was called");
        return modelAndView;
    }

    @PostMapping(value = "/new")
    public ModelAndView saveUser(Model model,
                                 @Valid @ModelAttribute("userForm") UserAccountDto userDto, Errors errors) {
        LOGGER.info("addUser - POST was called");
        ModelAndView modelAndView = new ModelAndView();
        if (errors.hasErrors()) {
            modelAndView.setViewName("addUser");
           // model.addAttribute("currentUser", MainController.currentUser);
        }
        else {
            modelAndView.setViewName("userList");
            UserAccount newUser = new UserAccount(
                    0,
                    userDto.getUsername(),
                    new BCryptPasswordEncoder().encode(userDto.getPassword()),
                    userDto.getFirstName(),
                    userDto.getLastName(),
                    userDto.getRole(),
                    userDto.getStatus());
            userService.addNewUser(newUser);
            model.addAttribute("userList",  userService.getAllUser());
            //model.addAttribute("currentUser", MainController.currentUser);
            return modelAndView;
        }
        return modelAndView;
    }

    @GetMapping(value = "/{id}/edit")
    public ModelAndView editPage(@PathVariable("id") int id) {
        UserAccount user = userService.getByUserId(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("edit");
        modelAndView.addObject("user", user);
        //modelAndView.addObject("currentUser", MainController.currentUser);
        LOGGER.info("/edit - GET was called");
        return modelAndView;
    }

    @PostMapping(value = "/{id}/edit")
    public ModelAndView editUser(@Valid @ModelAttribute("user") UserAccountDto userDto, Errors errors) {
        LOGGER.info("/edit - POST was called");
        ModelAndView modelAndView = new ModelAndView();
        if (errors.hasErrors()) {
            modelAndView.setViewName("edit");
        }
        UserAccount userAccount = new UserAccount(
                0,
                userDto.getUsername(),
                null,
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getRole(),
                null);
        userService.updateUser(userAccount);
        modelAndView.setViewName("redirect:/user/" + userAccount.getId());
        return modelAndView;
    }

    @PostMapping(value = "/{id}")
    public ModelAndView changeStatus(@PathVariable("id") int id,
                                     @ModelAttribute("newStatus") String newStatus) {
        LOGGER.info("/changeStatus - POST was called");
        ModelAndView modelAndView = new ModelAndView();
        String changeResult = userService.changeUserStatus(id, newStatus);
        modelAndView.addObject("changeResult", changeResult);
        modelAndView.setViewName("redirect:/user/" + id);
        return modelAndView;
    }
}
