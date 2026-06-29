import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import "./index.css";
import "leaflet/dist/leaflet.css";
import "./config/leafletConfig";
import App from "./App";
import { RouteProvider } from "./context/RouteContext";

createRoot(document.getElementById("root")).render(

    <StrictMode>

        <RouteProvider>

            <App />

        </RouteProvider>

    </StrictMode>

);