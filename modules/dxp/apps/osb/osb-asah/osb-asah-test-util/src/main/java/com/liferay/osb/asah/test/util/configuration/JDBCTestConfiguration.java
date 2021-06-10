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

package com.liferay.osb.asah.test.util.configuration;

import com.liferay.osb.asah.common.configuration.JDBCConfiguration;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

/**
 * @author Marcellus Tavares
 */
@EnableJdbcRepositories(
	basePackages = "com.liferay.osb.asah.common.repository",
	namedQueriesLocation = "classpath*:com/liferay/osb/asah/common/repository/*-sql.xml"
)
@TestConfiguration
public class JDBCTestConfiguration extends JDBCConfiguration {
}