package com.thespiritman.firstcrudapplication.Controller;

import com.thespiritman.firstcrudapplication.CustomException.UserNotFoundException;
import com.thespiritman.firstcrudapplication.Entity.User;
import com.thespiritman.firstcrudapplication.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String showUserList(Model model){
        List<User> userList = userService.listAll();
        model.addAttribute("userList",userList);
        return "users";
    }

    @GetMapping("/users/addNew")
    public String  showNewForm(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle","Add New User");
        return "users_form";
    }

    @PostMapping("/users/save")
    public String saveUser(User user , RedirectAttributes redirectAttributes){
        userService.save(user);
        redirectAttributes.addFlashAttribute("message","User added successfully.");
        return "redirect:/users";
    }

   @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes){
       try {
           User user = userService.get(id);
           model.addAttribute("user", user);
           model.addAttribute("pageTitle", "Edit User ID: " + id);
           redirectAttributes.addFlashAttribute("message","User edited Successfully");
           return "users_form";
       } catch (UserNotFoundException e) {
           redirectAttributes.addFlashAttribute("message",e.getMessage());
           return "redirect:/users";
       }
   }

   @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
       try {
           userService.delete(id);
           redirectAttributes.addFlashAttribute("message","User ID " + id +" has been deleted successfully");
       } catch (UserNotFoundException e) {
           redirectAttributes.addFlashAttribute("message",e.getMessage());
       }
       return "redirect:/users";
   }
}
