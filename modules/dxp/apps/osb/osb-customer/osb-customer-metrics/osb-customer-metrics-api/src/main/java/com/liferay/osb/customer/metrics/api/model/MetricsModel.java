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

package com.liferay.osb.customer.metrics.api.model;

import com.liferay.portal.kernel.model.BaseModel;

import java.util.List;
import java.util.Map;

/**
 * @author Jenny Chen
 */
public interface MetricsModel<T> {

	public void deleteAll() throws Exception;

	public Map<String, String> getMappingTables() throws Exception;

	public Map<String, List<String>> getMappingValues(BaseModel<T> model);

	public Class<T> getModelClass();

	public boolean hasMapping();

	public void resyncAll() throws Exception;

	public Map<String, Object> transformAttributes(BaseModel<T> model);

}