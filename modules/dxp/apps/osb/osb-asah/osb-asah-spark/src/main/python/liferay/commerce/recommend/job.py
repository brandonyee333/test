#
# Copyright (c) 2000-present Liferay, Inc. All rights reserved.
#
# The contents of this file are subject to the terms of the Liferay Enterprise
# Subscription License ("License"). You may not use this file except in
# compliance with the License. You can obtain a copy of the License by
# contacting Liferay, Inc. See the License for the specific language governing
# permissions and limitations under the License, including but not limited to
# distribution rights of the Software.
#

import logging
from abc import abstractmethod

from pyspark import StorageLevel
from pyspark.ml import Pipeline
from pyspark.ml.recommendation import ALS
from pyspark.ml.tuning import ParamGridBuilder, CrossValidator
from pyspark.sql import DataFrame, Window
from pyspark.sql.functions import col, explode, when, coalesce, regexp_replace, lit, current_date, rank

from liferay.commerce.constants import Field, CPDefinitionField, ProductContentRecommendationConstants, \
 UserInteractionRecommendationConstants, ProductInteractionRecommendationConstants
from liferay.commerce.recommend.feature import CommerceFeatureExtractor, MAPEvaluator
from liferay.common.spark import BaseSparkJob

class BaseJSONFileReader(BaseSparkJob):
	def __init__(self, spark_application, file_pattern, table_name, cache=True):
		super(BaseJSONFileReader, self).__init__(spark_application)

		self.cache = cache

		self.file_pattern = file_pattern

		self.table_name = table_name

	def run(self):
		data_frame_reader = self.spark_session.read

		data_frame = data_frame_reader.json(self._get_bucket_path())

		data_frame = self._post_process(data_frame)

		data_frame.createOrReplaceTempView(self.table_name)

		if self.cache:
			self.spark_session.catalog.cacheTable(self.table_name)

	@abstractmethod
	def _post_process(self, data_frame: DataFrame) -> DataFrame:
		raise NotImplementedError()

	def _get_bucket_path(self):
		configuration = self.spark_application.configuration

		bucket = configuration.get('google.storage.bucket')

		lcp_project_id = self.spark_application.args.lcp_project_id

		data_source_id = configuration.get('dataSourceId')

		return "gs://{}/{}/{}/{}/".format(
		    bucket, lcp_project_id, data_source_id, self.file_pattern
		)

class BaseJSONFileWriter(BaseSparkJob):
	def __init__(self, spark_application, target_folder, table_name):
		super(BaseJSONFileWriter, self).__init__(spark_application)

		self.table_name = table_name

		self.target_folder = target_folder

	def run(self):
		path = self._get_bucket_path()

		table = self.spark_session.table(self.table_name)

		table = self.pre_process(table)

		table.write.format("json") \
			.mode("overwrite") \
			.save(path)

	def _get_bucket_path(self):
		configuration = self.spark_application.configuration

		bucket = configuration.get('google.storage.bucket')

		lcp_project_id = self.spark_application.args.lcp_project_id

		data_source_id = configuration.get('dataSourceId')

		return "gs://{}/{}/{}/{}/".format(
		    bucket, lcp_project_id, data_source_id, self.target_folder
		)

	def pre_process(self, data_frame: DataFrame) -> DataFrame:
		return data_frame

