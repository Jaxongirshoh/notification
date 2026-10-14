package dev.wisespirit.notification;

import dev.wisespirit.notification.repository.TempNotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Service
public class NotificationService {

    private final TempNotificationRepository notificationRepository;
    private Executor executor = Executors
            .newFixedThreadPool(2);

    public NotificationService(TempNotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void prepareNotificationTableV1(){
        LocalDate monthAgo = LocalDate.now().minusMonths(1);
        notificationRepository.deleteDatedNotification(monthAgo);

        notificationRepository.insertFreshNotifications(monthAgo);
    }

    public void prepareNotificationTableV2(){

    }
}
