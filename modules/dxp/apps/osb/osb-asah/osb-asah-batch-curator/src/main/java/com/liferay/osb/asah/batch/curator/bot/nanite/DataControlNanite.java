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

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

import com.liferay.osb.asah.batch.curator.bot.nanite.data.exporter.DataExporter;
import com.liferay.osb.asah.batch.curator.bot.nanite.data.exporter.RawDataExporter;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.http.EmailHttp;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.DataControlTaskStatus;
import com.liferay.osb.asah.common.model.DataControlTaskType;
import com.liferay.osb.asah.common.zip.ZipFileBuilder;

import java.io.File;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.zip.ZipOutputStream;

import javax.annotation.PostConstruct;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Matthew Kong
 */
@Component
public class DataControlNanite extends BaseNanite {

	@Override
	@PostConstruct
	public void init() {
		super.init();

		_cerebroInfoElasticsearchInvoker =
			elasticsearchInvokerFactory.forCerebroInfo();
		_cerebroRawElasticsearchInvoker =
			elasticsearchInvokerFactory.forCerebroRaw();
		_dxpRawElasticsearchInvoker = elasticsearchInvokerFactory.forDXPRaw();
		_salesforceRawElasticsearchInvoker =
			elasticsearchInvokerFactory.forSalesforceRaw();
	}

	@Override
	public void run(JSONObject contextJSONObject) throws Exception {
		Path path = _getPath(contextJSONObject);

		JSONArrayIterator.of(
			"data-control-tasks", faroInfoElasticsearchInvoker,
			jsonObject -> _runDataControlTask(jsonObject, path)
		).setQueryBuilder(
			QueryBuilders.termQuery(
				"status", DataControlTaskStatus.PENDING.toString())
		).setStopOnExceptions(
			false
		).iterate();

		JSONArrayIterator.of(
			"data-control-tasks", faroInfoElasticsearchInvoker,
			jsonObject -> _expireDataControlTask(jsonObject, path)
		).setQueryBuilder(
			BoolQueryBuilderUtil.filter(
				QueryBuilders.rangeQuery(
					"completeDate"
				).lt(
					DateUtil.addDays(DateUtil.newDateString(), -30)
				)
			).filter(
				QueryBuilders.termQuery(
					"status", DataControlTaskStatus.COMPLETED.toString())
			).filter(
				QueryBuilders.termsQuery(
					"type", DataControlTaskType.ACCESS.toString())
			)
		).setStopOnExceptions(
			false
		).iterate();
	}

	private void _addSuppression(
		JSONObject dataControlTaskJSONObject, String emailAddress) {

		if (faroInfoElasticsearchInvoker.exists(
				"suppressions",
				QueryBuilders.termQuery("emailAddress", emailAddress))) {

			return;
		}

		faroInfoElasticsearchInvoker.add(
			"suppressions",
			JSONUtil.put(
				"createDate", DateUtil.newDateString()
			).put(
				"dataControlTaskBatchId",
				dataControlTaskJSONObject.getString("batchId")
			).put(
				"dataControlTaskCreateDate",
				dataControlTaskJSONObject.getString("createDate")
			).put(
				"dataControlTaskStatus",
				DataControlTaskStatus.COMPLETED.toString()
			).put(
				"emailAddress", emailAddress
			).put(
				"emailAddressHashed", DigestUtils.sha256Hex(emailAddress)
			));
	}

