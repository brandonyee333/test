import React, {Component} from 'react';
import PropTypes from 'prop-types';

export default class FilterSelect extends Component {
	static defaultProps = {
		autopopulate: false,
		cssClass: '',
		disabled: false,
		label: '',
		selected: ''
	};

	static propTypes = {
		autopopulate: PropTypes.bool,
		cssClass: PropTypes.string,
		disabled: PropTypes.bool,
		id: PropTypes.string.isRequired,
		label: PropTypes.string,
		onChange: PropTypes.func.isRequired,
		options: PropTypes.array.isRequired,
		placeholder: PropTypes.string.isRequired,
		selected: PropTypes.string
	};

	displayCurrentValue = () => {
		const {autopopulate, options} = this.props;

		let currentValue = '';

		if (autopopulate && options.length === 1) {
			currentValue = options[0].version;
		}

		return currentValue;
	};

	handleChange = event => {
		const {onChange} = this.props;

		onChange(event.currentTarget.value);
	};

	render() {
		const {
			cssClass,
			disabled,
			id,
			label,
			options,
			placeholder,
			selected
		} = this.props;

		return (
			<div className="filter">
				{label && (
					<label className="control-label" htmlFor={id}>
						{`${label}:`}
					</label>
				)}

				<select className={`form-control ${cssClass}`} disabled={disabled} id={id} name={id} onChange={this.handleChange} value={selected || this.displayCurrentValue()}>
					<option value="">{placeholder}</option>

					{!!options.length && options.map(
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