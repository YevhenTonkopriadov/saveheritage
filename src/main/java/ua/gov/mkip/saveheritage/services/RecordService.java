package ua.gov.mkip.saveheritage.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ua.gov.mkip.saveheritage.models.Record;
import ua.gov.mkip.saveheritage.models.User;
import ua.gov.mkip.saveheritage.repositories.RecordRepository;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;

    public Iterable<Record> findAllRecordsCurrentUser() {
        return recordRepository.findByUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    public Optional<Record> findOne(Long recordID) {
        return recordRepository.findById(recordID);
    }

    public void save(Record record) {
        recordRepository.save(record);
    }

    public void delete(Long id) {
        recordRepository.deleteById(id);
    }
}
