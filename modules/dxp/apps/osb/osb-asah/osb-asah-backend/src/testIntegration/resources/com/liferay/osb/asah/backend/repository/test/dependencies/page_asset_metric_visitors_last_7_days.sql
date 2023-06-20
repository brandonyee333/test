INSERT INTO PageDaily (canonicalUrl, channelId, eventDate, title, userId, views) VALUES ('https://www.beryl.com/delivery', 1, TIMESTAMP(DATETIME_TRUNC(timestamp '${today-2d}', HOUR)), 'Beryl Delivery', '12ec1ae5-d9da-474b-8d64-42096e8109f2', 1);
INSERT INTO PageDaily (canonicalUrl, channelId, eventDate, title, userId, views) VALUES ('https://www.beryl.com/delivery', 1, TIMESTAMP(DATETIME_TRUNC(timestamp '${today-4d}', HOUR)), 'Beryl Delivery', 'de2ccc5b-ccf4-44f0-87a5-c7e6ad22e977', 1);
INSERT INTO PageDaily (canonicalUrl, channelId, eventDate, title, userId, views) VALUES ('https://www.beryl.com/delivery', 1, TIMESTAMP(DATETIME_TRUNC(timestamp '${today-6d}', HOUR)), 'Beryl Delivery', '9b739f5a-76eb-4199-ba58-53d5dcaa3ec5', 1);

INSERT INTO Identity_Raw (id, individualId) VALUES ('12ec1ae5-d9da-474b-8d64-42096e8109f2', '2000');
INSERT INTO Identity_Raw (id, individualId) VALUES ('de2ccc5b-ccf4-44f0-87a5-c7e6ad22e977', '2000');
INSERT INTO Identity_Raw (id, individualId) VALUES ('9b739f5a-76eb-4199-ba58-53d5dcaa3ec5', '2001');

INSERT INTO Individual (id) VALUES ('2000');