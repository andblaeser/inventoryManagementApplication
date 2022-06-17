package inventoryManagementApplication.service;

import static org.mockito.Mockito.doReturn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import inventoryManagementApplication.model.Item;
import inventoryManagementApplication.repository.ItemRepository;

@SpringBootTest
public class ItemServiceImplTest {
	@Autowired
	private ItemService service;

	@MockBean
	private ItemRepository repository;

	@Test
	void testGetItem() {
		Item item = new Item();
		item.setId((long) 1);
		doReturn(item).when(repository).getById((long) 1);

		try {
			Item returnedItem = service.getItem((long) 1);
			Assertions.assertTrue(returnedItem != null);
			Assertions.assertSame(item, returnedItem);
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
	}
}
