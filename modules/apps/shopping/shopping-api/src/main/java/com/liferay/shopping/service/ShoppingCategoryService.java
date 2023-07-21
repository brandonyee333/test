/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.shopping.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.shopping.model.ShoppingCategory;

import java.util.List;

/**
 * Provides the remote service interface for ShoppingCategory. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see ShoppingCategoryServiceUtil
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(
	property = {
		"json.web.service.context.name=shopping",
		"json.web.service.context.path=ShoppingCategory"
	},
	service = ShoppingCategoryService.class
)
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface ShoppingCategoryService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.liferay.shopping.service.impl.ShoppingCategoryServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the shopping category remote service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link ShoppingCategoryServiceUtil} if injection and service tracking are not available.
	 */
	public ShoppingCategory addCategory(
			long parentCategoryId, String name, String description,
			ServiceContext serviceContext)
		throws PortalException;

	public void deleteCategory(long categoryId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ShoppingCategory> getCategories(long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ShoppingCategory> getCategories(
		long groupId, long parentCategoryId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Object> getCategoriesAndItems(
		long groupId, long categoryId, int start, int end,
		OrderByComparator<?> obc);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCategoriesAndItemsCount(long groupId, long categoryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCategoriesCount(long groupId, long parentCategoryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ShoppingCategory getCategory(long categoryId) throws PortalException;

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public void getSubcategoryIds(
		List<Long> categoryIds, long groupId, long categoryId);

	public ShoppingCategory updateCategory(
			long categoryId, long parentCategoryId, String name,
			String description, boolean mergeWithParentCategory,
			ServiceContext serviceContext)
		throws PortalException;

}