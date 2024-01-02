import useSWR from 'swr'
import axios from 'axios'
import { log } from 'console'
const fetcher = (url: string) => fetch(url).then((res) => res.json())
const API = axios.create({ baseURL: 'http://localhost:8080/api/' })
/// here upload
export const uploadCoffeeImage = async ({ formdata }) => {
  const { data } = await API.post('admin/upload/coffee', formdata)
  console.log(data)
  return data
}
export const uploadSupplierImage = async ({ formdata }) => {
  const { data } = await API.post('admin/upload/supplier', formdata)
  console.log(data)
  return data
}
/// here COFFEE
export const getAllCoffee = async () => {
  const { data } = await API.get('coffee/all')
  return data
}
export const addCoffee = async ({ coffee }) => {
  const { data } = await API.post(`coffee/add`, coffee)
  return data
}
export const getCoffeeById = async ({ id }) => {
  const { data } = await API.get(`coffee/get/${id}`)

  return data
}

export const updateCoffee = async ({ coffee }) => {
  const { data } = await API.put(`coffee/edit/${coffee.id}`, coffee)
  return data
}
export const deleteCoffee = async ({ id }) => {
  const { data } = await API.delete(`coffee/delete/${id}`)
  return data
}
/// here supplier
export const getAllSupplier = async () => {
  const { data } = await API.get('supplier/getAll')
  return data
}

export const getSuppliers = async () => {
  const { data } = await API.get('supplier/get')
  return data
}

export const getSupplierByID = async ({ id }) => {
  const { data } = await API.get(`supplier/get/${id}`)
  return data
}

export const updateSupplier = async ({ supplier }) => {
  const { data } = await API.put(`supplier/update/${supplier.id}`, supplier)
  return data
}
export const createSupplier = async ({ supplier }) => {
  const { data } = await API.post(`supplier/add`, supplier)
  return data
}
// here order
export const getAllOrder = async () => {
  const { data } = await API.get('order/getAll')

  return data
}
export const getOrderDetailById = async ({ id }) => {
  const { data } = await API.get(`order/detail/${id}`)
  console.log(data)
  return data
}
export const addOrder = async ({ order }) => {
  const { data } = await API.post('order/add', order)

  return data
}
// user
export const getAllClient = async () => {
  const { data } = await API.get('user/all')
  console.log(data)
  return data
}

export const updateClient = async ({ client }) => {
  const { data } = await API.put(`user/update/${client.id}`, client)
  return data
}

export const getClientById = async ({ id }) => {
  const { data } = await API.get(`user/get/${id}`)
  return data
}

export const getAllRoles = async () => {
  const { data } = await API.get(`role/all`)
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
