package ua.gov.mkip.saveheritage.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.gov.mkip.saveheritage.services.RecordService;
import ua.gov.mkip.saveheritage.models.Record;

@Component
@RequiredArgsConstructor
public class RecordValidator implements Validator {
    public final RecordService recordService;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Record recors = (Record) target;

    }
}
