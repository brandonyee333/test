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

package com.liferay.osb.asah.common.postgresql.impl.test;

import com.liferay.osb.asah.common.OSBAsahCommonSpringTestContext;
import com.liferay.osb.asah.common.entity.Project;
import com.liferay.osb.asah.common.postgresql.PostgreSQLSchemaManager;
import com.liferay.osb.asah.test.util.configuration.JDBCTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

/**
 * @author Rachael Koestartyo
 */
@Import(JDBCTestConfiguration.class)
public class PostgreSQLSchemaManagerImplTest
	implements OSBAsahCommonSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@Test
	public void test() {
		Project project = new Project("test");

		Assertions.assertTrue(
			_postgreSQLSchemaManager.existsTable(project, "EventAnalysis"));
		Assertions.assertTrue(
			_postgreSQLSchemaManager.existsTable(project, "EventDefinition"));
		Assertions.assertTrue(
			_postgreSQLSchemaManager.existsTable(project, "Project"));
	}

	@Autowired
	private PostgreSQLSchemaManager _postgreSQLSchemaManager;

}