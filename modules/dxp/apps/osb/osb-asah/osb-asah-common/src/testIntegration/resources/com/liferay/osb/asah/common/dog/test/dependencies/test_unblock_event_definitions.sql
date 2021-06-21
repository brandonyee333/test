INSERT INTO EventDefinition(blocked, description, displayName, name, type) VALUES (true, null, null, 'addedToCart', 'CUSTOM');
INSERT INTO EventDefinition(blocked, description, displayName, name, type) VALUES (true, null, null, 'addedToWishList', 'CUSTOM');
INSERT INTO EventDefinition(blocked, description, displayName, name, type) VALUES (true, null, null, 'checkedOut', 'CUSTOM');
INSERT INTO EventDefinition(blocked, description, displayName, name, type) VALUES (true, null, null, 'orderCancelled', 'CUSTOM');
INSERT INTO EventDefinition(blocked, description, displayName, name, type) VALUES (false, null, 'Remove From Cart', 'removedFromCart', 'CUSTOM');
INSERT INTO EventDefinition(blocked, description, displayName, name, type) VALUES (true, null, null, 'reviewAdded', 'CUSTOM');

INSERT INTO BlockedEventDefinition(eventDefinitionId, lastSeenDate, lastSeenURL) VALUES ((SELECT id FROM EventDefinition WHERE name = 'addedToCart'), '2020-12-27 03:03:00-07', 'http://test.com');
INSERT INTO BlockedEventDefinition(eventDefinitionId, lastSeenDate, lastSeenURL) VALUES ((SELECT id FROM EventDefinition WHERE name = 'addedToWishList'), '2021-01-03 18:10:00-07', 'http://test.com');
INSERT INTO BlockedEventDefinition(eventDefinitionId, lastSeenDate, lastSeenURL) VALUES ((SELECT id FROM EventDefinition WHERE name = 'checkedOut'), '2021-03-03 15:02:00-07', 'http://test.com');
INSERT INTO BlockedEventDefinition(eventDefinitionId, lastSeenDate, lastSeenURL) VALUES ((SELECT id FROM EventDefinition WHERE name = 'orderCancelled'), '2021-03-21 20:45:00-07', 'http://test.com');
INSERT INTO BlockedEventDefinition(eventDefinitionId, lastSeenDate, lastSeenURL) VALUES ((SELECT id FROM EventDefinition WHERE name = 'reviewAdded'), '2021-03-11 17:11:00-07', 'http://test.com');