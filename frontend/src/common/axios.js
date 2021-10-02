import axios from "axios";

const BASE_URL = "http://j5a305.p.ssafy.io:8080/api/v1";
const DEFAULT_ACCEPT_TYPE = "application/json; charset=utf-8";

axios.defaults.baseURL = BASE_URL;
axios.defaults.headers["Content-Type"] = DEFAULT_ACCEPT_TYPE;

export default { axios };
