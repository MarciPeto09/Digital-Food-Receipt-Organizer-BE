package marcelina.example.Digital.Food.Receipt.Organizer.controller;

import marcelina.example.Digital.Food.Receipt.Organizer.config.LoginRequest;
import marcelina.example.Digital.Food.Receipt.Organizer.jwt.JwtUtil;
import marcelina.example.Digital.Food.Receipt.Organizer.model.User;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.ReceiptDTO;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.UserDTO;
import marcelina.example.Digital.Food.Receipt.Organizer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        User user = userService.findByUsername(loginRequest.getUsername());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
        }

        String token = jwtUtil.generateToken(user);

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", user);

        return ResponseEntity.ok(response);

    }

    @PostMapping("/register")
    public UserDTO registerUser(@RequestBody UserDTO request) {
        return userService.register(request);
    }

    @PutMapping(value = "/updateUser/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UserDTO updateUser (@PathVariable Long userId,@RequestPart("user") UserDTO updateUser,@RequestPart(value = "photo", required = false)MultipartFile photoFile){
        return userService.updateUser(userId,updateUser,photoFile);
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



    @PutMapping("/deliveryAddress")
    public void addAddress(@RequestParam Long userId, @RequestParam String deliveryAddress){
        userService.addAddress(userId,deliveryAddress);
    }
}