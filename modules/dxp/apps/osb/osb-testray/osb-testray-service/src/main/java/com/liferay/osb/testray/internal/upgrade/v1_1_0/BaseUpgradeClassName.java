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

package com.liferay.osb.testray.internal.upgrade.v1_1_0;

import com.liferay.osb.testray.model.TestrayArchive;
import com.liferay.osb.testray.model.TestrayAssignment;
import com.liferay.osb.testray.model.TestrayBuild;
import com.liferay.osb.testray.model.TestrayBuildMetric;
import com.liferay.osb.testray.model.TestrayCase;
import com.liferay.osb.testray.model.TestrayCaseResult;
import com.liferay.osb.testray.model.TestrayCaseResultWarning;
import com.liferay.osb.testray.model.TestrayCaseType;
import com.liferay.osb.testray.model.TestrayComponent;
import com.liferay.osb.testray.model.TestrayFactor;
import com.liferay.osb.testray.model.TestrayFactorCategory;
import com.liferay.osb.testray.model.TestrayFactorOption;
import com.liferay.osb.testray.model.TestrayIssue;
import com.liferay.osb.testray.model.TestrayProductVersion;
import com.liferay.osb.testray.model.TestrayProject;
import com.liferay.osb.testray.model.TestrayRoutine;
import com.liferay.osb.testray.model.TestrayRun;
import com.liferay.osb.testray.model.TestraySubtask;
import com.liferay.osb.testray.model.TestraySuite;
import com.liferay.osb.testray.model.TestrayTask;
import com.liferay.osb.testray.model.TestrayTeam;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Ethan Bustad
 */
public abstract class BaseUpgradeClassName extends UpgradeProcess {

	protected static String getStaleClassName(String className) {
		return StringUtil.replace(className, ".osb.", StringPool.PERIOD);
	}

	protected static final String[] CLASS_NAMES = {
		TestrayArchive.class.getName(), TestrayAssignment.class.getName(),
		TestrayBuild.class.getName(), TestrayBuildMetric.class.getName(),
		TestrayCase.class.getName(), TestrayCaseResult.class.getName(),
		TestrayCaseResultWarning.class.getName(),
		TestrayCaseType.class.getName(), TestrayComponent.class.getName(),
		TestrayFactor.class.getName(), TestrayFactorCategory.class.getName(),
		TestrayFactorOption.class.getName(), TestrayIssue.class.getName(),
		TestrayProductVersion.class.getName(), TestrayProject.class.getName(),
		TestrayRoutine.class.getName(), TestrayRun.class.getName(),
		TestraySubtask.class.getName(), TestraySuite.class.getName(),
		TestrayTask.class.getName(), TestrayTeam.class.getName()
	};

}