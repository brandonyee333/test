INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id) VALUES ('Document', 1, date_trunc('HOUR', timestamp '${now}' ), 'documentPreviewed', '1');
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id) VALUES ('Document', 1, date_trunc('HOUR', timestamp '${now-1d}'), 'documentPreviewed', '2');
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id) VALUES ('Document', 1, date_trunc('HOUR', timestamp '${now-2d}'), 'documentPreviewed', '3');
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id) VALUES ('Document', 1, date_trunc('HOUR', timestamp '${now-6d}'), 'documentPreviewed', '4');
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id) VALUES ('Document', 1, date_trunc('HOUR', timestamp '${now-8d}'), 'documentPreviewed', '5');
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id) VALUES ('Document', 1, date_trunc('HOUR', timestamp '${now-9d}'), 'documentPreviewed', '6');
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id) VALUES ('Document', 1, date_trunc('HOUR', timestamp '${now-12d}'), 'documentPreviewed', '7');
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id) VALUES ('Document', 1, date_trunc('HOUR', timestamp '${now-14d}'), 'documentPreviewed', '8');
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id) VALUES ('Document', 1, date_trunc('HOUR', timestamp '${now-15d}'), 'documentPreviewed', '9');

INSERT INTO BQEventProperty (id, name, value) VALUES ('1', 'fileEntryId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('2', 'fileEntryId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('3', 'fileEntryId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('4', 'fileEntryId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('5', 'fileEntryId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('5', 'fileEntryId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('7', 'fileEntryId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('8', 'fileEntryId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('9', 'fileEntryId', 'e131fabc');