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
from pyspark.sql.functions import col, count, sha1, unix_timestamp

import datetime

class CompleteJobExecutionStepSparkJob(BaseSparkJob):
	def run(self):
		spark_application = self.spark_application

		args = self.spark_application.args
		elasticsearch_bridge = spark_application.elasticsearch_bridge

		elasticsearch_bridge.update_document(
		    'job-executions', {
		        'completedDate': datetime.datetime.utcnow(),
		        'status': 'COMPLETED'
		    }, args.job_execution_id, 'osbasahfaroinfo'
		)

class GenerateItemsSparkJob(BaseSparkJob):
	def run(self):
		spark_session = self.spark_session()

		items_data_frame = spark_session.table('user_item_interactions').select(
		    col('ITEM_ID')
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

		elasticsearch_bridge = spark_application.elasticsearch_bridge

		analytics_events_data_frame = elasticsearch_bridge.read(
		    'analytics-events', 'osbasahcerebroraw'
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
		spark_session = self.spark_session()

		data_frame_reader = spark_session.read

		args = self.spark_application.args
		configuration = self.spark_application.configuration

		recommended_items_data_frame = data_frame_reader.json(
		    '{}/{}/inference_result/*'.format(
		        configuration.get('storage.path'), args.lcp_project_id
		    )
		).filter('error is null').withColumn("id", sha1(
		    col('input.itemId')
		)).select(
		    col('id'),
		    col('input.itemId').alias('item'),
		    col('output.recommendedItems').alias('recommendedItems')
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
	def __init__(self, data_frame_name, spark_application):
		super(WriteDataframeSparkJob, self).__init__(spark_application)

		self._data_frame_name = data_frame_name

	def run(self):
		spark_session = self.spark_session()

		data_frame = spark_session.table(self._data_frame_name)

		data_frame_writer = data_frame.write

		args = self.spark_application.args
		configuration = self.spark_application.configuration

		data_frame_writer.csv(
		    '{}/{}/{}'.format(
		        configuration.get('storage.path'), args.lcp_project_id,
		        self._data_frame_name
		    ),
		    header=True,
		    mode="overwrite"
		)

class WriteItemsSparkJob(WriteDataframeSparkJob):
	def __init__(self, spark_application):
		super(WriteItemsSparkJob, self).__init__('items', spark_application)

class WriteUserItemInteractionsSparkJob(WriteDataframeSparkJob):
	def __init__(self, spark_application):
		super(WriteUserItemInteractionsSparkJob,
		      self).__init__('user_item_interactions', spark_application)

class WriteRecommendedItemsSparkJob(BaseSparkJob):
	def run(self):
		spark_application = self.spark_application
		spark_session = self.spark_session()

		elasticsearch_bridge = spark_application.elasticsearch_bridge

		elasticsearch_bridge.write(
		    'recommended-items', spark_session.table('recommended_items'),
		    'overwrite', 'osbasahfaroinfo'
		)
