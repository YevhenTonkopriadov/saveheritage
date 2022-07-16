package ua.gov.mkip.craft.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ua.gov.mkip.craft.models.Record;
import ua.gov.mkip.craft.models.User;
import ua.gov.mkip.craft.models.UserCraftsMan;
import ua.gov.mkip.craft.models.enums.Role;
import ua.gov.mkip.craft.repositories.RecordRepository;
import ua.gov.mkip.craft.repositories.UserRepository;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class RecordService {


    private final RecordRepository recordRepository;
    private final UserRepository userRepository;

    public Iterable<Record> findAll() {
        User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user.getRoles().contains(Role.ADMIN)) {
            return recordRepository.findAll();
        } else return recordRepository.findByUser(user);
    }

    public Optional<Record> findOne(Long recordID) {
        return recordRepository.findById(recordID);
    }

    public void save(Record record) {
        recordRepository.save(record);
    }

    public boolean delete(Long id) {
        recordRepository.deleteById(id);
        return true;
    }

    public Iterable<Record>  findAllByUserId(Long userId) {
        User userCraftsMan = userRepository.findById(userId).get();
        if (((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getRoles().contains(Role.ADMIN)||
                userCraftsMan.getRoles().contains(Role.USER)||
                        userCraftsMan.getRoles().contains(Role.USERADOPED)){
            return recordRepository.findByUser(userRepository.findById(userId).get());
        }
        return null;
    }
}
