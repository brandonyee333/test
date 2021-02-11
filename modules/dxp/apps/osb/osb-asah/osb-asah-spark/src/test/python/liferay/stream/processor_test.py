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

from liferay.stream.job import CuratorSparkJob
from liferay.stream.processor import BlogDataFrameProcessor, \
	DocumentLibraryDataFrameProcessor, \
	FormDataFrameProcessor, \
	JournalDataFrameProcessor

from pyspark.sql import functions as F, \
	types as T

import pytest

@pytest.fixture
def blog_data_frame_processor(spark_application):
	return BlogDataFrameProcessor(0, CuratorSparkJob(spark_application))

@pytest.fixture
def document_library_data_frame_processor(spark_application):
	return DocumentLibraryDataFrameProcessor(
		0, CuratorSparkJob(spark_application)
	)

@pytest.fixture
def form_data_frame_processor(spark_application):
	return FormDataFrameProcessor(0, CuratorSparkJob(spark_application))

@pytest.fixture
def journal_data_frame_processor(spark_application):
	return JournalDataFrameProcessor(0, CuratorSparkJob(spark_application))

def read_data_frame(file_name, spark_session, schema=None):
	data_frame_reader = spark_session.read

	if schema is not None:
		data_frame_reader = data_frame_reader.schema(schema)\

	return data_frame_reader.option(
		"multiLine", "true"
	).json(
		'resources/liferay/stream/dependencies/{}'.format(file_name)
	)

def read_session_events_data_frame(file_name, spark_session):
	return read_data_frame(
		file_name, spark_session,
		T.StructType([
			T.StructField("applicationId", T.StringType(), False),
			T.StructField("channelId", T.StringType(), False),
			T.StructField(
				"context",
				T.MapType(T.StringType(), T.StringType(), True), True
			),
			T.StructField("eventDate", T.StringType(), False),
			T.StructField("eventId", T.StringType(), False),
			T.StructField(
				"eventProperties",
				T.MapType(T.StringType(), T.StringType(), True), True
			),
			T.StructField("projectId", T.StringType(), False),
			T.StructField("sessionId", T.StringType(), True),
			T.StructField("userId", T.StringType(), False),
			T.StructField("variantId", T.StringType(), True)
		])
	)

def test_blog_data_frame_processor_calculate_read_time(
	blog_data_frame_processor, spark_session
):

	actual_data_frame = blog_data_frame_processor._calculate_read_time(
		read_session_events_data_frame(
			'blog_data_frame_processor_calculate_read_time_input.json',
			spark_session
		).withColumn(
			'assetId', F.col('eventProperties.entryId')
		).withColumn(
			'event_date',
			F.to_timestamp(F.col('eventDate'))
		)
	)

	actual_data_frame_rows = actual_data_frame.collect()

	assert len(actual_data_frame_rows) == 1

	assert actual_data_frame_rows[0].read_time == 44

def test_blog_data_frame_processor_filter(
	blog_data_frame_processor, spark_session
):

	actual_data_frame = blog_data_frame_processor._filter(
		read_session_events_data_frame(
			'blog_data_frame_processor_filter_input.json', spark_session
		)
	)

	expected_data_frame = read_session_events_data_frame(
		'blog_data_frame_processor_filter_expected_output.json', spark_session
	)

	assert expected_data_frame.collect() == actual_data_frame.collect()

def test_document_library_data_frame_processor_filter(
	 document_library_data_frame_processor, spark_session
):
	columns = ['applicationId', 'eventId', 'eventProperties']
	rows = [
		(
			'WebContent', 'webContentViewed', {"articleId": "245455875"}
		),
		(
			'Page', 'pageViewed', {}
		),
		(
			'Comment', 'posted',
			{
				"className": "com.liferay.blogs.model.BlogsEntry",
				"classPK": "25262823893"
			}
		),
		(
			'Comment', 'posted',
			{
				"className":
					"com.liferay.document.library.kernel.model.DLFileEntry",
				"classPK": "16527262828"
			 }
		),
		(
			'Document', 'documentDownloaded', {"fileEntryId": ""}
		),
		(
			'Document', 'documentLoaded', {}
		),
		(
			'Document', 'documentPreviewed', {"fileEntryId": "16527262828"}
		),
		(
			'Ratings', 'VOTE',
			{
				"className":
					"com.liferay.document.library.kernel.model.DLFileEntry",
				"classPK": "16527262828",
				"score": 3,
			}
		),
		(
			'Ratings', 'VOTE',
			{
				"className":
					"com.liferay.document.library.kernel.model.DLFileEntry",
				"score": 2,
				"ratingType": "stars"
			}
		),
		(
			'Ratings', 'VOTE',
			{
				"className":
					"com.liferay.document.library.kernel.model.DLFileEntry",
				"ratingType": "thumbs"
			}
		)
	]

	actual_data_frame = document_library_data_frame_processor._filter(
		spark_session.createDataFrame(rows, columns)
	)

	actual_data_frame_rows = actual_data_frame.collect()

	assert len(actual_data_frame_rows) == 3

