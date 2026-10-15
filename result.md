
# user_profile table  -- 4.000.000

# user_access table  -- 6.000.000 

# temp_notification table -- 2.500.000


Version1:
```
INFO 332913 --- [notification] [nio-8080-exec-1] d.w.notification.NotificationService     : Preparing notification table for users is completed in 7992 ms
INFO 356815 --- [notification] [nio-8080-exec-1] d.w.notification.NotificationService     : Preparing notification table for users is completed in 5705 ms
INFO 360030 --- [notification] [nio-8080-exec-1] d.w.notification.NotificationService     : Preparing notification table for users is completed in 5064 ms
INFO 362404 --- [notification] [nio-8080-exec-1] d.w.notification.NotificationService     : Preparing notification table for users is completed in 6496 ms
INFO 367947 --- [notification] [nio-8080-exec-1] d.w.notification.NotificationService     : Preparing notification table for users is completed in 4944 ms
```

Version2 with truncate:
```
INFO 340390 --- [notification] [nio-8080-exec-1] d.w.notification.NotificationService     : Preparing notification table for users is completed in 7206 ms
INFO 346737 --- [notification] [nio-8080-exec-2] d.w.notification.NotificationService     : Preparing notification table for users is completed in 7702 ms
INFO 350120 --- [notification] [nio-8080-exec-1] d.w.notification.NotificationService     : Preparing notification table for users is completed in 6931 ms
INFO 352980 --- [notification] [nio-8080-exec-1] d.w.notification.NotificationService     : Preparing notification table for users is completed in 5599 ms
INFO 365262 --- [notification] [nio-8080-exec-1] d.w.notification.NotificationService     : Preparing notification table for users is completed in 5805 ms
```


Request:
```http request
http localhost:8080/api/notification/prepare
```

Result:
```
HTTP/1.1 200 
Connection: keep-alive
Content-Length: 29
Content-Type: text/plain;charset=UTF-8
Date: Thu, 15 Oct 2026 05:17:53 GMT
Keep-Alive: timeout=60

Preparing process is started!

