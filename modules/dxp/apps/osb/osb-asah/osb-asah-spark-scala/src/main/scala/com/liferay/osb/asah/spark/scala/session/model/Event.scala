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

package com.liferay.osb.asah.spark.scala.session.model

import java.sql.{Date, Timestamp}

/**
 * @author Robson Pastor
 */
case class Event(
	analyticsKey: String,
	applicationId: String,
	channelId: String,
	clientIp: String,
	context: Map[String,String] = Map[String,String](),
	createDate: Timestamp,
	dataSourceId: String,
	date: Date,
	eventDate: Timestamp,
	eventId: String,
	eventProperties: Map[String,String] = Map[String,String](),
	id: String,
	knownIndividual: Boolean,
	normalizedEventDate: Long,
	projectId: String,
	segmentNames: Set[String] = Set[String](),
	userId: String,
	var iterationNumber: Int,
	var sessionId: String) {

	def isInteraction(): Boolean = {
		if (_nonInteractionEventIds.contains(eventId)) {
			false
		}
		else {
			true
		}
	}

	def isPageViewed(): Boolean = {
		if ((eventId == "pageViewed") && (applicationId == "Page")) {
			true
		}
		else {
			false
		}
	}

	private val _nonInteractionEventIds = Set[String](
		"blogViewed",
		"documentPreviewed",
		"formViewed",
		"pageLoaded",
		"pageUnloaded",
		"pageViewed",
		"webContentViewed")
}