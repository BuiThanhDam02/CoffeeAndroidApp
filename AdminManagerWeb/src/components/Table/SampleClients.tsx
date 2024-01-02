import { mdiEye, mdiTrashCan } from '@mdi/js'
import React, { useState, useEffect } from 'react'

import { useSampleClients, getAllClient } from '../../hooks/sampleData'
import { Client } from '../../interfaces'
import Button from '../Button'
import Buttons from '../Buttons'
import CardBoxModal from '../CardBox/Modal'
import UserAvatar from '../UserAvatar'
import { useRouter } from 'next/router'

const TableSampleClients = () => {
  const router = useRouter()
  const [clients, setClients] = useState([])

  // const { clients } = useSampleClients()

  const perPage = 5

  const [currentPage, setCurrentPage] = useState(0)

  const clientsPaginated = clients.slice(perPage * currentPage, perPage * (currentPage + 1))

  const numPages = Math.ceil(clients.length / perPage)

  const pagesList = []

  for (let i = 0; i < numPages; i++) {
    pagesList.push(i)
  }

  const [isModalInfoActive, setIsModalInfoActive] = useState(false)
  const [isModalTrashActive, setIsModalTrashActive] = useState(false)

  const viewDetailUser = (id) => {
    router.push(`/userformpage?id=${id}`)
  }

  const handleModalAction = () => {
    setIsModalInfoActive(false)
    setIsModalTrashActive(false)
  }

  useEffect(() => {
    getAllClient()
      .then((data) => {
        setClients(data)
      })
      .catch((error) => {
        // Handle any errors that occur during data fetching
        console.error('Error fetching order data:', error)
      })
  }, [])

  return (
    <>
      <CardBoxModal
        title="Sample modal"
        buttonColor="info"
        buttonLabel="Done"
        isActive={isModalInfoActive}
        onConfirm={handleModalAction}
        onCancel={handleModalAction}
      >
        <p>
          Lorem ipsum dolor sit amet <b>adipiscing elit</b>
        </p>
        <p>This is sample modal</p>
      </CardBoxModal>

      <CardBoxModal
        title="Please confirm"
        buttonColor="danger"
        buttonLabel="Confirm"
        isActive={isModalTrashActive}
        onConfirm={handleModalAction}
        onCancel={handleModalAction}
      >
        <p>
          Lorem ipsum dolor sit amet <b>adipiscing elit</b>
        </p>
        <p>This is sample modal</p>
      </CardBoxModal>

      <table>
        <thead>
          <tr>
            <th />
            <th>Name</th>
            <th>Address</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Role</th>
            <th />
          </tr>
        </thead>
        <tbody>
          {clientsPaginated.map((client) => (
            <tr key={client.id}>
              <td className="border-b-0 lg:w-6 before:hidden">
                <UserAvatar
                  api={'http://localhost:3000/admin-one-react-tailwind/user.png'}
                  username={client.name}
                  className="w-24 h-24 mx-auto lg:w-6 lg:h-6"
                />
              </td>
              <td data-label="Name">{client.name}</td>
              <td data-label="Address">{client.address}</td>
              <td data-label="Email">{client.email}</td>
              <td data-label="Phone">{client.phone}</td>
              <td data-label="Role" className="lg:w-1 whitespace-nowrap">
                <small className="text-gray-500 dark:text-slate-400">{client.roles[0]?.name}</small>
              </td>
              <td className="before:hidden lg:w-1 whitespace-nowrap">
                <Buttons type="justify-start lg:justify-end" noWrap>
                  <Button
                    color="info"
                    icon={mdiEye}
                    onClick={() => viewDetailUser(client.id)}
                    small
                  />
                  {/* <Button
                    color="danger"
                    icon={mdiTrashCan}
                    onClick={() => setIsModalTrashActive(true)}
                    small
                  /> */}
                </Buttons>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <div className="p-3 lg:px-6 border-t border-gray-100 dark:border-slate-800">
        <div className="flex flex-col md:flex-row items-center justify-between py-3 md:py-0">
          <Buttons>
            {pagesList.map((page) => (
              <Button
                key={page}
                active={page === currentPage}
                label={page + 1}
                color={page === currentPage ? 'lightDark' : 'whiteDark'}
                small
                onClick={() => setCurrentPage(page)}
              />
            ))}
          </Buttons>
          <small className="mt-6 md:mt-0">
            Page {currentPage + 1} of {numPages}
          </small>
        </div>
      </div>
    </>
  )
}

export default TableSampleClients
