INSERT INTO BQEvent (applicationId, canonicalUrl, channelId, eventDate, eventId, id) VALUES ('Blog', 'https://www.beryl.com/products/commercial/irrigation/FF-2100', 1, date_trunc('HOUR', timestamp '${now-5d}' ), 'blogViewed', '1');
INSERT INTO BQEvent (applicationId, canonicalUrl, channelId, eventDate, eventId, id) VALUES ('Blog', 'https://should.not.return.com', 1, date_trunc('HOUR', timestamp '${now-8d}'), 'blogViewed', '2' );
INSERT INTO BQEvent (applicationId, canonicalUrl, channelId, eventDate, eventId, id) VALUES ('Blog', 'https://www.beryl.com/products/commercial/irrigation', 1, date_trunc('HOUR', timestamp '${today-4h}'), 'blogViewed', '3');
INSERT INTO BQEvent (applicationId, canonicalUrl, channelId, eventDate, eventId, id) VALUES ('Blog', 'https://www.beryl.com/delivery', 1, date_trunc('HOUR', timestamp '${today-6h}'), 'blogViewed', '4');

INSERT INTO BQEventProperty (id, name, value) VALUES ('1', 'entryId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('2', 'entryId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('3', 'entryId', 'e131fabc');
INSERT INTO BQEventProperty (id, name, value) VALUES ('4', 'entryId', 'e131fabc');