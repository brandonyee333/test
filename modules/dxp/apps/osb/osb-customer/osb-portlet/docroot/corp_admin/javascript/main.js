AUI.add(
	'liferay-corp-admin',
	function(A) {
		var Address = {
			getCountryName: function(selectKey) {
				var country = Liferay.Service.Portal.Country.getCountry(
					{
						countryId: selectKey
					}
				);

				return country.name;
			},

			getRegionName: function(selectKey) {
				var region = Liferay.Service.Portal.Region.getRegion(
					{
						regionId: selectKey
					}
				);

				return region.name;
			}
		};

		var WorkflowConstants = {
			toLabel: function(selectKey) {
				if (selectKey == Liferay.Workflow.STATUS_ANY) {
					return Liferay.Language.get('any');
				}
				else if (selectKey == Liferay.Workflow.STATUS_APPROVED) {
					return Liferay.Language.get('approved');
				}
				else if (selectKey == Liferay.Workflow.STATUS_DENIED) {
					return Liferay.Language.get('denied');
				}
				else if (selectKey == Liferay.Workflow.STATUS_DRAFT) {
					return Liferay.Language.get('draft');
				}
				else if (selectKey == Liferay.Workflow.STATUS_EXPIRED) {
					return Liferay.Language.get('expired');
				}
				else if (selectKey == Liferay.Workflow.STATUS_PENDING) {
					return Liferay.Language.get('pending');
				}
			}
		};

		Liferay.CorpAdmin = {
			Address: Address,
			WorkflowConstants: WorkflowConstants
		};
	},
	'',
	{
		requires: ['aui-base']
	}
);