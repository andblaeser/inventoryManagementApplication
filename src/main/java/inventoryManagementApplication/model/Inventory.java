package inventoryManagementApplication.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table
public class Inventory {
	@Id
	@Column(name = "id")
	private Long id;

	private Integer count;
	private Date updatedOn;

	@OneToOne
	@MapsId
	@JoinColumn(name = "id")
	private Item item;
}
