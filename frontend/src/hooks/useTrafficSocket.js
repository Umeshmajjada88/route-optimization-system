import { useEffect } from "react";
import {
    connectTrafficSocket,
    disconnectTrafficSocket
} from "../websocket/trafficSocket";
import { useTrafficContext } from "../context/TrafficContext";

export default function useTrafficSocket() {

    const { setTrafficUpdated } = useTrafficContext();

    useEffect(() => {

        connectTrafficSocket(() => {

            setTrafficUpdated(true);

        });

        return () => {

            disconnectTrafficSocket();

        };

    }, []);

}