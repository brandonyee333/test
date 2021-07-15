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
	var sessionId: String = null,
	id:String,
	userId: String,
	projectId : String = null,
	date: Date,
	dataSourceId : String = null,
	channelId: String = null,
	clientIp: String = null,
	eventDate: Timestamp,
	normalizedEventDate:Long,
	eventId: String = null,
	createDate: Timestamp = null,
	context: Map[String,String] = Map[String,String](),
	eventProperties: Map[String,String] = Map[String,String](),
	applicationId:String = null,
	analyticsKey: String = null,
	knownIndividual:Boolean,
	var iterationNumber:Int=0){
	def browserName:String = context.getOrElse("browserName",null)
	def canonicalUrl:String = context.getOrElse("canonicalUrl",null)
	def city:String = context.getOrElse("city",null)
	def country:String = context.getOrElse("country",null)
	def deviceType:String = context.getOrElse("deviceType",null)
	def platformName:String = context.getOrElse("platformName",null)
	def referrer:String = context.getOrElse("referrer",null)
	def region:String = context.getOrElse("region",null)
	def url:String = context.getOrElse("url",null)
}