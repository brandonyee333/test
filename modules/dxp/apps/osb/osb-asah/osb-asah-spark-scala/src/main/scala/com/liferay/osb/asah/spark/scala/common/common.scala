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
package object common {

  implicit class StringImprovements(val string:String) {
    def toDate :Date =  {
      new Date(_simpleDateFormat.parse(string).getTime)
    }

    def toTimestamp :Timestamp =  {
      new Timestamp(_simpleTimestampFormat.parse(string).getTime)
    }
  }

  implicit class TimestampImprovements(val timestamp:Timestamp) {
    def max(value:Timestamp):Timestamp =  {
      if (timestamp.after(value))
      timestamp
      else
      value
    }

    def min(value:Timestamp):Timestamp =  {
      if (timestamp.before(value))
      timestamp
      else
      value
    }
  }


  private val _simpleDateFormat: SimpleDateFormat =
    new SimpleDateFormat("yyyy-MM-dd")

  private val _simpleTimestampFormat: SimpleDateFormat =
    new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")

}