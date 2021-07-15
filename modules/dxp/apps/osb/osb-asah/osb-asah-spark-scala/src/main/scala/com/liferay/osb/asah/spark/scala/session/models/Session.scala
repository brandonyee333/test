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

package com.liferay.osb.asah.spark.scala.session.models

import java.sql.{Date, Timestamp}

/**
 * @author Robson Pastor
 */
case class Session(
	sessionId: String,
	userId: String,
	projectId:String,
	date:Date,
	dataSourceId:String,
	channelId: String,
	clientIp:String=null,
	country:String=null,
	region:String=null,
	city:String=null,
	deviceType:String=null,
	platformName:String=null,
	browserName:String=null,
	firstEventDate:Timestamp,
	var events: List[Event],
	var lastEventDate: Timestamp,
	var interactionsCount:Int=0,
	var pageViewsCount:Int=0,
	var canonicalUrls:Set[String]=Set[String](),
	var referrers:Set[String]=Set[String](),
	var urls:Set[String]=Set[String](),
	var bounced:Boolean=true,
	var finished:Boolean=false,
	var iterationNumber:Int=0)
