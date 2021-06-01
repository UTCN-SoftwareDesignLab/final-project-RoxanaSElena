import SockJS from "sockjs-client";
import Stomp from "webstomp-client";

export default {
  connect() {
    let connected = false;
    this.socket = new SockJS("http://localhost:8088/gs-guide-websocket");
    this.stompClient = Stomp.over(this.socket);
    this.stompClient.connect(
      { "Access-Control-Allow-Origin": "*" },
      () => {
        connected = true;
      },
      (error) => {
        console.log(error);
        connected = false;
      }
    );
    return connected;
  },
  connectAndSubscribe() {
    console.log("AICI");
    this.socket = new SockJS("http://localhost:8088/gs-guide-websocket");
    this.stompClient = Stomp.over(this.socket);
    this.stompClient.connect(
      { "Access-Control-Allow-Origin": "*" },
      () => {
        this.connected = true;
        this.stompClient.subscribe(
          "/d/message",
          (tick) => {
            let response = JSON.parse(tick.body);

            this.alertFunc(response.message);
          },
          { "Access-Control-Allow-Origin": "*" }
        );
      },
      (error) => {
        console.log(error);
        this.connected = false;
      }
    );
  },
  disconnect() {
    let connected = false;
    if (this.stompClient) {
      this.stompClient.disconnect();
    }
    connected = false;
    return connected;
  },
};
