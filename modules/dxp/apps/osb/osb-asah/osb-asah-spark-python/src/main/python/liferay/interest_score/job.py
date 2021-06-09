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

from datetime import datetime, \
	timedelta

from liferay.common.spark import BaseSparkJob
from liferay.interest_score.nlp import LanguageDetectorPolyglotWrapper

from pyspark.sql import Window, \
	functions as F

from sparknlp.annotator import Chunker, \
	Normalizer, \
	PerceptronModel, \
	SentenceDetector, \
	Tokenizer
from sparknlp.base import DocumentAssembler, \
	Finisher, \
	RecursivePipeline

import json

class IndividualInterestScoreSparkJob(BaseSparkJob):

	def __init__(self, spark_application):
		super(IndividualInterestScoreSparkJob, self).__init__(spark_application)

		self._global_keyword_weight = 1.0
		self._interest_score_decay_rate = 0.9
		self._max_days_delta = 60
		self._minimum_logscore_threshold = 0.01
		self._user_keyword_weight = 2.0

	def _get_job_parameter(self, parameter_name, default_value=None):
		job_parameters = json.loads(self.spark_application_args.job_parameters)

		for job_parameter in job_parameters:
			if job_parameter.get('name') == parameter_name:
				return job_parameter.get('value')

		return default_value

	def _get_keyword_count_with_totals_data_frame(
		self, analytics_events_with_keywords_data_frame):

		keyword_count_data_frame = \
			analytics_events_with_keywords_data_frame.withColumn(
				'keyword', F.explode(F.col('extracted_keywords'))
			).groupby(
				'event_date', 'keyword'
			).count(
			).withColumnRenamed('count', 'keyword_count')

		total_keywords_count_data_frame = \
			keyword_count_data_frame.groupby(
				'event_date'
			).sum(
			).withColumnRenamed(
				'sum(keyword_count)', 'total_keywords_count'
			)

		return keyword_count_data_frame.join(
			total_keywords_count_data_frame,
			how='left',
			on=['event_date']
		)

	def _get_user_keyword_count_with_totals_data_frame(
		self, analytics_events_with_keywords_data_frame):

		user_keyword_count_data_frame = \
			analytics_events_with_keywords_data_frame.withColumn(
				'keyword', F.explode(F.col('extracted_keywords'))
			).groupBy(
				'userId', 'event_date', 'keyword'
			).count(
			).withColumnRenamed(
				'count', 'user_keyword_count'
			)

		user_total_keyword_count_data_frame = \
			user_keyword_count_data_frame.groupby(
				'userId', 'event_date'
			).sum(
			).withColumnRenamed(
				'sum(user_keyword_count)', 'user_total_keyword_count'
			)

		return user_keyword_count_data_frame.join(
			user_total_keyword_count_data_frame,
			how='inner',
			on=['userId', 'event_date']
		)

	def _weighted_average(self, column, offsets, weights, window):
		values = list()

		for i, weight in zip(offsets, weights):
			values.append(
				F.coalesce(F.lag(column, i).over(window) * weight, F.lit(0))
			)

		return sum(values, F.lit(0))

	def run(self):
		analytics_events_data_frame = self.spark_session.table(
			'analytics_events'
		)

		extracted_keywords_data_frame = self.spark_session.table(
			'extracted_keywords'
		)

		analytics_events_with_keywords_data_frame = \
			analytics_events_data_frame.join(
				extracted_keywords_data_frame,
				F.col('analytics_events.normalized_canonical_url') ==
				F.col('extracted_keywords.normalized_canonical_url'),
				how='inner'
			)

		keyword_count_with_totals_data_frame = \
			self._get_keyword_count_with_totals_data_frame(
				analytics_events_with_keywords_data_frame
			)

		user_keyword_counts_with_totals_data_frame = \
			self._get_user_keyword_count_with_totals_data_frame(
				analytics_events_with_keywords_data_frame
			)

		daily_logscores_data_frame = \
			user_keyword_counts_with_totals_data_frame.join(
				keyword_count_with_totals_data_frame,
				how='left',
				on=['event_date', 'keyword']
			)

		daily_logscores_data_frame = daily_logscores_data_frame.withColumn(
			'logscore',
			F.log10(
				(self._global_keyword_weight * F.col('total_keywords_count') +
				 self._user_keyword_weight * F.col('user_total_keyword_count')) /
				(self._global_keyword_weight * F.col('keyword_count') +
				 self._user_keyword_weight * F.col('user_keyword_count'))
			)
		)

		date_range_data_frame = self.spark_session.sql(
			"SELECT sequence(to_date('{}'), to_date('{}'), interval 1 day) "
			"as event_date".format(
				self._get_job_parameter('startDate'),
				self._get_job_parameter('endDate'))
		).withColumn(
			'event_date', F.explode(F.col('event_date'))
		)

		distinct_user_id_keyword_data_frame = \
			daily_logscores_data_frame.select(
				F.col('userId'), F.col('keyword')
			).distinct()

		date_user_id_keyword_data_frame = date_range_data_frame.crossJoin(
			distinct_user_id_keyword_data_frame)

		expanded_dates_logscore_data_frame = \
			date_user_id_keyword_data_frame.join(
				daily_logscores_data_frame,
				['event_date', 'userId', 'keyword'],
				how='left'
			)

		offsets = [i for i in range(0, self._max_days_delta)]
		weights = [
			self._interest_score_decay_rate ** i
			for i in range(0, self._max_days_delta)
		]

		interest_score_data_frame = expanded_dates_logscore_data_frame.select(
			'event_date', 'userId', 'keyword', 'logscore'
		).fillna(
			{'logscore': 0}
		).withColumn(
			'interest_score',
			self._weighted_average(
				F.col('logscore'), offsets, weights,
				Window.partitionBy('userId', 'keyword').orderBy('event_date')
			)
		).withColumn(
			'interest_score',
			F.when(
				F.col('interest_score') < self._minimum_logscore_threshold, 0
			).otherwise(
				F.col('interest_score')
			)
		)

		interest_score_data_frame.createOrReplaceTempView('interest_score')

