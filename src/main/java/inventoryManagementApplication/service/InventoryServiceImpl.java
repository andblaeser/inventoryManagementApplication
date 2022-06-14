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

// Inventory service implementation
@Service
public class InventoryServiceImpl implements InventoryService {
	@Autowired
	InventoryRepository inventoryRepository;

	// Get inventory entity by id
	@Override
	public Inventory getInventory(Long id) throws ChangeSetPersister.NotFoundException {
		return inventoryRepository.getById(id);
	}

	// Create new inventory
	@Override
	public Inventory createInventory(Inventory inventory) {
		return inventoryRepository.save(inventory);
	}

	// Get list of each inventory entity
	@Override
	public List<Inventory> getInventorys() {
		return inventoryRepository.findAll();
	}

	// Update inventory entity by id
	@Override
	public Inventory updateInventory(Long id, Inventory request) throws ChangeSetPersister.NotFoundException {
		Inventory fromDb = getInventory(id);
		fromDb.setCount(request.getCount());
		fromDb.setUpdatedOn(new Date());
		return inventoryRepository.save(fromDb);
	}

	// Add stock to inventory by id and count of stock
	@Override
	public Inventory addToInventory(Long id, int count) {
		Optional<Inventory> inventoryOptional = inventoryRepository.findById(id);
		Inventory inventory = inventoryOptional.isPresent() ? inventoryOptional.get() : null;
		if (inventory != null) {
			inventory.setCount(inventory.getCount() + count);
			inventory.setUpdatedOn(new Date());
		} else {
			inventory = new Inventory();
			inventory.setId(id);
			inventory.setCount(count);
		}
		return inventoryRepository.save(inventory);
	}

	// Reduce stock of inventory by id and count of stock
	@Override
	public Inventory deleteFromInventory(Long id, int count) throws InsufficientStockException {
		Optional<Inventory> inventoryOptional = inventoryRepository.findById(id);
		Inventory inventory = inventoryOptional.isPresent() ? inventoryOptional.get() : null;
		if (inventory != null && inventory.getCount() >= count) {
			inventory.setCount(inventory.getCount() - count);
			inventory.setUpdatedOn(new Date());
			return inventoryRepository.save(inventory);
		} else {
			throw new InsufficientStockException(inventory.getItem().getItemName() + " does not have enough stock!");
		}
	}

	// Get list of inventory entities with stock equal to 0
	@Override
	public List<Inventory> getOutOfStock() {
		return inventoryRepository.findByCountEquals(0);
	}
}
