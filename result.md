
# user_profile table  -- 4.000.000

# user_access table  -- 6.000.000 

# temp_notification table -- 2.500.000


Version1:
```
INFO 332913 --- [notification] [nio-8080-exec-1] d.w.notification.NotificationService     : Preparing notification table for users is completed in 7992 ms
```

Version2 with truncate:
```
INFO 340390 --- [notification] [nio-8080-exec-1] d.w.notification.NotificationService     : Preparing notification table for users is completed in 7206 ms
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

