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

import json

from google.api_core import retry
from google.cloud import pubsub_v1

from liferay.common.spark import BaseSparkJob

class EventIngestionSparkJob(BaseSparkJob):

	def __init__(self, spark_application):
		super(EventIngestionSparkJob, self).__init__(spark_application)
		self.write_format = self.spark_application_configuration.get(
			'spark.table.fileformat')
		self.partition_by = self.spark_application_configuration.get(
			'spark.table.partition_by')
		self.subscription_path = self.spark_application_configuration.get(
			'google.pubsub.subscription_path')
		self.max_messages = self.spark_application_configuration.get(
			'google.pubsub.max_messages')
		self.deadline = self.spark_application_configuration.get(
			'google.pubsub.deadline')

	def _pull_messages(self, subscriber_client):
		return subscriber_client.pull(
			request={"subscription": self.subscription_path,
					 "max_messages": self.max_messages},
			retry=retry.Retry(deadline=self.deadline),
		)

	def _deserialize_events(self, received_messages):
		return [json.loads(item.message.data) for item in received_messages]

	def _group_events(self, events):
		events_dict = {event['eventId']: [] for event in events}
		for event in events:
			events_dict[event['eventId']].append(event)
			return events_dict

	def _save_event_list(self, table_name, event_list):
		prdd = self.spark_session.sparkContext.parallelize(event_list)
		self.spark_session.read.json(prdd).write.format(self.write_format).partitionBy(
			self.partition_by).option("mergeSchema", "true").mode("append").save(table_name)

	def _save_grouped_events(self, grouped_events):
		for table_name, event_list in grouped_events.items():
			self._save_event_list(table_name, event_list)

	def _list_ack_ids(self, received_messages):
		return [item.ack_id for item in received_messages]

	def _send_acks(self, subscriber_client, ack_ids):
		subscriber_client.acknowledge(
			request={"subscription": self.subscription_path, "ack_ids": ack_ids}
		)

	def run(self):
		with pubsub_v1.SubscriberClient() as subscriber_client:
			while(True):
				response = self._pull_messages(subscriber_client)
				if len(response.received_messages):
					print(response.received_messages)
					events = self._deserialize_events(response.received_messages)
					grouped_events = self._group_events(events)
					self._save_grouped_events(grouped_events)
					self._send_acks(subscriber_client, self._list_ack_ids(
						response.received_messages))