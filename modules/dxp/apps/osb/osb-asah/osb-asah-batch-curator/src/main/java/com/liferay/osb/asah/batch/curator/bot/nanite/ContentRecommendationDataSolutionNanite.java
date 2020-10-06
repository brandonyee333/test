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

import com.amazonaws.services.personalize.AmazonPersonalize;
import com.amazonaws.services.personalize.AmazonPersonalizeClient;
import com.amazonaws.services.personalize.AmazonPersonalizeClientBuilder;
import com.amazonaws.services.personalize.model.BatchInferenceJob;
import com.amazonaws.services.personalize.model.BatchInferenceJobInput;
import com.amazonaws.services.personalize.model.BatchInferenceJobOutput;
import com.amazonaws.services.personalize.model.CreateBatchInferenceJobRequest;
import com.amazonaws.services.personalize.model.CreateBatchInferenceJobResult;
import com.amazonaws.services.personalize.model.CreateDatasetGroupRequest;
import com.amazonaws.services.personalize.model.CreateDatasetGroupResult;
import com.amazonaws.services.personalize.model.CreateDatasetImportJobRequest;
import com.amazonaws.services.personalize.model.CreateDatasetImportJobResult;
import com.amazonaws.services.personalize.model.CreateDatasetRequest;
import com.amazonaws.services.personalize.model.CreateDatasetResult;
import com.amazonaws.services.personalize.model.CreateSchemaRequest;
import com.amazonaws.services.personalize.model.CreateSchemaResult;
import com.amazonaws.services.personalize.model.CreateSolutionRequest;
import com.amazonaws.services.personalize.model.CreateSolutionResult;
import com.amazonaws.services.personalize.model.CreateSolutionVersionRequest;
import com.amazonaws.services.personalize.model.CreateSolutionVersionResult;
import com.amazonaws.services.personalize.model.DataSource;
import com.amazonaws.services.personalize.model.Dataset;
import com.amazonaws.services.personalize.model.DatasetGroup;
import com.amazonaws.services.personalize.model.DatasetGroupSummary;
import com.amazonaws.services.personalize.model.DatasetImportJob;
import com.amazonaws.services.personalize.model.DatasetSchemaSummary;
import com.amazonaws.services.personalize.model.DatasetSummary;
import com.amazonaws.services.personalize.model.DescribeBatchInferenceJobRequest;
import com.amazonaws.services.personalize.model.DescribeBatchInferenceJobResult;
import com.amazonaws.services.personalize.model.DescribeDatasetGroupRequest;
import com.amazonaws.services.personalize.model.DescribeDatasetGroupResult;
import com.amazonaws.services.personalize.model.DescribeDatasetImportJobRequest;
import com.amazonaws.services.personalize.model.DescribeDatasetImportJobResult;
import com.amazonaws.services.personalize.model.DescribeDatasetRequest;
import com.amazonaws.services.personalize.model.DescribeDatasetResult;
import com.amazonaws.services.personalize.model.DescribeSolutionRequest;
import com.amazonaws.services.personalize.model.DescribeSolutionResult;
import com.amazonaws.services.personalize.model.DescribeSolutionVersionRequest;
import com.amazonaws.services.personalize.model.DescribeSolutionVersionResult;
import com.amazonaws.services.personalize.model.ListDatasetGroupsRequest;
import com.amazonaws.services.personalize.model.ListDatasetGroupsResult;
import com.amazonaws.services.personalize.model.ListDatasetsRequest;
import com.amazonaws.services.personalize.model.ListDatasetsResult;
import com.amazonaws.services.personalize.model.ListSchemasRequest;
import com.amazonaws.services.personalize.model.ListSchemasResult;
import com.amazonaws.services.personalize.model.ListSolutionsRequest;
import com.amazonaws.services.personalize.model.ListSolutionsResult;
import com.amazonaws.services.personalize.model.S3DataConfig;
import com.amazonaws.services.personalize.model.Solution;
import com.amazonaws.services.personalize.model.SolutionSummary;
import com.amazonaws.services.personalize.model.SolutionVersion;

