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
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.repository.BQIndividualInterestScoreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

/**
 * @author Matthew Kong
 */
@Component
public class InterestCompositionDog {

	public CompositionResultBag getIndividualCompositionResultBag(
		Long channelId, String keywords, int size, Sort sort, int start) {

		return _bqIndividualInterestScoreRepository.
			getInterestCompositionResultBag(
				Boolean.FALSE, channelId, keywords, null,
				PageRequest.of(start / size, size, sort));
	}

	public CompositionResultBag getIndividualSegmentCompositionResultBag(
		boolean active, Long channelId, String keywords, Long segmentId,
		int size, Sort sort, int start) {

		return _bqIndividualInterestScoreRepository.
			getInterestCompositionResultBag(
				active, channelId, keywords, segmentId,
				PageRequest.of(start / size, size, sort));
	}

	@Autowired
	private BQIndividualInterestScoreRepository
		_bqIndividualInterestScoreRepository;

}