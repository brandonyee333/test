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

package com.liferay.osb.customer.service.persistence;

import com.liferay.osb.customer.model.AuditForm;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the audit form service. This utility wraps <code>com.liferay.osb.customer.service.persistence.impl.AuditFormPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AuditFormPersistence
 * @generated
 */
public class AuditFormUtil {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(AuditForm auditForm) {
		getPersistence().clearCache(auditForm);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, AuditForm> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<AuditForm> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AuditForm> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AuditForm> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<AuditForm> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static AuditForm update(AuditForm auditForm) {
		return getPersistence().update(auditForm);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static AuditForm update(
		AuditForm auditForm, ServiceContext serviceContext) {

		return getPersistence().update(auditForm, serviceContext);
	}

	/**
	 * Caches the audit form in the entity cache if it is enabled.
	 *
	 * @param auditForm the audit form
	 */
	public static void cacheResult(AuditForm auditForm) {
		getPersistence().cacheResult(auditForm);
	}

	/**
	 * Caches the audit forms in the entity cache if it is enabled.
	 *
	 * @param auditForms the audit forms
	 */
	public static void cacheResult(List<AuditForm> auditForms) {
		getPersistence().cacheResult(auditForms);
	}

	/**
	 * Creates a new audit form with the primary key. Does not add the audit form to the database.
	 *
	 * @param auditFormId the primary key for the new audit form
	 * @return the new audit form
	 */
	public static AuditForm create(long auditFormId) {
		return getPersistence().create(auditFormId);
	}

	/**
	 * Removes the audit form with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param auditFormId the primary key of the audit form
	 * @return the audit form that was removed
	 * @throws NoSuchAuditFormException if a audit form with the primary key could not be found
	 */
	public static AuditForm remove(long auditFormId)
		throws com.liferay.osb.customer.exception.NoSuchAuditFormException {

		return getPersistence().remove(auditFormId);
	}

	public static AuditForm updateImpl(AuditForm auditForm) {
		return getPersistence().updateImpl(auditForm);
	}

	/**
	 * Returns the audit form with the primary key or throws a <code>NoSuchAuditFormException</code> if it could not be found.
	 *
	 * @param auditFormId the primary key of the audit form
	 * @return the audit form
	 * @throws NoSuchAuditFormException if a audit form with the primary key could not be found
	 */
	public static AuditForm findByPrimaryKey(long auditFormId)
		throws com.liferay.osb.customer.exception.NoSuchAuditFormException {

		return getPersistence().findByPrimaryKey(auditFormId);
	}

	/**
	 * Returns the audit form with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param auditFormId the primary key of the audit form
	 * @return the audit form, or <code>null</code> if a audit form with the primary key could not be found
	 */
	public static AuditForm fetchByPrimaryKey(long auditFormId) {
		return getPersistence().fetchByPrimaryKey(auditFormId);
	}

	/**
	 * Returns all the audit forms.
	 *
	 * @return the audit forms
	 */
	public static List<AuditForm> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the audit forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of audit forms
	 * @param end the upper bound of the range of audit forms (not inclusive)
	 * @return the range of audit forms
	 */
	public static List<AuditForm> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the audit forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of audit forms
	 * @param end the upper bound of the range of audit forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of audit forms
	 */
	public static List<AuditForm> findAll(
		int start, int end, OrderByComparator<AuditForm> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the audit forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of audit forms
	 * @param end the upper bound of the range of audit forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of audit forms
	 */
	public static List<AuditForm> findAll(
		int start, int end, OrderByComparator<AuditForm> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the audit forms from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of audit forms.
	 *
	 * @return the number of audit forms
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static AuditFormPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<AuditFormPersistence, AuditFormPersistence>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(AuditFormPersistence.class);

		ServiceTracker<AuditFormPersistence, AuditFormPersistence>
			serviceTracker =
				new ServiceTracker<AuditFormPersistence, AuditFormPersistence>(
					bundle.getBundleContext(), AuditFormPersistence.class,
					null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}