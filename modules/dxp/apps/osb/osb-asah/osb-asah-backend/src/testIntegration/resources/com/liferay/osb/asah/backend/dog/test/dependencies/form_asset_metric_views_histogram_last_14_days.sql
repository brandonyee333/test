INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id) VALUES ('Form', 1, date_trunc('HOUR', timestamp '${now}' ), 'formViewed', '1');
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id) VALUES ('Form', 1, date_trunc('HOUR', timestamp '${now-1d}'), 'formViewed', '2' );
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id) VALUES ('Form', 1, date_trunc('HOUR', timestamp '${now-2d}'), 'formViewed', '3');
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id) VALUES ('Form', 1, date_trunc('HOUR', timestamp '${now-6d}'), 'formViewed', '4');
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id) VALUES ('Form', 1, date_trunc('HOUR', timestamp '${now-8d}'), 'formViewed', '5');
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id) VALUES ('Form', 1, date_trunc('HOUR', timestamp '${now-9d}'), 'formViewed', '6');
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id) VALUES ('Form', 1, date_trunc('HOUR', timestamp '${now-12d}'), 'formViewed', '7');
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id) VALUES ('Form', 1, date_trunc('HOUR', timestamp '${now-14d}'), 'formViewed', '8');
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id) VALUES ('Form', 1, date_trunc('HOUR', timestamp '${now-15d}'), 'formViewed', '9');

INSERT INTO BQEventProperty (id, name, value) VALUES ('1', 'formId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('2', 'formId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('3', 'formId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('4', 'formId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('5', 'formId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('5', 'formId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('7', 'formId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('8', 'formId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('9', 'formId', 'e131fabc');