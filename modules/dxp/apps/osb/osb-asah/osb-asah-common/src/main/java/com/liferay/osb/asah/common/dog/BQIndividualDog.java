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
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.postgresql.converter.helper.IndividualsFilterStringConverterHelper;
import com.liferay.osb.asah.common.repository.BQIndividualRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;

/**
 * @author Robson Pastor
 */
@Component
public class BQIndividualDog {

	public Page<Distribution> getDistributionPage(
		Long channelId, String fieldName, Long individualSegmentId, int size,
		String[] sorts) {

		PageRequest pageRequest = PageRequest.of(
			0, size,
			SortUtil.getSort(Sort.by(Sort.Order.desc("count")), sorts));

		List<Distribution> distributions =
			_bqIndividualRepository.getIndividualDistributions(
				channelId, fieldName, individualSegmentId, pageRequest);

		return PageableExecutionUtils.getPage(
			distributions, pageRequest, distributions::size);
	}

	@Autowired
	private BQIndividualRepository _bqIndividualRepository;

	@Autowired
	private IndividualsFilterStringConverterHelper
		_individualsFilterStringConverterHelper;

}