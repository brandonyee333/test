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

package com.liferay.osb.asah.spark.session.function;

import com.liferay.osb.asah.spark.session.model.Event;

import java.sql.Date;

import org.apache.spark.api.java.function.MapFunction;

import scala.Tuple2;

/**
 * @author Robson Pastor
 */
public class EventGroupByKeyFunction
	implements MapFunction<Event, Tuple2<String, Date>> {

	@Override
	public Tuple2<String, Date> call(Event event) {
		return new Tuple2<>(event.getUserId(), event.getDate());
	}

}