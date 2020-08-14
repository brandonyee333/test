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

from liferay.common.spark import BaseSparkJob
from liferay.common.util import new_utc_date_string

from pyspark.sql import Window
from pyspark.sql.functions import col, count, current_date, datediff, expr, lit, unix_timestamp

class GenerateItemsSparkJob(BaseSparkJob):
	def _update_job_run_items_dataset_count(self, items_data_frame):
		elasticsearch_bridge = self.spark_application.elasticsearch_bridge

		elasticsearch_bridge.update_document(
		    'job-runs', {
		        'context': {
		            'itemsDatasetCount': items_data_frame.count()
		        },
		        'lastUpdatedDate': new_utc_date_string(),
		    }, self.spark_application_args.job_run_id, 'osbasahfaroinfo'
		)

	def run(self):
		items_data_frame = self.spark_session.table(
		    'user_item_interactions'
		).select(col('ITEM_ID').alias("itemId")).distinct()

		self._update_job_run_items_dataset_count(items_data_frame)

		items_data_frame.createOrReplaceTempView('items')

class GenerateUserItemInteractionsSparkJob(BaseSparkJob):
	def _update_job_run_user_item_interactions_dataset_count(
	    self, user_item_interactions_data_frame
	):
		elasticsearch_bridge = self.spark_application.elasticsearch_bridge

		elasticsearch_bridge.update_document(
		    'job-runs', {
		        'context':
		            {
		                'userItemInteractionsDatasetCount':
		                    user_item_interactions_data_frame.count()
		            },
		        'lastUpdatedDate': new_utc_date_string(),
		    }, self.spark_application_args.job_run_id, 'osbasahfaroinfo'
		)

	def run(self):
		user_item_interactions_data_frame = self.spark_session.table(
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

		self._update_job_run_user_item_interactions_dataset_count(
		    user_item_interactions_data_frame
		)

		user_item_interactions_data_frame.createOrReplaceTempView(
		    'user_item_interactions'
		)

class PublishJobRunSparkJob(BaseSparkJob):
	def run(self):
		self._complete_previous_published_job_runs()

		elasticsearch_bridge = self.spark_application.elasticsearch_bridge

		date_string = new_utc_date_string()

		elasticsearch_bridge.update_document(
		    'job-runs', {
		        'completedDate': date_string,
		        'lastUpdatedDate': date_string,
		        'status': 'PUBLISHED'
		    }, self.spark_application_args.job_run_id, 'osbasahfaroinfo'
		)

	def _complete_previous_published_job_runs(self):
		elasticsearch_bridge = self.spark_application.elasticsearch_bridge
		job = self.spark_application.job

		job_runs, total = elasticsearch_bridge.search(
		    {
		        'query':
		            {
		                'bool':
		                    {
		                        'filter':
		                            [
		                                {
		                                    "term": {
		                                        "job.id": job.get('id')
		                                    }
		                                }, {
		                                    "term": {
		                                        "status": 'PUBLISHED'
		                                    }
		                                }
		                            ]
		                    }
		            }
		    }, 'job-runs', 'osbasahfaroinfo'
		)

		date_string = new_utc_date_string()

		for job_run in job_runs:
			elasticsearch_bridge.update_document(
			    'job-runs', {
			        'lastUpdatedDate': date_string,
			        'status': 'COMPLETED'
			    }, job_run.get('id'), 'osbasahfaroinfo'
			)

class ReadAnalyticsEventsSparkJob(BaseSparkJob):
	def __init__(self, spark_application):
		super(ReadAnalyticsEventsSparkJob, self).__init__(spark_application)

		self._minimum_interactions_threshold = 3
		self._minimum_view_duration_threshold = 5000
		self._run_data_periods_days_delta = {
		    'LAST_7_DAYS': 7,
		    'LAST_30_DAYS': 30,
		    'LAST_180_DAYS': 180,
		    'LAST_365_DAYS': 365
		}

	def _get_expression(self, filter_string, negate):
		tokens = [token for token in filter_string.split(' ') if len(token) > 0]

		if tokens[1] == '=':
			expression = '(lower(context.`{}`) = "{}")'.format(
			    tokens[0], tokens[2].lower()
			)
		else:
			expression = '(rlike(context.`{}`, "(?i){}"))'.format(
			    tokens[0], tokens[2]
			)

		if negate:
			return '({} == False)'.format(expression)
		else:
			return expression

	def _get_filter_expresssions(self):
		expressions = ['(eventId = "pageUnloaded")']

		job = self.spark_application.job

		for parameter in job.get('parameters'):
			if parameter.get('name') == 'excludeFilter':
				expressions.append(
				    self._get_expression(parameter.get('value'), True)
				)
			elif parameter.get('name') == 'includeFilter':
				expressions.append(
				    self._get_expression(parameter.get('value'), False)
				)

		return " AND ".join(expressions)

	def _get_maximum_days_delta(self):
		job_run = self.spark_application.job_run

		job_run_context = job_run.get('context')

		return self._run_data_periods_days_delta.get(
		    job_run_context.get('runDataPeriod')
		)

	def run(self):
		data_frame_reader = self.spark_session.read

		analytics_events_data_frame = data_frame_reader.json(
		    '{}/{}/*'.format(
		        self.spark_application_configuration.get('google.storage.path'),
		        self.spark_application_args.lcp_project_id
		    )
		).withColumn(
		    'days_delta', datediff(current_date(), expr("to_date(eventDate)"))
		).withColumn(
		    'interactions',
		    count('userId').over(Window.partitionBy('userId'))
		)

		analytics_events_data_frame = analytics_events_data_frame.filter(
		    self._get_filter_expresssions()
		)

		analytics_events_data_frame = analytics_events_data_frame.filter(
		    col('days_delta') <= self._get_maximum_days_delta()
		)

		analytics_events_data_frame = analytics_events_data_frame.filter(
		    col('eventProperties.viewDuration') >=
		    self._minimum_view_duration_threshold
		)

		analytics_events_data_frame = analytics_events_data_frame.filter(
		    col('interactions') >= self._minimum_interactions_threshold
		)

		analytics_events_data_frame.createOrReplaceTempView('analytics_events')

class ReadRecommendedItemsSparkJob(BaseSparkJob):
	def run(self):
		data_frame_reader = self.spark_session.read

		job = self.spark_application.job

		recommended_items_data_frame = data_frame_reader.json(
		    '{}/{}/{}/inference_result/*.json.out'.format(
		        self.spark_application_configuration.get('aws.storage.path'),
		        self.spark_application_args.lcp_project_id, job.get('id')
		    )
		).filter('error is null').withColumn('jobId', lit(
		    job.get('id')
		)).selectExpr(
		    'sha1(concat(jobId, input.itemId)) as id', 'input.itemId as itemId',
		    'jobId', 'output.recommendedItems as recommendedItemIds'
		)

		recommended_items_data_frame.createOrReplaceTempView(
		    'recommended_items'
		)

class UpdateJobRunStepSparkJob(BaseSparkJob):
	def run(self):
		elasticsearch_bridge = self.spark_application.elasticsearch_bridge

		elasticsearch_bridge.update_document(
		    'job-runs', {
		        'lastUpdatedDate': new_utc_date_string(),
		        'step': 'DATA_SOLUTION'
		    }, self.spark_application_args.job_run_id, 'osbasahfaroinfo'
		)

class WriteDataframeSparkJob(BaseSparkJob):
	def __init__(self, data_frame_name, output_format, spark_application):
		super(WriteDataframeSparkJob, self).__init__(spark_application)

		self._data_frame_name = data_frame_name
		self._output_format = output_format

	def run(self):
		data_frame = self.spark_session.table(self._data_frame_name)

		data_frame_writer = data_frame.write

		job = self.spark_application.job

		data_frame_writer.format(
		    self._output_format
		).mode('overwrite').option("header", "True").save(
		    '{}/{}/{}/{}'.format(
		        self.spark_application_configuration.get('aws.storage.path'),
		        self.spark_application_args.lcp_project_id, job.get('id'),
		        self._data_frame_name
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
		elasticsearch_bridge = self.spark_application.elasticsearch_bridge

		elasticsearch_bridge.write(
		    'recommended-items', self.spark_session.table('recommended_items'),
		    'overwrite', 'osbasahfaroinfo'
		)