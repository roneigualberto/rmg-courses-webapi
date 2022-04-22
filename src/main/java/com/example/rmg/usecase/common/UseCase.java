package com.example.rmg.usecase.common;

public interface UseCase<IN, OUT> {

    OUT execute(IN input);
}
