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

import com.liferay.osb.asah.common.entity.BQTeam;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.repository.BQTeamRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author Marcos Martins
 */
@Component
public class BQTeamDog extends BaseBQDXPEntityDog {

	public Page<BQTeam> getBQTeamPage(
		@Nullable Long channelId, @Nullable String keywords, int size,
		Sort sort, int start) {

		List<Long> dataSourceIds = getDataSourceIds(channelId);

		PageRequest pageRequest = PageRequest.of(start / size, size, sort);

		return PageableExecutionUtils.getPage(
			_bqTeamRepository.searchByDataSourceIdsAndKeywords(
				dataSourceIds, keywords, pageRequest),
			pageRequest,
			() -> _bqTeamRepository.countByDataSourceIdsAndKeywords(
				dataSourceIds, keywords));
	}

	@Autowired
	private BQTeamRepository _bqTeamRepository;

}