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

<%@ include file="/alloy_mvc/jsp/watson/controllers/init.jspf" %>

<%@ include file="/alloy_mvc/jsp/watson/indexers/list_type_indexer.jspf" %>

<%!
public static class AlloyControllerImpl extends WatsonAlloyControllerImpl {

	public AlloyControllerImpl() throws Exception {
		setAlloyServiceInvokerClass(WatsonListType.getBaseModelClass());
		setPermissioned(false);
	}

	public void fetchChildrenListTypes() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		long parentWatsonListTypeId = ParamUtil.getLong(request, "parentWatsonListTypeId");
		String type = ParamUtil.getString(request, "listType");

		List<WatsonListType> watsonListTypes = WatsonListType.query("parentWatsonListTypeId", parentWatsonListTypeId, "type", type);

		for (WatsonListType watsonListType : watsonListTypes) {
			JSONObject labelJSONObject = JSONFactoryUtil.createJSONObject();

			labelJSONObject.put("label", watsonListType.getName(LocaleUtil.getMostRelevantLocale()));
			labelJSONObject.put("value", watsonListType.getWatsonListTypeId());

			jsonObject.put(String.valueOf(watsonListType.getWatsonListTypeId()), labelJSONObject);
		}

		respondWith(jsonObject);
	}

	public void fetchListType() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonListTypeId = ParamUtil.getLong(request, "watsonListTypeId");

		if (watsonListTypeId != 0) {
			WatsonListType watsonListType = WatsonListType.fetch(watsonListTypeId);

			respondWith(WatsonListType._getAsJSONObject(watsonListType));
		}
	}

	public void syncListTypes() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		SearchContext searchContext = getSearchContext(WatsonListType.baseModelClass, true);

		String language = ParamUtil.getString(request, "language", StringPool.BLANK);

		String nameType = language.contains("th") ? "name_th" : "name_en_US";

		String countryA2 = ParamUtil.getString(request, "country", StringPool.BLANK);

		String countryId = _searchCountry(countryA2);

		jsonObject.put("countryId", countryId);

		String province = ParamUtil.getString(request, "province", StringPool.BLANK);

		String provinceWatsonListTypeId = _searchProvinces(searchContext, StringUtil.toLowerCase(province), nameType);

		jsonObject.put("provinceWatsonListTypeId", provinceWatsonListTypeId);

		String district = ParamUtil.getString(request, "district", StringPool.BLANK);

		String districtWatsonListTypeId = _searchDistricts(searchContext, StringUtil.toLowerCase(district), provinceWatsonListTypeId, nameType);

		jsonObject.put("districtWatsonListTypeId", districtWatsonListTypeId);

		String subdistrict = ParamUtil.getString(request, "subdistrict", StringPool.BLANK);

		String subdistrictWatsonListTypeId = _searchSubdistricts(searchContext, StringUtil.toLowerCase(subdistrict), districtWatsonListTypeId, nameType);

		jsonObject.put("subDistrictWatsonListTypeId", subdistrictWatsonListTypeId);

		respondWith(jsonObject);
	}

	@Override
	protected Indexer buildIndexer() {
		return WatsonListTypeIndexer.getInstance();
	}

	private String _doSearchForClassPK(SearchContext searchContext) throws Exception {
		Hits hits = indexer.search(searchContext);

		if (hits.getLength() < 1) {
			return StringPool.BLANK;
		}

		Document[] documents = hits.getDocs();

		Document document = documents[0];

		return GetterUtil.getString(document.get("entryClassPK"));
	}

	private String _searchCountry(String countryA2) throws Exception {
		Country country = CountryServiceUtil.fetchCountryByA2(countryA2);

		return String.valueOf(country.getCountryId());
	}

	private String _searchDistricts(SearchContext searchContext, String district, String parentWatsonListTypeId, String nameType) throws Exception{
		if (district.equals(StringPool.BLANK)) {
			return StringPool.BLANK;
		}

		BooleanQuery booleanQuery = new BooleanQueryImpl();

		booleanQuery.addTerm(nameType, district);

		BooleanClause<Query> districtClause = BooleanClauseFactoryUtil.create(booleanQuery, BooleanClauseOccur.MUST.getName());
		BooleanClause<Query> parentWatsonListTypeIdClause = BooleanClauseFactoryUtil.create("parentWatsonListTypeId", parentWatsonListTypeId, BooleanClauseOccur.MUST.getName());
		BooleanClause<Query> typeClause = BooleanClauseFactoryUtil.create("type", "com.liferay.watson.model.WatsonAddress.district", BooleanClauseOccur.MUST.getName());

		searchContext.setBooleanClauses(new BooleanClause[] {districtClause, parentWatsonListTypeIdClause, typeClause});

		return _doSearchForClassPK(searchContext);
	}

	private String _searchProvinces(SearchContext searchContext, String province, String nameType) throws Exception {
		if (province.equals(StringPool.BLANK)) {
			return StringPool.BLANK;
		}

		BooleanQuery booleanQuery = new BooleanQueryImpl();

		booleanQuery.addTerm(nameType, province);

		BooleanClause<Query> provinceClause = BooleanClauseFactoryUtil.create(booleanQuery, BooleanClauseOccur.MUST.getName());
		BooleanClause<Query> typeClause = BooleanClauseFactoryUtil.create("type", "com.liferay.watson.model.WatsonAddress.province", BooleanClauseOccur.MUST.getName());

		searchContext.setBooleanClauses(new BooleanClause[] {provinceClause, typeClause});

		return _doSearchForClassPK(searchContext);
	}

	private String _searchSubdistricts(SearchContext searchContext, String subdistrict, String parentWatsonListTypeId, String nameType) throws Exception {
		if (subdistrict.equals(StringPool.BLANK)) {
			return StringPool.BLANK;
		}

		BooleanClause<Query> parentWatsonListTypeIdClause = BooleanClauseFactoryUtil.create("parentWatsonListTypeId", parentWatsonListTypeId, BooleanClauseOccur.MUST.getName());

		BooleanQuery booleanQuery = new BooleanQueryImpl();

		booleanQuery.addTerm(nameType, subdistrict);

		BooleanClause<Query> subdistrictClause = BooleanClauseFactoryUtil.create(booleanQuery, BooleanClauseOccur.MUST.getName());
		BooleanClause<Query> typeClause = BooleanClauseFactoryUtil.create("type", "com.liferay.watson.model.WatsonAddress.subDistrict", BooleanClauseOccur.MUST.getName());

		searchContext.setBooleanClauses(new BooleanClause[] {parentWatsonListTypeIdClause, subdistrictClause, typeClause});

		return _doSearchForClassPK(searchContext);
	}

}
%>