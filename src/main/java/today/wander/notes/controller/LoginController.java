package today.wander.notes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import today.wander.notes.model.User;
import today.wander.notes.model.security.UserRole;
import today.wander.notes.repository.RoleDao;
import today.wander.notes.repository.UserService;

import java.util.HashSet;
import java.util.Set;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleDao roleDao;

    @RequestMapping("/")
    public String home() {
        return "redirect:/login";
    }

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String login(ModelMap modelMap) {
        return "login";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signUp(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signupPost(@ModelAttribute("user") User user, Model model) {

        if (userService.checkUserExists(user.getUsername(), user.getEmail())) {
            if (userService.checkEmailExists(user.getEmail())) {
                model.addAttribute("emailExists", true);
            }
            if (userService.checkUsernameExists(user.getUsername())) {
                model.addAttribute("usernameExists", true);
            }
            return "signup";
        } else {
            Set<UserRole> userRoles = new HashSet<>();
            userRoles.add(new UserRole(user, roleDao.findByName("ROLE_USER")));

            userService.createUser(user, userRoles);
            return "redirect:/login";
        }
    }

 /*   @RequestMapping("/home")
    public String userFront(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "home";
    }*/

}
