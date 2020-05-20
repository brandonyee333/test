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

package com.liferay.osb.service.impl;

import com.liferay.osb.exception.DuplicateOfferingBundleException;
import com.liferay.osb.exception.OfferingBundleNameException;
import com.liferay.osb.model.OfferingBundle;
import com.liferay.osb.service.base.OfferingBundleLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;

/**
 * @author Amos Fong
 */
public class OfferingBundleLocalServiceImpl
	extends OfferingBundleLocalServiceBaseImpl {

	public OfferingBundle addOfferingBundle(
			long userId, String name, long[] offeringDefinitionIds)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		validate(0, name);

		long offeringBundleId = counterLocalService.increment();

		OfferingBundle offeringBundle = offeringBundlePersistence.create(
			offeringBundleId);

		offeringBundle.setUserId(user.getUserId());
		offeringBundle.setUserName(user.getFullName());
		offeringBundle.setCreateDate(new Date());
		offeringBundle.setName(name);

		offeringBundle = offeringBundlePersistence.update(offeringBundle);

		offeringBundlePersistence.setOfferingDefinitions(
			offeringBundleId, offeringDefinitionIds);

		return offeringBundle;
	}

	public OfferingBundle updateOfferingBundle(
			long offeringBundleId, String name, long[] offeringDefinitionIds)
		throws PortalException {

		validate(offeringBundleId, name);

		OfferingBundle offeringBundle =
			offeringBundlePersistence.findByPrimaryKey(offeringBundleId);

		offeringBundle.setName(name);

		offeringBundle = offeringBundlePersistence.update(offeringBundle);

		offeringBundlePersistence.setOfferingDefinitions(
			offeringBundleId, offeringDefinitionIds);

		return offeringBundle;
	}

	protected void validate(long offeringBundleId, String name)
		throws PortalException {

		if (Validator.isNull(name)) {
			throw new OfferingBundleNameException();
		}

		OfferingBundle offeringBundle = offeringBundlePersistence.fetchByName(
			name);

		if ((offeringBundle != null) &&
			(offeringBundle.getOfferingBundleId() != offeringBundleId)) {

			throw new DuplicateOfferingBundleException();
		}
	}

}