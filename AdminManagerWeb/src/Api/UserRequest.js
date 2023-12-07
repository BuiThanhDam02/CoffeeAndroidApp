import axios from "axios";
import { BASE_URL } from "../Utils/apiURL";

const API = axios.create({ baseURL: BASE_URL });

export const getAllUsers = () => {
  return API.get("user/all");
};

// export function getAllUsers() {
//   // return fetch(BASE_URL + "user/all").then((res) => res.json());
//   return API.get("user/all");
// }
