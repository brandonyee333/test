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


from polyglot.detect import Detector

from pyspark.ml import Transformer
from pyspark.sql.functions import col, \
	udf
from pyspark.sql.types import ArrayType, \
	FloatType, \
	IntegerType, \
	MapType, \
	Row, \
	StringType, \
	StructField, \
	StructType

class LanguageDetectorPolyglotWrapper(Transformer):

	def __init__(self):
		super(LanguageDetectorPolyglotWrapper, self).__init__()

		self._input_col = None
		self._output_col = None

	def _get_language_and_confidence(self, row):
		text = row[0]['result']

		detector = Detector(text, quiet=True)

		highest_confidence = -1
		highest_confidence_language_code = 'un'
		languages_and_confidences = {}

		for language in detector.languages:
			languages_and_confidences[language.code] = str(language.confidence)

			if language.confidence > highest_confidence:
				highest_confidence_language_code = language.code,
				highest_confidence = language.confidence

		result = [
			Row(annotatorType='document',
				begin=row[0]['begin'],
				end=row[0]['end'],
				result=highest_confidence_language_code[0],
				metadata=languages_and_confidences,
				embeddings=row[0]['embeddings']
		  )]

		return result

	def _transform(self, data_frame):
		schema = ArrayType(StructType([
			StructField('annotatorType', StringType(), False),
			StructField('begin', IntegerType(), False),
			StructField('end', IntegerType(), False),
			StructField('result', StringType(), False),
			StructField(
				'metadata', MapType(StringType(), StringType(), True), True),
			StructField('embeddings', ArrayType(FloatType(), False), True)
		]))

		language_and_confidence_udf = udf(
			lambda row: self._get_language_and_confidence(row),
			schema)

		data_frame = data_frame.withColumn(
			self._output_col,
			language_and_confidence_udf(self._input_col)
		)

		return data_frame

	def setInputCol(self, inputCol):
		self._input_col = inputCol

		return self

	def setOutputCol(self, outputCol):
		self._output_col = outputCol

		return self