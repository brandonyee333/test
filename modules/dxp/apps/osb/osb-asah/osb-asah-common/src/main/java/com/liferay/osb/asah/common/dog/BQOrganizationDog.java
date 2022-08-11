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

import com.liferay.osb.asah.common.entity.BQOrganization;
import com.liferay.osb.asah.common.postgresql.converter.helper.OrganizationsFilterStringConverterHelper;
import com.liferay.osb.asah.common.repository.BQOrganizationRepository;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author Matthew Kong
 */
@Component
public class BQOrganizationDog {

	public List<BQOrganization> findByDataSourceIdAndOrganizationIdIn(
		Long dataSourceId, Collection<Long> organizationIds) {

		return _bqOrganizationRepository.findByDataSourceIdAndOrganizationIdIn(
			dataSourceId, organizationIds);
	}

	public BQOrganization getBQOrganization(String bqOrganizationId) {
		Optional<BQOrganization> bqOrganizationOptional =
			_bqOrganizationRepository.findById(bqOrganizationId);

		return bqOrganizationOptional.orElseThrow(
			() -> new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				"There is no organization with ID " + bqOrganizationId));
	}

	public Page<BQOrganization> getBQOrganizationPage(
		@Nullable String name, int size,
		com.liferay.osb.asah.common.model.Sort sort, int start) {

		PageRequest pageRequest = PageRequest.of(start / size, size, sort);

		return PageableExecutionUtils.getPage(
			_bqOrganizationRepository.findByName(name, pageRequest),
			pageRequest, () -> _bqOrganizationRepository.countByName(name));
	}

	public List<BQOrganization> searchBQOrganizations(
		String filterString, int page, int size) {

		PageRequest pageRequest = PageRequest.of(page, size);

		return _bqOrganizationRepository.searchBQOrganizations(
			new FilterHelper(
				null, filterString, _organizationsFilterStringConverterHelper),
			pageRequest);
	}

	@Autowired
	private BQOrganizationRepository _bqOrganizationRepository;

	@Autowired
	private OrganizationsFilterStringConverterHelper
		_organizationsFilterStringConverterHelper;

}