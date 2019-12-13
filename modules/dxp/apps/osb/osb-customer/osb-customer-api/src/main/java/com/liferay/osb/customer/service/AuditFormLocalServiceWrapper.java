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

package com.liferay.osb.customer.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AuditFormLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AuditFormLocalService
 * @generated
 */
public class AuditFormLocalServiceWrapper
	implements AuditFormLocalService, ServiceWrapper<AuditFormLocalService> {

	public AuditFormLocalServiceWrapper(
		AuditFormLocalService auditFormLocalService) {

		_auditFormLocalService = auditFormLocalService;
	}

	/**
	 * Adds the audit form to the database. Also notifies the appropriate model listeners.
	 *
	 * @param auditForm the audit form
	 * @return the audit form that was added
	 */
	@Override
	public com.liferay.osb.customer.model.AuditForm addAuditForm(
		com.liferay.osb.customer.model.AuditForm auditForm) {

		return _auditFormLocalService.addAuditForm(auditForm);
	}

	@Override
	public com.liferay.osb.customer.model.AuditForm addAuditForm(
			long userId, String endUserName, String endUserEmailAddress,
			String companyName, boolean agreement)
		throws Exception {

		return _auditFormLocalService.addAuditForm(
			userId, endUserName, endUserEmailAddress, companyName, agreement);
	}

	/**
	 * Creates a new audit form with the primary key. Does not add the audit form to the database.
	 *
	 * @param auditFormId the primary key for the new audit form
	 * @return the new audit form
	 */
	@Override
	public com.liferay.osb.customer.model.AuditForm createAuditForm(
		long auditFormId) {

		return _auditFormLocalService.createAuditForm(auditFormId);
	}

	/**
	 * Deletes the audit form from the database. Also notifies the appropriate model listeners.
	 *
	 * @param auditForm the audit form
	 * @return the audit form that was removed
	 */
	@Override
	public com.liferay.osb.customer.model.AuditForm deleteAuditForm(
		com.liferay.osb.customer.model.AuditForm auditForm) {

		return _auditFormLocalService.deleteAuditForm(auditForm);
	}

	/**
	 * Deletes the audit form with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param auditFormId the primary key of the audit form
	 * @return the audit form that was removed
	 * @throws PortalException if a audit form with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.customer.model.AuditForm deleteAuditForm(
			long auditFormId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _auditFormLocalService.deleteAuditForm(auditFormId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _auditFormLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _auditFormLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _auditFormLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.model.impl.AuditFormModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _auditFormLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.model.impl.AuditFormModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _auditFormLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _auditFormLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _auditFormLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.customer.model.AuditForm fetchAuditForm(
		long auditFormId) {

		return _auditFormLocalService.fetchAuditForm(auditFormId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _auditFormLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the audit form with the primary key.
	 *
	 * @param auditFormId the primary key of the audit form
	 * @return the audit form
	 * @throws PortalException if a audit form with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.customer.model.AuditForm getAuditForm(
			long auditFormId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _auditFormLocalService.getAuditForm(auditFormId);
	}

	/**
	 * Returns a range of all the audit forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.model.impl.AuditFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of audit forms
	 * @param end the upper bound of the range of audit forms (not inclusive)
	 * @return the range of audit forms
	 */
	@Override
	public java.util.List<com.liferay.osb.customer.model.AuditForm>
		getAuditForms(int start, int end) {

		return _auditFormLocalService.getAuditForms(start, end);
	}

	/**
	 * Returns the number of audit forms.
	 *
	 * @return the number of audit forms
	 */
	@Override
	public int getAuditFormsCount() {
		return _auditFormLocalService.getAuditFormsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _auditFormLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _auditFormLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _auditFormLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the audit form in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param auditForm the audit form
	 * @return the audit form that was updated
	 */
	@Override
	public com.liferay.osb.customer.model.AuditForm updateAuditForm(
		com.liferay.osb.customer.model.AuditForm auditForm) {

		return _auditFormLocalService.updateAuditForm(auditForm);
	}

	@Override
	public AuditFormLocalService getWrappedService() {
		return _auditFormLocalService;
	}

	@Override
	public void setWrappedService(AuditFormLocalService auditFormLocalService) {
		_auditFormLocalService = auditFormLocalService;
	}

	private AuditFormLocalService _auditFormLocalService;

}