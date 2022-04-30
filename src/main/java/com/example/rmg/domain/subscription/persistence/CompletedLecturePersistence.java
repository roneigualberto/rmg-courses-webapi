package com.example.rmg.domain.subscription.persistence;

import com.example.rmg.domain.common.persistence.GenericPersistence;
import com.example.rmg.domain.course.entity.Lecture;
import com.example.rmg.domain.subscription.entity.CompletedLecture;

import java.util.UUID;

public interface CompletedLecturePersistence extends GenericPersistence<CompletedLecture, UUID> {


    boolean isCompleted(Lecture lecture);
}