package inventoryManagementApplication.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import inventoryManagementApplication.model.User;

@DataJpaTest
public class UserRepositoryTest {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	UserRepository userRepository;

	@Test
	public void findByEmailTest() {
		User user1 = new User("John", "Doe", "johndoe@email.com", "password");
		entityManager.persist(user1);
		User user2 = new User("Demo", "User", "demo-user@email.com", "demo");
		entityManager.persist(user2);
		User foundUser = userRepository.findByEmail(user2.getEmail());
		assertTrue(foundUser.equals(user2));
	}
}
