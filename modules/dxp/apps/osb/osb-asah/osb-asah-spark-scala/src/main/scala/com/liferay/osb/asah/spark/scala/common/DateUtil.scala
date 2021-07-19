/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.asah.spark.scala

import java.sql.{Date, Timestamp}
import java.text.SimpleDateFormat

/**
 * @author Robson Pastor
 */
object DateUtil {

	def max(timestamp1: Timestamp, timestamp2: Timestamp): Timestamp = {
		if (timestamp1.after(timestamp2)) {
			timestamp1
		}
		else {
			timestamp2
		}
	}

	def min(timestamp1: Timestamp, timestamp2: Timestamp): Timestamp = {
		if (timestamp1.before(timestamp2)) {
			timestamp1
		}
		else {
			timestamp2
		}
	}

	def toDate(dateString: String): Date = {
		val date = _parseDate(dateString, _simpleDateFormat)

		new Date(date.getTime())
	}

	def toTimestamp(timestampString: String): Timestamp =  {
		val date = _parseDate(timestampString, _simpleTimestampFormat)

		new Timestamp(date.getTime())
	}

	private def _parseDate(
			dateString: String, simpleDateFormat: SimpleDateFormat):
		java.util.Date = {

		simpleDateFormat.parse(dateString)
	}

	private val _simpleDateFormat: SimpleDateFormat =
		new SimpleDateFormat("yyyy-MM-dd")

	private val _simpleTimestampFormat: SimpleDateFormat =
		new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")

}