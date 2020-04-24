import Component, {Config} from 'metal-jsx';
import {range} from 'lodash';

import Button from '../Button';
import ElementContainer from './ElementContainer';
import EntityCategoryCard from '../EntityCategoryCard';
import Kit from './Kit';
import LoopAssets from '../../tests/loop-assets';
import LoopConstants from '../../lib/loop-constants';

const {currentPerson} = LoopConstants;

const people_1 = LoopAssets.getPerson(1);

const people_3 = range(1, 4).map(
	item => LoopAssets.getPerson(item)
);

const people_5 = range(5, 10).map(
	item => LoopAssets.getPerson(item)
);

const people_10 = range(11, 21).map(
	item => LoopAssets.getPerson(item)
);

const defaultConfigs = {
	seeMoreLinkData: {
		link: '#',
		message: ''
	},
	style: {width: '264px'},
	title: 'My Title'
};

const mediumConfigs = {
	...defaultConfigs,
	style: {width: '362px'}
};

class EntityCategoryCardKit extends Component {
	created() {
		this.handleClick_ = this.handleClick_.bind(this);
	}

	handleClick_() {
		this.state.loading_ = !this.state.loading_;
	}

	render() {
		const {loading_} = this.state;

		return (
			<Kit card={false} header="EntityCategoryCard">
				<ElementContainer>
					<Button onClick={this.handleClick_} role="secondary">{'Toggle Loading'}</Button>
				</ElementContainer>

				<ElementContainer header="Default">
					<EntityCategoryCard
						{...defaultConfigs}
						categories={[
							{
								items: people_5,
								label: 'Group 1'
							},
							{
								items: people_10,
								label: 'Group 2'
							}
						]}
						entityCount={15}
						loading={loading_}
					/>
				</ElementContainer>

				<ElementContainer header="With See More">
					<EntityCategoryCard
						{...defaultConfigs}
						categories={[
							{
								items: people_5,
								label: 'Group 1'
							},
							{
								items: people_10,
								label: 'Group 2'
							}
						]}
						entityCount={15}
						loading={loading_}
						seeMoreLinkData={{
							link: '#',
							message: 'Look at more!'
						}}
					/>
				</ElementContainer>

				<ElementContainer header="Line Bottom">
					<EntityCategoryCard
						{...defaultConfigs}
						categories={[
							{
								items: currentPerson,
								lineBottom: true
							},
							{
								items: people_1,
								label: 'Group 1'
							}
						]}
						entityCount={1}
						loading={loading_}
					/>
				</ElementContainer>

				<ElementContainer header="Line Top">
					<EntityCategoryCard
						{...defaultConfigs}
						categories={[
							{
								items: people_1,
								label: 'Group 1'
							},
							{
								items: currentPerson,
								lineTop: true
							}
						]}
						entityCount={1}
						loading={loading_}
					/>
				</ElementContainer>

				<ElementContainer header="Two Lines">
					<EntityCategoryCard
						{...defaultConfigs}
						categories={[
							{
								items: people_1,
								label: 'Group 1'
							},
							{
								items: currentPerson,
								lineBottom: true,
								lineTop: true
							},
							{
								items: people_3,
								label: 'Group 2'
							}
						]}
						entityCount={4}
						loading={loading_}
					/>
				</ElementContainer>

				<ElementContainer header="Two Lines with Long Label">
					<EntityCategoryCard
						{...defaultConfigs}
						categories={[
							{
								items: people_1,
								label: 'VeryLongLabelThatShouldBeGracefullyHandled'
							},
							{
								items: currentPerson,
								lineBottom: true,
								lineTop: true
							},
							{
								items: people_3,
								label: 'Very Long Label That Should Be Gracefully Handled'
							}
						]}
						entityCount={4}
						loading={loading_}
					/>
				</ElementContainer>

				<ElementContainer header="Two Lines with Multiple People in the Middle">
					<EntityCategoryCard
						{...defaultConfigs}
						categories={[
							{
								items: people_1,
								label: 'Group 1'
							},
							{
								items: people_3,
								lineBottom: true,
								lineTop: true
							},
							{
								items: people_3,
								label: 'Group 2'
							}
						]}
						entityCount={6}
						loading={loading_}
					/>
				</ElementContainer>

				<ElementContainer header="Row with Display Size as Medium">
					<EntityCategoryCard
						{...mediumConfigs}
						categories={[
							{
								items: people_1,
								label: 'Group 1'
							},
							{
								items: currentPerson,
								lineBottom: true,
								lineTop: true
							},
							{
								items: people_3,
								label: 'Group 2'
							}
						]}
						displaySize="medium"
						entityCount={4}
						loading={loading_}
					/>
				</ElementContainer>

				<ElementContainer header="Column Display 2 Lines">
					<EntityCategoryCard
						{...defaultConfigs}
						categories={[
							{
								items: people_1,
								label: 'Group 1'
							},
							{
								items: currentPerson,
								lineBottom: true,
								lineTop: true
							},
							{
								items: people_3,
								label: 'Group 2'
							}
						]}
						displayDirection="column"
						entityCount={6}
						loading={loading_}
					/>
				</ElementContainer>

				<ElementContainer header="Column Display Line Top">
					<EntityCategoryCard
						{...defaultConfigs}
						categories={[
							{
								items: people_1,
								label: 'Group 1'
							},
							{
								items: currentPerson,
								lineTop: true
							}
						]}
						displayDirection="column"
						entityCount={1}
						loading={loading_}
					/>
				</ElementContainer>

				<ElementContainer header="Column Display Line Bottom with Long Name">
					<EntityCategoryCard
						{...defaultConfigs}
						categories={[
							{
								items: [
									{
										...currentPerson,
										name: 'A very long name that should wrap'
									}
								],
								lineBottom: true
							},
							{
								items: people_3,
								label: 'Group 2'
							}
						]}
						displayDirection="column"
						entityCount={3}
						loading={loading_}
					/>
				</ElementContainer>

				<ElementContainer header="Column Display With See More">
					<EntityCategoryCard
						{...defaultConfigs}
						categories={[
							{
								items: people_1,
								lineBottom: true
							},
							{
								items: people_10,
								label: 'Group 2'
							}
						]}
						displayDirection="column"
						entityCount={11}
						loading={loading_}
						seeMoreLinkData={{
							link: '#',
							message: 'Look at more!'
						}}
					/>
				</ElementContainer>

				<ElementContainer header="Column with Display Size as Medium">
					<EntityCategoryCard
						{...mediumConfigs}
						categories={[
							{
								items: people_1,
								label: 'Group 1'
							},
							{
								items: currentPerson,
								lineBottom: true,
								lineTop: true
							},
							{
								items: people_3,
								label: 'Group 2'
							}
						]}
						displayDirection="column"
						displaySize="medium"
						entityCount={6}
						loading={loading_}
					/>
				</ElementContainer>

				<ElementContainer header="Column with no count">
					<EntityCategoryCard
						{...defaultConfigs}
						categories={[
							{
								items: people_1,
								label: 'Group 1'
							},
							{
								items: currentPerson,
								lineBottom: true,
								lineTop: true
							},
							{
								items: people_3,
								label: 'Group 2'
							}
						]}
						displayDirection="column"
						loading={loading_}
					/>
				</ElementContainer>
			</Kit>
		);
	}
}

EntityCategoryCardKit.STATE = {
	loading_: Config.bool().value(false)
};

export default EntityCategoryCardKit;