class ProductContentJSONFileReader(BaseJSONFileReader):

	FILE_PATTERN = 'com.liferay.headless.commerce.admin.catalog.dto.v1_0.Product'

	_DEFAULT_PRODUCT_COLUMNS = \
	 ["CAST(categories.id AS array<string>) " + Field.ASSET_CATEGORY_IDS,
	  "description.en_US AS " + Field.DESCRIPTION,
	  "metaDescription.en_US AS " + CPDefinitionField.META_DESCRIPTION,
	  "metaTitle.en_US AS " + CPDefinitionField.META_TITLE,
	  "name.en_US AS " + Field.NAME,
	  "productType AS " + CPDefinitionField.PRODUCT_TYPE_NAME,
	  "id"]

	_SPECIFICATION_PREFIX = "SPECIFICATION_"

	def __init__(self, spark_application):
		super(ProductContentJSONFileReader, self).__init__(
		    spark_application, self.FILE_PATTERN, 'product_data'
		)

	def _post_process(self, data_frame: DataFrame) -> DataFrame:
		product_specification_ids = self._get_specification_option_ids(
		    data_frame.select(
		        explode('productSpecifications').alias('productSpecifications')
		    )
		)

		product_specifications = data_frame.select(
		    "id",
		    explode("productSpecifications").alias("productSpecifications")
		)

		deafault_locale = self.spark_application.configuration.get(
		    "commerce.ml.locale"
		)

		specification_options = self._get_spec_options(
		    product_specifications, product_specification_ids, deafault_locale
		)

		# Read base product information

		products = data_frame.selectExpr(self._DEFAULT_PRODUCT_COLUMNS)

		cp_definitions = products.join(
				specification_options, on=["id"], how='left_outer'
			).withColumnRenamed(
				"id", Field.ENTRY_CLASS_PK
			)

		return cp_definitions

	def _get_specification_option_ids(self, data_frame):
		data_frame = data_frame.select('productSpecifications.specificationId')

		data_frame = data_frame.distinct()

		return data_frame.rdd.map(lambda r: r[0]).collect()

	def _get_spec_options(self, data_frame, spec_ids, locale):
		spec_column = []

		for spec_id in spec_ids:
			col_name = self._SPECIFICATION_PREFIX + str(spec_id)

			s = data_frame.withColumn(
			    col_name,
			    when(
			        data_frame['productSpecifications.specificationId'] ==
			        spec_id,
			        col('productSpecifications.value').getItem(locale)
			    )
			)

			s = s.dropna(subset=col_name)
			s = s.drop('productSpecifications')

			spec_column += [s]

		res = data_frame.select("id") \
			.drop('productSpecifications') \
			.distinct()

		for spec_df in spec_column:
			res = res.join(spec_df, on=['id'], how='left_outer')

		return res

class ProductContentPipelineJob(BaseSparkJob):

	_COMMERCE_EXCLUDE_CP_COLUMNS = [
	    Field.ENTRY_CLASS_PK, CPDefinitionField.META_KEYWORDS,
	    CPDefinitionField.SPECIFICATION_OPTIONS_NAMES,
	    CPDefinitionField.SPECIFICATION_OPTIONS_IDS
	]

	_REGEX_EXPRESSION = r'[^\w\s]'

	def __init__(self, spark_application):
		super(ProductContentPipelineJob, self).__init__(spark_application)

		self.commerce_feature_extractor = CommerceFeatureExtractor()

	def run(self):
		product_data = self.spark_session.table('product_data')

		feature_column_names = \
			[c for c in product_data.columns if c not in self._COMMERCE_EXCLUDE_CP_COLUMNS]

		product_data = product_data \
			.withColumn(
		  		Field.DESCRIPTION,
		  		coalesce(col(Field.DESCRIPTION), col(Field.NAME))
			 ).withColumn(
				Field.DESCRIPTION,
				regexp_replace(Field.DESCRIPTION, self._REGEX_EXPRESSION, '')
			).persist(StorageLevel.MEMORY_AND_DISK_2)

		extract_features = \
			self.commerce_feature_extractor.extract_features(feature_column_names)

		pipeline = Pipeline(stages=extract_features['stages'])

		pipeline_model = pipeline.fit(product_data)

		pipeline_data = pipeline_model.transform(product_data) \
			.select(Field.ENTRY_CLASS_PK, 'features')

		pipeline_data.createOrReplaceTempView('pipeline_data')

		self.spark_session.catalog.cacheTable('pipeline_data')

class ProductContentRecommendationJob(BaseSparkJob):
	def __init__(self, spark_application):
		super(ProductContentRecommendationJob, self).__init__(spark_application)

	def run(self):
		pipeline_data = self.spark_session.table('pipeline_data')

		cross_join_data = pipeline_data.crossJoin(
		    pipeline_data.selectExpr(
		        'entryClassPK as entryClassPK2', 'features as features2'
		    )
		)

		score_function = \
			self.spark_application.configuration.get(
		  		ProductContentRecommendationConstants.
					PRODUCT_CONTENT_RECOMMENDATION_SCORE_FUNCTION
			)

		score_data = cross_join_data.selectExpr(
				'*', score_function + '(features,features2) AS score'
			)

		window = Window.partitionBy(
				col('entryClassPK')
			).orderBy(
				col('score').desc()
			)

		recommendations = score_data.select(
				'*',
				rank().over(window).alias('rank')
			).filter(
				'rank > 1 AND rank <= 11'
			).withColumn(
				'createDate', current_date()
			).withColumn(
				'jobId', lit(self.spark_application.args.job_run_id)
			).withColumn(
				'entryClassPK', col('entryClassPK').cast('long')
			).withColumn(
			   'recommendedEntryClassPK',
			   col('entryClassPK2').cast('long')
			).drop(
				'entryClassPK2'
			).drop(
				'features'
			).drop(
				'features2'
			)

		recommendations.createOrReplaceTempView(
		    'product_content_recommendations'
		)

		self.spark_session.catalog.cacheTable('product_content_recommendations')

