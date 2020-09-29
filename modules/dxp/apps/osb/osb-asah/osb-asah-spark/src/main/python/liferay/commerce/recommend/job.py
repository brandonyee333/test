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

from abc import abstractmethod

from liferay.commerce.recommend.feature import CommerceFeatureExtractor, MAPEvaluator
from liferay.common.spark import BaseSparkJob

from pyspark import StorageLevel
from pyspark.ml import Pipeline
from pyspark.ml.recommendation import ALS
from pyspark.ml.tuning import ParamGridBuilder, CrossValidator
from pyspark.sql import Window
from pyspark.sql.functions import col, explode, when, coalesce, regexp_replace, lit, current_date, rank

import logging

class BaseDataFrameReaderSparkJob(BaseSparkJob):
	def __init__(
	    self,
	    spark_application,
	    file_pattern,
	    table_name,
	    cache=True,
	    latest=True
	):
		super(BaseDataFrameReaderSparkJob, self).__init__(spark_application)

		self.cache = cache
		self.file_pattern = file_pattern
		self.latest = latest
		self.table_name = table_name

	def run(self):
		data_frame_reader = self.spark_session.read

		data_frame = data_frame_reader.json(self._get_bucket_path())

		data_frame = self._post_process(data_frame)

		data_frame.createOrReplaceTempView(self.table_name)

		if self.cache:
			self.spark_session.catalog.cacheTable(self.table_name)

	def _get_bucket_path(self):
		configuration = self.spark_application_configuration

		bucket_path = "{}/{}/{}/{}/".format(
		    configuration.get('google.storage.bucket'),
		    self.spark_application.args.lcp_project_id,
		    configuration.get('dataSourceId'), self.file_pattern
		)

		if self.latest:
			jvm = self.spark_session._jvm
			spark_context = self.spark_session.sparkContext

			file_system = jvm.org.apache.hadoop.fs.FileSystem

			file_system_instance = file_system.get(
			    jvm.java.net.URI(bucket_path),
			    spark_context._jsc.hadoopConfiguration()
			)

			file_status_list = file_system_instance.listStatus(
			    jvm.org.apache.hadoop.fs.Path(bucket_path)
			)

			file_status_list_sorted = sorted(
			    file_status_list, key=lambda f: f.getModificationTime()
			)

			bucket_path = str(file_status_list_sorted[-1].getPath())

		self.spark_application.log.info(
		    "Loading data from: {}".format(bucket_path)
		)

		return bucket_path

	@abstractmethod
	def _post_process(self, data_frame):
		raise NotImplementedError()

class BaseDataFrameWriterSparkJob(BaseSparkJob):
	def __init__(self, spark_application, target_folder, table_name):
		super(BaseDataFrameWriterSparkJob, self).__init__(spark_application)

		self.table_name = table_name
		self.target_folder = target_folder

	def pre_process(self, data_frame):
		return data_frame

	def run(self):
		data_frame = self.spark_session.table(self.table_name)

		data_frame = self.pre_process(data_frame)

		data_frame_writer = data_frame.write

		data_frame_writer.format("json").mode("overwrite").save(
		    self._get_bucket_path()
		)

	def _get_bucket_path(self):
		configuration = self.spark_application_configuration

		return "{}/{}/{}/{}/".format(
		    configuration.get('google.storage.bucket'),
		    self.spark_application.args.lcp_project_id,
		    configuration.get('dataSourceId'), self.target_folder
		)

class ContextUserInteractionRecommendationDataFrameWriterSparkJob(
    BaseDataFrameWriterSparkJob
):
	def __init__(self, spark_application):
		super().__init__(
		    spark_application,
		    'com.liferay.headless.commerce.machine.learning.dto.v1_0.'
		    'UserRecommendation', 'context_user_interaction_recommendation'
		)

	def pre_process(self, data_frame):
		data_frame = data_frame.withColumn('createDate', current_date())
		data_frame = data_frame.withColumn(
		    'jobId',
		    lit(self.spark_application_configuration.get('spark.app.id'))
		)
		data_frame = data_frame.withColumn(
		    'entryClassPK',
		    col('commerceAccountId').cast('long')
		)
		data_frame = data_frame.withColumn(
		    'recommendedEntryClassPK',
		    col('CPDefinitionId').cast('long')
		)

		data_frame = data_frame.drop('commerceAccountId')
		data_frame = data_frame.drop('CPDefinitionId')

		return data_frame

