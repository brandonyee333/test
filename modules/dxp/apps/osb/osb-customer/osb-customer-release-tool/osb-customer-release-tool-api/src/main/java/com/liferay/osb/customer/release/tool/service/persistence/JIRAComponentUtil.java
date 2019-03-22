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

package com.liferay.osb.customer.release.tool.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.release.tool.model.JIRAComponent;
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
 * The persistence utility for the jira component service. This utility wraps <code>com.liferay.osb.customer.release.tool.service.persistence.impl.JIRAComponentPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JIRAComponentPersistence
 * @generated
 */
@ProviderType
public class JIRAComponentUtil {

	/*
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
	public static void clearCache(JIRAComponent jiraComponent) {
		getPersistence().clearCache(jiraComponent);
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
	public static Map<Serializable, JIRAComponent> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<JIRAComponent> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<JIRAComponent> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<JIRAComponent> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<JIRAComponent> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static JIRAComponent update(JIRAComponent jiraComponent) {
		return getPersistence().update(jiraComponent);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static JIRAComponent update(
		JIRAComponent jiraComponent, ServiceContext serviceContext) {

		return getPersistence().update(jiraComponent, serviceContext);
	}

	/**
	 * Returns the jira component where remoteId = &#63; or throws a <code>NoSuchJIRAComponentException</code> if it could not be found.
	 *
	 * @param remoteId the remote ID
	 * @return the matching jira component
	 * @throws NoSuchJIRAComponentException if a matching jira component could not be found
	 */
	public static JIRAComponent findByRemoteId(long remoteId)
		throws com.liferay.osb.customer.release.tool.exception.
			NoSuchJIRAComponentException {

		return getPersistence().findByRemoteId(remoteId);
	}

	/**
	 * Returns the jira component where remoteId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param remoteId the remote ID
	 * @return the matching jira component, or <code>null</code> if a matching jira component could not be found
	 */
	public static JIRAComponent fetchByRemoteId(long remoteId) {
		return getPersistence().fetchByRemoteId(remoteId);
	}

	/**
	 * Returns the jira component where remoteId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param remoteId the remote ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching jira component, or <code>null</code> if a matching jira component could not be found
	 */
	public static JIRAComponent fetchByRemoteId(
		long remoteId, boolean retrieveFromCache) {

		return getPersistence().fetchByRemoteId(remoteId, retrieveFromCache);
	}

	/**
	 * Removes the jira component where remoteId = &#63; from the database.
	 *
	 * @param remoteId the remote ID
	 * @return the jira component that was removed
	 */
	public static JIRAComponent removeByRemoteId(long remoteId)
		throws com.liferay.osb.customer.release.tool.exception.
			NoSuchJIRAComponentException {

		return getPersistence().removeByRemoteId(remoteId);
	}

	/**
	 * Returns the number of jira components where remoteId = &#63;.
	 *
	 * @param remoteId the remote ID
	 * @return the number of matching jira components
	 */
	public static int countByRemoteId(long remoteId) {
		return getPersistence().countByRemoteId(remoteId);
	}

	/**
	 * Returns all the jira components where visible = &#63;.
	 *
	 * @param visible the visible
	 * @return the matching jira components
	 */
	public static List<JIRAComponent> findByVisible(boolean visible) {
		return getPersistence().findByVisible(visible);
	}

	/**
	 * Returns a range of all the jira components where visible = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>JIRAComponentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param visible the visible
	 * @param start the lower bound of the range of jira components
	 * @param end the upper bound of the range of jira components (not inclusive)
	 * @return the range of matching jira components
	 */
	public static List<JIRAComponent> findByVisible(
		boolean visible, int start, int end) {

		return getPersistence().findByVisible(visible, start, end);
	}

