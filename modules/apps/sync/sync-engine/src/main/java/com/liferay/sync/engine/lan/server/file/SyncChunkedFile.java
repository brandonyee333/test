/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.lan.server.file;

import com.liferay.sync.engine.util.OSDetector;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.stream.ChunkedInput;

import java.io.IOException;

import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;

/**
 * @author Dennis Ju
 */
public class SyncChunkedFile implements ChunkedInput<ByteBuf> {

	public SyncChunkedFile(
			Path path, long length, int chunkSize, long modifiedTime)
		throws IOException {

		this(path, 0L, length, chunkSize, modifiedTime);
	}

	public SyncChunkedFile(
			Path path, long offset, long length, int chunkSize,
			long modifiedTime)
		throws IOException {

		if (offset != 0L) {
			_fileChannel.position(offset);
		}

		_path = path;
		_offset = offset;
		_chunkSize = chunkSize;
		_modifiedTime = modifiedTime;

		_startOffset = offset;
		_endOffset = offset + length;

		if (OSDetector.isWindows()) {
			_closeAggressively = true;
		}
		else {
			_closeAggressively = false;
		}
	}

	@Override
	public void close() throws Exception {
		if (_fileChannel != null) {
			_fileChannel.close();
		}

		_closed = true;
	}

	@Override
	public boolean isEndOfInput() throws Exception {
		if ((_offset >= _endOffset) || _closed) {
			return true;
		}

		return false;
	}

	@Override
	public long length() {
		return _endOffset - _startOffset;
	}

	@Override
	public long progress() {
		return _offset - _startOffset;
	}

	@Override
	public ByteBuf readChunk(ByteBufAllocator byteBufAllocator)
		throws Exception {

		long offset = _offset;

		if (offset >= _endOffset) {
			return null;
		}

		int chunkSize = (int)Math.min((long)_chunkSize, _endOffset - offset);

		ByteBuf byteBuf = byteBufAllocator.buffer(chunkSize);

		boolean release = true;

		try {
			FileTime currentFileTime = Files.getLastModifiedTime(
				_path, LinkOption.NOFOLLOW_LINKS);

			long currentTime = currentFileTime.toMillis();

			if (currentTime != _modifiedTime) {
				throw new Exception("File modified during transfer: " + _path);
			}

			int bytesRead = 0;

			if (_closeAggressively || (_fileChannel == null)) {
				_fileChannel = FileChannel.open(_path);

				_fileChannel.position(_offset);
			}

			while (true) {
				int localBytesRead = byteBuf.writeBytes(
					_fileChannel, chunkSize - bytesRead);

				if (localBytesRead >= 0) {
					bytesRead += localBytesRead;

					if (bytesRead != chunkSize) {
						continue;
					}
				}

				_offset += bytesRead;

				release = false;

				return byteBuf;
			}
		}
		finally {
			if (_closeAggressively && (_fileChannel != null)) {
				_fileChannel.close();
			}

			if (release) {
				byteBuf.release();
			}
		}
	}

	/**
	 * @deprecated As of Judson (7.1.x), As of Netty 4.1.0, replaced by {@link
	 *             #readChunk(ByteBufAllocator)}
	 */
	@Deprecated
	@Override
	public ByteBuf readChunk(ChannelHandlerContext channelHandlerContext)
		throws Exception {

		return readChunk(channelHandlerContext.alloc());
	}

	private final int _chunkSize;
	private final boolean _closeAggressively;
	private boolean _closed;
	private final long _endOffset;
	private FileChannel _fileChannel;
	private final long _modifiedTime;
	private long _offset;
	private final Path _path;
	private final long _startOffset;

}