class ProductContentDataFrameReaderSparkJob(BaseDataFrameReaderSparkJob):
	def __init__(self, spark_application):
		super(ProductContentDataFrameReaderSparkJob, self).__init__(
		    spark_application,
		    'com.liferay.headless.commerce.admin.catalog.dto.v1_0.Product',
		    'products'
		)

	def _post_process(self, product_data_frame):
		locale = self.spark_application_configuration.get("commerce.ml.locale")

		products_data_frame = product_data_frame.selectExpr(
		    "CAST(categories.id AS array<string>) assetCategoryIds",
		    "description.{} AS description".format(locale),
		    "metaDescription.{} AS metaDescription".format(locale),
		    "metaTitle.{} AS metaTitle".format(locale),
		    "name.{} AS name".format(locale), "productType AS productTypeName",
		    "id"
		)

		return products_data_frame.join(
		    self._get_product_specifications_data_frame(product_data_frame),
		    on=["id"],
		    how='left_outer'
		).withColumnRenamed("id", 'entryClassPK')

	def _get_product_specifications_data_frame(self, products_data_frame):
		product_specification_ids = self._get_product_specification_ids(
		    products_data_frame
		)

		product_specifications_data_frame = products_data_frame.select(
		    "id",
		    explode("productSpecifications").alias("productSpecifications")
		)

		locale = self.spark_application_configuration.get("commerce.ml.locale")

		specification_data_frames = []

		for product_specification_id in product_specification_ids:
			specification_column_name = "SPECIFICATION_" + str(
			    product_specification_id
			)

			data_frame = product_specifications_data_frame.withColumn(
			    specification_column_name,
			    when(
			        product_specifications_data_frame[
			            'productSpecifications.specificationId'] ==
			        product_specification_id,
			        col('productSpecifications.value').getItem(locale)
			    )
			)

			data_frame = data_frame.dropna(subset=specification_column_name)
			data_frame = data_frame.drop('productSpecifications')

			specification_data_frames += [data_frame]

		product_specification_data_frame = products_data_frame.select(
		    "id"
		).distinct()

		for specification_data_frame in specification_data_frames:
			product_specification_data_frame = product_specification_data_frame.join(
			    specification_data_frame, on=['id'], how='left_outer'
			)

		return product_specification_data_frame

	def _get_product_specification_ids(self, products_data_frame):
		data_frame = products_data_frame.select(
		    explode('productSpecifications').alias('productSpecifications')
		)

		data_frame = data_frame.select('productSpecifications.specificationId')

		data_frame = data_frame.distinct()

		return data_frame.rdd.map(lambda r: r[0]).collect()

class ProductContentPipelineSparkJob(BaseSparkJob):
	def __init__(self, spark_application):
		super(ProductContentPipelineSparkJob, self).__init__(spark_application)

		self._commerce_exclude_cp_columns = [
		    'entryClassPK', 'metaKeywords', 'specificationOptionsNames',
		    'specificationOptionsIds'
		]
		self._commerce_feature_extractor = CommerceFeatureExtractor()

	def run(self):
		products_data_frame = self.spark_session.table('products')

		feature_column_names = [
		    column for column in products_data_frame.columns
		    if column not in self._commerce_exclude_cp_columns
		]

		products_data_frame = products_data_frame.withColumn(
		    'description', coalesce(col('description'), col('name'))
		).withColumn(
		    'description', regexp_replace('description', r'[^\w\s]', '')
		).persist(StorageLevel.MEMORY_AND_DISK_2)

		extracted_features = self._commerce_feature_extractor.extract_features(
		    feature_column_names
		)

		pipeline = Pipeline(stages=extracted_features['stages'])

		pipeline_model = pipeline.fit(products_data_frame)

		pipeline_data = pipeline_model.transform(products_data_frame).select(
		    'entryClassPK', 'features'
		)

		pipeline_data.createOrReplaceTempView('pipeline_data')

		self.spark_session.catalog.cacheTable('pipeline_data')

