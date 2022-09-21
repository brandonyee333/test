INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id) VALUES ('Form', 1, date_trunc('HOUR', timestamp '${today-4h}' ), 'formViewed', '1');
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id) VALUES ('Form', 1, date_trunc('HOUR', timestamp '${today-4h}'), 'formViewed', '2' );
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id) VALUES ('Form', 1, date_trunc('HOUR', timestamp '${today-6h}'), 'formViewed', '3');

INSERT INTO BQEventProperty (id, name, value) VALUES ('1', 'formId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('2', 'formId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('3', 'formId', 'e131fabc');