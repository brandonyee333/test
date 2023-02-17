WITH CustomFieldMapping AS (
	SELECT
		'custom' AS context,
		dataSourceIds,
		CASE
			WHEN
				rowNumber = 1
			THEN
				name
			ELSE
				name || (rowNumber - 1)
		END displayName,
		displayType,
		CASE
			WHEN
				displaytype IN ('checkbox', 'radio', 'selection-list')
			THEN
				CONCAT(name, '_', datatype, '_array')
			ELSE
				CONCAT(name, '_', datatype)
		END fieldName,
		datatype as fieldType,
		modifiedDate,
		ownerType,
		CASE
			WHEN
				displaytype IN ('checkbox', 'radio', 'selection-list')
			THEN
				TRUE
			ELSE
				FALSE
		END repeatable_
	FROM (
		SELECT
			*,
			ROW_NUMBER() OVER (
				PARTITION BY
					name,
					ownerType
				ORDER BY
					modifiedDate ASC
			) AS rowNumber
		FROM (
			SELECT
				ARRAY_AGG(datasourceid) AS dataSourceIds,
				displayType,
				dataType,
				MAX(modifieddate) AS modifiedDate,
				name,
				CASE
					WHEN
						classname = 'com.liferay.portal.kernel.model.User'
					THEN
						'individual'
					WHEN
						classname = 'com.liferay.portal.kernel.model.Organization'
					THEN
						'organization'
				END ownerType
			FROM
				`$[AC_PROJECT_ID].expandocolumn` AS ExpandoColumn
			GROUP BY
				name, dataType, displayType, ownerType
		) AS TMP1
	) AS TMP2
),
DemographicsFieldMapping AS (
	SELECT 'demographics' context,
		ARRAY<INT64>[] dataSourceIds,
		displayName,
		'input-field' displayType,
		fieldName,
		fieldType,
		CURRENT_TIMESTAMP() modifiedDate,
		'individual' ownerType,
		false repeatable_
	FROM UNNEST(ARRAY<STRUCT<displayName STRING, fieldName STRING,fieldType STRING>>
		[
			('additionalName', 'middleName', 'text'),
			('address', 'addresses', 'text'),
			('birthDate', 'birthday', 'date'),
			('email', 'emailAddress', 'text'),
			('familyName', 'lastName', 'text'),
			('gender', 'gender', 'text'),
			('givenName', 'firstName', 'text'),
			('jobTitle', 'jobTitle', 'text'),
			('languageId', 'languageId', 'text'),
			('screenName', 'screenName', 'text'),
			('timeZoneId', 'timeZoneId', 'text')
		])
)

SELECT
	*
FROM
	CustomFieldMapping
UNION ALL
SELECT
	*
FROM
	DemographicsFieldMapping