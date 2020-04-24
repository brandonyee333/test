import Component, {Config} from 'metal-jsx';
import {Map} from 'immutable';

import SeeMore from '../SeeMore';
import ElementContainer from './ElementContainer';
import Kit from './Kit';

class SeeMoreExample extends Component {
	created() {
		this.handleMessageInfoMessage_ = this.handleMessageInfoMessage_.bind(this);
	}

	handleMessageInfoMessage_() {
		this.props.message = this.props.messageInfoIMap.get('message');
	}

	render() {
		const {
			handleMessageInfoMessage_,
			href,
			message,
			messageInfoIMap,
			truncated
		} = this.props;

		return (
			<div>
				<div>{message}</div>

				<SeeMore
					href={href}
					messageInfoIMap={messageInfoIMap}
					onSeeMore={handleMessageInfoMessage_}
					truncated={truncated}
				/>
			</div>
		);
	}
}

SeeMoreExample.PROPS = {
	href: Config.number().value(0),
	message: Config.string(),
	messageInfoIMap: Config.instanceOf(Map).value(Map()),
	truncated: Config.bool()
};

class SeeMoreKit extends Component {
	render() {
		return (
			<Kit header="SeeMore">
				<ElementContainer header="Not Truncated">
					<SeeMoreExample
						message="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce eleifend condimentum fermentum. Donec vehicula lacus vel magna eleifend, et vehicula libero venenatis. Aliquam dolor nunc, congue et porttitor et, pulvinar sed est. Duis pharetra magna ac erat congue, sit amet molestie nisi consectetur."
						messageInfoIMap={Map()}
						truncated={false}
					/>
				</ElementContainer>

				<ElementContainer header="Truncated Once">
					<SeeMoreExample
						message="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce eleifend condimentum fermentum. Donec vehicula lacus vel magna eleifend, et vehicula libero venenatis. Aliquam dolor nunc, congue et porttitor et, pulvinar sed est. Duis pharetra magna ac erat congue, sit..."
						messageInfoIMap={
							Map(
								{
									content: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce eleifend condimentum fermentum. Donec vehicula lacus vel magna eleifend, et vehicula libero venenatis. Aliquam dolor nunc, congue et porttitor et, pulvinar sed est. Duis pharetra magna ac erat congue, sit amet molestie nisi consectetur. Pellentesque feugiat feugiat neque, ac bibendum urna commodo sit amet. Suspendisse finibus arcu lacus, et convallis magna dictum ut. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Sed finibus orci venenatis lorem maximus consectetur. Phasellus vel volutpat metus. Phasellus sit amet ligula nisl.',
									truncated: false
								}
							)
						}
						truncated={true}
					/>
				</ElementContainer>

				<ElementContainer header="Truncated Twice">
					<SeeMoreExample
						href={'#'}
						message="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce eleifend condimentum fermentum. Donec vehicula lacus vel magna eleifend, et vehicula libero venenatis. Aliquam dolor nunc, congue et porttitor et, pulvinar sed est. Duis pharetra magna ac erat congue, sit..."
						messageInfoIMap={
							Map(
								{
									message: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce eleifend condimentum fermentum. Donec vehicula lacus vel magna eleifend, et vehicula libero venenatis. Aliquam dolor nunc, congue et porttitor et, pulvinar sed est. Duis pharetra magna ac erat congue, sit amet molestie nisi consectetur. Pellentesque feugiat feugiat neque, ac bibendum urna commodo sit amet. Suspendisse finibus arcu lacus, et convallis magna dictum ut. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Sed finibus orci venenatis lorem maximus consectetur. Phasellus vel volutpat metus. Phasellus sit amet ligula nisl.',
									truncated: true
								}
							)
						}
						truncated={true}
					/>
				</ElementContainer>
			</Kit>
		);
	}
}

export default SeeMoreKit;