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

package com.liferay.osb.asah.batch.curator.bot.nanite.data.exporter;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsonorg.JsonOrgModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.OutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * @author Marcellus Tavares
 */
public abstract class BaseDataExporter implements DataExporter {

	public BaseDataExporter(JsonFactory jsonFactory, OutputStream outputStream)
		throws Exception {

		jsonGenerator = jsonFactory.createGenerator(
			outputStream, JsonEncoding.UTF8);

		jsonGenerator.setCodec(
			new ObjectMapper() {
				{
					disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
					disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

					registerModule(new JavaTimeModule());
					registerModule(new JsonOrgModule());
				}
			});
		jsonGenerator.setPrettyPrinter(new MinimalPrettyPrinter(""));
	}

	@Override
	public void export() throws Exception {
		int page = 0;

		while (true) {
			JSONObject resultPageJSONObject = doGetResultPageJSONObject(
				PageRequest.of(page, _PAGE_SIZE));

			JSONArray resultsJSONArray = resultPageJSONObject.getJSONArray(
				"results");

			if (resultsJSONArray.length() == 0) {
				break;
			}

			exportResults(resultsJSONArray);

			page++;
		}

		jsonGenerator.close();
	}

	protected abstract JSONObject doGetResultPageJSONObject(Pageable pageable);

	protected void exportResults(JSONArray resultsJSONArray) {
		for (int i = 0; i < resultsJSONArray.length(); i++) {
			_exportResult(resultsJSONArray.getJSONObject(i));
		}
	}

	protected JsonGenerator jsonGenerator;

	private void _exportResult(JSONObject resultJSONObject) {
		try {
			jsonGenerator.writeObject(resultJSONObject);

			jsonGenerator.writeRaw("\n");
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn(exception, exception);
			}
		}
	}

	private static final int _PAGE_SIZE = 50;

	private static final Log _log = LogFactory.getLog(BaseDataExporter.class);

}