package inventoryManagementApplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import inventoryManagementApplication.model.Inventory;

// Inventory repository class
@RepositoryRestResource(collectionResourceRel = "inventory", path = "inventory")
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
	List<Inventory> findAll();

	Inventory getById(Long id);

	@Query("SELECT i FROM Inventory i where i.count = :count")
	List<Inventory> findByCountEquals(@Param("count") int count);
}
