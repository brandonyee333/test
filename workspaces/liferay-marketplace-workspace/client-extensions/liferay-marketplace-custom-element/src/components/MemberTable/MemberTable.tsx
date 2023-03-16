import ClayTable from '@clayui/table';

import swapVert from '../../assets/icons/swap-vert.svg';

import './MemberTable.scss';
import {MemberTableRow} from './MemberTableRow';

export type MemberProps = {
	email: string;
	name: string;
	role: string;
};
interface MemberTableProps {
	members: MemberProps[];
	loading: boolean
}

export function MemberTable({members, loading}: MemberTableProps) {
	if (members.length === 0) {
		if (loading) {
			return (
				<div className="member-table-loading-container">
					<div className="member-table-loading-image-container">
						<img
							alt="Member Loading Image"
							className="member-table-loading-image"
						/>
					</div>
					<div className="member-table-loading-text">
						Members Loading . . .
					</div>
				</div>
			)
		} else {
			return (
				<div className="member-table-loading-container">
					<div className="member-table-loading-image-container">
						<img
							alt="Member Loading Image"
							className="member-table-loading-image"
						/>
						</div>
					<div className="member-table-no-apps-text">
						No apps yet
					</div>
					<div className="member-table-no-apps-description">
						Create new apps and they will show up here. Click on "<a href="/create-new-app">New App</a>" to start creating apps
					</div>
				</div>
			)
		}
	} else {
		return (
			<ClayTable borderless className="member-table-container">
				<ClayTable.Head>
					<ClayTable.Cell headingCell>
						<div className="member-table-header-name">
							<span className="member-table-header-text">
								Name
							</span>

							<img
								alt="Swap Vert"
								className="member-table-header-name-icon"
								src={swapVert}
							/>
						</div>
					</ClayTable.Cell>

					<ClayTable.Cell headingCell>
						<span className="member-table-header-text">Email</span>
					</ClayTable.Cell>

					<ClayTable.Cell headingCell>
						<span className="member-table-header-text">Role</span>
					</ClayTable.Cell>
				</ClayTable.Head>

				<ClayTable.Body>
					{members.map((member) => (
						<MemberTableRow member={member} key={member.name} />
					))}
				</ClayTable.Body>
			</ClayTable>
		);
	}
}
