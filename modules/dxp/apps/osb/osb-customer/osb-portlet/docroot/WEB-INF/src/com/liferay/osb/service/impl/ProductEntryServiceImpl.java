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

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.service.base.ProductEntryServiceBaseImpl;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;

/**
 * The implementation of the product entry remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.osb.service.ProductEntryService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProductEntryServiceBaseImpl
 * @see com.liferay.osb.service.ProductEntryServiceUtil
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
@ProviderType
public class ProductEntryServiceImpl extends ProductEntryServiceBaseImpl {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.liferay.osb.service.ProductEntryServiceUtil} to access the product entry remote service.
	 */

}