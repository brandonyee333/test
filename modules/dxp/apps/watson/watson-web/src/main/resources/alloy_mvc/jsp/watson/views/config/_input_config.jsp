<%--
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
--%>

<script>
	var config = {
		inputConfig: {
			activities: {
				inputs: {
					activityResource: {
						dependentKey: '${WatsonListTypeConstants.ACTIVITY_TYPE_COURT_PROCEEDING}',
						filterable: ${false},
						inputType: 'MICRO_FORM',
						invertHidden: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("resources")}',
						parentInputId: 'typeWatsonListTypeId',
						translatable: ${false},
						type: 'DEPENDENT_KEYED_INPUT',
						validations: []
					},
					id: {
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("id")}',
						tooltipLabel: '',
						translatable: ${false},
						type: 'INPUT_VIEW',
						validations: []
					},
					narrative: {
						cssClass: 'rich-editor',
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("narrative")}',
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("provide-a-detailed-narrative-of-the-events-of-this-activity")}',
						translatable: ${true},
						type: 'RICH_TEXT_EDITOR',
						validations: [
							'required'
						]
					},
					reportDate: {
						filterable: ${true},
						htmlType: 'date',
						label: '${AlloyLanguageUtil.formatUnicode("date-reported")}',
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("provide-the-date-that-the-events-of-this-activity-were-reported-to-you")}',
						translatable: ${false},
						type: 'INPUT',
						validations: [
							'date',
							'required'
						]
					},
					startDate: {
						filterable: ${true},
						htmlType: 'date',
						label: '${AlloyLanguageUtil.formatUnicode("date-occurred")}',
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("provide-the-date-that-the-events-of-this-activity-occurred")}',
						translatable: ${false},
						type: 'INPUT',
						validations: [
							'date',
							'required'
						]
					},
					typeWatsonListTypeId: {
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("activity-type")}',
						options: {
							<c:set value='${WatsonListType.getWatsonListTypes(WatsonActivity.modelClassName.concat(".type"))}' var="watsonActivityTypes" />

							<c:forEach items="${watsonActivityTypes}" var="watsonActivityType" varStatus="watsonActivityTypeIndex">
								<c:set value='${watsonActivityTypeIndex.last ? "" : ","}' var="delimiter" />

								${watsonActivityType.watsonListTypeId}: {
									label: '${AlloyLanguageUtil.formatUnicode(watsonActivityType.getName(locale))}',
									value: '${watsonActivityType.watsonListTypeId}'
								}${delimiter}
							</c:forEach>
						},
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("provide-the-type-of-report-that-best-describes-this-activity")}',
						translatable: ${false},
						type: 'SELECT_INPUT',
						validations: [
							'required'
						]
					},
					subtypeWatsonListTypeId: {
						dependentKey: '${WatsonListTypeConstants.ACTIVITY_TYPE_COURT_PROCEEDING}',
						filterable: ${true},
						inputType: 'SELECT_INPUT',
						invertHidden: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("activity-sub-type")}',
						parentInputId: 'typeWatsonListTypeId',
						options: {
								<c:set value='${WatsonListType.getWatsonListTypes(WatsonActivity.modelClassName.concat(".subtype"))}' var="watsonActivitySubtypes" />

								<c:forEach items="${watsonActivitySubtypes}" var="watsonActivitySubtype" varStatus="watsonActivitySubtypeIndex">
									<c:set value='${watsonActivitySubtypeIndex.last ? "" : ","}' var="delimiter" />

									${watsonActivitySubtype.watsonListTypeId}: {
										label: '${AlloyLanguageUtil.formatUnicode(watsonActivitySubtype.getName(locale))}',
										value: '${watsonActivitySubtype.watsonListTypeId}'
									}${delimiter}
								</c:forEach>
						},
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("provide-the-type-of-report-that-best-describes-this-activity")}',
						translatable: ${false},
						type: 'DEPENDENT_KEYED_INPUT',
						validations: [
							'required'
						]
					},
					watsonRelationships: {
						buttonLabel: '${AlloyLanguageUtil.formatUnicode("add-relations")}',
						cssClass: 'triple-dynamic',
						fancy: ${false},
						filterable: ${false},
						label: '${AlloyLanguageUtil.formatUnicode("relationships")}',
						tooltipLabel: '',
						translatable: ${false},
						tripleOnly: ${true},
						type: 'DYNAMIC_RELATIONSHIP_INPUT_GENERATOR',
						validations: [
							'arrayValues4'
						]
					}
				},
				relationshipObjectOptions: {
					addresses: {
						key: '${ClassNameLocalService.getClassNameId(WatsonAddress.class)}',
							label: '${AlloyLanguageUtil.formatUnicode("addresses")}',
							options: {
							<c:set value='${WatsonListType.getWatsonListTypes(WatsonRelationship.modelClassName.concat(".addressActivityRelationshipTypes"))}' var="addressActivityTypes" />

							<c:forEach items="${addressActivityTypes}" var="addressActivityType" varStatus="addressActivityTypesIndex">
								<c:set value='${addressActivityTypesIndex.last ? "" : ","}' var="delimiter" />

								${addressActivityType.watsonListTypeId}: {
									label: '${AlloyLanguageUtil.formatUnicode(addressActivityType.getName(locale))}',
									value: '${addressActivityType.watsonListTypeId}'
								}${delimiter}
							</c:forEach>
						},
						value: 'addresses'
					},
					people: {
						key: '${ClassNameLocalService.getClassNameId(WatsonPerson.class)}',
							label: '${AlloyLanguageUtil.formatUnicode("people")}',
							options: {
							<c:set value='${WatsonListType.getWatsonListTypes(WatsonRelationship.modelClassName.concat(".activityPersonRelationshipTypes"))}' var="activityPersonTypes" />

							<c:forEach items="${activityPersonTypes}" var="activityPersonType" varStatus="activityPersonTypesIndex">
								<c:set value='${activityPersonTypesIndex.last ? "" : ","}' var="delimiter" />

								${activityPersonType.watsonListTypeId}: {
									label: '${AlloyLanguageUtil.formatUnicode(activityPersonType.getName(locale))}',
									value: '${activityPersonType.watsonListTypeId}'
								}${delimiter}
							</c:forEach>
						},
						value: 'people'
					},
					resources: {
						key: '${ClassNameLocalService.getClassNameId(WatsonResource.class)}',
							label: '${AlloyLanguageUtil.formatUnicode("resources")}',
							options: {
							<c:set value='${WatsonListType.getWatsonListTypes(WatsonRelationship.modelClassName.concat(".activityResourceRelationshipTypes"))}' var="addressResourceTypes" />

							<c:forEach items="${addressResourceTypes}" var="addressResourceType" varStatus="addressResourceTypesIndex">
								<c:set value='${addressResourceTypesIndex.last ? "" : ","}' var="delimiter" />

								${addressResourceType.watsonListTypeId}: {
									label: '${AlloyLanguageUtil.formatUnicode(addressResourceType.getName(locale))}',
									value: '${addressResourceType.watsonListTypeId}'
								}${delimiter}
							</c:forEach>
						},
						value: 'resources'
					}
				},
				pluralLabel: '${AlloyLanguageUtil.formatUnicode("activities")}',
				singularLabel: '${AlloyLanguageUtil.formatUnicode("activity")}',
				sortByDefault: 'watsonIncidentId',
				sortByOptions: {
					CREATED: {
						label: '${AlloyLanguageUtil.formatUnicode("created")}',
						value: 'createDate'
					},
					LAST_MODIFIED: {
						label: '${AlloyLanguageUtil.formatUnicode("last-modified")}',
						value: 'modifiedDate'
					},
					INCIDENT_ID: {
						label: '${AlloyLanguageUtil.formatUnicode("incident-id")}',
						value: 'watsonIncidentId'
					},
					DATE_OCCURRED: {
						label: '${AlloyLanguageUtil.formatUnicode("date-occurred")}',
						value: 'startDate'
					},
					REPORT_DATE: {
						label: '${AlloyLanguageUtil.formatUnicode("date-reported")}',
						value: 'reportDate'
					}
				}
			},
			addresses: {
				inputs: {
					building: {
						filterable: ${false},
						label: '${AlloyLanguageUtil.formatUnicode("building")}',
						translatable: ${true},
						type: 'INPUT',
						validations: []
					},
					countryId: {
						cssClass: 'columnize',
						defaultValue:
							<c:set value='${CountryServiceUtil.fetchCountryByA3("THA")}' var="thailandCountry" />

							'${thailandCountry.getPrimaryKey()}',
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("country")}',
						options: {
							<c:forEach items="${CountryServiceUtil.getCountries()}" var="country" varStatus="countryIndex">
								<c:set value='${countryIndex.last ? "" : ","}' var="delimiter" />

								${country.countryId}: {
									label: '${AlloyLanguageUtil.formatUnicode(country.getName(locale))}',
									value: '${country.countryId}'
								}${delimiter}
							</c:forEach>
						},
						sortOptions: ${true},
						tooltipLabel: '',
						translatable: ${false},
						type: 'SELECT_INPUT',
						validations: [
							'required'
						]
					},
					description: {
						filterable: ${true},
						cssClass: 'textarea',
						label: '${AlloyLanguageUtil.formatUnicode("description")}',
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("provide-a-brief-description-of-this-address-and-how-it-relates-to-the-incident")}',
						translatable: ${true},
						type: 'TEXT_AREA_INPUT',
						validations: []

					},
					districtWatsonListTypeId: {
						cssClass: 'columnize',
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("district")}',
						listTypeValue: '${WatsonAddress.modelClassName.concat(".district")}',
						options: {
							${0}: {
								label: '${AlloyLanguageUtil.formatUnicode("not-available")}',
								value: ${0}
							}
						},
						parentInputId: 'provinceWatsonListTypeId',
						translatable: ${false},
						type: 'DEPENDENT_SELECT_INPUT',
						validations: []
					},
					floor: {
						filterable: ${false},
						label: '${AlloyLanguageUtil.formatUnicode("floor")}',
						translatable: ${false},
						type: 'INPUT',
						validations: []
					},
					googleMap: {
						filterable: ${false},
						noLabel: ${true},
						translatable: ${false},
						tooltipLabel: '',
						type: 'GOOGLE_MAP',
						validations: []
					},
					id: {
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("id")}',
						tooltipLabel: '',
						translatable: ${false},
						type: 'INPUT_VIEW',
						validations: []
					},
					imagePayload: {
						acceptedTypes: 'image/*',
						enableCropperTool: ${true},
						filterable: ${false},
						label: '${AlloyLanguageUtil.formatUnicode("picture")}',
						multiple: ${false},
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("click-upload-a-picture-of-what-it-looks-like-at-this-address")}',
						translatable: ${false},
						type: 'FILE',
						uploaderLabel: '${AlloyLanguageUtil.formatUnicode("add-picture")}',
						validations: []
					},
					lastSeenDate: {
						controlledInputs: [],
						dependentKey: '${WatsonListTypeConstants.ADDRESS_TYPE_LAST_SEEN}',
						filterable: ${false},
						htmlType: 'date',
						invertHidden: ${true},
						label: '${AlloyLanguageUtil.formatUnicode('last-seen-date')}',
						parentInputId: 'typeWatsonListTypeId',
						translatable: ${false},
						type: 'DEPENDENT_KEYED_INPUT',
						validations: [
							'date',
							'required'
						]
					},
					latitude: {
						cssClass: 'columnize',
						filterable: ${false},
						htmlType: 'number',
						label: '${AlloyLanguageUtil.formatUnicode("latitude")}',
						translatable: ${false},
						type: 'INPUT',
						validations: []
					},
					longitude: {
						cssClass: 'columnize',
						filterable: ${false},
						htmlType: 'number',
						label: '${AlloyLanguageUtil.formatUnicode("longitude")}',
						translatable: ${false},
						type: 'INPUT',
						validations: []
					},
					name: {
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("name")}',
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("provide-a-name-that-describes-this-address")}',
						translatable: ${true},
						type: 'INPUT',
						validations: [
							'required'
						]
					},
					number: {
						filterable: ${true},
						htmlType: 'input',
						label: '${AlloyLanguageUtil.formatUnicode("number")}',
						translatable: ${false},
						type: 'INPUT',
						validations: []
					},
					postalCode: {
						cssClass: 'columnize',
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("postal-code")}',
						listTypeValue: '${WatsonAddress.modelClassName.concat(".zipCode")}',
						parentInputId: 'districtWatsonListTypeId',
						translatable: ${false},
						type: 'DEPENDENT_INPUT',
						validations: []
					},
					provinceWatsonListTypeId: {
						cssClass: 'columnize',
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("province")}',
						options: {
							<c:set value='${WatsonListType.getWatsonListTypes(WatsonAddress.modelClassName.concat(".province"))}' var="watsonProvinceAddresses" />

							<c:forEach items="${watsonProvinceAddresses}" var="watsonProvinceAddress" varStatus="watsonProvinceAddressesIndex">
								<c:set value='${watsonProvinceAddressesIndex.last ? "" : ","}' var="delimiter" />

								${watsonProvinceAddress.watsonListTypeId}: {
									label: '${AlloyLanguageUtil.formatUnicode(watsonProvinceAddress.getName(locale))}',
									value: '${watsonProvinceAddress.watsonListTypeId}'
								}${delimiter}
							</c:forEach>
						},
						translatable: ${false},
						type: 'SELECT_INPUT',
						validations: []
					},
					region: {
						controlledInputs: [
							'districtWatsonListTypeId',
							'provinceWatsonListTypeId',
							'subDistrictWatsonListTypeId'
						],
						cssClass: 'columnize',
						dependentKey:
							<c:set value='${CountryServiceUtil.fetchCountryByA3("THA")}' var="thailandCountry" />

							'${thailandCountry.getPrimaryKey()}',
						filterable: ${true},
						invertHidden: ${false},
						label: '${AlloyLanguageUtil.formatUnicode("region")}',
						parentInputId: 'countryId',
						translatable: ${true},
						type: 'DEPENDENT_KEYED_INPUT',
						validations: [
							'required'
						]
					},
					room: {
						filterable: ${false},
						label: '${AlloyLanguageUtil.formatUnicode("room")}',
						translatable: ${false},
						type: 'INPUT',
						validations: []
					},
					street: {
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("street")}',
						translatable: ${true},
						type: 'INPUT',
						validations: []
					},
					subDistrictWatsonListTypeId: {
						cssClass: 'columnize',
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("subdistrict")}',
						listTypeValue: '${WatsonAddress.modelClassName.concat(".subDistrict")}',
						options: {
							${0}: {
								label: '${AlloyLanguageUtil.formatUnicode("not-available")}',
								value: ${0}
							}
						},
						parentInputId: 'districtWatsonListTypeId',
						translatable: ${false},
						type: 'DEPENDENT_SELECT_INPUT'
					},
					typeWatsonListTypeId: {
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("type")}',
						options: {
							<c:set value='${WatsonListType.getWatsonListTypes(WatsonAddress.modelClassName.concat(".type"))}' var="watsonAddressTypes" />

							<c:forEach items="${watsonAddressTypes}" var="watsonAddressType" varStatus="watsonAddressTypeIndex">
								<c:set value='${watsonAddressTypeIndex.last ? "" : ","}' var="delimiter" />

								${watsonAddressType.watsonListTypeId}: {
									label: '${AlloyLanguageUtil.formatUnicode(watsonAddressType.getName(locale))}',
									value: '${watsonAddressType.watsonListTypeId}'
								}${delimiter}
							</c:forEach>
						},
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("provide-the-type-of-this-address")}',
						translatable: ${false},
						type: 'SELECT_INPUT',
						validations: [
							'required'
						]
					},
					watsonRelationships: {
						buttonLabel: '${AlloyLanguageUtil.formatUnicode("add-relations")}',
						cssClass: 'triple-dynamic',
						fancy: ${false},
						filterable: ${false},
						label: '${AlloyLanguageUtil.formatUnicode("relationships")}',
						tooltipLabel: '',
						translatable: ${false},
						tripleOnly: ${true},
						type: 'DYNAMIC_RELATIONSHIP_INPUT_GENERATOR',
						validations: [
							'arrayValues4'
						]
					}
				},
				miscInputs: {
					address: {
						filterable: ${false},
						htmlType: 'checkbox',
						label: '${AlloyLanguageUtil.formatUnicode("send-full-address")}',
						type: 'INPUT',
						validations: []
					},
					coordinates: {
						filterable: ${false},
						htmlType: 'checkbox',
						label: '${AlloyLanguageUtil.formatUnicode("send-coordinates")}',
						type: 'INPUT',
						validations: []
					},
					description: {
						filterable: ${false},
						cssClass: 'textarea',
						label: '${AlloyLanguageUtil.formatUnicode("notes")}',
						type: 'TEXT_AREA_INPUT',
						validations: []
					},
					emailAddress: {
						filterable: ${false},
						label: '${AlloyLanguageUtil.formatUnicode("email-address")}',
						type: 'INPUT',
						validations: [
							'emailAddress',
							'required'
						]
					}
				},
				relationshipObjectOptions: {
					activities: {
						key : '${ClassNameLocalService.getClassNameId(WatsonActivity.class)}',
						label: '${AlloyLanguageUtil.formatUnicode("activities")}',
						options: {
							<c:set value='${WatsonListType.getWatsonListTypes(WatsonRelationship.modelClassName.concat(".addressActivityRelationshipTypes"))}' var="addressActivityTypes" />

							<c:forEach items="${addressActivityTypes}" var="addressActivityType" varStatus="addressActivityTypesIndex">
								<c:set value='${addressActivityTypesIndex.last ? "" : ","}' var="delimiter" />

								${addressActivityType.watsonListTypeId}: {
									label: '${AlloyLanguageUtil.formatUnicode(addressActivityType.getName(locale))}',
									value: '${addressActivityType.watsonListTypeId}'
								}${delimiter}
							</c:forEach>
						},
						value: 'activities'
					},
					people: {
						key: '${ClassNameLocalService.getClassNameId(WatsonPerson.class)}',
						label: '${AlloyLanguageUtil.formatUnicode("people")}',
						options: {
							<c:set value='${WatsonListType.getWatsonListTypes(WatsonRelationship.modelClassName.concat(".addressPersonRelationshipTypes"))}' var="addressPersonTypes" />

							<c:forEach items="${addressPersonTypes}" var="addressPersonType" varStatus="addressPersonTypesIndex">
								<c:set value='${addressPersonTypesIndex.last ? "" : ","}' var="delimiter" />

								${addressPersonType.watsonListTypeId}: {
									label: '${AlloyLanguageUtil.formatUnicode(addressPersonType.getName(locale))}',
									value: '${addressPersonType.watsonListTypeId}'
								}${delimiter}
							</c:forEach>
						},
						value: 'people'
					},
					resources: {
						key: '${ClassNameLocalService.getClassNameId(WatsonResource.class)}',
						label: '${AlloyLanguageUtil.formatUnicode("resources")}',
						options: {
							<c:set value='${WatsonListType.getWatsonListTypes(WatsonRelationship.modelClassName.concat(".addressResourceRelationshipTypes"))}' var="addressResourceTypes" />

							<c:forEach items="${addressResourceTypes}" var="addressResourceType" varStatus="addressResourceTypesIndex">
								<c:set value='${addressResourceTypesIndex.last ? "" : ","}' var="delimiter" />

								${addressResourceType.watsonListTypeId}: {
									label: '${AlloyLanguageUtil.formatUnicode(addressResourceType.getName(locale))}',
									value: '${addressResourceType.watsonListTypeId}'
								}${delimiter}
							</c:forEach>
						},
						value: 'resources'
					},
					vehicles: {
						key: '${ClassNameLocalService.getClassNameId(WatsonVehicle.class)}',
						label: '${AlloyLanguageUtil.formatUnicode("vehicles")}',
						options: {
							<c:set value='${WatsonListType.getWatsonListTypes(WatsonRelationship.modelClassName.concat(".addressVehicleRelationshipTypes"))}' var="addressVehicleTypes" />

							<c:forEach items="${addressVehicleTypes}" var="addressVehicleType" varStatus="addressVehicleTypesIndex">
								<c:set value='${addressVehicleTypesIndex.last ? "" : ","}' var="delimiter" />

								${addressVehicleType.watsonListTypeId}: {
									label: '${AlloyLanguageUtil.formatUnicode(addressVehicleType.getName(locale))}',
									value: '${addressVehicleType.watsonListTypeId}'
								}${delimiter}
							</c:forEach>
						},
						value: 'vehicles'
					}
				},
				pluralLabel: '${AlloyLanguageUtil.formatUnicode("addresses")}',
				singularLabel: '${AlloyLanguageUtil.formatUnicode("address")}',
				sortByDefault: 'watsonIncidentId',
				sortByOptions: {
					INCIDENT_ID: {
						label: '${AlloyLanguageUtil.formatUnicode("incident-id")}',
						value: 'watsonIncidentId'
					},
					LAST_MODIFIED: {
						label: '${AlloyLanguageUtil.formatUnicode("last-modified")}',
						value: 'modifiedDate'
					},
					NAME: {
						label: '${AlloyLanguageUtil.formatUnicode("name")}',
						value: 'name'
					}
				}
			},
			children: {
				inputs: {
					activitiesInvolvedWatsonListTypeRels: {
						buttonLabel: '${AlloyLanguageUtil.formatUnicode("add-activities")}',
						filterable: ${false},
						label: '${AlloyLanguageUtil.formatUnicode("activities")}',
							options: {
							<c:set value='${WatsonListType.getWatsonListTypes(WatsonChild.modelClassName.concat(".activitiesInvolved"))}' var="activitiesInvolvedWatsonListTypes" />

							<c:forEach items="${activitiesInvolvedWatsonListTypes}" var="activitiesInvolvedWatsonListType" varStatus="activitiesInvolvedWatsonListTypesIndex">
								<c:set value='${activitiesInvolvedWatsonListTypesIndex.last ? "" : ","}' var="delimiter" />

								${activitiesInvolvedWatsonListType.watsonListTypeId}: {
									label: '${AlloyLanguageUtil.formatUnicode(activitiesInvolvedWatsonListType.getName(locale))}',
									value: '${activitiesInvolvedWatsonListType.watsonListTypeId}'
								}${delimiter}
							</c:forEach>
						},
						tooltipLabel: '',
						translatable: ${false},
						type: 'MULTI_SELECT_INPUT',
						validations: []
					},
					activitiesInvolvedWatsonListTypeRelString: {
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("activities")}',
						translatable: ${false},
						type: 'INPUT'
				    },
					birthCountryId: {
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("country-of-birth")}',
						options: {
							<c:forEach items="${CountryServiceUtil.getCountries()}" var="country" varStatus="countryIndex">
							<c:set value='${countryIndex.last ? "" : ","}' var="delimiter" />

							${country.countryId}: {
								label: '${AlloyLanguageUtil.formatUnicode(country.getName(locale))}',
								value: '${country.countryId}'
							}${delimiter}
							</c:forEach>
						},
						sortOptions: ${true},
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("provide-the-country-where-this-person-was-born")}',
						translatable: ${false},
						type: 'SELECT_INPUT',
							validations: []
					},
					createDate: {
						filterable: ${true},
						htmlType: 'date',
						label: '${AlloyLanguageUtil.formatUnicode("date-created")}',
						translatable: ${false},
						type: 'INPUT',
						validations: []
					},
					citizenshipWatsonListTypeId: {
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("citizenship")}',
						options: {
							<c:set value='${WatsonListType.getWatsonListTypes(WatsonPerson.modelClassName.concat(".citizenship"))}' var="citizenshipWatsonListTypes" />

							<c:forEach items="${citizenshipWatsonListTypes}" var="citizenshipWatsonListType" varStatus="citizenshipWatsonListTypesIndex">
								<c:set value='${citizenshipWatsonListTypesIndex.last ? "" : ","}' var="delimiter" />

								${citizenshipWatsonListType.watsonListTypeId}: {
									label: '${AlloyLanguageUtil.formatUnicode(citizenshipWatsonListType.getName(locale))}',
									value: '${citizenshipWatsonListType.watsonListTypeId}'
								}${delimiter}
							</c:forEach>
						},
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("provide-this-persons-citizenship")}',
						translatable: ${false},
						type: 'SELECT_INPUT',
						validations: []
					},
					countryIDWatsonListTypeRels: {
						buttonLabel: '${AlloyLanguageUtil.formatUnicode("add-id")}',
						filterable: ${false},
						label: '${AlloyLanguageUtil.formatUnicode("government-ids")}',
						options: {
							<c:set value='${WatsonListType.getWatsonListTypes(WatsonPerson.modelClassName.concat(".countryIDType"))}' var="countryIDWatsonListTypes" />

							<c:forEach items="${countryIDWatsonListTypes}" var="countryIDWatsonListType" varStatus="countryIDWatsonListTypesIndex">
								<c:set value='${countryIDWatsonListTypesIndex.last ? "" : ","}' var="delimiter" />

								${countryIDWatsonListType.watsonListTypeId}: {
									label: '${AlloyLanguageUtil.formatUnicode(countryIDWatsonListType.getName(locale))}',
									value: '${countryIDWatsonListType.watsonListTypeId}'
								}${delimiter}
							</c:forEach>
						},
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("provide-government-issued-ids-for-this-person")}',
						translatable: ${false},
						type: 'DOUBLE_DEPENDENT_INPUT',
						validations: []
					},
					countryIDWatsonListTypeRelString: {
						filterable: ${true},
						label: '${AlloyLanguageUtil.format("government-ids")}',
						translatable: ${false},
						type: 'INPUT'
					},
					countryWatsonListTypeId: {
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("country-of-ethnicity")}',
						options: {
							<c:set value='${WatsonListType.getWatsonListTypes(WatsonPerson.modelClassName.concat(".ethnicityCountry"))}' var="countryWatsonListTypes" />

							<c:forEach items="${countryWatsonListTypes}" var="countryWatsonListType" varStatus="countryWatsonListTypesIndex">
								<c:set value='${countryWatsonListTypesIndex.last ? "" : ","}' var="delimiter" />

								${countryWatsonListType.watsonListTypeId}: {
									label: '${AlloyLanguageUtil.formatUnicode(countryWatsonListType.getName(locale))}',
									value: '${countryWatsonListType.watsonListTypeId}'
								}${delimiter}
							</c:forEach>
						},
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("provide-the-country-of-ethnicity-for-this-person")}',
						translatable: ${false},
						type: 'SELECT_INPUT',
						validations: [
							'required'
						]
					},
					dateAccepted: {
						defaultValue: new Date().toISOString().substr(0,10),
						filterable: ${true},
						htmlType: 'date',
						label: '${AlloyLanguageUtil.formatUnicode('date-accepted-to-zoe')}',
						translatable: ${false},
						type: 'INPUT',
						validations: [
							'date'
						]
					},
					dateDischarged: {
						controlledInputs: [
							'dischargeWatsonListTypeId'
						],
						filterable: ${true},
						htmlType: 'date',
						label: '${AlloyLanguageUtil.formatUnicode('date-discharged')}',
						translatable: ${false},
						type: 'INPUT',
						validations: [
							'date'
						]
					},
					dateFollowUp: {
						filterable: ${true},
						htmlType: 'DATE',
						label: '${AlloyLanguageUtil.formatUnicode("follow-up")}',
						tooltipLabel: '',
						translatable: ${false},
						type: 'INPUT',
						validations: []
					},
					dischargeWatsonListTypeId: {
						dependentKey: ${true},
						filterable: ${true},
						inputType: 'SELECT_INPUT',
						label: '${AlloyLanguageUtil.formatUnicode("reason-for-discharge")}',
						options: {
							<c:set value='${WatsonListType.getWatsonListTypes(WatsonChild.modelClassName.concat(".dischargeType"))}' var="dischargeWatsonListTypes" />

							<c:forEach items="${dischargeWatsonListTypes}" var="dischargeWatsonListType" varStatus="dischargeWatsonListTypesIndex">
								<c:set value='${dischargeWatsonListTypesIndex.last ? "" : ","}' var="delimiter" />

								${dischargeWatsonListType.watsonListTypeId}: {
									label: '${AlloyLanguageUtil.formatUnicode(dischargeWatsonListType.getName(locale))}',
									value: '${dischargeWatsonListType.watsonListTypeId}'
								}${delimiter}
							</c:forEach>
						},
						parentInputId: 'dateDischarged',
						tooltipLabel: '',
						translatable: ${false},
						type: 'DEPENDENT_KEYED_INPUT',
						validations: [
							'required'
						]
					},
					ethnicityWatsonListTypeId: {
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("ethnicity")}',
						listTypeValue: '${WatsonPerson.modelClassName.concat(".ethnicityRegion")}',
						options: {
							<c:set value="${WatsonListType.fetch(WatsonListTypeConstants.PERSON_ETHNICITY_REGION_OTHER)}" var="ethnicityWatsonListTypeDefault" />

							${ethnicityWatsonListTypeDefault.watsonListTypeId}: {
								label: '${AlloyLanguageUtil.formatUnicode(ethnicityWatsonListTypeDefault.getName(locale))}',
								value: '${ethnicityWatsonListTypeDefault.watsonListTypeId}'
							}
						},
						parentInputId: 'countryWatsonListTypeId',
						showDefaultOptions: ${true},
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("if-the-country-of-ethnicity-is-thailand-or-burma-provide-a-more-specific-ethnicity")}',
						translatable: ${false},
						type: 'DEPENDENT_SELECT_INPUT',
						validations: []
					},
					guardianNameWatsonListTypeRels: {
						buttonLabel: '${AlloyLanguageUtil.formatUnicode("add-name")}',
						filterable: ${false},
						label: '${AlloyLanguageUtil.formatUnicode("guardian-names")}',
						options: {
							<c:set value='${WatsonListType.getWatsonListTypes(WatsonPerson.modelClassName.concat(".nameType"))}' var="nameTypeWatsonListTypes" />

							<c:forEach items="${nameTypeWatsonListTypes}" var="nameTypeWatsonListType" varStatus="nameTypeWatsonListTypesIndex">
								<c:set value='${nameTypeWatsonListTypesIndex.last ? "" : ","}' var="delimiter" />

								${nameTypeWatsonListType.watsonListTypeId}: {
									label: '${AlloyLanguageUtil.formatUnicode(nameTypeWatsonListType.getName(locale))}',
									value: '${nameTypeWatsonListType.watsonListTypeId}'
								}${delimiter}
							</c:forEach>
						},
						showToggle: true,
						tooltipLabel: '',
						translatable: ${false},
						type: 'DOUBLE_DEPENDENT_INPUT',
						validations: []
					},
					guardianNameWatsonListTypeRelString: {
						filterable: ${true},
						label: '${AlloyLanguageUtil.format("guardian-names")}',
						translatable: ${false},
						type: 'INPUT'
					},
					id: {
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("id")}',
						tooltipLabel: '',
						translatable: ${false},
						type: 'INPUT_VIEW',
						validations: []
					},
					modifiedDate: {
						filterable: ${true},
						htmlType: 'date',
						label: '${AlloyLanguageUtil.formatUnicode("date-modified")}',
						translatable: ${false},
						type: 'INPUT',
						validations: []
					},
					nameWatsonListTypeRels: {
						buttonLabel: '${AlloyLanguageUtil.formatUnicode("add-name")}',
						filterable: ${false},
						label: '${AlloyLanguageUtil.formatUnicode("names")}',
						options: {
							<c:set value='${WatsonListType.getWatsonListTypes(WatsonPerson.modelClassName.concat(".nameType"))}' var="nameTypeWatsonListTypes" />

							<c:forEach items="${nameTypeWatsonListTypes}" var="nameTypeWatsonListType" varStatus="nameTypeWatsonListTypesIndex">
								<c:set value='${nameTypeWatsonListTypesIndex.last ? "" : ","}' var="delimiter" />

								${nameTypeWatsonListType.watsonListTypeId}: {
									label: '${AlloyLanguageUtil.formatUnicode(nameTypeWatsonListType.getName(locale))}',
									value: '${nameTypeWatsonListType.watsonListTypeId}'
								}${delimiter}
							</c:forEach>
						},
						showToggle: true,
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("provide-one-or-more-names-for-this-person")}',
						translatable: ${false},
						type: 'DOUBLE_DEPENDENT_INPUT',
						validations: [
							'required'
						]
					},
					nameWatsonListTypeRelString: {
						filterable: ${true},
						label: '${AlloyLanguageUtil.format("names")}',
						translatable: ${false},
						type: 'INPUT'
					},
					parentNameWatsonListTypeRels: {
						buttonLabel: '${AlloyLanguageUtil.formatUnicode("add-name")}',
						filterable: ${false},
						label: '${AlloyLanguageUtil.formatUnicode("parent-names")}',
						options: {
							<c:set value='${WatsonListType.getWatsonListTypes(WatsonPerson.modelClassName.concat(".nameType"))}' var="nameTypeWatsonListTypes" />

							<c:forEach items="${nameTypeWatsonListTypes}" var="nameTypeWatsonListType" varStatus="nameTypeWatsonListTypesIndex">
								<c:set value='${nameTypeWatsonListTypesIndex.last ? "" : ","}' var="delimiter" />

								${nameTypeWatsonListType.watsonListTypeId}: {
									label: '${AlloyLanguageUtil.formatUnicode(nameTypeWatsonListType.getName(locale))}',
									value: '${nameTypeWatsonListType.watsonListTypeId}'
								}${delimiter}
							</c:forEach>
						},
						showToggle: true,
						tooltipLabel: '',
						translatable: ${false},
						type: 'DOUBLE_DEPENDENT_INPUT',
						validations: []
					},
					parentNameWatsonListTypeRelString: {
						filterable: ${true},
						label: '${AlloyLanguageUtil.format("parent-names")}',
						translatable: ${false},
						type: 'INPUT'
					},
					primaryNameString: {
						filterable: ${true},
						label: '${AlloyLanguageUtil.format("primary-name")}',
						translatable: ${false},
						type: 'INPUT'
					},
					sexWatsonListTypeId: {
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("gender")}',
						options: {
							<c:set value='${WatsonListType.getWatsonListTypes(WatsonPerson.modelClassName.concat(".sex"))}' var="sexWatsonListTypes" />

							<c:forEach items="${sexWatsonListTypes}" var="sexWatsonListType" varStatus="sexWatsonListTypesIndex">
								<c:set value='${sexWatsonListTypesIndex.last ? "" : ","}' var="delimiter" />

								${sexWatsonListType.watsonListTypeId}: {
									label: '${AlloyLanguageUtil.formatUnicode(sexWatsonListType.getName(locale))}',
									value: '${sexWatsonListType.watsonListTypeId}'
								}${delimiter}
							</c:forEach>
						},
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("provide-the-sex-gender-of-this-person")}',
						translatable: ${false},
						type: 'SELECT_INPUT',
						validations: [
							'required'
						]
					},
					sourceWatsonListTypeId: {
						controlledInputs: [
							'sourceSubtypeWatsonListTypeId',
							'source'
						],
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("sending-source")}',
							options: {
							<c:set value='${WatsonListType.getWatsonListTypes(WatsonChild.modelClassName.concat(".sourceType"))}' var="sourceSubtypesWatsonListTypes" />

							<c:forEach items="${sourceSubtypesWatsonListTypes}" var="sourceSubtypesWatsonListType" varStatus="sourceSubtypesWatsonListTypesIndex">
								<c:set value='${sourceSubtypesWatsonListTypesIndex.last ? "" : ","}' var="delimiter" />

								${sourceSubtypesWatsonListType.watsonListTypeId}: {
									label: '${AlloyLanguageUtil.formatUnicode(sourceSubtypesWatsonListType.getName(locale))}',
									value: '${sourceSubtypesWatsonListType.watsonListTypeId}'
								}${delimiter}
							</c:forEach>
						},
						tooltipLabel: '',
						translatable: ${false},
						type: 'SELECT_INPUT',
						validations: [
							'required'
						]
					},
					sourceSubtypeWatsonListTypeId: {
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("sending-source-sub-type")}',
						listTypeValue: '${WatsonChild.modelClassName.concat(".sourceSubtype")}',
						options: {
							<c:set value='${WatsonListType.getWatsonListTypes(WatsonChild.modelClassName.concat(".sourceSubtype"))}' var="sourceSubtypesWatsonListTypes" />

							<c:forEach items="${sourceSubtypesWatsonListTypes}" var="sourceSubtypesWatsonListType" varStatus="sourceSubtypesWatsonListTypesIndex">
								<c:set value='${sourceSubtypesWatsonListTypesIndex.last ? "" : ","}' var="delimiter" />

								${sourceSubtypesWatsonListType.watsonListTypeId}: {
									label: '${AlloyLanguageUtil.formatUnicode(sourceSubtypesWatsonListType.getName(locale))}',
									value: '${sourceSubtypesWatsonListType.watsonListTypeId}'
								}${delimiter}
							</c:forEach>
						},
						parentInputId: 'sourceWatsonListTypeId',
						tooltipLabel: '',
						translatable: ${false},
						type: 'DEPENDENT_SELECT_INPUT',
						validations: [
							'required'
						]
					},
					source: {
						controlledInputs: [],
						dependentKey: '${WatsonListTypeConstants.CHILD_SOURCE_TYPE_OTHER}',
						filterable: ${false},
						htmlType: 'input',
						invertHidden: ${true},
						label: '${AlloyLanguageUtil.formatUnicode('source')}',
						parentInputId: 'sourceTypeWatsonListTypeId',
						translatable: ${true},
						type: 'DEPENDENT_KEYED_INPUT',
						validations: [
							'required'
						]
					},
					typeWatsonListTypeId: {
						controlledInputs: [],
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("type-of-report")}',
						options: {
							<c:set value='${WatsonListType.getWatsonListTypes(WatsonIncident.modelClassName.concat(".type"))}' var="typeWatsonListTypes" />

							<c:forEach items="${typeWatsonListTypes}" var="typeWatsonListType" varStatus="typeWatsonListTypesIndex">
								<c:set value='${typeWatsonListTypesIndex.last ? "" : ","}' var="delimiter" />

								${typeWatsonListType.watsonListTypeId}: {
									label: '${AlloyLanguageUtil.formatUnicode(typeWatsonListType.getName(locale))}',
									value: '${typeWatsonListType.watsonListTypeId}'
								}${delimiter}
							</c:forEach>
						},
						tooltipLabel: '',
						translatable: ${false},
						type: 'SELECT_INPUT',
							validations: [
							'required'
						]
					},
					vocationalTrainingWatsonListTypeRels: {
						buttonLabel: '${AlloyLanguageUtil.formatUnicode("add-vocation")}',
						filterable: ${false},
						label: '${AlloyLanguageUtil.formatUnicode("vocational-training")}',
						options: {
							<c:set value='${WatsonListType.getWatsonListTypes(WatsonChild.modelClassName.concat(".vocationalTraining"))}' var="vocationalTrainingWatsonListTypes" />

							<c:forEach items="${vocationalTrainingWatsonListTypes}" var="vocationalTrainingWatsonListType" varStatus="vocationalTrainingWatsonListTypesIndex">
								<c:set value='${vocationalTrainingWatsonListTypesIndex.last ? "" : ","}' var="delimiter" />

								${vocationalTrainingWatsonListType.watsonListTypeId}: {
									label: '${AlloyLanguageUtil.formatUnicode(vocationalTrainingWatsonListType.getName(locale))}',
									value: '${vocationalTrainingWatsonListType.watsonListTypeId}'
								}${delimiter}
							</c:forEach>
						},
						tooltipLabel: '',
						translatable: ${false},
						type: 'MULTI_SELECT_INPUT',
						validations: []
					},
					vocationalTrainingWatsonListTypeRelString: {
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("vocational-training")}',
						translatable: ${false},
						type: 'INPUT'
					},
					watsonRelationships: {
						buttonLabel: '${AlloyLanguageUtil.formatUnicode("add-relations")}',
						cssClass: 'triple-dynamic',
						disabled: ${true},
						fancy: ${false},
						filterable: ${false},
						label: '${AlloyLanguageUtil.formatUnicode("relationships")}',
						tooltipLabel: '',
						translatable: ${false},
						tripleOnly: ${true},
						type: 'DYNAMIC_RELATIONSHIP_INPUT_GENERATOR',
						validations: [
							'arrayValues4'
						]
					}
				},
				relationshipObjectOptions: {},
				pluralLabel: '${AlloyLanguageUtil.formatUnicode("children")}',
				singularLabel: '${AlloyLanguageUtil.formatUnicode("child")}',
				sortByDefault: 'watsonChildId',
				sortByOptions: {
					CREATED: {
						label: '${AlloyLanguageUtil.formatUnicode("created")}',
						value: 'createDate'
					},
					INCIDENT_ID: {
						label: '${AlloyLanguageUtil.formatUnicode("child-id")}',
						value: 'watsonChildId'
					},
					LAST_MODIFIED: {
						label: '${AlloyLanguageUtil.formatUnicode("last-modified")}',
						value: 'modifiedDate'
					}
				},
				viewByOptions: {
					children: {
						label: '${AlloyLanguageUtil.formatUnicode("children")}',
						value: 'children'
					}
				}
			},
			histories: {
				inputs: {
					createDate: {
						filterable: ${false},
						htmlType: 'date',
						label: '${AlloyLanguageUtil.formatUnicode("date-created")}',
						translatable: ${false},
						type: 'INPUT',
						validations: []
					},
					modifiedDate: {
						filterable: ${false},
						htmlType: 'date',
						label: '${AlloyLanguageUtil.formatUnicode("date-modified")}',
						translatable: ${false},
						type: 'INPUT',
						validations: []
					},
					type: {
						filterable: ${false},
						label: '${AlloyLanguageUtil.formatUnicode("type")}',
							options: {
							'${WatsonHistoryConstants.HISTORY_TYPE_CREATED}': {
								label: '${AlloyLanguageUtil.formatUnicode("created")}',
								value: '${WatsonHistoryConstants.HISTORY_TYPE_CREATED}'
							},
							'${WatsonHistoryConstants.HISTORY_TYPE_DELETED}': {
								label: '${AlloyLanguageUtil.formatUnicode("deleted")}',
								value: '${WatsonHistoryConstants.HISTORY_TYPE_DELETED}'
							},
							'${WatsonHistoryConstants.HISTORY_TYPE_UPDATED}': {
								label: '${AlloyLanguageUtil.formatUnicode("updated")}',
								value: '${WatsonHistoryConstants.HISTORY_TYPE_UPDATED}'
							}
						},
						tooltipLabel: '',
						translatable: ${false},
						type: 'SELECT_INPUT',
						validations: []
					},
					watsonModel: {
						filterable: ${false},
						label: '${AlloyLanguageUtil.formatUnicode("model")}',
						options: {},
						tooltipLabel: '',
						translatable: ${false},
						type: 'SELECT_INPUT',
						validations: []
					}
				},
				pluralLabel: '${AlloyLanguageUtil.formatUnicode("histories")}',
				singularLabel: '${AlloyLanguageUtil.formatUnicode("history")}',
				sortByDefault: 'watsonIncidentId',
				sortByOptions: {
					INCIDENT_ID: {
						label: '${AlloyLanguageUtil.formatUnicode("incident-id")}',
						value: 'watsonIncidentId'
					},
					LAST_MODIFIED: {
						label: '${AlloyLanguageUtil.formatUnicode("last-modified")}',
						value: 'modifiedDate'
					},
					REPORT_DATE: {
						label: '${AlloyLanguageUtil.formatUnicode("report-date")}',
						value: 'reportDate'
					}
				}
			},
			incidents: {
				inputs: {
					audienceKey: {
						controlledInputs: [],
						dependentKey: '${WatsonListTypeConstants.INCIDENT_TYPE_PREVENTION_OUTREACH}',
						filterable: ${false},
						htmlType: 'input',
						invertHidden: ${true},
						label: '${AlloyLanguageUtil.formatUnicode('audience-number')}',
						parentInputId: 'typeWatsonListTypeId',
						translatable: ${false},
						type: 'DEPENDENT_KEYED_INPUT',
						validations: [
							'required'
						]
					},
					createDate: {
						filterable: ${false},
						htmlType: 'date',
						label: '${AlloyLanguageUtil.formatUnicode("date-created")}',
						translatable: ${false},
						type: 'INPUT',
						validations: []
					},
					description: {
						cssClass: 'textarea',
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("description")}',
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("a-brief-description-of-the-events-of-this-incident")}',
						translatable: ${true},
						type: 'TEXT_AREA_INPUT',
						validations: [
							'required'
						]
					},
					endDate: {
						filterable: ${true},
						htmlType: 'date',
						label: '${AlloyLanguageUtil.formatUnicode("date-ended")}',
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("provide-the-date-that-the-events-of-this-incident-ended")}',
						translatable: ${false},
						type: 'INPUT',
						validations: [
							'date'
						]
					},
					externalCaseId: {
						filterable: ${true},
						htmlType: 'input',
						label: '${AlloyLanguageUtil.formatUnicode("ticac-id")}',
						tooltipLabel: '',
						translatable: ${false},
						type: 'INPUT',
						validations: []
					},
					incidentStatus: {
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("status")}',
						options: {
							'${WatsonIncidentConstants.INCIDENT_STATUS_CLOSED}': {
								label: '${AlloyLanguageUtil.formatUnicode("closed")}',
								value: '${WatsonIncidentConstants.INCIDENT_STATUS_CLOSED}'
							},
							'${WatsonIncidentConstants.INCIDENT_STATUS_INACTIVE}': {
								label: '${AlloyLanguageUtil.formatUnicode("inactive")}',
								value: '${WatsonIncidentConstants.INCIDENT_STATUS_INACTIVE}'
							},
							'${WatsonIncidentConstants.INCIDENT_STATUS_OPEN}': {
								label: '${AlloyLanguageUtil.formatUnicode("open")}',
								value: '${WatsonIncidentConstants.INCIDENT_STATUS_OPEN}'
							}
						},
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("provide-the-status-of-the-current-incident")}',
						translatable: ${false},
						type: 'SELECT_INPUT',
						validations: [
							'required'
						]
					},
					modifiedDate: {
						filterable: ${true},
						htmlType: 'date',
						label: '${AlloyLanguageUtil.formatUnicode("date-modified")}',
						translatable: ${false},
						type: 'INPUT',
						validations: []
					},
					name: {
						disabled: ${true},
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("incident-id")}',
						translatable: ${false},
						type: 'INPUT',
						validations: []
					},
					natureWatsonListType: {
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("nature")}',
						options: {
							<c:set value='${WatsonListType.getWatsonListTypes(WatsonIncident.modelClassName.concat(".nature"))}' var="watsonIncidentNatures" />

							<c:forEach items="${watsonIncidentNatures}" var="watsonIncidentNature" varStatus="watsonIncidentNatureIndex">
								<c:set value='${watsonIncidentNatureIndex.last ? "" : ","}' var="delimiter" />

								${watsonIncidentNature.watsonListTypeId}: {
									label: '${AlloyLanguageUtil.formatUnicode(watsonIncidentNature.getName(locale))}',
									value: '${watsonIncidentNature.watsonListTypeId}'
								}${delimiter}
							</c:forEach>
						},
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("provide-the-nature-of-the-incident")}',
						translatable: ${false},
						type: 'MULTI_SELECT_INPUT',
						validations: [
							'required'
						]
					},
					reportDate: {
						filterable: ${true},
						htmlType: 'date',
						label: '${AlloyLanguageUtil.formatUnicode("incident-date-reported")}',
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("provide-the-date-that-the-events-of-this-incident-were-reported-to-you")}',
						translatable: ${false},
						type: 'INPUT',
						validations: [
							'date',
							'required'
						]
					},
					reportedBy: {
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("reported-by")}',
						translatable: ${false},
						type: 'INPUT',
						validations: []
					},
					sourceWatsonListTypeId: {
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("source")}',
						options: {
							<c:set value='${WatsonListType.getWatsonListTypes(WatsonIncident.modelClassName.concat(".source"))}' var="watsonIncidentSources" />

							<c:forEach items="${watsonIncidentSources}" var="watsonIncidentSource" varStatus="watsonIncidentSourceIndex">
								<c:set value='${watsonIncidentSourceIndex.last ? "" : ","}' var="delimiter" />

								${watsonIncidentSource.watsonListTypeId}: {
									label: '${AlloyLanguageUtil.formatUnicode(watsonIncidentSource.getName(locale))}',
									value: '${watsonIncidentSource.watsonListTypeId}'
								}${delimiter}
							</c:forEach>
						},
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("provide-the-source-from-which-the-details-of-this-incident-originated")}',
						translatable: ${false},
						type: 'SELECT_INPUT',
						validations: [
							'required'
						]
					},
					startDate: {
						filterable: ${true},
						htmlType: 'date',
						label: '${AlloyLanguageUtil.formatUnicode("incident-date-started")}',
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("provide-the-date-that-the-events-of-this-incident-started")}',
						translatable: ${false},
						type: 'INPUT',
						validations: [
							'date',
							'required'
						]
					},
					typeWatsonListTypeId: {
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("type")}',
						options: {
							<c:set value='${WatsonListType.getWatsonListTypes(WatsonIncident.modelClassName.concat(".type"))}' var="watsonIncidentTypes" />

							<c:forEach items="${watsonIncidentTypes}" var="watsonIncidentType" varStatus="watsonIncidentTypeIndex">
								<c:set value='${watsonIncidentTypeIndex.last ? "" : ","}' var="delimiter" />

								${watsonIncidentType.watsonListTypeId}: {
									label: '${AlloyLanguageUtil.formatUnicode(watsonIncidentType.getName(locale))}',
									value: '${watsonIncidentType.watsonListTypeId}'
								}${delimiter}
							</c:forEach>
						},
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("provide-the-type-of-report-that-best-describes-this-incident")}',
						translatable: ${false},
						type: 'SELECT_INPUT',
						validations: [
							'required'
						]
					},
					subtypeWatsonListTypeId: {
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("type-of-report")}',
						listTypeValue: '${WatsonIncident.modelClassName.concat(".subtype")}',
						options: {
							<c:set value='${WatsonListType.getWatsonListTypes(WatsonIncident.modelClassName.concat(".subtype"))}' var="watsonIncidentSubtypes" />

							<c:forEach items="${watsonIncidentSubtypes}" var="watsonIncidentSubtype" varStatus="watsonIncidentSubtypeIndex">
								<c:set value='${watsonIncidentSubtypeIndex.last ? "" : ","}' var="delimiter" />

								${watsonIncidentSubtype.watsonListTypeId}: {
									label: '${AlloyLanguageUtil.formatUnicode(watsonIncidentSubtype.getName(locale))}',
									value: '${watsonIncidentSubtype.watsonListTypeId}'
								}${delimiter}
							</c:forEach>
						},
						parentInputId: 'typeWatsonListTypeId',
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("provide-the-type-of-report-that-best-describes-this-incident")}',
						translatable: ${false},
						type: 'DEPENDENT_SELECT_INPUT',
							validations: [
							'required'
						]
					},
					watsonIncidentRelIds: {
						buttonLabel: '${AlloyLanguageUtil.formatUnicode("add-relations")}',
						cssClass: 'dynamic',
						fancy: ${true},
						filterable: ${false},
						label: '${AlloyLanguageUtil.formatUnicode("relationships")}',
						tooltipLabel: '',
						translatable: ${false},
						type: 'DYNAMIC_RELATIONSHIP_INPUT_GENERATOR',
						validations: []
					}
				},
				pluralLabel: '${AlloyLanguageUtil.formatUnicode("incidents")}',
				singularLabel: '${AlloyLanguageUtil.formatUnicode("incident")}',
				sortByDefault: 'name',
				sortByOptions: {
					NAME: {
						label: '${AlloyLanguageUtil.formatUnicode("incident-id")}',
						value: 'name'
					},
					LAST_MODIFIED: {
						label: '${AlloyLanguageUtil.formatUnicode("last-modified")}',
						value: 'modifiedDate'
					},
					REPORT_DATE: {
						label: '${AlloyLanguageUtil.formatUnicode("report-date")}',
						value: 'reportDate'
					},
					STATUS: {
						label: '${AlloyLanguageUtil.formatUnicode("status")}',
						value: 'incidentStatus'
					}
				},
				viewByOptions: {
					activities: {
						label: '${AlloyLanguageUtil.formatUnicode("activities")}',
						value: 'activities'
					},
					addresses: {
						label: '${AlloyLanguageUtil.formatUnicode("addresses")}',
						value: 'addresses'
					},
					incidents: {
						label: '${AlloyLanguageUtil.formatUnicode("incidents")}',
						value: 'incidents'
					},
					people: {
						label: '${AlloyLanguageUtil.formatUnicode("people")}',
						value: 'people'
					},
					resources: {
						label: '${AlloyLanguageUtil.formatUnicode("resources")}',
						value: 'resources'
					},
					vehicles: {
						label: '${AlloyLanguageUtil.formatUnicode("vehicles")}',
						value: 'vehicles'
					}
				}
			},
			inputTypes: {
				dependentKeyedInput: 'DEPENDENT_KEYED_INPUT',
				dependentInput: 'DEPENDENT_INPUT',
				dependentSelectInput: 'DEPENDENT_SELECT_INPUT',
				doubleDependentInput: 'DOUBLE_DEPENDENT_INPUT',
				dynamicInputGenerator: 'DYNAMIC_INPUT_GENERATOR',
				dynamicRelationshipInputGenerator: 'DYNAMIC_RELATIONSHIP_INPUT_GENERATOR',
				file: 'FILE',
				googleMap : 'GOOGLE_MAP',
				input: 'INPUT',
				inputView: 'INPUT_VIEW',
				microForm: 'MICRO_FORM',
				multiSelectInput: 'MULTI_SELECT_INPUT',
				richTextEditor: 'RICH_TEXT_EDITOR',
				selectInput: 'SELECT_INPUT',
				textareaInput: 'TEXT_AREA_INPUT'
			},
			metrics: {
				heatmaps: {
					inputs: {
						startDate: {
							filterable: ${true},
							htmlType: 'date',
							label: '${AlloyLanguageUtil.formatUnicode("date-range")}',
							type: 'INPUT'
						},
						natureWatsonListType: {
							filterable: ${true},
							label: '${AlloyLanguageUtil.formatUnicode("nature")}',
							options: {
								<c:set value='${WatsonListType.getWatsonListTypes(WatsonIncident.modelClassName.concat(".nature"))}' var="watsonIncidentNatures" />

								<c:forEach items="${watsonIncidentNatures}" var="watsonIncidentNature" varStatus="watsonIncidentNatureIndex">
									<c:set value='${watsonIncidentNatureIndex.last ? "" : ","}' var="delimiter" />

									${watsonIncidentNature.watsonListTypeId}: {
										label: '${AlloyLanguageUtil.formatUnicode(watsonIncidentNature.getName(locale))}',
										value: '${watsonIncidentNature.watsonListTypeId}'
									}${delimiter}
								</c:forEach>
							},
							type: 'MULTI_SELECT_INPUT'
						},
						typeWatsonListTypeId: {
							filterable: ${true},
							label: '${AlloyLanguageUtil.formatUnicode("incident-type")}',
							options: {
								<c:set value='${WatsonListType.getWatsonListTypes(WatsonIncident.modelClassName.concat(".type"))}' var="watsonIncidentTypes" />

								<c:forEach items="${watsonIncidentTypes}" var="watsonIncidentType" varStatus="watsonIncidentTypeIndex">
									<c:set value='${watsonIncidentTypeIndex.last ? "" : ","}' var="delimiter" />

									${watsonIncidentType.watsonListTypeId}: {
										label: '${AlloyLanguageUtil.formatUnicode(watsonIncidentType.getName(locale))}',
										value: '${watsonIncidentType.watsonListTypeId}'
									}${delimiter}
								</c:forEach>
							},
							tooltipLabel: '${AlloyLanguageUtil.formatUnicode("provide-the-type-of-report-that-best-describes-this-incident")}',
							translatable: ${false},
							type: 'SELECT_INPUT',
								validations: [
								'required'
							]
						},
						subtypeWatsonListTypeId: {
							filterable: ${true},
							label: '${AlloyLanguageUtil.formatUnicode("type-of-report")}',
							listTypeValue: '${WatsonIncident.modelClassName.concat(".subtype")}',
							options: {
								<c:set value='${WatsonListType.getWatsonListTypes(WatsonIncident.modelClassName.concat(".subtype"))}' var="watsonIncidentSubtypes" />

								<c:forEach items="${watsonIncidentSubtypes}" var="watsonIncidentSubtype" varStatus="watsonIncidentSubtypeIndex">
									<c:set value='${watsonIncidentSubtypeIndex.last ? "" : ","}' var="delimiter" />

									${watsonIncidentSubtype.watsonListTypeId}: {
										label: '${AlloyLanguageUtil.formatUnicode(watsonIncidentSubtype.getName(locale))}',
										value: '${watsonIncidentSubtype.watsonListTypeId}'
									}${delimiter}
								</c:forEach>
							},
							type: 'SELECT_INPUT'
						}
					}
				},
				reports: {
					types: [
						{
							key: 'rescued',
							label: '${AlloyLanguageUtil.formatUnicode('rescued')}',
							modelKey: 'person'
						},
						{
							key: 'accepted',
							label: '${AlloyLanguageUtil.formatUnicode('accepted-to-zoe')}',
							modelKey: 'person'
						}
					]
				}
			},
			people: {
				inputs: {
					accepted: {
						controlledInputs: [
							'dateAccepted'
						],
						dependentKey: '${WatsonListTypeConstants.PERSON_TYPE_VICTIM}',
						filterable: ${false},
						htmlType: 'checkbox',
						invertHidden: ${true},
						label: '${AlloyLanguageUtil.formatUnicode('accepted-to-zoe')}',
						parentInputId: 'typeWatsonListTypeId',
						translatable: ${false},
						type: 'DEPENDENT_KEYED_INPUT'
					},
					birthCountryId: {
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("country-of-birth")}',
						options: {
							<c:forEach items="${CountryServiceUtil.getCountries()}" var="country" varStatus="countryIndex">
								<c:set value='${countryIndex.last ? "" : ","}' var="delimiter" />

								${country.countryId}: {
									label: '${AlloyLanguageUtil.formatUnicode(country.getName(locale))}',
									value: '${country.countryId}'
								}${delimiter}
							</c:forEach>
						},
						sortOptions: ${true},
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("provide-the-country-where-this-person-was-born")}',
						translatable: ${false},
						type: 'SELECT_INPUT',
						validations: []
					},
					birthDate: {
						filterable: ${true},
						htmlType: 'date',
						label: '${AlloyLanguageUtil.formatUnicode("date-of-birth")}',
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("provide-the-birth-date-of-this-person-if-available")}',
						translatable: ${false},
						type: 'INPUT',
						validations: [
							'date'
						]
					},
					createDate: {
						filterable: ${true},
						htmlType: 'date',
						label: '${AlloyLanguageUtil.formatUnicode("date-created")}',
						translatable: ${false},
						type: 'INPUT',
						validations: []
					},
					citizenshipWatsonListTypeId: {
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("citizenship")}',
						options: {
							<c:set value='${WatsonListType.getWatsonListTypes(WatsonPerson.modelClassName.concat(".citizenship"))}' var="citizenshipWatsonListTypes" />

							<c:forEach items="${citizenshipWatsonListTypes}" var="citizenshipWatsonListType" varStatus="citizenshipWatsonListTypesIndex">
								<c:set value='${citizenshipWatsonListTypesIndex.last ? "" : ","}' var="delimiter" />

								${citizenshipWatsonListType.watsonListTypeId}: {
									label: '${AlloyLanguageUtil.formatUnicode(citizenshipWatsonListType.getName(locale))}',
									value: '${citizenshipWatsonListType.watsonListTypeId}'
								}${delimiter}
							</c:forEach>
						},
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("provide-this-persons-citizenship")}',
						translatable: ${false},
						type: 'SELECT_INPUT',
						validations: []
					},
					countryIDWatsonListTypeRels: {
						buttonLabel: '${AlloyLanguageUtil.formatUnicode("add-id")}',
						filterable: ${false},
						label: '${AlloyLanguageUtil.formatUnicode("government-ids")}',
						options: {
							<c:set value='${WatsonListType.getWatsonListTypes(WatsonPerson.modelClassName.concat(".countryIDType"))}' var="countryIDWatsonListTypes" />

							<c:forEach items="${countryIDWatsonListTypes}" var="countryIDWatsonListType" varStatus="countryIDWatsonListTypesIndex">
								<c:set value='${countryIDWatsonListTypesIndex.last ? "" : ","}' var="delimiter" />

								${countryIDWatsonListType.watsonListTypeId}: {
									label: '${AlloyLanguageUtil.formatUnicode(countryIDWatsonListType.getName(locale))}',
									value: '${countryIDWatsonListType.watsonListTypeId}'
								}${delimiter}
							</c:forEach>
						},
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("provide-government-issued-ids-for-this-person")}',
						translatable: ${false},
						type: 'DOUBLE_DEPENDENT_INPUT',
						validations: []
					},
					countryIDWatsonListTypeRelString: {
						filterable: ${true},
						label: '${AlloyLanguageUtil.format("government-ids")}',
						translatable: ${false},
						type: 'INPUT'
					},
					countryWatsonListTypeId: {
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("country-of-ethnicity")}',
						options: {
							<c:set value='${WatsonListType.getWatsonListTypes(WatsonPerson.modelClassName.concat(".ethnicityCountry"))}' var="countryWatsonListTypes" />

							<c:forEach items="${countryWatsonListTypes}" var="countryWatsonListType" varStatus="countryWatsonListTypesIndex">
								<c:set value='${countryWatsonListTypesIndex.last ? "" : ","}' var="delimiter" />

								${countryWatsonListType.watsonListTypeId}: {
									label: '${AlloyLanguageUtil.formatUnicode(countryWatsonListType.getName(locale))}',
									value: '${countryWatsonListType.watsonListTypeId}'
								}${delimiter}
							</c:forEach>
						},
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("provide-the-country-of-ethnicity-for-this-person")}',
						translatable: ${false},
						type: 'SELECT_INPUT',
						validations: [
							'required'
						]
					},
					dateAccepted: {
						controlledInputs: [],
						defaultValue: new Date().toISOString().substr(0,10),
						dependentKey: ${true},
						filterable: ${true},
						htmlType: 'date',
						invertHidden: ${true},
						label: '${AlloyLanguageUtil.formatUnicode('date-accepted-to-zoe')}',
						parentInputId: 'accepted',
						translatable: ${false},
						type: 'DEPENDENT_KEYED_INPUT',
						validations: [
							'date',
							'required'
						]
					},
					dateRescued: {
						controlledInputs: [],
						defaultValue: new Date().toISOString().substr(0,10),
						dependentKey: ${true},
						filterable: ${true},
						htmlType: 'date',
						invertHidden: ${true},
						label: '${AlloyLanguageUtil.formatUnicode('date-rescued')}',
						parentInputId: 'rescued',
						translatable: ${false},
						type: 'DEPENDENT_KEYED_INPUT',
						validations: [
							'date',
							'required'
						]
					},
					description: {
						cssClass: 'textarea',
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("comments")}',
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("provide-any-additional-comments-about-this-person")}',
						translatable: ${true},
						type: 'TEXT_AREA_INPUT',
						validations: []
					},
					endAge: {
						filterable: ${true},
						htmlType: 'number',
						label: '${AlloyLanguageUtil.formatUnicode("age-range-end")}',
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("if-the-birth-date-of-this-person-is-not-available-provide-the-oldest-age-this-person-could-be")}',
						translatable: ${false},
						type: 'INPUT',
						validations: [
							'integer'
						]
					},
					ethnicityWatsonListTypeId: {
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("ethnicity")}',
						listTypeValue: '${WatsonPerson.modelClassName.concat(".ethnicityRegion")}',
						options: {
							<c:set value="${WatsonListType.fetch(WatsonListTypeConstants.PERSON_ETHNICITY_REGION_OTHER)}" var="ethnicityWatsonListTypeDefault" />

							${ethnicityWatsonListTypeDefault.watsonListTypeId}: {
								label: '${AlloyLanguageUtil.formatUnicode(ethnicityWatsonListTypeDefault.getName(locale))}',
								value: '${ethnicityWatsonListTypeDefault.watsonListTypeId}'
							}
						},
						parentInputId: 'countryWatsonListTypeId',
						showDefaultOptions: ${true},
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("if-the-country-of-ethnicity-is-thailand-or-burma-provide-a-more-specific-ethnicity")}',
						translatable: ${false},
						type: 'DEPENDENT_SELECT_INPUT',
						validations : []
					},
					eyesWatsonListTypeId: {
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("eyes")}',
						options: {
							<c:set value='${WatsonListType.getWatsonListTypes(WatsonPerson.modelClassName.concat(".eyeColor"))}' var="eyesWatsonListTypes" />

							<c:forEach items="${eyesWatsonListTypes}" var="eyesWatsonListType" varStatus="eyesWatsonListTypesIndex">
								<c:set value='${eyesWatsonListTypesIndex.last ? "" : ","}' var="delimiter" />

								${eyesWatsonListType.watsonListTypeId}: {
									label: '${AlloyLanguageUtil.formatUnicode(eyesWatsonListType.getName(locale))}',
									value: '${eyesWatsonListType.watsonListTypeId}'
								}${delimiter}
							</c:forEach>
						},
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("provide-the-eye-color-of-this-person")}',
						translatable: ${false},
						type: 'SELECT_INPUT',
						validations: []
					},
					hairWatsonListTypeId: {
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("hair")}',
						options: {
							<c:set value='${WatsonListType.getWatsonListTypes(WatsonPerson.modelClassName.concat(".hairColor"))}' var="hairWatsonListTypes" />

							<c:forEach items="${hairWatsonListTypes}" var="hairWatsonListType" varStatus="hairWatsonListTypesIndex">
								<c:set value='${hairWatsonListTypesIndex.last ? "" : ","}' var="delimiter" />

								${hairWatsonListType.watsonListTypeId}: {
									label: '${AlloyLanguageUtil.formatUnicode(hairWatsonListType.getName(locale))}',
									value: '${hairWatsonListType.watsonListTypeId}'
								}${delimiter}
							</c:forEach>
						},
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("provide-the-hair-color-of-this-person")}',
						translatable: ${false},
						type: 'SELECT_INPUT',
						validations: []
					},
					height: {
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("height")}',
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("provide-the-height-of-this-person-in-centimeters")}',
						translatable: ${false},
						type: 'INPUT',
						validations: []
					},
					id: {
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("id")}',
						tooltipLabel: '',
						translatable: ${false},
						type: 'INPUT_VIEW',
						validations: []
					},
					imagePayload: {
						acceptedTypes: 'image/*',
						enableCropperTool: ${true},
						filterable: ${false},
						label: '${AlloyLanguageUtil.formatUnicode("picture")}',
						multiple: ${false},
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("click-add-picture-or-drag-an-image-file-onto-it-to-upload-a-picture-of-this-person")}',
						translatable: ${false},
						type: 'FILE',
						uploaderLabel: '${AlloyLanguageUtil.formatUnicode("add-picture")}',
						validations: []
					},
					modifiedDate: {
						filterable: ${true},
						htmlType: 'date',
						label: '${AlloyLanguageUtil.formatUnicode("date-modified")}',
						translatable: ${false},
						type: 'INPUT',
						validations: []
					},
					nameWatsonListTypeRels: {
						buttonLabel: '${AlloyLanguageUtil.formatUnicode("add-name")}',
						filterable: ${false},
						label: '${AlloyLanguageUtil.formatUnicode("names")}',
						options: {
							<c:set value='${WatsonListType.getWatsonListTypes(WatsonPerson.modelClassName.concat(".nameType"))}' var="nameTypeWatsonListTypes" />

							<c:forEach items="${nameTypeWatsonListTypes}" var="nameTypeWatsonListType" varStatus="nameTypeWatsonListTypesIndex">
								<c:set value='${nameTypeWatsonListTypesIndex.last ? "" : ","}' var="delimiter" />

								${nameTypeWatsonListType.watsonListTypeId}: {
									label: '${AlloyLanguageUtil.formatUnicode(nameTypeWatsonListType.getName(locale))}',
									value: '${nameTypeWatsonListType.watsonListTypeId}'
								}${delimiter}
							</c:forEach>
						},
						showToggle: true,
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("provide-one-or-more-names-for-this-person")}',
						translatable: ${false},
						type: 'DOUBLE_DEPENDENT_INPUT',
						validations: [
							'required'
						]
					},
					nameWatsonListTypeRelString: {
						filterable: ${true},
						label: '${AlloyLanguageUtil.format("names")}',
						translatable: ${false},
						type: 'INPUT'
					},
					occupation: {
						disabled: ${false},
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("occupation")}',
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("provide-the-occupation-of-this-person")}',
						translatable: ${true},
						type: 'INPUT',
						validations: []
					},
					phoneNumberWatsonListTypeRels: {
						buttonLabel: '${AlloyLanguageUtil.formatUnicode("add-phone-number")}',
						filterable: ${false},
						label: '${AlloyLanguageUtil.formatUnicode("phone-numbers")}',
						options: {
							<c:set value='${WatsonListType.getWatsonListTypes(WatsonPerson.modelClassName.concat(".phoneNumberType"))}' var="phoneNumberWatsonListTypes" />

							<c:forEach items="${phoneNumberWatsonListTypes}" var="phoneNumberWatsonListType" varStatus="phoneNumberWatsonListTypesIndex">
								<c:set value='${phoneNumberWatsonListTypesIndex.last ? "" : ","}' var="delimiter" />

								${phoneNumberWatsonListType.watsonListTypeId}: {
									label: '${AlloyLanguageUtil.formatUnicode(phoneNumberWatsonListType.getName(locale))}',
									value: '${phoneNumberWatsonListType.watsonListTypeId}'
								}${delimiter}
							</c:forEach>
						},
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("provide-phone-numbers-for-this-person")}',
						translatable: ${false},
						type: 'DOUBLE_DEPENDENT_INPUT',
						validations: []
					},
					phoneNumberWatsonListTypeRelString: {
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("phone-numbers")}',
						translatable: ${false},
						type: 'INPUT'
					},
					primaryNameString: {
						filterable: ${true},
						label: '${AlloyLanguageUtil.format("primary-name")}',
						translatable: ${false},
						type: 'INPUT'
					},
					rescued: {
						controlledInputs: [
							'dateRescued'
						],
						dependentKey: '${WatsonListTypeConstants.PERSON_TYPE_VICTIM}',
						filterable: ${false},
						htmlType: 'checkbox',
						invertHidden: ${true},
						label: '${AlloyLanguageUtil.formatUnicode('rescued')}',
						parentInputId: 'typeWatsonListTypeId',
						translatable: ${false},
						type: 'DEPENDENT_KEYED_INPUT'
					},
					sexWatsonListTypeId: {
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("gender")}',
						options: {
							<c:set value='${WatsonListType.getWatsonListTypes(WatsonPerson.modelClassName.concat(".sex"))}' var="sexWatsonListTypes" />

							<c:forEach items="${sexWatsonListTypes}" var="sexWatsonListType" varStatus="sexWatsonListTypesIndex">
								<c:set value='${sexWatsonListTypesIndex.last ? "" : ","}' var="delimiter" />

								${sexWatsonListType.watsonListTypeId}: {
									label: '${AlloyLanguageUtil.formatUnicode(sexWatsonListType.getName(locale))}',
									value: '${sexWatsonListType.watsonListTypeId}'
								}${delimiter}
							</c:forEach>
						},
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("provide-the-sex-gender-of-this-person")}',
						translatable: ${false},
						type: 'SELECT_INPUT',
						validations: [
							'required'
						]
					},
					socialMediaAccountWatsonListTypeRels: {
						buttonLabel: '${AlloyLanguageUtil.formatUnicode("add-social-media-account")}',
						filterable: ${false},
						label: '${AlloyLanguageUtil.formatUnicode("social-media-accounts")}',
						options: {
							<c:set value='${WatsonListType.getWatsonListTypes(WatsonPerson.modelClassName.concat(".socialMediaAccountType"))}' var="socialMediaAccountWatsonListTypes" />

							<c:forEach items="${socialMediaAccountWatsonListTypes}" var="socialMediaAccountWatsonListType" varStatus="socialMediaAccountWatsonListTypesIndex">
								<c:set value='${socialMediaAccountWatsonListTypesIndex.last ? "" : ","}' var="delimiter" />

								${socialMediaAccountWatsonListType.watsonListTypeId}: {
									label: '${AlloyLanguageUtil.formatUnicode(socialMediaAccountWatsonListType.getName(locale))}',
									value: '${socialMediaAccountWatsonListType.watsonListTypeId}'
								}${delimiter}
							</c:forEach>
						},
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("provide-social-media-account-ids-for-this-person")}',
						translatable: ${false},
						type: 'DOUBLE_DEPENDENT_INPUT',
						validations: []
					},
					socialMediaAccountWatsonListTypeRelString: {
						filterable: ${true},
						label: '${AlloyLanguageUtil.format("social-media-account")}',
						translatable: ${false},
						type: 'INPUT'
					},
					startAge: {
						filterable: ${true},
						htmlType: 'number',
						label: '${AlloyLanguageUtil.formatUnicode("age-range-start")}',
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("if-the-birth-date-of-this-person-is-not-available-provide-the-youngest-age-this-person-could-be")}',
						translatable: ${false},
						type: 'INPUT',
						validations: [
							'integer'
						]
					},
					typeWatsonListTypeId: {
						controlledInputs: [],
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("involvement")}',
						options: {
							<c:set value='${WatsonListType.getWatsonListTypes(WatsonPerson.modelClassName.concat(".type"))}' var="typeWatsonListTypes" />

							<c:forEach items="${typeWatsonListTypes}" var="typeWatsonListType" varStatus="typeWatsonListTypesIndex">
								<c:set value='${typeWatsonListTypesIndex.last ? "" : ","}' var="delimiter" />

								${typeWatsonListType.watsonListTypeId}: {
									label: '${AlloyLanguageUtil.formatUnicode(typeWatsonListType.getName(locale))}',
									value: '${typeWatsonListType.watsonListTypeId}'
								}${delimiter}
							</c:forEach>

							<c:set value='${WatsonListType.getWatsonListTypes(WatsonPerson.modelClassName.concat(".involvement"))}' var="involvementTypeWatsonListTypes" />

							<c:forEach items="${involvementTypeWatsonListTypes}" var="involvementTypeWatsonListType" varStatus="involvementTypeWatsonListTypesIndex">
								<c:set value='${involvementTypeWatsonListTypesIndex.last ? "" : ","}' var="delimiter" />

								${involvementTypeWatsonListType.watsonListTypeId}: {
									label: '${AlloyLanguageUtil.formatUnicode(involvementTypeWatsonListType.getName(locale))}',
									value: '${involvementTypeWatsonListType.watsonListTypeId}'
								}${delimiter}
							</c:forEach>
						},
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("provide-the-way-that-this-person-is-involved-in-the-incident")}',
						translatable: ${false},
						type: 'SELECT_INPUT',
							validations: [
							'required'
						]
					},
					watsonRelationships: {
						buttonLabel: '${AlloyLanguageUtil.formatUnicode("add-relations")}',
						cssClass: 'triple-dynamic',
						fancy: ${false},
						filterable: ${false},
						label: '${AlloyLanguageUtil.formatUnicode("relationships")}',
						tooltipLabel: '',
						translatable: ${false},
						tripleOnly: ${true},
						type: 'DYNAMIC_RELATIONSHIP_INPUT_GENERATOR',
						validations: [
							'arrayValues4'
						]
					},
					weight: {
						filterable: ${true},
						htmlType: 'number',
						label: '${AlloyLanguageUtil.formatUnicode("weight")}',
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("provide-the-weight-of-this-person-in-kilograms")}',
						translatable: ${false},
						type: 'INPUT',
						validations: []
					}
				},
				relationshipObjectOptions: {
					activities: {
						key : '${ClassNameLocalService.getClassNameId(WatsonActivity.class)}',
						label: '${AlloyLanguageUtil.formatUnicode("activities")}',
						options: {
							<c:set value='${WatsonListType.getWatsonListTypes(WatsonRelationship.modelClassName.concat(".activityPersonRelationshipTypes"))}' var="activityPersonTypes" />

							<c:forEach items="${activityPersonTypes}" var="activityPersonType" varStatus="activityPersonTypesIndex">
								<c:set value='${activityPersonTypesIndex.last ? "" : ","}' var="delimiter" />

								${activityPersonType.watsonListTypeId}: {
									label: '${AlloyLanguageUtil.formatUnicode(activityPersonType.getName(locale))}',
									value: '${activityPersonType.watsonListTypeId}'
								}${delimiter}
							</c:forEach>
						},
						value: 'activities'
					},
					addresses: {
						key : '${ClassNameLocalService.getClassNameId(WatsonAddress.class)}',
						label: '${AlloyLanguageUtil.formatUnicode("addresses")}',
						options: {
							<c:set value='${WatsonListType.getWatsonListTypes(WatsonRelationship.modelClassName.concat(".addressPersonRelationshipTypes"))}' var="addressPersonTypes" />

							<c:forEach items="${addressPersonTypes}" var="addressPersonType" varStatus="addressPersonTypesIndex">
								<c:set value='${addressPersonTypesIndex.last ? "" : ","}' var="delimiter" />

								${addressPersonType.watsonListTypeId}: {
									label: '${AlloyLanguageUtil.formatUnicode(addressPersonType.getName(locale))}',
									value: '${addressPersonType.watsonListTypeId}'
								}${delimiter}
							</c:forEach>
						},
						value: 'addresses'
					},
					people: {
						key: '${ClassNameLocalService.getClassNameId(WatsonPerson.class)}',
						label: '${AlloyLanguageUtil.formatUnicode("people")}',
						options: {
							<c:set value='${WatsonListType.getWatsonListTypes(WatsonRelationship.modelClassName.concat(".personRelationshipTypes"))}' var="personTypes" />

							<c:forEach items="${personTypes}" var="personType" varStatus="personTypesIndex">
								<c:set value='${personTypesIndex.last ? "" : ","}' var="delimiter" />

								${personType.watsonListTypeId}: {
									label: '${AlloyLanguageUtil.formatUnicode(personType.getName(locale))}',
									value: '${personType.watsonListTypeId}'
								}${delimiter}
							</c:forEach>
						},
						value: 'people'
					},
					resources: {
						key: '${ClassNameLocalService.getClassNameId(WatsonResource.class)}',
						label: '${AlloyLanguageUtil.formatUnicode("resources")}',
						options: {
							<c:set value='${WatsonListType.getWatsonListTypes(WatsonRelationship.modelClassName.concat(".personResourceRelationshipTypes"))}' var="personResourceTypes" />

							<c:forEach items="${personResourceTypes}" var="personResourceType" varStatus="personResourceTypesIndex">
								<c:set value='${personResourceTypesIndex.last ? "" : ","}' var="delimiter" />

								${personResourceType.watsonListTypeId}: {
									label: '${AlloyLanguageUtil.formatUnicode(personResourceType.getName(locale))}',
									value: '${personResourceType.watsonListTypeId}'
								}${delimiter}
							</c:forEach>
						},
						value: 'resources'
					},
					vehicles: {
						key: '${ClassNameLocalService.getClassNameId(WatsonVehicle.class)}',
						label: '${AlloyLanguageUtil.formatUnicode("vehicles")}',
						options: {
							<c:set value='${WatsonListType.getWatsonListTypes(WatsonRelationship.modelClassName.concat(".personVehicleRelationshipTypes"))}' var="personVehicleTypes" />

							<c:forEach items="${personVehicleTypes}" var="personVehicleType" varStatus="personVehicleTypesIndex">
								<c:set value='${personVehicleTypesIndex.last ? "" : ","}' var="delimiter" />

								${personVehicleType.watsonListTypeId}: {
									label: '${AlloyLanguageUtil.formatUnicode(personVehicleType.getName(locale))}',
									value: '${personVehicleType.watsonListTypeId}'
								}${delimiter}
							</c:forEach>
						},
						value: 'vehicles'
					}
				},
				pluralLabel: '${AlloyLanguageUtil.formatUnicode("people")}',
				singularLabel: '${AlloyLanguageUtil.formatUnicode("person")}',
				sortByDefault: 'watsonIncidentId',
				sortByOptions: {
					CREATED: {
						label: '${AlloyLanguageUtil.formatUnicode("created")}',
						value: 'createDate'
					},
					INCIDENT_ID: {
						label: '${AlloyLanguageUtil.formatUnicode("incident-id")}',
						value: 'watsonIncidentId'
					},
					LAST_MODIFIED: {
						label: '${AlloyLanguageUtil.formatUnicode("last-modified")}',
						value: 'modifiedDate'
					}
				}
			},
			relationships: {
				inputs: {
					watsonRelationships: {
						buttonLabel: '${AlloyLanguageUtil.formatUnicode("add-relations")}',
						cssClass: 'dynamic-relationship-generator-input',
						fancy: ${false},
						filterable: ${false},
						label: '',
						tooltipLabel: '',
						translatable: ${false},
						tripleOnly: ${false},
						type: 'DYNAMIC_RELATIONSHIP_INPUT_GENERATOR',
						validations: [
							'arrayValues7'
						]
					}
				},
				relationshipObjectOptions: {
					activities: {
						key : '${ClassNameLocalService.getClassNameId(WatsonActivity.class)}',
							label: '${AlloyLanguageUtil.formatUnicode("activities")}',
							options: {
							<c:set value='${WatsonListType.getWatsonListTypes(WatsonRelationship.modelClassName.concat(".addressActivityRelationshipTypes"))}' var="activityResourceTypes" />

							<c:forEach items="${activityResourceTypes}" var="activityResourceType" varStatus="activityResourceTypesIndex">
							<c:set value='${activityResourceTypesIndex.last ? "" : ","}' var="delimiter" />

							${activityResourceType.watsonListTypeId}: {
								label: '${AlloyLanguageUtil.formatUnicode(activityResourceType.getName(locale))}',
								value: '${activityResourceType.watsonListTypeId}'
							}${delimiter}
							</c:forEach>
						},
						value: 'activities'
					},
					addresses: {
						key : '${ClassNameLocalService.getClassNameId(WatsonAddress.class)}',
						label: '${AlloyLanguageUtil.formatUnicode("addresses")}',
						value: 'addresses'
					},
					people: {
						key: '${ClassNameLocalService.getClassNameId(WatsonPerson.class)}',
						label: '${AlloyLanguageUtil.formatUnicode("people")}',
						value: 'people'
					},
					resources: {
						key: '${ClassNameLocalService.getClassNameId(WatsonResource.class)}',
						label: '${AlloyLanguageUtil.formatUnicode("resources")}',
						value: 'resources'
					},
					vehicles: {
						key: '${ClassNameLocalService.getClassNameId(WatsonVehicle.class)}',
						label: '${AlloyLanguageUtil.formatUnicode("vehicles")}',
						value: 'vehicles'
					}
				},
				pluralLabel: '${AlloyLanguageUtil.formatUnicode("relationships")}',
				singularLabel: '${AlloyLanguageUtil.formatUnicode("relationship")}',
				sortByDefault: 'watsonIncidentId',
				sortByOptions: {
					CREATED: {
						label: '${AlloyLanguageUtil.formatUnicode("created")}',
						value: 'createDate'
					},
					INCIDENT_ID: {
						label: '${AlloyLanguageUtil.formatUnicode("incident-id")}',
						value: 'watsonIncidentId'
					},
					LAST_MODIFIED: {
						label: '${AlloyLanguageUtil.formatUnicode("last-modified")}',
						value: 'modifiedDate'
					}
				}
			},
			resources: {
				inputs: {
					createDate: {
						filterable: ${true},
						htmlType: 'date',
						label: '${AlloyLanguageUtil.formatUnicode("date-created")}',
						translatable: ${false},
						type: 'INPUT',
						validations: []
					},
					description: {
						cssClass: 'textarea',
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("description")}',
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("provide-a-detailed-description-of-this-resource")}',
						translatable: ${true},
						type: 'TEXT_AREA_INPUT',
						validations: [
							'required'
						]
					},
					id: {
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("id")}',
						tooltipLabel: '',
						translatable: ${false},
						type: 'INPUT_VIEW',
						validations: []
					},
					imagePayload: {
						acceptedImageTypes: '',
						enableCropperTool: ${true},
						filterable: ${false},
						label: '${AlloyLanguageUtil.formatUnicode("related-file")}',
						multiple: ${false},
						parentInputId: 'typeWatsonListTypeId',
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("click-add-resource-or-drag-an-image-file-onto-it-to-upload-a-picture-of-this-resource")}',
						translatable: ${false},
						type: 'FILE',
						uploaderLabel: '${AlloyLanguageUtil.formatUnicode("add-file")}',
						validations: []
					},
					name: {
						disabled: ${false},
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("summary")}',
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("provide-a-summary-for-this-resource")}',
						translatable: ${true},
						type: 'INPUT',
						validations: [
							'required'
						]
					},
					typeWatsonListTypeId: {
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("type")}',
						options: {
							<c:set value='${WatsonListType.getWatsonListTypes(WatsonResource.modelClassName.concat(".resourceType"))}' var="typeWatsonListTypes" />

							<c:forEach items="${typeWatsonListTypes}" var="typeWatsonListType" varStatus="typeWatsonListTypesIndex">
								<c:set value='${typeWatsonListTypesIndex.last ? "" : ","}' var="delimiter" />

								${typeWatsonListType.watsonListTypeId}: {
									label: '${AlloyLanguageUtil.formatUnicode(typeWatsonListType.getName(locale))}',
									value: '${typeWatsonListType.watsonListTypeId}'
								}${delimiter}
							</c:forEach>
						},
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("provide-the-type-of-this-resource")}',
						translatable: ${false},
						type: 'SELECT_INPUT',
						validations: [
							'required'
						]
					},
					watsonRelationships: {
						buttonLabel: '${AlloyLanguageUtil.formatUnicode("add-relations")}',
						cssClass: 'triple-dynamic',
						fancy: ${false},
						filterable: ${false},
						label: '${AlloyLanguageUtil.formatUnicode("relationships")}',
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("relate-this-resource-on-this-incident")}',
						translatable: ${false},
						tripleOnly: ${true},
						type: 'DYNAMIC_RELATIONSHIP_INPUT_GENERATOR',
						validations: [
							'arrayValues4'
						]
					}
				},
				relationshipObjectOptions: {
					activities: {
						key : '${ClassNameLocalService.getClassNameId(WatsonActivity.class)}',
							label: '${AlloyLanguageUtil.formatUnicode("activities")}',
							options: {
								<c:set value='${WatsonListType.getWatsonListTypes(WatsonRelationship.modelClassName.concat(".activityResourceRelationshipTypes"))}' var="activityResourceTypes" />

								<c:forEach items="${activityResourceTypes}" var="activityResourceType" varStatus="activityResourceTypesIndex">
									<c:set value='${activityResourceTypesIndex.last ? "" : ","}' var="delimiter" />

									${activityResourceType.watsonListTypeId}: {
										label: '${AlloyLanguageUtil.formatUnicode(activityResourceType.getName(locale))}',
										value: '${activityResourceType.watsonListTypeId}'
									}${delimiter}
								</c:forEach>
						},
						value: 'activities'
					},
					addresses: {
						key : '${ClassNameLocalService.getClassNameId(WatsonAddress.class)}',
							label: '${AlloyLanguageUtil.formatUnicode("addresses")}',
							options: {
								<c:set value='${WatsonListType.getWatsonListTypes(WatsonRelationship.modelClassName.concat(".addressResourceRelationshipTypes"))}' var="addressResourceTypes" />

								<c:forEach items="${addressResourceTypes}" var="addressResourceType" varStatus="addressResourceTypesIndex">
									<c:set value='${addressResourceTypesIndex.last ? "" : ","}' var="delimiter" />

									${addressResourceType.watsonListTypeId}: {
										label: '${AlloyLanguageUtil.formatUnicode(addressResourceType.getName(locale))}',
										value: '${addressResourceType.watsonListTypeId}'
									}${delimiter}
								</c:forEach>
						},
						value: 'addresses'
					},
					people: {
						key: '${ClassNameLocalService.getClassNameId(WatsonPerson.class)}',
							label: '${AlloyLanguageUtil.formatUnicode("people")}',
							options: {
								<c:set value='${WatsonListType.getWatsonListTypes(WatsonRelationship.modelClassName.concat(".personResourceRelationshipTypes"))}' var="resourcePersonTypes" />

								<c:forEach items="${resourcePersonTypes}" var="resourcePersonType" varStatus="resourcePersonTypesIndex">
									<c:set value='${resourcePersonTypesIndex.last ? "" : ","}' var="delimiter" />

									${resourcePersonType.watsonListTypeId}: {
										label: '${AlloyLanguageUtil.formatUnicode(resourcePersonType.getName(locale))}',
										value: '${resourcePersonType.watsonListTypeId}'
									}${delimiter}
								</c:forEach>
						},
						value: 'people'
					},
					vehicles: {
						key: '${ClassNameLocalService.getClassNameId(WatsonVehicle.class)}',
							label: '${AlloyLanguageUtil.formatUnicode("vehicles")}',
							options: {
								<c:set value='${WatsonListType.getWatsonListTypes(WatsonRelationship.modelClassName.concat(".resourceVehicleRelationshipTypes"))}' var="resourceVehicleTypes" />

								<c:forEach items="${resourceVehicleTypes}" var="resourceVehicleType" varStatus="resourceVehicleTypesIndex">
									<c:set value='${resourceVehicleTypesIndex.last ? "" : ","}' var="delimiter" />

									${resourceVehicleType.watsonListTypeId}: {
										label: '${AlloyLanguageUtil.formatUnicode(resourceVehicleType.getName(locale))}',
										value: '${resourceVehicleType.watsonListTypeId}'
									}${delimiter}
								</c:forEach>
						},
						value: 'vehicles'
					}
				},
				pluralLabel: '${AlloyLanguageUtil.formatUnicode("resources")}',
				singularLabel: '${AlloyLanguageUtil.formatUnicode("resource")}',
				sortByDefault: 'watsonIncidentId',
				sortByOptions: {
					CREATED: {
						label: '${AlloyLanguageUtil.formatUnicode("created")}',
						value: 'createDate'
					},
					INCIDENT_ID: {
						label: '${AlloyLanguageUtil.formatUnicode("incident-id")}',
						value: 'watsonIncidentId'
					},
					LAST_MODIFIED: {
						label: '${AlloyLanguageUtil.formatUnicode("last-modified")}',
						value: 'modifiedDate'
					},
					TYPE: {
						label: '${AlloyLanguageUtil.formatUnicode("type")}',
						value: 'typeWatsonListTypeId'
					}
				}
			},
			vehicles: {
				inputs: {
					createDate: {
						filterable: ${true},
						htmlType: 'date',
						label: '${AlloyLanguageUtil.formatUnicode("date-created")}',
						translatable: ${false},
						type: 'INPUT',
						validations: []
					},
					colorWatsonListTypeId: {
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("color")}',
						options: {
							<c:set value='${WatsonListType.getWatsonListTypes(WatsonVehicle.modelClassName.concat(".color"))}' var="watsonVehicleColors" />

							<c:forEach items="${watsonVehicleColors}" var="watsonVehicleColor" varStatus="watsonVehicleColorsIndex">
								<c:set value='${watsonVehicleColorsIndex.last ? "" : ","}' var="delimiter" />

								${watsonVehicleColor.watsonListTypeId}: {
									label: '${AlloyLanguageUtil.formatUnicode(watsonVehicleColor.getName(locale))}',
									value: '${watsonVehicleColor.watsonListTypeId}'
								}${delimiter}
							</c:forEach>
						},
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("provide-the-color-of-this-vehicle")}',
						translatable: ${false},
						type: 'SELECT_INPUT',
						validations: []
					},
					description: {
						cssClass: 'textarea',
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("unique-identifiers")}',
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("provide-any-unique-identifiers-for-this-vehicle")}',
						translatable: ${true},
						type: 'TEXT_AREA_INPUT',
						validations: []
					},
					id: {
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("id")}',
						tooltipLabel: '',
						translatable: ${false},
						type: 'INPUT_VIEW',
						validations: []
					},
					imagePayload: {
						acceptedTypes: 'image/*',
						enableCropperTool: ${true},
						filterable: ${false},
						label: '${AlloyLanguageUtil.formatUnicode("picture")}',
						multiple: ${false},
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("click-add-picture-or-drag-an-image-file-onto-it-to-upload-a-picture-of-this-vehicle")}',
						translatable: ${false},
						type: 'FILE',
						uploaderLabel: '${AlloyLanguageUtil.formatUnicode("add-picture")}',
						validations: []
					},
					licensePlate: {
						disabled: ${false},
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("license-plate")}',
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("provide-the-license-plate-of-this-vehicle")}',
						translatable: ${false},
						type: 'INPUT',
						validations: []
					},
					makeWatsonListTypeId: {
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("make")}',
						options: {
							<c:set value='${WatsonListType.getWatsonListTypes(WatsonVehicle.modelClassName.concat(".make"))}' var="watsonVehicleMakes" />

							<c:forEach items="${watsonVehicleMakes}" var="watsonVehicleMake" varStatus="watsonVehicleMakesIndex">
								<c:set value='${watsonVehicleMakesIndex.last ? "" : ","}' var="delimiter" />

								${watsonVehicleMake.watsonListTypeId}: {
									label: '${AlloyLanguageUtil.formatUnicode(watsonVehicleMake.getName(locale))}',
									value: '${watsonVehicleMake.watsonListTypeId}'
								}${delimiter}
							</c:forEach>
						},
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("provide-the-make-of-this-vehicle")}',
						translatable: ${false},
						type: 'SELECT_INPUT',
						validations: []
					},
					modelWatsonListTypeId: {
						filterable: ${false},
						label: '${AlloyLanguageUtil.formatUnicode("vehicle-model")}',
						listTypeValue: '${WatsonVehicle.modelClassName.concat(".model")}',
						options: '${null}',
						parentInputId: 'makeWatsonListTypeId',
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("after-choosing-the-make-provide-the-model")}',
						translatable: ${false},
						type: 'DEPENDENT_SELECT_INPUT',
						validations: []
					},
					modifiedDate: {
						filterable: ${true},
						htmlType: 'date',
						label: '${AlloyLanguageUtil.formatUnicode("date-modified")}',
						translatable: ${false},
						type: 'INPUT',
						validations: []
					},
					typeWatsonListTypeId: {
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("type")}',
						options: {
							<c:set value='${WatsonListType.getWatsonListTypes(WatsonVehicle.modelClassName.concat(".type"))}' var="watsonVehicleTypes" />

							<c:forEach items="${watsonVehicleTypes}" var="watsonVehicleType" varStatus="watsonVehicleTypesIndex">
								<c:set value='${watsonVehicleTypesIndex.last ? "" : ","}' var="delimiter" />

								${watsonVehicleType.watsonListTypeId}: {
									label: '${AlloyLanguageUtil.formatUnicode(watsonVehicleType.getName(locale))}',
									value: '${watsonVehicleType.watsonListTypeId}'
								}${delimiter}
							</c:forEach>
						},
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("provide-the-type-of-this-vehicle")}',
						translatable: ${false},
						type: 'SELECT_INPUT',
						validations: [
							'required'
						]
					},
					watsonRelationships: {
						buttonLabel: '${AlloyLanguageUtil.formatUnicode("add-relations")}',
						cssClass: 'triple-dynamic',
						fancy: ${false},
						filterable: ${false},
						label: '${AlloyLanguageUtil.formatUnicode("relationships")}',
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("relate-this-vehicle-on-this-incident")}',
						translatable: ${false},
						tripleOnly: ${true},
						type: 'DYNAMIC_RELATIONSHIP_INPUT_GENERATOR',
						validations: [
							'arrayValues4'
						]
					},
					yearWatsonListTypeId: {
						filterable: ${true},
						label: '${AlloyLanguageUtil.formatUnicode("year")}',
						options: {
							<c:set value='${WatsonListType.getWatsonListTypes(WatsonVehicle.modelClassName.concat(".year"))}' var="watsonVehicleYears" />

							<c:forEach items="${watsonVehicleYears}" var="watsonVehicleYear" varStatus="watsonVehicleYearsIndex">
								<c:set value='${watsonVehicleYearsIndex.last ? "" : ","}' var="delimiter" />

								${watsonVehicleYear.watsonListTypeId}: {
									label: '${AlloyLanguageUtil.formatUnicode(watsonVehicleYear.getName(locale))}',
									value: '${watsonVehicleYear.watsonListTypeId}'
								}${delimiter}
							</c:forEach>
						},
						tooltipLabel: '${AlloyLanguageUtil.formatUnicode("provide-vehicle-year-range")}',
						translatable: ${false},
						type: 'SELECT_INPUT',
						validations: [
							'required'
						]
					}
				},
				relationshipObjectOptions: {
					addresses: {
						key : '${ClassNameLocalService.getClassNameId(WatsonAddress.class)}',
							label: '${AlloyLanguageUtil.formatUnicode("addresses")}',
							options: {
								<c:set value='${WatsonListType.getWatsonListTypes(WatsonRelationship.modelClassName.concat(".addressVehicleRelationshipTypes"))}' var="addressVehicleTypes" />

								<c:forEach items="${addressVehicleTypes}" var="addressVehicleType" varStatus="addressVehicleTypesIndex">
									<c:set value='${addressVehicleTypesIndex.last ? "" : ","}' var="delimiter" />

									${addressVehicleType.watsonListTypeId}: {
										label: '${AlloyLanguageUtil.formatUnicode(addressVehicleType.getName(locale))}',
										value: '${addressVehicleType.watsonListTypeId}'
									}${delimiter}
								</c:forEach>
						},
						value: 'addresses'
					},
					people: {
						key: '${ClassNameLocalService.getClassNameId(WatsonPerson.class)}',
							label: '${AlloyLanguageUtil.formatUnicode("people")}',
							options: {
								<c:set value='${WatsonListType.getWatsonListTypes(WatsonRelationship.modelClassName.concat(".personVehicleRelationshipTypes"))}' var="vehiclePersonTypes" />

								<c:forEach items="${vehiclePersonTypes}" var="vehiclePersonType" varStatus="vehiclePersonTypesIndex">
									<c:set value='${vehiclePersonTypesIndex.last ? "" : ","}' var="delimiter" />

									${vehiclePersonType.watsonListTypeId}: {
										label: '${AlloyLanguageUtil.formatUnicode(vehiclePersonType.getName(locale))}',
										value: '${vehiclePersonType.watsonListTypeId}'
									}${delimiter}
								</c:forEach>
						},
						value: 'people'
					},
					resources: {
						key: '${ClassNameLocalService.getClassNameId(WatsonResource.class)}',
							label: '${AlloyLanguageUtil.formatUnicode("resources")}',
							options: {
								<c:set value='${WatsonListType.getWatsonListTypes(WatsonRelationship.modelClassName.concat(".resourceVehicleRelationshipTypes"))}' var="vehicleResourceTypes" />

								<c:forEach items="${vehicleResourceTypes}" var="vehicleResourceType" varStatus="vehicleResourceTypesIndex">
									<c:set value='${vehicleResourceTypesIndex.last ? "" : ","}' var="delimiter" />

									${vehicleResourceType.watsonListTypeId}: {
										label: '${AlloyLanguageUtil.formatUnicode(vehicleResourceType.getName(locale))}',
										value: '${vehicleResourceType.watsonListTypeId}'
									}${delimiter}
								</c:forEach>
						},
						value: 'resources'
					}
				},
				pluralLabel: '${AlloyLanguageUtil.formatUnicode("vehicles")}',
				singularLabel: '${AlloyLanguageUtil.formatUnicode("vehicle")}',
				sortByDefault: 'watsonIncidentId',
				sortByOptions: {
					CREATED: {
						label: '${AlloyLanguageUtil.formatUnicode("created")}',
						value: 'createDate'
					},
					INCIDENT_ID: {
						label: '${AlloyLanguageUtil.formatUnicode("incident-id")}',
						value: 'watsonIncidentId'
					},
					LAST_MODIFIED: {
						label: '${AlloyLanguageUtil.formatUnicode("last-modified")}',
						value: 'modifiedDate'
					},
					MAKE: {
						label: '${AlloyLanguageUtil.formatUnicode("make")}',
						value: 'makeWatsonListTypeId'
					},
					TYPE: {
						label: '${AlloyLanguageUtil.formatUnicode("type")}',
						value: 'typeWatsonListTypeId'
					}
				}
			}
		}
	};

	window.WatsonConstants = config;
</script>