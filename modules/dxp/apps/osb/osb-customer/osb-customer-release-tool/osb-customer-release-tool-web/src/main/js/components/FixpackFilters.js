import React, {Component, Fragment} from 'react';
import PropTypes from 'prop-types';

import 'core-js/fn/array/find';
import 'core-js/fn/array/find-index';

import {filtersJSONObject} from '../types/highlights';

import Button from './Button';

const PORTLET_ID = 'com_liferay_osb_customer_release_tool_web_portlet_ReleaseToolPortlet';

export default class FixpackFilters extends Component {
	fixpackFiltersFormRef = React.createRef();

	static defaultProps = {
		fixpackURL: '',
		fromFixPackVersion: '',
		fromProductVersion: '',
		toFixPackVersion: '',
		toProductVersion: ''
	};

	static propTypes = {
		actionURL: PropTypes.string.isRequired,
		filtersJSON: filtersJSONObject.isRequired,
		fixpackURL: PropTypes.string,
		fromFixPackVersion: PropTypes.string,
		fromProductVersion: PropTypes.string,
		toFixPackVersion: PropTypes.string,
		toProductVersion: PropTypes.string
	};

	determineVersion = version => {
		return version === '0.0' ? '' : version;
	};

	state = {
		fromFixPackVersion: this.determineVersion(this.props.fromFixPackVersion),
		fromProductVersion: this.determineVersion(this.props.fromProductVersion),
		toProductVersion: this.determineVersion(this.props.toProductVersion)
	};

	getNameFromVersion = version => {
		const {toProductVersion} = this.state;

		const fixpacks = this.lookupFixPacksByProduct(toProductVersion, true);

		const fixpack = fixpacks.find(
			item => item.version === version
		);

		return fixpack ? fixpack.name : '';
	};

	handleFromFixpackOnChange = target => {
		const {toFixPackVersion} = this.props;

		this.setState(
			{
				fromFixPackVersion: target
			}
		);

		if (this.determineVersion(toFixPackVersion)) {
			this.handleSubmit();
		}
	};

	handleFromProductOnChange = target => {
		this.setState(
			{
				fromProductVersion: target,
				toProductVersion: target
			}
		);
	};

	handleToProductOnChange = target => {
		this.setState(
			{
				toProductVersion: target
			}
		);
	};

	handleSubmit = () => this.fixpackFiltersFormRef.current.submit();

	lookupFixPacksByProduct = (productVersion, isToFixPackFilter = false) => {
		const {filtersJSON} = this.props;

		const {
			fromFixPackVersion,
			fromProductVersion,
			toProductVersion
		} = this.state;

		const currentProduct = filtersJSON.find(
			item => item.version === productVersion
		);

		let fixpacks = currentProduct ? currentProduct.fixPacks : [];

		if (isToFixPackFilter && fromProductVersion === toProductVersion) {
			const index = fixpacks.findIndex(
				item => item.version === fromFixPackVersion
			);

			fixpacks = fixpacks.slice(index);
		}

		return fixpacks;
	};

	render() {
		const {actionURL, filtersJSON, fixpackURL, toFixPackVersion} = this.props;

		const {
			fromFixPackVersion,
			fromProductVersion,
			toProductVersion
		} = this.state;

		const {namespace} = window.ReleaseToolConstants;

		const fixpackDownloadLinkDescription = (
			<Fragment>
				{`${Liferay.Language.get('get')} ${
					toProductVersion ? this.getNameFromVersion(toFixPackVersion) : ''
				}`}{' '}
				<svg className="lexicon-icon lexicon-icon-arrow-right">
					<use xlinkHref="#arrow-right" />
				</svg>
			</Fragment>
		);

		return (
			<Fragment>
				<form ref={this.fixpackFiltersFormRef} action={actionURL} method="get">
					<input name="p_p_id" type="hidden" value={PORTLET_ID} />
					<input name={`${namespace}product`} type="hidden" value="dxp" />

					<div className="search-filters">
						<div className="search-filter-container">
							<Filter
								id={`${namespace}fromProductVersion`}
								label={Liferay.Language.get('from')}
								options={filtersJSON}
								onChange={this.handleFromProductOnChange}
								placeholder={Liferay.Language.get('select-product')}
								selected={fromProductVersion}
							/>

							<Filter
								disabled={!fromProductVersion}
								id={`${namespace}fromFixPackVersion`}
								options={this.lookupFixPacksByProduct(fromProductVersion)}
								onChange={this.handleFromFixpackOnChange}
								placeholder={Liferay.Language.get('select-release')}
								selected={fromFixPackVersion}
							/>
						</div>

						<div className="search-filter-container">
							<Filter
								disabled={!fromFixPackVersion}
								id={`${namespace}toProductVersion`}
								label={Liferay.Language.get('to')}
								options={filtersJSON}
								onChange={this.handleToProductOnChange}
								placeholder={Liferay.Language.get('select-product')}
								selected={toProductVersion}
							/>

							<Filter
								disabled={!fromFixPackVersion || !toProductVersion}
								id={`${namespace}toFixPackVersion`}
								options={this.lookupFixPacksByProduct(toProductVersion, true)}
								onChange={this.handleSubmit}
								placeholder={Liferay.Language.get('select-release')}
								selected={toFixPackVersion}
							/>
						</div>
					</div>
				</form>

				{fixpackURL !== 'null' && (
					<Button
						children={fixpackDownloadLinkDescription}
						display="link"
						href={fixpackURL}
						size="lg"
					/>
				)}
			</Fragment>
		);
	}
}

class Filter extends Component {
	static defaultProps = {
		disabled: false,
		label: '',
		selected: ''
	};

	static propTypes = {
		disabled: PropTypes.bool,
		id: PropTypes.string.isRequired,
		label: PropTypes.string,
		onChange: PropTypes.func.isRequired,
		options: PropTypes.array.isRequired,
		placeholder: PropTypes.string.isRequired,
		selected: PropTypes.string
	};

	handleChange = (event) => {
		const {onChange} = this.props;

		onChange(event.currentTarget.value);
	}

	render() {
		const {disabled, id, label, options, placeholder, selected} = this.props;

		return (
			<div className="filter">
				{label && (
					<label className="control-label" htmlFor={id}>
						{`${label}:`}
					</label>
				)}

				<select
					className="form-control"
					disabled={disabled}
					id={id}
					name={id}
					onChange={this.handleChange}
					value={selected || (options.length === 1 ? options[0].version : '')}
				>
					<option value="">{placeholder}</option>

					{options.map(
						option => {
							return (
								<option
									key={option.version}
									label={option.name}
									value={option.version}
								>
									{option.name}
								</option>
							);
						}
					)}
				</select>
			</div>
		);
	}
}