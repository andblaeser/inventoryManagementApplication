package inventoryManagementApplication.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import inventoryManagementApplication.model.User;

public interface UserService extends UserDetailsService {
	User findByEmail(String email);

	User save(UserRegistrationDto registration);
}
