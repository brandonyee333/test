import React, {Component} from 'react';
import PropTypes from 'prop-types';

export default class FilterCheckbox extends Component {
	static propTypes = {
		checkboxLabel: PropTypes.string.isRequired,
		checkboxValue: PropTypes.string.isRequired,
		handleCheckboxChange: PropTypes.func.isRequired
	};

	handleCheckboxChange = () => {
		const {checkboxValue, handleCheckboxChange} = this.props;

		handleCheckboxChange(checkboxValue);
	}

	render() {
		const {checkboxLabel, checkboxValue} = this.props;

		return (
			<div className="refinement-checkbox-container">
				<label>
					<input
						className="refinement-checkbox"
						name={checkboxValue}
						onChange={this.handleCheckboxChange}
						type="checkbox"
					/>

					<span className="refinement-checkbox-label">{checkboxLabel}</span>
				</label>
			</div>
		);
	}
}