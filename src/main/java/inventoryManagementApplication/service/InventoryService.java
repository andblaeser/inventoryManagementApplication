package inventoryManagementApplication.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import inventoryManagementApplication.exceptions.InsufficientStockException;
import inventoryManagementApplication.model.Inventory;

// Inventory service interface
public interface InventoryService {
	public Inventory getInventory(Long id) throws NotFoundException;

	public Inventory createInventory(Inventory inventory);

	public List<Inventory> getInventorys();

	public Inventory updateInventory(Long id, Inventory request) throws NotFoundException;

	public Inventory addToInventory(Long id, int count);

	public Inventory deleteFromInventory(Long id, int count) throws InsufficientStockException;

	public List<Inventory> getOutOfStock();
}
