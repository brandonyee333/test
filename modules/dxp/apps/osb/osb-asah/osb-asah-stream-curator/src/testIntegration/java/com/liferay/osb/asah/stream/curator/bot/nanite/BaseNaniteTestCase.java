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

package com.liferay.osb.asah.stream.curator.bot.nanite;

import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;

/**
 * @author Inácio Nery
 */
public abstract class BaseNaniteTestCase {

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

}