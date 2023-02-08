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

import json
import logging

from liferay.common.spark import BaseSparkJob

from pyspark.ml import Pipeline
from pyspark.sql import Window, \
	functions as F

from sparknlp.annotator import Chunker, \
	LanguageDetectorDL, \
	Normalizer, \
	PerceptronModel, \
	SentenceDetector, \
	Tokenizer
from sparknlp.base import DocumentAssembler, \
	Finisher

class IdentityInterestScoreSparkJob(BaseSparkJob):

	def _30_day_sum(self, column, window):
		values = list()

		offsets = [i for i in range(0, 30)]

		for i in offsets:
			values.append(
				F.coalesce(F.lag(column, i).over(window), F.lit(0))
			)

		return sum(values, F.lit(0))

	def __init__(self, spark_application):
		super(IdentityInterestScoreSparkJob, self).__init__(spark_application)

		self._global_keyword_weight = 1.0
		self._interest_score_decay_rate = 0.9
		self._interest_score_minimum_threshold = 0.1
		self._max_days_delta = 60
		self._minimum_logscore_threshold = 0.01
		self._user_keyword_weight = 2.0

		self._decay_threshold_weight = self._interest_score_decay_rate ** 14

	def _get_identity_interest_pages_data_frame(
			self, analytics_events_data_frame, extracted_keywords_data_frame):

		return analytics_events_data_frame.groupby(
			'userId', 'canonicalUrl'
		).agg(F.count('*').alias('views')).join(
			extracted_keywords_data_frame,
			how='inner',
			on=['canonicalUrl']
		).withColumn(
			'keyword', F.explode(F.col('extracted_keywords'))
		).select(
			F.col('userId').alias('identityId'),
			F.col('keyword'),
			F.col('canonicalUrl'),
			F.col('title').alias('pageTitle'),
			F.col('views')
		)

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
			).withColumnRenamed(
				'count', 'keyword_count'
			)

		total_keywords_count_data_frame = keyword_count_data_frame.groupby(
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
			analytics_events_data_frame.drop(
				'keywords',
				'description',
				'title').join(
					extracted_keywords_data_frame,
					how='inner',
					on=['canonicalUrl']
				)

		keyword_count_with_totals_data_frame = \
			self._get_keyword_count_with_totals_data_frame(
				analytics_events_with_keywords_data_frame
			)

		user_keyword_counts_with_totals_data_frame = \
			self._get_user_keyword_count_with_totals_data_frame(
				analytics_events_with_keywords_data_frame
			)

		analytics_events_with_keywords_data_frame.createOrReplaceTempView(
			'analytics_events_with_keywords_data_frame')

		identity_interest_pages_data_frame = self._get_identity_interest_pages_data_frame(
			analytics_events_data_frame,
			extracted_keywords_data_frame)

		identity_interest_pages_data_frame.write.format(
			'bigquery'
		).mode(
			"overwrite"
		).save(
			'{}.identityinterestpages'.format(
				self.spark_application_args.lcp_project_id
			)
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

		threshold_data_frame = keyword_count_with_totals_data_frame.select(
			'event_date', 'keyword', 'keyword_count', 'total_keywords_count'
		).fillna(
			{'keyword_count': 0,
			 'total_keywords_count': 0}
		).withColumn(
			'keyword_view_count_30_days',
			self._30_day_sum(
				F.col('keyword_count'),
				Window.partitionBy('keyword').orderBy('event_date')
			)
		).withColumn(
			'all_keyword_views_30_days',
			self._30_day_sum(
				F.col('total_keywords_count'),
				Window.partitionBy('keyword').orderBy('event_date')
			)
		).withColumn(
			'threshold_score',
			F.log(
				10.0,
				(
					F.col('all_keyword_views_30_days') /
					F.col('keyword_view_count_30_days')
				)
			) * self._decay_threshold_weight
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
				F.col('keyword'), F.col('sessionId'), F.col('userId'),
			).distinct()

		date_user_id_keyword_data_frame = date_range_data_frame.crossJoin(
			distinct_user_id_keyword_data_frame)

		expanded_dates_logscore_data_frame = \
			date_user_id_keyword_data_frame.join(
				daily_logscores_data_frame,
				['event_date', 'keyword', 'sessionId', 'userId'],
				how='left'
			)

		offsets = [i for i in range(0, self._max_days_delta)]
		weights = [
			self._interest_score_decay_rate ** i
			for i in range(0, self._max_days_delta)
		]

		interest_score_data_frame = expanded_dates_logscore_data_frame.select(
			'event_date', 'keyword', 'logscore', 'sessionId', 'userId'
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

		interest_score_data_frame = interest_score_data_frame.join(
			threshold_data_frame,
			['event_date', 'keyword'],
			how='left'
		).fillna(
			{
				'threshold_score': self._interest_score_minimum_threshold
			}
		)

		interest_score_data_frame = interest_score_data_frame.withColumn(
			'interested',
			F.col('interest_score') >= F.col('threshold_score')
		)

		interest_score_data_frame.createOrReplaceTempView(
			'individual_interest_score')

		individual_interest_score_data_frame = self.spark_session.sql("""
			SELECT
				userId as identityId,
				interested,
				interest_score as interestScore,
				keyword,
				event_date as recordedDate
			FROM
				individual_interest_score
		""")

		data_frame_writer = individual_interest_score_data_frame.write

		data_frame_writer.format(
			'bigquery'
		).mode(
			"overwrite"
		).save(
			'{}.individualinterestscore'.format(
				self.spark_application_args.lcp_project_id
			)
		)

		session_interest_score_data_frame = self.spark_session.sql("""
			SELECT
				userId as identityId,
				interested,
				interest_score as interestScore,
				keyword,
				event_date as recordedDate,
				sessionId
			FROM 
				individual_interest_score
		""")

		data_frame_writer = session_interest_score_data_frame.write

		data_frame_writer.format(
			'bigquery'
		).mode(
			"overwrite"
		).save(
			'{}.sessioninterestscore'.format(
				self.spark_application_args.lcp_project_id
			)
		)

class KeywordsExtractionSparkJob(BaseSparkJob):

	def __init__(self, spark_application):
		self._log = logging.getLogger(self.__class__.__name__)

		try:
			language_detector_path = spark_application.configuration.get(
				'interest.models.language-detector.path')

			self._language_detector = LanguageDetectorDL.load(
				language_detector_path)

		except Exception as exception:
			self._log.warning(
				'Unable to load Language Detector {}'.format(exception))

			self._language_detector = LanguageDetectorDL.pretrained(
				'ld_tatoeba_cnn_99', 'xx')

		try:
			pos_tagger_path = spark_application.configuration.get(
				'interest.models.pos-tagger.path')

			self._part_of_speech_tagger = PerceptronModel.load(pos_tagger_path)
		except Exception as exception:
			self._log.warning(
				'Unable to load Part of Speech Tagger {}'.format(exception))

			self._part_of_speech_tagger = PerceptronModel.pretrained(
				"pos_anc", "en")

		super(KeywordsExtractionSparkJob, self).__init__(spark_application)

	def _create_chunker_stage(self, column_names, output_column_name):
		chunker = Chunker()

		chunker.setInputCols(column_names)
		chunker.setOutputCol(output_column_name)
		chunker.setRegexParsers([
			'<JJ>*<NN>+',
		])

		return chunker

	def _create_document_assembler_stage(self, column_name):
		document_assembler = DocumentAssembler()

		document_assembler.setInputCol(column_name)
		document_assembler.setOutputCol(f'{column_name}_document')

		return document_assembler

	def _create_finisher_stage(self, column_names, output_column_name):
		finisher = Finisher()

		finisher.setCleanAnnotations(False)
		finisher.setInputCols(column_names)
		finisher.setOutputCols(output_column_name)

		return finisher

	def _create_language_detector_stage(self, column_names):
		language_detector = self._language_detector

		language_detector.setInputCols(column_names)
		language_detector.setThreshold(
			self.spark_application.configuration.get(
				'interest.models.language-detector.threshold'))

		return language_detector

	def _create_normalizer_stage(self, column_names, output_column_name):
		normalizer = Normalizer()

		normalizer.setCleanupPatterns(['(?U)[^\w\d\s]'])
		normalizer.setInputCols(column_names)
		normalizer.setOutputCol(output_column_name)

		return normalizer

	def _create_pos_tagger_stage(self, column_names, output_column_name):
		pos_tagger = self._part_of_speech_tagger

		pos_tagger.setInputCols(column_names)
		pos_tagger.setOutputCol(output_column_name)

		return pos_tagger

	def _create_sentence_detector_stage(self, column_names, output_column_name):
		sentence_detector = SentenceDetector()

		sentence_detector.setInputCols(column_names)
		sentence_detector.setOutputCol(output_column_name)

		return sentence_detector

	def _create_tokenizer_stage(self, column_names, output_column_name):
		tokenizer = Tokenizer()

		tokenizer.setInputCols(column_names)
		tokenizer.setOutputCol(output_column_name)
		tokenizer.setSplitChars(['|'])

		return tokenizer

	def _generate_language_detector_pipeline(self):
		pipeline = Pipeline()

		title_document_assembler = self._create_document_assembler_stage(
			'title')

		description_document_assembler = self._create_document_assembler_stage(
			'description')

		language_detector = self._create_language_detector_stage([
			title_document_assembler.getOutputCol(),
			description_document_assembler.getOutputCol()
		])

		finisher_stage = self._create_finisher_stage(
			language_detector.getOutputCol(), 'detected_language')

		pipeline.setStages([
			title_document_assembler,
			description_document_assembler,
			language_detector,
			finisher_stage
		])

		return pipeline

	def _generate_nlp_pipeline(self):
		pipeline = Pipeline()

		description_document_assembler = self._create_document_assembler_stage(
			'description')

		sentence_detector = self._create_sentence_detector_stage(
			[description_document_assembler.getOutputCol()],
			'description_sentences')

		tokenizer = self._create_tokenizer_stage(
			sentence_detector.getOutputCol(), 'description_tokens')

		normalizer = self._create_normalizer_stage(
			tokenizer.getOutputCol(), 'description_normalized')

		part_of_speech_tagger = self._create_pos_tagger_stage([
			sentence_detector.getOutputCol(),
			tokenizer.getOutputCol()],
			'description_pos')

		chunker = self._create_chunker_stage([
			sentence_detector.getOutputCol(),
			part_of_speech_tagger.getOutputCol()],
			'description_chunks')

		finisher = self._create_finisher_stage(
			chunker.getOutputCol(),
			'extracted_keywords')

		pipeline.setStages([
			description_document_assembler,
			sentence_detector,
			tokenizer,
			normalizer,
			part_of_speech_tagger,
			chunker,
			finisher
		])

		return pipeline

	def _get_sample_analytics_event_by_canonical_url(
		self, analytics_events_data_frame):

		window = Window.partitionBy('canonicalUrl')

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
			'title', F.col('title')
		).fillna('')

		nlp_data_frame = nlp_data_frame.withColumn(
			'description', F.col('description')
		).fillna('')

		language_detection_pipeline = \
			self._generate_language_detector_pipeline()

		nlp_data_frame = language_detection_pipeline.fit(
			nlp_data_frame
		).transform(
			nlp_data_frame
		)

		nlp_data_frame = nlp_data_frame.filter(
			F.array_contains(
				F.col('detected_language'), 'en'))

		keyword_pipeline = self._generate_nlp_pipeline()

		nlp_data_frame = keyword_pipeline.fit(
			nlp_data_frame
		).transform(
			nlp_data_frame
		)

		extracted_keywords_data_frame = nlp_data_frame.withColumn(
			'extracted_keywords',
			F.array(
				F.col('extracted_keywords'),
				F.array(F.col('keywords')))
		).withColumn(
			'extracted_keywords', F.flatten(F.col('extracted_keywords'))
		).withColumn(
			'extracted_keywords', F.array_distinct('extracted_keywords')
		).withColumn(
			'extracted_keywords', F.array_remove('extracted_keywords', '')
		).select(
			'canonicalUrl', 'title', 'description', 'title_and_description', 'extracted_keywords'
		)

		extracted_keywords_data_frame.createOrReplaceTempView(
			'extracted_keywords')

class ReadAnalyticsEventsSparkJob(BaseSparkJob):

	def __init__(self, spark_application):
		super(ReadAnalyticsEventsSparkJob, self).__init__(spark_application)

		self._max_days_delta = 60
		self._minimum_interactions_threshold = 5
		self._minimum_view_duration_threshold = 5000

	def run(self):
		sql_command = f"""
			WITH FilteredEvent AS ( 
				SELECT 
					event.canonicalUrl, 
					DATE_DIFF(CURRENT_DATE(), DATE(event.eventDate), DAY) as days_delta,
					event.description, 
					DATE(timestamp_trunc(event.eventDate, DAY)) as event_date,
					event.id,
					COUNT(event.userId) OVER (PARTITION BY event.userId) AS interactions,  
					event.keywords,
					event.sessionId,
					event.title, 
					event.userId, 
					eventproperty.name, 
					eventproperty.value
				FROM 
					`{self.spark_application_args.lcp_project_id}`.event 
				JOIN `{self.spark_application_args.lcp_project_id}`.eventproperty ON 
					event.id = eventproperty.id
				WHERE 
					event.eventDate >= TIMESTAMP(DATE_SUB(CURRENT_DATE(), INTERVAL {self._max_days_delta} DAY)) AND
					eventproperty.eventDate >= TIMESTAMP(DATE_SUB(CURRENT_DATE(), INTERVAL {self._max_days_delta} DAY)) AND
					eventproperty.name = "viewDuration" AND
					STARTS_WITH(event.contentLanguageId, 'en') AND
					CAST(eventproperty.value AS INTEGER) >= {self._minimum_view_duration_threshold}
				)
				SELECT
					canonicalUrl,
					days_delta,
					description, 
					event_date,
					id,
					keywords,  
					name, 
					sessionId,
					title, 
					userId, 
					value
				FROM 
					FilteredEvent
				WHERE 
					interactions >= {self._minimum_interactions_threshold}
			"""

		event_data_frame = self.spark_session.read.format(
			"bigquery"
		).load(sql_command)

		event_data_frame.createOrReplaceTempView(
			'analytics_events')

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