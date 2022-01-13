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