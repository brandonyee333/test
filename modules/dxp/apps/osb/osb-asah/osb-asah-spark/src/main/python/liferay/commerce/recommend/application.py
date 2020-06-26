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

import argparse
import logging
import sys
from abc import ABCMeta, abstractmethod

from liferay.commerce.configuration import CommerceConfiguration, JobManager
from liferay.commerce.constants import ProductInteractionRecommendationConstants
from liferay.commerce.recommend.job import ProductContentJSONFileReader, ProductContentPipelineJob, \
 ProductContentRecommendationJob, ProductContentRecommendationJSONFileWriter, ProductInteractionJSONFileReader, \
 OrderInteractionJSONFileReader, UserInteractionDataPreparationSparkJob, \
 UserInteractionCollaborativeFilteringSparkJob, ProductInteractionRecommendationSparkJob, \
 ProductInteractionRecommendationJSONFileWriterSparkJob, ContextUserInteractionRecommendationJSONFileWriterSparkJob
from liferay.commerce.udf import TanimotoCoefficientUDFFunction, ToDenseVectorUDFFunction
from liferay.common.elasticsearch import ElasticsearchBridge
from liferay.common.spark import BaseSparkApplication, SparkJobPipeline

class BaseCommerceSparkApplication(BaseSparkApplication, metaclass=ABCMeta):
	def __init__(self):
		super(BaseCommerceSparkApplication, self).__init__()

		self.job_manager = JobManager(
		    elasticsearch_bridge=ElasticsearchBridge(self),
		    job_run_id=self.args.job_run_id
		)

		self.configuration = CommerceConfiguration(
		    self.args.configuration, self.job_manager
		)

		self.log = self._initialize_logging()

	def start(self):
		spark_job_pipeline = self._create_spark_job_pipeline()

		try:
			spark_job_pipeline.run()
		except Exception as e:
			self.job_manager.update_status("FAILED")

			raise e

		self.job_manager.update_status("COMPLETED")

	def _create_argument_parser(self):
		argument_parser = argparse.ArgumentParser(
		    usage='{} liferay.commerce.recommend.<ApplicationName> '
		    '--configuration <Configuration Path> '
		    '--elasticsearch-hostname <Elasticsearch Hostname> '
		    '--job-run-id <Job Run ID> '
		    '--lcp-project-id <LCP Project ID>'.format(sys.argv[0])
		)

		argument_parser.add_argument('application')
		argument_parser.add_argument('--configuration', required=True)
		argument_parser.add_argument('--elasticsearch-hostname', required=True)
		argument_parser.add_argument('--job-run-id', required=True)
		argument_parser.add_argument('--lcp-project-id', required=True)

		return argument_parser

	def _initialize_logging(self):
		logging.basicConfig(
		    format='%(asctime)s %(name)-12s %(levelname)-8s %(message)s',
		    level=logging.INFO
		)

		return logging.getLogger(self.__class__.__name__)

	@abstractmethod
	def _create_spark_job_pipeline(self) -> SparkJobPipeline:
		pass

class ProductContentRecommendationApplication(BaseCommerceSparkApplication):
	def __init__(self):
		super(ProductContentRecommendationApplication, self).__init__()

		TanimotoCoefficientUDFFunction(self.spark_session)

	def _create_spark_job_pipeline(self):
		jobs = list()

		jobs.append(ProductContentJSONFileReader(self))
		jobs.append(ProductContentPipelineJob(self))
		jobs.append(ProductContentRecommendationJob(self))
		jobs.append(ProductContentRecommendationJSONFileWriter(self))

		return SparkJobPipeline(jobs)

class UserInteractionRecommendationApplication(BaseCommerceSparkApplication):
	def __init__(self):
		super(UserInteractionRecommendationApplication, self).__init__()

		TanimotoCoefficientUDFFunction(self.spark_session)
		ToDenseVectorUDFFunction(self.spark_session)

	def _create_spark_job_pipeline(self) -> SparkJobPipeline:
		jobs = list()

		jobs.append(ProductInteractionJSONFileReader(self))
		jobs.append(OrderInteractionJSONFileReader(self))
		jobs.append(UserInteractionDataPreparationSparkJob(self))
		jobs.append(UserInteractionCollaborativeFilteringSparkJob(self))
		jobs.append(
		    ContextUserInteractionRecommendationJSONFileWriterSparkJob(self)
		)

		product_interaction_recommendation_enable = self.configuration.get(
		    ProductInteractionRecommendationConstants.
		    PRODUCT_INTERACTION_RECOMMENDATION_ENABLE
		)

		if product_interaction_recommendation_enable:
			jobs.append(ProductInteractionRecommendationSparkJob(self))
			jobs.append(
			    ProductInteractionRecommendationJSONFileWriterSparkJob(self)
			)

		return SparkJobPipeline(jobs)
