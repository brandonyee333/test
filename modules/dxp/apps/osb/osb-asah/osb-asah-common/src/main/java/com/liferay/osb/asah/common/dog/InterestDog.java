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

import com.liferay.osb.asah.common.entity.Interest;
import com.liferay.osb.asah.common.repository.InterestRepository;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.IterableUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class InterestDog {

	public List<Interest> addInterests(List<Interest> interests) {
		return IterableUtils.toList(_interestRepository.saveAll(interests));
	}

	public void deleteInterests(Long ownerId, String ownerType) {
		_interestRepository.deleteByOwnerIdAndOwnerType(ownerId, ownerType);
	}

	public void deleteInterests(String ownerType, Date recordedDate) {
		_interestRepository.deleteByOwnerTypeAndRecordedDateLessThanEqual(
			ownerType, recordedDate);
	}

	public Interest fetchInterest(
		String name, Long ownerId, String ownerType, Date recordedDate) {

		return _interestRepository.
			getByNameAndOwnerIdAndOwnerTypeAndRecordedDate(
				name, ownerId, ownerType, recordedDate);
	}

	public Page<Interest> getInterestPage(
		Long ownerId, String ownerType, int size, int start) {

		return PageableExecutionUtils.getPage(
			_interestRepository.findByOwnerIdAndOwnerType(
				ownerId, ownerType, PageRequest.of(start / size, size)),
			PageRequest.of(start / size, size),
			() -> _interestRepository.countByOwnerIdAndOwnerType(
				ownerId, ownerType));
	}

	public List<Interest> getInterests(
		Long interestId, String ownerType, Date recordedDate, int size) {

		return _interestRepository.findByOwnerTypeAndRecordedDate(
			interestId, ownerType, recordedDate, size);
	}

	@Autowired
	private InterestRepository _interestRepository;

}