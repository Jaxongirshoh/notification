package dev.wisespirit.notification.repository;

import dev.wisespirit.notification.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile,Long> {

}
