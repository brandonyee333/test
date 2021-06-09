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
from liferay.stream.processor import FormDataFrameProcessor, \
	FormFieldDataFrameProcessor, \
	FormPageDataFrameProcessor

import pytest
import test_util

from pyspark.sql import types as T

@pytest.fixture
def form_data_frame_processor(spark_application):
	return FormDataFrameProcessor(
		0, 'forms', CuratorSparkJob(spark_application)
	)

@pytest.fixture
def form_field_data_frame_processor(spark_application):
	return FormFieldDataFrameProcessor(
		0, 'form-fields', CuratorSparkJob(spark_application)
	)

@pytest.fixture
def form_page_data_frame_processor(spark_application):
	return FormPageDataFrameProcessor(
		0, 'form-pages', CuratorSparkJob(spark_application)
	)

def test_form_data_frame_processor_calculate_submission_time(
	form_data_frame_processor, spark_session
):

	input_data_frame = form_data_frame_processor._pre_process(
		test_util.read_session_events_data_frame(
			'form_data_frame_processor_calculate_submission_time_input.json',
			spark_session
		)
	)

	actual_data_frame = form_data_frame_processor._calculate_submission_time(
		input_data_frame
	)

	actual_data_frame_rows = actual_data_frame.collect()

	assert len(actual_data_frame_rows) == 1

	assert actual_data_frame_rows[0].submission_time == 120

def test_form_data_frame_processor_filter(
	form_data_frame_processor, spark_session
):

	actual_data_frame = form_data_frame_processor._filter(
		test_util.read_session_events_data_frame(
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
		test_util.read_session_events_data_frame(
			'form_data_frame_processor_process_input.json',
			spark_session
		),
		write=False
	)

	expected_data_frame = test_util.read_data_frame(
		'form_data_frame_processor_process_expected_output.json', spark_session,
		T.StructType([
			T.StructField('abandonments', T.LongType(), False),
			T.StructField('assetId', T.StringType(), False),
			T.StructField('channelId', T.StringType(), False),
			T.StructField('normalized_event_date', T.TimestampType(), False),
			T.StructField('primaryKey', T.StringType(), False),
			T.StructField('projectId', T.StringType(), False),
			T.StructField('submission_time', T.LongType(), False),
			T.StructField('submissions', T.LongType(), False),
			T.StructField('userId', T.StringType(), False),
			T.StructField('variantId', T.StringType(), False),
			T.StructField('views', T.LongType(), False)
		])
	)

	test_util.assert_data_frames(expected_data_frame, actual_data_frame)

def test_form_field_data_frame_processor_process(
	form_field_data_frame_processor, spark_session
):

	actual_data_frame = form_field_data_frame_processor.process(
		test_util.read_session_events_data_frame(
			'form_field_data_frame_processor_process_input.json',
			spark_session
		),
		write=False
	)

	expected_data_frame = test_util.read_data_frame(
		'form_field_data_frame_processor_process_expected_output.json',
		spark_session,
		T.StructType([
			T.StructField('projectId', T.StringType(), False),
			T.StructField('channelId', T.StringType(), False),
			T.StructField('userId', T.StringType(), False),
			T.StructField('assetId', T.StringType(), False),
			T.StructField('variantId', T.StringType(), False),
			T.StructField('normalized_event_date', T.TimestampType(), False),
			T.StructField('primaryKey', T.StringType(), False),
			T.StructField('page_index', T.IntegerType(), False),
			T.StructField('field_name', T.StringType(), False),
			T.StructField('abandonments', T.LongType(), False),
			T.StructField('interaction_duration', T.LongType(), False),
			T.StructField('interactions', T.LongType(), False)
		])
	)

	assert expected_data_frame.collect() == actual_data_frame.collect()

def test_form_page_data_frame_processor_process(
	form_page_data_frame_processor, spark_session
):

	actual_data_frame = form_page_data_frame_processor.process(
		test_util.read_session_events_data_frame(
			'form_page_data_frame_processor_process_input.json',
			spark_session
		),
		write=False
	)

	expected_data_frame = test_util.read_data_frame(
		'form_page_data_frame_processor_process_expected_output.json',
		spark_session,
		T.StructType([
			T.StructField('abandonments', T.LongType(), False),
			T.StructField('assetId', T.StringType(), False),
			T.StructField('channelId', T.StringType(), False),
			T.StructField('normalized_event_date', T.TimestampType(), False),
			T.StructField('page_index', T.IntegerType(), False),
			T.StructField('primaryKey', T.StringType(), False),
			T.StructField('projectId', T.StringType(), False),
			T.StructField('userId', T.StringType(), False),
			T.StructField('variantId', T.StringType(), False),
			T.StructField('views', T.LongType(), False)
		])
	)

	test_util.assert_data_frames(expected_data_frame, actual_data_frame)