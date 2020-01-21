/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.shopping.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.shopping.service.ShoppingCategoryServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>ShoppingCategoryServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.liferay.shopping.model.ShoppingCategorySoap</code>. If the method in the
 * service utility returns a
 * <code>com.liferay.shopping.model.ShoppingCategory</code>, that is translated to a
 * <code>com.liferay.shopping.model.ShoppingCategorySoap</code>. Methods that SOAP
 * cannot safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ShoppingCategoryServiceHttp
 * @generated
 */
public class ShoppingCategoryServiceSoap {

	public static com.liferay.shopping.model.ShoppingCategorySoap addCategory(
			long parentCategoryId, String name, String description,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			com.liferay.shopping.model.ShoppingCategory returnValue =
				ShoppingCategoryServiceUtil.addCategory(
					parentCategoryId, name, description, serviceContext);

			return com.liferay.shopping.model.ShoppingCategorySoap.toSoapModel(
				returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static void deleteCategory(long categoryId) throws RemoteException {
		try {
			ShoppingCategoryServiceUtil.deleteCategory(categoryId);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.shopping.model.ShoppingCategorySoap[]
			getCategories(long groupId)
		throws RemoteException {

		try {
			java.util.List<com.liferay.shopping.model.ShoppingCategory>
				returnValue = ShoppingCategoryServiceUtil.getCategories(
					groupId);

			return com.liferay.shopping.model.ShoppingCategorySoap.toSoapModels(
				returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.shopping.model.ShoppingCategorySoap[]
			getCategories(
				long groupId, long parentCategoryId, int start, int end)
		throws RemoteException {

		try {
			java.util.List<com.liferay.shopping.model.ShoppingCategory>
				returnValue = ShoppingCategoryServiceUtil.getCategories(
					groupId, parentCategoryId, start, end);

			return com.liferay.shopping.model.ShoppingCategorySoap.toSoapModels(
				returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static int getCategoriesAndItemsCount(long groupId, long categoryId)
		throws RemoteException {

		try {
			int returnValue =
				ShoppingCategoryServiceUtil.getCategoriesAndItemsCount(
					groupId, categoryId);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static int getCategoriesCount(long groupId, long parentCategoryId)
		throws RemoteException {

		try {
			int returnValue = ShoppingCategoryServiceUtil.getCategoriesCount(
				groupId, parentCategoryId);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.shopping.model.ShoppingCategorySoap getCategory(
			long categoryId)
		throws RemoteException {

		try {
			com.liferay.shopping.model.ShoppingCategory returnValue =
				ShoppingCategoryServiceUtil.getCategory(categoryId);

			return com.liferay.shopping.model.ShoppingCategorySoap.toSoapModel(
				returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static void getSubcategoryIds(
			Long[] categoryIds, long groupId, long categoryId)
		throws RemoteException {

		try {
			ShoppingCategoryServiceUtil.getSubcategoryIds(
				ListUtil.toList(categoryIds), groupId, categoryId);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.shopping.model.ShoppingCategorySoap
			updateCategory(
				long categoryId, long parentCategoryId, String name,
				String description, boolean mergeWithParentCategory,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			com.liferay.shopping.model.ShoppingCategory returnValue =
				ShoppingCategoryServiceUtil.updateCategory(
					categoryId, parentCategoryId, name, description,
					mergeWithParentCategory, serviceContext);

			return com.liferay.shopping.model.ShoppingCategorySoap.toSoapModel(
				returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		ShoppingCategoryServiceSoap.class);

}