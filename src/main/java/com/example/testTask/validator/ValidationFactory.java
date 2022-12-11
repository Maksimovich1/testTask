package com.example.testTask.validator;

public interface ValidationFactory {
    HeaderSourceValidator getValidator(String source);
}
