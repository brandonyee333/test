import {bindAll, isEmpty} from 'lodash';
import {connect} from 'metal-redux';
import JSXComponent, {Config} from 'metal-jsx';

import SelectInput from './SelectInput';

import {fetchChildrenListTypes} from '../actions/list-types';

class DependentSelectInput extends JSXComponent {
	created() {
		bindAll(
			this,
			'getInputOptions'
		);
	}

	getInputOptions() {
		const {props} = this;

		const {
			fetchChildrenListTypes,
			listTypeValue,
			parentInputValue: parentWatsonListTypeId
		} = props;

		fetchChildrenListTypes(parentWatsonListTypeId, listTypeValue);
	}

	render() {
		const {props} = this;

		const {
			defaultOptions,
			loading,
			requestOptions,
			showDefaultOptions
		} = props;

		const options = (showDefaultOptions && isEmpty(requestOptions)) ? defaultOptions : requestOptions;

		return (
			<SelectInput {...props} options={options} optionsLoading={loading} />
		);
	}

	syncParentInputValue(newState, oldState) {
		if (newState && newState !== oldState) {
			this.getInputOptions();
		}

		const clearValue = (newState && oldState && newState !== oldState) || (!newState && oldState);

		const {props} = this;

		if (clearValue && props.value) {
			props.onChange('', props.inputId);
		}
	}
}

DependentSelectInput.PROPS = {
	defaultOptions: Config.any(),
	inputId: Config.string(),
	loading: Config.bool(),
	onChange: Config.func(),
	parentInputValue: Config.string(),
	requestOptions: Config.object(),
	showDefaultOptions: Config.bool(),
	value: Config.string().value('')
};

DependentSelectInput.STATE = {};

function mapStateToProps(state, props) {
	const {listTypeValue, parentInputValue} = props;

	const requestOptions = state.getIn(['list_types', 'data', parentInputValue, listTypeValue]) || {};

	return {
		loading: state.getIn(
			[
				'list_types',
				'loading'
			]
		),
		requestOptions
	};
}

function mapDispatchToProps(dispatch) {
	return {
		fetchChildrenListTypes: (parentWatsonListTypeId, listTypeValue) => {
			dispatch(
				fetchChildrenListTypes(parentWatsonListTypeId, listTypeValue)
			);
		}
	};
}

export default connect(mapStateToProps, mapDispatchToProps)(DependentSelectInput);