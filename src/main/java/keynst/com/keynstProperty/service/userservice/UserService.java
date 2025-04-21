package keynst.com.keynstProperty.service.userservice;

import keynst.com.keynstProperty.dtos.LoginDtos.LoginRequest;
import keynst.com.keynstProperty.dtos.LoginDtos.LoginResponse;
import keynst.com.keynstProperty.dtos.UsersDtos.UsersRequest;
import keynst.com.keynstProperty.dtos.UsersDtos.UsersResponse;
import keynst.com.keynstProperty.models.usersmodel.Users;

import java.math.BigInteger;
import java.util.List;

public interface UserService {

    UsersResponse register(UsersRequest usersRequest);
    LoginResponse verifyLogin(LoginRequest user);
    UsersResponse findById(Long id);
    List<Users> findAll();
}