	private QueryBuilder _buildIndividualQueryBuilder(
		String dataSourceType, JSONObject individualJSONObject,
		String individualPKFieldName) {

		BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();

		JSONArray dataSourceIndividualPKsJSONArray =
			individualJSONObject.getJSONArray("dataSourceIndividualPKs");

		for (int i = 0; i < dataSourceIndividualPKsJSONArray.length(); i++) {
			JSONObject dataSourceIndividualPKJSONObject =
				dataSourceIndividualPKsJSONArray.getJSONObject(i);

			if (!StringUtils.equals(
					dataSourceIndividualPKJSONObject.getString(
						"dataSourceType"),
					dataSourceType)) {

				continue;
			}

			String dataSourceId = dataSourceIndividualPKJSONObject.getString(
				"dataSourceId");

			JSONArray individualPKsJSONArray =
				dataSourceIndividualPKJSONObject.getJSONArray("individualPKs");

			for (int j = 0; j < individualPKsJSONArray.length(); j++) {
				boolQueryBuilder.should(
					BoolQueryBuilderUtil.filter(
						QueryBuilders.termsQuery("dataSourceId", dataSourceId)
					).filter(
						QueryBuilders.termsQuery(
							individualPKFieldName,
							individualPKsJSONArray.getString(j))
					));
			}
		}

		if (boolQueryBuilder.hasClauses()) {
			return boolQueryBuilder;
		}

		return null;
	}

	private void _deleteData(String emailAddress) {
		_dxpRawElasticsearchInvoker.delete(
			"users", QueryBuilders.termQuery("emailAddress", emailAddress));
		_salesforceRawElasticsearchInvoker.delete(
			"individuals", QueryBuilders.termQuery("email", emailAddress));

		JSONObject individualJSONObject = faroInfoElasticsearchInvoker.fetch(
			"individuals",
			QueryBuilders.termQuery("demographics.email.value", emailAddress));

		if (individualJSONObject == null) {
			return;
		}

		QueryBuilder csvIndividualQueryBuilder = _buildIndividualQueryBuilder(
			"CSV", individualJSONObject, "dataSourceIndividualPK");

		if (csvIndividualQueryBuilder != null) {
			faroInfoElasticsearchInvoker.delete(
				"csv-individuals",
				_buildIndividualQueryBuilder(
					"CSV", individualJSONObject, "dataSourceIndividualPK"));
		}

		individualJSONObject.put("demographics", new JSONArray());

		faroInfoElasticsearchInvoker.update(
			"individuals", individualJSONObject);
	}

	private void _deleteSuppression(String emailAddress) {
		faroInfoElasticsearchInvoker.delete(
			"suppressions",
			QueryBuilders.termsQuery("emailAddress", emailAddress));
	}

	private JSONObject _expireDataControlTask(
		JSONObject dataControlTaskJSONObject, Path path) {

		Path zipFilePath = Paths.get(
			path.toString(),
			dataControlTaskJSONObject.getString("id") + ".zip");

		File file = zipFilePath.toFile();

		if (file.exists() && !file.delete()) {
			_log.error("Unable to delete file " + file.getAbsolutePath());
		}

		_updateDataControlTaskStatus(
			dataControlTaskJSONObject, DataControlTaskStatus.EXPIRED);

		return dataControlTaskJSONObject;
	}

