package com.example.rmg.usecase.category;

public interface UseCase<IN, OUT> {

    OUT execute(IN input);
}
