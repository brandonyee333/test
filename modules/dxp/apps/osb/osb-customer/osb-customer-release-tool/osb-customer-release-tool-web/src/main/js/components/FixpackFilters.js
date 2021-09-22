import React, {Component, Fragment} from 'react';
import PropTypes from 'prop-types';

import 'core-js/features/array/find';
import 'core-js/features/array/find-index';

import {filtersJSONObjectType} from '../types/generic';

import Button from './Button';
import FilterSelect from './FilterSelect';

const DXP = 'dxp';
const PORTLET_ID =
	'com_liferay_osb_customer_release_tool_web_portlet_ReleaseToolPortlet';

export default class FixpackFilters extends Component {
	fixpackFiltersFormRef = React.createRef();

	static defaultProps = {
		fixpackURL: '',
		fromFixPackVersion: '',
		productVersion: '',
		toFixPackVersion: ''
	};

	static propTypes = {
		actionURL: PropTypes.string.isRequired,
		filtersJSON: filtersJSONObjectType.isRequired,
		fixpackURL: PropTypes.string,
		fromFixPackVersion: PropTypes.string,
		productName: PropTypes.string.isRequired,
		productVersion: PropTypes.string,
		tabName: PropTypes.string.isRequired,
		toFixPackVersion: PropTypes.string
	};

	determineVersion = (version) => {
		return version === '0.0' ? '' : version;
	};

	state = {
		fixpackURL: this.props.fixpackURL,
		fromFixPackVersion: this.props.fromFixPackVersion,
		productVersion: this.determineVersion(this.props.productVersion),
		toFixPackVersion: this.props.toFixPackVersion
	};

	componentDidUpdate(_, prevState) {
		const {
			fromFixPackVersion,
			productVersion,
			toFixPackVersion
		} = this.state;

		const {
			fromFixPackVersion: prevFromFixPackVersion,
			productVersion: prevProductVersion,
			toFixPackVersion: prevToFixPackVersion
		} = prevState;

		if (
			fromFixPackVersion &&
			productVersion &&
			toFixPackVersion &&
			(fromFixPackVersion !== prevFromFixPackVersion ||
				productVersion !== prevProductVersion ||
				toFixPackVersion !== prevToFixPackVersion)
		) {
			this.fixpackFiltersFormRef.current.submit();
		}
	}

	getNameFromVersion = (version) => {
		const fixpacks = this.lookupFixPacksByProduct();

		const fixpack = fixpacks.find((item) => item.version === version);

		return fixpack ? fixpack.name : '';
	};

	handleFromFixpackOnChange = (target) => {
		this.setState({
			fixpackURL: '',
			fromFixPackVersion: target,
			toFixPackVersion: ''
		});
	};

	handleProductVersionOnChange = (target) => {
		const fixpacks = this.getFixpacksByProduct(target);

		this.setState({
			fixpackURL: '',
			fromFixPackVersion: this.getAutopopulatedOptionVersion(fixpacks),
			productVersion: target,
			toFixPackVersion: this.getAutopopulatedOptionVersion(fixpacks)
		});
	};

	handleToFixpackOnChange = (target) => {
		this.setState({
			fixpackURL: '',
			toFixPackVersion: target
		});
	};

	lookupFixPacksByProduct = (isToFixPackFilter = false) => {
		const {fromFixPackVersion, productVersion} = this.state;
		const fixpacks = this.getFixpacksByProduct(productVersion);

		if (isToFixPackFilter) {
			const index = fixpacks.findIndex(
				(item) => item.version === fromFixPackVersion
			);

			return fixpacks.slice(index);
		}

		return fixpacks;
	};

	getAutopopulatedOptionVersion = (options) => {
		if (options.length === 1) {
			const [option] = options;

			return option.version;
		}

		return '';
	};

	getFixpacksByProduct = (productVersion) => {
		const {filtersJSON} = this.props;

		const currentProduct = filtersJSON.find(
			(item) => item.version === productVersion
		);

		return currentProduct ? currentProduct.fixPacks : [];
	};

	render() {
		const {actionURL, filtersJSON, productName, tabName} = this.props;
		const {
			fixpackURL,
			fromFixPackVersion,
			productVersion,
			toFixPackVersion
		} = this.state;

		const {namespace} = window.ReleaseToolConstants;

		const fixpackDownloadLinkDescription = (
			<Fragment>
				{`${Liferay.Language.get('get')} ${this.getNameFromVersion(
					toFixPackVersion
				)}`}{' '}
				<svg className="lexicon-icon lexicon-icon-arrow-right">
					<use xlinkHref="#arrow-right" />
				</svg>
			</Fragment>
		);

		const productLabel =
			productName === DXP
				? Liferay.Language.get('product')
				: Liferay.Language.get('dxp-version');

		const productPlaceholder =
			productName === DXP
				? Liferay.Language.get('select-product')
				: Liferay.Language.get('select-dxp-version');

		return (
			<Fragment>
				<form
					ref={this.fixpackFiltersFormRef}
					action={actionURL}
					method="get"
				>
					<input name="p_p_id" type="hidden" value={PORTLET_ID} />
					<input
						name={`${namespace}product`}
						type="hidden"
						value={DXP}
					/>
					<input
						name={`${namespace}tabs1`}
						type="hidden"
						value={tabName}
					/>

					<div className="search-filters">
						<div className="search-filter-container">
							<FilterSelect
								id={`${namespace}productVersion`}
								label={productLabel}
								onChange={this.handleProductVersionOnChange}
								options={filtersJSON}
								placeholder={productPlaceholder}
								selected={productVersion}
							/>
						</div>

						<div className="search-filter-container">
							<FilterSelect
								disabled={!productVersion}
								id={`${namespace}fromFixPackVersion`}
								label={Liferay.Language.get('from')}
								onChange={this.handleFromFixpackOnChange}
								options={this.lookupFixPacksByProduct()}
								placeholder={Liferay.Language.get(
									'select-release'
								)}
								selected={fromFixPackVersion}
							/>
						</div>

						<div className="search-filter-container">
							<FilterSelect
								disabled={
									!fromFixPackVersion || !productVersion
								}
								id={`${namespace}toFixPackVersion`}
								label={Liferay.Language.get('to')}
								onChange={this.handleToFixpackOnChange}
								options={this.lookupFixPacksByProduct(
									!!fromFixPackVersion
								)}
								placeholder={Liferay.Language.get(
									'select-release'
								)}
								selected={toFixPackVersion}
							/>
						</div>
					</div>
				</form>

				{fixpackURL && (
					<Button display="link" href={fixpackURL} size="lg">
						{fixpackDownloadLinkDescription}
					</Button>
				)}
			</Fragment>
		);
	}
}