class ProductContentRecommendationJSONFileWriter(BaseJSONFileWriter):

	_TARGET_FOLDER = \
		'com.liferay.headless.commerce.machine.learning.dto.v1_0.ProductContentRecommendation'

	def __init__(self, spark_application):
		super().__init__(
		    spark_application, self._TARGET_FOLDER,
		    'product_content_recommendations'
		)

class ProductInteractionJSONFileReader(BaseJSONFileReader):

	FILE_PATTERN = 'com.liferay.headless.commerce.admin.catalog.dto.v1_0.Product'

	def __init__(self, spark_application):
		super().__init__(
		    spark_application, self.FILE_PATTERN, 'product_interaction_table'
		)

	def _post_process(self, data_frame: DataFrame) -> DataFrame:
		return data_frame.selectExpr(
		    "id AS CPDefinitionId", "categories.id AS assetCategoryIds",
		    "skus.sku AS sku_list"
		)

class OrderInteractionJSONFileReader(BaseJSONFileReader):

	FILE_PATTERN = 'com.liferay.headless.commerce.admin.order.dto.v1_0.Order'

	def __init__(self, spark_application):
		super().__init__(
		    spark_application, self.FILE_PATTERN, 'order_interaction_table'
		)

	def _post_process(self, data_frame: DataFrame) -> DataFrame:
		return data_frame.select(
		    col("accountId").alias("commerceAccountId"), "createDate",
		    explode("orderItems.sku").alias("sku")
		)

class UserInteractionDataPreparationSparkJob(BaseSparkJob):
	def __init__(self, spark_application):
		super().__init__(spark_application)

	def run(self):
		product_interaction_data_frame = self.spark_session.table(
		    'product_interaction_table'
		)

		cp_instance_data_frame = product_interaction_data_frame.select(
		    'CPDefinitionId',
		    explode('sku_list').alias('sku')
		)

		order_interaction_data_frame = self.spark_session.table(
		    'order_interaction_table'
		)

		user_item_ratings = order_interaction_data_frame.join(
				cp_instance_data_frame, on=["sku"]
			).drop(
				"sku"
			).withColumn('rating', lit(1.0))

		user_item_ratings.createOrReplaceTempView('user_item_rating_table')

		self.spark_session.catalog.cacheTable('user_item_rating_table')

