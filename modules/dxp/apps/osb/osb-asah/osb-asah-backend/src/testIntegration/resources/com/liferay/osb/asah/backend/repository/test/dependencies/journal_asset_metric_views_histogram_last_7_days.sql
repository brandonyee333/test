INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id) VALUES ('WebContent', 1, date_trunc('HOUR', timestamp '${today-4h}' ), 'webContentViewed', '1');
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id) VALUES ('WebContent', 1, date_trunc('HOUR', timestamp '${today-4h}'), 'webContentViewed', '2' );
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id) VALUES ('WebContent', 1, date_trunc('HOUR', timestamp '${today-6h}'), 'webContentViewed', '3');

INSERT INTO BQEventProperty (id, name, value) VALUES ('1', 'articleId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('2', 'articleId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('3', 'articleId', 'e131fabc');