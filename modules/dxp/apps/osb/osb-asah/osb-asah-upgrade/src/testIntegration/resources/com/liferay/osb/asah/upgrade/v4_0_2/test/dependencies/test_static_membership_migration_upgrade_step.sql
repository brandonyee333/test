INSERT INTO Channel (id, name) VALUES (1, 'Test Channel');

INSERT INTO Segment (id, channelId, name, type) VALUES (12345, 1, 'Test Static Segment', 'STATIC');
INSERT INTO Segment (id, channelId, name, type) VALUES (67890, 1, 'Test Dynamic Segment', 'DYNAMIC');