MERGE INTO
	`{{ dag.default_args['ac_project_id'] }}.user` AS replica
USING
	(
		SELECT
			*
		FROM (
			SELECT
				analyticsDeleteMessage.deleted,
				user.classPK,
				user.dataSourceId,
				user.modifiedDate,
				user.projectId,
				user.type,
				user.uploadDate,
				(
					SELECT
						SAFE_CAST(value AS INT64)
					FROM
						UNNEST(user.fields)
					WHERE
						name = 'accountId'
				) AS accountId,
				(
					SELECT
						SAFE_CAST(TIMESTAMP_MILLIS(CAST(value AS INT64)) AS TIMESTAMP)
					FROM
						UNNEST(user.fields)
					WHERE
						name = 'birthday'
				) AS birthday,
				(
					SELECT
						SAFE_CAST(value AS INT64)
					FROM
						UNNEST(user.fields)
					WHERE
						name = 'classNameId'
				) AS classNameId,
				(
					SELECT
						SAFE_CAST(value AS INT64)
					FROM
						UNNEST(user.fields)
					WHERE
						name = 'contactId'
				) AS contactId,
				(
					SELECT
						SAFE_CAST(TIMESTAMP_MILLIS(CAST(value AS INT64)) AS TIMESTAMP)
					FROM
						UNNEST(user.fields)
					WHERE
						name = 'createDate'
				) AS createDate,
				(
					SELECT
						SAFE_CAST(value AS STRING)
					FROM
						UNNEST(user.fields)
					WHERE
						name = 'emailAddress'
				) AS emailAddress,
				(
					SELECT ARRAY(
						SELECT
							DISTINCT SAFE_CAST(columnId AS STRING)
						FROM
							UNNEST(user.expandoFields)
					)
				) AS expandoColumnIds,
				(
					SELECT
						SAFE_CAST(value AS STRING)
					FROM
						UNNEST(user.fields)
					WHERE
						name = 'firstName'
				) AS firstName,
				(
					SELECT
						ARRAY (
							SELECT
								SAFE_CAST(number AS INT64)
							FROM
								UNNEST(arrayField.groupIds) AS number
						)
					FROM (
						SELECT (
							SELECT
								JSON_EXTRACT_ARRAY(value)
							FROM
								UNNEST(user.fields)
							WHERE
								name = 'groupIds'
						) groupIds
					) arrayField
				) AS groupIds,
				(
					SELECT
						SAFE_CAST(value AS STRING)
					FROM
						UNNEST(user.fields)
					WHERE
						name = 'jobTitle'
				) AS jobTitle,
				(
					SELECT
						SAFE_CAST(value AS STRING)
					FROM
						UNNEST(user.fields)
					WHERE
						name = 'languageId'
				) AS languageId,
				(
					SELECT
						SAFE_CAST(value AS STRING)
					FROM
						UNNEST(user.fields)
					WHERE
						name = 'lastName'
				) AS lastName,
				(
					SELECT
						SAFE_CAST(CAST(value AS STRING) AS BOOL)
					FROM
						UNNEST(user.fields)
					WHERE
						name = 'male'
				) AS male,
				(
					SELECT
						SAFE_CAST(value AS STRING)
					FROM
						UNNEST(user.fields)
					WHERE
						name = 'middleName'
				) AS middleName,
				(
					SELECT
						ARRAY (
							SELECT
								SAFE_CAST(number AS INT64)
							FROM
								UNNEST(arrayField.organizationIds) AS number
						)
					FROM (
						SELECT (
							SELECT
								JSON_EXTRACT_ARRAY(value)
							FROM
								UNNEST(user.fields)
							WHERE
								name = 'organizationIds'
						) organizationIds
					) arrayField
				) AS organizationIds,
				(
					SELECT
						SAFE_CAST(value AS INT64)
					FROM
						UNNEST(user.fields)
					WHERE
						name = 'parentContactId'
				) AS parentContactId,
				(
					SELECT
						SAFE_CAST(value AS STRING)
					FROM
						UNNEST(user.fields)
					WHERE
						name = 'prefixId'
				) AS prefixId,
				(
					SELECT
						ARRAY (
							SELECT
								SAFE_CAST(number AS INT64)
							FROM
								UNNEST(arrayField.roleIds) AS number
						)
					FROM (
						SELECT (
							SELECT
								JSON_EXTRACT_ARRAY(value)
							FROM
								UNNEST(user.fields)
							WHERE
								name = 'roleIds'
						) roleIds
					) arrayField
				) AS roleIds,
				ROW_NUMBER() OVER (
					PARTITION BY
						user.projectId, user.dataSourceId, user.classPK
					ORDER BY
						user.modifiedDate DESC
				) AS rowNumber,
				(
					SELECT
						SAFE_CAST(value AS STRING)
					FROM
						UNNEST(user.fields)
					WHERE
						name = 'screenName'
				) AS screenName,
				TO_HEX(
					SHA256(
						CONCAT(user.projectId, '#', user.dataSourceId, '#', user.classPK)
					)
				) AS sha256HexId,
				(
					SELECT
						SAFE_CAST(value AS STRING)
					FROM
						UNNEST(user.fields)
					WHERE
						name = 'suffixId'
				) AS suffixId,
				(
					SELECT
						ARRAY (
							SELECT
								SAFE_CAST(number AS INT64)
							FROM
								UNNEST(arrayField.teamIds) AS number
						)
					FROM (
						SELECT (
							SELECT
								JSON_EXTRACT_ARRAY(value)
							FROM
								UNNEST(user.fields)
							WHERE
								name = 'teamIds'
						) teamIds
					) arrayField
				) AS teamIds,
				(
					SELECT
						SAFE_CAST(value AS STRING)
					FROM
						UNNEST(user.fields)
					WHERE
						name = 'timeZoneId'
				) AS timeZoneId,
				(
					SELECT
						ARRAY (
							SELECT
								SAFE_CAST(number AS INT64)
							FROM
								UNNEST(arrayField.userGroupIds) AS number
						)
					FROM (
						SELECT (
							SELECT
								JSON_EXTRACT_ARRAY(value)
							FROM
								UNNEST(user.fields)
							WHERE
								name = 'userGroupIds'
						) userGroupIds
					) arrayField
				) AS userGroupIds,
				(
					SELECT
						SAFE_CAST(value AS INT64)
					FROM
						UNNEST(user.fields)
					WHERE
						name = 'userId'
				) AS userId,
				(
					SELECT
						SAFE_CAST(value AS STRING)
					FROM
						UNNEST(user.fields)
					WHERE
						name = 'userName'
				) AS userName,
				(
					SELECT
						SAFE_CAST(value AS STRING)
					FROM
						UNNEST(user.fields)
					WHERE
						name = 'uuid'
				) AS uuid
			FROM
				`{{ dag.default_args['ac_project_id'] }}.dxpentity` AS user
			LEFT JOIN (
				SELECT
					*,
					TRUE AS deleted,
					(
						SELECT
							SAFE_CAST(value AS INT64)
						FROM
							UNNEST(fields)
						WHERE
							name = 'classPK'
					) AS userId,
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
				analyticsDeleteMessage.className = user.type AND
				analyticsDeleteMessage.dataSourceId = user.dataSourceId AND
				analyticsDeleteMessage.projectId = user.projectId AND
				analyticsDeleteMessage.userId = SAFE_CAST(user.classPK AS INT64)
			WHERE
				(
					analyticsDeleteMessage.userId IS NOT NULL OR
					user.uploadDate >=
						{% if prev_start_date_success is not none %}
							'{{ prev_start_date_success }}'
						{% else %}
							'1970-01-01T00:00:00'
						{% endif %}
				) AND
				user.type = 'com.liferay.portal.kernel.model.User'
		)
		WHERE
			rowNumber = 1
	) AS staging
ON
	SAFE_CAST(staging.classPK AS INT64) = replica.dxpUserId AND
	staging.dataSourceId = replica.dataSourceId AND
	staging.projectId = replica.projectId
WHEN MATCHED AND staging.deleted IS NULL THEN
	UPDATE SET
		replica.accountId = staging.accountId,
		replica.birthday = staging.birthday,
		replica.emailAddress = staging.emailAddress,
		replica.emailAddressHashed = TO_HEX(SHA256(staging.emailAddress)),
		replica.expandoColumnIds = staging.expandoColumnIds,
		replica.firstName = staging.firstName,
		replica.groupIds = staging.groupIds,
		replica.jobTitle = staging.jobTitle,
		replica.languageId = staging.languageId,
		replica.lastName = staging.lastName,
		replica.male = staging.male,
		replica.middleName = staging.middleName,
		replica.modifiedDate = staging.modifiedDate,
		replica.organizationIds = staging.organizationIds,
		replica.parentContactId = staging.parentContactId,
		replica.prefixId = staging.prefixId,
		replica.roleIds = staging.roleIds,
		replica.screenName = staging.screenName,
		replica.suffixId = staging.suffixId,
		replica.teamIds = staging.teamIds,
		replica.timeZoneId = staging.timeZoneId,
		replica.userGroupIds = staging.userGroupIds,
		replica.userName = staging.userName
WHEN MATCHED AND staging.deleted = true THEN
	DELETE
WHEN NOT MATCHED BY TARGET AND staging.deleted IS NULL THEN
	INSERT (
		`accountId`,
		`birthday`,
		`classNameId`,
		`contactId`,
		`createDate`,
		`dataSourceId`,
		`dxpUserId`,
		`emailAddress`,
		`emailAddressHashed`,
		`expandoColumnIds`,
		`firstName`,
		`groupIds`,
		`id`,
		`jobTitle`,
		`languageId`,
		`lastName`,
		`male`,
		`middleName`,
		`modifiedDate`,
		`organizationIds`,
		`parentContactId`,
		`prefixId`,
		`projectId`,
		`roleIds`,
		`screenName`,
		`suffixId`,
		`teamIds`,
		`timeZoneId`,
		`userGroupIds`,
		`userName`,
		`uuid`
	)
	VALUES (
		staging.accountId,
		staging.birthday,
		staging.classNameId,
		staging.contactId,
		staging.createDate,
		staging.dataSourceId,
		staging.userId,
		staging.emailAddress,
		TO_HEX(SHA256(staging.emailAddress)),
		staging.expandoColumnIds,
		staging.firstName,
		staging.groupIds,
		staging.sha256HexId,
		staging.jobTitle,
		staging.languageId,
		staging.lastName,
		staging.male,
		staging.middleName,
		staging.modifiedDate,
		staging.organizationIds,
		staging.parentContactId,
		staging.prefixId,
		staging.projectId,
		staging.roleIds,
		staging.screenName,
		staging.suffixId,
		staging.teamIds,
		staging.timeZoneId,
		staging.userGroupIds,
		staging.userName,
		staging.uuid
	)