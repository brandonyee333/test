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
import com.liferay.shopping.model.ShoppingItem;
import com.liferay.shopping.model.ShoppingItemField;
import com.liferay.shopping.model.ShoppingItemPrice;

import java.io.File;

import java.util.List;

/**
 * Provides the remote service interface for ShoppingItem. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see ShoppingItemServiceUtil
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(
	property = {
		"json.web.service.context.name=shopping",
		"json.web.service.context.path=ShoppingItem"
	},
	service = ShoppingItemService.class
)
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface ShoppingItemService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.liferay.shopping.service.impl.ShoppingItemServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the shopping item remote service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link ShoppingItemServiceUtil} if injection and service tracking are not available.
	 */
	public ShoppingItem addItem(
			long groupId, long categoryId, String sku, String name,
			String description, String properties, String fieldsQuantities,
			boolean requiresShipping, int stockQuantity, boolean featured,
			Boolean sale, boolean smallImage, String smallImageURL,
			File smallFile, boolean mediumImage, String mediumImageURL,
			File mediumFile, boolean largeImage, String largeImageURL,
			File largeFile, List<ShoppingItemField> itemFields,
			List<ShoppingItemPrice> itemPrices, ServiceContext serviceContext)
		throws PortalException;

	public void deleteItem(long itemId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCategoriesItemsCount(long groupId, List<Long> categoryIds);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ShoppingItem getItem(long itemId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ShoppingItem> getItems(long groupId, long categoryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ShoppingItem> getItems(
		long groupId, long categoryId, int start, int end,
		OrderByComparator<ShoppingItem> obc);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getItemsCount(long groupId, long categoryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ShoppingItem[] getItemsPrevAndNext(
			long itemId, OrderByComparator<ShoppingItem> obc)
		throws PortalException;

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	public ShoppingItem updateItem(
			long itemId, long groupId, long categoryId, String sku, String name,
			String description, String properties, String fieldsQuantities,
			boolean requiresShipping, int stockQuantity, boolean featured,
			Boolean sale, boolean smallImage, String smallImageURL,
			File smallFile, boolean mediumImage, String mediumImageURL,
			File mediumFile, boolean largeImage, String largeImageURL,
			File largeFile, List<ShoppingItemField> itemFields,
			List<ShoppingItemPrice> itemPrices, ServiceContext serviceContext)
		throws PortalException;

}