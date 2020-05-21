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

from ..common.spark import BaseSparkJob

from pyspark.sql import Window
from pyspark.sql.functions import col, count, unix_timestamp

import datetime

class CompleteJobExecutionStepSparkJob(BaseSparkJob):
	def run(self):
		spark_application = self.spark_application

		args = self.spark_application.args
		elasticsearch_bridge = spark_application.elasticsearch_bridge

		now = datetime.datetime.utcnow()

		elasticsearch_bridge.update_document(
		    'job-executions', {
		        'completedDate': now,
		        'lastUpdatedDate': now,
		        'status': 'COMPLETED'
		    }, args.job_execution_id, 'osbasahfaroinfo'
		)

class GenerateItemsSparkJob(BaseSparkJob):
	def run(self):
		spark_session = self.spark_session()

		items_data_frame = spark_session.table('user_item_interactions').select(
		    col('ITEM_ID').alias("itemId")
		).distinct()

		items_data_frame.createOrReplaceTempView('items')

class GenerateUserItemInteractionsSparkJob(BaseSparkJob):
	def run(self):
		spark_session = self.spark_session()

		user_item_interactions_data_frame = spark_session.table(
		    'analytics_events'
		).withColumn(
		    'event_timestamp',
		    unix_timestamp(col('eventDate').cast('timestamp'))
		).select(
		    col('userId').alias('USER_ID'),
		    col('context.canonicalUrl').alias('ITEM_ID'),
		    col('event_timestamp').alias('TIMESTAMP'),
		    col('eventId').alias('EVENT_TYPE'),
		    col('eventProperties.viewDuration').alias('EVENT_VALUE')
		)

		user_item_interactions_data_frame.createOrReplaceTempView(
		    'user_item_interactions'
		)

class ReadAnalyticsEventsSparkJob(BaseSparkJob):
	def __init__(self, spark_application):
		super(ReadAnalyticsEventsSparkJob, self).__init__(spark_application)

		self._minimum_interactions_threshold = 3
		self._minimum_view_duration_threshold = 5000

	def run(self):
		spark_application = self.spark_application

		spark_session = spark_application.spark_session

		data_frame_reader = spark_session.read

		analytics_events_storage_path = '{}/{}/*'.format(
		    spark_application.configuration.get('google.storage.path'),
		    spark_application.args.lcp_project_id
		)

		analytics_events_data_frame = data_frame_reader.json(
		    analytics_events_storage_path
		).withColumn('article_section', col('context.article:section')).filter(
		    'article_section IS NOT NULL AND'
		    '(context.contentLanguageId = "en-US") AND '
		    '(eventId = "pageUnloaded")'
		).filter(
		    col('eventProperties.viewDuration') >=
		    self._minimum_view_duration_threshold
		).withColumn(
		    'interactions',
		    count('userId').over(Window.partitionBy('userId'))
		).filter(col('interactions') >= self._minimum_interactions_threshold)

		analytics_events_data_frame.createOrReplaceTempView('analytics_events')

class ReadRecommendedItemsSparkJob(BaseSparkJob):
	def run(self):
		spark_application = self.spark_application

		spark_session = spark_application.spark_session

		data_frame_reader = spark_session.read

		job_execution = spark_application.job_execution

		job = job_execution.get('job')

		inference_result_storage_path = '{}/{}/{}/inference_result/*.json.out'.format(
		    spark_application.configuration.get('aws.storage.path'),
		    spark_application.args.lcp_project_id, job.get('id')
		)

		recommended_items_data_frame = data_frame_reader.json(
		    inference_result_storage_path
		).filter('error is null').selectExpr(
		    'sha1(input.itemId) as id', 'input.itemId as item',
		    'output.recommendedItems as recommendedItems'
		)

		recommended_items_data_frame.createOrReplaceTempView(
		    'recommended_items'
		)

class UpdateJobExecutionStepSparkJob(BaseSparkJob):
	def run(self):
		spark_application = self.spark_application

		args = self.spark_application.args
		elasticsearch_bridge = spark_application.elasticsearch_bridge

		elasticsearch_bridge.update_document(
		    'job-executions', {
		        'lastUpdatedDate': datetime.datetime.utcnow(),
		        'step': 'DATA_SOLUTION'
		    }, args.job_execution_id, 'osbasahfaroinfo'
		)

class WriteDataframeSparkJob(BaseSparkJob):
	def __init__(self, data_frame_name, output_format, spark_application):
		super(WriteDataframeSparkJob, self).__init__(spark_application)

		self._data_frame_name = data_frame_name
		self._output_format = output_format

	def run(self):
		spark_session = self.spark_session()

		data_frame = spark_session.table(self._data_frame_name)

		data_frame_writer = data_frame.write

		args = self.spark_application.args
		configuration = self.spark_application.configuration
		job_execution = self.spark_application.job_execution

		job = job_execution.get('job')

		data_frame_writer.format(
		    self._output_format
		).mode('overwrite').option("header", "True").save(
		    '{}/{}/{}/{}'.format(
		        configuration.get('aws.storage.path'), args.lcp_project_id,
		        job.get('id'), self._data_frame_name
		    )
		)

class WriteItemsSparkJob(WriteDataframeSparkJob):
	def __init__(self, spark_application):
		super(WriteItemsSparkJob,
		      self).__init__('items', 'json', spark_application)

class WriteUserItemInteractionsSparkJob(WriteDataframeSparkJob):
	def __init__(self, spark_application):
		super(WriteUserItemInteractionsSparkJob, self).__init__(
		    'user_item_interactions', 'csv', spark_application
		)

class WriteRecommendedItemsSparkJob(BaseSparkJob):
	def run(self):
		spark_application = self.spark_application
		spark_session = self.spark_session()

		elasticsearch_bridge = spark_application.elasticsearch_bridge

		elasticsearch_bridge.write(
		    'recommended-items', spark_session.table('recommended_items'),
		    'overwrite', 'osbasahfaroinfo'
		)
