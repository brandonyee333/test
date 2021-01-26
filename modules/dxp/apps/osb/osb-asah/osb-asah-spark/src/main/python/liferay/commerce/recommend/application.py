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

from abc import ABCMeta, \
	abstractmethod

from liferay.commerce.configuration import CommerceConfiguration
from liferay.commerce.recommend.job import ContextUserInteractionRecommendationJSONDataFrameWriterSparkJob, \
	FrequentPatternDataPreparationSparkJob, \
	FrequentPatternOrderJSONDataFrameReaderSparkJob, \
	FrequentPatternPostProcessRecommendationSparkJob, \
	FrequentPatternProductJSONDataFrameReaderSparkJob, \
	FrequentPatternRecommendationJSONDataFrameWriterSparkJob, \
	FrequentPatternRecommendationSparkJob, \
	OrderInteractionJSONDataFrameReaderSparkJob, \
	ProductContentJSONDataFrameReaderSparkJob, \
	ProductContentPipelineSparkJob, \
	ProductContentRecommendationJSONDataFrameWriter, \
	ProductContentRecommendationSparkJob, \
	ProductInteractionJSONDataFrameReaderSparkJob, \
	ProductInteractionRecommendationJSONDataFrameWriterSparkJob, \
	ProductInteractionRecommendationSparkJob, \
	UserInteractionCollaborativeFilteringSparkJob, \
	UserInteractionDataPreparationSparkJob
from liferay.commerce.udf import TanimotoCoefficientUDFFunction, \
	ToDenseVectorUDFFunction
from liferay.common.spark import BaseSparkApplication, \
	SparkJobPipeline

import argparse
import logging
import os
import sys

class BaseCommerceSparkApplication(BaseSparkApplication, metaclass=ABCMeta):

	def __init__(self):
		super(BaseCommerceSparkApplication, self).__init__()

		self.configuration = CommerceConfiguration(self.args.configuration)

		self.log = self._initialize_logging()

		spark_context = self.spark_session.sparkContext

		spark_conf = spark_context.getConf()

		for key, value in spark_conf.getAll():
			if key.startswith('spark.yarn.appMasterEnv.'):
				env_key = key[len('spark.yarn.appMasterEnv.'):]

				os.environ[env_key] = value

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

	@abstractmethod
	def _create_spark_job_pipeline(self):
		pass

	def _initialize_logging(self):
		logging.basicConfig(
			format='%(asctime)s %(name)-12s %(levelname)-8s %(message)s',
			level=logging.INFO
		)

		return logging.getLogger(self.__class__.__name__)

	def start(self):
		spark_job_pipeline = self._create_spark_job_pipeline()

		try:
			spark_job_pipeline.run()
		except Exception as e:
			self.log.error(e)

			raise e

class FrequentPatternRecommendationApplication(BaseCommerceSparkApplication):

	def __init__(self):
		super(FrequentPatternRecommendationApplication, self).__init__()

	def _create_spark_job_pipeline(self):
		jobs = list()

		jobs.append(FrequentPatternOrderJSONDataFrameReaderSparkJob(self))

		jobs.append(FrequentPatternProductJSONDataFrameReaderSparkJob(self))

		jobs.append(FrequentPatternDataPreparationSparkJob(self))

		jobs.append(FrequentPatternRecommendationSparkJob(self))

		jobs.append(FrequentPatternPostProcessRecommendationSparkJob(self))

		jobs.append(
			FrequentPatternRecommendationJSONDataFrameWriterSparkJob(self)
		)

		return SparkJobPipeline(jobs)

class ProductContentRecommendationApplication(BaseCommerceSparkApplication):

	def __init__(self):
		super(ProductContentRecommendationApplication, self).__init__()

		TanimotoCoefficientUDFFunction(self.spark_session)

	def _create_spark_job_pipeline(self):
		jobs = list()

		jobs.append(ProductContentJSONDataFrameReaderSparkJob(self))

		jobs.append(ProductContentPipelineSparkJob(self))

		jobs.append(ProductContentRecommendationSparkJob(self))

		jobs.append(ProductContentRecommendationJSONDataFrameWriter(self))

		return SparkJobPipeline(jobs)

class UserInteractionRecommendationApplication(BaseCommerceSparkApplication):

	def __init__(self):
		super(UserInteractionRecommendationApplication, self).__init__()

		TanimotoCoefficientUDFFunction(self.spark_session)
		ToDenseVectorUDFFunction(self.spark_session)

	def _create_spark_job_pipeline(self):
		jobs = list()

		jobs.append(ProductInteractionJSONDataFrameReaderSparkJob(self))

		jobs.append(OrderInteractionJSONDataFrameReaderSparkJob(self))

		jobs.append(UserInteractionDataPreparationSparkJob(self))

		jobs.append(UserInteractionCollaborativeFilteringSparkJob(self))

		jobs.append(
			ContextUserInteractionRecommendationJSONDataFrameWriterSparkJob(
				self
			)
		)

		product_interaction_recommendation_enable = self.configuration.get(
			'product.interaction.recommendation.enable'
		)

		if product_interaction_recommendation_enable:
			jobs.append(ProductInteractionRecommendationSparkJob(self))

			jobs.append(
				ProductInteractionRecommendationJSONDataFrameWriterSparkJob(
					self
				)
			)

		return SparkJobPipeline(jobs)