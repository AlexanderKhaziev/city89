package ru.city.city89.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.city.city89.domain.Message;

public interface MessageRepo extends JpaRepository<Message, Long> {
}
