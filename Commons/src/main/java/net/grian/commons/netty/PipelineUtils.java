package net.grian.commons.netty;

import com.google.common.base.Supplier;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

public class PipelineUtils {

    private static final Supplier<LengthFieldBasedFrameDecoder> FrameDecoderSupplier = () -> new LengthFieldBasedFrameDecoder(2147483647, 0, 4, 0, 4);

    public static final LengthFieldPrepender GLOBAL_PREPENDER = new LengthFieldPrepender(4);

    public static LengthFieldBasedFrameDecoder getNewDecoder() {
        return FrameDecoderSupplier.get();
    }

}
