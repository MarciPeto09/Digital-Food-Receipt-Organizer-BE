package marcelina.example.Digital.Food.Receipt.Organizer.service;

import jakarta.transaction.Transactional;
import marcelina.example.Digital.Food.Receipt.Organizer.model.Basket;
import marcelina.example.Digital.Food.Receipt.Organizer.model.User;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.ReceiptMapper;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.UserMapper;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.ReceiptDTO;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.UserDTO;
import marcelina.example.Digital.Food.Receipt.Organizer.repository.BasketRepo;
import marcelina.example.Digital.Food.Receipt.Organizer.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private BasketRepo basketRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ReceiptMapper receiptMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ReceiptService receiptService;

    public List<UserDTO> getAllUsers(){
        List<User> userList = userRepository.findAll();
        return userList.stream()
                .map(u -> userMapper.mapToDto(u))
                .toList();
    }

    public UserDTO getUserById(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User with id " + userId + " does not exist!"));
        return userMapper.mapToDto(user);
    }

    public List<ReceiptDTO> getUserReceipts(Long userId){
        return userRepository.findById(userId).get().getReceipts().stream()
                .map(receiptMapper::mapToDto)
                .toList();
    }

    public Double getUserTotalSpending(Long userId){
        return userRepository.findById(userId).get().getReceipts().getLast().getTotalAmount();
    }


    public UserDTO register(UserDTO request) {
        User user = new User();
        if (request.getUsername() == null || request.getEmail() == null || request.getPassword() == null || request.getRole() == null) {
            throw new IllegalArgumentException("All fields are required");
        }
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username is already taken");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email is already taken");
        }
        final Basket basket = new Basket();
        basketRepository.save(basket);
        user.setBasket(basket);
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        userRepository.save(user);
        return userMapper.mapToDto(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Transactional
    public UserDTO updateUser(Long userId, UserDTO updatedUser, MultipartFile photoFile){
        User user = userRepository.findById(userId).get();

        if(updatedUser.getUsername() != null && !updatedUser.getUsername().isEmpty()){
            user.setUsername(updatedUser.getUsername());
        }

        if(updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()){
            user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }

        if (photoFile != null && !photoFile.isEmpty()) {
            try {
                String uploadDir = "uploads/";
                String fileName = UUID.randomUUID() + "_" + photoFile.getOriginalFilename();
                Path filePath = Paths.get(uploadDir + fileName);

                Files.createDirectories(filePath.getParent());
                Files.copy(photoFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                user.setPhotoUrl(fileName);
            } catch (IOException e) {
                throw new RuntimeException("Errore nel salvataggio della foto", e);
            }
        }

        if(updatedUser.getEmail() != null && !updatedUser.getEmail().isEmpty()){
            user.setEmail(updatedUser.getEmail());
        }

        userRepository.save(user);

        return userMapper.mapToDto(user);
    }


    public void addAddress(Long userId, String deliveryAddress){
        User user = userRepository.findById(userId).get();
        user.setDeliveryAddress(deliveryAddress);
        userRepository.save(user);
    }

}
