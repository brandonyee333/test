INSERT INTO BlockedEventDefinition(eventDefinitionId, hidden, lastSeenDate, lastSeenURL) VALUES (1001, true, now(), 'http://test.com');
INSERT INTO BlockedEventDefinition(eventDefinitionId, hidden, lastSeenDate, lastSeenURL) VALUES (1002, true, now(), 'http://test.com');
INSERT INTO BlockedEventDefinition(eventDefinitionId, hidden, lastSeenDate, lastSeenURL) VALUES (1003, true, now(), 'http://test.com');

INSERT INTO EventDefinition(id, blocked, description, displayName, name, type) VALUES (1001, true, 'Add notification of user activity', 'Add Notification', 'addNotification', 'CUSTOM');
INSERT INTO EventDefinition(id, blocked, description, displayName, name, type) VALUES (1002, true, 'Subscribed to user', 'Subscribed', 'subscribed', 'CUSTOM');
INSERT INTO EventDefinition(id, blocked, description, displayName, name, type) VALUES (1003, true, 'Unsubscribed from user', 'Unsubscribed', 'unsubscribed', 'CUSTOM');