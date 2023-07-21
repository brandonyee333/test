/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.messaging;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ivica Cardic
 */
public class CacheMetricsMessage extends MetricsMessage {

	public Map<String, Object> getHibernateMetrics() {
		return _hibernateMetrics;
	}

	public Map<String, Map<String, Object>> getLiferayMultiVMMetrics() {
		return _liferayMultiVMMetrics;
	}

	public Map<String, Map<String, Object>> getLiferaySingleVMMetrics() {
		return _liferaySingleVMMetrics;
	}

	public void setHibernateMetrics(Map<String, Object> hibernateMetrics) {
		_hibernateMetrics = hibernateMetrics;
	}

	public void setLiferayMultiVMMetrics(
		Map<String, Map<String, Object>> liferayMultiVMMetrics) {

		_liferayMultiVMMetrics = liferayMultiVMMetrics;
	}

	public void setLiferaySingleVMMetrics(
		Map<String, Map<String, Object>> liferaySingleVMMetrics) {

		_liferaySingleVMMetrics = liferaySingleVMMetrics;
	}

	private Map<String, Object> _hibernateMetrics =
		new HashMap<String, Object>();
	private Map<String, Map<String, Object>> _liferayMultiVMMetrics =
		new HashMap<String, Map<String, Object>>();
	private Map<String, Map<String, Object>> _liferaySingleVMMetrics =
		new HashMap<String, Map<String, Object>>();

}