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

import com.liferay.osb.asah.common.model.CompositionResultBag;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.repository.BQSessionInterestScoreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

/**
 * @author Rachael Koestartyo
 */
@Component
public class SiteInterestCompositionDog {

	public CompositionResultBag getCompositionResultBag(
		Long channelId, int size, int start, TimeRange timeRange) {

		return _bqSessionInterestScoreRepository.
			getInterestCompositionResultBag(
				channelId, PageRequest.of(start / size, size), timeRange);
	}

	@Autowired
	private BQSessionInterestScoreRepository _bqSessionInterestScoreRepository;

}