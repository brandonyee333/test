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

from datetime import datetime, timedelta

from liferay.common.spark import BaseSparkJob
from liferay.interest_score.nlp import LanguageDetectorPolyglotWrapper

from pyspark.sql import Window
from pyspark.sql.functions import array, array_contains, array_distinct, array_remove, col, concat, count, current_date, datediff, desc, expr, flatten, lit, row_number, split

from sparknlp.base import DocumentAssembler, Finisher, RecursivePipeline
from sparknlp.annotator import Chunker, Normalizer, PerceptronModel, SentenceDetector, Tokenizer

class KeywordsExtractionSparkJob(BaseSparkJob):
	def __init__(self, spark_application):
		super(KeywordsExtractionSparkJob, self).__init__(spark_application)

	def _create_chunker_stage(self, column_name):
		chunker = Chunker()

		chunker.setInputCols([f'{column_name}_sentence', f'{column_name}_normalized', f'{column_name}_pos'])
		chunker.setOutputCol(f'{column_name}_chunk')
		chunker.setRegexParsers([
			"<NNP>+",
			"<JJ>*<NN>+",
			"<JJ>*<NNS>+",
			"<JJ>*<NNS><NNP>",
			"<JJ>*<NNP>+",
			"<JJ>*<NN>*<NNS>+",
			"<JJ>*<NN>*<NNP>+"
		])

		return chunker

	def _create_document_assembler_stage(self, column_name):
		document_assembler = DocumentAssembler()

		document_assembler.setInputCol(column_name)
		document_assembler.setOutputCol(f'{column_name}_document')

		return document_assembler

	def _create_finisher_stage(self, column_name, include_language_detector_step):
		finisher = Finisher()

		input_cols = [f'{column_name}_chunk']

		if include_language_detector_step:
			input_cols.append(f'{column_name}_language')

		finisher.setInputCols(input_cols)
		finisher.setCleanAnnotations(False)

		return finisher

	def _create_language_detector_stage(
		self, column_name, include_language_detector_step):

		if not include_language_detector_step:
			return None

		language_detector = LanguageDetectorPolyglotWrapper()

		language_detector.setInputCol(f'{column_name}_document')
		language_detector.setOutputCol(f'{column_name}_language')

		return language_detector

	def _create_normalizer_stage(self, column_name):
		normalizer = Normalizer()

		normalizer.setInputCols([f'{column_name}_token'])
		normalizer.setOutputCol(f'{column_name}_normalized')
		normalizer.setCleanupPatterns(["[^\w\d\s]"])

		return normalizer

	def _create_pos_tagger_stage(self, column_name):
		pos_tagger = PerceptronModel.pretrained()

		pos_tagger.setInputCols([f'{column_name}_normalized', f'{column_name}_sentence'])
		pos_tagger.setOutputCol(f'{column_name}_pos')

		return pos_tagger

	def _create_sentence_detector_stage(self, column_name):
		sentence_detector = SentenceDetector()

		sentence_detector.setInputCols([f'{column_name}_document'])
		sentence_detector.setOutputCol(f'{column_name}_sentence')

		return sentence_detector

	def _create_tokenizer_stage(self, column_name):
		tokenizer = Tokenizer()

		tokenizer.setInputCols([f'{column_name}_sentence'])
		tokenizer.setOutputCol(f'{column_name}_token')
		tokenizer.setSplitChars(['|'])

		return tokenizer

	def _generate_nlp_pipeline(self, column_name, include_language_detector_step=False):
		recursive_pipeline_stages = list(filter(
			lambda stage: stage is not None,
			[
				self._create_document_assembler_stage(column_name),
				self._create_language_detector_stage(
					column_name, include_language_detector_step
				),
				self._create_sentence_detector_stage(column_name),
				self._create_tokenizer_stage(column_name),
				self._create_normalizer_stage(column_name),
				self._create_pos_tagger_stage(column_name),
				self._create_chunker_stage(column_name),
				self._create_finisher_stage(
					column_name, include_language_detector_step
				)
			]
		))

		return RecursivePipeline(stages=recursive_pipeline_stages)

	def _get_sample_analytics_event_by_canonical_url(self, analytics_events_data_frame):
		window = Window.partitionBy('normalized_canonical_url')

		sample_data_frame = analytics_events_data_frame.withColumn(
			'window_count', count('*').over(window)
		)

		sample_data_frame = sample_data_frame.withColumn(
			'row_number',
			row_number().over(window.orderBy(desc('window_count')))
		)

		return sample_data_frame.filter('row_number = 1')

	def run(self):
		analytics_events_data_frame = self.spark_session.table(
			'analytics_events'
		)

		nlp_data_frame = self._get_sample_analytics_event_by_canonical_url(
			analytics_events_data_frame
		)

		nlp_data_frame = nlp_data_frame.withColumn(
			'title', col('context.title')
		).fillna('')

		nlp_data_frame = nlp_data_frame.withColumn(
			'description', col('context.description')
		).fillna('')

		nlp_data_frame = nlp_data_frame.withColumn(
			'title_and_description',
			concat(col('title'), lit('. '), col('description'), lit('. '))
		).fillna('')

		nlp_data_frame = nlp_data_frame.withColumn(
			'keywords', split(col('context.keywords'), ',\s*')
		)

		title_and_description_pipeline = self._generate_nlp_pipeline(
			'title_and_description', include_language_detector_step=True
		)

		nlp_data_frame = title_and_description_pipeline.fit(
			nlp_data_frame
		).transform(
			nlp_data_frame
		)

		title_nlp_pipeline = self._generate_nlp_pipeline('title')

		nlp_data_frame = title_nlp_pipeline.fit(
			nlp_data_frame
		).transform(
			nlp_data_frame
		)

		description_nlp_pipeline = self._generate_nlp_pipeline('description')

		nlp_data_frame = description_nlp_pipeline.fit(
			nlp_data_frame
		).transform(
			nlp_data_frame
		)

		extracted_keywords_data_frame = nlp_data_frame.filter(
			array_contains(col('finished_title_and_description_language'), 'en'))

		extracted_keywords_data_frame = \
			extracted_keywords_data_frame.withColumn(
				'extracted_keywords',
				array(
					col('finished_title_chunk'),
					col('finished_description_chunk'), col('keywords')))

		extracted_keywords_data_frame = \
			extracted_keywords_data_frame.withColumn(
				'extracted_keywords', flatten(col('extracted_keywords')))

		extracted_keywords_data_frame = \
			extracted_keywords_data_frame.withColumn(
				'extracted_keywords', array_distinct('extracted_keywords'))

		extracted_keywords_data_frame = \
			extracted_keywords_data_frame.withColumn(
				'extracted_keywords', array_remove('extracted_keywords', ''))

		extracted_keywords_data_frame.createOrReplaceTempView(
			'extracted_keywords'
		)

