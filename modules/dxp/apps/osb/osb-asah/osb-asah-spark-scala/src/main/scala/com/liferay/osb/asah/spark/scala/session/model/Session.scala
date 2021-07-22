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

import com.liferay.osb.asah.spark.scala.DateUtil

import java.sql.{Date, Timestamp}

/**
 * @author Robson Pastor
 */
case class Session(
	bounced: Boolean,
	browserName: String,
	canonicalUrls: Set[String] = Set[String](),
	channelId: String,
	city: String,
	clientIp: String,
	country: String,
	dataSourceId: String,
	date: Date,
	deviceType: String,
	events: List[Event] = List[Event](),
	finished: Boolean,
	firstEventDate: Timestamp,
	interactionsCount: Int,
	iterationNumber: Int,
	lastEventDate: Timestamp,
	pageViewsCount: Int,
	platformName: String,
	projectId: String,
	referrers: Set[String] = Set[String](),
	region: String,
	sessionId: String,
	urls: Set[String] = Set[String](),
	userId: String)

object Session {

	def addEvent(event: Event, session: Session): Session = {
		var interactionsCount = session.interactionsCount

		if (Event.isInteraction(event.eventId)) {
			interactionsCount += 1
		}

		var pageViewsCount = session.pageViewsCount

		if (Event.isPageViewed(event.applicationId, event.eventId)) {
			pageViewsCount += 1
		}

		val eventContext = event.context

		session.copy(
			bounced = Session.isBounced(interactionsCount, pageViewsCount),
			canonicalUrls =
				session.canonicalUrls ++ eventContext.get("canonicalUrl"),
			events = event :: session.events,
			firstEventDate =
				DateUtil.min(event.eventDate, session.firstEventDate),
			interactionsCount = interactionsCount,
			lastEventDate =
				DateUtil.max(event.eventDate, session.lastEventDate),
			pageViewsCount = pageViewsCount,
			referrers = session.referrers ++ eventContext.get("referrer"),
			urls = session.urls ++ eventContext.get("url"))
	}

	def isBounced(interactionsCount:Int, pageViewsCount:Int): Boolean = {
		if ((interactionsCount < 1) && (pageViewsCount < 2)) {
			true
		}
		else {
			false
		}
	}

}