	/**
	 * Returns an ordered range of all the jira components where visible = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>JIRAComponentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param visible the visible
	 * @param start the lower bound of the range of jira components
	 * @param end the upper bound of the range of jira components (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching jira components
	 */
	public static List<JIRAComponent> findByVisible(
		boolean visible, int start, int end,
		OrderByComparator<JIRAComponent> orderByComparator) {

		return getPersistence().findByVisible(
			visible, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the jira components where visible = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>JIRAComponentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param visible the visible
	 * @param start the lower bound of the range of jira components
	 * @param end the upper bound of the range of jira components (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching jira components
	 */
	public static List<JIRAComponent> findByVisible(
		boolean visible, int start, int end,
		OrderByComparator<JIRAComponent> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByVisible(
			visible, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first jira component in the ordered set where visible = &#63;.
	 *
	 * @param visible the visible
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching jira component
	 * @throws NoSuchJIRAComponentException if a matching jira component could not be found
	 */
	public static JIRAComponent findByVisible_First(
			boolean visible, OrderByComparator<JIRAComponent> orderByComparator)
		throws com.liferay.osb.customer.release.tool.exception.
			NoSuchJIRAComponentException {

		return getPersistence().findByVisible_First(visible, orderByComparator);
	}

	/**
	 * Returns the first jira component in the ordered set where visible = &#63;.
	 *
	 * @param visible the visible
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching jira component, or <code>null</code> if a matching jira component could not be found
	 */
	public static JIRAComponent fetchByVisible_First(
		boolean visible, OrderByComparator<JIRAComponent> orderByComparator) {

		return getPersistence().fetchByVisible_First(
			visible, orderByComparator);
	}

	/**
	 * Returns the last jira component in the ordered set where visible = &#63;.
	 *
	 * @param visible the visible
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching jira component
	 * @throws NoSuchJIRAComponentException if a matching jira component could not be found
	 */
	public static JIRAComponent findByVisible_Last(
			boolean visible, OrderByComparator<JIRAComponent> orderByComparator)
		throws com.liferay.osb.customer.release.tool.exception.
			NoSuchJIRAComponentException {

		return getPersistence().findByVisible_Last(visible, orderByComparator);
	}

	/**
	 * Returns the last jira component in the ordered set where visible = &#63;.
	 *
	 * @param visible the visible
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching jira component, or <code>null</code> if a matching jira component could not be found
	 */
	public static JIRAComponent fetchByVisible_Last(
		boolean visible, OrderByComparator<JIRAComponent> orderByComparator) {

		return getPersistence().fetchByVisible_Last(visible, orderByComparator);
	}

	/**
	 * Returns the jira components before and after the current jira component in the ordered set where visible = &#63;.
	 *
	 * @param jiraComponentId the primary key of the current jira component
	 * @param visible the visible
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next jira component
	 * @throws NoSuchJIRAComponentException if a jira component with the primary key could not be found
	 */
	public static JIRAComponent[] findByVisible_PrevAndNext(
			long jiraComponentId, boolean visible,
			OrderByComparator<JIRAComponent> orderByComparator)
		throws com.liferay.osb.customer.release.tool.exception.
			NoSuchJIRAComponentException {

		return getPersistence().findByVisible_PrevAndNext(
			jiraComponentId, visible, orderByComparator);
	}

	/**
	 * Removes all the jira components where visible = &#63; from the database.
	 *
	 * @param visible the visible
	 */
	public static void removeByVisible(boolean visible) {
		getPersistence().removeByVisible(visible);
	}

	/**
	 * Returns the number of jira components where visible = &#63;.
	 *
	 * @param visible the visible
	 * @return the number of matching jira components
	 */
	public static int countByVisible(boolean visible) {
		return getPersistence().countByVisible(visible);
	}

	/**
	 * Caches the jira component in the entity cache if it is enabled.
	 *
	 * @param jiraComponent the jira component
	 */
	public static void cacheResult(JIRAComponent jiraComponent) {
		getPersistence().cacheResult(jiraComponent);
	}

	/**
	 * Caches the jira components in the entity cache if it is enabled.
	 *
	 * @param jiraComponents the jira components
	 */
	public static void cacheResult(List<JIRAComponent> jiraComponents) {
		getPersistence().cacheResult(jiraComponents);
	}

	/**
	 * Creates a new jira component with the primary key. Does not add the jira component to the database.
	 *
	 * @param jiraComponentId the primary key for the new jira component
	 * @return the new jira component
	 */
	public static JIRAComponent create(long jiraComponentId) {
		return getPersistence().create(jiraComponentId);
	}

	/**
	 * Removes the jira component with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param jiraComponentId the primary key of the jira component
	 * @return the jira component that was removed
	 * @throws NoSuchJIRAComponentException if a jira component with the primary key could not be found
	 */
	public static JIRAComponent remove(long jiraComponentId)
		throws com.liferay.osb.customer.release.tool.exception.
			NoSuchJIRAComponentException {

		return getPersistence().remove(jiraComponentId);
	}

	public static JIRAComponent updateImpl(JIRAComponent jiraComponent) {
		return getPersistence().updateImpl(jiraComponent);
	}

	/**
	 * Returns the jira component with the primary key or throws a <code>NoSuchJIRAComponentException</code> if it could not be found.
	 *
	 * @param jiraComponentId the primary key of the jira component
	 * @return the jira component
	 * @throws NoSuchJIRAComponentException if a jira component with the primary key could not be found
	 */
	public static JIRAComponent findByPrimaryKey(long jiraComponentId)
		throws com.liferay.osb.customer.release.tool.exception.
			NoSuchJIRAComponentException {

		return getPersistence().findByPrimaryKey(jiraComponentId);
	}

	/**
	 * Returns the jira component with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param jiraComponentId the primary key of the jira component
	 * @return the jira component, or <code>null</code> if a jira component with the primary key could not be found
	 */
	public static JIRAComponent fetchByPrimaryKey(long jiraComponentId) {
		return getPersistence().fetchByPrimaryKey(jiraComponentId);
	}

	/**
	 * Returns all the jira components.
	 *
	 * @return the jira components
	 */
	public static List<JIRAComponent> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the jira components.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>JIRAComponentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of jira components
	 * @param end the upper bound of the range of jira components (not inclusive)
	 * @return the range of jira components
	 */
	public static List<JIRAComponent> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the jira components.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>JIRAComponentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of jira components
	 * @param end the upper bound of the range of jira components (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of jira components
	 */
	public static List<JIRAComponent> findAll(
		int start, int end,
		OrderByComparator<JIRAComponent> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the jira components.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>JIRAComponentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of jira components
	 * @param end the upper bound of the range of jira components (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of jira components
	 */
	public static List<JIRAComponent> findAll(
		int start, int end, OrderByComparator<JIRAComponent> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Removes all the jira components from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of jira components.
	 *
	 * @return the number of jira components
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static JIRAComponentPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<JIRAComponentPersistence, JIRAComponentPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(JIRAComponentPersistence.class);

		ServiceTracker<JIRAComponentPersistence, JIRAComponentPersistence>
			serviceTracker =
				new ServiceTracker
					<JIRAComponentPersistence, JIRAComponentPersistence>(
						bundle.getBundleContext(),
						JIRAComponentPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}