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

package com.liferay.osb.asah.monolith.common.upgrade.impl;

import com.liferay.osb.asah.common.upgrade.UpgradeState;

import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 */
@Component
public class UpgradeStateImpl implements UpgradeState {

	@Override
	public void awaitCompletion() {
	}

	@Override
	public void complete() {
	}

	@Override
	public boolean isComplete() {
		return false;
	}

}