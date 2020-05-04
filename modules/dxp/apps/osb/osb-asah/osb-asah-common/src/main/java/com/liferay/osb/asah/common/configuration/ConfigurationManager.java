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

/**
 * @author Vishal Reddy
 * @author Brian Wing Shun Chan
 */
public interface ConfigurationManager {

	public boolean addRuntimeConfiguration(String json);

	public boolean deleteRuntimeConfiguration(String json);

	public Configuration getConfiguration(String dataSourceId);

	public Configuration[] getConfigurations();

	public String getState(String json);

	public String refresh(String json);

	public Configuration updateRuntimeConfiguration(String json)
		throws Exception;

	public boolean validate(String json);

}