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

package com.liferay.osb.asah.spark.scala.common

import scala.io.Source

/**
 * @author Marcellus Tavares
 */
class Configuration(val path: String) {

    def get(propertyKey:String, default: String): String = {
        val propertyValueOption:Option[String] = _configurationMap.get(
            propertyKey)

        propertyValueOption.map(
            propertyValue => propertyValue
        ).getOrElse(
            default
        )
    }

    private val _configurationMap:Map[String, String] = _createConfigurationMap(
        path)

    private def _createConfigurationMap(path: String): Map[String, String] = {
        val classLoader = getClass.getClassLoader

        val inputStream = classLoader.getResourceAsStream(path)

        val bufferedSource = Source.fromInputStream(inputStream)

        val lines = bufferedSource.getLines()

        lines.map(
            line => {
                val tokens = line.split("=")

                if (tokens.length == 1) {
                    tokens(0) -> ""
                }
                else {
                    tokens(0) -> tokens(1)
                }

            }
        ).toMap
    }

}