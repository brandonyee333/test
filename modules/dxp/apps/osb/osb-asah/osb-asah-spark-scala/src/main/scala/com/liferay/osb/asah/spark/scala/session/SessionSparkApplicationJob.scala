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

package com.liferay.osb.asah.spark.scala.session

import com.liferay.osb.asah.spark.scala.common._
import com.liferay.osb.asah.spark.scala.session.model.{Event, Session}

import java.util.UUID

/**
 * @author Marcellus Tavares
 */
class SessionSparkApplicationJob(
        val sessionSparkApplication: SessionSparkApplication)
    extends SparkJob {

    override def run(): Unit = {
    }

}

object SessionSparkApplicationJob {

    def isInteractionEvent(eventId: String): Boolean = {
        if (nonInteractionEvents.contains(eventId)) {
            false
        }
        else {
            true
        }
    }

    def isPageViewedEvent(applicationId: String, eventId: String): Boolean = {
        if ((eventId == "pageViewed") && (applicationId == "Page")) {
            true
        }
        else {
            false
        }
    }

    def createSession(event: Event): Session = {
        val eventContext: Map[String, String] = event.context

        val session = Session(
            bounced = true,
            browserName = eventContext.getOrElse("browserName", null),
            channelId = event.channelId,
            city = eventContext.getOrElse("city", null),
            clientIp = event.clientIp,
            country =eventContext.getOrElse("country", null),
            dataSourceId = event.dataSourceId,
            date = event.date,
            deviceType = eventContext.getOrElse("deviceType", null),
            events = List[Event](),
            finished = false,
            firstEventDate = event.eventDate,
            interactionsCount = 0,
            iterationNumber = 0,
            lastEventDate = event.eventDate,
            pageViewsCount = 0,
            platformName = eventContext.getOrElse("platformName", null),
            projectId = event.projectId,
            region = eventContext.getOrElse("region", null),
            sessionId = UUID.randomUUID.toString,
            userId = event.userId
        )

        session.addEvent(event)
    }


    def isSessionBounced(interactionsCount: Int, pageViewsCount: Int): Boolean = {
        if ((interactionsCount < 1) && (pageViewsCount < 2)) {
            true
        }
        else {
            false
        }
    }

    implicit class SessionImprovements(val session:Session) {
        def addEvent(event:Event):Session =  {
            event.iterationNumber = session.iterationNumber
            event.sessionId = session.sessionId
            session.canonicalUrls += event.context.getOrElse("canonicalUrl", null)
            session.events = event :: session.events
            session.firstEventDate = session.firstEventDate.min(event.eventDate)

            if (isInteractionEvent(event.eventId)) {
                session.interactionsCount += 1
            }

            session.lastEventDate = session.lastEventDate.max(event.eventDate)

            if (isPageViewedEvent(event.applicationId, event.eventId)) {
                session.pageViewsCount += 1
            }

            session.referrers += event.context.getOrElse("referrer",null)
            session.urls += event.context.getOrElse("url",null)

            session.bounced = isSessionBounced(
                  session.interactionsCount,
                  session.pageViewsCount
            )

            session
        }
    }

    private val nonInteractionEvents = Set[String](
        "blogViewed",
        "documentPreviewed",
        "formViewed",
        "pageLoaded",
        "pageUnloaded",
        "pageViewed",
        "webContentViewed")

}