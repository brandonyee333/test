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

from pyspark.sql import types as T

def assert_data_frames(
	expected_data_frame, actual_data_frame, sort_column_names=['primaryKey']
):
	expected_columns = expected_data_frame.columns

	expected_columns.sort()

	expected_data_frame = expected_data_frame.select(
		expected_columns
	).sort(
		sort_column_names
	)

	actual_data_frame = actual_data_frame.select(
		expected_columns
	).sort(
		sort_column_names
	)

	assert expected_data_frame.collect() == actual_data_frame.collect()

def read_data_frame(file_name, spark_session, schema=None):
	data_frame_reader = spark_session.read

	if schema is not None:
		data_frame_reader = data_frame_reader.schema(schema)

	return data_frame_reader.option(
		'multiLine', 'true'
	).json(
		'resources/liferay/stream/processor/dependencies/{}'.format(file_name)
	)

def read_session_events_data_frame(file_name, spark_session):
	return read_data_frame(
		file_name, spark_session,
		T.StructType([
			T.StructField('applicationId', T.StringType(), False),
			T.StructField('channelId', T.StringType(), False),
			T.StructField(
				'context',
				T.MapType(T.StringType(), T.StringType(), True), True
			),
			T.StructField('eventDate', T.StringType(), False),
			T.StructField('eventId', T.StringType(), False),
			T.StructField(
				'eventProperties',
				T.MapType(T.StringType(), T.StringType(), True), True
			),
			T.StructField('projectId', T.StringType(), False),
			T.StructField('sessionId', T.StringType(), True),
			T.StructField('userId', T.StringType(), False),
			T.StructField('variantId', T.StringType(), True)
		])
	)