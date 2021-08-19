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
import com.liferay.osb.asah.common.dog.IndividualDog;
import com.liferay.osb.asah.common.dog.RunLogDog;
import com.liferay.osb.asah.common.dog.SuppressionDog;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.DataSourceIndividual;
import com.liferay.osb.asah.common.entity.Individual;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;

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

		Individual individual = _individualDog.fetchIndividualByEmailAddress(
			emailAddress);

		if (individual == null) {
			return;
		}

		Set<DataSourceIndividual> dataSourceIndividuals =
			individual.getDataSourceIndividuals();

		if (dataSourceIndividuals.size() == 1) {
			Iterator<DataSourceIndividual> iterator =
				dataSourceIndividuals.iterator();

			DataSourceIndividual dataSourceIndividual = iterator.next();

			if (CollectionUtils.isNotEmpty(
					dataSourceIndividual.getIndividualPKs())) {

				_individualDog.deleteIndividual(
					deletionDate, individual.getId());
			}

			return;
		}

		_individualDog.removeDataSourceIndividualPKs(individual, dataSourceId);

		_individualDog.updateIndividual(
			null, getEmptyDataJSONObject(),
			_dataSourceDog.getDataSource(dataSourceId), individual);
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

		Individual individual =
			_individualDog.fetchIndividualByEmailAddressOrEmailAddressHashed(
				emailAddress, DigestUtils.sha256Hex(emailAddress));

		if (individual == null) {
			_individualDog.addIndividual(dataId, dataJSONObject, dataSource);
		}
		else {
			_individualDog.updateIndividual(
				dataId, dataJSONObject, dataSource, individual);
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
	private IndividualDog _individualDog;

	@Autowired
	private SuppressionDog _suppressionDog;

}