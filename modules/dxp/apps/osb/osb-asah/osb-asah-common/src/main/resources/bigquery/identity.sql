SELECT
	id,
	MAX(individualId) AS individualId
FROM
	`$[AC_PROJECT_ID].identity_raw`
GROUP BY
	id