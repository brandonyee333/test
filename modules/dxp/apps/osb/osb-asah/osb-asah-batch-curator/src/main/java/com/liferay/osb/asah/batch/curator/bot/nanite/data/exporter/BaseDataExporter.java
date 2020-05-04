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

import com.liferay.osb.asah.common.http.ReportHttp;

import java.io.OutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Marcellus Tavares
 */
public abstract class BaseDataExporter implements DataExporter {

	public BaseDataExporter(
			JsonFactory jsonFactory, OutputStream outputStream,
			ReportHttp reportHttp)
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

		this.reportHttp = reportHttp;
	}

	public void export() throws Exception {
		String lastDocumentId = "0";

		while (true) {
			JSONObject resultPageJSONObject = doGetResultPageJSONObject(
				lastDocumentId);

			JSONArray resultsJSONArray = resultPageJSONObject.getJSONArray(
				"results");

			if (resultsJSONArray.length() == 0) {
				break;
			}

			lastDocumentId = _getLastDocumentId(resultsJSONArray);

			_exportResults(resultsJSONArray);
		}

		jsonGenerator.close();
	}

	protected abstract JSONObject doGetResultPageJSONObject(String after);

	protected JsonGenerator jsonGenerator;
	protected ReportHttp reportHttp;

	private void _exportResult(JSONObject resultJSONObject) {
		try {
			jsonGenerator.writeObject(resultJSONObject);
			jsonGenerator.writeRaw("\n");
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}
	}

	private void _exportResults(JSONArray resultsJSONArray) {
		for (int i = 0; i < resultsJSONArray.length(); i++) {
			_exportResult(resultsJSONArray.getJSONObject(i));
		}
	}

	private String _getLastDocumentId(JSONArray jsonArray) {
		JSONObject jsonObject = jsonArray.getJSONObject(jsonArray.length() - 1);

		return jsonObject.getString("id");
	}

	private static final Log _log = LogFactory.getLog(BaseDataExporter.class);

}