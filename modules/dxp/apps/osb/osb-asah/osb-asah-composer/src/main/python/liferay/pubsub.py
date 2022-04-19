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
import time

from airflow import DAG
from airflow.api.client.local_client import Client
from airflow.exceptions import AirflowSkipException
from airflow.providers.google.cloud.hooks.pubsub import PubSubHook

from liferay.common import BaseOperator

class PubSubDagTriggerOperator(BaseOperator):

	def __init__(self, **kwargs):
		super().__init__(**kwargs)

		self._pub_sub_hook = PubSubHook()

	def do_execute(self, dag: DAG, **kwargs):
		dag_configuration = kwargs[dag.dag_id]

		pubsub_subscription = dag_configuration['pubsub.subscription']

		self.log.info(
			'Pulling from subscription {}'.format(pubsub_subscription)
		)

		messages = self._pub_sub_hook.pull(
			project_id=dag.default_args['project_id'],
			subscription=pubsub_subscription,
			max_messages=10,
			return_immediately=True
		)

		if messages:
			self.log.info('Sending ACK for {} messages'.format(len(messages)))

			self._pub_sub_hook.acknowledge(
				project_id=dag.default_args['project_id'],
				subscription=pubsub_subscription,
				messages=messages
			)
		else:
			raise AirflowSkipException()

		client = Client(None, None)

		for message in messages:
			data = message.message.data

			self.log.info(
				'Trigger {} with conf: {}'.format(
					dag_configuration['trigger.dag.id'],
					json.loads(data.decode("utf-8"))
				)
			)

			client.trigger_dag(
				dag_id=dag_configuration['trigger.dag.id'],
				conf=json.loads(data)
			)

			time.sleep(1)