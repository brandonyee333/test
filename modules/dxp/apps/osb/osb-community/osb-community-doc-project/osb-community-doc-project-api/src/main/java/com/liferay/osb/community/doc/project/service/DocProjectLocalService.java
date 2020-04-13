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

package com.liferay.osb.community.doc.project.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.osb.community.doc.project.exception.NoSuchDocProjectException;
import com.liferay.osb.community.doc.project.model.DocProject;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.File;
import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for DocProject. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Ryan Park
 * @see DocProjectLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface DocProjectLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DocProjectLocalServiceUtil} to access the doc project local service. Add custom service methods to <code>com.liferay.osb.community.doc.project.service.impl.DocProjectLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	 * Adds the doc project to the database. Also notifies the appropriate model listeners.
	 *
	 * @param docProject the doc project
	 * @return the doc project that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public DocProject addDocProject(DocProject docProject);

	public DocProject addDocProject(
			long userId, String name, String description, String iconFileName,
			File iconFile, boolean unlisted, String type, String typeSettings,
			int status, ServiceContext serviceContext)
		throws PortalException;

	/**
	 * Creates a new doc project with the primary key. Does not add the doc project to the database.
	 *
	 * @param docProjectId the primary key for the new doc project
	 * @return the new doc project
	 */
	@Transactional(enabled = false)
	public DocProject createDocProject(long docProjectId);

	/**
	 * Deletes the doc project from the database. Also notifies the appropriate model listeners.
	 *
	 * @param docProject the doc project
	 * @return the doc project that was removed
	 * @throws PortalException
	 */
	@Indexable(type = IndexableType.DELETE)
	public DocProject deleteDocProject(DocProject docProject)
		throws PortalException;

	/**
	 * Deletes the doc project with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param docProjectId the primary key of the doc project
	 * @return the doc project that was removed
	 * @throws PortalException if a doc project with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public DocProject deleteDocProject(long docProjectId)
		throws PortalException;

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DynamicQuery dynamicQuery();

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.community.doc.project.model.impl.DocProjectModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end);

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.community.doc.project.model.impl.DocProjectModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DocProject fetchDocProject(long docProjectId);

	/**
	 * Returns the doc project matching the UUID and group.
	 *
	 * @param uuid the doc project's UUID
	 * @param groupId the primary key of the group
	 * @return the matching doc project, or <code>null</code> if a matching doc project could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DocProject fetchDocProjectByUuidAndGroupId(
		String uuid, long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	/**
	 * Returns the doc project with the primary key.
	 *
	 * @param docProjectId the primary key of the doc project
	 * @return the doc project
	 * @throws PortalException if a doc project with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DocProject getDocProject(long docProjectId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DocProject getDocProjectByGroupId(long groupId)
		throws NoSuchDocProjectException;

	/**
	 * Returns the doc project matching the UUID and group.
	 *
	 * @param uuid the doc project's UUID
	 * @param groupId the primary key of the group
	 * @return the matching doc project
	 * @throws PortalException if a matching doc project could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DocProject getDocProjectByUuidAndGroupId(String uuid, long groupId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DocProject> getDocProjects(
			boolean unlisted, int status, int start, int end,
			OrderByComparator obc)
		throws NoSuchDocProjectException;

	/**
	 * Returns a range of all the doc projects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.community.doc.project.model.impl.DocProjectModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of doc projects
	 * @param end the upper bound of the range of doc projects (not inclusive)
	 * @return the range of doc projects
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DocProject> getDocProjects(int start, int end);

	/**
	 * Returns all the doc projects matching the UUID and company.
	 *
	 * @param uuid the UUID of the doc projects
	 * @param companyId the primary key of the company
	 * @return the matching doc projects, or an empty list if no matches were found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DocProject> getDocProjectsByUuidAndCompanyId(
		String uuid, long companyId);

	/**
	 * Returns a range of doc projects matching the UUID and company.
	 *
	 * @param uuid the UUID of the doc projects
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of doc projects
	 * @param end the upper bound of the range of doc projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching doc projects, or an empty list if no matches were found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DocProject> getDocProjectsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<DocProject> orderByComparator);

	/**
	 * Returns the number of doc projects.
	 *
	 * @return the number of doc projects
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getDocProjectsCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

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

	public void updateAsset(
			long userId, long docProjectId, long[] assetCategoryIds,
			String[] assetTagNames)
		throws PortalException;

	/**
	 * Updates the doc project in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param docProject the doc project
	 * @return the doc project that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public DocProject updateDocProject(DocProject docProject);

	public DocProject updateDocProject(
			long docProjectId, String name, String description,
			String iconFileName, File iconFile, boolean unlisted, String type,
			String typeSettings, int status, ServiceContext serviceContext)
		throws PortalException;

}