class UserInteractionCollaborativeFilteringSparkJob(BaseSparkJob):
	def __init__(self, spark_application):
		super().__init__(spark_application)

		self._DEFAULT_COUNT_APPROX_TIMEOUT = 5000
		self._log = logging.getLogger(self.__class__.__name__)

	def run(self):
		user_item_data_frame = self.spark_session.table(
		    'user_item_rating_table'
		)

		train_data_frame, test_data_frame = self._split_train_test(
		    user_item_data_frame
		)

		training_pipeline = self._get_training_pipeline()

		training_pipeline_model = training_pipeline.fit(train_data_frame)

		best_model = training_pipeline_model.bestModel

		# Model parameters

		if self._log.isEnabledFor(logging.INFO):
			self._log.info("Best model details:")
			self._log.info(
			    "  Max Iterations: {}".format(
			        best_model._java_obj.parent().getMaxIter()
			    )
			)
			self._log.info(
			    "  Reg Parameter: {}".format(
			        best_model._java_obj.parent().getRegParam()
			    )
			)
			self._log.info(
			    "  Alpha: {}".format(best_model._java_obj.parent().getAlpha())
			)
			self._log.info(
			    "  Rank: {}".format(best_model._java_obj.parent().getRank())
			)
			self._log.info(
			    "  Non-negative feedback: {}".format(
			        best_model._java_obj.parent().getNonnegative()
			    )
			)

		# Model performance

		if self._log.isEnabledFor(logging.INFO):
			test_map = self._get_evaluator().evaluate(
			    best_model.transform(test_data_frame)
			)

			self._log.info("Model performance on TEST set: {}".format(test_map))

			train_map = self._get_evaluator().evaluate(
			    best_model.transform(train_data_frame)
			)

			self._log.info(
			    "Model performance on TRAIN set: {}".format(train_map)
			)

		requested_catalog_coverage = self._get_requested_catalog_coverage()

		self._log.info(
		    "Generating recommendations of {} items per user".
		    format(requested_catalog_coverage)
		)

		recommendations = best_model.recommendForAllUsers(
		    requested_catalog_coverage
		)

		context_recommendations = self._get_context_recommendations(
		    recommendations
		)

		context_recommendations.createOrReplaceTempView(
		    'context_user_interaction_recommendation'
		)

		self.spark_session.catalog.cacheTable(
		    'context_user_interaction_recommendation'
		)

		product_interaction_recommendation_enable = \
			self.spark_application_configuration.get(
				ProductInteractionRecommendationConstants.
					PRODUCT_INTERACTION_RECOMMENDATION_ENABLE
			)

		if product_interaction_recommendation_enable:
			item_factors = best_model.itemFactors

			item_factors.createOrReplaceTempView('item_factors')

			self.spark_session.catalog.cacheTable('item_factors')

	def _split_train_test(self, user_item_data_frame):
		train_split_ratio = self.spark_application.configuration.get(
		    UserInteractionRecommendationConstants.
		    USER_INTERACTION_RECOMMENDATION_TRAIN_SPLIT_RATIO
		)

		train, test = user_item_data_frame.randomSplit(
		    [train_split_ratio, 1 - train_split_ratio]
		)

		train.cache()

		test.cache()

		return train, test

	def _get_training_pipeline(self):
		configuration = self.spark_application.configuration

		als = ALS(
		    userCol='commerceAccountId',
		    itemCol='CPDefinitionId',
		    ratingCol='rating',
		    coldStartStrategy='drop',
		    implicitPrefs=True,
		    nonnegative=True
		)

		als_checkpoint_interval = configuration.get(
		    UserInteractionRecommendationConstants.
		    USER_INTERACTION_RECOMMENDATION_ALS_CHECKPOINT_INTERVAL
		)
		als.setCheckpointInterval(als_checkpoint_interval)

		tuning_max_iterations = configuration.get_list(
		    UserInteractionRecommendationConstants.
		    USER_INTERACTION_RECOMMENDATION_TUNING_MAX_ITERATION
		)

		tuning_reg_parameter = configuration.get_list(
		    UserInteractionRecommendationConstants.
		    USER_INTERACTION_RECOMMENDATION_TUNING_REGULARIZATION_PARAMETER
		)

		tuning_alpha = configuration.get_list(
		    UserInteractionRecommendationConstants.
		    USER_INTERACTION_RECOMMENDATION_TUNING_ALPHA
		)

		tuning_rank = configuration.get_list(
		    UserInteractionRecommendationConstants.
		    USER_INTERACTION_RECOMMENDATION_TUNING_RANK
		)

		param_grid = ParamGridBuilder().addGrid(
				als.maxIter, tuning_max_iterations
			).addGrid(
				als.regParam, tuning_reg_parameter
			).addGrid(
				als.alpha, tuning_alpha
			).addGrid(
				als.rank, tuning_rank
			).build()

		cross_validator_num_folds = int(
		    configuration.get(
		        UserInteractionRecommendationConstants.
		        USER_INTERACTION_RECOMMENDATION_TUNING_CROSS_VALIDATOR_NUM_FOLDS,
		        3
		    )
		)

		cross_validator_parallelism = int(
		    configuration.get(
		        UserInteractionRecommendationConstants.
		        USER_INTERACTION_RECOMMENDATION_TUNING_CROSS_VALIDATOR_PARALLELISM,
		        2
		    )
		)

		cross_validator = CrossValidator(
		    estimator=als,
		    estimatorParamMaps=param_grid,
		    evaluator=self._get_evaluator(),
		    numFolds=cross_validator_num_folds,
		    parallelism=cross_validator_parallelism
		)

		return cross_validator

	def _get_evaluator(self):
		return MAPEvaluator(
		    query_col='commerceAccountId',
		    label_col='rating',
		    prediction_col='prediction'
		)

	def _get_requested_catalog_coverage(self):
		product_interaction_data_frame = self.spark_session.table(
		    'product_interaction_table'
		)

		catalog_coverage = float(
		    self.spark_application.configuration.get(
		        UserInteractionRecommendationConstants.
		        USER_INTERACTION_RECOMMENDATION_CATALOG_COVERAGE
		    )
		)

		catalog_count = product_interaction_data_frame.rdd.countApprox(
		    self._DEFAULT_COUNT_APPROX_TIMEOUT
		)

		return int(catalog_count * catalog_coverage)

	def _get_context_recommendations(self, recommendations):

		product_data_frame = self.spark_session.table(
		    'product_interaction_table'
		)

		product_data_frame = product_data_frame.select(
		    'CPDefinitionId', 'assetCategoryIds'
		)

		return recommendations.select(
				'commerceAccountId',
				explode('recommendations').alias('recommendations')
			).selectExpr(
				'commerceAccountId',
				'recommendations.CPDefinitionId as CPDefinitionId',
				'recommendations.rating as score'
			).join(
				product_data_frame, on='CPDefinitionId'
			)

