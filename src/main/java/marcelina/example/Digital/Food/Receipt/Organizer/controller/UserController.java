package marcelina.example.Digital.Food.Receipt.Organizer.controller;

import marcelina.example.Digital.Food.Receipt.Organizer.config.LoginRequest;
import marcelina.example.Digital.Food.Receipt.Organizer.model.User;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.ReceiptDTO;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.UserDTO;
import marcelina.example.Digital.Food.Receipt.Organizer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody LoginRequest loginRequest) {
        User user = userService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());

        if (user != null) {
            UserDTO userDto = new UserDTO(user.getId(), user.getUsername(), user.getEmail());
            return ResponseEntity.ok(userDto);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody UserDTO request) {
            userService.register(request);
    }

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