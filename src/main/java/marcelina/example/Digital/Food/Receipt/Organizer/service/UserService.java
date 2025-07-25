package marcelina.example.Digital.Food.Receipt.Organizer.service;

import jakarta.persistence.EntityNotFoundException;
import marcelina.example.Digital.Food.Receipt.Organizer.model.User;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.ReceiptMapper;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.UserMapper;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.ReceiptDTO;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.UserDTO;
import marcelina.example.Digital.Food.Receipt.Organizer.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ReceiptMapper receiptMapper;

    public UserDTO getUserById(Long userId){
        return userMapper.mapToDto(userRepository.findById(userId).get());
    }

    public List<ReceiptDTO> getUserReceipts(Long userId){
        return userRepository.findById(userId).get().getReceipts().stream()
                .map(receiptMapper::mapToDto)
                .toList();
    }

    public Double getUserTotalSpending(Long userId){
        return userRepository.findById(userId).get().getReceipts().getLast().getTotalAmount();
    }

    public User authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User with username '" + username + "' does not exist"));

        if (user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }

}
