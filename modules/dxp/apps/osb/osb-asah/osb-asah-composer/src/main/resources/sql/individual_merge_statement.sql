MERGE INTO
	`{{ dag.default_args['ac_project_id'] }}.individual` AS replica
USING
	(
		WITH BQUserExpandoFields AS (
			SELECT
				expandoValue.dataSourceId,
				user.emailAddress,
				user.modifiedDate,
				CASE
					WHEN
						expandoColumn.displayType IN ('checkbox', 'radio', 'selection-list')
					THEN
						CONCAT(expandoColumn.name, '_', expandoColumn.dataType, '_array')
					ELSE
						CONCAT(expandoColumn.name, '_', expandoColumn.dataType)
				END name,
				expandoValue.value
			FROM
				`{{ dag.default_args['ac_project_id'] }}.expandovalue` AS expandoValue
			INNER JOIN
				`{{ dag.default_args['ac_project_id'] }}.expandocolumn` AS expandoColumn
			ON
				expandoValue.columnId = expandoColumn.columnId
			INNER JOIN
				`{{ dag.default_args['ac_project_id'] }}.user` AS user
			ON
				expandoValue.classPK = CAST(user.dxpUserId AS STRING)
			WHERE
				value IS NOT NULL AND value != ''
		),
		BQUserFields AS (
			SELECT
				* EXCEPT(fields)
			FROM
				`{{ dag.default_args['ac_project_id'] }}.user`,
				UNNEST(fields)
		)

		SELECT
			(
				SELECT
					SAFE_CAST(value AS STRING)
				FROM
					UNNEST(stagingFields)
				WHERE
					name = 'firstName'
			) AS firstName,
			emailAddress,
			(
				SELECT
					SAFE_CAST(value AS STRING)
				FROM
					UNNEST(stagingFields)
				WHERE
					name = 'jobTitle'
			) AS jobTitle,
			(
                SELECT
                    SAFE_CAST(value AS STRING)
                FROM
                    UNNEST(stagingFields)
                WHERE
                    name = 'lastName'
            ) AS lastName,
            (
                SELECT
                    SAFE_CAST(value AS STRING)
                FROM
                    UNNEST(stagingFields)
                WHERE
                    name = 'middleName'
            ) AS middleName,
			modifiedDate,
			(
                SELECT
                    SAFE_CAST(value AS STRING)
                FROM
                    UNNEST(stagingFields)
                WHERE
                    name = 'screenName'
            ) AS screenName,
			ARRAY(
				SELECT AS STRUCT
					dataSourceId,
					name,
					value
				FROM
					UNNEST(stagingFields)
			) AS stagingFields
		FROM (
			SELECT
				emailAddress,
				MAX(modifiedDate) AS modifiedDate,
				ARRAY_AGG(
					STRUCT(dataSourceId, name, value)
				) AS stagingFields
			FROM (
				SELECT
					dataSourceId,
					emailAddress,
					modifiedDate,
					name,
					value,
					ROW_NUMBER() OVER(
						PARTITION BY
							emailAddress,
							name
						ORDER BY
							modifiedDate DESC
					) AS rowNumber
				FROM (
					SELECT
						dataSourceId,
						emailAddress,
						modifiedDate,
						name,
						value
					FROM
						BQUserExpandoFields
					UNION ALL
					SELECT
						dataSourceId,
						emailAddress,
						modifiedDate,
						name,
						value
					FROM
						BQUserFields
				)
			)
			WHERE
				rowNumber = 1
			GROUP BY
				emailAddress
		)
	) AS staging
ON
	LOWER(replica.emailAddress) = staging.emailAddress
WHEN MATCHED THEN
	UPDATE SET
		replica.firstName = staging.firstName,
		replica.fields = staging.stagingFields,
		replica.jobTitle = staging.jobTitle,
		replica.lastName = staging.lastName,
		replica.middleName = staging.middleName,
		replica.modifiedDate = staging.modifiedDate,
		replica.screenName = staging.screenName
WHEN NOT MATCHED BY TARGET THEN
	INSERT(
		`createDate`,
		`emailAddress`,
		`firstName`,
		`fields`,
		`id`,
		`jobTitle`,
		`lastName`,
		`middleName`,
		`modifiedDate`,
		`screenName`
	)
	VALUES (
		staging.modifiedDate,
		LOWER(staging.emailAddress),
		staging.firstName,
		staging.stagingFields,
		TO_HEX(SHA256(LOWER(staging.emailAddress))),
		staging.jobTitle,
		staging.lastName,
		staging.middleName,
		staging.modifiedDate,
		staging.screenName
	)
WHEN NOT MATCHED BY SOURCE THEN
	DELETE