package ua.gov.mkip.craft.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ua.gov.mkip.craft.models.Record;
import ua.gov.mkip.craft.models.User;
import ua.gov.mkip.craft.models.enums.Role;
import ua.gov.mkip.craft.repositories.RecordRepository;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class RecordService {


    private final RecordRepository recordRepository;

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

}
