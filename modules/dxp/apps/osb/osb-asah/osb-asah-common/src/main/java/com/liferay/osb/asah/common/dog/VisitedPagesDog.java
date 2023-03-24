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
import com.liferay.osb.asah.common.repository.BQIdentityInterestPageRepository;

import org.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

/**
 * @author Leslie Wong
 */
@Component
public class VisitedPagesDog {

	public long countActivePages(
		Long channelId, String filterString, String ownerId, String ownerType) {

		return _bqIdentityInterestPageRepository.
			countActivePagesTransformations(
				channelId, filterString, ownerId, ownerType);
	}

	public long countInactivePages(
		Long channelId, String filterString, String ownerId, String ownerType) {

		return _bqIdentityInterestPageRepository.
			countInactivePagesTransformations(
				channelId, filterString, ownerId, ownerType);
	}

	public JSONArray getVisitedPagesTransformations(
		Long channelId, String filterString, String ownerId, String ownerType,
		int page, int size, String[] sorts, boolean visitedPages) {

		PageRequest pageRequest = PageRequest.of(
			page, size, SortUtil.getSort(sorts));

		if (visitedPages) {
			return new JSONArray(
				_bqIdentityInterestPageRepository.getActivePagesTransformations(
					channelId, filterString, ownerId, ownerType, pageRequest));
		}

		return new JSONArray(
			_bqIdentityInterestPageRepository.getInactivePagesTransformations(
				channelId, filterString, ownerId, ownerType, pageRequest));
	}

	@Autowired
	private BQIdentityInterestPageRepository _bqIdentityInterestPageRepository;

}