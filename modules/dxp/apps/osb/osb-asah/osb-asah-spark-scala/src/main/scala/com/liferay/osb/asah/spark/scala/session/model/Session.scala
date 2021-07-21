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

	def addEvent(event: Event, session: Session): Session =  {

		val eventCopy = event.copy(iterationNumber = session.iterationNumber)

		val eventContext = eventCopy.context

		val canonicalUrls: Set[String] =
			session.canonicalUrls ++ eventContext.get("canonicalUrl")
		val events: List[Event] = eventCopy :: session.events
		val firstEventDate = DateUtil.min(
			eventCopy.eventDate,
			session.firstEventDate
		)
		val interactionsCount = if (Event.isInteraction(
			eventCopy.eventId
		)) {
			1 + session.interactionsCount
		}
		else {
			session.interactionsCount
		}
		val lastEventDate = DateUtil.max(
			eventCopy.eventDate,
			session.lastEventDate
		)
		val pageViewsCount = if (Event.isPageViewed(
			eventCopy.applicationId,
			eventCopy.eventId
		)) {
			1 + session.pageViewsCount}
		else{
			session.pageViewsCount
		}
		val referrers: Set[String] =
			session.referrers ++ eventContext.get("referrer")
		val urls: Set[String] = session.urls ++ eventContext.get("url")

		val bounced = Session.isBounced(interactionsCount, pageViewsCount)

		session.copy(bounced = bounced,
			canonicalUrls = canonicalUrls,
			events = events,
			firstEventDate = firstEventDate,
			interactionsCount = interactionsCount,
			lastEventDate = lastEventDate,
			pageViewsCount = pageViewsCount,
			referrers = referrers,
			urls = urls)
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