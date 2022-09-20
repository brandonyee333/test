INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id) VALUES ('Blog', 1, date_trunc('HOUR', timestamp '${now}' ), 'blogViewed', '1');
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id) VALUES ('Blog', 1, date_trunc('HOUR', timestamp '${now-1d}'), 'blogViewed', '2' );
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id) VALUES ('Blog', 1, date_trunc('HOUR', timestamp '${now-2d}'), 'blogViewed', '3');
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id) VALUES ('Blog', 1, date_trunc('HOUR', timestamp '${now-6d}'), 'blogViewed', '4');
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id) VALUES ('Blog', 1, date_trunc('HOUR', timestamp '${now-8d}'), 'blogViewed', '5');
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id) VALUES ('Blog', 1, date_trunc('HOUR', timestamp '${now-9d}'), 'blogViewed', '6');
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id) VALUES ('Blog', 1, date_trunc('HOUR', timestamp '${now-12d}'), 'blogViewed', '7');
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id) VALUES ('Blog', 1, date_trunc('HOUR', timestamp '${now-14d}'), 'blogViewed', '8');
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id) VALUES ('Blog', 1, date_trunc('HOUR', timestamp '${now-15d}'), 'blogViewed', '9');

INSERT INTO BQEventProperty (id, name, value) VALUES ('1', 'entryId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('2', 'entryId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('3', 'entryId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('4', 'entryId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('5', 'entryId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('5', 'entryId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('7', 'entryId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('8', 'entryId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('9', 'entryId', 'e131fabc');