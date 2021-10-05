import axios from "axios";

const BASE_URL = "https://j5a305.p.ssafy.io:8443/api/v1";
const DEFAULT_ACCEPT_TYPE = "application/json; charset=utf-8";

axios.defaults.baseURL = BASE_URL;
axios.defaults.headers["Content-Type"] = DEFAULT_ACCEPT_TYPE;

export default { axios };
