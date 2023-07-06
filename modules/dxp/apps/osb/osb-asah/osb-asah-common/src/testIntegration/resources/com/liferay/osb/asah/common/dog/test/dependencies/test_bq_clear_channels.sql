INSERT INTO Session (channelId, id) VALUES (1, '366909399944213421');
INSERT INTO Session (channelId, id) VALUES (3, '366909399944215919');

INSERT INTO Event (applicationId, channelId, eventDate, eventId, id, sessionId, userId) VALUES ('CustomEvent', 1, '2021-05-14', 'testEvent1', '1', '366909399944213421', '1');
INSERT INTO Event (applicationId, channelId, eventDate, eventId, id, sessionId, userId) VALUES ('CustomEvent', 3, '2021-05-15', 'testEvent2', '2', '366909399944215919', '1');
