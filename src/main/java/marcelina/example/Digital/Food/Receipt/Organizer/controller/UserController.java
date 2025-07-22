package marcelina.example.Digital.Food.Receipt.Organizer.controller;

import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.ReceiptDTO;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.UserDTO;
import marcelina.example.Digital.Food.Receipt.Organizer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/{id}/receipts")
    public List<ReceiptDTO> getUserReceipts(@PathVariable Long id) {
        return userService.getUserReceipts(id);
    }


    @GetMapping("/{id}/total-spending")
    public Double getUserTotalSpending(@PathVariable Long id) {
        return userService.getUserTotalSpending(id);
    }
}