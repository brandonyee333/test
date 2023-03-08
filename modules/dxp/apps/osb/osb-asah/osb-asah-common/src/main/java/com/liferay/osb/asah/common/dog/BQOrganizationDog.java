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
public class BQOrganizationDog extends BaseBQDXPEntityDog {

	public List<BQOrganization> findByDataSourceIdAndOrganizationIdIn(
		Long dataSourceId, Collection<Long> organizationIds) {

		List<BQOrganization> bqOrganizations =
			_bqOrganizationRepository.findByDataSourceIdAndOrganizationIdIn(
				dataSourceId, organizationIds);

		for (BQOrganization bqOrganization : bqOrganizations) {
			_populateParentOrganizationName(bqOrganization);
		}

		return bqOrganizations;
	}

	public BQOrganization getBQOrganization(String bqOrganizationId) {
		Optional<BQOrganization> bqOrganizationOptional =
			_bqOrganizationRepository.findById(bqOrganizationId);

		BQOrganization bqOrganization = bqOrganizationOptional.orElseThrow(
			() -> new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				"There is no organization with ID " + bqOrganizationId));

		_populateParentOrganizationName(bqOrganization);

		return bqOrganization;
	}

	public Page<BQOrganization> getBQOrganizationPage(
		@Nullable Long channelId, @Nullable String name, int size, Sort sort,
		int start) {

		List<Long> dataSourceIds = getDataSourceIds(channelId);
		PageRequest pageRequest = PageRequest.of(start / size, size, sort);

		List<BQOrganization> bqOrganizations =
			_bqOrganizationRepository.searchByDataSourceIdsAndName(
				dataSourceIds, name, pageRequest);

		for (BQOrganization bqOrganization : bqOrganizations) {
			_populateParentOrganizationName(bqOrganization);
		}

		return PageableExecutionUtils.getPage(
			bqOrganizations, pageRequest,
			() -> _bqOrganizationRepository.countByDataSourceIdsAndName(
				dataSourceIds, name));
	}

	public List<BQOrganization> searchBQOrganizations(
		String filterString, int page, int size) {

		PageRequest pageRequest = PageRequest.of(page, size);

		List<BQOrganization> bqOrganizations =
			_bqOrganizationRepository.searchBQOrganizations(
				new FilterHelper(filterString), pageRequest);

		for (BQOrganization bqOrganization : bqOrganizations) {
			_populateParentOrganizationName(bqOrganization);
		}

		return bqOrganizations;
	}

	private void _populateParentOrganizationName(
		BQOrganization bqOrganization) {

		if (bqOrganization == null) {
			return;
		}

		if ((bqOrganization.getParentOrganizationId() == null) ||
			(bqOrganization.getParentOrganizationId() == 0)) {

			return;
		}

		if (bqOrganization.getParentOrganizationId() != null) {
			Optional<BQOrganization> parentBQOrganizationOptional =
				_bqOrganizationRepository.findByDataSourceIdAndOrganizationId(
					bqOrganization.getDataSourceId(),
					bqOrganization.getParentOrganizationId());

			BQOrganization parentBQOrganization =
				parentBQOrganizationOptional.orElse(null);

			if (parentBQOrganization != null) {
				bqOrganization.setParentOrganizationName(
					parentBQOrganization.getName());
			}
		}
	}

	@Autowired
	private BQOrganizationRepository _bqOrganizationRepository;

}