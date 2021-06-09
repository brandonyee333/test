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
from liferay.stream.processor import BlogDataFrameProcessor

import pytest
import test_util

@pytest.fixture
def blog_data_frame_processor(spark_application):
	return BlogDataFrameProcessor(
		0, 'blogs', CuratorSparkJob(spark_application)
	)

def test_blog_data_frame_processor_calculate_read_time(
	blog_data_frame_processor, spark_session
):
	input_data_frame = blog_data_frame_processor._pre_process(
		test_util.read_session_events_data_frame(
			'blog_data_frame_processor_calculate_read_time_input.json',
			spark_session
		)
	)

	actual_data_frame = blog_data_frame_processor._calculate_read_time(
		input_data_frame
	)

	actual_data_frame_rows = actual_data_frame.collect()

	assert len(actual_data_frame_rows) == 1

	assert actual_data_frame_rows[0].read_time == 44

def test_blog_data_frame_processor_filter(
	blog_data_frame_processor, spark_session
):

	actual_data_frame = blog_data_frame_processor._filter(
		test_util.read_session_events_data_frame(
			'blog_data_frame_processor_filter_input.json', spark_session
		)
	)

	expected_data_frame = test_util.read_session_events_data_frame(
		'blog_data_frame_processor_filter_expected_output.json', spark_session
	)

	assert expected_data_frame.collect() == actual_data_frame.collect()