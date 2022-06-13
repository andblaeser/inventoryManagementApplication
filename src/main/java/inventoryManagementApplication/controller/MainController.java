package inventoryManagementApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import inventoryManagementApplication.model.Inventory;
import inventoryManagementApplication.service.InventoryService;

@Controller
public class MainController {
	@Autowired
	InventoryService inventoryService;

	@GetMapping("/")
	public String root(Model model) {
		List<Inventory> outOfStock = inventoryService.getOutOfStock();
		model.addAttribute("outOfStock", outOfStock);
		return "index";
	}

	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}

	@GetMapping("/user")
	public String userIndex() {
		return "user/index";
	}

	@GetMapping("/terms")
	public String terms() {
		return "terms";
	}
}
