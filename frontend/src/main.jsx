import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import "./index.css";
import "leaflet/dist/leaflet.css";
import "./config/leafletConfig";
import App from "./App";
import { RouteProvider } from "./context/RouteContext";
import { TrafficProvider } from "./context/TrafficContext";

createRoot(document.getElementById("root")).render(
  <StrictMode>
    <RouteProvider>
      <TrafficProvider>
        <App />
      </TrafficProvider>
    </RouteProvider>
  </StrictMode>,
);