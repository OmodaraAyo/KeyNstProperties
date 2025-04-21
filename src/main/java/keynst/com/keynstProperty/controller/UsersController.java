package keynst.com.keynstProperty.controller;

import jakarta.validation.Valid;
import keynst.com.keynstProperty.dtos.UsersDtos.UsersRequest;
import keynst.com.keynstProperty.dtos.UsersDtos.UsersResponse;
import keynst.com.keynstProperty.models.usersmodel.Users;
import keynst.com.keynstProperty.service.userservice.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsersController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/register")
    public UsersResponse createNewUser(@RequestBody @Valid UsersRequest usersRequest) {
        return userService.register(usersRequest);
    }

    @GetMapping("/users")
    public List<Users> getAllUsers() {
         return userService.findAll();
    }
}
