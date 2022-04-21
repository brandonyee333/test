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

from airflow import DAG
from airflow.models import DagRun
from airflow.operators.python import ShortCircuitOperator
from airflow.providers.google.cloud.hooks.dataproc import DataprocHook
from airflow.providers.google.cloud.operators.dataproc import ClusterGenerator, \
	DataprocCreateClusterOperator, \
	DataprocSubmitPySparkJobOperator
from airflow.utils.context import Context

from liferay.common import BaseOperator

import json
import time

class DataprocClusterGetOrCreateOperator(BaseOperator):

	def __init__(
			self,
			**kwargs
	):
		super().__init__(**kwargs)

		self.dataproc_hook = DataprocHook()

	def do_execute(self, dag: DAG, dag_run: DagRun, **kwargs):
		datasource_id = dag_run.conf['datasourceId']

		dag_configuration = kwargs[dag.dag_id]

		cluster_name = '{}-{}-{}'.format(
			dag_configuration['dataproc.cluster.name_prefix'],
			datasource_id,
			kwargs['ds_nodash']
		)

		project_id = dag.default_args['project_id']

		region = dag.default_args['region']

		self.log.info("cluster_name: {}".format(cluster_name))

		cluster_config = ClusterGenerator(
			project_id=project_id,
			idle_delete_ttl=dag_configuration['dataproc.cluster.idle_delete_ttl'],
			image_version=dag_configuration['dataproc.cluster.image_version'],
			num_masters=dag_configuration['dataproc.cluster.master.count'],
			num_workers=dag_configuration['dataproc.cluster.worker.count'],
			master_disk_size=dag_configuration['dataproc.cluster.master.disk_size'],
			master_disk_type=dag_configuration['dataproc.cluster.master.disk_type'],
			master_machine_type=dag_configuration['dataproc.cluster.master.machine_type'],
			metadata=json.loads(dag_configuration['dataproc.cluster.metadata']),
			worker_disk_size=dag_configuration['dataproc.cluster.worker.disk_size'],
			worker_disk_type=dag_configuration['dataproc.cluster.worker.disk_type'],
			worker_machine_type=dag_configuration['dataproc.cluster.worker.machine_type'],
			use_if_exists=True
		).make()

		dataproc_cluster_create_operator = DataprocCreateClusterOperator(
			task_id='dataproc_cluster_create',
			region=region,
			cluster_name=cluster_name,
			cluster_config=cluster_config
		)

		return dataproc_cluster_create_operator.execute(Context(kwargs))


class DataprocShortCircuitOperator(ShortCircuitOperator):

	RESOURCE_NAMES = [
		'com.liferay.headless.commerce.machine.learning.dto.v1_0.Product'
	]

	def __init__(self, **kwargs):
		super().__init__(
			python_callable=self._validate,
			op_args=None,
			op_kwargs=None,
			templates_dict=None,
			templates_exts=None,
			provide_context=True,
			**kwargs)

	def _validate(self, dag_run: DagRun):
		resource_name = dag_run.conf['resourceName']

		return resource_name in self.RESOURCE_NAMES


class DataprocSubmitCommercePySparkJobOperator(BaseOperator):

	template_fields = (
		'_cluster_name',
		'templates_dict',
		'op_args',
		'op_kwargs'
	)

	APPLICATION_MAP = {
		'com.liferay.headless.commerce.admin.order.dto.v1_0.Order':
			'liferay.commerce.recommend.UserInteractionRecommendationApplication',
		'com.liferay.headless.commerce.admin.catalog.dto.v1_0.Product':
			'liferay.commerce.recommend.ProductContentRecommendationApplication',
		'com.liferay.headless.commerce.machine.learning.dto.v1_0.Product':
			'liferay.commerce.recommend.ProductContentRecommendationApplication',
	}

	def __init__(self, cluster_name: str, **kwargs):
		super().__init__(**kwargs)

		self.log.warning(cluster_name)

		self._cluster_name = cluster_name

	def do_execute(self, dag: DAG, dag_run: DagRun, **kwargs):
		cluster_name = self.render_template(content=self._cluster_name, context=Context(kwargs))

		dag_configuration = kwargs[dag.dag_id]

		dataproc_submit_pyspark_job_operator = DataprocSubmitPySparkJobOperator(
			task_id='dataproc_submit_pyspark_job',
			project_id=dag.default_args['project_id'],
			cluster_name=cluster_name,
			region=dag.default_args['region'],
			job_name=self._get_application_name(
				lcp_project_id=dag.default_args['lcp_project_id'],
				resource_name=dag_run.conf['resourceName'],
				datasource_id=dag_run.conf['datasourceId']
			),
			main='gs://{}/osb-asah-spark-python-driver.py'.format(dag_configuration['dataproc.bucket']),
			arguments=[
				self.APPLICATION_MAP.get(dag_run.conf['resourceName']),
				'--lcp-project-id',
				dag.default_args['lcp_project_id'],
				'--configuration',
				dag_configuration['dataproc.pyspark.configuration']
			],
			archives=[
				'gs://{}/resources/{}'.format(
					dag_configuration['dataproc.bucket'],
					dag_configuration['dataproc.pyspark.configuration']
				)
			],
			dataproc_properties=json.loads(
				dag_configuration['dataproc.pyspark.properties']
			),
			pyfiles=[
				'gs://{}/osb-asah-spark-python.zip'.format(dag_configuration['dataproc.bucket'])
			]
		)

		dataproc_submit_pyspark_job_operator.execute(Context(kwargs))

	def _get_application_name(
			self,
			lcp_project_id: str,
			resource_name: str,
			datasource_id: str
	):
		application_name = self.APPLICATION_MAP.get(resource_name)

		if application_name is not None:
			application_name = application_name.split('.')[-1]

		return '{}-{}-{}-{}'.format(
			lcp_project_id,
			application_name.lower(),
			datasource_id,
			int(time.time())
		)