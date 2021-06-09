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

from enum import Enum

class CommerceMLForecastPeriod(Enum):

	MONTH = ('month', 30, 12)

	WEEK = ('week', 7, 52)

	@property
	def days(self):
		return self.value[1]

	@classmethod
	def from_label(cls, label):
		for item in cls:
			if item.label == label:
				return item

		raise ValueError(
			'{} is not a valid {} label'.format(label, cls.__name__)
		)

	@property
	def label(self):
		return self.value[0]

	@property
	def period_value(self):
		return self.value[2]

class CommerceMLForecastScope(Enum):

	ASSET_CATEGORY = (
		'asset-category', ['assetCategoryId', 'commerceAccountId']
	)

	COMMERCE_ACCOUNT = ('commerce-account', ['commerceAccountId'])

	@property
	def columns(self):
		return self.value[1]

	@classmethod
	def from_label(cls, label):
		for item in cls:
			if item.label == label:
				return item

		raise ValueError(
			'{} is not a valid {} label'.format(label, cls.__name__)
		)

	@property
	def label(self):
		return self.value[0]

class CommerceMLForecastTarget(Enum):

	QUANTITY = ('quantity', 'quantity')

	REVENUE = ('revenue', 'finalPrice')

	@property
	def column(self):
		return self.value[1]

	@property
	def label(self):
		return self.value[0]

	@classmethod
	def from_description(cls, label):
		for item in cls:
			if item.label == label:
				return item

		raise ValueError(
			'{} is not a valid {} label'.format(label, cls.__name__)
		)