CREATE OR REPLACE FUNCTION array_subtract(current_array ANYARRAY, subtract_array ANYARRAY) RETURNS ANYARRAY AS '
SELECT
    array_agg(unnested_current_array)
FROM
    unnest(current_array) AS unnested_current_array
WHERE
    unnested_current_array <> all(subtract_array);
' LANGUAGE sql;

CREATE OR REPLACE FUNCTION try_cast_bigint(value_string TEXT, default_value BIGINT DEFAULT NULL) RETURNS BIGINT AS '
BEGIN
    return value_string::BIGINT;
EXCEPTION
    WHEN OTHERS THEN
        RETURN default_value;
END;
' LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION try_cast_boolean(value_string TEXT, default_value BOOLEAN DEFAULT NULL) RETURNS BOOLEAN AS '
BEGIN
    return value_string::BOOLEAN;
EXCEPTION
    WHEN OTHERS THEN
        RETURN default_value;
END;
' LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION try_cast_float(value_string TEXT, default_value FLOAT DEFAULT NULL) RETURNS FLOAT AS '
BEGIN
    return value_string::FLOAT;
EXCEPTION
    WHEN OTHERS THEN
        RETURN default_value;
END;
' LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION try_cast_timestamp(value_string TEXT, default_value TIMESTAMP DEFAULT NULL) RETURNS TIMESTAMP AS '
BEGIN
    return value_string::TIMESTAMP;
EXCEPTION
    WHEN OTHERS THEN
        RETURN default_value;
END;
' LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION search_term(searchQueryParams VARCHAR ARRAY, uriString VARCHAR) RETURNS VARCHAR AS '
DECLARE
 decodeUrl VARCHAR := REPLACE(uriString, ''+'', '' '');
 queryParamSeparator INTEGER := POSITION(''?'' in decodeUrl);
 queryParams VARCHAR ARRAY;
 term VARCHAR;
 param VARCHAR;
BEGIN
	IF queryParamSeparator <= 0 THEN RETURN NULL;
	END IF;

	FOREACH term IN ARRAY searchQueryParams
		LOOP
	   		FOREACH param IN ARRAY STRING_TO_ARRAY(SUBSTRING(decodeUrl, queryParamSeparator + 1),''&'')
				LOOP
					queryParams := STRING_TO_ARRAY(param, ''='');
					IF queryParams[1] = term THEN RETURN queryParams[2];
					END IF;
				END LOOP;
		END LOOP;
	RETURN NULL;
END;
' LANGUAGE plpgsql;