MERGE INTO
	`${ac_project_id}.membershipindividual` AS target
USING
	(
		${membership_individuals_select_stmt}
	) AS source
ON (
	source.individualId = target.individualId AND
	source.segmentId = target.segmentId
)
WHEN NOT MATCHED BY TARGET THEN
	INSERT (
		`dataSourceUUIDs`,
		`individualId`,
		`modifiedDate`,
		`segmentId`
	)
	VALUES (
	   source.dataSourceUUIDs,
	   source.individualId,
	   source.modifiedDate,
	   source.segmentId
   )
WHEN NOT MATCHED BY SOURCE THEN
	DELETE