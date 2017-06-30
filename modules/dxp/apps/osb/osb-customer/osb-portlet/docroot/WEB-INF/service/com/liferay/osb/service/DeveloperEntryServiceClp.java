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

package com.liferay.osb.service;

import com.liferay.portal.service.InvokableService;

/**
 * @author Brian Wing Shun Chan
 */
public class DeveloperEntryServiceClp implements DeveloperEntryService {
	public DeveloperEntryServiceClp(InvokableService invokableService) {
		_invokableService = invokableService;

		_methodName0 = "getBeanIdentifier";

		_methodParameterTypes0 = new String[] {  };

		_methodName1 = "setBeanIdentifier";

		_methodParameterTypes1 = new String[] { "java.lang.String" };

		_methodName3 = "addCompanyDeveloperEntry";

		_methodParameterTypes3 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"long", "long", "java.util.Map", "java.lang.String",
				"java.io.File", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.io.File", "long", "long",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName4 = "addUserDeveloperEntry";

		_methodParameterTypes4 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "long", "long",
				"java.lang.String", "com.liferay.portal.service.ServiceContext"
			};

		_methodName5 = "updateCompanyDeveloperEntry";

		_methodParameterTypes5 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "long", "long", "java.io.File",
				"java.util.Map", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.io.File",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName6 = "updateDeveloperEntry";

		_methodParameterTypes6 = new String[] { "long", "java.lang.String" };

		_methodName7 = "updateDeveloperEntry";

		_methodParameterTypes7 = new String[] {
				"long", "java.lang.String", "int", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.util.Date", "int",
				"double", "java.lang.String", "java.io.File"
			};

		_methodName8 = "updateDeveloperEntry";