class ProductContentRecommendationDataFrameWriter(BaseDataFrameWriterSparkJob):
	def __init__(self, spark_application):
		super().__init__(
		    spark_application,
		    'com.liferay.headless.commerce.machine.learning.dto.v1_0.'
		    'ProductContentRecommendation', 'product_content_recommendations'
		)

class ProductContentRecommendationSparkJob(BaseSparkJob):
	def __init__(self, spark_application):
		super(ProductContentRecommendationSparkJob,
		      self).__init__(spark_application)

	def run(self):
		pipeline_data_frame = self.spark_session.table('pipeline_data')

		cross_join_data_frame = pipeline_data_frame.crossJoin(
		    pipeline_data_frame.selectExpr(
		        'entryClassPK as entryClassPK2', 'features as features2'
		    )
		)

		score_function = self.spark_application_configuration.get(
		    'product.content.recommendation.score.function'
		)

		score_data_frame = cross_join_data_frame.selectExpr(
		    '*', score_function + '(features, features2) AS score'
		)

		window = Window.partitionBy(col('entryClassPK')).orderBy(
		    col('score').desc()
		)

		recommendations_data_frame = score_data_frame.select(
		    '*',
		    rank().over(window).alias('rank')
		)

		recommendations_data_frame = recommendations_data_frame.filter(
		    'rank > 1 AND rank <= 11'
		)

		recommendations_data_frame = recommendations_data_frame.withColumn(
		    'createDate', current_date()
		)
		recommendations_data_frame = recommendations_data_frame.withColumn(
		    'jobId',
		    lit(self.spark_application_configuration.get('spark.app.id'))
		)
		recommendations_data_frame = recommendations_data_frame.withColumn(
		    'entryClassPK',
		    col('entryClassPK').cast('long')
		)
		recommendations_data_frame = recommendations_data_frame.withColumn(
		    'recommendedEntryClassPK',
		    col('entryClassPK2').cast('long')
		)

		recommendations_data_frame = recommendations_data_frame.drop(
		    'entryClassPK2'
		).drop('features').drop('features2')

		recommendations_data_frame.createOrReplaceTempView(
		    'product_content_recommendations'
		)

		self.spark_session.catalog.cacheTable('product_content_recommendations')

class ProductInteractionDataFrameReaderSparkJob(BaseDataFrameReaderSparkJob):
	def __init__(self, spark_application):
		super().__init__(
		    spark_application,
		    'com.liferay.headless.commerce.admin.catalog.dto.v1_0.Product',
		    'product_interactions'
		)

	def _post_process(self, data_frame):
		return data_frame.selectExpr(
		    "id AS CPDefinitionId", "categories.id AS assetCategoryIds",
		    "skus.sku AS sku_list"
		)

class ProductInteractionRecommendationDataFrameWriterSparkJob(
    BaseDataFrameWriterSparkJob
):
	def __init__(self, spark_application):
		super().__init__(
		    spark_application,
		    'com.liferay.headless.commerce.machine.learning.dto.v1_0.'
		    'ProductInteractionRecommendation',
		    'product_interaction_recommendations'
		)

class ProductInteractionRecommendationSparkJob(BaseSparkJob):
	def __init__(self, spark_application):
		super().__init__(spark_application)

	def run(self):
		configuration = self.spark_application_configuration
		spark_context = self.spark_session.sparkContext

		# Item-Item Collaborative Filtering

		item_factors_data_frame = self.spark_session.table('item_factors')

		score_function = configuration.get(
		    'product.interaction.recommendation.score.function'
		)

		window = Window.partitionBy(col('id')).orderBy(col('score').desc())

		recommendations_data_frame = item_factors_data_frame.crossJoin(
		    item_factors_data_frame.selectExpr(
		        'id as id2', 'features as features2'
		    )
		)

		recommendations_data_frame = recommendations_data_frame.selectExpr(
		    'id', 'toDenseVector(features) as features', 'id2',
		    'toDenseVector(features2) as features2'
		)

		recommendations_data_frame = recommendations_data_frame.selectExpr(
		    '*', score_function + '(features, features2) AS score'
		)

		recommendations_data_frame = recommendations_data_frame.select(
		    '*',
		    rank().over(window).alias('rank')
		)

		recommendations_data_frame = recommendations_data_frame.filter(
		    'rank > 1 AND rank <= 11'
		)

		recommendations_data_frame = recommendations_data_frame.withColumn(
		    'createDate', current_date()
		)
		recommendations_data_frame = recommendations_data_frame.withColumn(
		    'jobId', lit(spark_context.applicationId)
		)
		recommendations_data_frame = recommendations_data_frame.withColumn(
		    'entryClassPK',
		    col('id').cast('long')
		)
		recommendations_data_frame = recommendations_data_frame.withColumn(
		    'recommendedEntryClassPK',
		    col('id2').cast('long')
		)

		recommendations_data_frame = recommendations_data_frame.drop('features')
		recommendations_data_frame = recommendations_data_frame.drop(
		    'features2'
		)
		recommendations_data_frame = recommendations_data_frame.drop('id')
		recommendations_data_frame = recommendations_data_frame.drop('id2')

		recommendations_data_frame.createOrReplaceTempView(
		    "product_interaction_recommendations"
		)

		self.spark_session.catalog.cacheTable(
		    'product_interaction_recommendations'
		)

