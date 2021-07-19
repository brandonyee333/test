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


    def checkInteraction(eventId:String):Int={
        if (nonInteractionEvents.contains(eventId)) 0 else 1
    }

    def checkPageView(applicationId:String, eventId:String):Int={
        if (eventId=="pageViewed" && applicationId=="Page")
            1
        else
            0
    }

    def createSessionFromEvent(event: Event): Session = {
        val session = Session(
            browserName = event.context.getOrElse("browserName",null),
            bounced = true,
            channelId = event.channelId,
            city = event.context.getOrElse("city",null),
            clientIp = event.clientIp,
            country = event.context.getOrElse("country",null),
            dataSourceId = event.dataSourceId,
            date = event.date,
            deviceType = event.context.getOrElse("deviceType",null),
            events = List[Event](),
            finished = false,
            firstEventDate = event.eventDate,
            interactionsCount = 0,
            iterationNumber = 0,
            lastEventDate = event.eventDate,
            pageViewsCount = 0,
            platformName = event.context.getOrElse("platformName",null),
            projectId = event.projectId,
            region = event.context.getOrElse("region",null),
            sessionId = UUID.randomUUID.toString,
            userId = event.userId,
        )
        session.addEvent(event)
    }


    def isSessionBounced(interactionsCount:Int,pageViewsCount:Int):Boolean= {
        (interactionsCount < 1) && (pageViewsCount < 2)
    }


    implicit class SessionImprovements(val session:Session) {
        def addEvent(event:Event):Session =  {
            event.iterationNumber = session.iterationNumber
            event.sessionId = session.sessionId
            session.canonicalUrls += event.context.getOrElse("canonicalUrl",null)
            session.events = event :: session.events
            session.firstEventDate = session.firstEventDate.min(event.eventDate)
            session.interactionsCount += checkInteraction(event.eventId)
            session.lastEventDate = session.lastEventDate.max(event.eventDate)
            session.pageViewsCount += checkPageView(event.applicationId,event.eventId)
            session.referrers += event.context.getOrElse("referrer",null)
            session.urls += event.context.getOrElse("url",null)

            session.bounced = isSessionBounced(
                  session.interactionsCount,
                  session.pageViewsCount
            )

            session
        }
    }

    private val nonInteractionEvents = Set[String]("blogViewed",
    "documentPreviewed",
    "formViewed",
    "pageLoaded",
    "pageUnloaded",
    "pageViewed",
    "webContentViewed")


}