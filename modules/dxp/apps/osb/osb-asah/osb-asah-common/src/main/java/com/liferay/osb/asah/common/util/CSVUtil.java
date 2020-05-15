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

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONArrayIterator;

import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;

import java.io.File;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.elasticsearch.index.query.QueryBuilder;

/**
 * @author Matthew Kong
 */
public class CSVUtil {

	public static File createCSVFile(
			String collectionName, ElasticsearchInvoker elasticsearchInvoker,
			Map<String, String> fieldNames, String fileNamePrefix, File folder,
			QueryBuilder queryBuilder)
		throws Exception {

		File file = File.createTempFile(fileNamePrefix, ".csv", folder);

		file.deleteOnExit();

		CsvWriter csvWriter = new CsvWriter(file, new CsvWriterSettings());

		csvWriter.writeHeaders(fieldNames.values());

		JSONArrayIterator.of(
			collectionName, elasticsearchInvoker,
			jsonObject -> {
				Set<String> keys = fieldNames.keySet();

				Stream<String> stream = keys.stream();

				csvWriter.writeRow(
					stream.map(
						jsonObject::get
					).collect(
						Collectors.toList()
					));

				return null;
			}
		).setQueryBuilder(
			queryBuilder
		).iterate();

		csvWriter.close();

		return file;
	}

}