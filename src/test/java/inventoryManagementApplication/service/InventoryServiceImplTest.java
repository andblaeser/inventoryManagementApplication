package inventoryManagementApplication.service;

import static org.mockito.Mockito.doReturn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import inventoryManagementApplication.model.Inventory;
import inventoryManagementApplication.repository.InventoryRepository;

@SpringBootTest
public class InventoryServiceImplTest {
	@Autowired
	private InventoryService service;

	@MockBean
	private InventoryRepository repository;

	@Test
	void testGetInventory() {
		Inventory inventory = new Inventory();
		inventory.setId((long) 1);
		doReturn(inventory).when(repository).getById((long) 1);

		try {
			Inventory returnedInventory = service.getInventory((long) 1);
			Assertions.assertTrue(returnedInventory != null);
			Assertions.assertSame(inventory, returnedInventory);
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
	}

}