	private void _exportData(
			JSONObject dataControlTaskJSONObject, String emailAddress,
			Path path)
		throws Exception {

		Path zipFilePath = Paths.get(
			path.toString(),
			dataControlTaskJSONObject.getString("id") + ".zip");

		ZipFileBuilder zipFileBuilder = new ZipFileBuilder(
			zipFilePath.toString());

		zipFileBuilder.addToZip(
			"dxp_users.json",
			zipOutputStream -> _writeToZip(
				"users", _dxpRawElasticsearchInvoker,
				QueryBuilders.termQuery("emailAddress", emailAddress),
				zipOutputStream));
		zipFileBuilder.addToZip(
			"individuals.json",
			zipOutputStream -> _writeToZip(
				"individuals", faroInfoElasticsearchInvoker,
				QueryBuilders.termQuery(
					"demographics.email.value", emailAddress),
				zipOutputStream));
		zipFileBuilder.addToZip(
			"salesforce-individuals.json",
			zipOutputStream -> _writeToZip(
				"individuals", _salesforceRawElasticsearchInvoker,
				QueryBuilders.termQuery("email", emailAddress),
				zipOutputStream));

		JSONObject individualJSONObject = faroInfoElasticsearchInvoker.fetch(
			"individuals",
			QueryBuilders.termQuery("demographics.email.value", emailAddress));

		if (individualJSONObject == null) {
			_exportDataControlTask(dataControlTaskJSONObject, zipFileBuilder);

			return;
		}

		QueryBuilder csvIndividualQueryBuilder = _buildIndividualQueryBuilder(
			"CSV", individualJSONObject, "dataSourceIndividualPK");

		if (csvIndividualQueryBuilder != null) {
			zipFileBuilder.addToZip(
				"csv-individuals.json",
				zipOutputStream -> _writeToZip(
					"csv-individuals", faroInfoElasticsearchInvoker,
					_buildIndividualQueryBuilder(
						"CSV", individualJSONObject, "dataSourceIndividualPK"),
					zipOutputStream));
		}

		QueryBuilder liferayIndividualQueryBuilder =
			_buildIndividualQueryBuilder(
				"LIFERAY", individualJSONObject, "userId");

		if (liferayIndividualQueryBuilder != null) {
			zipFileBuilder.addToZip(
				"analytics-events.json",
				zipOutputStream -> _writeToZip(
					"analytics-events", _cerebroRawElasticsearchInvoker,
					_buildIndividualQueryBuilder(
						"LIFERAY", individualJSONObject, "userId"),
					zipOutputStream));
		}

		QueryBuilder individualIdQueryBuilder = QueryBuilders.termQuery(
			"individualId", individualJSONObject.getString("id"));

		zipFileBuilder.addToZip(
			"blogs.json",
			zipOutputStream -> _writeToZip(
				"blogs", _cerebroInfoElasticsearchInvoker,
				individualIdQueryBuilder, zipOutputStream));
		zipFileBuilder.addToZip(
			"document-libraries.json",
			zipOutputStream -> _writeToZip(
				"document-libraries", _cerebroInfoElasticsearchInvoker,
				individualIdQueryBuilder, zipOutputStream));
		zipFileBuilder.addToZip(
			"forms.json",
			zipOutputStream -> _writeToZip(
				"forms", _cerebroInfoElasticsearchInvoker,
				individualIdQueryBuilder, zipOutputStream));
		zipFileBuilder.addToZip(
			"journals.json",
			zipOutputStream -> _writeToZip(
				"journals", _cerebroInfoElasticsearchInvoker,
				individualIdQueryBuilder, zipOutputStream));
		zipFileBuilder.addToZip(
			"page-referrers.json",
			zipOutputStream -> _writeToZip(
				"page-referrers", _cerebroInfoElasticsearchInvoker,
				individualIdQueryBuilder, zipOutputStream));
		zipFileBuilder.addToZip(
			"pages.json",
			zipOutputStream -> _writeToZip(
				"pages", _cerebroInfoElasticsearchInvoker,
				individualIdQueryBuilder, zipOutputStream));
		zipFileBuilder.addToZip(
			"user-sessions.json",
			zipOutputStream -> _writeToZip(
				"user-sessions", _cerebroInfoElasticsearchInvoker,
				individualIdQueryBuilder, zipOutputStream));

		_exportDataControlTask(dataControlTaskJSONObject, zipFileBuilder);
	}

	private void _exportDataControlTask(
			JSONObject dataControlTaskJSONObject, ZipFileBuilder zipFileBuilder)
		throws Exception {

		zipFileBuilder.addToZip(
			"data-control-tasks.json",
			zipOutputStream -> {
				_updateDataControlTaskStatus(
					dataControlTaskJSONObject, DataControlTaskStatus.COMPLETED);

				String dataControlTaskJSON = dataControlTaskJSONObject.toString(
					2);

				zipOutputStream.write(
					dataControlTaskJSON.getBytes(StandardCharsets.UTF_8));
			});

		zipFileBuilder.build();
	}

	private Path _getPath(JSONObject contextJSONObject) {
		if (contextJSONObject == null) {
			return Paths.get(_exportPathName);
		}

		return Paths.get(contextJSONObject.optString("path", _exportPathName));
	}

