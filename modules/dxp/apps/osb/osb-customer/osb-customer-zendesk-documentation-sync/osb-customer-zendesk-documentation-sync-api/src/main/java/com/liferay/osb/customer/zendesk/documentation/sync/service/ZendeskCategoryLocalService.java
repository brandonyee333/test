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

package com.liferay.osb.customer.zendesk.documentation.sync.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskCategory;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.InputStream;
import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for ZendeskCategory. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see ZendeskCategoryLocalServiceUtil
 * @see com.liferay.osb.customer.zendesk.documentation.sync.service.base.ZendeskCategoryLocalServiceBaseImpl
 * @see com.liferay.osb.customer.zendesk.documentation.sync.service.impl.ZendeskCategoryLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ZendeskCategoryLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ZendeskCategoryLocalServiceUtil} to access the zendesk category local service. Add custom service methods to {@link com.liferay.osb.customer.zendesk.documentation.sync.service.impl.ZendeskCategoryLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public ZendeskCategory addZendeskCategory(String documentationKey,
		String articleLabels, long remoteId) throws PortalException;

	/**
	* Adds the zendesk category to the database. Also notifies the appropriate model listeners.
	*
	* @param zendeskCategory the zendesk category
	* @return the zendesk category that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ZendeskCategory addZendeskCategory(ZendeskCategory zendeskCategory);

	/**
	* Creates a new zendesk category with the primary key. Does not add the zendesk category to the database.
	*
	* @param zendeskCategoryId the primary key for the new zendesk category
	* @return the new zendesk category
	*/
	@Transactional(enabled = false)
	public ZendeskCategory createZendeskCategory(long zendeskCategoryId);

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	/**
	* Deletes the zendesk category with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param zendeskCategoryId the primary key of the zendesk category
	* @return the zendesk category that was removed
	* @throws PortalException if a zendesk category with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public ZendeskCategory deleteZendeskCategory(long zendeskCategoryId)
		throws PortalException;

	/**
	* Deletes the zendesk category from the database. Also notifies the appropriate model listeners.
	*
	* @param zendeskCategory the zendesk category
	* @return the zendesk category that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public ZendeskCategory deleteZendeskCategory(
		ZendeskCategory zendeskCategory);

	public DynamicQuery dynamicQuery();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.customer.zendesk.documentation.sync.model.impl.ZendeskCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.customer.zendesk.documentation.sync.model.impl.ZendeskCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ZendeskCategory fetchZendeskCategory(long zendeskCategoryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ZendeskCategory fetchZendeskCategory(String documentationKey);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public String getOSGiServiceIdentifier();

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Returns a range of all the zendesk categories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.customer.zendesk.documentation.sync.model.impl.ZendeskCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of zendesk categories
	* @param end the upper bound of the range of zendesk categories (not inclusive)
	* @return the range of zendesk categories
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ZendeskCategory> getZendeskCategories(int start, int end);

	/**
	* Returns the number of zendesk categories.
	*
	* @return the number of zendesk categories
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getZendeskCategoriesCount();

	/**
	* Returns the zendesk category with the primary key.
	*
	* @param zendeskCategoryId the primary key of the zendesk category
	* @return the zendesk category
	* @throws PortalException if a zendesk category with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ZendeskCategory getZendeskCategory(long zendeskCategoryId)
		throws PortalException;

	public void importDocumentationArchive(long zendeskCategoryId,
		String fileName, InputStream inputStream) throws Exception;

	public ZendeskCategory updateZendeskCategory(long zendeskCategoryId,
		String articleLabels) throws PortalException;

	/**
	* Updates the zendesk category in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param zendeskCategory the zendesk category
	* @return the zendesk category that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ZendeskCategory updateZendeskCategory(
		ZendeskCategory zendeskCategory);
}