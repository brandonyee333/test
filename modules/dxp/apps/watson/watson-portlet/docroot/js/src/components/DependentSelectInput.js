import {bindAll} from 'lodash';
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

		return (
			<SelectInput {...props} options={props.options} optionsLoading={props.loading} />
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
	inputId: Config.string(),
	loading: Config.bool(),
	onChange: Config.func(),
	options: Config.object(),
	parentInputValue: Config.string(),
	value: Config.string().value('')
};

DependentSelectInput.STATE = {
	inputOptions: Config.array().value([])
};

function mapStateToProps(state, props) {
	const {listTypeValue, parentInputValue} = props;

	const options = state.getIn(['list_types', 'data', parentInputValue, listTypeValue]) || {};

	return {
		loading: state.getIn(
			[
				'list_types',
				'loading'
			]
		),
		options
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