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

package com.liferay.osb.asah.common.postgresql;

import com.liferay.osb.asah.common.entity.Project;

/**
 * @author Rachael Koestartyo
 */
public interface PostgreSQLSchemaManager {

	public void createGlobalSchema();

	public void createSchema(Project project);

	public boolean existsTable(Project project, String tableName);

}