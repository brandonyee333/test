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

from liferay.stream.processor.common import AnalyticsEventsDataFrameProcessor

from pyspark.sql import functions as F

class DocumentLibraryDataFrameProcessor(AnalyticsEventsDataFrameProcessor):

	def _filter(self, analytics_events_data_frame):
		return analytics_events_data_frame.filter(
			"""
				(
					(
						(applicationId = 'Comment') AND 
						(eventId = 'posted') AND 
						(eventProperties.className = '{class_name}') AND
						(eventProperties.classPK != '')
					) OR
					(
						(applicationId = 'Document') AND 
						(
							(eventId = 'documentDownloaded') OR
							(eventId = 'documentPreviewed')
						) AND 
						( 
							(eventProperties.fileEntryId != '')
						)
					) OR 
					(
						(applicationId = 'Ratings') AND 
						(eventId = 'VOTE') AND
						(eventProperties.className = '{class_name}') AND
						(eventProperties.classPK != '') AND
						(
							(eventProperties.ratingType IS NULL) OR
							(eventProperties.ratingType = 'stars')
						)
					)
				)	
			""".format(
				class_name =
					'com.liferay.document.library.kernel.model.DLFileEntry'
			)
		)

	def _get_asset_id_column(self):
		return F.when(
			F.col('eventId').isin('posted', 'VOTE'),
			F.col('eventProperties.classPK')
		).otherwise(
			F.col('eventProperties.fileEntryId')
		)

	def _process(self, data_frame):
		data_frame_with_rating_score = self._calculate_rating_score(data_frame)

		data_frame = data_frame.filter(
			"applicationId != 'Ratings'"
		).withColumn(
			'comments',
			F.when(
				F.col('eventId') == 'posted', F.lit(1)
			).otherwise(
				F.lit(0)
			)
		).withColumn(
			'downloads',
			F.when(
				F.col('eventId') == 'documentDownloaded', F.lit(1)
			).otherwise(
				F.lit(0)
			)
		).withColumn(
			'previews',
			F.when(
				F.col('eventId') == 'documentPreviewed', F.lit(1)
			).otherwise(
				F.lit(0)
			)
		).groupBy(
			'projectId', 'channelId', 'userId', 'assetId', 'variantId',
			'normalized_event_date', 'primaryKey'
		).agg(
			F.sum('comments').alias('comments'),
			F.sum('downloads').alias('downloads'),
			F.sum('previews').alias('previews')
		)

		return data_frame.join(
			data_frame_with_rating_score,
			how='left',
			on=[
				'projectId', 'channelId', 'userId', 'assetId', 'variantId',
				'normalized_event_date', 'primaryKey'
			]
		).fillna(0)