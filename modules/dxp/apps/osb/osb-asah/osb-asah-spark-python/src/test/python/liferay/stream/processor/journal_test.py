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
from liferay.stream.processor import JournalDataFrameProcessor

import pytest

@pytest.fixture
def journal_data_frame_processor(spark_application):
	return JournalDataFrameProcessor(
		0, 'journals', CuratorSparkJob(spark_application)
	)

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