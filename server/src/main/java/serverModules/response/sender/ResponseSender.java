package serverModules.response.sender;

import responses.Response;
import serializer.ObjectSerializer;
import serverModules.connection.ConnectionModule;

import java.net.DatagramPacket;
import java.net.InetAddress;

public class ResponseSender implements ResponseAble<Response> {

    @Override
    public void sendResponse(ConnectionModule module, InetAddress address, int port, Response response) {
        ObjectSerializer os = new ObjectSerializer();
        try {
            module.sendData(os.serialize(response), address, port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
