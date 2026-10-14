package dev.wisespirit.notification.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "temp_notification")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TempNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Language userLang;
    private boolean isSent;
    private boolean isSuccess;
}
