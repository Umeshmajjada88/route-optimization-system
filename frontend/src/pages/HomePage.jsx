import { useEffect, useState } from "react";
import { getHealth } from "../services/healthService";

function HomePage() {
    const [health, setHealth] = useState(null);

    useEffect(() => {
        getHealth()
            .then((response) => {
                console.log("Backend Response:", response);
                setHealth(response);
            })
            .catch((error) => {
                console.error("API Error:", error);
            });
    }, []);

    return (
        <div style={{ padding: "40px" }}>
            <h1>Route Optimization System</h1>

            <h3>Backend Response</h3>

            <pre>{JSON.stringify(health, null, 2)}</pre>
        </div>
    );
}

export default HomePage;