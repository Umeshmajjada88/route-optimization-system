import { Client } from "@stomp/stompjs";

let client = null;

export function connectTrafficSocket(onMessage) {

    client = new Client({

        brokerURL: "ws://localhost:8080/ws",

        reconnectDelay: 5000,

        onConnect: () => {

            console.log("Connected to WebSocket");

            client.subscribe("/topic/traffic", message => {

                onMessage(JSON.parse(message.body));

            });

        }

    });

    client.activate();

}

export function disconnectTrafficSocket() {

    if (client) {

        client.deactivate();

    }

}