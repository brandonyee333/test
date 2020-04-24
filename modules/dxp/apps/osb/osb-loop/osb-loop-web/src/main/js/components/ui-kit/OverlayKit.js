import Component, {Config} from 'metal-jsx';
import {bindAll} from 'lodash';

import Overlay from '../Overlay';
import Button from '../Button';
import ElementContainer from './ElementContainer';
import ElementDisplay from './ElementDisplay';
import Kit from './Kit';

const HOVER_LABEL = 'Hover Me';

const styles = {
	maxWidth: '200px',
	padding: '20px'
};

class OverlayContent extends Component {
	render() {
		return (
			<div style={styles}>
				{'Sit obcaecati sed at eaque cupiditate atque sequi ad? Quod molestias incidunt tenetur sapiente ut, magnam nihil incidunt officia? Quaerat odit mollitia consectetur cupiditate ducimus unde? Placeat pariatur quod consequuntur veniam eum mollitia nesciunt laborum optio tempora laudantium labore, omnis. Nostrum minima saepe facilis recusandae aspernatur magni vel provident quod.'}

				<br />

				{this.props.recursive &&
					<Overlay vertical="topEdge">
						<Button role="primary">{HOVER_LABEL}</Button>
						<OverlayContent recursive={true} />
					</Overlay>
				}
			</div>
		);
	}
}

OverlayContent.PROPS = {
	recursive: Config.bool().value(false)
};

const horizontalOptions = [
	'leftEdge',
	'left',
	'center',
	'right',
	'rightEdge'
];

const verticalOptions = [
	'topEdge',
	'top',
	'center',
	'bottom',
	'bottomEdge'
];

class OverlayKit extends Component {
	created() {
		bindAll(
			this,
			'handleActiveToggle_',
			'handleDisabledToggle_'
		);
	}

	handleActiveToggle_() {
		this.state.active_ = !this.state.active_;
	}

	handleDisabledToggle_() {
		this.state.disabled_ = !this.state.disabled_;
	}

	render() {
		const {active_, disabled_} = this.state;
		const {handleActiveToggle_, handleDisabledToggle_} = this;

		return (
			<Kit header="Overlay">
				<ElementContainer header="Trigger">
					<ElementDisplay>
						<Overlay vertical="topEdge">
							<Button role="primary">{HOVER_LABEL}</Button>
							<OverlayContent />
						</Overlay>
					</ElementDisplay>
				</ElementContainer>

				<ElementContainer header="Nested">
					<ElementDisplay>
						<Overlay vertical="topEdge">
							<Button role="primary">{HOVER_LABEL}</Button>
							<OverlayContent recursive={true} />
						</Overlay>
					</ElementDisplay>
				</ElementContainer>

				<ElementContainer header="Horizontal Alignment">
					{horizontalOptions.map(
						option => (
							<ElementDisplay key={option}>
								<Overlay horizontal={option}>
									<Button role="primary">{option}</Button>
									<OverlayContent />
								</Overlay>
							</ElementDisplay>
						)
					)}
				</ElementContainer>

				<ElementContainer header="Vertical Alignment">
					{verticalOptions.map(
						option => (
							<ElementDisplay key={option}>
								<Overlay vertical={option}>
									<Button role="primary">{option}</Button>
									<OverlayContent />
								</Overlay>
							</ElementDisplay>
						)
					)}
				</ElementContainer>

				<ElementContainer header="Controlled Active State">
					<ElementDisplay>
						<Overlay active={active_} vertical="topEdge">
							<Button onClick={handleActiveToggle_} role="primary">{'Click Me'}</Button>
							<OverlayContent />
						</Overlay>
					</ElementDisplay>
				</ElementContainer>

				<ElementContainer header="Delay">
					{[250, 500, 1000, 2000].map(
						delay => (
							<ElementDisplay key={delay}>
								<Overlay delay={delay}>
									<Button role="primary">{delay}</Button>
									<OverlayContent />
								</Overlay>
							</ElementDisplay>
						)
					)}
				</ElementContainer>

				<ElementContainer header="Disabled">
					<ElementDisplay>
						<Overlay disabled={disabled_} vertical="topEdge">
							<Button onClick={handleDisabledToggle_} role="primary">
								{`Toggle (${disabled_ ? 'Disabled' : 'Enabled'})`}
							</Button>
							<OverlayContent />
						</Overlay>
					</ElementDisplay>
				</ElementContainer>

				<ElementContainer header="offsetTop">
					{[20, 100, 200].map(
						offset => (
							<ElementDisplay key={offset}>
								<Overlay offsetTop={offset} vertical="bottomEdge">
									<Button role="primary">{offset}</Button>
									<OverlayContent />
								</Overlay>
							</ElementDisplay>
						)
					)}
				</ElementContainer>

				<ElementContainer header="offsetLeft">
					{[20, 100, 200].map(
						offset => (
							<ElementDisplay key={offset}>
								<Overlay horizontal="rightEdge" offsetLeft={offset}>
									<Button role="primary">{offset}</Button>
									<OverlayContent />
								</Overlay>
							</ElementDisplay>
						)
					)}
				</ElementContainer>
			</Kit>
		);
	}
}

OverlayKit.STATE = {
	active_: Config.value(false),
	disabled_: Config.value(false)
};

export default OverlayKit;