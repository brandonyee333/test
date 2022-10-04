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
import com.liferay.osb.asah.common.repository.helper.FilterHelper;

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

	public long countBQIndividuals(Long channelId, String filterString) {
		return _bqIndividualRepository.countBQIndividuals(
			channelId,
			new FilterHelper(
				null, filterString, _individualsFilterStringConverterHelper),
			null, null);
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
		Long channelId, String filterString, int page, int size,
		String[] sorts) {

		return PageableExecutionUtils.getPage(
			searchBQIndividuals(channelId, filterString, page, size, sorts),
			PageRequest.of(page, size, _getSort(sorts)),
			() -> countBQIndividuals(channelId, filterString));
	}

	public List<Individual> searchBQIndividuals(
		Long channelId, String filterString, int page, int size,
		String[] sorts) {

		return _bqIndividualRepository.searchBQIndividuals(
			channelId,
			new FilterHelper(
				null, filterString, _individualsFilterStringConverterHelper),
			null, null, PageRequest.of(page, size, _getSort(sorts)));
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