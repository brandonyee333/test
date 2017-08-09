import {connect} from 'metal-redux';
import {isEmpty} from 'lodash';
import JSXComponent, {Config} from 'metal-jsx';

import Input from './Input';

import {fetchChildrenListTypes} from '../actions/list-types';

class DependentInput extends JSXComponent {
	render() {
		return <Input {...this.props} />;
	}

	getValue() {
		const {props} = this;

		const {
			fetchChildrenListTypes,
			listTypeValue,
			parentInputValue: parentWatsonListTypeId
		} = props;

		fetchChildrenListTypes(parentWatsonListTypeId, listTypeValue);
	}

	syncParentInputValue(newState, oldState) {
		if (newState && newState !== oldState) {
			this.getValue();
		}
		else if (!newState && oldState && this.props.value) {
			this.props.onChange('', this.props.inputId);
		}
	}

	syncValue(newState, oldState) {
		if (newState && newState !== oldState) {
			this.props.onChange(newState, this.props.inputId);
		}
	}
}

DependentInput.PROPS = {
	parentInputValue: Config.value(null),
	value: Config.string().value('')
};

DependentInput.STATE = {};

function mapStateToProps(state, props) {
	const {listTypeValue, parentInputValue} = props;
	let {value} = props;

	const data = state.getIn(['list_types', 'data', parentInputValue, listTypeValue]);

	if (!isEmpty(data)) {
		const key = Object.keys(data);

		value = data[key].label;
	}

	return {
		loading: state.getIn(
			[
				'list_types',
				'loading'
			]
		),
		value
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

export default connect(mapStateToProps, mapDispatchToProps)(DependentInput);