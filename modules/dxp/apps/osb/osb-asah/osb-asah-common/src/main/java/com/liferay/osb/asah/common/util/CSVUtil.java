/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.asah.common.util;

import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;

import java.io.File;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.JSONObject;

/**
 * @author Matthew Kong
 */
public class CSVUtil {

	public static File createCSVFile(
			Map<String, String> fieldNames, String fileNamePrefix, File folder)
		throws Exception {

		File file = File.createTempFile(fileNamePrefix, ".csv", folder);

		file.deleteOnExit();

		CsvWriter csvWriter = new CsvWriter(file, new CsvWriterSettings());

		csvWriter.writeHeaders(fieldNames.values());

		// TODO Write suppression rows to CSV

		csvWriter.close();

		return file;
	}

	public static File createCSVFile(
			Map<String, String> fieldNames, String fileNamePrefix, File folder,
			List<JSONObject> jsonObjects)
		throws Exception {

		File file = File.createTempFile(fileNamePrefix, ".csv", folder);

		file.deleteOnExit();

		CsvWriter csvWriter = new CsvWriter(file, new CsvWriterSettings());

		csvWriter.writeHeaders(fieldNames.values());

		Set<String> keys = fieldNames.keySet();

		Stream<JSONObject> jsonObjectsStream = jsonObjects.stream();

		jsonObjectsStream.forEach(
			jsonObject -> {
				Stream<String> keysStream = keys.stream();

				csvWriter.writeRow(
					keysStream.map(
						jsonObject::get
					).collect(
						Collectors.toList()
					));
			});

		csvWriter.close();

		return file;
	}

}