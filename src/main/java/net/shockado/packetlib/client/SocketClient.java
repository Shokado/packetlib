package net.shockado.packetlib.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import net.shockado.packetlib.protocol.NetworkHandler;
import net.shockado.packetlib.protocol.PacketDecoder;
import net.shockado.packetlib.protocol.PacketEncoder;

public class SocketClient {

    private final ServerAddress serverAddress;
    private final NetworkHandler networkHandler;

    public SocketClient(ServerAddress serverAddress, NetworkHandler networkHandler) {
        this.serverAddress = serverAddress;
        this.networkHandler = networkHandler;
    }

    public void connect() throws InterruptedException {
        EventLoopGroup child = new NioEventLoopGroup();

        ChannelInitializer<SocketChannel> channelInitializer = new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel channel) {
                channel.pipeline().addLast(new PacketEncoder(), new PacketDecoder(), networkHandler);
            }
        };

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(child);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.handler(channelInitializer);

        try {
            ChannelFuture future = bootstrap.connect(serverAddress.getIp(), serverAddress.getPort()).sync();
            future.channel().closeFuture().sync();
        } finally {
            child.shutdownGracefully();
        }
    }
}