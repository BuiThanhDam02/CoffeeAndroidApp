import axios from "axios";
import { BASE_URL } from "../Utils/apiURL";

const API = axios.create({ baseURL: BASE_URL });

export const getAllCoffee = () => {
  return API.get("coffee/all");
};

export const deleteCoffee = (id) => {
  return API.delete("coffee/delete/" + id);
};
