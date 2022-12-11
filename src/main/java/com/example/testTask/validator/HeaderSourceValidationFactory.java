package com.example.testTask.validator;

import com.example.testTask.domain.HeaderSourceType;
import com.example.testTask.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class HeaderSourceValidationFactory implements ValidationFactory {

    private final List<HeaderSourceValidator> validators;

    private Map<HeaderSourceType, HeaderSourceValidator> validatorMap;

    @PostConstruct
    public void init() {
        validatorMap = validators.stream()
                .collect(Collectors.toMap(HeaderSourceValidator::getType, Function.identity()));
    }

    @Override
    public HeaderSourceValidator getValidator(String source) {
        if (!StringUtils.hasText(source)) {
            throw new IllegalArgumentException("Type of source cannot be empty!");
        }
        return Optional.ofNullable(validatorMap.get(HeaderSourceType.getBySourceName(source)))
                .orElseThrow(() -> new ValidationException("This type of source (header) doesn't support!"));
    }
}
