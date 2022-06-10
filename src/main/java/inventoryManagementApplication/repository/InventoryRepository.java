package inventoryManagementApplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import inventoryManagementApplication.model.Inventory;

@RepositoryRestResource(collectionResourceRel = "inventory", path = "inventory")
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
	List<Inventory> findAll();

	Inventory getById(Long id);
}
