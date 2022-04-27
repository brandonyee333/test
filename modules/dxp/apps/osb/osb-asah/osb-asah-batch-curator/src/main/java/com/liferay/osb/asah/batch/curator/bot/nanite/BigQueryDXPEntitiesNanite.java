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

package com.liferay.osb.asah.batch.curator.bot.nanite;

import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.JobId;
import com.google.cloud.bigquery.QueryJobConfiguration;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.entity.AsahMarker;
import com.liferay.osb.asah.common.spring.annotation.ConditionalOnGoogleApplicationCredentials;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;

import java.io.InputStream;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.text.StringSubstitutor;

import org.json.JSONObject;

import org.springframework.stereotype.Component;

/**
 * @author Rachael Koestartyo
 */
@Component
@ConditionalOnGoogleApplicationCredentials
public class BigQueryDXPEntitiesNanite extends BaseNanite {

	@Override
	public boolean isLogRunEnabled() {
		return true;
	}

	@Override
	public void run(JSONObject contextJSONObject) throws Exception {
		AsahMarker asahMarker = getAsahMarker();

		JSONObject asahMarkerContextJSONObject =
			asahMarker.getContextJSONObject();

		String previousRunDateString = asahMarkerContextJSONObject.optString(
			"previousRunDateString", null);

		if (previousRunDateString == null) {
			previousRunDateString = DateUtil.toString(new Date(0));
		}

		String newDateString = DateUtil.newDateString();

		Map<String, String> mergeQueryValues = new HashMap<>();

		mergeQueryValues.put("previousRunDateString", previousRunDateString);
		mergeQueryValues.put("projectId", ProjectIdThreadLocal.getProjectId());

		for (String propertyName : _templateProperties.stringPropertyNames()) {
			_bigQuery.query(
				QueryJobConfiguration.newBuilder(
					StringSubstitutor.replace(
						_templateProperties.getProperty(propertyName),
						mergeQueryValues, "{", "}")
				).build(),
				JobId.of(_createJobId()));
		}

		asahMarkerContextJSONObject.put("previousRunDateString", newDateString);

		asahMarkerDog.updateAsahMarker(asahMarker);
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	private String _createJobId() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ssz");

		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

		return String.format(
			"%s_%s", "merge_dxp_entities", dateFormat.format(new Date()));
	}

	@PostConstruct
	private void _init() {
		BigQueryOptions bigQueryOptions = BigQueryOptions.getDefaultInstance();

		_bigQuery = bigQueryOptions.getService();

		_templateProperties = _loadTemplateProperties();
	}

	private Properties _loadTemplateProperties() {
		Class<?> clazz = getClass();

		try (InputStream inputStream = clazz.getResourceAsStream(
				"/bq-dxp-entities-merge-sql.xml")) {

			Properties properties = new Properties();

			properties.loadFromXML(inputStream);

			return properties;
		}
		catch (Exception exception) {
			throw new IllegalStateException(exception);
		}
	}

	private static final Log _log = LogFactory.getLog(
		BigQueryDXPEntitiesNanite.class);

	private BigQuery _bigQuery;
	private Properties _templateProperties;

}