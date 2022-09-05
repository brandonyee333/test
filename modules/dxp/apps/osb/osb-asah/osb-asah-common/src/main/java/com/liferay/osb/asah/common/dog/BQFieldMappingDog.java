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
import com.liferay.osb.asah.common.entity.BQFieldMapping;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.repository.BQFieldMappingRepository;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author Robson Pastor
 */
@Component
public class BQFieldMappingDog {

	public Page<BQFieldMapping> searchBQFieldMappingPage(
		@Nullable String filterString, int page, int size,
		@Nullable String[] sorts) {

		FilterHelper filterHelper = new FilterHelper(filterString);

		PageRequest pageRequest = PageRequest.of(
			page, size,
			SortUtil.getSort(Sort.by(Sort.Order.asc("fieldName")), sorts));

		return PageableExecutionUtils.getPage(
			_bqFieldMappingRepository.searchBQFieldMappings(
				filterHelper, pageRequest),
			pageRequest,
			() -> _bqFieldMappingRepository.countBQFieldMappings(filterHelper));
	}

	@Autowired
	private BQFieldMappingRepository _bqFieldMappingRepository;

}