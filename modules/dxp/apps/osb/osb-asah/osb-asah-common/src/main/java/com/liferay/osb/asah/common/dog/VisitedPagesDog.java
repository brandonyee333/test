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

import com.liferay.osb.asah.common.dog.util.SortUtil;
import com.liferay.osb.asah.common.model.Transformation;
import com.liferay.osb.asah.common.repository.BQIdentityInterestPageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;

/**
 * @author Leslie Wong
 */
@Component
public class VisitedPagesDog {

	public Page<Transformation> getVisitedPagesTransformations(
		String filterString, String ownerId, String ownerType, int page,
		int size, String[] sorts, boolean visitedPages) {

		PageRequest pageRequest = PageRequest.of(
			page, size, SortUtil.getSort(sorts));

		if (visitedPages) {
			return PageableExecutionUtils.getPage(
				_bqIdentityInterestPageRepository.getActivePagesTransformations(
					filterString, ownerId, ownerType, pageRequest),
				pageRequest,
				() ->
					_bqIdentityInterestPageRepository.
						countActivePagesTransformations(
							filterString, ownerId, ownerType));
		}

		return PageableExecutionUtils.getPage(
			_bqIdentityInterestPageRepository.getInactivePagesTransformations(
				filterString, ownerId, ownerType, pageRequest),
			pageRequest,
			() ->
				_bqIdentityInterestPageRepository.
					countInactivePagesTransformations(
						filterString, ownerId, ownerType));
	}

	@Autowired
	private BQIdentityInterestPageRepository _bqIdentityInterestPageRepository;

}