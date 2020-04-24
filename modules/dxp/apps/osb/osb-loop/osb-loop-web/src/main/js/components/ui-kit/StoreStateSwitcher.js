import Component, {Config} from 'metal-jsx';
import {applyMiddleware, bindActionCreators, createStore} from 'redux';
import {connect} from 'metal-redux';
import {isUndefined} from 'lodash';

import ElementContainer from './ElementContainer';
import RadioGroup from '../radio-group';
import {createReducer} from '../../lib/util';

class StoreStateSwitcher extends Component {
	created() {
		this.props.checked = this.props.stateKeys[0];

		this.handleRadioChange_ = this.handleRadioChange_.bind(this);
	}

	handleRadioChange_(value) {
		this.props.switchState(value);

		this.props.checked = value;
	}

	render() {
		const {checked, stateKeys} = this.props;

		return (
			<ElementContainer
				elementClasses="store-state-switcher-container"
				header="Toggle Store States"
			>
				<RadioGroup checked={checked} name="switchRadio" onChange={this.handleRadioChange_}>
					{stateKeys.map(
						key => (
							<RadioGroup.Option
								key={key}
								label={key}
								value={key}
							/>
						)
					)}
				</RadioGroup>
			</ElementContainer>
		);
	}
}

StoreStateSwitcher.PROPS = {
	checked: Config.string(),
	stateKeys: Config.array(),
	switchState: Config.func()
};

const normalizeActionTypes = store => next => action => {
	if (isUndefined(action.type)) {
		action.type = 'NO_OP';
	}

	return next(action);
};

const TYPE_SWITCH_STATE = 'SWITCH_STATE';

function switchState(stateKey) {
	return {
		stateKey,
		type: TYPE_SWITCH_STATE
	};
}

export default states => {
	const stateKeys = Object.keys(states);

	const initialState = states[stateKeys[0]];

	const reducer = createReducer(
		initialState,
		{
			[TYPE_SWITCH_STATE]: (state, action) => states[action.stateKey]
		}
	);

	return {
		store: createStore(reducer, applyMiddleware(normalizeActionTypes)),
		StoreStateSwitcher: connect(
			(state, ownProps) => (
				{
					stateKeys
				}
			),
			dispatch => bindActionCreators(
				{
					switchState
				},
				dispatch
			)
		)(StoreStateSwitcher)
	};
};