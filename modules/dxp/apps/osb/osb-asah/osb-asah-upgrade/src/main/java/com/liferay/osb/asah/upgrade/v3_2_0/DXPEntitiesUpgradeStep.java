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

package com.liferay.osb.asah.upgrade.v3_2_0;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.repository.DXPEntityRepository;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

/**
 * @author Marcos Martins
 */
@Component
public class DXPEntitiesUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		Date newDate = DateUtil.newDate();

		_upgrade(newDate, DXPEntity.Type.GROUP);
		_upgrade(newDate, DXPEntity.Type.ORGANIZATION);
		_upgrade(newDate, DXPEntity.Type.ROLE);
		_upgrade(newDate, DXPEntity.Type.TEAM);
		_upgrade(newDate, DXPEntity.Type.USER);
		_upgrade(newDate, DXPEntity.Type.USER_GROUP);
	}

	private void _upgrade(Date date, DXPEntity.Type type) {
		int page = 0;

		while (true) {
			List<DXPEntity> dxpEntities =
				_dxpEntityRepository.searchByDataSourceIdsAndKeywordsAndType(
					Collections.emptyList(), null, type,
					PageRequest.of(page++, _DXP_ENTITY_PAGE_SIZE));

			if (dxpEntities.isEmpty()) {
				return;
			}

			for (DXPEntity dxpEntity : dxpEntities) {
				dxpEntity.setModifiedDate(date);
			}

			_dxpEntityRepository.saveAll(dxpEntities);
		}
	}

	private static final int _DXP_ENTITY_PAGE_SIZE = 1000;

	@Autowired
	private DXPEntityRepository _dxpEntityRepository;

}