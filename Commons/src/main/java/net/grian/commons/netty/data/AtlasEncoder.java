package net.grian.commons.netty.data;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.CharsetUtil;
import net.grian.commons.Global;
import net.grian.commons.netty.Packet;

public class AtlasEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet packet, ByteBuf buf) throws Exception {
        buf.writeInt(packet.packetId());
        byte[] data = Global.MAPPER.writeValueAsString(packet).getBytes(CharsetUtil.UTF_8);
        buf.writeInt(data.length);
        buf.writeBytes(data);
        buf.capacity(buf.readableBytes());
    }



}
