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

from datetime import datetime, timedelta

from liferay.common.spark import BaseSparkJob

from pyspark.sql import Window
from pyspark.sql.functions import col, count, current_date, datediff, expr

class ReadAnalyticsEventsSparkJob(BaseSparkJob):
	def __init__(self, spark_application):
		super(ReadAnalyticsEventsSparkJob, self).__init__(spark_application)

		self._max_days_delta = 30
		self._minimum_interactions_threshold = 5
		self._minimum_view_duration_threshold = 5000

	def _get_analytics_events_paths(self):
		jvm = self.spark_session._jvm
		spark_context = self.spark_session.sparkContext

		file_system = jvm.org.apache.hadoop.fs.FileSystem

		bucket_path = '{}/{}/analytics_events.json'.format(
		    self.spark_application_configuration.
		    get('google.storage.path.analytics-events'),
		    self.spark_application_args.lcp_project_id
		)

		file_system_instance = file_system.get(
		    jvm.java.net.URI(bucket_path),
		    spark_context._jsc.hadoopConfiguration()
		)

		analytics_events_paths_map = map(
		    lambda f: str(f.getPath()),
		    file_system_instance.listStatus(
		        jvm.org.apache.hadoop.fs.Path(bucket_path)
		    )
		)

		return sorted(list(analytics_events_paths_map), reverse=True)

	def _get_analytics_events_paths_filtered(self):
		analytics_events_paths_filtered = []

		delta = datetime.utcnow() - timedelta(days=self._max_days_delta)

		minimum_chunk_timestamp = int(delta.timestamp() * 1000)

		for analytics_events_paths in self._get_analytics_events_paths():
			chunk_timestamp = int(analytics_events_paths.split('/')[-1])

			if chunk_timestamp >= minimum_chunk_timestamp:
				analytics_events_paths_filtered.append(analytics_events_paths)
			else:
				break

		return analytics_events_paths_filtered

	def run(self):
		data_frame_reader = self.spark_session.read

		analytics_events_data_frame = data_frame_reader.json(
		    self._get_analytics_events_paths_filtered()
		)

		analytics_events_data_frame = analytics_events_data_frame.filter(
		    col('eventId') == 'pageUnloaded'
		).withColumn(
		    'days_delta', datediff(current_date(), expr("to_date(eventDate)"))
		).withColumn(
		    'interactions',
		    count('userId').over(Window.partitionBy('userId'))
		)

		analytics_events_data_frame = analytics_events_data_frame.filter(
		    col('context.contentLanguageId') == 'en'
		)

		analytics_events_data_frame = analytics_events_data_frame.filter(
		    col('days_delta') <= self._max_days_delta
		)

		analytics_events_data_frame = analytics_events_data_frame.filter(
		    col('eventProperties.viewDuration') >=
		    self._minimum_view_duration_threshold
		)

		analytics_events_data_frame = analytics_events_data_frame.filter(
		    col('interactions') >= self._minimum_interactions_threshold
		)

		analytics_events_data_frame = analytics_events_data_frame.withColumn(
			'normalized_url', expr('normalize_url(context.url)')
		)

		analytics_events_data_frame = analytics_events_data_frame.withColumn(
			'normalized_canonical_url',
			expr('normalize_url(context.canonicalUrl)')
		)

		analytics_events_data_frame.createOrReplaceTempView('analytics_events')