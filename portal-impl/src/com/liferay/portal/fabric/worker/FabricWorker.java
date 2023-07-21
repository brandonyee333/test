/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.fabric.worker;

import com.liferay.portal.fabric.status.FabricStatus;
import com.liferay.portal.kernel.process.ProcessChannel;

import java.io.Serializable;

/**
 * @author Shuyang Zhou
 */
public interface FabricWorker<T extends Serializable>
	extends ProcessChannel<T> {

	public FabricStatus getFabricStatus();

}