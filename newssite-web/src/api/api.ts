import axios from "axios";
import {News} from "@/models/news";

const axiosParams = {
        baseURL: process.env.REACT_APP_BASE_URL
};

const axiosInstance = axios.create(axiosParams)

function api(axios: any) {
    return {
        get:  (url:string, config = {}) => axios.get(url, config),
        post: (url:string, body:News, config = {}) => axios.post(url, body, config),
        put: (url:string, body:News, config = {}) => axios.put(url, body, config),
        patch: (url:string, body:News, config = {}) => axios.patch(url, body, config),
        delete: (url:string, config = {}) => axios.delete(url, config),
    };
};

export default api(axiosInstance);