class ProductInteractionRecommendationSparkJob(BaseSparkJob):
	def __init__(self, spark_application):
		super().__init__(spark_application)

	def run(self):
		configuration = self.spark_application_configuration

		# Item-Item Collaborative Filtering

		item_factors = self.spark_session.table('item_factors')

		item_factors_2 = item_factors.selectExpr(
		    'id as id2', 'features as features2'
		)

		product_interaction_recommendations = item_factors.crossJoin(
			item_factors_2
		)

		product_interaction_recommendations = \
			product_interaction_recommendations.selectExpr(
					'id', 'toDenseVector(features) as features', 'id2',
					'toDenseVector(features2) as features2'
				)

		score_function = configuration.get(
			ProductInteractionRecommendationConstants
				.PRODUCT_INTERACTION_RECOMMENDATION_SCORE_FUNCTION
		)

		product_interaction_recommendations = \
			product_interaction_recommendations.selectExpr(
					'*', score_function + '(features, features2) AS score'
				)

		window = Window.partitionBy(col('id')).orderBy(col('score').desc())

		product_interaction_recommendations = \
			product_interaction_recommendations.select(
				'*', rank().over(window).alias('rank')
			).filter(
				'rank > 1 AND rank <= 11'
			)

		spark_context = self.spark_session.sparkContext

		product_interaction_recommendations = \
			product_interaction_recommendations.withColumn(
					'createDate', current_date()
				).withColumn(
					'jobId', lit(spark_context.applicationId)
				).withColumn(
					'entryClassPK', col('id').cast('long')
				).withColumn(
					'recommendedEntryClassPK', col('id2').cast('long')
				).drop(
					'features'
				).drop(
					'features2'
				).drop(
					'id'
				).drop('id2')

		product_interaction_recommendations.createOrReplaceTempView(
		    "product_interaction_recommendations"
		)

		self.spark_session.catalog.cacheTable(
		    'product_interaction_recommendations'
		)

class ContextUserInteractionRecommendationJSONFileWriterSparkJob(
    BaseJSONFileWriter
):

	_TARGET_FOLDER = \
		'com.liferay.headless.commerce.machine.learning.dto.v1_0.UserRecommendation'

	def __init__(self, spark_application):
		super().__init__(
		    spark_application, self._TARGET_FOLDER,
		    'context_user_interaction_recommendation'
		)

	def pre_process(self, data_frame: DataFrame) -> DataFrame:
		return data_frame.withColumn(
					'createDate', current_date()
				).withColumn(
					'jobId', lit(self.spark_application.args.job_run_id)
				).withColumn(
					'entryClassPK', col('commerceAccountId').cast('long')
				).withColumn(
		              'recommendedEntryClassPK',
		              col('CPDefinitionId').cast('long')
				).drop(
					'commerceAccountId'
				).drop('CPDefinitionId')

class ProductInteractionRecommendationJSONFileWriterSparkJob(
    BaseJSONFileWriter
):

	_TARGET_FOLDER = \
		'com.liferay.headless.commerce.machine.learning.dto.v1_0.ProductInteractionRecommendation'

	def __init__(self, spark_application):
		super().__init__(
		    spark_application, self._TARGET_FOLDER,
		    'product_interaction_recommendations'
		)
