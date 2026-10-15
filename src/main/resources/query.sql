CREATE TABLE user_profile (
                              id              BIGSERIAL PRIMARY KEY,
                              name            VARCHAR(255),
                              phone_number    VARCHAR(50),
                              lang            VARCHAR(20)
);

CREATE TABLE user_access (
                             id                      BIGSERIAL PRIMARY KEY,
                             user_id                 BIGINT NOT NULL,
                             access_token_expire     TIMESTAMP,

                             CONSTRAINT fk_user_access_user
                                 FOREIGN KEY (user_id)
                                     REFERENCES user_profile (id)
                                     ON DELETE CASCADE
);

CREATE TABLE temp_notification (
                                   id          BIGSERIAL PRIMARY KEY,
                                   user_id     BIGINT NOT NULL,
                                   user_lang   VARCHAR(20),
                                   is_sent     BOOLEAN NOT NULL DEFAULT FALSE,
                                   is_success  BOOLEAN NOT NULL DEFAULT FALSE
);



-- fake data generation queries
INSERT INTO user_profile (name, phone_number, lang)
SELECT
    'User_' || g AS name,
    '+9989' || lpad((random() * 99999999)::int::text, 8, '0') AS phone_number,
    (ARRAY['UZ', 'EN', 'RU'])[1 + floor(random() * 3)] AS lang
FROM generate_series(1, 3000000) AS g;



INSERT INTO user_access (user_id, access_token_expire)
SELECT
    (1 + floor(random() * 3000000))::bigint AS user_id,
    CASE
        WHEN random() < 0.4 THEN NOW() - (random() * INTERVAL '25 days')
        ELSE NOW() - INTERVAL '40 days' - (random() * INTERVAL '80 days')         
END AS access_token_expire
FROM generate_series(1, 5000000);


INSERT INTO temp_notification (user_id, user_lang, is_sent, is_success)
SELECT user_id, user_lang, false, false
FROM (
         SELECT DISTINCT
             up.id AS user_id,
             COALESCE(up.lang, 'UZ') AS user_lang
         FROM user_profile up
                  INNER JOIN user_access ua ON up.id = ua.user_id
         WHERE ua.access_token_expire >= NOW() - INTERVAL '30 days'
     ) t
ORDER BY random()
    LIMIT 400000;

INSERT INTO temp_notification (user_id, user_lang, is_sent, is_success)
SELECT user_id, user_lang, false, false
FROM (
         SELECT DISTINCT
             up.id AS user_id,
             COALESCE(up.lang, 'UZ') AS user_lang
         FROM user_profile up
         WHERE NOT EXISTS (
             SELECT 1 FROM temp_notification tn WHERE tn.user_id = up.id
         )
           AND EXISTS (
             SELECT 1
             FROM user_access ua
             WHERE ua.user_id = up.id
               AND ua.access_token_expire < NOW() - INTERVAL '30 days'
         )
     ) t
ORDER BY random()
    LIMIT 400000;

