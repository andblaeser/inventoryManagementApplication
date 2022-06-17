package inventoryManagementApplication.repository;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import inventoryManagementApplication.model.User;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
	@Autowired
	UserRepository userRepository;

	@Autowired
	private EntityManager entityManager;

	@ParameterizedTest
	@ValueSource(strings = { "userone@email.com", "usertwo@email.com" })
	public void findByEmailTest(String email) {
		User user = new User();
		user.setEmail(email);
		entityManager.persist(user);
		entityManager.flush();
		entityManager.clear();
		Assertions.assertTrue(userRepository.findByEmail(email) != null);
		entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
		entityManager.flush();
		entityManager.clear();
	}
}
