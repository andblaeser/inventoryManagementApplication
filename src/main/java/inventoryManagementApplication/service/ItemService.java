package inventoryManagementApplication.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import inventoryManagementApplication.model.Item;

// Item service interface
public interface ItemService {
	public Item getItem(Long id) throws NotFoundException;

	public Item createItem(Item item);

	public List<Item> getItems();

	public Item updateItem(Long id, Item request) throws NotFoundException;

	public void deleteItem(Long id) throws NotFoundException;
}
