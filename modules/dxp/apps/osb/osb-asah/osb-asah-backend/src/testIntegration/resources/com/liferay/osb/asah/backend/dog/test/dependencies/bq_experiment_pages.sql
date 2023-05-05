INSERT INTO Identity_Raw(id, individualId) VALUES ('1', '1');
INSERT INTO Identity_Raw(id, individualId) VALUES ('2', '2');
INSERT INTO Identity_Raw(id, individualId) VALUES ('3', '3');
INSERT INTO Identity_Raw(id, individualId) VALUES ('4', '4');
INSERT INTO Identity_Raw(id, individualId) VALUES ('5', '5');
INSERT INTO Identity_Raw(id, individualId) VALUES ('6', '6');

INSERT INTO PageDaily (bounce, canonicalUrl, ctaClicks, eventDate, experimentId, sessionId, title, userId, variantId, views) VALUES (0, 'http://192.168.108.90:8080/', 1, TIMESTAMP '${today-2dT10:40:00.000Z}', 1, '10', 'Page 1', '1', '1', 1);
INSERT INTO PageDaily (bounce, canonicalUrl, ctaClicks, eventDate, experimentId, sessionId, title, userId, variantId, views) VALUES (0, 'http://192.168.108.90:8080/', 1, TIMESTAMP '${today-2dT11:00:00.000Z}', 1, '10', 'Page 1', '1', '1', 1);
INSERT INTO PageDaily (bounce, canonicalUrl, ctaClicks, eventDate, experimentId, sessionId, title, userId, variantId, views) VALUES (1, 'http://192.168.108.90:8080/', 1, TIMESTAMP '${today-2dT10:00:00.000Z}', 1, '20', 'Page 1', '2', '1', 1);
INSERT INTO PageDaily (bounce, canonicalUrl, ctaClicks, eventDate, experimentId, sessionId, title, userId, variantId, views) VALUES (0, 'http://192.168.108.90:8080/', 1, TIMESTAMP '${today-2dT11:50:00.000Z}', 1, '21', 'Page 1', '2', '1', 1);
INSERT INTO PageDaily (bounce, canonicalUrl, ctaClicks, eventDate, experimentId, sessionId, title, userId, variantId, views) VALUES (0, 'http://192.168.108.90:8080/', 1, TIMESTAMP '${today-2dT12:15:00.000Z}', 1, '21', 'Page 1', '2', '1', 1);
INSERT INTO PageDaily (bounce, canonicalUrl, ctaClicks, eventDate, experimentId, sessionId, title, userId, variantId, views) VALUES (1, 'http://192.168.108.90:8080/', 1, TIMESTAMP '${today-2dT11:20:00.000Z}', 1, '30', 'Page 1', '3', '2', 1);
INSERT INTO PageDaily (bounce, canonicalUrl, ctaClicks, eventDate, experimentId, sessionId, title, userId, variantId, views) VALUES (1, 'http://192.168.108.90:8080/', 1, TIMESTAMP '${today-1dT12:00:00.000Z}', 1, '40', 'Page 1', '4', '2', 1);
INSERT INTO PageDaily (bounce, canonicalUrl, ctaClicks, eventDate, experimentId, sessionId, title, userId, variantId, views) VALUES (1, 'http://192.168.108.90:8080/', 1, TIMESTAMP '${today-1dT12:50:00.000Z}', 1, '50', 'Page 1', '5', '1', 1);
INSERT INTO PageDaily (bounce, canonicalUrl, ctaClicks, eventDate, experimentId, sessionId, title, userId, variantId, views) VALUES (1, 'http://192.168.108.90:8080/', 1, TIMESTAMP '${today-1dT12:50:00.000Z}', 1, '41', 'Page 1', '4', '2', 1);
INSERT INTO PageDaily (bounce, canonicalUrl, ctaClicks, eventDate, experimentId, sessionId, title, userId, variantId, views) VALUES (1, 'http://192.168.108.90:8080/', 1, TIMESTAMP '${now!-2h}', 1, '11', 'Page 1', '1', '1', 1);
INSERT INTO PageDaily (bounce, canonicalUrl, ctaClicks, eventDate, experimentId, sessionId, title, userId, variantId, views) VALUES (1, 'http://192.168.108.90:8080/', 1, TIMESTAMP '${now!-1h}', 1, '51', 'Page 1', '5', '2', 1);
INSERT INTO PageDaily (bounce, canonicalUrl, ctaClicks, eventDate, experimentId, sessionId, title, userId, variantId, views) VALUES (1, 'http://192.168.108.90:8080/', 1, TIMESTAMP '${now}', 2, '60', 'Page 1', '6', '2', 1);