INSERT INTO Event (id, applicationId, channelId, createDate, dataSourceId, eventDate, eventId, sessionId, userId) VALUES ('analyticsEventId1', 'Custom', 10001, timestamp  '${now}', 1, timestamp '${now}', 'addedToCart', '6789', 'userId1');
INSERT INTO Event (id, applicationId, channelId, createDate, dataSourceId, eventDate, eventId, sessionId, userId) VALUES ('analyticsEventId2', 'Custom', 10001, timestamp '${now}', 1,  timestamp '${now}', 'addedToCart', '6790', 'userId2');

INSERT INTO EventProperty (eventDate, id, name, value) VALUES (timestamp '${now}', 'analyticsEventId1', 'itemPrice', '150.00');
INSERT INTO EventProperty (eventDate, id, name, value) VALUES (timestamp '${now}', 'analyticsEventId2', 'itemPrice', '275.00');