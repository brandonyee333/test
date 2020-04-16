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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for DocProject. This utility wraps
 * <code>com.liferay.osb.community.doc.project.service.impl.DocProjectLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Ryan Park
 * @see DocProjectLocalService
 * @generated
 */
public class DocProjectLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.community.doc.project.service.impl.DocProjectLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the doc project to the database. Also notifies the appropriate model listeners.
	 *
	 * @param docProject the doc project
	 * @return the doc project that was added
	 */
	public static com.liferay.osb.community.doc.project.model.DocProject
		addDocProject(
			com.liferay.osb.community.doc.project.model.DocProject docProject) {

		return getService().addDocProject(docProject);
	}

	public static com.liferay.osb.community.doc.project.model.DocProject
			addDocProject(
				long userId, String name, String description,
				String iconFileName, java.io.File iconFile, boolean unlisted,
				String type, String typeSettings, int status,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addDocProject(
			userId, name, description, iconFileName, iconFile, unlisted, type,
			typeSettings, status, serviceContext);
	}

	/**
	 * Creates a new doc project with the primary key. Does not add the doc project to the database.
	 *
	 * @param docProjectId the primary key for the new doc project
	 * @return the new doc project
	 */
	public static com.liferay.osb.community.doc.project.model.DocProject
		createDocProject(long docProjectId) {

		return getService().createDocProject(docProjectId);
	}

	/**
	 * Deletes the doc project from the database. Also notifies the appropriate model listeners.
	 *
	 * @param docProject the doc project
	 * @return the doc project that was removed
	 * @throws PortalException
	 */
	public static com.liferay.osb.community.doc.project.model.DocProject
			deleteDocProject(
				com.liferay.osb.community.doc.project.model.DocProject
					docProject)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteDocProject(docProject);
	}

	/**
	 * Deletes the doc project with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param docProjectId the primary key of the doc project
	 * @return the doc project that was removed
	 * @throws PortalException if a doc project with the primary key could not be found
	 */
	public static com.liferay.osb.community.doc.project.model.DocProject
			deleteDocProject(long docProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteDocProject(docProjectId);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQuery(dynamicQuery);
	}

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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

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
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.osb.community.doc.project.model.DocProject
		fetchDocProject(long docProjectId) {

		return getService().fetchDocProject(docProjectId);
	}

	/**
	 * Returns the doc project matching the UUID and group.
	 *
	 * @param uuid the doc project's UUID
	 * @param groupId the primary key of the group
	 * @return the matching doc project, or <code>null</code> if a matching doc project could not be found
	 */
	public static com.liferay.osb.community.doc.project.model.DocProject
		fetchDocProjectByUuidAndGroupId(String uuid, long groupId) {

		return getService().fetchDocProjectByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the doc project with the primary key.
	 *
	 * @param docProjectId the primary key of the doc project
	 * @return the doc project
	 * @throws PortalException if a doc project with the primary key could not be found
	 */
	public static com.liferay.osb.community.doc.project.model.DocProject
			getDocProject(long docProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getDocProject(docProjectId);
	}

	public static com.liferay.osb.community.doc.project.model.DocProject
			getDocProjectByGroupId(long groupId)
		throws com.liferay.osb.community.doc.project.exception.
			NoSuchDocProjectException {

		return getService().getDocProjectByGroupId(groupId);
	}

	/**
	 * Returns the doc project matching the UUID and group.
	 *
	 * @param uuid the doc project's UUID
	 * @param groupId the primary key of the group
	 * @return the matching doc project
	 * @throws PortalException if a matching doc project could not be found
	 */
	public static com.liferay.osb.community.doc.project.model.DocProject
			getDocProjectByUuidAndGroupId(String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getDocProjectByUuidAndGroupId(uuid, groupId);
	}

	public static java.util.List
		<com.liferay.osb.community.doc.project.model.DocProject> getDocProjects(
				boolean unlisted, int status, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator obc)
			throws com.liferay.osb.community.doc.project.exception.
				NoSuchDocProjectException {

		return getService().getDocProjects(unlisted, status, start, end, obc);
	}

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
	public static java.util.List
		<com.liferay.osb.community.doc.project.model.DocProject> getDocProjects(
			int start, int end) {

		return getService().getDocProjects(start, end);
	}

	/**
	 * Returns all the doc projects matching the UUID and company.
	 *
	 * @param uuid the UUID of the doc projects
	 * @param companyId the primary key of the company
	 * @return the matching doc projects, or an empty list if no matches were found
	 */
	public static java.util.List
		<com.liferay.osb.community.doc.project.model.DocProject>
			getDocProjectsByUuidAndCompanyId(String uuid, long companyId) {

		return getService().getDocProjectsByUuidAndCompanyId(uuid, companyId);
	}

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
	public static java.util.List
		<com.liferay.osb.community.doc.project.model.DocProject>
			getDocProjectsByUuidAndCompanyId(
				String uuid, long companyId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.osb.community.doc.project.model.DocProject>
						orderByComparator) {

		return getService().getDocProjectsByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of doc projects.
	 *
	 * @return the number of doc projects
	 */
	public static int getDocProjectsCount() {
		return getService().getDocProjectsCount();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	public static void updateAsset(
			long userId, long docProjectId, long[] assetCategoryIds,
			String[] assetTagNames)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().updateAsset(
			userId, docProjectId, assetCategoryIds, assetTagNames);
	}

	/**
	 * Updates the doc project in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param docProject the doc project
	 * @return the doc project that was updated
	 */
	public static com.liferay.osb.community.doc.project.model.DocProject
		updateDocProject(
			com.liferay.osb.community.doc.project.model.DocProject docProject) {

		return getService().updateDocProject(docProject);
	}

	public static com.liferay.osb.community.doc.project.model.DocProject
			updateDocProject(
				long docProjectId, String name, String description,
				String iconFileName, java.io.File iconFile, boolean unlisted,
				String type, String typeSettings, int status,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateDocProject(
			docProjectId, name, description, iconFileName, iconFile, unlisted,
			type, typeSettings, status, serviceContext);
	}

	public static DocProjectLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<DocProjectLocalService, DocProjectLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(DocProjectLocalService.class);

		ServiceTracker<DocProjectLocalService, DocProjectLocalService>
			serviceTracker =
				new ServiceTracker
					<DocProjectLocalService, DocProjectLocalService>(
						bundle.getBundleContext(), DocProjectLocalService.class,
						null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}