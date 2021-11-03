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

package com.liferay.osb.asah.stream.curator.bot.nanite.test;

import com.liferay.osb.asah.common.entity.Project;
import com.liferay.osb.asah.common.multitenancy.ProjectDog;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.stream.curator.bot.nanite.Nanite;

import org.junit.Before;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Inácio Nery
 */
public abstract class BaseNaniteTestCase {

	@Before
	public void setUp() {
		_projectDog.addProject(new Project("test"));

		ProjectIdThreadLocal.setProjectId("test");
	}

	protected abstract Nanite getNanite();

	protected void runNanite() {
		try {
			Nanite nanite = getNanite();

			nanite.run();
		}
		finally {
			ProjectIdThreadLocal.setProjectId("test");
		}
	}

	@Autowired
	private ProjectDog _projectDog;

}