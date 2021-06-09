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
from liferay.stream.processor import CustomAssetDataFrameProcessor

import pytest
import test_util

from pyspark.sql import types as T

@pytest.fixture
def custom_asset_data_frame_processor(spark_application):
	return CustomAssetDataFrameProcessor(
		0, 'custom-assets', CuratorSparkJob(spark_application)
	)

def test_custom_asset_data_frame_processor_process(
	 custom_asset_data_frame_processor, spark_session
):

	actual_data_frame = custom_asset_data_frame_processor.process(
		test_util.read_session_events_data_frame(
			'custom_asset_data_frame_processor_process_input.json',
			spark_session
		),
		write=False
	)

	expected_data_frame = test_util.read_data_frame(
		'custom_asset_data_frame_processor_process_expected_output.json',
		spark_session,
		T.StructType([
			T.StructField('abandonments', T.LongType(), False),
			T.StructField('assetId', T.StringType(), False),
			T.StructField('category', T.StringType(), False),
			T.StructField('channelId', T.StringType(), False),
			T.StructField('clicks', T.LongType(), False),
			T.StructField('downloads', T.LongType(), False),
			T.StructField('normalized_event_date', T.TimestampType(), False),
			T.StructField('projectId', T.StringType(), False),
			T.StructField('read_time', T.LongType(), False),
			T.StructField('submission_time', T.LongType(), False),
			T.StructField('submissions', T.LongType(), False),
			T.StructField('userId', T.StringType(), False),
			T.StructField('variantId', T.StringType(), False),
			T.StructField('views', T.LongType(), False)
		])
	)

	test_util.assert_data_frames(expected_data_frame, actual_data_frame, sort_column_names=['assetId'])