		_methodParameterTypes8 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"java.lang.String"
			};

		_methodName9 = "updateDeveloperEntryGoogleAnalyticsKey";

		_methodParameterTypes9 = new String[] { "long", "java.lang.String" };

		_methodName10 = "updateStatus";

		_methodParameterTypes10 = new String[] { "long", "int", "java.lang.String" };

		_methodName11 = "updateSubscription";

		_methodParameterTypes11 = new String[] { "long", "java.util.Date", "int" };

		_methodName12 = "updateUserDeveloperEntry";

		_methodParameterTypes12 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "long", "long",
				"java.lang.String", "java.io.File"
			};
	}

	public java.lang.String getBeanIdentifier() {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName0,
					_methodParameterTypes0, new Object[] {  });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.lang.String)ClpSerializer.translateOutput(returnObj);
	}

	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		try {
			_invokableService.invokeMethod(_methodName1,
				_methodParameterTypes1,
				new Object[] { ClpSerializer.translateInput(beanIdentifier) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		throw new UnsupportedOperationException();
	}

	public com.liferay.osb.model.DeveloperEntry addCompanyDeveloperEntry(
		java.lang.String emailAddress, java.lang.String legalEntityName,
		java.lang.String phoneNumber, java.lang.String faxNumber,
		java.lang.String street1, java.lang.String street2,
		java.lang.String street3, java.lang.String city, java.lang.String zip,
		long regionId, long countryId,
		java.util.Map<java.util.Locale, java.lang.String> profileDescriptionMap,
		java.lang.String profileEmailAddress, java.io.File profileLogo,
		java.lang.String profileWebsite, java.lang.String dossieraAccountKey,
		java.lang.String taxDocumentFileName, java.io.File taxDocumentFile,
		long tosContractEntryId, long developerContractEntryId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName3,
					_methodParameterTypes3,
					new Object[] {
						ClpSerializer.translateInput(emailAddress),
						
					ClpSerializer.translateInput(legalEntityName),
						
					ClpSerializer.translateInput(phoneNumber),
						
					ClpSerializer.translateInput(faxNumber),
						
					ClpSerializer.translateInput(street1),
						
					ClpSerializer.translateInput(street2),
						
					ClpSerializer.translateInput(street3),
						
					ClpSerializer.translateInput(city),
						
					ClpSerializer.translateInput(zip),
						
					regionId,
						
					countryId,
						
					ClpSerializer.translateInput(profileDescriptionMap),
						
					ClpSerializer.translateInput(profileEmailAddress),
						
					ClpSerializer.translateInput(profileLogo),
						
					ClpSerializer.translateInput(profileWebsite),
						
					ClpSerializer.translateInput(dossieraAccountKey),
						
					ClpSerializer.translateInput(taxDocumentFileName),
						
					ClpSerializer.translateInput(taxDocumentFile),
						
					tosContractEntryId,
						
					developerContractEntryId,
						
					ClpSerializer.translateInput(serviceContext)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.osb.model.DeveloperEntry)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.osb.model.DeveloperEntry addUserDeveloperEntry(
		java.lang.String screenName, java.lang.String emailAddress,
		java.lang.String firstName, java.lang.String middleName,
		java.lang.String lastName, long contractEntryId, long countryId,
		java.lang.String phoneNumber,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName4,
					_methodParameterTypes4,
					new Object[] {
						ClpSerializer.translateInput(screenName),
						
					ClpSerializer.translateInput(emailAddress),
						
					ClpSerializer.translateInput(firstName),
						
					ClpSerializer.translateInput(middleName),
						
					ClpSerializer.translateInput(lastName),
						
					contractEntryId,
						
					countryId,
						
					ClpSerializer.translateInput(phoneNumber),
						
					ClpSerializer.translateInput(serviceContext)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.osb.model.DeveloperEntry)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.osb.model.DeveloperEntry updateCompanyDeveloperEntry(
		long developerEntryId, java.lang.String emailAddress,
		java.lang.String legalEntityName, java.lang.String phoneNumber,
		java.lang.String faxNumber, java.lang.String street1,
		java.lang.String street2, java.lang.String street3,
		java.lang.String city, java.lang.String zip, long regionId,
		long countryId, java.io.File profileLogo,
		java.util.Map<java.util.Locale, java.lang.String> profileDescriptionMap,
		java.lang.String profileEmailAddress, java.lang.String profileWebsite,
		java.lang.String taxDocumentFileName, java.io.File taxDocumentFile,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName5,
					_methodParameterTypes5,
					new Object[] {
						developerEntryId,
						
					ClpSerializer.translateInput(emailAddress),
						
					ClpSerializer.translateInput(legalEntityName),
						
					ClpSerializer.translateInput(phoneNumber),
						
					ClpSerializer.translateInput(faxNumber),
						
					ClpSerializer.translateInput(street1),
						
					ClpSerializer.translateInput(street2),
						
					ClpSerializer.translateInput(street3),
						
					ClpSerializer.translateInput(city),
						
					ClpSerializer.translateInput(zip),
						
					regionId,
						
					countryId,
						
					ClpSerializer.translateInput(profileLogo),
						
					ClpSerializer.translateInput(profileDescriptionMap),
						
					ClpSerializer.translateInput(profileEmailAddress),
						
					ClpSerializer.translateInput(profileWebsite),
						
					ClpSerializer.translateInput(taxDocumentFileName),
						
					ClpSerializer.translateInput(taxDocumentFile),
						
					ClpSerializer.translateInput(serviceContext)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.osb.model.DeveloperEntry)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.osb.model.DeveloperEntry updateDeveloperEntry(
		long developerEntryId, java.lang.String dossieraAccountKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName6,
					_methodParameterTypes6,
					new Object[] {
						developerEntryId,
						
					ClpSerializer.translateInput(dossieraAccountKey)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.osb.model.DeveloperEntry)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.osb.model.DeveloperEntry updateDeveloperEntry(
		long developerEntryId, java.lang.String domainName, int domainStatus,
		java.lang.String paymentFirstName, java.lang.String paymentLastName,
		java.lang.String paymentEmailAddress,
		java.util.Date subscriptionExpirationDate, int subscriptionStatus,
		double fatcaWithholdingPercentage,
		java.lang.String taxDocumentFileName, java.io.File taxDocumentFile)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName7,
					_methodParameterTypes7,
					new Object[] {
						developerEntryId,
						
					ClpSerializer.translateInput(domainName),
						
					domainStatus,
						
					ClpSerializer.translateInput(paymentFirstName),
						
					ClpSerializer.translateInput(paymentLastName),
						
					ClpSerializer.translateInput(paymentEmailAddress),
						
					ClpSerializer.translateInput(subscriptionExpirationDate),
						
					subscriptionStatus,
						
					fatcaWithholdingPercentage,
						
					ClpSerializer.translateInput(taxDocumentFileName),
						
					ClpSerializer.translateInput(taxDocumentFile)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.osb.model.DeveloperEntry)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.osb.model.DeveloperEntry updateDeveloperEntry(
		long developerEntryId, java.lang.String paymentFirstName,
		java.lang.String paymentLastName, java.lang.String paymentEmailAddress)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName8,
					_methodParameterTypes8,
					new Object[] {
						developerEntryId,
						
					ClpSerializer.translateInput(paymentFirstName),
						
					ClpSerializer.translateInput(paymentLastName),
						
					ClpSerializer.translateInput(paymentEmailAddress)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.osb.model.DeveloperEntry)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.osb.model.DeveloperEntry updateDeveloperEntryGoogleAnalyticsKey(
		long developerEntryId, java.lang.String googleAnalyticsKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName9,
					_methodParameterTypes9,
					new Object[] {
						developerEntryId,
						
					ClpSerializer.translateInput(googleAnalyticsKey)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.osb.model.DeveloperEntry)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.osb.model.DeveloperEntry updateStatus(
		long developerEntryId, int status, java.lang.String statusMessage)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName10,
					_methodParameterTypes10,
					new Object[] {
						developerEntryId,
						
					status,
						
					ClpSerializer.translateInput(statusMessage)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.osb.model.DeveloperEntry)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.osb.model.DeveloperEntry updateSubscription(
		long developerEntryId, java.util.Date subscriptionExpirationDate,
		int subscriptionStatus)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName11,
					_methodParameterTypes11,
					new Object[] {
						developerEntryId,
						
					ClpSerializer.translateInput(subscriptionExpirationDate),
						
					subscriptionStatus
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.osb.model.DeveloperEntry)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.osb.model.DeveloperEntry updateUserDeveloperEntry(
		long developerEntryId, java.lang.String firstName,
		java.lang.String middleName, java.lang.String lastName,
		java.lang.String legalEntityName, java.lang.String phoneNumber,
		java.lang.String street1, java.lang.String street2,
		java.lang.String street3, java.lang.String city, java.lang.String zip,
		long regionId, long countryId, java.lang.String taxDocumentFileName,
		java.io.File taxDocumentFile)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName12,
					_methodParameterTypes12,
					new Object[] {
						developerEntryId,
						
					ClpSerializer.translateInput(firstName),
						
					ClpSerializer.translateInput(middleName),
						
					ClpSerializer.translateInput(lastName),
						
					ClpSerializer.translateInput(legalEntityName),
						
					ClpSerializer.translateInput(phoneNumber),
						
					ClpSerializer.translateInput(street1),
						
					ClpSerializer.translateInput(street2),
						
					ClpSerializer.translateInput(street3),
						
					ClpSerializer.translateInput(city),
						
					ClpSerializer.translateInput(zip),
						
					regionId,
						
					countryId,
						
					ClpSerializer.translateInput(taxDocumentFileName),
						
					ClpSerializer.translateInput(taxDocumentFile)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.osb.model.DeveloperEntry)ClpSerializer.translateOutput(returnObj);
	}

	private InvokableService _invokableService;
	private String _methodName0;
	private String[] _methodParameterTypes0;
	private String _methodName1;
	private String[] _methodParameterTypes1;
	private String _methodName3;
	private String[] _methodParameterTypes3;
	private String _methodName4;
	private String[] _methodParameterTypes4;
	private String _methodName5;
	private String[] _methodParameterTypes5;
	private String _methodName6;
	private String[] _methodParameterTypes6;
	private String _methodName7;
	private String[] _methodParameterTypes7;
	private String _methodName8;
	private String[] _methodParameterTypes8;
	private String _methodName9;
	private String[] _methodParameterTypes9;
	private String _methodName10;
	private String[] _methodParameterTypes10;
	private String _methodName11;
	private String[] _methodParameterTypes11;
	private String _methodName12;
	private String[] _methodParameterTypes12;
}