import React, {Component} from 'react';
import PropTypes from 'prop-types';

export default class FilterCheckbox extends Component {
	static defaultProps = {
		checked: false
	};

	static propTypes = {
		checked: PropTypes.bool,
		handleOnChange: PropTypes.func.isRequired,
		label: PropTypes.string.isRequired,
		value: PropTypes.string.isRequired
	};

	handleOnChange = event => {
		const {handleOnChange} = this.props;

		handleOnChange(event);
	};

	render() {
		const {checked, label, value} = this.props;

		return (
			<div className="filter-checkbox-container">
				<label>
					<input
						className="filter-checkbox"
						checked={checked}
						name={label}
						onChange={this.handleOnChange}
						type="checkbox"
						value={value}
					/>

					<span className="filter-checkbox-label">{label}</span>
				</label>
			</div>
		);
	}
}