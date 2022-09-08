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
		) TMP1
	) TMP2
),
DemographicsFieldMapping AS (
	SELECT
		*
	FROM UNNEST(
		[
			('demographics', ARRAY[], 'address', 'input-field', 'addresses', 'text', CURRENT_TIMESTAMP(), 'individual', false),
			('demographics', ARRAY[], 'birthDate', 'input-field', 'birthday', 'date', CURRENT_TIMESTAMP(), 'individual', false),
			('demographics', ARRAY[], 'email', 'input-field', 'emailAddress', 'text', CURRENT_TIMESTAMP(), 'individual', false),
			('demographics', ARRAY[], 'givenName', 'input-field', 'firstName', 'text', CURRENT_TIMESTAMP(), 'individual', false),
			('demographics', ARRAY[], 'gender', 'input-field', 'gender', 'text', CURRENT_TIMESTAMP(), 'individual', false),
			('demographics', ARRAY[], 'jobTitle', 'input-field',  'jobTitle', 'text', CURRENT_TIMESTAMP(), 'individual', false),
			('demographics', ARRAY[], 'familyName', 'input-field', 'lastName', 'text', CURRENT_TIMESTAMP(), 'individual', false),
			('demographics', ARRAY[], 'additionalName', 'input-field', 'middleName', 'text', CURRENT_TIMESTAMP(), 'individual', false),
			('demographics', ARRAY[], 'telephone', 'input-field', 'phones', 'text', CURRENT_TIMESTAMP(), 'individual', false)
		]
	)
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