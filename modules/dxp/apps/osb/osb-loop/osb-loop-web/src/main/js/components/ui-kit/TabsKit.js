import Component, {Config} from 'metal-jsx';
import {range} from 'lodash';

import Card from '../card';
import ElementContainer from './ElementContainer';
import Kit from './Kit';
import Tabs from '../Tabs';

const contentStyles = {
	padding: '16px'
};

const elementContainerStyles = {
	display: 'block'
};

class TabsKit extends Component {
	created() {
		this.handleTabIndexChange_ = this.handleTabIndexChange_.bind(this);
	}

	handleTabIndexChange_(index) {
		this.state.tabIndex_ = index;
	}

	render() {
		return (
			<Kit card={false} header="Tabs">
				<ElementContainer header="align top" style={elementContainerStyles}>
					<Card>
						<Tabs
							index={this.state.tabIndex_}
							onIndexChange={this.handleTabIndexChange_}
						>
							{
								range(5).map(
									i => {
										return <Tabs.Content key={i} name={`Tab ${i}`} style={contentStyles}>{`Content ${i}`}</Tabs.Content>;
									}
								)
							}
						</Tabs>
					</Card>
				</ElementContainer>

				<ElementContainer header="align left" style={elementContainerStyles}>
					<Card>
						<Tabs
							align="left"
							index={this.state.tabIndex_}
							onIndexChange={this.handleTabIndexChange_}
						>
							{
								range(5).map(
									i => {
										return <Tabs.Content key={i} name={`Tab ${i}`} style={contentStyles}>{`Content ${i}`}</Tabs.Content>;
									}
								)
							}
						</Tabs>
					</Card>
				</ElementContainer>

				<ElementContainer header="renderAll = true, uses 'display: none;'" style={elementContainerStyles}>
					<Card>
						<Tabs
							align="left"
							index={this.state.tabIndex_}
							onIndexChange={this.handleTabIndexChange_}
							renderAll={true}
						>
							{
								range(5).map(
									i => {
										return <Tabs.Content key={i} name={`Tab ${i}`} style={contentStyles}>{`Content ${i}`}</Tabs.Content>;
									}
								)
							}
						</Tabs>
					</Card>
				</ElementContainer>
			</Kit>
		);
	}
}

TabsKit.STATE = {
	tabIndex_: Config.value(0)
};

export default TabsKit;