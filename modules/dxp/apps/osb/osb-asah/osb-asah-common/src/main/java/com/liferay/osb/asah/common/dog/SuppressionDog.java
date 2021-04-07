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

import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.model.Suppression;
import com.liferay.osb.asah.common.repository.SuppressionRepository;

import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;

/**
 * @author Matthew Kong
 */
@Component
public class SuppressionDog {

	public Page<Suppression> getSuppressionPage(
		String emailAddress, int page, int size, Sort sort) {

		PageRequest pageRequest = PageRequest.of(page, size, sort);

		if (StringUtils.isBlank(emailAddress)) {
			return PageableExecutionUtils.getPage(
				_suppressionRepository.findAll(pageRequest), pageRequest,
				_suppressionRepository::count);
		}

		return PageableExecutionUtils.getPage(
			_suppressionRepository.findByEmailAddressContainingIgnoreCase(
				emailAddress, pageRequest),
			pageRequest,
			() ->
				_suppressionRepository.countByEmailAddressContainingIgnoreCase(
					emailAddress));
	}

	@Autowired
	private SuppressionRepository _suppressionRepository;

}