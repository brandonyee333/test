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

from abc import ABCMeta, abstractmethod

from liferay.commerce.configuration import CommerceConfiguration
from liferay.commerce.recommend.job import ContextUserInteractionRecommendationDataFrameWriterSparkJob, \
 OrderInteractionDataFrameReaderSparkJob, ProductContentDataFrameReaderSparkJob, \
 ProductContentPipelineSparkJob, ProductContentRecommendationDataFrameWriter, \
 ProductContentRecommendationSparkJob, ProductInteractionDataFrameReaderSparkJob, \
 ProductInteractionRecommendationDataFrameWriterSparkJob, \
 ProductInteractionRecommendationSparkJob, UserInteractionCollaborativeFilteringSparkJob, \
 UserInteractionDataPreparationSparkJob
from liferay.commerce.udf import TanimotoCoefficientUDFFunction, ToDenseVectorUDFFunction
from liferay.common.spark import BaseSparkApplication, SparkJobPipeline

import argparse
import logging
import sys

class BaseCommerceSparkApplication(BaseSparkApplication, metaclass=ABCMeta):
	def __init__(self):
		super(BaseCommerceSparkApplication, self).__init__()

		self.configuration = CommerceConfiguration(self.args.configuration)

		self.log = self._initialize_logging()

	def start(self):
		spark_job_pipeline = self._create_spark_job_pipeline()

		try:
			spark_job_pipeline.run()
		except Exception as e:
			self.log.error(e)

			raise e

	def _create_argument_parser(self):
		argument_parser = argparse.ArgumentParser(
		    usage='{} liferay.commerce.recommend.<ApplicationName> '
		    '--configuration <Configuration Path> '
		    '--lcp-project-id <LCP Project ID>'.format(sys.argv[0])
		)

		argument_parser.add_argument('application')
		argument_parser.add_argument('--configuration', required=True)
		argument_parser.add_argument('--lcp-project-id', required=True)

		return argument_parser

	def _initialize_logging(self):
		logging.basicConfig(
		    format='%(asctime)s %(name)-12s %(levelname)-8s %(message)s',
		    level=logging.INFO
		)

		return logging.getLogger(self.__class__.__name__)

	@abstractmethod
	def _create_spark_job_pipeline(self):
		pass

class ProductContentRecommendationApplication(BaseCommerceSparkApplication):
	def __init__(self):
		super(ProductContentRecommendationApplication, self).__init__()

		TanimotoCoefficientUDFFunction(self.spark_session)

	def _create_spark_job_pipeline(self):
		jobs = list()

		jobs.append(ProductContentDataFrameReaderSparkJob(self))
		jobs.append(ProductContentPipelineSparkJob(self))
		jobs.append(ProductContentRecommendationSparkJob(self))
		jobs.append(ProductContentRecommendationDataFrameWriter(self))

		return SparkJobPipeline(jobs)

class UserInteractionRecommendationApplication(BaseCommerceSparkApplication):
	def __init__(self):
		super(UserInteractionRecommendationApplication, self).__init__()

		TanimotoCoefficientUDFFunction(self.spark_session)
		ToDenseVectorUDFFunction(self.spark_session)

	def _create_spark_job_pipeline(self):
		jobs = list()

		jobs.append(ProductInteractionDataFrameReaderSparkJob(self))
		jobs.append(OrderInteractionDataFrameReaderSparkJob(self))
		jobs.append(UserInteractionDataPreparationSparkJob(self))
		jobs.append(UserInteractionCollaborativeFilteringSparkJob(self))
		jobs.append(
		    ContextUserInteractionRecommendationDataFrameWriterSparkJob(self)
		)

		product_interaction_recommendation_enable = self.configuration.get(
		    'product.interaction.recommendation.enable'
		)

		if product_interaction_recommendation_enable:
			jobs.append(ProductInteractionRecommendationSparkJob(self))
			jobs.append(
			    ProductInteractionRecommendationDataFrameWriterSparkJob(self)
			)

		return SparkJobPipeline(jobs)