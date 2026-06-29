import api from "../api/axiosConfig";

export const getAllLocations = async () => {

    const response = await api.get("/api/locations");

    return response.data.data;

};