import { createContext, useContext, useState } from "react";

const TrafficContext = createContext();

export function TrafficProvider({ children }) {
  const [trafficUpdated, setTrafficUpdated] = useState(false);

  return (
    <TrafficContext.Provider
      value={{
        trafficUpdated,
        setTrafficUpdated,
      }}
    >
      {children}
    </TrafficContext.Provider>
  );
}

export function useTrafficContext() {
  return useContext(TrafficContext);
}
