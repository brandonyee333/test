/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.data.partitioning.sql.builder.oracle.exporter.serializer;

import com.liferay.portal.tools.data.partitioning.sql.builder.exporter.serializer.FieldSerializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;

import java.nio.charset.Charset;

import java.sql.Clob;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import oracle.sql.TIMESTAMP;

/**
 * @author Manuel de la Peña
 */
public class OracleFieldSerializer implements FieldSerializer {

	@Override
	public String serialize(Object object) {
		StringBuilder sb = new StringBuilder();

		if (object == null) {
			sb.append("null");
		}
		else if (object instanceof Clob) {
			sb.append("TO_CLOB('");

			try (InputStream inputStream = ((Clob)object).getAsciiStream()) {
				Reader reader = new InputStreamReader(
					inputStream, Charset.forName("UTF-8"));

				StringWriter stringWriter = new StringWriter();

				int c = -1;

				while ((c = reader.read()) != -1) {
					stringWriter.write(c);
				}

				String value = stringWriter.toString();

				value = value.replace("'", "''");

				sb.append(value);
			}
			catch (IOException | SQLException e) {
				throw new RuntimeException("Unable to read the CLOB value", e);
			}

			sb.append("')");
		}
		else if (object instanceof Date || object instanceof Timestamp ||
				 object instanceof TIMESTAMP) {

			sb.append("to_timestamp('");
			sb.append(_formatDateTime(object));
			sb.append("', '");
			sb.append("yyyy-MM-dd HH24:MI:SS.FF");
			sb.append("')");
		}
		else if (object instanceof Number) {
			sb.append(object);
		}
		else if (object instanceof String) {
			String value = (String)object;

			value = value.replace("'", "''");

			sb.append("'");
			sb.append(value);
			sb.append("'");
		}
		else {
			sb.append("'");
			sb.append(object);
			sb.append("'");
		}

		return sb.toString();
	}

	private String _formatDateTime(Object date) {
		if (date instanceof TIMESTAMP) {
			try {
				TIMESTAMP oracleTimestamp = (TIMESTAMP)date;

				Timestamp timestamp = oracleTimestamp.timestampValue();

				return _dateFormat.format(timestamp);
			}
			catch (SQLException sqle) {
				throw new RuntimeException(
					"Unable to get the timestamp value of " + date, sqle);
			}
		}

		return _dateFormat.format(date);
	}

	private final DateFormat _dateFormat = new SimpleDateFormat(
		"yyyy-MM-dd HH:mm:ss.FFF");

}