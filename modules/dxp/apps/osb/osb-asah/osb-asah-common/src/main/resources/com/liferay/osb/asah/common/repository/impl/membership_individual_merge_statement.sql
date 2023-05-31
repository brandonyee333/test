MERGE INTO
	`${ac_project_id}.membershipindividual` AS target
USING
	(
		WITH MembershipIndividual AS (
		    SELECT
				individualId, modifiedDate, segmentId
			FROM
				`${ac_project_id}.membership` as Membership
			WHERE
				individualId IS NOT NULL
			GROUP BY
				individualId, modifiedDate, segmentId
		)
		SELECT
			ARRAY_AGG(
				(
					SELECT AS STRUCT
						User.dataSourceId AS dataSourceId,
						User.uuid as uuid
				)
			) AS dataSourceUser
			MembershipIndividual.individualId,
			MAX(MembershipIndividual.modifiedDate) AS modifiedDate,
			MembershipIndividual.segmentId
		FROM
			MembershipIndividual
		JOIN `${ac_project_id}.user` AS User ON (
			MembershipIndividual.individualId = User.individualId
		)
		WHERE
			User.uuid != ''
		GROUP BY
			individualId, segmentId
	) AS source
ON (
	source.individualId = target.individualId AND
	source.segmentId = target.segmentId
)
WHEN NOT MATCHED BY TARGET THEN
	INSERT (
		`dataSourceId`,
		`individualId`,
		`modifiedDate`,
		`segmentId`,
		`uuid`
	)
	VALUES (
	   source.dataSourceId,
	   source.individualId,
	   source.modifiedDate,
	   source.segmentId,
	   source.uuid
   )
WHEN NOT MATCHED BY SOURCE THEN
	DELETE