class ReadAnalyticsEventsSparkJob(BaseSparkJob):
	def __init__(self, spark_application):
		super(ReadAnalyticsEventsSparkJob, self).__init__(spark_application)

		self._max_days_delta = 30
		self._minimum_interactions_threshold = 5
		self._minimum_view_duration_threshold = 5000

	def _get_analytics_events_paths(self):
		jvm = self.spark_session._jvm
		spark_context = self.spark_session.sparkContext

		file_system = jvm.org.apache.hadoop.fs.FileSystem

		bucket_path = '{}/{}/analytics_events.json'.format(
		    self.spark_application_configuration.
		    get('google.storage.path.analytics-events'),
		    self.spark_application_args.lcp_project_id
		)

		file_system_instance = file_system.get(
		    jvm.java.net.URI(bucket_path),
		    spark_context._jsc.hadoopConfiguration()
		)

		analytics_events_paths_map = map(
		    lambda f: str(f.getPath()),
		    file_system_instance.listStatus(
		        jvm.org.apache.hadoop.fs.Path(bucket_path)
		    )
		)

		return sorted(list(analytics_events_paths_map), reverse=True)

	def _get_analytics_events_paths_filtered(self):
		analytics_events_paths_filtered = []

		delta = datetime.utcnow() - timedelta(days=self._max_days_delta)

		minimum_chunk_timestamp = int(delta.timestamp() * 1000)

		for analytics_events_paths in self._get_analytics_events_paths():
			chunk_timestamp = int(analytics_events_paths.split('/')[-1])

			if chunk_timestamp >= minimum_chunk_timestamp:
				analytics_events_paths_filtered.append(analytics_events_paths)
			else:
				break

		return analytics_events_paths_filtered

	def run(self):
		data_frame_reader = self.spark_session.read

		analytics_events_data_frame = data_frame_reader.json(
		    self._get_analytics_events_paths_filtered()
		)

		analytics_events_data_frame = analytics_events_data_frame.filter(
		    col('eventId') == 'pageUnloaded'
		).withColumn(
			'event_date', expr('to_date(eventDate)')
		).withColumn(
		    'days_delta', datediff(current_date(), col('event_date'))
		).withColumn(
		    'interactions',
		    count('userId').over(Window.partitionBy('userId'))
		)

		analytics_events_data_frame = analytics_events_data_frame.filter(
		    col('context.contentLanguageId') == 'en'
		)

		analytics_events_data_frame = analytics_events_data_frame.filter(
		    col('days_delta') <= self._max_days_delta
		)

		analytics_events_data_frame = analytics_events_data_frame.filter(
		    col('eventProperties.viewDuration') >=
		    self._minimum_view_duration_threshold
		)

		analytics_events_data_frame = analytics_events_data_frame.filter(
		    col('interactions') >= self._minimum_interactions_threshold
		)

		analytics_events_data_frame = analytics_events_data_frame.withColumn(
			'normalized_canonical_url',
			expr('normalize_url(context.canonicalUrl)')
		)

		analytics_events_data_frame = analytics_events_data_frame.withColumn(
			'normalized_url', expr('normalize_url(context.url)')
		)

		analytics_events_data_frame.createOrReplaceTempView('analytics_events')