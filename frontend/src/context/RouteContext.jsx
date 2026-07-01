import { createContext, useContext, useState } from "react";

const RouteContext = createContext();

export function RouteProvider({ children }) {

    const [locations, setLocations] = useState([]);

    const [route, setRoute] = useState(null);

    const [lastRequest, setLastRequest] = useState(null);

    const [loading, setLoading] = useState(false);

    const [error, setError] = useState(null);

    return (
      <RouteContext.Provider
        value={{
          locations,
          setLocations,

          route,
          setRoute,

          lastRequest,
          setLastRequest,

          loading,
          setLoading,

          error,
          setError,
        }}
      >
        {children}
      </RouteContext.Provider>
    );

}

export function useRouteContext() {

    return useContext(RouteContext);

}