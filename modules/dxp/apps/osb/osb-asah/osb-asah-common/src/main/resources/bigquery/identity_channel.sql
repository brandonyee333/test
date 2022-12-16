WITH IdentityChannel AS (
	SELECT
		*
	FROM
		`$[AC_PROJECT_ID].identitychannelsummary`
	UNION ALL
	SELECT
		COUNT(*) AS activitiesCount,
		Event.channelId,
		MIN(Event.eventDate) AS createDate,
		TO_HEX(SHA256(Event.channelId || '-' || Event.userId)) AS id,
		Event.userId AS identityId,
		MAX(Identity.individualId) AS individualId,
		MAX(Event.eventDate) AS lastActivityDate,
		MAX(Event.eventDate) AS modifiedDate
	FROM
		`$[AC_PROJECT_ID].event` AS Event
	LEFT JOIN `$[AC_PROJECT_ID].identity` AS Identity ON (
		Event.userId = Identity.id
	)
	WHERE
		Event.eventDate >  TIMESTAMP_TRUNC(CURRENT_TIMESTAMP(), DAY) AND
		Event.eventId IN (
			'blogClicked', 'blogViewed', 'documentDownloaded',
			'documentPreviewed', 'formSubmitted', 'formViewed', 'pageViewed',
			'posted', 'VOTE', 'webContentClicked', 'webContentViewed'
		)
	GROUP BY
		Event.channelId,
		Event.userId
)

SELECT
	SUM(activitiesCount) AS activitiesCount,
	channelId,
	MIN(createDate) AS createDate,
	id,
	identityId,
	MAX(individualId) AS individualId,
	MAX(lastActivityDate) AS lastActivityDate,
	MAX(modifiedDate) AS modifiedDate
FROM
	IdentityChannel
GROUP BY
	channelId, id, identityId