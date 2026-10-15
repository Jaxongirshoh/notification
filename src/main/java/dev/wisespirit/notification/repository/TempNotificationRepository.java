package dev.wisespirit.notification.repository;

import dev.wisespirit.notification.entity.TempNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface TempNotificationRepository extends JpaRepository<TempNotification,Long> {

    @Modifying
    @Query(value = """
        DELETE FROM temp_notification tn
        WHERE NOT EXISTS (
            SELECT 1
            FROM user_access ua
            WHERE ua.user_id = tn.user_id
              AND ua.access_token_expire >= :oneMonthAgo
        )
        """, nativeQuery = true)
    void deleteDatedNotification(@Param("oneMonthAgo") LocalDate monthAgo);


    @Modifying
    @Query(value = """
        INSERT INTO temp_notification (user_id, user_lang, is_sent, is_success)
        SELECT DISTINCT up.id, COALESCE(up.lang, 'UZ'), false, false
        FROM user_profile up
        INNER JOIN user_access ua ON up.id = ua.user_id
        WHERE ua.access_token_expire >= :oneMonthAgo
          AND NOT EXISTS (
              SELECT 1
              FROM temp_notification tn
              WHERE tn.user_id = up.id
          )
        """, nativeQuery = true)
    void insertFreshNotifications(@Param("oneMonthAgo") LocalDate monthAgo);
}
