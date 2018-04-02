#include "channel.h"
#include "byte_stream.h"

void network::Channel::send(const ChannelBuffer & buf)
{
	send(buf.ChannelBufferData(), buf.ChannelBufferSize());
}

void network::Channel::asyncSendCopy(const ChannelBuffer & buf)
{
	asyncSendCopy(buf.ChannelBufferData(), buf.ChannelBufferSize());
}

void network::Channel::asyncSendCopy(const void * bufferPtr, uint64_t length)
{
	std::unique_ptr<ByteStream> bs(new ByteStream((uint8_t*)bufferPtr, length));
	asyncSend(std::move(bs));
}
