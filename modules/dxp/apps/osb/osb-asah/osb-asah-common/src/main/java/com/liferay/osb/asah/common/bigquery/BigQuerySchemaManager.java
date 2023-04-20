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

package com.liferay.osb.asah.common.bigquery;

/**
 * @author Marcellus Tavares
 */
public interface BigQuerySchemaManager {

	public void createFunction(String projectId, String functionsName);

	public void createOrReplaceView(String projectId, String viewName);

	public void createSchema(String projectId);

	public void createTable(String projectId, String tableName);

	public void createTables(String projectId);

	public void deleteSchema(String projectId);

	public void dropTable(String projectId, String tableName);

}