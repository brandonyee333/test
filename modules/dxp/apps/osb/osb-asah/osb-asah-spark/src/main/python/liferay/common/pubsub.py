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

from google.cloud import pubsub_v1

class PubSubBridge:
	def __init__(self, spark_application):
		self.publisher_client = pubsub_v1.PublisherClient()

		self.topic_path = self.self.publisher_client.topic_path(
		    spark_application.configuration.get('google.project.id'),
		    '{lcp_project_id}_dataproc'.format(
		        spark_application.args.lcp_project_id
		    )
		)

	def send(self, message):
		self.publisher_client.publish(self.topic_path, message.encode('UTF-8'))
