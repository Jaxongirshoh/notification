package dev.wisespirit.notification.repository;

import dev.wisespirit.notification.entity.UserAccess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccessRepository extends JpaRepository<UserAccess, Long> {
}
