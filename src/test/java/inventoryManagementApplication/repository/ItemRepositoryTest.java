package inventoryManagementApplication.repository;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import inventoryManagementApplication.model.Item;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(MockitoExtension.class)
public class ItemRepositoryTest {
	@Autowired
	ItemRepository itemRepository;

	@BeforeEach
	void initUseCase() {
		Item item = new Item();
		item.setId((long) 500);
		itemRepository.save(item);
	}

	@AfterEach
	public void destroyAll() {
		itemRepository.deleteAll();
	}

	@Test
	public void testFindAll() {
		List<Item> allItems = itemRepository.findAll();
		Assertions.assertTrue(allItems.size() >= 1);
	}

	@Test
	public void testGetById() {
		Item returnedItem = itemRepository.getById((long) 500);
		Assertions.assertNotNull(returnedItem);
	}
}
