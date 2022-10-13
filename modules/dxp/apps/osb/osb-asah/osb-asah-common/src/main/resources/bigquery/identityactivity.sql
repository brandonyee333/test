SELECT
	IdentityActivity.channelId,
	IdentityActivity.createDate,
	IdentityActivity.datasourceId,
	MAX(Identity.id) as identityId,
	MAX(Identity.individualId) as individualId
FROM
	`$[AC_PROJECT_ID].identityactivity_raw` AS IdentityActivity
INNER JOIN
	`$[AC_PROJECT_ID].identity_raw` Identity ON
		IdentityActivity.identityId = Identity.id
GROUP BY
	IdentityActivity.channelId,
	IdentityActivity.createDate,
	IdentityActivity.dataSourceId