def test_document_library_data_frame_processor_process(
	 document_library_data_frame_processor, spark_session
):

	actual_data_frame = document_library_data_frame_processor.process(
		read_session_events_data_frame(
			'document_library_data_frame_processor_process_input.json',
			spark_session
		),
		write=False
	)

	actual_data_frame_rows = actual_data_frame.collect()

	assert len(actual_data_frame_rows) == 1

	row = actual_data_frame_rows[0]

	assert 0 == row.comments
	assert 0 == row.downloads
	assert 3 == row.previews
	assert 1 == row.ratings
	assert 2.0 == row.ratingsScore

def test_form_data_frame_processor_calculate_submission_time(
	form_data_frame_processor, spark_session
):

	actual_data_frame = form_data_frame_processor._calculate_submission_time(
		read_session_events_data_frame(
			'form_data_frame_processor_calculate_submission_time_input.json',
			spark_session
		).withColumn(
			'assetId', F.col('eventProperties.formId')
		).withColumn(
			'event_date',
			F.to_timestamp(F.col('eventDate'))
		)
	)

	actual_data_frame_rows = actual_data_frame.collect()

	assert len(actual_data_frame_rows) == 1

	assert actual_data_frame_rows[0].submission_time == 120

def test_form_data_frame_processor_filter(
	form_data_frame_processor, spark_session
):

	actual_data_frame = form_data_frame_processor._filter(
		read_session_events_data_frame(
			'form_data_frame_processor_filter_input.json',
			spark_session
		)
	)

	actual_data_frame_rows = actual_data_frame.collect()

	assert len(actual_data_frame_rows) == 4

def test_form_data_frame_processor_process(
	form_data_frame_processor, spark_session
):
	actual_data_frame = form_data_frame_processor.process(
		read_session_events_data_frame(
			'form_data_frame_processor_process_input.json',
			spark_session
		),
		write=False
	)

	expected_data_frame = read_data_frame(
		'form_data_frame_processor_process_expected_output.json', spark_session,
		T.StructType([
			T.StructField("projectId", T.StringType(), False),
			T.StructField("channelId", T.StringType(), False),
			T.StructField("userId", T.StringType(), False),
			T.StructField("assetId", T.StringType(), False),
			T.StructField("variantId", T.StringType(), False),
			T.StructField("normalized_event_date", T.TimestampType(), False),
			T.StructField("primaryKey", T.StringType(), False),
			T.StructField("submissions", T.LongType(), False),
			T.StructField("views", T.LongType(), False),
			T.StructField("abandonments", T.LongType(), False),
		])
	)

	assert expected_data_frame.collect() == actual_data_frame.collect()

def test_journal_data_frame_processor_filter(
		journal_data_frame_processor, spark_session
):
	columns = ['applicationId', 'eventId', 'eventProperties']
	rows = [
		(
			'WebContent', 'webContentViewed', {"articleId": "245455875"}
		),
		(
			'WebContent', 'webContentViewed', {"articleId": ""}
		),
		(
			'WebContent', 'webContentViewed', {}
		),
		(
			"Page", 'pageViewed', {}
		)
	]

	actual_data_frame = journal_data_frame_processor._filter(
		spark_session.createDataFrame(rows, columns)
	)

	actual_data_frame_rows = actual_data_frame.collect()

	assert len(actual_data_frame_rows) == 1

	assert actual_data_frame_rows[0].applicationId == 'WebContent'