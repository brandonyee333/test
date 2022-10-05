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
import com.liferay.osb.asah.common.model.Distribution;
import com.liferay.osb.asah.common.model.Individual;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.postgresql.converter.helper.IndividualsFilterStringConverterHelper;
import com.liferay.osb.asah.common.repository.BQIndividualRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.ArrayUtils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author Robson Pastor
 * @author Ivica Cardic
 */
@Component
public class BQIndividualDog {

	public BQIndividualDog(
		BQIndividualRepository bqIndividualRepository,
		IndividualsFilterStringConverterHelper
			individualsFilterStringConverterHelper) {

		_bqIndividualRepository = bqIndividualRepository;
		_individualsFilterStringConverterHelper =
			individualsFilterStringConverterHelper;
	}

	public long countBQIndividuals(
		Long accountId, Long channelId, Long dataSourceId, Long notSegmentId,
		String query, Long segmentId) {

		return _bqIndividualRepository.countBQIndividuals(
			accountId, channelId, dataSourceId, notSegmentId, query, segmentId);
	}

	public Individual fetchBQIndividual(@Nullable Long channelId, String id) {
		Optional<Individual> bqIndividualOptional =
			_bqIndividualRepository.findByChannelIdAndId(channelId, id);

		return bqIndividualOptional.orElse(null);
	}

	public Page<Distribution> getDistributionPage(
		@Nullable Long channelId, String fieldName,
		@Nullable Long individualSegmentId, int size, String[] sorts) {

		PageRequest pageRequest = PageRequest.of(
			0, size,
			SortUtil.getSort(Sort.by(Sort.Order.desc("count")), sorts));

		List<Distribution> distributions =
			_bqIndividualRepository.getIndividualDistributions(
				channelId, fieldName, individualSegmentId, pageRequest);

		return PageableExecutionUtils.getPage(
			distributions, pageRequest, distributions::size);
	}

	public Page<Individual> searchBQIndividualPage(
		Long accountId, Long channelId, Long dataSourceId, Long notSegmentId,
		int page, String query, Long segmentId, int size, String[] sorts) {

		return PageableExecutionUtils.getPage(
			searchBQIndividuals(
				accountId, channelId, dataSourceId, notSegmentId, page, query,
				segmentId, size, sorts),
			PageRequest.of(page, size, _getSort(sorts)),
			() -> countBQIndividuals(
				accountId, channelId, dataSourceId, notSegmentId, query,
				segmentId));
	}

	public List<Individual> searchBQIndividuals(
		Long accountId, Long channelId, Long dataSourceId, Long notSegmentId,
		int page, String query, Long segmentId, int size, String[] sorts) {

		return _bqIndividualRepository.searchBQIndividuals(
			accountId, channelId, dataSourceId, notSegmentId,
			PageRequest.of(page, size, _getSort(sorts)), query, segmentId);
	}

	private org.springframework.data.domain.Sort _getSort(String[] sorts) {
		if (ArrayUtils.isEmpty(sorts)) {
			return org.springframework.data.domain.Sort.by(
				org.springframework.data.domain.Sort.Order.asc("id"));
		}

		List<Sort.Order> orders = new ArrayList<>();

		for (int i = 0; i < sorts.length; i++) {
			String sort = sorts[i];

			String order = null;

			String[] properties = sort.split(",");

			if (properties.length == 1) {
				order = sorts[++i];
			}
			else {
				order = properties[1];
			}

			if (Objects.equals(order, "asc")) {
				orders.add(Sort.Order.asc(properties[0]));
			}
			else {
				orders.add(Sort.Order.desc(properties[0]));
			}
		}

		return Sort.by(orders);
	}

	private final BQIndividualRepository _bqIndividualRepository;
	private final IndividualsFilterStringConverterHelper
		_individualsFilterStringConverterHelper;

}