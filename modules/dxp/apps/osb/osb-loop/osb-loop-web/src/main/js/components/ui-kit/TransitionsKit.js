import Component, {Config} from 'metal-jsx';
import Transition from 'metal-css-transitions';
import {bindAll} from 'lodash';

import Button from '../Button';
import ElementContainer from './ElementContainer';
import ElementDisplay from './ElementDisplay';
import Kit from './Kit';

const styles = {position: 'relative'};

const transitions = [
	[
		'fade-in',
		'fade-out',
		'fade-in-out',
		'scale-in-out'
	],
	[
		'slide-up',
		'slide-down',
		'slide-left',
		'slide-right'
	]
];

class HandleTransition extends Component {
	created() {
		bindAll(
			this,
			'handleClick_',
			'handleAppear_'
		);
	}

	handleAppear_() {
		this.setState(
			{
				active_: true,
				appear_: false
			},
			() => {
				this.state.appear_ = true;
			}
		);
	}

	handleClick_() {
		this.state.active_ = !this.state.active_;
	}

	render() {
		const {active_, appear_} = this.state;
		const {name} = this.props;

		return (
			<div style={styles}>
				<div>
					<Button onClick={this.handleClick_} role="primary">
						{'Toggle'}
					</Button>
					<Button fill="inverted" onClick={this.handleAppear_}>
						{'Appear'}
					</Button>
				</div>

				{appear_ &&
					<Transition name={`transition-${name}`}>
						{active_ &&
							<div class="transition-container">{name}</div>
						}
					</Transition>
				}
			</div>
		);
	}
}

HandleTransition.PROPS = {
	name: Config.string()
};

HandleTransition.STATE = {
	active_: Config.value(true),
	appear_: Config.value(true)
};

class TransitionsKit extends Component {
	render() {
		return (
			<Kit header="Transitions">
				{
					transitions.map(
						(transArr, i) => (
							<ElementContainer key={i} style="min-height: 150px">
								{
									transArr.map(
										transName => (
											<ElementDisplay key={transName}>
												<HandleTransition name={transName} />
											</ElementDisplay>
										)
									)
								}
							</ElementContainer>
						)
					)
				}
			</Kit>
		);
	}
}

export default TransitionsKit;