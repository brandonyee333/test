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

import com.liferay.osb.asah.common.entity.BQUser;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.repository.BQUserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author Marcos Martins
 */
@Component
public class BQUserDog extends BaseBQDXPEntityDog {

	public List<BQUser> findByFields(
		Map<String, Object> fields, Pageable pageable) {

		List<BQUser> bqUsers = _bqUserRepository.findByFields(fields, pageable);

		for (BQUser bqUser : bqUsers) {
			_populateExpandoFields(bqUser);
		}

		return bqUsers;
	}

	public Page<BQUser> getBQUserPage(
		@Nullable Long channelId, @Nullable String keywords, int size,
		Sort sort, int start) {

		List<Long> dataSourceIds = getDataSourceIds(channelId);

		PageRequest pageRequest = PageRequest.of(start / size, size, sort);

		List<BQUser> bqUsers =
			_bqUserRepository.searchByDataSourceIdsAndKeywords(
				dataSourceIds, keywords, pageRequest);

		for (BQUser bqUser : bqUsers) {
			_populateExpandoFields(bqUser);
		}

		return PageableExecutionUtils.getPage(
			bqUsers, pageRequest,
			() -> _bqUserRepository.countByDataSourceIdsAndKeywords(
				dataSourceIds, keywords));
	}

	private BQUser _populateExpandoFields(BQUser bqUser) {
		bqUser.setExpandoFields(
			getExpandoFields(
				Arrays.asList(bqUser.getExpandoColumnIds()),
				Arrays.asList(bqUser.getExpandoValueIds())));

		return bqUser;
	}

	@Autowired
	private BQUserRepository _bqUserRepository;

}