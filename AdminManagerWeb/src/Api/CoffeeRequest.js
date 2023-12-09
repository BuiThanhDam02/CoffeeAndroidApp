import axios from "axios";
import { BASE_URL } from "../Utils/apiURL";

const API = axios.create({ baseURL: BASE_URL });

// export const getAllCoffee = () => {
//   return API.get("coffee/all");
// };

// export const deleteCoffee = (id) => {
//   return API.delete("coffee/delete/" + id);
// };

export const getAllCoffee = async () => {
  const response = await API.get("coffee/all");
  return response.data;
};

export const deleteCoffee = async (id) => {
  try {
    const response = await API.delete(`coffee/delete/${id}`);
    return response.data;
  } catch (error) {
    console.error(`Error deleting coffee with ID ${id}:`, error);
    throw error; // Throw the error to handle it in the calling code
  }
};

export const editCoffee = async (coffeeRequest) => {
  console.log(coffeeRequest);
  const response = await API.put(
    "coffee/edit/" + coffeeRequest.id,
    coffeeRequest
  );
  console.log(response.data);
  return response.data;
};
