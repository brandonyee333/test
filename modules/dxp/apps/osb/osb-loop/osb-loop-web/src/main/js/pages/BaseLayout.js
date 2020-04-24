import Component, {Config} from 'metal-jsx';
import {List} from 'immutable';

import LoadingCard from '../components/LoadingCard';
import SubNav from '../components/SubNav';

class BaseLayout extends Component {
	render() {
		const {
			bottomNavContent,
			content,
			leftContent,
			loading,
			navItemsIList
		} = this.props;

		return (
			<div class="base-layout-container flex-row">
				<div class="flex-col-3">
					<SubNav align="left" bottomContent={bottomNavContent}>
						{
							navItemsIList.toJS().map(
								({count, href, label, selected, total}) => (
									<SubNav.Item href={href} key={href} selected={selected}>
										{label}

										{!!total &&
											<span class="count">
												{total}
											</span>
										}
									</SubNav.Item>
								)
							)
						}
					</SubNav>

					{leftContent}
				</div>

				{!loading &&
					content
				}

				{!!loading &&
					<BaseLayout.NineColumn key="loadingContainer">
						<LoadingCard />
					</BaseLayout.NineColumn>
				}
			</div>
		);
	}
}

BaseLayout.PROPS = {
	bottomNavContent: Config.any(),
	content: Config.array(),
	leftContent: Config.array(),
	loading: Config.bool().value(false),
	navItemsIList: Config.instanceOf(List),
	rightContent: Config.array()
};

class FourColumn extends Component {
	render() {
		return <div class="flex-col-4">{this.props.children}</div>;
	}
}

class NineColumn extends Component {
	render() {
		return <div class="flex-col-9">{this.props.children}</div>;
	}
}

class Row extends Component {
	render() {
		return <div class="flex-row">{this.props.children}</div>;
	}
}

class SixColumn extends Component {
	render() {
		return <div class="flex-col-6">{this.props.children}</div>;
	}
}

class ThreeColumn extends Component {
	render() {
		return <div class="flex-col-3">{this.props.children}</div>;
	}
}

BaseLayout.FourColumn = FourColumn;
BaseLayout.NineColumn = NineColumn;
BaseLayout.Row = Row;
BaseLayout.SixColumn = SixColumn;
BaseLayout.ThreeColumn = ThreeColumn;

export default BaseLayout;