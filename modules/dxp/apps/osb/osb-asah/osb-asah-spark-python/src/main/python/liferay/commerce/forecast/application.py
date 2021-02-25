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

from liferay.commerce.common import BaseCommerceSparkApplication
from liferay.commerce.forecast.constants import CommerceMLForecastPeriod, \
	CommerceMLForecastScope, \
	CommerceMLForecastTarget
from liferay.commerce.forecast.job import AccountCategoryForecastJSONDataFrameWriterSparkJob, \
	AccountForecastJSONDataFrameWriterSparkJob, \
	ForecastDataPrepareSparkJob, \
	ForecastOrderJSONDataFrameReader, \
	ForecastProductCategoryAugmentationSparkJob, \
	ForecastProductJSONDataFrameReaderSparkJob, \
	ForecastSparkJob, \
	MergeHistorySparkJob, \
	SkuForecastJSONDataFrameWriterSparkJob
from liferay.common.spark import SparkJobPipeline

class AccountCategoryForecastApplication(BaseCommerceSparkApplication):

	def __init__(self):
		super(AccountCategoryForecastApplication, self).__init__()

		self._period = CommerceMLForecastPeriod.MONTH
		self._scope = CommerceMLForecastScope.ASSET_CATEGORY
		self._target = CommerceMLForecastTarget.REVENUE

	def _create_spark_job_pipeline(self):
		jobs = list()

		jobs.append(
			ForecastOrderJSONDataFrameReader(
				self, self._period, self._scope, self._target
			)
		)

		jobs.append(ForecastProductJSONDataFrameReaderSparkJob(self))

		jobs.append(ForecastProductCategoryAugmentationSparkJob(self))

		jobs.append(
			ForecastDataPrepareSparkJob(
				self, self._period, self._scope, self._target
			)
		)

		jobs.append(
			ForecastSparkJob(self, self._period, self._scope, self._target)
		)

		jobs.append(MergeHistorySparkJob(self))

		jobs.append(AccountCategoryForecastJSONDataFrameWriterSparkJob(self))

		return SparkJobPipeline(jobs)

class AccountForecastApplication(BaseCommerceSparkApplication):

	def __init__(self):
		super(AccountForecastApplication, self).__init__()

		self._period = CommerceMLForecastPeriod.MONTH
		self._scope = CommerceMLForecastScope.COMMERCE_ACCOUNT
		self._target = CommerceMLForecastTarget.REVENUE

	def _create_spark_job_pipeline(self):
		jobs = list()

		jobs.append(
			ForecastOrderJSONDataFrameReader(
				self, self._period, self._scope, self._target
			)
		)

		jobs.append(
			ForecastDataPrepareSparkJob(
				self, self._period, self._scope, self._target
			)
		)

		jobs.append(
			ForecastSparkJob(self, self._period, self._scope, self._target)
		)

		jobs.append(MergeHistorySparkJob(self))

		jobs.append(AccountForecastJSONDataFrameWriterSparkJob(self))

		return SparkJobPipeline(jobs)

class ProductDemandForecastApplication(BaseCommerceSparkApplication):

	def __init__(self):
		super(ProductDemandForecastApplication, self).__init__()

		self._period = CommerceMLForecastPeriod.MONTH
		self._scope = CommerceMLForecastScope.SKU
		self._target = CommerceMLForecastTarget.QUANTITY

	def _create_spark_job_pipeline(self):
		jobs = list()

		jobs.append(
			ForecastOrderJSONDataFrameReader(
				self, self._period, self._scope, self._target
			)
		)

		jobs.append(
			ForecastDataPrepareSparkJob(
				self, self._period, self._scope, self._target
			)
		)

		jobs.append(
			ForecastSparkJob(self, self._period, self._scope, self._target)
		)

		jobs.append(MergeHistorySparkJob(self))

		jobs.append(SkuForecastJSONDataFrameWriterSparkJob(self))

		return SparkJobPipeline(jobs)