package com.example.rmg.usecase.category.common;

public interface UseCase<IN, OUT> {

    OUT execute(IN input);
}
