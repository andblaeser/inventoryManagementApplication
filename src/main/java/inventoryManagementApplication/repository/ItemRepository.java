package inventoryManagementApplication.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import inventoryManagementApplication.model.Item;

@RepositoryRestResource(collectionResourceRel = "item", path = "item")
public interface ItemRepository extends JpaRepository<Item, Long> {
	List<Item> findAll();

	Item getById(Long id);
}