import com.liferay.osb.asah.batch.curator.bot.nanite.ml.SparkManager;
import com.liferay.osb.asah.common.constants.ServiceConstants;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class ContentRecommendationDataSolutionNanite extends BaseNanite {

	@PostConstruct
	public void init() {
		_amazonPersonalize = _buildAmazonPersonalize();
	}

	@Override
	public void logCompleted(
		JSONObject contextJSONObject, long duration, String osbAsahTaskId) {
	}

	@Override
	public void logStart(JSONObject contextJSONObject) {
	}

	@Override
	public void run(JSONObject contextJSONObject) throws Exception {
		JSONArray jsonArray = _faroInfoElasticsearchInvoker.get(
			"job-runs",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery(
					"job.type", "CONTENT_RECOMMENDATION_ITEM_SIMILARITY")
			).filter(
				QueryBuilders.termQuery("status", "RUNNING")
			).filter(
				QueryBuilders.termsQuery("step", "DATA_SOLUTION")
			));

		if (_log.isDebugEnabled()) {
			_log.debug(
				String.format(
					"There are %s running job executions", jsonArray.length()));
		}

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jobRunJSONObject = jsonArray.getJSONObject(i);

			try {
				_run(jobRunJSONObject);
			}
			catch (Exception e) {
				jobRunJSONObject.put("status", "FAILED");

				_updateJobRun(jobRunJSONObject);

				throw e;
			}
		}
	}

	@Override
	protected Log getLog() {
		return LogFactory.getLog(ContentRecommendationDataSolutionNanite.class);
	}

	private AmazonPersonalize _buildAmazonPersonalize() {
		AmazonPersonalizeClientBuilder amazonPersonalizeClientBuilder =
			AmazonPersonalizeClient.builder();

		amazonPersonalizeClientBuilder.withRegion(_awsPersonalizeRegion);

		return amazonPersonalizeClientBuilder.build();
	}

	private String _createBatchInferenceJobArn(
		String jobId, String solutionVersionArn) {

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Creating batch inference job with solution version ARN " +
					solutionVersionArn);
		}

		BatchInferenceJobInput batchInferenceJobInput =
			new BatchInferenceJobInput();

		batchInferenceJobInput.withS3DataSource(
			new S3DataConfig() {
				{
					withPath(
						String.format(
							"%s/%s/%s/items/", _awsPersonalizeDataLocation,
							ServiceConstants.LCP_PROJECT_ID, jobId));
				}
			});

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Using batch inference job input S3 data source " +
					batchInferenceJobInput.getS3DataSource());
		}

		BatchInferenceJobOutput batchInferenceJobOutput =
			new BatchInferenceJobOutput();

		batchInferenceJobOutput.withS3DataDestination(
			new S3DataConfig() {
				{
					withPath(
						String.format(
							"%s/%s/%s/inference_result/",
							_awsPersonalizeDataLocation,
							ServiceConstants.LCP_PROJECT_ID, jobId));
				}
			});

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Using batch inference job output S3 destination " +
					batchInferenceJobOutput.getS3DataDestination());
		}

		CreateBatchInferenceJobResult createBatchInferenceJobResult =
			_amazonPersonalize.createBatchInferenceJob(
				new CreateBatchInferenceJobRequest() {
					{
						withJobName(
							String.format(
								"batch_inference_job_%s",
								RandomStringUtils.randomAlphanumeric(4, 5)));
						withJobInput(batchInferenceJobInput);
						withJobOutput(batchInferenceJobOutput);
						withNumResults(
							_awsPersonalizeItemSimilarityInferenceResults);
						withRoleArn(_awsPersonalizeRoleArn);
						withSolutionVersionArn(solutionVersionArn);
					}
				});

		return createBatchInferenceJobResult.getBatchInferenceJobArn();
	}

	private String _createDatasetGroupArn(String jobId) {
		if (_log.isDebugEnabled()) {
			_log.debug("Creating dataset group with job ID " + jobId);
		}

		CreateDatasetGroupResult createDatasetGroupResult =
			_amazonPersonalize.createDatasetGroup(
				new CreateDatasetGroupRequest() {
					{
						withName(ServiceConstants.LCP_PROJECT_ID + "_" + jobId);
					}
				});

		return createDatasetGroupResult.getDatasetGroupArn();
	}

	private JSONObject _createSchemaFieldJSONObject(String name, String type) {
		return JSONUtil.put(
			"name", name
		).put(
			"type", type
		);
	}

	private String _createSolutionArn(String datasetGroupArn) {
		if (_log.isDebugEnabled()) {
			_log.debug(
				"Creating item similarity solution with dataset group ARN " +
					datasetGroupArn);
		}

		CreateSolutionResult createSolutionResult =
			_amazonPersonalize.createSolution(
				new CreateSolutionRequest() {
					{
						withDatasetGroupArn(datasetGroupArn);
						withName("item_similarity_solution");
						withRecipeArn(_awsPersonalizeItemSimilarityRecipeArn);
					}
				});

		return createSolutionResult.getSolutionArn();
	}

	private String _createSolutionVersionArn(String solutionArn) {
		if (_log.isDebugEnabled()) {
			_log.debug(
				"Creating solution version with solution ARN " + solutionArn);
		}

		CreateSolutionVersionResult createSolutionVersionResult =
			_amazonPersonalize.createSolutionVersion(
				new CreateSolutionVersionRequest() {
					{
						withSolutionArn(solutionArn);
					}
				});

		return createSolutionVersionResult.getSolutionVersionArn();
	}

	private String _createUserItemInteractionsDatasetArn(
		String datasetGroupArn) {

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Creating user item interactions dataset with dataset group " +
					"ARN " + datasetGroupArn);
		}

		CreateDatasetResult createDatasetResult =
			_amazonPersonalize.createDataset(
				new CreateDatasetRequest() {
					{
						withDatasetGroupArn(datasetGroupArn);
						withName("user_item_interactions_dataset");
						withDatasetType("interactions");
						withSchemaArn(
							_getOrCreateUserItemInteractionsSchemaArn());
					}
				});

		return createDatasetResult.getDatasetArn();
	}

	private String _createUserItemInteractionsDatasetImportJobArn(
		String jobId, String userItemInteractionsDatasetArn) {

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Creating user item interactions dataset import job with " +
					"dataset ARN " + userItemInteractionsDatasetArn);
		}

		String jobName = String.format(
			"user_item_interactions_dataset_import_job_%s",
			RandomStringUtils.randomAlphanumeric(4, 5));

		DataSource dataSource = new DataSource();

		dataSource.withDataLocation(
			String.format(
				"%s/%s/%s/user_item_interactions/", _awsPersonalizeDataLocation,
				ServiceConstants.LCP_PROJECT_ID, jobId));

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Using data source location " + dataSource.getDataLocation());
		}

		CreateDatasetImportJobResult createDatasetImportJobResult =
			_amazonPersonalize.createDatasetImportJob(
				new CreateDatasetImportJobRequest() {
					{
						withJobName(jobName);
						withDatasetArn(userItemInteractionsDatasetArn);
						withDataSource(dataSource);
						withRoleArn(_awsPersonalizeRoleArn);
					}
				});

		return createDatasetImportJobResult.getDatasetImportJobArn();
	}

	private String _createUserItemInteractionsDatasetSchemaArn() {
		if (_log.isDebugEnabled()) {
			_log.debug("Creating user item interactions dataset schema");
		}

		JSONObject schemaJSONObject = JSONUtil.put(
			"fields",
			JSONUtil.putAll(
				_createSchemaFieldJSONObject("EVENT_TYPE", "string"),
				_createSchemaFieldJSONObject("EVENT_VALUE", "float"),
				_createSchemaFieldJSONObject("ITEM_ID", "string"),
				_createSchemaFieldJSONObject("TIMESTAMP", "long"),
				_createSchemaFieldJSONObject("USER_ID", "string"))
		).put(
			"name", "Interactions"
		).put(
			"namespace", "com.amazonaws.personalize.schema"
		).put(
			"type", "record"
		).put(
			"version", "1.0"
		);

		CreateSchemaResult createSchemaResult = _amazonPersonalize.createSchema(
			new CreateSchemaRequest() {
				{
					withName("user_item_interactions");
					withSchema(schemaJSONObject.toString());
				}
			});

		return createSchemaResult.getSchemaArn();
	}

	private String _fetchDatasetGroupArn(String jobId) {
		ListDatasetGroupsResult listDatasetGroupsResult =
			_amazonPersonalize.listDatasetGroups(
				new ListDatasetGroupsRequest());

		List<DatasetGroupSummary> datasetGroupSummaries =
			listDatasetGroupsResult.getDatasetGroups();

		Stream<DatasetGroupSummary> stream = datasetGroupSummaries.stream();

		return stream.filter(
			datasetGroupSummary -> Objects.equals(
				datasetGroupSummary.getName(),
				ServiceConstants.LCP_PROJECT_ID + "_" + jobId)
		).findFirst(
		).map(
			DatasetGroupSummary::getDatasetGroupArn
		).orElse(
			null
		);
	}

	private String _fetchSolutionArn(String datasetGroupArn) {
		ListSolutionsResult listSolutionsResult =
			_amazonPersonalize.listSolutions(
				new ListSolutionsRequest() {
					{
						withDatasetGroupArn(datasetGroupArn);
					}
				});

		List<SolutionSummary> solutions = listSolutionsResult.getSolutions();

		Stream<SolutionSummary> stream = solutions.stream();

		return stream.filter(
			solutionSummary -> Objects.equals(
				solutionSummary.getName(), "item_similarity_solution")
		).findFirst(
		).map(
			SolutionSummary::getSolutionArn
		).orElse(
			null
		);
	}

	private String _fetchUserItemInteractionsDatasetArn(
		String datasetGroupArn) {

		ListDatasetsResult listDatasetsResult = _amazonPersonalize.listDatasets(
			new ListDatasetsRequest() {
				{
					withDatasetGroupArn(datasetGroupArn);
				}
			});

		List<DatasetSummary> datasetSummaries =
			listDatasetsResult.getDatasets();

		Stream<DatasetSummary> stream = datasetSummaries.stream();

		return stream.filter(
			datasetSummary -> Objects.equals(
				datasetSummary.getName(), "user_item_interactions_dataset")
		).findFirst(
		).map(
			DatasetSummary::getDatasetArn
		).orElse(
			null
		);
	}

	private String _fetchUserItemInteractionsDatasetSchemaArn() {
		ListSchemasResult listSchemasResult = _amazonPersonalize.listSchemas(
			new ListSchemasRequest());

		List<DatasetSchemaSummary> schemaSummaries =
			listSchemasResult.getSchemas();

		Stream<DatasetSchemaSummary> stream = schemaSummaries.stream();

		return stream.filter(
			datasetSchemaSummary -> Objects.equals(
				datasetSchemaSummary.getName(), "user_item_interactions")
		).findFirst(
		).map(
			DatasetSchemaSummary::getSchemaArn
		).orElse(
			null
		);
	}

	private BatchInferenceJob _getOrCreateBatchInferenceJob(
		JSONObject jobRunJSONObject, String solutionVersionArn) {

		JSONObject jobRunContextJSONObject = jobRunJSONObject.getJSONObject(
			"context");

		String batchInferenceJobArn = jobRunContextJSONObject.optString(
			"batchInferenceJobArn", null);

		if (batchInferenceJobArn == null) {
			JSONObject jobJSONObject = jobRunJSONObject.getJSONObject("job");

			batchInferenceJobArn = _createBatchInferenceJobArn(
				jobJSONObject.getString("id"), solutionVersionArn);

			jobRunContextJSONObject.put(
				"batchInferenceJobArn", batchInferenceJobArn);

			jobRunJSONObject.put("context", jobRunContextJSONObject);

			_updateJobRun(jobRunJSONObject);
		}

		DescribeBatchInferenceJobRequest describeBatchInferenceJobRequest =
			new DescribeBatchInferenceJobRequest();

		describeBatchInferenceJobRequest.withBatchInferenceJobArn(
			batchInferenceJobArn);

		DescribeBatchInferenceJobResult describeBatchInferenceJobResult =
			_amazonPersonalize.describeBatchInferenceJob(
				describeBatchInferenceJobRequest);

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Fetched batch inference job " +
					describeBatchInferenceJobResult.getBatchInferenceJob());
		}

		return describeBatchInferenceJobResult.getBatchInferenceJob();
	}

	private DatasetGroup _getOrCreateDatasetGroup(String jobId) {
		String datasetGroupArn = _fetchDatasetGroupArn(jobId);

		if (datasetGroupArn == null) {
			datasetGroupArn = _createDatasetGroupArn(jobId);
		}

		DescribeDatasetGroupRequest describeDatasetGroupRequest =
			new DescribeDatasetGroupRequest();

		describeDatasetGroupRequest.withDatasetGroupArn(datasetGroupArn);

		DescribeDatasetGroupResult describeDatasetGroupResult =
			_amazonPersonalize.describeDatasetGroup(
				describeDatasetGroupRequest);

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Fetched dataset group " +
					describeDatasetGroupResult.getDatasetGroup());
		}

		return describeDatasetGroupResult.getDatasetGroup();
	}

	private Solution _getOrCreateSolution(String datasetGroupArn) {
		String solutionArn = _fetchSolutionArn(datasetGroupArn);

		if (solutionArn == null) {
			solutionArn = _createSolutionArn(datasetGroupArn);
		}

		DescribeSolutionRequest describeSolutionRequest =
			new DescribeSolutionRequest();

		describeSolutionRequest.withSolutionArn(solutionArn);

		DescribeSolutionResult describeSolutionResult =
			_amazonPersonalize.describeSolution(describeSolutionRequest);

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Fetched solution " + describeSolutionResult.getSolution());
		}

		return describeSolutionResult.getSolution();
	}

	private SolutionVersion _getOrCreateSolutionVersion(
		JSONObject jobRunJSONObject, String solutionArn) {

		JSONObject jobRunContextJSONObject = jobRunJSONObject.getJSONObject(
			"context");

		String solutionVersionArn = jobRunContextJSONObject.optString(
			"solutionVersionArn", null);

		if (solutionVersionArn == null) {
			solutionVersionArn = _createSolutionVersionArn(solutionArn);

			jobRunContextJSONObject.put(
				"solutionVersionArn", solutionVersionArn);

			jobRunJSONObject.put("context", jobRunContextJSONObject);

			_updateJobRun(jobRunJSONObject);
		}

		DescribeSolutionVersionRequest describeSolutionVersionRequest =
			new DescribeSolutionVersionRequest();

		describeSolutionVersionRequest.withSolutionVersionArn(
			solutionVersionArn);

		DescribeSolutionVersionResult describeSolutionVersionResult =
			_amazonPersonalize.describeSolutionVersion(
				describeSolutionVersionRequest);

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Fetched solution version " +
					describeSolutionVersionResult.getSolutionVersion());
		}

		return describeSolutionVersionResult.getSolutionVersion();
	}

	private Dataset _getOrCreateUserItemInteractionsDataset(
		String datasetGroupArn) {

		String userItemInteractionsDatasetArn =
			_fetchUserItemInteractionsDatasetArn(datasetGroupArn);

		if (userItemInteractionsDatasetArn == null) {
			userItemInteractionsDatasetArn =
				_createUserItemInteractionsDatasetArn(datasetGroupArn);
		}

		DescribeDatasetRequest describeDatasetRequest =
			new DescribeDatasetRequest();

		describeDatasetRequest.withDatasetArn(userItemInteractionsDatasetArn);

		DescribeDatasetResult describeDatasetResult =
			_amazonPersonalize.describeDataset(describeDatasetRequest);

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Fetched user item interactions dataset " +
					describeDatasetResult.getDataset());
		}

		return describeDatasetResult.getDataset();
	}

	private DatasetImportJob _getOrCreateUserItemInteractionsDatasetImportJob(
		JSONObject jobRunJSONObject, String userItemInteractionsDatasetArn) {

		JSONObject jobRunContextJSONObject = jobRunJSONObject.getJSONObject(
			"context");

		String userItemInteractionsDatasetImportJobArn =
			jobRunContextJSONObject.optString(
				"userItemInteractionsDatasetImportJobArn", null);

		if (userItemInteractionsDatasetImportJobArn == null) {
			JSONObject jobJSONObject = jobRunJSONObject.getJSONObject("job");

			userItemInteractionsDatasetImportJobArn =
				_createUserItemInteractionsDatasetImportJobArn(
					jobJSONObject.getString("id"),
					userItemInteractionsDatasetArn);

			jobRunContextJSONObject.put(
				"userItemInteractionsDatasetImportJobArn",
				userItemInteractionsDatasetImportJobArn);

			jobRunJSONObject.put("context", jobRunContextJSONObject);

			_updateJobRun(jobRunJSONObject);
		}

		DescribeDatasetImportJobRequest describeDatasetImportJobRequest =
			new DescribeDatasetImportJobRequest();

		describeDatasetImportJobRequest.withDatasetImportJobArn(
			userItemInteractionsDatasetImportJobArn);

		DescribeDatasetImportJobResult describeDatasetImportJobResult =
			_amazonPersonalize.describeDatasetImportJob(
				describeDatasetImportJobRequest);

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Fetched user item interactions dataset import job " +
					describeDatasetImportJobResult.getDatasetImportJob());
		}

		return describeDatasetImportJobResult.getDatasetImportJob();
	}

	private String _getOrCreateUserItemInteractionsSchemaArn() {
		String userItemInteractionsDatasetSchemaArn =
			_fetchUserItemInteractionsDatasetSchemaArn();

		if (userItemInteractionsDatasetSchemaArn != null) {
			return userItemInteractionsDatasetSchemaArn;
		}

		return _createUserItemInteractionsDatasetSchemaArn();
	}

	private void _run(JSONObject jobRunJSONObject) {
		if (_log.isDebugEnabled()) {
			_log.debug("Processing " + jobRunJSONObject.toString());
		}

		JSONObject jobJSONObject = jobRunJSONObject.getJSONObject("job");

		DatasetGroup datasetGroup = _getOrCreateDatasetGroup(
			jobJSONObject.getString("id"));

		if (!Objects.equals(datasetGroup.getStatus(), "ACTIVE")) {
			_verifyResourceStatus(
				jobRunJSONObject, datasetGroup::getDatasetGroupArn,
				datasetGroup::getStatus);

			return;
		}

		Dataset userItemInteractionsDataset =
			_getOrCreateUserItemInteractionsDataset(
				datasetGroup.getDatasetGroupArn());

		if (!Objects.equals(
				userItemInteractionsDataset.getStatus(), "ACTIVE")) {

			_verifyResourceStatus(
				jobRunJSONObject, userItemInteractionsDataset::getDatasetArn,
				userItemInteractionsDataset::getStatus);

			return;
		}

		DatasetImportJob userItemInteractionsDatasetImportJob =
			_getOrCreateUserItemInteractionsDatasetImportJob(
				jobRunJSONObject, userItemInteractionsDataset.getDatasetArn());

		if (!Objects.equals(
				userItemInteractionsDatasetImportJob.getStatus(), "ACTIVE")) {

			_verifyResourceStatus(
				jobRunJSONObject,
				userItemInteractionsDatasetImportJob::getDatasetImportJobArn,
				userItemInteractionsDatasetImportJob::getStatus);

			return;
		}

		Solution solution = _getOrCreateSolution(
			datasetGroup.getDatasetGroupArn());

		if (!Objects.equals(solution.getStatus(), "ACTIVE")) {
			_verifyResourceStatus(
				jobRunJSONObject, solution::getSolutionArn,
				solution::getStatus);

			return;
		}

		SolutionVersion solutionVersion = _getOrCreateSolutionVersion(
			jobRunJSONObject, solution.getSolutionArn());

		if (!Objects.equals(solutionVersion.getStatus(), "ACTIVE")) {
			_verifyResourceStatus(
				jobRunJSONObject, solutionVersion::getSolutionVersionArn,
				solutionVersion::getStatus);

			return;
		}

		BatchInferenceJob batchInferenceJob = _getOrCreateBatchInferenceJob(
			jobRunJSONObject, solutionVersion.getSolutionVersionArn());

		if (!Objects.equals(batchInferenceJob.getStatus(), "ACTIVE")) {
			_verifyResourceStatus(
				jobRunJSONObject, batchInferenceJob::getBatchInferenceJobArn,
				batchInferenceJob::getStatus);

			return;
		}

		jobRunJSONObject.put("step", "DATA_OUTPUT");

		_updateJobRun(jobRunJSONObject);

		_sparkManager.submitJob(
			Arrays.asList(
				"--job-run-id", jobRunJSONObject.getString("id"),
				"--lcp-project-id", ServiceConstants.LCP_PROJECT_ID),
			"content_recommendation.yaml", Collections.emptyList(),
			"liferay.content_recommendation.ContentRecommendationApplication",
			Collections.emptyMap());
	}

	private void _updateJobRun(JSONObject jobRunJSONObject) {
		jobRunJSONObject.put("lastUpdatedDate", DateUtil.newUTCDateString());

		_faroInfoElasticsearchInvoker.update("job-runs", jobRunJSONObject);
	}

	private void _verifyResourceStatus(
		JSONObject jobRunJSONObject, Supplier<String> resourceIdSupplier,
		Supplier<String> resourceStatusSupplier) {

		if (_log.isDebugEnabled()) {
			_log.debug(
				String.format(
					"Resource {%s} has status %s", resourceIdSupplier.get(),
					resourceStatusSupplier.get()));
		}

		if (Objects.equals(resourceStatusSupplier.get(), "CREATE FAILED")) {
			jobRunJSONObject.put("status", "FAILED");

			_updateJobRun(jobRunJSONObject);
		}
	}

	private static final Log _log = LogFactory.getLog(
		ContentRecommendationDataSolutionNanite.class);

	private AmazonPersonalize _amazonPersonalize;

	@Value(
		"${aws.personalize.data.location:s3://analytics-cloud-aws-personalize}"
	)
	private String _awsPersonalizeDataLocation;

	@Value("${aws.personalize.item.similarity.inference.results:10}")
	private Integer _awsPersonalizeItemSimilarityInferenceResults;

	@Value(
		"${aws.personalize.item.similarity.recipe.arn:arn:aws:personalize:::recipe/aws-sims}"
	)
	private String _awsPersonalizeItemSimilarityRecipeArn;

	@Value("${aws.personalize.region:us-west-2}")
	private String _awsPersonalizeRegion;

	@Value("${aws.personalize.role.arn:}")
	private String _awsPersonalizeRoleArn;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private SparkManager _sparkManager;

}