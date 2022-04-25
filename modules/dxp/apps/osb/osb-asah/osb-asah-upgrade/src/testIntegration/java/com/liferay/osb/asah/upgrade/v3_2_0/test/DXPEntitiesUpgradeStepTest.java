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

package com.liferay.osb.asah.upgrade.v3_2_0.test;

import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.repository.DXPEntityRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.upgrade.OSBAsahUpgradeSpringTestContext;
import com.liferay.osb.asah.upgrade.v3_2_0.DXPEntitiesUpgradeStep;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

/**
 * @author Marcos Martins
 */
public class DXPEntitiesUpgradeStepTest
	implements OSBAsahUpgradeSpringTestContext {

	@BeforeEach
	public void setUp() {
		ProjectIdThreadLocal.setProjectId("test");

		DataSource dataSource = new DataSource("Liferay Brazil");

		dataSource.setId(123L);
		dataSource.setIsNew(true);
		dataSource.setProviderType("LIFERAY");

		_dataSourceRepository.save(dataSource);

		DXPEntity dxpEntity = new DXPEntity();

		dxpEntity.setDataSourceId(123L);
		dxpEntity.setType(DXPEntity.Type.USER);

		_dxpEntity = _dxpEntityRepository.save(dxpEntity);
	}

	@AfterEach
	public void tearDown() {
		_dxpEntityRepository.delete(_dxpEntity);
	}

	@Test
	public void testUpgrade() throws Exception {
		List<DXPEntity> dxpEntities =
			_dxpEntityRepository.searchByDataSourceIdsAndKeywordsAndType(
				Collections.emptyList(), null, DXPEntity.Type.USER,
				PageRequest.of(0, 10));

		Assertions.assertEquals(1, dxpEntities.size(), dxpEntities.toString());

		DXPEntity dxpEntity = dxpEntities.get(0);

		Assertions.assertNull(dxpEntity.getModifiedDate());

		_dxpEntitiesUpgradeStep.upgrade(null);

		dxpEntities =
			_dxpEntityRepository.searchByDataSourceIdsAndKeywordsAndType(
				Collections.emptyList(), null, DXPEntity.Type.USER,
				PageRequest.of(0, 10));

		Assertions.assertEquals(1, dxpEntities.size(), dxpEntities.toString());

		dxpEntity = dxpEntities.get(0);

		Assertions.assertNotNull(dxpEntity.getModifiedDate());
	}

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	@Autowired
	private DXPEntitiesUpgradeStep _dxpEntitiesUpgradeStep;

	private DXPEntity _dxpEntity;

	@Autowired
	private DXPEntityRepository _dxpEntityRepository;

}