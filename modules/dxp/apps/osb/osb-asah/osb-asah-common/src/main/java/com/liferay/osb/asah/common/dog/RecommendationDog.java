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

package com.liferay.osb.asah.common.dog;

import com.liferay.osb.asah.common.model.ItemRecommendation;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.repository.ItemRecommendationRepository;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class RecommendationDog {

	public void deleteItemRecommendationsByJobId(Long jobId) {
		_itemRecommendationRepository.deleteByJobId(jobId);
	}

	public ItemRecommendation getItemRecommendation(String id) {
		Optional<ItemRecommendation> itemRecommendationOptional =
			_itemRecommendationRepository.findById(id);

		return itemRecommendationOptional.orElseThrow(
			() -> new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				"There is no item recommendation with ID " + id));
	}

	public Page<ItemRecommendation> getItemRecommendationPage(
		Long jobId, int page, int size, Sort sort) {

		PageRequest pageRequest = PageRequest.of(page, size, sort);

		return PageableExecutionUtils.getPage(
			_itemRecommendationRepository.findByJobId(jobId, pageRequest),
			pageRequest,
			() -> _itemRecommendationRepository.countByJobId(jobId));
	}

	@Autowired
	private ItemRecommendationRepository _itemRecommendationRepository;

}