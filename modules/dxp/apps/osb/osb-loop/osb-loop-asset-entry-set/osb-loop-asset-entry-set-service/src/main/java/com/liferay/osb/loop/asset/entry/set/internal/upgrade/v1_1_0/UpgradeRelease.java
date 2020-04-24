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

package com.liferay.osb.loop.asset.entry.set.internal.upgrade.v1_1_0;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Timothy Bell
 */
public class UpgradeRelease extends BaseUpgradeClassName {

	@Override
	protected void doUpgrade() throws Exception {
		StringBundler sb = new StringBundler(3);

		sb.append("delete from Release_ where servletContextName = '");
		sb.append(_OLD_SERVLET_CONTEXT_NAME);
		sb.append(StringPool.APOSTROPHE);

		runSQL(sb.toString());
	}

	private static final String _OLD_SERVLET_CONTEXT_NAME =
		"asset-entry-set-portlet";

}