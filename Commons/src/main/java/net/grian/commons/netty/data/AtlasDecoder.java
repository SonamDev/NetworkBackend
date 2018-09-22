package net.grian.commons.netty.data;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import net.grian.commons.Protocol;

import java.util.List;

public class AtlasDecoder extends MessageToMessageDecoder<ByteBuf> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) {
        if(!buf.isReadable())
            buf.resetReaderIndex();
        int id = buf.readInt();
        out.add(Protocol.Packet.read(id, buf));
    }

}
