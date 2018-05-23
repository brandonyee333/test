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