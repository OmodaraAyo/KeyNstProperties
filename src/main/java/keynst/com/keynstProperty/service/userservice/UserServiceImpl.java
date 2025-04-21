package keynst.com.keynstProperty.service.userservice;

import keynst.com.keynstProperty.dtos.LoginDtos.LoginRequest;
import keynst.com.keynstProperty.dtos.LoginDtos.LoginResponse;
import keynst.com.keynstProperty.dtos.UsersDtos.UsersRequest;
import keynst.com.keynstProperty.dtos.UsersDtos.UsersResponse;
import keynst.com.keynstProperty.models.usersmodel.Users;
import keynst.com.keynstProperty.repository.UserRepo;
import keynst.com.keynstProperty.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    @Override
    public UsersResponse register(UsersRequest usersRequest){
        validateAllInput(usersRequest);
        Users savedUser = registerNewUser(usersRequest);
        return getUserResponse(savedUser);
    }

    @Override
    public LoginResponse verifyLogin(LoginRequest user) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            return confirmedLoginResponse(user);
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Bad credentials: Invalid username or password");
        }
    }

    private LoginResponse confirmedLoginResponse(LoginRequest user) {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtService.generateToken(user.getUsername()));
        loginResponse.setResponse("Login Successful");
        loginResponse.setIsLoggedIn(true);
        return loginResponse;
    }

    @Override
    public UsersResponse findById(Long id) {
        Optional<Users> user = userRepo.findById(id);
        if(user.isEmpty()){
           throw new RuntimeException("User not found");
        }
        return getUserResponse(user.get());
    }

    @Override
    public List<Users> findAll() {
        return userRepo.findAll();
    }

    private String validateIdLength(String id) {
        if(id == null || id.isBlank() || id.length() != 24) throw new RuntimeException("User not found");
        return id;
    }

    private String getCurrentTime(){
       return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    private Users registerNewUser(UsersRequest usersRequest) {
        Users user = new Users();
        user.setFirstName(usersRequest.getFirstName());
        user.setLastName(usersRequest.getLastName());
        user.setUsername(usersRequest.getUsername());
        user.setGender(usersRequest.getGender());
        user.setPassword(bCryptPasswordEncoder.encode(usersRequest.getPassword()));
        user.setEmail(usersRequest.getEmail());
        user.setDob(usersRequest.getDob());
        user.setPhone(usersRequest.getPhone());
        user.setDateCreated(getCurrentTime());
        user.setLoggedIn(false);
        return userRepo.save(user);
    }

    private UsersResponse getUserResponse(Users savedUser) {
        UsersResponse usersResponse = new UsersResponse();
        usersResponse.setUserId(savedUser.getUserId());
        usersResponse.setFirstName(savedUser.getFirstName());
        usersResponse.setLastName(savedUser.getLastName());
        usersResponse.setUsername(savedUser.getUsername());
        usersResponse.setPassword(savedUser.getPassword());
        usersResponse.setEmail(savedUser.getEmail());
        usersResponse.setDob(savedUser.getDob());
        usersResponse.setGender(savedUser.getGender());
        usersResponse.setPhone(savedUser.getPhone());
        usersResponse.setDateCreated(getCurrentTime());
        usersResponse.setLoggedIn(savedUser.isLoggedIn());
        return usersResponse;
    }

    private void validateEmail(String email) {
        String validEmailFormat = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$";
        if(email == null || email.isEmpty() ||email.trim().isEmpty()) throw new RuntimeException("Email is required");
        if(!email.matches(validEmailFormat)) throw new RuntimeException("Invalid email format");
    }

    private void validateDOBFormat(String dob) {
        try{
            LocalDate.parse(dob, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }catch(Exception e){
            throw new RuntimeException("Invalid date of birth");
        }
    }

    private void validateAllInput(UsersRequest usersRequest) {
        if(usersRequest.getFirstName() == null || usersRequest.getFirstName().isBlank()){
            throw new RuntimeException("First name is required");
        }
        if(usersRequest.getLastName() == null || usersRequest.getLastName().isBlank()){
            throw new RuntimeException("Last name is required");
        }
        if(usersRequest.getUsername() == null || usersRequest.getUsername().isBlank()){
            throw new RuntimeException("Username is required");
        }
        validateEmail(usersRequest.getEmail());
        if(usersRequest.getPassword() == null || usersRequest.getPassword().isBlank()){
            throw new RuntimeException("Password is required");
        }
        if(usersRequest.getPhone() == null || usersRequest.getPhone().isEmpty()){
            throw new RuntimeException("At least one phone number must be provided");
        }
        if(usersRequest.getDob() == null || usersRequest.getDob().isBlank()){
            throw new RuntimeException("Date of birth is required");
        }
        validateDOBFormat(usersRequest.getDob());
    }
}