	private JSONObject _runDataControlTask(
		JSONObject dataControlTaskJSONObject, Path path) {

		_updateDataControlTaskStatus(
			dataControlTaskJSONObject, DataControlTaskStatus.RUNNING);

		String emailAddress = dataControlTaskJSONObject.getString(
			"emailAddress");
		String type = dataControlTaskJSONObject.getString("type");

		try {
			if (StringUtils.equals(
					type, DataControlTaskType.ACCESS.toString())) {

				_exportData(dataControlTaskJSONObject, emailAddress, path);
			}
			else if (StringUtils.equals(
						type, DataControlTaskType.DELETE.toString())) {

				_deleteData(emailAddress);
			}
			else if (StringUtils.equals(
						type, DataControlTaskType.SUPPRESS.toString())) {

				_addSuppression(dataControlTaskJSONObject, emailAddress);
			}
			else if (StringUtils.equals(
						type, DataControlTaskType.UNSUPPRESS.toString())) {

				_deleteSuppression(emailAddress);
			}

			if (!StringUtils.equals(
					type, DataControlTaskType.ACCESS.toString())) {

				_exportDataControlTask(
					dataControlTaskJSONObject,
					new ZipFileBuilder(
						path + "/" + dataControlTaskJSONObject.getString("id") +
							".zip"));
			}
		}
		catch (Exception e) {
			_updateDataControlTaskStatus(
				dataControlTaskJSONObject, DataControlTaskStatus.ERROR);
		}

		_sendEmail(dataControlTaskJSONObject);

		return dataControlTaskJSONObject;
	}

	private void _sendEmail(JSONObject dataControlTaskJSONObject) {
		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery(
				"batchId", dataControlTaskJSONObject.getString("batchId"))
		).filter(
			BoolQueryBuilderUtil.should(
				QueryBuilders.termQuery(
					"status", DataControlTaskStatus.PENDING.toString())
			).should(
				QueryBuilders.termQuery(
					"status", DataControlTaskStatus.RUNNING.toString())
			)
		);

		if (!faroInfoElasticsearchInvoker.exists(
				"data-control-tasks", boolQueryBuilder)) {

			_emailHttp.sendEmail(dataControlTaskJSONObject);
		}
	}

	private void _updateDataControlTaskStatus(
		JSONObject dataControlTaskJSONObject,
		DataControlTaskStatus dataControlTaskStatus) {

		if (dataControlTaskStatus == DataControlTaskStatus.COMPLETED) {
			dataControlTaskJSONObject.put(
				"completeDate", DateUtil.newUTCDateString());
		}
		else if (dataControlTaskStatus == DataControlTaskStatus.RUNNING) {
			dataControlTaskJSONObject.put(
				"startDate", DateUtil.newUTCDateString());
		}

		dataControlTaskJSONObject.put(
			"status", dataControlTaskStatus.toString());

		faroInfoElasticsearchInvoker.update(
			"data-control-tasks", dataControlTaskJSONObject);
	}

	private void _writeToZip(
			String collectionName, ElasticsearchInvoker elasticsearchInvoker,
			QueryBuilder queryBuilder, ZipOutputStream zipOutputStream)
		throws Exception {

		DataExporter dataExporter = new RawDataExporter(
			collectionName, elasticsearchInvoker, _jsonFactory, zipOutputStream,
			queryBuilder);

		dataExporter.export();
	}

	private static final Log _log = LogFactory.getLog(DataControlNanite.class);

	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;
	private ElasticsearchInvoker _cerebroRawElasticsearchInvoker;
	private ElasticsearchInvoker _dxpRawElasticsearchInvoker;

	@Autowired
	private EmailHttp _emailHttp;

	@Value("${osb.asah.batch.curator.data.export.path:/export}")
	private String _exportPathName;

	private final JsonFactory _jsonFactory = new JsonFactory() {
		{
			disable(JsonGenerator.Feature.AUTO_CLOSE_TARGET);
		}
	};
	private ElasticsearchInvoker _salesforceRawElasticsearchInvoker;

}