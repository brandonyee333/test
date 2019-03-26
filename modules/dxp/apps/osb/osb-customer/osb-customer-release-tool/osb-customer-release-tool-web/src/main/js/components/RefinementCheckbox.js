import React, {Component} from 'react';
import PropTypes from 'prop-types';

export default class RefinementCheckbox extends Component {
	static PropTypes = {
		checkboxLabel: PropTypes.string.isRequired,
		checkboxValue: PropTypes.string.isRequired,
		refineByCheckbox: PropTypes.func.isRequired
	};

	handleCheckboxChange = () => {
		const {checkboxLabel, refineByCheckbox} = this.props;

		refineByCheckbox(checkboxLabel);
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