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

import com.google.api.client.util.Objects;

import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.dog.RunLogDog;
import com.liferay.osb.asah.common.dog.SuppressionDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoIndividualDog;
import com.liferay.osb.asah.common.faro.info.util.FaroInfoIndividualUtil;

import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Shinn Lok
 */
public abstract class BaseIndividualsNanite extends BaseNanite {

	@Override
	public void run(JSONObject contextJSONObject) throws Exception {
		Long dataSourceId = Long.valueOf(
			contextJSONObject.getString("dataSourceId"));
		String type = contextJSONObject.getString("type");

		if (type.equals("audit-events")) {
			while (isRunning(dataSourceId)) {
			}

			setRunning(dataSourceId, true);

			processDataSourceAuditEvents(dataSourceId);

			setRunning(dataSourceId, false);
		}
		else if (type.equals("reprocess")) {
			synchronized (this) {
				if (isRunning(dataSourceId)) {
					setInterrupted(dataSourceId, true);

					while (isRunning(dataSourceId)) {
					}
				}

				setRunning(dataSourceId, true);
			}

			reprocessUpdateDataSource(dataSourceId);

			setRunning(dataSourceId, false);
		}
	}

	protected void delete(
			Long dataSourceId, Date deletionDate, String emailAddress)
		throws Exception {

		JSONObject individualJSONObject = faroInfoElasticsearchInvoker.fetch(
			"individuals",
			QueryBuilders.termQuery("demographics.email.value", emailAddress));

		if (individualJSONObject == null) {
			return;
		}

		JSONArray dataSourceIndividualPKsJSONArray =
			individualJSONObject.getJSONArray("dataSourceIndividualPKs");

		if (dataSourceIndividualPKsJSONArray.length() == 1) {
			JSONArray individualPKsJSONArray =
				FaroInfoIndividualUtil.getIndividualPKsJSONArray(
					String.valueOf(dataSourceId),
					dataSourceIndividualPKsJSONArray);

			if (individualPKsJSONArray.length() > 0) {
				_faroInfoIndividualDog.deleteIndividual(
					deletionDate, individualJSONObject.getString("id"));
			}

			return;
		}

		_faroInfoIndividualDog.removeDataSourceIndividualPKs(
			individualJSONObject, dataSourceId);

		_faroInfoIndividualDog.updateIndividual(
			null, getEmptyDataJSONObject(),
			_dataSourceDog.getDataSource(dataSourceId), individualJSONObject);
	}

	protected JSONObject getEmptyDataJSONObject() {
		return new JSONObject();
	}

	protected abstract boolean isInterrupted(Long dataSourceId);

	protected abstract boolean isRunning(Long dataSourceId);

	protected void processData(
			String dataId, Long dataSourceId, JSONObject dataJSONObject,
			String emailAddress)
		throws Exception {

		if ((emailAddress == null) ||
			_suppressionDog.isSuppressed(emailAddress, null)) {

			return;
		}

		DataSource dataSource = _dataSourceDog.getDataSource(dataSourceId);

		if (Objects.equal(dataSource.getState(), "IN_PROGRESS_DELETING")) {
			Log log = getLog();

			if (log.isWarnEnabled()) {
				log.warn("Unable to get data source " + dataSourceId);
			}

			return;
		}

		emailAddress = StringUtils.lowerCase(emailAddress);

		JSONObject individualJSONObject = faroInfoElasticsearchInvoker.fetch(
			"individuals",
			BoolQueryBuilderUtil.should(
				QueryBuilders.termQuery(
					"demographics.email.value", emailAddress)
			).should(
				QueryBuilders.termQuery(
					"emailAddressHashed", DigestUtils.sha256Hex(emailAddress))
			));

		if (individualJSONObject == null) {
			_faroInfoIndividualDog.addIndividual(
				dataId, dataJSONObject, dataSource);
		}
		else {
			_faroInfoIndividualDog.updateIndividual(
				dataId, dataJSONObject, dataSource, individualJSONObject);
		}
	}

	protected abstract void processDataSourceAuditEvents(Long dataSourceId)
		throws Exception;

	protected abstract void reprocessUpdateDataSource(Long dataSourceId)
		throws Exception;

	protected abstract void setInterrupted(
		Long dataSourceId, boolean interrupted);

	protected abstract void setRunning(Long dataSourceId, boolean running);

	@Autowired
	protected RunLogDog runLogDog;

	@Autowired
	private DataSourceDog _dataSourceDog;

	@Autowired
	private FaroInfoIndividualDog _faroInfoIndividualDog;

	@Autowired
	private SuppressionDog _suppressionDog;

}