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

package com.liferay.shopping.service;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.shopping.model.ShoppingOrder;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for ShoppingOrder. This utility wraps
 * <code>com.liferay.shopping.service.impl.ShoppingOrderLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ShoppingOrderLocalService
 * @generated
 */
public class ShoppingOrderLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.shopping.service.impl.ShoppingOrderLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static ShoppingOrder addLatestOrder(long userId, long groupId)
		throws PortalException {

		return getService().addLatestOrder(userId, groupId);
	}

	/**
	 * Adds the shopping order to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ShoppingOrderLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param shoppingOrder the shopping order
	 * @return the shopping order that was added
	 */
	public static ShoppingOrder addShoppingOrder(ShoppingOrder shoppingOrder) {
		return getService().addShoppingOrder(shoppingOrder);
	}

	public static void completeOrder(
			String number, String ppTxnId, String ppPaymentStatus,
			double ppPaymentGross, String ppReceiverEmail, String ppPayerEmail,
			boolean updateInventory,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		getService().completeOrder(
			number, ppTxnId, ppPaymentStatus, ppPaymentGross, ppReceiverEmail,
			ppPayerEmail, updateInventory, serviceContext);
	}

	/**
	 * Creates a new shopping order with the primary key. Does not add the shopping order to the database.
	 *
	 * @param orderId the primary key for the new shopping order
	 * @return the new shopping order
	 */
	public static ShoppingOrder createShoppingOrder(long orderId) {
		return getService().createShoppingOrder(orderId);
	}

	public static void deleteOrder(long orderId) throws PortalException {
		getService().deleteOrder(orderId);
	}

	public static void deleteOrder(ShoppingOrder order) throws PortalException {
		getService().deleteOrder(order);
	}

	public static void deleteOrders(long groupId) throws PortalException {
		getService().deleteOrders(groupId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the shopping order with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ShoppingOrderLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param orderId the primary key of the shopping order
	 * @return the shopping order that was removed
	 * @throws PortalException if a shopping order with the primary key could not be found
	 */
	public static ShoppingOrder deleteShoppingOrder(long orderId)
		throws PortalException {

		return getService().deleteShoppingOrder(orderId);
	}

	/**
	 * Deletes the shopping order from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ShoppingOrderLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param shoppingOrder the shopping order
	 * @return the shopping order that was removed
	 */
	public static ShoppingOrder deleteShoppingOrder(
		ShoppingOrder shoppingOrder) {

		return getService().deleteShoppingOrder(shoppingOrder);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.shopping.model.impl.ShoppingOrderModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.shopping.model.impl.ShoppingOrderModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static ShoppingOrder fetchShoppingOrder(long orderId) {
		return getService().fetchShoppingOrder(orderId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	public static ShoppingOrder getLatestOrder(long userId, long groupId)
		throws PortalException {

		return getService().getLatestOrder(userId, groupId);
	}

	public static ShoppingOrder getOrder(long orderId) throws PortalException {
		return getService().getOrder(orderId);
	}

	public static ShoppingOrder getOrder(String number) throws PortalException {
		return getService().getOrder(number);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static ShoppingOrder getPayPalTxnIdOrder(String ppTxnId)
		throws PortalException {

		return getService().getPayPalTxnIdOrder(ppTxnId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the shopping order with the primary key.
	 *
	 * @param orderId the primary key of the shopping order
	 * @return the shopping order
	 * @throws PortalException if a shopping order with the primary key could not be found
	 */
	public static ShoppingOrder getShoppingOrder(long orderId)
		throws PortalException {

		return getService().getShoppingOrder(orderId);
	}

	/**
	 * Returns a range of all the shopping orders.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.shopping.model.impl.ShoppingOrderModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of shopping orders
	 * @param end the upper bound of the range of shopping orders (not inclusive)
	 * @return the range of shopping orders
	 */
	public static List<ShoppingOrder> getShoppingOrders(int start, int end) {
		return getService().getShoppingOrders(start, end);
	}

	/**
	 * Returns the number of shopping orders.
	 *
	 * @return the number of shopping orders
	 */
	public static int getShoppingOrdersCount() {
		return getService().getShoppingOrdersCount();
	}

	public static ShoppingOrder saveLatestOrder(
			com.liferay.shopping.model.ShoppingCart cart)
		throws PortalException {

		return getService().saveLatestOrder(cart);
	}

	public static List<ShoppingOrder> search(
		long groupId, long companyId, long userId, String number,
		String billingFirstName, String billingLastName,
		String billingEmailAddress, String shippingFirstName,
		String shippingLastName, String shippingEmailAddress,
		String ppPaymentStatus, boolean andOperator, int start, int end) {

		return getService().search(
			groupId, companyId, userId, number, billingFirstName,
			billingLastName, billingEmailAddress, shippingFirstName,
			shippingLastName, shippingEmailAddress, ppPaymentStatus,
			andOperator, start, end);
	}

	public static int searchCount(
		long groupId, long companyId, long userId, String number,
		String billingFirstName, String billingLastName,
		String billingEmailAddress, String shippingFirstName,
		String shippingLastName, String shippingEmailAddress,
		String ppPaymentStatus, boolean andOperator) {

		return getService().searchCount(
			groupId, companyId, userId, number, billingFirstName,
			billingLastName, billingEmailAddress, shippingFirstName,
			shippingLastName, shippingEmailAddress, ppPaymentStatus,
			andOperator);
	}

	public static void sendEmail(
			long orderId, String emailType,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		getService().sendEmail(orderId, emailType, serviceContext);
	}

	public static void sendEmail(
			ShoppingOrder order, String emailType,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		getService().sendEmail(order, emailType, serviceContext);
	}

	public static ShoppingOrder updateLatestOrder(
			long userId, long groupId, String billingFirstName,
			String billingLastName, String billingEmailAddress,
			String billingCompany, String billingStreet, String billingCity,
			String billingState, String billingZip, String billingCountry,
			String billingPhone, boolean shipToBilling,
			String shippingFirstName, String shippingLastName,
			String shippingEmailAddress, String shippingCompany,
			String shippingStreet, String shippingCity, String shippingState,
			String shippingZip, String shippingCountry, String shippingPhone,
			String ccName, String ccType, String ccNumber, int ccExpMonth,
			int ccExpYear, String ccVerNumber, String comments)
		throws PortalException {

		return getService().updateLatestOrder(
			userId, groupId, billingFirstName, billingLastName,
			billingEmailAddress, billingCompany, billingStreet, billingCity,
			billingState, billingZip, billingCountry, billingPhone,
			shipToBilling, shippingFirstName, shippingLastName,
			shippingEmailAddress, shippingCompany, shippingStreet, shippingCity,
			shippingState, shippingZip, shippingCountry, shippingPhone, ccName,
			ccType, ccNumber, ccExpMonth, ccExpYear, ccVerNumber, comments);
	}

	public static ShoppingOrder updateOrder(
			long orderId, String ppTxnId, String ppPaymentStatus,
			double ppPaymentGross, String ppReceiverEmail, String ppPayerEmail)
		throws PortalException {

		return getService().updateOrder(
			orderId, ppTxnId, ppPaymentStatus, ppPaymentGross, ppReceiverEmail,
			ppPayerEmail);
	}

	public static ShoppingOrder updateOrder(
			long orderId, String billingFirstName, String billingLastName,
			String billingEmailAddress, String billingCompany,
			String billingStreet, String billingCity, String billingState,
			String billingZip, String billingCountry, String billingPhone,
			boolean shipToBilling, String shippingFirstName,
			String shippingLastName, String shippingEmailAddress,
			String shippingCompany, String shippingStreet, String shippingCity,
			String shippingState, String shippingZip, String shippingCountry,
			String shippingPhone, String ccName, String ccType, String ccNumber,
			int ccExpMonth, int ccExpYear, String ccVerNumber, String comments)
		throws PortalException {

		return getService().updateOrder(
			orderId, billingFirstName, billingLastName, billingEmailAddress,
			billingCompany, billingStreet, billingCity, billingState,
			billingZip, billingCountry, billingPhone, shipToBilling,
			shippingFirstName, shippingLastName, shippingEmailAddress,
			shippingCompany, shippingStreet, shippingCity, shippingState,
			shippingZip, shippingCountry, shippingPhone, ccName, ccType,
			ccNumber, ccExpMonth, ccExpYear, ccVerNumber, comments);
	}

	/**
	 * Updates the shopping order in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ShoppingOrderLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param shoppingOrder the shopping order
	 * @return the shopping order that was updated
	 */
	public static ShoppingOrder updateShoppingOrder(
		ShoppingOrder shoppingOrder) {

		return getService().updateShoppingOrder(shoppingOrder);
	}

	public static ShoppingOrderLocalService getService() {
		return _service;
	}

	public static void setService(ShoppingOrderLocalService service) {
		_service = service;
	}

	private static volatile ShoppingOrderLocalService _service;

}