class KeywordsExtractionSparkJob(BaseSparkJob):

	def __init__(self, spark_application):
		super(KeywordsExtractionSparkJob, self).__init__(spark_application)

	def _create_chunker_stage(self, column_name):
		chunker = Chunker()

		chunker.setInputCols([
			f'{column_name}_sentence', f'{column_name}_normalized',
			f'{column_name}_pos'
		])
		chunker.setOutputCol(f'{column_name}_chunk')
		chunker.setRegexParsers([
			'<NNP>+',
			'<JJ>*<NN>+',
			'<JJ>*<NNS>+',
			'<JJ>*<NNS><NNP>',
			'<JJ>*<NNP>+',
			'<JJ>*<NN>*<NNS>+',
			'<JJ>*<NN>*<NNP>+'
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
		normalizer.setCleanupPatterns(['[^\w\d\s]'])

		return normalizer

	def _create_pos_tagger_stage(self, column_name):
		pos_tagger = PerceptronModel.pretrained()

		pos_tagger.setInputCols([
			f'{column_name}_normalized', f'{column_name}_sentence'
		])
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

	def _generate_nlp_pipeline(
		self, column_name, include_language_detector_step=False
	):

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

	def _get_sample_analytics_event_by_canonical_url(
		self, analytics_events_data_frame
	):

		window = Window.partitionBy('normalized_canonical_url')

		sample_data_frame = analytics_events_data_frame.withColumn(
			'window_count', F.count('*').over(window)
		)

		sample_data_frame = sample_data_frame.withColumn(
			'row_number',
			F.row_number().over(window.orderBy(F.desc('window_count')))
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
			'title', F.col('context.title')
		).fillna('')

		nlp_data_frame = nlp_data_frame.withColumn(
			'description', F.col('context.description')
		).fillna('')

		nlp_data_frame = nlp_data_frame.withColumn(
			'title_and_description',
			F.concat(
				F.col('title'), F.lit('. '), F.col('description'), F.lit('. '))
		).fillna('')

		nlp_data_frame = nlp_data_frame.withColumn(
			'keywords', F.split(F.col('context.keywords'), ',\s*')
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
			F.array_contains(
				F.col('finished_title_and_description_language'), 'en'))

		extracted_keywords_data_frame = \
			extracted_keywords_data_frame.withColumn(
				'extracted_keywords',
				F.array(
					F.col('finished_title_chunk'),
					F.col('finished_description_chunk'), F.col('keywords')))

		extracted_keywords_data_frame = \
			extracted_keywords_data_frame.withColumn(
				'extracted_keywords', F.flatten(F.col('extracted_keywords')))

		extracted_keywords_data_frame = \
			extracted_keywords_data_frame.withColumn(
				'extracted_keywords', F.array_distinct('extracted_keywords'))

		extracted_keywords_data_frame = \
			extracted_keywords_data_frame.withColumn(
				'extracted_keywords', F.array_remove('extracted_keywords', ''))

		extracted_keywords_data_frame = extracted_keywords_data_frame.select(
			'normalized_canonical_url', 'extracted_keywords'
		)

		extracted_keywords_data_frame.createOrReplaceTempView(
			'extracted_keywords'
		)

class ReadAnalyticsEventsSparkJob(BaseSparkJob):

	def __init__(self, spark_application):
		super(ReadAnalyticsEventsSparkJob, self).__init__(spark_application)

		self._max_days_delta = 60
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
			F.col('eventId') == 'pageUnloaded'
		).withColumn(
			'event_date', F.expr('to_date(eventDate)')
		).withColumn(
			'days_delta', F.datediff(F.current_date(), F.col('event_date'))
		).withColumn(
			'interactions',
			F.count('userId').over(Window.partitionBy('userId'))
		)

		analytics_events_data_frame = analytics_events_data_frame.filter(
			F.col('context.contentLanguageId') == 'en'
		)

		analytics_events_data_frame = analytics_events_data_frame.filter(
			F.col('days_delta') <= self._max_days_delta
		)

		analytics_events_data_frame = analytics_events_data_frame.filter(
			F.col('eventProperties.viewDuration') >=
			self._minimum_view_duration_threshold
		)

		analytics_events_data_frame = analytics_events_data_frame.filter(
			F.col('interactions') >= self._minimum_interactions_threshold
		)

		analytics_events_data_frame = analytics_events_data_frame.withColumn(
			'normalized_canonical_url',
			F.expr('normalize_url(context.canonicalUrl)')
		)

		analytics_events_data_frame = analytics_events_data_frame.withColumn(
			'normalized_url', F.expr('normalize_url(context.url)')
		)

		analytics_events_data_frame.createOrReplaceTempView('analytics_events')

class SegmentInterestScoreSparkJob(BaseSparkJob):

	def __init__(self, spark_application):
		super(SegmentInterestScoreSparkJob, self).__init__(spark_application)

	def run(self):
		data_frame_reader = self.spark_session.read

		individuals_data_frame = data_frame_reader.json(
			'{}/{}/individuals.json'.format(
				self.spark_application_configuration.get(
					'google.storage.path.individuals'
				),
				self.spark_application_args.lcp_project_id
			)
		)

		individual_with_segments_data_frame = individuals_data_frame.filter(
			F.size(F.array_distinct(F.col('individualSegmentIds'))) >= 1
		).withColumn(
			'dataSourceId',
			F.explode(F.col('dataSourceIndividualPKs.dataSourceId'))
		).withColumn(
			'userId',
			F.explode(F.col('dataSourceIndividualPKs.individualPKs'))
		).withColumn(
			'userId', F.explode(F.col('userId'))
		).withColumn(
			'individualSegmentId', F.explode(F.col('individualSegmentIds'))
		).select(
			'dataSourceId', 'userId', 'individualSegmentId'
		).alias(
			'segmented_individuals'
		)

		individual_segments_data_frame = data_frame_reader.json(
			'{}/{}/individual_segments.json'.format(
				self.spark_application_configuration.get(
					'google.storage.path.individual-segments'
				),
				self.spark_application_args.lcp_project_id
			)
		).select(
			'id', 'name'
		).alias(
			'individual_segments'
		)

		interest_score_data_frame = self.spark_session.table('interest_score')

		segment_interest_score_data_frame = \
			individual_with_segments_data_frame.join(
				interest_score_data_frame,
				F.col('segmented_individuals.userId') ==
				F.col('interest_score.userId'),
				how='inner'
			).join(
				individual_segments_data_frame,
				F.col('segmented_individuals.individualSegmentId') ==
				F.col('individual_segments.id'),
				how='inner'
			).select(
				'event_date', 'individualSegmentId', 'interest_score',
				'keyword', 'name', 'segmented_individuals.userId'
			).groupby(
				'event_date', 'individualSegmentId', 'keyword', 'name'
			).avg(
				'interest_score'
			).withColumnRenamed(
				'avg(interest_score)',
				'segment_interest_score'
			)

		segment_interest_score_data_frame.createOrReplaceTempView(
			'segment_interest_score'
		)