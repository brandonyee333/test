import React, {Component} from 'react';
import PropTypes from 'prop-types';

export default class FilterCheckbox extends Component {
	static propTypes = {
		handleOnChange: PropTypes.func.isRequired,
		label: PropTypes.string.isRequired,
		value: PropTypes.string.isRequired
	};

	handleOnChange = () => {
		const {value, handleOnChange} = this.props;

		handleOnChange(value);
	}

	render() {
		const {label, value} = this.props;

		return (
			<div className="filter-checkbox-container">
				<label>
					<input
						className="filter-checkbox"
						name={value}
						onChange={this.handleOnChange}
						type="checkbox"
					/>

					<span className="filter-checkbox-label">{label}</span>
				</label>
			</div>
		);
	}
}