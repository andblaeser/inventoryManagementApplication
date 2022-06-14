package inventoryManagementApplication.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Item model class

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	private String itemName;
	private String description;
	private Date addedOn = new Date();

	@OneToOne(mappedBy = "item", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Inventory inventory;
}
