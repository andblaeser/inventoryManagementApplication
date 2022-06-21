package inventoryManagementApplication.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import inventoryManagementApplication.model.Inventory;
import inventoryManagementApplication.model.Item;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(MockitoExtension.class)
public class InventoryRepositoryTest {
	@Autowired
	InventoryRepository inventoryRepository;

	@Autowired
	ItemRepository itemRepository;

	@Test
	public void testInventoryFindAll() {
		List<Inventory> allInventorys = inventoryRepository.findAll();
		Assertions.assertTrue(allInventorys.size() >= 1);
	}

	@Test
	public void testItemFindAll() {
		List<Item> allItems = itemRepository.findAll();
		Assertions.assertTrue(allItems.size() >= 1);
	}

	@Test
	public void testGetById() {
		Item item = new Item();
		Inventory inventory = new Inventory();
		inventory.setItem(item);
		inventoryRepository.save(inventory);
		Item returnedItem = itemRepository.getById(item.getId());
		Inventory returnedInventory = inventoryRepository.getById(inventory.getId());
		Assertions.assertNotNull(returnedInventory);
		Assertions.assertNotNull(returnedItem);
		itemRepository.delete(returnedItem);
		inventoryRepository.delete(returnedInventory);
	}

	@Test
	public void testFindByCountEquals() {
		List<Inventory> zeroStock = inventoryRepository.findByCountEquals(0);
		Assertions.assertTrue(zeroStock.size() >= 1);
	}
}
