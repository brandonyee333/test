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
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.model.Distribution;
import com.liferay.osb.asah.common.model.Field;
import com.liferay.osb.asah.common.model.Individual;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.postgresql.converter.helper.IndividualsFilterStringConverterHelper;
import com.liferay.osb.asah.common.repository.BQIdentityRepository;
import com.liferay.osb.asah.common.repository.BQIndividualRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

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
		BQIdentityRepository bqIdentityRepository,
		BQIndividualRepository bqIndividualRepository,
		DataSourceRepository dataSourceRepository,
		IndividualsFilterStringConverterHelper
			individualsFilterStringConverterHelper) {

		_bqIdentityRepository = bqIdentityRepository;
		_bqIndividualRepository = bqIndividualRepository;
		_dataSourceRepository = dataSourceRepository;
		_individualsFilterStringConverterHelper =
			individualsFilterStringConverterHelper;
	}

	public long countBQIndividuals(
		@Nullable Long accountId, @Nullable Long channelId,
		@Nullable Long dataSourceId, @Nullable Long notSegmentId,
		@Nullable String query, @Nullable Long segmentId) {

		return _bqIndividualRepository.countBQIndividuals(
			accountId, channelId, dataSourceId, notSegmentId, query, segmentId);
	}

	public long countIndividuals(boolean includeAnonymousUsers) {
		return _bqIdentityRepository.countIndividuals(includeAnonymousUsers);
	}

	public Individual fetchBQIndividual(@Nullable Long channelId, String id) {
		Optional<Individual> bqIndividualOptional =
			_bqIndividualRepository.findByChannelIdAndId(channelId, id);

		if (!bqIndividualOptional.isPresent()) {
			return null;
		}

		Individual individual = bqIndividualOptional.get();

		_setDataSourceName(individual.getCustomFields());
		_setDataSourceName(individual.getFields());

		return individual;
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
		@Nullable Long accountId, @Nullable Long channelId,
		@Nullable Long dataSourceId, @Nullable Long notSegmentId, int page,
		@Nullable String query, @Nullable Long segmentId, int size,
		String[] sorts) {

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
		@Nullable Long accountId, @Nullable Long channelId,
		@Nullable Long dataSourceId, @Nullable Long notSegmentId, int page,
		@Nullable String query, @Nullable Long segmentId, int size,
		String[] sorts) {

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

	private void _setDataSourceName(Set<Field> fields) {
		for (Field field : fields) {
			Optional<DataSource> dataSourceOptional =
				_dataSourceRepository.findById(field.getDataSourceId());

			if (dataSourceOptional.isPresent()) {
				DataSource dataSource = dataSourceOptional.get();

				field.setDataSourceName(dataSource.getName());
			}
		}
	}

	private final BQIdentityRepository _bqIdentityRepository;
	private final BQIndividualRepository _bqIndividualRepository;
	private final DataSourceRepository _dataSourceRepository;
	private final IndividualsFilterStringConverterHelper
		_individualsFilterStringConverterHelper;

}