INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id) VALUES ('WebContent', 1, date_trunc('HOUR', timestamp '${now}' ), 'webContentViewed', '1');
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id) VALUES ('WebContent', 1, date_trunc('HOUR', timestamp '${now-1d}'), 'webContentViewed', '2' );
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id) VALUES ('WebContent', 1, date_trunc('HOUR', timestamp '${now-2d}'), 'webContentViewed', '3');
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id) VALUES ('WebContent', 1, date_trunc('HOUR', timestamp '${now-6d}'), 'webContentViewed', '4');
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id) VALUES ('WebContent', 1, date_trunc('HOUR', timestamp '${now-8d}'), 'webContentViewed', '5');
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id) VALUES ('WebContent', 1, date_trunc('HOUR', timestamp '${now-9d}'), 'webContentViewed', '6');
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id) VALUES ('WebContent', 1, date_trunc('HOUR', timestamp '${now-12d}'), 'webContentViewed', '7');
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id) VALUES ('WebContent', 1, date_trunc('HOUR', timestamp '${now-14d}'), 'webContentViewed', '8');
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id) VALUES ('WebContent', 1, date_trunc('HOUR', timestamp '${now-15d}'), 'webContentViewed', '9');

INSERT INTO BQEventProperty (id, name, value) VALUES ('1', 'articleId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('2', 'articleId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('3', 'articleId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('4', 'articleId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('5', 'articleId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('5', 'articleId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('7', 'articleId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('8', 'articleId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('9', 'articleId', 'e131fabc');