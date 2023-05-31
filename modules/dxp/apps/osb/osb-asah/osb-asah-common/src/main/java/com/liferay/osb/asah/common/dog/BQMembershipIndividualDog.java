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
import com.liferay.osb.asah.common.entity.BQMembershipIndividual;
import com.liferay.osb.asah.common.repository.BQMembershipIndividualRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class BQMembershipIndividualDog {

	public Page<BQMembershipIndividual> getMembershipIndividualPage(
		int page, Long segmentId, int size, @Nullable String[] sorts) {

		PageRequest pageRequest = PageRequest.of(
			page, size,
			SortUtil.getSort(Sort.by(Sort.Order.desc("modifiedDate")), sorts));

		return PageableExecutionUtils.getPage(
			_bqMembershipIndividualRepository.getMembershipIndividuals(
				pageRequest, segmentId),
			pageRequest,
			() -> _bqMembershipIndividualRepository.countMembershipIndividuals(
				segmentId));
	}

	public void updateMembershipIndividuals() {
		_bqMembershipIndividualRepository.updateMembershipIndividuals();
	}

	public void updateMembershipIndividuals(Long segmentId) {
		_bqMembershipIndividualRepository.updateMembershipIndividuals(
			segmentId);
	}

	@Autowired
	private BQMembershipIndividualRepository _bqMembershipIndividualRepository;

}