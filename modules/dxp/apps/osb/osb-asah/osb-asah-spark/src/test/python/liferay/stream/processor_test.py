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
from liferay.stream.processor import DocumentLibraryDataFrameProcessor, \
	JournalDataFrameProcessor

import pytest

@pytest.fixture
def document_library_data_frame_processor(spark_application):
	return DocumentLibraryDataFrameProcessor(
		0, CuratorSparkJob(spark_application)
	)

@pytest.fixture
def journal_data_frame_processor(spark_application):
	return JournalDataFrameProcessor(0, CuratorSparkJob(spark_application))

def test_document_library_data_frame_processor_deduplicate_ratings_events(
	 document_library_data_frame_processor, spark_session
):
	columns = [
		'projectId', 'channelId', 'applicationId', 'sessionId', 'eventId',
		'eventDate', 'assetId'
	]
	rows = [
		(
			'a', 'c1', 'Ratings', '1', 'VOTE', '2019-04-23T16:00:00.000Z', '1'
		),
		(
			'a', 'c1', 'Ratings', '1', 'VOTE', '2019-04-23T16:01:00.000Z', '1'
		),
		(
			'a', 'c1', 'Ratings', '1', 'VOTE', '2019-04-23T16:02:00.000Z', '2'
		),
		(
			'a', 'c1', 'Comment', '1', 'posted', '2019-04-23T16:00:00.000Z', '1'
		),
		(
			'a', 'c1', 'Comment', '1', 'posted', '2019-04-23T16:01:00.000Z', '1'
		),
		(
			'b', 'c1', 'Comment', '1', 'posted', '2019-04-23T16:02:00.000Z', '1'
		)
	]

	actual_data_frame = \
		document_library_data_frame_processor._deduplicate_ratings_events(
			spark_session.createDataFrame(rows, columns)
		)

	actual_data_frame_rows = actual_data_frame.collect()

	assert len(actual_data_frame_rows) == 5

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