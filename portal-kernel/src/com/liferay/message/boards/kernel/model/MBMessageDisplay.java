/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.message.boards.kernel.model;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public interface MBMessageDisplay extends Serializable {

	public MBCategory getCategory();

	public MBMessage getMessage();

	/**
	 * @deprecated As of Wilberforce (7.0.x), with no direct replacement
	 */
	@Deprecated
	public MBThread getNextThread();

	public MBMessage getParentMessage();

	/**
	 * @deprecated As of Wilberforce (7.0.x), with no direct replacement
	 */
	@Deprecated
	public MBThread getPreviousThread();

	public MBThread getThread();

	/**
	 * @deprecated As of Wilberforce (7.0.x), with no direct replacement
	 */
	@Deprecated
	public String getThreadView();

	public MBTreeWalker getTreeWalker();

	public boolean isDiscussionMaxComments();

}