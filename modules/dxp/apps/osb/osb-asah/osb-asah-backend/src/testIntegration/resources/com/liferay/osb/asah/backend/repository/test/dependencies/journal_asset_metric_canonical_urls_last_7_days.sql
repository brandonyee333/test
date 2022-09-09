INSERT INTO BQEvent (applicationId, canonicalUrl, channelId, eventDate, eventId, id) VALUES ('WebContent', 'https://www.beryl.com/products/commercial/irrigation/FF-2100', 1, date_trunc('HOUR', timestamp '${now-5d}' ), 'webContentViewed', '1');
INSERT INTO BQEvent (applicationId, canonicalUrl, channelId, eventDate, eventId, id) VALUES ('WebContent', 'https://should.not.return.com', 1, date_trunc('HOUR', timestamp '${now-8d}'), 'webContentViewed', '2' );
INSERT INTO BQEvent (applicationId, canonicalUrl, channelId, eventDate, eventId, id) VALUES ('WebContent', 'https://www.beryl.com/products/commercial/irrigation', 1, date_trunc('HOUR', timestamp '${today-4h}'), 'webContentViewed', '3');
INSERT INTO BQEvent (applicationId, canonicalUrl, channelId, eventDate, eventId, id) VALUES ('WebContent', 'https://www.beryl.com/delivery', 1, date_trunc('HOUR', timestamp '${today-6h}'), 'webContentViewed', '4');

INSERT INTO BQEventProperty (id, name, value) VALUES ('1', 'articleId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('2', 'articleId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('3', 'articleId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('4', 'articleId', 'e131fabc');