class OrderInteractionDataFrameReaderSparkJob(BaseDataFrameReaderSparkJob):
	def __init__(self, spark_application):
		super().__init__(
		    spark_application,
		    'com.liferay.headless.commerce.admin.order.dto.v1_0.Order',
		    'order_interactions'
		)

	def _post_process(self, data_frame):
		return data_frame.selectExpr(
		    'accountId as commerceAccountId', 'createDate',
		    'explode(orderItems.sku) as sku'
		)

class UserInteractionCollaborativeFilteringSparkJob(BaseSparkJob):
	def __init__(self, spark_application):
		super().__init__(spark_application)

		self._default_count_approx_timeout = 5000

		self._log = logging.getLogger(self.__class__.__name__)

	def run(self):
		user_item_rating_data_frame = self.spark_session.table(
		    'user_item_rating_table'
		)

		train_data_frame, test_data_frame = self._split_train_test(
		    user_item_rating_data_frame
		)

		training_pipeline = self._get_training_pipeline()

		training_pipeline_model = training_pipeline.fit(train_data_frame)

		best_model = training_pipeline_model.bestModel

		self._log_model_details(best_model)

		self._log_model_performance(
		    best_model, test_data_frame, train_data_frame
		)

		requested_catalog_coverage = self._get_requested_catalog_coverage()

		self._log.info(
		    "Generating recommendations of {} items per user".
		    format(requested_catalog_coverage)
		)

		recommendations_data_frame = best_model.recommendForAllUsers(
		    requested_catalog_coverage
		)

		self._get_context_recommendations_data_frame(
		    recommendations_data_frame
		).createOrReplaceTempView('context_user_interaction_recommendation')

		self.spark_session.catalog.cacheTable(
		    'context_user_interaction_recommendation'
		)

		product_interaction_recommendation_enable = self.spark_application_configuration.get(
		    'product.interaction.recommendation.enable'
		)

		if product_interaction_recommendation_enable:
			item_factors = best_model.itemFactors

			item_factors.createOrReplaceTempView('item_factors')

			self.spark_session.catalog.cacheTable('item_factors')

	def _get_context_recommendations_data_frame(
	    self, recommendations_data_frame
	):
		product_interaction_data_frame = self.spark_session.table(
		    'product_interactions'
		).select('CPDefinitionId', 'assetCategoryIds')

		return recommendations_data_frame.selectExpr(
		    'commerceAccountId',
		    'explode(recommendations) as recommendations',
		).selectExpr(
		    'commerceAccountId',
		    'recommendations.CPDefinitionId as CPDefinitionId',
		    'recommendations.rating as score'
		).join(product_interaction_data_frame, on='CPDefinitionId')

	def _get_evaluator(self):
		return MAPEvaluator(
		    label_column_name='rating',
		    prediction_column_name='prediction',
		    query_column_name='commerceAccountId'
		)

	def _get_requested_catalog_coverage(self):
		product_interaction_data_frame = self.spark_session.table(
		    'product_interactions'
		)

		catalog_coverage = float(
		    self.spark_application_configuration.
		    get('user.interaction.recommendation.catalog.coverage')
		)

		catalog_count = product_interaction_data_frame.rdd.countApprox(
		    self._default_count_approx_timeout
		)

		return int(catalog_count * catalog_coverage)

	def _get_training_pipeline(self):
		configuration = self.spark_application_configuration

		als = ALS(
		    userCol='commerceAccountId',
		    itemCol='CPDefinitionId',
		    ratingCol='rating',
		    coldStartStrategy='drop',
		    implicitPrefs=True,
		    nonnegative=True
		)

		als_checkpoint_interval = configuration.get(
		    'user.interaction.recommendation.als.checkpoint.interval'
		)

		als.setCheckpointInterval(als_checkpoint_interval)

		tuning_alpha = configuration.get_list(
		    'user.interaction.recommendation.tuning.alpha'
		)
		tuning_max_iterations = configuration.get_list(
		    'user.interaction.recommendation.tuning.max.iteration'
		)
		tuning_rank = configuration.get_list(
		    'user.interaction.recommendation.tuning.rank'
		)
		tuning_reg_parameter = configuration.get_list(
		    'user.interaction.recommendation.tuning.regularization.parameter'
		)

		param_grid_builder = ParamGridBuilder()

		param_grid_builder.addGrid(als.alpha, tuning_alpha)
		param_grid_builder.addGrid(als.maxIter, tuning_max_iterations)
		param_grid_builder.addGrid(als.rank, tuning_rank)
		param_grid_builder.addGrid(als.regParam, tuning_reg_parameter)

		return CrossValidator(
		    estimator=als,
		    estimatorParamMaps=param_grid_builder.build(),
		    evaluator=self._get_evaluator(),
		    numFolds=int(
		        configuration.get(
		            'user.interaction.recommendation.tuning.cross.validator.'
		            'num.folds', 3
		        )
		    ),
		    parallelism=int(
		        configuration.get(
		            'user.interaction.recommendation.tuning.cross.validator.'
		            'parallelism', 2
		        )
		    )
		)

	def _log_model_details(self, model):
		if not self._log.isEnabledFor(logging.INFO):
			return

		self._log.info("Best model details:")
		self._log.info(
		    "Max Iterations: {}".format(model._java_obj.parent().getMaxIter())
		)
		self._log.info(
		    "Reg Parameter: {}".format(model._java_obj.parent().getRegParam())
		)
		self._log.info("Alpha: {}".format(model._java_obj.parent().getAlpha()))
		self._log.info("Rank: {}".format(model._java_obj.parent().getRank()))
		self._log.info(
		    "Non-negative feedback: {}".format(
		        model._java_obj.parent().getNonnegative()
		    )
		)

	def _log_model_performance(self, model, test_data_frame, train_data_frame):
		if not self._log.isEnabledFor(logging.INFO):
			return

		test_map = self._get_evaluator().evaluate(
		    model.transform(test_data_frame)
		)

		self._log.info("Model performance on TEST set: {}".format(test_map))

		train_map = self._get_evaluator().evaluate(
		    model.transform(train_data_frame)
		)

		self._log.info("Model performance on TRAIN set: {}".format(train_map))

	def _split_train_test(self, user_item_data_frame):
		train_split_ratio = self.spark_application_configuration.get(
		    'user.interaction.recommendation.train.split.ratio'
		)

		train_data_frame, test_data_frame = user_item_data_frame.randomSplit(
		    [train_split_ratio, 1 - train_split_ratio]
		)

		train_data_frame.cache()

		test_data_frame.cache()

		return train_data_frame, test_data_frame

class UserInteractionDataPreparationSparkJob(BaseSparkJob):
	def __init__(self, spark_application):
		super().__init__(spark_application)

	def run(self):
		cp_instance_data_frame = self.spark_session.table(
		    'product_interactions'
		).selectExpr('CPDefinitionId', 'explode(sku_list) as sku')

		order_interaction_data_frame = self.spark_session.table(
		    'order_interactions'
		)

		user_item_ratings_data_frame = order_interaction_data_frame.join(
		    cp_instance_data_frame, on=["sku"]
		)

		user_item_ratings_data_frame = user_item_ratings_data_frame.drop("sku")
		user_item_ratings_data_frame = user_item_ratings_data_frame.withColumn(
		    'rating', lit(1.0)
		)

		user_item_ratings_data_frame.createOrReplaceTempView(
		    'user_item_rating_table'
		)

		self.spark_session.catalog.cacheTable('user_item_rating_table')