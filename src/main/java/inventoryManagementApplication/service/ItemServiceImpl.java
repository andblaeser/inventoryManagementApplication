package inventoryManagementApplication.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import inventoryManagementApplication.model.Item;
import inventoryManagementApplication.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	ItemRepository itemRepository;

	@Override
	public Item getItem(Long id) throws ChangeSetPersister.NotFoundException {
		return itemRepository.getById(id);
	}

	@Override
	public Item createItem(Item item) {
		return itemRepository.save(item);
	}

	@Override
	public List<Item> getItems() {
		return itemRepository.findAll();
	}

	@Override
	public Item updateItem(Long id, Item request) throws NotFoundException {
		Item fromDb = getItem(id);
		fromDb.setItemName(request.getItemName());
		fromDb.setDescription(request.getDescription());
		fromDb.setAddedOn(new Date());
		return itemRepository.save(fromDb);
	}

	@Override
	public void deleteItem(Long id) throws NotFoundException {
		itemRepository.delete(getItem(id));
	}

}
