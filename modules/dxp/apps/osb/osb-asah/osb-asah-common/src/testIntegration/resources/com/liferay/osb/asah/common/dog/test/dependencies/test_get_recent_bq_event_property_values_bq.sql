INSERT INTO Event (applicationId, channelId, createDate, dataSourceId, eventDate, eventId, id, sessionId, userId) VALUES ('Page', 12345, timestamp '${today-1d}', 1, timestamp '${today-1d}', 'pageUnloaded', 'analyticsEventId1', 'sessionId', 'userId');
INSERT INTO Event (applicationId, channelId, createDate, dataSourceId, eventDate, eventId, id, sessionId, userId) VALUES ('Page', 12345, timestamp '${today}', 1, timestamp '${today}', 'pageUnloaded', 'analyticsEventId2', 'sessionId', 'userId');

INSERT INTO EventProperty (eventDate, id, name, value) VALUES (timestamp '${today-1d}', 'analyticsEventId1', 'testAttribute1', 'testValue1');
INSERT INTO EventProperty (eventDate, id, name, value) VALUES (timestamp '${today}', 'analyticsEventId2', 'testAttribute1', 'testValue2');