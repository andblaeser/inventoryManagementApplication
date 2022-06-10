package inventoryManagementApplication.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import inventoryManagementApplication.exceptions.InsufficientStockException;
import inventoryManagementApplication.model.Inventory;
import inventoryManagementApplication.repository.InventoryRepository;

@Service
public class InventoryServiceImpl implements InventoryService {
	@Autowired
	InventoryRepository inventoryRepository;

	@Override
	public Inventory getInventory(Long id) throws ChangeSetPersister.NotFoundException {
		return inventoryRepository.getById(id);
	}

	@Override
	public Inventory createInventory(Inventory inventory) {
		return inventoryRepository.save(inventory);
	}

	@Override
	public List<Inventory> getInventorys() {
		return inventoryRepository.findAll();
	}

	@Override
	public Inventory updateInventory(Long id, Inventory request) throws ChangeSetPersister.NotFoundException {
		Inventory fromDb = getInventory(id);
		fromDb.setCount(request.getCount());
		fromDb.setUpdatedOn(new Date());
		return inventoryRepository.save(fromDb);
	}

	@Override
	public Inventory addToInventory(Long id, int count) {
		Optional<Inventory> inventoryOptional = inventoryRepository.findById(id);
		Inventory inventory = inventoryOptional.isPresent() ? inventoryOptional.get() : null;
		if (inventory != null) {
			inventory.setCount(inventory.getCount() + count);
		} else {
			inventory = new Inventory();
			inventory.setId(id);
			inventory.setCount(count);
		}
		return inventoryRepository.save(inventory);
	}

	@Override
	public Inventory deleteFromInventory(Long id, int count) throws InsufficientStockException {
		Optional<Inventory> inventoryOptional = inventoryRepository.findById(id);
		Inventory inventory = inventoryOptional.isPresent() ? inventoryOptional.get() : null;
		if (inventory != null && inventory.getCount() > count) {
			inventory.setCount(inventory.getCount() - count);
			return inventoryRepository.save(inventory);
		} else {
			throw new InsufficientStockException(inventory.getItem().getItemName() + " does not have enough stock!");
		}
	}
}
