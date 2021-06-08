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

import org.apache.spark.sql.SparkSession

/**
 * @author Inácio Nery
 */
object OSBAsahSparkScalaApplication {

	def main(args: Array[String]) {
		val sparkSessionBuilder = SparkSession.builder()

		sparkSessionBuilder.appName("OSBAsahSparkScalaApplication")
		sparkSessionBuilder.master("local")

		val sparkSession = sparkSessionBuilder.getOrCreate()

		sparkSession.stop()
	}

}