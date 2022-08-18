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

import java.util.Collections;

import org.springframework.stereotype.Component;

import com.liferay.osb.asah.backend.model.CompositionResultBag;
import com.liferay.osb.asah.common.model.TimeRange;

/**
 * @author Rachael Koestartyo
 */
@Component
public class SearchTermCompositionDog {

	public CompositionResultBag getCompositionResultBag(
		String channelId, String dataSourceId, int size, int start,
		TimeRange timeRange) {

		return new CompositionResultBag(
			Collections.emptyList(), 0,
			0);
	}

}