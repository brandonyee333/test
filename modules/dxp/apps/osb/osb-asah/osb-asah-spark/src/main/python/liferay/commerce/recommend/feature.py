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

import logging

from pyspark.ml.evaluation import Evaluator
from pyspark.ml.feature import VectorAssembler, StringIndexer, OneHotEncoder, Tokenizer, StopWordsRemover, \
 CountVectorizer, IDF
from pyspark.sql.functions import col, collect_list, when

from liferay.commerce.constants import Field, CPDefinitionField

class CommerceFeatureExtractor(object):

	CATEGORICAL_COLUMNS = \
        [Field.COMPANY_ID, CPDefinitionField.PRODUCT_TYPE_NAME]
	TEXT_COLUMNS = [Field.NAME, Field.DESCRIPTION]
	COUNT_VECTORIZER_COLUMNS = [Field.ASSET_CATEGORY_IDS]

	def __init__(self):
		self._log = logging.getLogger(self.__class__.__name__)

	def extract_features(self, feature_column_list):
		feature_columns = []

		stages = []

		for feature_column in feature_column_list:
			res = self._extract_features(feature_column)

			if res is not None:
				feature_columns += [res['feature_column']]

				stages += res['stages']

		stages += [
		    VectorAssembler(inputCols=feature_columns, outputCol='features')
		]

		return {'feature_column': 'features', 'stages': stages}

	def _extract_features(self, column_name):
		if column_name in self.CATEGORICAL_COLUMNS:

			return self.categorical_encoder(column_name)
		elif column_name in self.TEXT_COLUMNS:

			return self.text_encoder(column_name)
		elif column_name in self.COUNT_VECTORIZER_COLUMNS:

			return self.count_vectorizer_encoder(column_name)
		elif column_name.startswith('SPECIFICATION_'):

			return self.categorical_encoder(column_name, handle_invalid='keep')

		else:
			self._log.warn(
			    "Unable to find feature extractor for: " + column_name
			)

		return None

	@staticmethod
	def categorical_encoder(column_name, handle_invalid='skip'):
		feature_column_name = column_name + 'Feature'

		stages = [
		    StringIndexer(
		        inputCol=column_name,
		        outputCol=column_name + 'Index',
		        handleInvalid=handle_invalid
		    )
		]

		stages += [
		    OneHotEncoder(
		        dropLast=False,
		        inputCol=column_name + 'Index',
		        outputCol=feature_column_name
		    )
		]

		return {'feature_column': feature_column_name, 'stages': stages}

	@staticmethod
	def text_encoder(column_name):
		feature_column_name = column_name + 'Feature'

		stages = [
		    Tokenizer(inputCol=column_name, outputCol=column_name + 'Token')
		]

		stages += [
		    StopWordsRemover(
		        inputCol=column_name + 'Token',
		        outputCol=column_name + 'TokenFiltered'
		    )
		]

		stages += [
		    CountVectorizer(
		        inputCol=column_name + 'TokenFiltered',
		        outputCol=column_name + 'Count'
		    )
		]

		stages += [
		    IDF(inputCol=column_name + 'Count', outputCol=feature_column_name)
		]

		return {'feature_column': feature_column_name, 'stages': stages}

	@staticmethod
	def count_vectorizer_encoder(column_name):
		feature_column_name = column_name + 'Feature'

		stages = [
		    CountVectorizer(
		        inputCol=column_name, outputCol=feature_column_name
		    )
		]

		return {'feature_column': feature_column_name, 'stages': stages}

class MAPEvaluator(Evaluator):
	def __init__(self, query_col, label_col, prediction_col, threshold=0.5):
		super(MAPEvaluator, self).__init__()

		self._queryCol = query_col
		self._labelCol = label_col
		self._predictionCol = prediction_col
		self._threshold = threshold

	def _evaluate(self, dataset):
		dataset = dataset.withColumn(
		    'binary',
		    self._get_binary_expression(self._predictionCol,
		                                self._threshold).cast('double')
		).withColumn(
		    self._predictionCol,
		    col(self._predictionCol).cast('double')
		)

		map_ = dataset.groupBy(self._queryCol).agg(
		    collect_list(self._labelCol).alias('gt'),
		    collect_list('binary').alias('pred')
		).rdd.map(lambda r: sum(r.pred) / sum(r.gt)).mean()

		return map_

	def _get_binary_expression(self, column_name, threshold):
		return when(col(column_name) > threshold, 1.0).otherwise(0.0)
