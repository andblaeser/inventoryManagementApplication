package inventoryManagementApplication.controller;

import java.util.List;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import inventoryManagementApplication.exceptions.InsufficientStockException;
import inventoryManagementApplication.model.Inventory;
import inventoryManagementApplication.model.Item;
import inventoryManagementApplication.service.InventoryService;
import inventoryManagementApplication.service.ItemService;

// Inventory controller class
@Controller
public class InventoryController {
	@Autowired
	ItemService itemService;

	@Autowired
	InventoryService inventoryService;

	// Get add item page
	@GetMapping("/add")
	public String addItemPage(Model model) {
		model.addAttribute("item", new Item());
		return "add";
	}

	// Add item to inventory
	@PostMapping("/add")
	String addItem(@ModelAttribute Item item, Model model) throws NotFoundException {
		String message;
		Item savedItem;
		boolean success = false;
		try {
			savedItem = itemService.createItem(item);
			Inventory inventory = new Inventory();
			inventory.setCount(0);
			inventory.setItem(item);
			inventory.setUpdatedOn(item.getAddedOn());
			inventoryService.createInventory(inventory);
			message = item.getItemName() + " saved with id " + savedItem.getId();
			model.addAttribute("item", savedItem);
			success = true;
		} catch (EntityExistsException e) {
			message = e.getMessage();
			model.addAttribute("item", itemService.getItem(item.getId()));
		}
		model.addAttribute("message", message);
		model.addAttribute("success", success);
		return "add";
	}

	// Delete item from inventory
	@DeleteMapping("/deleteItem/{id}")
	String deleteItem(@PathVariable("id") Long id) throws NotFoundException {
		if (itemService.getItem(id) != null) {
			itemService.deleteItem(id);
			return "list";
		} else {
			return new ResponseStatusException(HttpStatus.NOT_FOUND).getMessage();
		}
	}

	// Get inventory page
	@GetMapping("/list")
	public String showAll(Model model) {
		List<Inventory> inventory = inventoryService.getInventorys();
		model.addAttribute("inventory", inventory);
		return "list";
	}

	// Get individual item page by id
	@GetMapping("/view/{id}")
	String viewItem(@PathVariable("id") Long id, Model model) throws NotFoundException {
		model.addAttribute("item", itemService.getItem(id));
		return "view";
	}

	// Add stock to an item in inventory
	@PutMapping("/addStock")
	@ResponseBody
	boolean addStock(@RequestParam("id") Long id, @RequestParam("count") Integer count) throws NotFoundException {
		boolean success = false;
		if (itemService.getItem(id) != null) {
			success = inventoryService.addToInventory(id, count) != null;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return success;
	}

	// Reduce stock of an item in inventory
	@PutMapping("/deleteStock")
	@ResponseBody
	String deleteStock(@RequestParam("id") Long id, @RequestParam("count") Integer count) throws NotFoundException {
		String message;
		@SuppressWarnings("unused")
		boolean success = false;
		if (itemService.getItem(id) != null) {
			try {
				success = inventoryService.deleteFromInventory(id, count) != null;
				message = "Stock deleted successfully";
			} catch (InsufficientStockException e) {
				message = e.getMessage();
			}
			return message;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
}
