import { createContext, useContext, useState } from "react";

const RouteContext = createContext();

export function RouteProvider({ children }) {

    const [locations, setLocations] = useState([]);

    const [route, setRoute] = useState(null);

    const [loading, setLoading] = useState(false);

    return (

        <RouteContext.Provider
            value={{
                locations,
                setLocations,
                route,
                setRoute,
                loading,
                setLoading
            }}
        >

            {children}

        </RouteContext.Provider>

    );

}

export function useRouteContext() {

    return useContext(RouteContext);

}