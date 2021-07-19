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

import com.liferay.osb.asah.spark.scala.DateUtil

/**
 * @author Robson Pastor
 */
class Session(
	browserName: String,
	channelId: String,
	city: String,
	clientIp: String,
	country: String,
	dataSourceId: String,
	date: Date,
	deviceType: String,
	platformName: String,
	projectId: String,
	region: String,
	sessionId: String,
	userId: String,
	var bounced: Boolean,
	var canonicalUrls: Set[String] = Set[String](),
	var events: List[Event] = List[Event](),
	var firstEventDate: Timestamp,
	var finished: Boolean,
	var interactionsCount: Int,
	var iterationNumber: Int,
	var lastEventDate: Timestamp,
	var pageViewsCount: Int,
	var referrers: Set[String] = Set[String](),
	var urls: Set[String] = Set[String]()) {

	def addEvent(event: Event): Unit =  {
		event.iterationNumber = iterationNumber
		event.sessionId = sessionId

		canonicalUrls += event.context.getOrElse("canonicalUrl", null)
		events = event :: events
		firstEventDate = DateUtil.min(event.eventDate, firstEventDate)

		if (event.isInteraction()) {
			interactionsCount += 1
		}

		lastEventDate = DateUtil.max(event.eventDate, lastEventDate)

		if (event.isPageViewed()) {
			pageViewsCount += 1
		}

		referrers += event.context.getOrElse("referrer",null)
		urls += event.context.getOrElse("url",null)

		bounced = _isBounced()
	}

	private def _isBounced(): Boolean = {
		if ((interactionsCount < 1) && (pageViewsCount < 2)) {
			true
		}
		else {
			false
		}
	}

}