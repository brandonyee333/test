MERGE INTO
	`{{ dag.default_args['ac_project_id'] }}.expandovalue` AS replica
USING (
	SELECT
		*
	FROM
	(
		SELECT
			analyticsDeleteMessage.deleted,
			dxpEntity.classPK,
			dxpEntity.dataSourceId,
			dxpEntity.modifiedDate,
			dxpEntity.projectId,
			dxpEntity.type,
			dxpEntity.uploadDate,
			expandoField.columnId,
			expandoField.value,
			ROW_NUMBER() OVER (
				PARTITION BY
					dxpEntity.projectId, dxpEntity.dataSourceId, expandoField.columnId, dxpEntity.classPK
				ORDER BY
					dxpEntity.modifiedDate DESC
			) AS rowNumber,
			TO_HEX(
				SHA256(
					CONCAT(dxpEntity.projectId, '#', dxpEntity.dataSourceId, '#', expandoField.columnId, '#', dxpEntity.classPK)
				)
			) AS sha256HexId

		FROM
			`{{ dag.default_args['ac_project_id'] }}.dxpentity` AS dxpEntity, UNNEST(expandoFields) AS expandoField
		LEFT JOIN (
			SELECT
				*,
				TRUE AS deleted,
				(
					SELECT
						SAFE_CAST(value AS STRING)
					FROM
						UNNEST(fields)
					WHERE
						name = 'classPK'
				) AS entityId,
				(
					SELECT
						SAFE_CAST(value AS STRING)
					FROM
						UNNEST(fields)
					WHERE
						name = 'className'
				) AS className
			FROM
				`{{ dag.default_args['ac_project_id'] }}.dxpentity`
			WHERE
				type = 'com.liferay.analytics.message.storage.model.AnalyticsDeleteMessage'
		) AS analyticsDeleteMessage
		ON
			analyticsDeleteMessage.className = dxpEntity.type AND
			analyticsDeleteMessage.dataSourceId = dxpEntity.dataSourceId AND
			analyticsDeleteMessage.entityId = dxpEntity.classPK AND
			analyticsDeleteMessage.projectId = dxpEntity.projectId
		WHERE
			(
				analyticsDeleteMessage.entityId IS NOT NULL OR
				dxpEntity.uploadDate >=
					{% if prev_start_date_success is not none %}
						'{{ prev_start_date_success }}'
					{% else %}
						'1970-01-01T00:00:00'
					{% endif %}
			) AND
			(
				dxpEntity.type = 'com.liferay.portal.kernel.model.Organization' OR
				dxpEntity.type = 'com.liferay.portal.kernel.model.User'
			)
	)
	WHERE
		rowNumber = 1
) AS staging

ON
	staging.classPK = replica.classPK AND
	staging.dataSourceId = replica.dataSourceId AND
	staging.columnId = replica.columnId AND
	staging.projectId = replica.projectId
WHEN MATCHED AND staging.deleted IS NULL THEN
	UPDATE SET
		replica.value = staging.value
WHEN MATCHED AND staging.deleted = true THEN
	DELETE
WHEN NOT MATCHED BY TARGET AND staging.deleted IS NULL THEN
	INSERT (
		`classPK`,
		`classType`,
		`columnId`,
		`dataSourceId`,
		`id`,
		`projectId`,
		`value`
	)
	VALUES (
		staging.classPK,
		staging.type,
		staging.columnId,
		staging.dataSourceId,
		staging.sha256HexId,
		staging.projectId,
		staging.value
	)