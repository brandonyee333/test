import React, {Component} from 'react';
import PropTypes from 'prop-types';

import {filtersJSONObjectType} from '../types/generic';

import FilterSelect from './FilterSelect';

const PORTLET_ID =
	'com_liferay_osb_customer_release_tool_web_portlet_ModuleVersionUpgradePortlet';

export default class CompareVersionFilters extends Component {
	compareVersionFiltersFormRef = React.createRef();

	static defaultProps = {
		fromFixPackVersion: '',
		fromProductVersion: '',
		toFixPackVersion: '',
		toProductVersion: ''
	};

	static propTypes = {
		actionURL: PropTypes.string.isRequired,
		filtersJSON: filtersJSONObjectType.isRequired,
		fromFixPackVersion: PropTypes.string,
		fromProductVersion: PropTypes.string,
		toFixPackVersion: PropTypes.string,
		toProductVersion: PropTypes.string
	};

	determineVersion = version => {
		return version === '0.0' ? '' : version;
	};

	state = {
		fromFixPackVersion: this.props.fromFixPackVersion,
		fromProductVersion: this.determineVersion(this.props.fromProductVersion),
		toFixPackVersion: this.props.toFixPackVersion,
		toProductVersion: this.determineVersion(this.props.toProductVersion)
	};

	componentDidUpdate(prevProps, prevState) {
		const validVersionRange = this.validateVersionRange();
		const validVersions = this.validateVersions();
		const versionChanged = this.validateVersionChange(prevState);

		if (validVersions && versionChanged && validVersionRange) {
			this.compareVersionFiltersFormRef.current.submit();
		}
	}

	determineCompareToVersionSelectOptions = options => {
		const {fromFixPackVersion, fromProductVersion} = this.state;

		let compareToVersionSelectOptions = [];

		if (fromFixPackVersion && fromProductVersion) {
			const index = options.findIndex(
				option => option.version === `${fromProductVersion}-${fromFixPackVersion}`
			);

			compareToVersionSelectOptions = options.slice(index + 1);
		}

		return compareToVersionSelectOptions;
	};

	determineSelectOptions = () => {
		const {filtersJSON} = this.props;

		return filtersJSON.flatMap(
			filter => {
				const name = filter.name;
				const version = filter.version;

				return filter.fixPacks.map(
					fixpack => {
						return (
							{
								name: `${name} ${fixpack.name}`,
								version: `${version}-${fixpack.version}`
							}
						);
					}
				);
			}
		);
	};

	handleCompareToVersionOnChange = event => {
		const selectedValues = this.processSelection(event);

		this.setState(
			{
				toFixPackVersion: selectedValues[1],
				toProductVersion: selectedValues[0]
			}
		);
	};

	handleStartingVersionOnChange = event => {
		const selectedValues = this.processSelection(event);

		this.setState(
			{
				fromFixPackVersion: selectedValues[1],
				fromProductVersion: selectedValues[0],
				toFixPackVersion: '',
				toProductVersion: ''
			}
		);
	};

	processSelection = selection => {
		let selectedValues = ['', ''];

		if (selection) {
			selectedValues = selection.split('-');
		}

		return selectedValues;
	};

	validateVersionChange = prevState => {
		const {
			fromFixPackVersion,
			fromProductVersion,
			toFixPackVersion,
			toProductVersion
		} = this.state;

		let versionChanged = false;

		if (
			prevState.fromFixPackVersion !== fromFixPackVersion ||
			prevState.fromProductVersion !== fromProductVersion ||
			prevState.toFixPackVersion !== toFixPackVersion ||
			prevState.toProductVersion !== toProductVersion
		) {
			versionChanged = true;
		}

		return versionChanged;
	};

	validateVersionRange = () => {
		const fromFixPackVersion = parseFloat(this.state.fromFixPackVersion);
		const fromProductVersion = parseFloat(this.state.fromProductVersion);
		const toFixPackVersion = parseFloat(this.state.toFixPackVersion);
		const toProductVersion = parseFloat(this.state.toProductVersion);

		let validRange = false;

		if (
			(fromFixPackVersion < toFixPackVersion &&
				fromProductVersion === toProductVersion) ||
			fromProductVersion < toProductVersion
		) {
			validRange = true;
		}

		return validRange;
	};

	validateVersions = () => {
		const {
			fromFixPackVersion,
			fromProductVersion,
			toFixPackVersion,
			toProductVersion
		} = this.state;

		let versionValues = false;

		if (
			fromFixPackVersion &&
			fromProductVersion &&
			toFixPackVersion &&
			toProductVersion
		) {
			versionValues = true;
		}

		return versionValues;
	};

	render() {
		const {actionURL} = this.props;
		const {
			fromFixPackVersion,
			fromProductVersion,
			toFixPackVersion,
			toProductVersion
		} = this.state;

		const {namespace} = window.ReleaseToolConstants;

		const options = this.determineSelectOptions();

		return (
			<div className="sidebar-filters">
				<h3>{Liferay.Language.get('compare')}</h3>

				<form ref={this.compareVersionFiltersFormRef} action={actionURL} method="get">
					<input name="p_p_id" type="hidden" value={PORTLET_ID} />
					<input name={`${namespace}product`} type="hidden" value="dxp" />
					<input name={`${namespace}fromFixPackVersion`} type="hidden" value={fromFixPackVersion} />
					<input name={`${namespace}fromProductVersion`} type="hidden" value={fromProductVersion} />
					<input name={`${namespace}toFixPackVersion`} type="hidden" value={toFixPackVersion} />
					<input name={`${namespace}toProductVersion`} type="hidden" value={toProductVersion} />

					<FilterSelect
						cssClass="input-small"
						id="startingVersion"
						label={Liferay.Language.get('starting-version')}
						onChange={this.handleStartingVersionOnChange}
						options={options}
						placeholder={Liferay.Language.get('select-version')}
						selected={`${fromProductVersion}-${fromFixPackVersion}`}
					/>

					<FilterSelect
						cssClass="input-small"
						disabled={!(fromFixPackVersion && fromProductVersion)}
						id="compareToVersion"
						label={Liferay.Language.get('check-against')}
						onChange={this.handleCompareToVersionOnChange}
						options={this.determineCompareToVersionSelectOptions(options)}
						placeholder={Liferay.Language.get('select-version')}
						selected={`${toProductVersion}-${toFixPackVersion}`}
					/>
				</form>
			</div>
		)
	}
}