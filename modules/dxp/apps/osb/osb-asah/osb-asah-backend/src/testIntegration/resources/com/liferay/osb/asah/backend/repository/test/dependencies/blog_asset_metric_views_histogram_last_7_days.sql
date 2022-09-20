INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id) VALUES ('Blog', 1, date_trunc('HOUR', timestamp '${today-4h}' ), 'blogViewed', '1');
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id) VALUES ('Blog', 1, date_trunc('HOUR', timestamp '${today-4h}'), 'blogViewed', '2' );
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id) VALUES ('Blog', 1, date_trunc('HOUR', timestamp '${today-6h}'), 'blogViewed', '3');

INSERT INTO BQEventProperty (id, name, value) VALUES ('1', 'entryId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('2', 'entryId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('3', 'entryId', 'e131fabc');