import useSWR from 'swr'
import axios from 'axios'
const fetcher = (url: string) => fetch(url).then((res) => res.json())
const API = axios.create({baseURL: 'http://localhost:8080/api/'});

/// here COFFEE
export const getAllCoffee = async () => { 
  const {data} = await API.get("coffee/all");
  return data
};

export const getCoffeeById = async ({id}) =>{
  const {data} = await API.get(`coffee/get/${id}`);

  return data

}

export const updateCoffee = async ({coffee}) =>{
  const {data} = await API.put(`coffee/edit/${coffee.id}`,coffee);
  return data;
}
export const deleteCoffee = async ({id}) =>{
  const {data} = await API.delete(`coffee/delete/${id}`);
  return data;
}
/// here supplier
export const getAllSupplier = async () => { 
  const {data} = await API.get("supplier/getAll");
  return data
};
// here order
export const getAllOrder = async () => { 
  const {data} = await API.get("order/getAll");
  return data
};

export const getOrderDetailById = async ({id}) =>{
  const {data} = await API.get(`order/detail/${id}`);
  console.log(data);
  return data

}

export const deleteOrder= async ({id}) =>{
  const {data} = await API.delete(`order/delete/${id}`);
  console.log(data);
  return data
}

///
export const useSampleClients = () => {
  const { data, error } = useSWR('/admin-one-react-tailwind/data-sources/clients.json', fetcher)

  return {
    clients: data?.data ?? [],
    isLoading: !error && !data,
    isError: error,
  }
}

export const useSampleTransactions = () => {
  const { data, error } = useSWR('/admin-one-react-tailwind/data-sources/history.json', fetcher)

  return {
    transactions: data?.data ?? [],
    isLoading: !error && !data,
    isError: error,
  }
}
