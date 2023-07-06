BEGIN
	BEGIN TRANSACTION;

	DELETE FROM BlogDaily WHERE channelId IN ( $CHANNEL_IDS$ );
	DELETE FROM BQEvent WHERE channelId IN ( $CHANNEL_IDS$ );
	DELETE FROM BQEventProperty WHERE channelId IN ( $CHANNEL_IDS$ );
	DELETE FROM BQIdentityActivitySummary WHERE channelId IN ( $CHANNEL_IDS$ );
	DELETE FROM BQIdentityInterestPage WHERE channelId IN ( $CHANNEL_IDS$ );
	DELETE FROM BQMembership WHERE segmentId IN ( $SEGMENT_IDS$ );
	DELETE FROM BQMembershipChange WHERE segmentId IN ( $SEGMENT_IDS$ );
	DELETE FROM BQMembershipIndividual WHERE segmentId IN ( $SEGMENT_IDS$ );
	DELETE FROM BQOrder WHERE channelId IN ( $CHANNEL_IDS$ );
	DELETE FROM BQOrder_Raw WHERE channelId IN ( $CHANNEL_IDS$ );
	DELETE FROM BQProduct WHERE channelId IN ( $CHANNEL_IDS$ );
	DELETE FROM BQProduct_Raw WHERE channelId IN ( $CHANNEL_IDS$ );
	DELETE FROM BQSession WHERE channelId IN ( $CHANNEL_IDS$ );
	DELETE FROM BQSessionInterestScore WHERE channelId IN ( $CHANNEL_IDS$ );
	DELETE FROM CustomAssetDaily WHERE channelId IN ( $CHANNEL_IDS$ );
	DELETE FROM DocumentLibraryDaily WHERE channelId IN ( $CHANNEL_IDS$ );
	DELETE FROM FormDaily WHERE channelId IN ( $CHANNEL_IDS$ );
	DELETE FROM JournalDaily WHERE channelId IN ( $CHANNEL_IDS$ );
	DELETE FROM PageDaily WHERE channelId IN ( $CHANNEL_IDS$ );

	COMMIT TRANSACTION;

EXCEPTION WHEN ERROR THEN
	SELECT @@error.message;
	ROLLBACK TRANSACTION;
END