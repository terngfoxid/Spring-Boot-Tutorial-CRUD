package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TestObjectRepository extends JpaRepository<TestObject, Long> {
    List<TestObject> findByNameContaining(String name);
}
