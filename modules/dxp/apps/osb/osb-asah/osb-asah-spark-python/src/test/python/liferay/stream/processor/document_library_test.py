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
from liferay.stream.processor import DocumentLibraryDataFrameProcessor

import pytest
import test_util

@pytest.fixture
def document_library_data_frame_processor(spark_application):
	return DocumentLibraryDataFrameProcessor(
		0, 'document-libraries', CuratorSparkJob(spark_application)
	)

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
				'className':
					'com.liferay.document.library.kernel.model.DLFileEntry',
				'classPK': '16527262828'
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
				'className':
					'com.liferay.document.library.kernel.model.DLFileEntry',
				'classPK': '16527262828',
				'score': 3,
			}
		),
		(
			'Ratings', 'VOTE',
			{
				'className':
					'com.liferay.document.library.kernel.model.DLFileEntry',
				'score': 2,
				'ratingType': 'stars'
			}
		),
		(
			'Ratings', 'VOTE',
			{
				'className':
					'com.liferay.document.library.kernel.model.DLFileEntry',
				'ratingType': 'thumbs'
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
		test_util.read_session_events_data_frame(
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

def test_document_library_rating_metric_data_frame_processor_process(
	 document_library_data_frame_processor, spark_session
):

	actual_data_frame = document_library_data_frame_processor.process(
		test_util.read_session_events_data_frame(
			'document_library_rating_metric_data_frame_processor_process_input.json',
			spark_session
		),
		write=False
	)

	actual_data_frame_rows = actual_data_frame.collect()

	assert 2 == len(actual_data_frame_rows)

	row = actual_data_frame_rows[0]

	assert 0 == row.ratings
	assert 0.0 == row.ratingsScore