package inventoryManagementApplication.service;

import static org.mockito.Mockito.doReturn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import inventoryManagementApplication.model.User;
import inventoryManagementApplication.repository.UserRepository;

@SpringBootTest
public class UserServiceImplTest {
	@Autowired
	private UserService service;

	@MockBean
	private UserRepository repository;

	@Test
	void testFindByEmail() {
		User user = new User("demo", "user", "demo@email.com", "password");
		doReturn(user).when(repository).findByEmail("demo@email.com");

		User returnedUser = service.findByEmail("demo@email.com");

		Assertions.assertTrue(returnedUser != null);
		Assertions.assertSame(returnedUser, user);
	}
}
