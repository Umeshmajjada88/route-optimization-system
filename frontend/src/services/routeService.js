import api from "../api/axiosConfig";

export const optimizeRoute = async (request) => {

    const response = await api.post(
        "/api/routes/optimize",
        request
    );

    return response.data.data;

};