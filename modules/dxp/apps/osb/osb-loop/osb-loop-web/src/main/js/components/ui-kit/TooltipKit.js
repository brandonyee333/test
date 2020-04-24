import Component from 'metal-jsx';

import ElementContainer from './ElementContainer';
import ElementDisplay from './ElementDisplay';
import Kit from './Kit';
import Tooltip from '../Tooltip';

class TooltipKit extends Component {
	render() {
		const childStyles = {
			'background-color': '#000',
			'border-radius': '4px',
			'box-sizing': 'border-box',
			'margin': '10px',
			'padding': '4px'
		};

		const targetStyles = {
			'background-color': '#1F7AFF',
			'border-radius': '4px',
			'box-sizing': 'border-box',
			'color': '#FFF',
			'margin': '0 20px',
			'padding': '20px',
			'text-align': 'center'
		};

		return (
			<Kit header="Tooltip will display on hover">
				<ElementContainer header="Should not display tooltips">
					<ElementDisplay description="Does not have a child">
						<div style={targetStyles} title="Should not display Tooltip 2">{'Hover me! Nothing will happen.'}</div>
					</ElementDisplay>

					<ElementDisplay description="Does have a child">
						<div style={targetStyles} title="Should not display Tooltip 1">
							<div style={childStyles}>{'Hover me! Nothing will happen.'}</div>
						</div>
					</ElementDisplay>
				</ElementContainer>

				<ElementContainer header="Should display tooltips">
					<ElementDisplay description="Does not have child">
						<div data-tooltip style={targetStyles} title={'Should display Tooltip 2'}>{'Hover me! I don\'t have a child.'}</div>
					</ElementDisplay>

					<ElementDisplay description="Parent has a tooltip but child does not">
						<div data-tooltip style={targetStyles} title="Should display 1">
							{'Hover me! I have a child.'}

							<div style={childStyles}>{'Hover me! I\'m the child.'}</div>
						</div>
					</ElementDisplay>

					<ElementDisplay description="Parent and child both have tooltips">
						<div data-tooltip style={targetStyles} title="Should display Tooltip 3">
							{'Hover me! I have a tooltip.'}

							<div data-tooltip style={childStyles} title="Should display Child Tooltip" >{'Hover me! I\'m a child with a tooltip.'}</div>
						</div>
					</ElementDisplay>

					<ElementDisplay description="Initial tooltip and response tooltip after click as well as copying data">
						<div
							data-clipboard-text="Hover me! I have a tooltip, you can copy me, and I have a response message!"
							data-tooltip-response={Liferay.Language.get('clicked')}
							style={targetStyles}
							title={Liferay.Language.get('click-to-copy')}
						>
							{'Hover me! I have a tooltip, you can copy me, and I have a response message!'}
						</div>
					</ElementDisplay>
				</ElementContainer>

				<Tooltip />
			</Kit>
		);
	}
}

export default TooltipKit;