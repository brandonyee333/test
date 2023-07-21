/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.metrics.model;

import java.util.List;
import java.util.Map;

/**
 * @author Jenny Chen
 */
public interface MetricsModel<T> {

	public boolean allowDeleteAll();

	public void deleteAll() throws Exception;

	public Map<String, Object> getAttributes(T model);

	public String[] getMappingTables();

	public List<Map<String, Object>> getMappingValues(T model);

	public Class<T> getModelClass();

	public String getModelName();

	public String getModelPrimaryKeyName();

	public boolean hasMapping();

	public void resyncAll() throws Exception;

}