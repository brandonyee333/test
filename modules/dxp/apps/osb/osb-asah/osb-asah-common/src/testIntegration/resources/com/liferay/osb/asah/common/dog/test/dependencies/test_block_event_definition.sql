INSERT INTO Channel(createDate, name) VALUES('2021-04-19 00:00:00-07', 'testChannelName');

INSERT INTO EventDefinition(blocked, description, displayName, name, type) VALUES(false, 'Subscribed to user', 'Subscribed', 'subscribed', 'CUSTOM');

INSERT INTO Event(canonicalUrl, channelId, eventDate, eventDefinitionId) VALUES('http://localhost:8080/web/guest/home', (SELECT id from Channel WHERE name='testChannelName'), '2021-04-19 00:00:00', (SELECT id from EventDefinition WHERE name='subscribed'));
INSERT INTO Event(canonicalUrl, channelId, eventDate, eventDefinitionId) VALUES('http://localhost:8089/web/guest/home', (SELECT id from Channel WHERE name='testChannelName'), '2021-04-19 12:35:00', (SELECT id from EventDefinition WHERE name='subscribed'));