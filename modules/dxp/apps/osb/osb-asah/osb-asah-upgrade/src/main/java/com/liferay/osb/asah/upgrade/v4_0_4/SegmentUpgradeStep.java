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

package com.liferay.osb.asah.upgrade.v4_0_4;

import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.stereotype.Component;

/**
 * @author Rachael Koestartyo
 */
@Component
public class SegmentUpgradeStep implements UpgradeStep {

	public SegmentUpgradeStep(SegmentRepository segmentRepository) {
		_segmentRepository = segmentRepository;
	}

	@Override
	public void upgrade(String version) {
		for (Segment segment : _segmentRepository.findAll()) {
			String filterString = segment.getFilter();

			if (StringUtils.isBlank(filterString)) {
				continue;
			}

			if (StringUtils.containsAnyIgnoreCase(
					filterString, "dataSourceAccountPKs/accountPKs",
					"demographics/address", "demographics/city",
					"demographics/country", "demographics/department",
					"demographics/division", "demographics/fullName",
					"demographics/gender", "demographics/portraitId",
					"demographics/region", "demographics/role",
					"demographics/state")) {

				segment.setState("DISABLED");

				_segmentRepository.save(segment);

				if (_log.isInfoEnabled()) {
					_log.info(
						String.format(
							"Disabled segment ID %s with filter %s",
							segment.getId(), filterString));
				}
			}
		}
	}

	private static final Log _log = LogFactory.getLog(SegmentUpgradeStep.class);

	private final SegmentRepository _segmentRepository;

}