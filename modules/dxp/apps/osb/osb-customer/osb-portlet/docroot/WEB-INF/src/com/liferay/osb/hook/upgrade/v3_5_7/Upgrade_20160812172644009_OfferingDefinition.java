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

package com.liferay.osb.hook.upgrade.v3_5_7;

import com.liferay.osb.hook.upgrade.v3_5_1.Upgrade_20160602105202547_OfferingDefinition;

/**
 * @author Amos Fong
 */
public class Upgrade_20160812172644009_OfferingDefinition
	extends Upgrade_20160602105202547_OfferingDefinition {

	@Override
	protected void doUpgrade() throws Exception {
		removeDuplicates();
	}

}