INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id) VALUES ('Document', 1, date_trunc('HOUR', timestamp '${today-4h}' ), 'documentPreviewed', '1');
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id) VALUES ('Document', 1, date_trunc('HOUR', timestamp '${today-4h}'), 'documentPreviewed', '2' );
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id) VALUES ('Document', 1, date_trunc('HOUR', timestamp '${today-6h}'), 'documentPreviewed', '3');

INSERT INTO BQEventProperty (id, name, value) VALUES ('1', 'fileEntryId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('2', 'fileEntryId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('3', 'fileEntryId', 'e131fabc');