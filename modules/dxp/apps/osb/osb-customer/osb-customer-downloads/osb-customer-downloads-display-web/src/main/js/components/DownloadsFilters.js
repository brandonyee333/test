import React from 'react';
import PropTypes from 'prop-types';

const SELECT_LABEL = Liferay.Language.get('select');

export default class DownloadsFilters extends React.Component {
	searchDownloadsFormRef = React.createRef();

	state = {
		availableFileTypes: [],
		selected: {
			fileType: "",
			product: ""
		}
	};

	static propTypes = {
		actionURL: PropTypes.string.isRequired,
		productsJSONArray: PropTypes.array.isRequired
	};

	componentDidUpdate() {
		const {fileType, product} = this.state.selected;

		if (!product || (fileType && product)) {
			this.handleSubmit();
		}
	}

	handleFileTypeChange = event => {
		const {selected} = this.state;

		this.setState(
			{
				selected: {
					...selected,
					fileType: event.target.value
				}
			}
		);
	};

	handleProductChange = event => {
		const {productsJSONArray} = this.props;

		let autoSelectFileType = false;
		let fileTypes = [];

		if (event.target.value) {
			fileTypes = productsJSONArray.find(product => product.value == event.target.value).fileTypes;

			autoSelectFileType = fileTypes.length == 1;
		}

		this.setState(
			{
				availableFileTypes: fileTypes,
				selected: {
					fileType: autoSelectFileType ? fileTypes[0].value : "",
					product: event.target.value
				}
			}
		);
	};

	handleSubmit = () => this.searchDownloadsFormRef.current.submit();

	render() {
		const {namespace} = window.DownloadsConstants;

		const {actionURL, productsJSONArray} = this.props;

		const {availableFileTypes} = this.state;
		const {fileType, product} = this.state.selected;

		return (
			<form action={actionURL} method="get" ref={this.searchDownloadsFormRef}>
				<div className="search-filters">
					<Filter
						id={`${namespace}product`}
						label={Liferay.Language.get('product')}
						onSelectChange={this.handleProductChange}
						options={productsJSONArray}
						selectedOption={product}
					/>

					<Filter
						id={`${namespace}fileType`}
						label={Liferay.Language.get('file-type')}
						onSelectChange={this.handleFileTypeChange}
						options={availableFileTypes}
						selectedOption={fileType}
					/>
				</div>
			</form>
		);
	}
};

const Filter = props => (
	<div className="downloads-search form-group form-group-inline form-inline input-select-wrapper search-filter-container">
		<label for={props.id} className="control-label">
			{props.label}
		</label>

		<span className="prefix">&#58;</span>

		<select className="form-control" id={props.id} name={props.id} onChange={props.onSelectChange} value={props.selectedOption}>
			<option value="">{Liferay.Language.get('select') + ' ' + props.label}</option>

			{props.options.map(
				(option) => {
					return (
						<option key={option.value} id={'product-' + option.value} label={option.name} value={option.value}>{option.name}</option>
					);
				}
			)}
		</select>
	</div>
);

Filter.propTypes = {
	id: PropTypes.string.isRequired,
	label: PropTypes.string.isRequired,
	onSelectChange: PropTypes.func.isRequired,
	options: PropTypes.array.isRequired,
	selectedOption: PropTypes.string.isRequired
};