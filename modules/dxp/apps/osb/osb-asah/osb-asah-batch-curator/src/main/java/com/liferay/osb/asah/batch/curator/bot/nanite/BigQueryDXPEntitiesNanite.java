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

import com.liferay.osb.asah.batch.curator.bot.nanite.model.BigQueryMergeJobConfiguration;
import com.liferay.osb.asah.batch.curator.bot.nanite.model.ExpandoColumnBigQueryMergeJobConfiguration;
import com.liferay.osb.asah.batch.curator.bot.nanite.model.ExpandoValueBigQueryMergeJobConfiguration;
import com.liferay.osb.asah.batch.curator.bot.nanite.model.GroupBigQueryMergeJobConfiguration;
import com.liferay.osb.asah.batch.curator.bot.nanite.model.OrganizationBigQueryMergeJobConfiguration;
import com.liferay.osb.asah.batch.curator.bot.nanite.model.RoleBigQueryMergeJobConfiguration;
import com.liferay.osb.asah.batch.curator.bot.nanite.model.TeamBigQueryMergeJobConfiguration;
import com.liferay.osb.asah.batch.curator.bot.nanite.model.UserBigQueryMergeJobConfiguration;
import com.liferay.osb.asah.batch.curator.bot.nanite.model.UserGroupBigQueryMergeJobConfiguration;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.entity.AsahMarker;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.spring.annotation.ConditionalOnGoogleApplicationCredentials;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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

		String projectId = ProjectIdThreadLocal.getProjectId();

		List<BigQueryMergeJobConfiguration> bigQueryMergeJobConfigurations =
			Arrays.asList(
				new ExpandoColumnBigQueryMergeJobConfiguration(projectId),
				new ExpandoValueBigQueryMergeJobConfiguration(projectId),
				new GroupBigQueryMergeJobConfiguration(projectId),
				new OrganizationBigQueryMergeJobConfiguration(projectId),
				new RoleBigQueryMergeJobConfiguration(projectId),
				new TeamBigQueryMergeJobConfiguration(projectId),
				new UserGroupBigQueryMergeJobConfiguration(projectId),
				new UserBigQueryMergeJobConfiguration(projectId));

		for (BigQueryMergeJobConfiguration bigQueryMergeJobConfiguration :
				bigQueryMergeJobConfigurations) {

			_bigQuery.query(
				QueryJobConfiguration.newBuilder(
					bigQueryMergeJobConfiguration.getMergeStatement(
						previousRunDateString)
				).build(),
				JobId.of(_createJobId()));
		}

		asahMarkerContextJSONObject.put("previousRunDateString", newDateString);

		asahMarkerDog.updateAsahMarker(asahMarker);
	}

	protected AsahMarker getAsahMarker() {
		Class<?> clazz = getClass();

		AsahMarker asahMarker = asahMarkerDog.fetchAsahMarker(
			clazz.getSimpleName());

		if (asahMarker == null) {
			asahMarker = asahMarkerDog.addAsahMarker(
				new AsahMarker(
					clazz.getSimpleName(), JSONUtil.put("type", "nanite")));

			asahMarker.setIsNew(Boolean.FALSE);
		}

		return asahMarker;
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
	}

	private static final Log _log = LogFactory.getLog(
		BigQueryDXPEntitiesNanite.class);

	private BigQuery _bigQuery;

}