package dev.wisespirit.notification;

import dev.wisespirit.notification.repository.TempNotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Slf4j
@Service
public class NotificationService {

    private final TempNotificationRepository notificationRepository;
    /*private Executor executor = Executors
            .newFixedThreadPool(2);*/

    public NotificationService(TempNotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Transactional
    public void prepareNotificationTableV1(){
        LocalDate monthAgo = LocalDate.now().minusMonths(1);
        log.info("Preparing notification table for users");
        long start = System.currentTimeMillis();
        notificationRepository.deleteDatedNotification(monthAgo);

        notificationRepository.insertFreshNotifications(monthAgo);
        long end = System.currentTimeMillis();
        log.info("Preparing notification table for users is completed in {} ms", (end - start));
    }

    @Transactional
    public void prepareNotificationTableV2(){
        LocalDate monthAgo = LocalDate.now().minusMonths(1);
        log.info("Preparing notification table for users");
        long start = System.currentTimeMillis();
        notificationRepository.truncateNotificationTable();

        notificationRepository.insertNotifications(monthAgo);
        long end = System.currentTimeMillis();
        log.info("Preparing notification table for users is completed in {} ms", (end - start));
    }
}
