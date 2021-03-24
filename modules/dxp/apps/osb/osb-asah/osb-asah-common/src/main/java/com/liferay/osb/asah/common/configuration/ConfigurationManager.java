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

package com.liferay.osb.asah.common.configuration;

import com.liferay.osb.asah.common.model.DataSource;

/**
 * @author Vishal Reddy
 * @author Brian Wing Shun Chan
 */
public interface ConfigurationManager {

	public boolean addConfiguration(DataSource dataSource);

	public boolean deleteConfiguration(String dataSourceId);

	public Configuration getConfiguration(String dataSourceId);

	public Configuration[] getConfigurations(String projectId);

	public String getState(DataSource dataSource);

	public DataSource refresh(DataSource dataSource);

	public Configuration updateConfiguration(DataSource dataSource);

}