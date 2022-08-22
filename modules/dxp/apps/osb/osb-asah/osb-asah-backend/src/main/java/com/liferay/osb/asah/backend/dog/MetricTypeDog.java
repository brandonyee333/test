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

package com.liferay.osb.asah.backend.dog;

import com.liferay.osb.asah.backend.model.AssetType;
import com.liferay.osb.asah.common.model.MetricType;
import com.liferay.osb.asah.common.model.PageMetricType;

import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class MetricTypeDog {

	public MetricType getMetricType(AssetType assetType, String name) {
		if (assetType == AssetType.PAGE) {
			return PageMetricType.of(name);
		}

		return null;
	}

}