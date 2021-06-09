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
from liferay.stream.processor import PageDataFrameProcessor, \
	PageReferrerDataFrameProcessor

import pytest
import test_util

from liferay.stream.udf import AcquisitionChannelFunction

from pyspark.sql import types as T

@pytest.fixture
def page_data_frame_processor(spark_application):
	return PageDataFrameProcessor(
		0, 'pages', CuratorSparkJob(spark_application)
	)

@pytest.fixture(scope='session')
def page_referrer_data_frame_processor(spark_application):
	AcquisitionChannelFunction(
		spark_application.spark_session,
		search_host_names=['google.com'], social_host_names=['facebook.com']
	)

	return PageReferrerDataFrameProcessor(
		0, 'page-referrers', CuratorSparkJob(spark_application)
	)

def test_page_data_frame_create_page_time_on_page_data_frame(
	page_data_frame_processor, spark_session
):

	actual_data_frame = page_data_frame_processor._create_page_time_on_page_data_frame(
		test_util.read_session_events_data_frame(
			'page_data_frame_processor_create_page_time_on_page_data_frame_input.json',
			spark_session
		)
	)

	expected_data_frame = test_util.read_data_frame(
		'page_data_frame_processor_create_page_time_on_page_data_frame_expected_output.json',
		spark_session,
		T.StructType([
			T.StructField('channelId', T.StringType(), False),
			T.StructField('normalized_event_date', T.TimestampType(), False),
			T.StructField('primaryKey', T.StringType(), False),
			T.StructField('projectId', T.StringType(), False),
			T.StructField("sessionId", T.StringType(), False),
			T.StructField('time_on_page', T.LongType(), False),
			T.StructField('url', T.StringType(), False),
			T.StructField('userId', T.StringType(), False),
			T.StructField('variantId', T.StringType(), False)
		])
	)

	test_util.assert_data_frames(expected_data_frame, actual_data_frame)

def test_page_data_frame_processor_process(
	page_data_frame_processor, spark_session
):

	actual_data_frame = page_data_frame_processor.process(
		test_util.read_session_events_data_frame(
			'page_data_frame_processor_process_input.json',
			spark_session
		),
		write=False
	)

	expected_data_frame = test_util.read_data_frame(
		'page_data_frame_processor_process_expected_output.json',
		spark_session,
		T.StructType([
			T.StructField("bounces", T.LongType(), False),
			T.StructField("channelId", T.StringType(), False),
			T.StructField("cta_clicks", T.LongType(), False),
			T.StructField("direct_access", T.LongType(), False),
			T.StructField("entrances", T.LongType(), False),
			T.StructField("exits", T.LongType(), False),
			T.StructField("indirect_access", T.LongType(), False),
			T.StructField("normalized_event_date", T.TimestampType(), False),
			T.StructField("primaryKey", T.StringType(), False),
			T.StructField("projectId", T.StringType(), False),
			T.StructField("reads", T.LongType(), False),
			T.StructField("sessionId", T.StringType(), False),
			T.StructField('time_on_page', T.LongType(), False),
			T.StructField("url", T.StringType(), False),
			T.StructField("userId", T.StringType(), False),
			T.StructField("variantId", T.StringType(), False),
			T.StructField("views", T.LongType(), False)
		])
	)

	test_util.assert_data_frames(expected_data_frame, actual_data_frame)

def test_page_referrer_data_frame_processor_process(
	page_referrer_data_frame_processor, spark_session
):

	actual_data_frame = page_referrer_data_frame_processor.process(
		test_util.read_session_events_data_frame(
			'page_referrer_data_frame_processor_process_input.json',
			spark_session
		),
		write=False
	)

	expected_data_frame = test_util.read_data_frame(
		'page_referrer_data_frame_processor_process_expected_output.json',
		spark_session,
		T.StructType([
			T.StructField("access", T.LongType(), False),
			T.StructField("acquisition_channel", T.StringType(), False),
			T.StructField("channelId", T.StringType(), False),
			T.StructField("normalized_event_date", T.TimestampType(), False),
			T.StructField("primaryKey", T.StringType(), False),
			T.StructField("projectId", T.StringType(), False),
			T.StructField("referrer", T.StringType(), False),
			T.StructField("url", T.StringType(), False),
			T.StructField("userId", T.StringType(), False),
			T.StructField("variantId", T.StringType(), False)
		])
	)

	test_util.assert_data_frames(expected_data_frame, actual_data_frame)