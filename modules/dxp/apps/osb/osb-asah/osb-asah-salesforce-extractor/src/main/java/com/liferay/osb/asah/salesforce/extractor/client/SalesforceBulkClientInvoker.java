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

package com.liferay.osb.asah.salesforce.extractor.client;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.salesforce.extractor.configuration.SalesforceExtractorConfiguration;
import com.liferay.osb.asah.salesforce.extractor.util.TimeUtil;
import com.liferay.petra.salesforce.client.bulk.SalesforceBulkClient;
import com.liferay.petra.salesforce.client.bulk.SalesforceBulkClientImpl;

import com.sforce.async.BatchInfo;
import com.sforce.async.BatchStateEnum;
import com.sforce.async.ConcurrencyMode;
import com.sforce.async.ContentType;
import com.sforce.async.JobInfo;
import com.sforce.async.OperationEnum;
import com.sforce.async.QueryResultList;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import java.nio.charset.StandardCharsets;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.stereotype.Component;

/**
 * @author Brian Wing Shun Chan
 * @author Rachael Koestartyo
 */
@Component
public class SalesforceBulkClientInvoker
	extends BaseSalesforceClientInvoker<SalesforceBulkClient> {

	public JobInfo closeJob(
			SalesforceExtractorConfiguration salesforceExtractorConfiguration,
			String jobInfoId)
		throws Exception {

		SalesforceBulkClient salesforceBulkClient = _getSalesforceBulkClient(
			salesforceExtractorConfiguration);

		return salesforceBulkClient.closeJob(jobInfoId, _RETRY_COUNT);
	}

	public BatchInfo getBatchInfo(
			SalesforceExtractorConfiguration salesforceExtractorConfiguration,
			String soql, String typeName)
		throws Exception {

		BatchInfo batchInfo = null;

		for (int i = 0; i < _RETRY_COUNT; i++) {
			JobInfo jobInfo = _getJobInfo(
				salesforceExtractorConfiguration, ContentType.XML,
				OperationEnum.query, typeName);

			batchInfo = _getBatchInfo(
				salesforceExtractorConfiguration, jobInfo, soql);

			if (batchInfo.getState() == BatchStateEnum.Completed) {
				return batchInfo;
			}

			closeJob(salesforceExtractorConfiguration, jobInfo.getId());
		}

		throw new Exception(
			"Unable to get batch info for type " + typeName + " with " + soql);
	}

	public QueryResultList getQueryResultList(
			SalesforceExtractorConfiguration salesforceExtractorConfiguration,
			String batchInfoId, String jobInfoId)
		throws Exception {

		SalesforceBulkClient salesforceBulkClient = _getSalesforceBulkClient(
			salesforceExtractorConfiguration);

		return salesforceBulkClient.getQueryResultList(
			jobInfoId, batchInfoId, _RETRY_COUNT);
	}

	public InputStream getQueryResultStream(
			SalesforceExtractorConfiguration salesforceExtractorConfiguration,
			String batchInfoId, String jobInfoId, String queryResultId)
		throws Exception {

		SalesforceBulkClient salesforceBulkClient = _getSalesforceBulkClient(
			salesforceExtractorConfiguration);

		return salesforceBulkClient.getQueryResultStream(
			jobInfoId, batchInfoId, queryResultId, _RETRY_COUNT);
	}

	private BatchInfo _getBatchInfo(
		SalesforceExtractorConfiguration salesforceExtractorConfiguration,
		JobInfo jobInfo, String soql) {

		BatchInfo batchInfo = null;

		SalesforceBulkClient salesforceBulkClient = _getSalesforceBulkClient(
			salesforceExtractorConfiguration);
		long startTime = System.currentTimeMillis();

		try (InputStream inputStream = new ByteArrayInputStream(
				soql.getBytes(StandardCharsets.UTF_8))) {

			if (_log.isDebugEnabled()) {
				_log.debug("SOQL: " + soql);
			}

			batchInfo = salesforceBulkClient.createBatchFromStream(
				jobInfo, inputStream, _RETRY_COUNT);

			long elapsedTime = 0;

			do {
				elapsedTime = System.currentTimeMillis() - startTime;

				Thread.sleep(elapsedTime + DateUtil.SECOND);

				batchInfo = salesforceBulkClient.getBatchInfo(
					jobInfo.getId(), batchInfo.getId(), _RETRY_COUNT);

				if ((batchInfo.getState() != BatchStateEnum.InProgress) &&
					(batchInfo.getState() != BatchStateEnum.Queued)) {

					return batchInfo;
				}
			}
			while (elapsedTime < _TIMEOUT);
		}
		catch (Exception e) {
			_log.error("Unable to get batch info", e);
		}
		finally {
			if (_log.isInfoEnabled()) {
				if (batchInfo == null) {
					_log.info("Batch time: " + TimeUtil.format(startTime));
				}
				else if (batchInfo.getState() == BatchStateEnum.Completed) {
					_log.info(
						"Completed batch time: " + TimeUtil.format(startTime));
				}
			}
		}

		return batchInfo;
	}

	private JobInfo _getJobInfo(
			SalesforceExtractorConfiguration salesforceExtractorConfiguration,
			ContentType contentType, OperationEnum operationEnum,
			String typeName)
		throws Exception {

		SalesforceBulkClient salesforceBulkClient = _getSalesforceBulkClient(
			salesforceExtractorConfiguration);

		JobInfo jobInfo = new JobInfo();

		jobInfo.setConcurrencyMode(ConcurrencyMode.Parallel);
		jobInfo.setContentType(contentType);
		jobInfo.setObject(typeName);
		jobInfo.setOperation(operationEnum);

		return salesforceBulkClient.createJob(jobInfo, _RETRY_COUNT);
	}

	private SalesforceBulkClient _getSalesforceBulkClient(
		SalesforceExtractorConfiguration salesforceExtractorConfiguration) {

		return (SalesforceBulkClient)getSalesforceClient(
			new SalesforceBulkClientImpl(), salesforceExtractorConfiguration);
	}

	private static final int _RETRY_COUNT = 3;

	private static final long _TIMEOUT = DateUtil.HOUR * 2;

	private static final Log _log = LogFactory.getLog(
		SalesforceBulkClientInvoker.class);

}