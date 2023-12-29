import { mdiEye, mdiTrashCan,mdiPlus } from '@mdi/js'
import React, { useEffect, useState } from 'react'
import { deleteCoffee, getAllCoffee, getCoffeeById, useSampleClients } from '../../hooks/sampleData'
import { Client } from '../../interfaces'
import Button from '../Button'
import Buttons from '../Buttons'
import CardBoxModal from '../CardBox/Modal'
import UserAvatar from '../UserAvatar'
import { useRouter } from 'next/router';
const TableSampleProducts = () => {
  const router = useRouter();


  const [coffees,setCoffees] = useState([]);
  const perPage = 5

  const [currentPage, setCurrentPage] = useState(0)

  const coffeesPaginated = coffees.slice(perPage * currentPage, perPage * (currentPage + 1))

  const numPages = coffees.length / perPage

  const pagesList = []

  for (let i = 0; i < numPages; i++) {
    pagesList.push(i)
  }

  const [isModalInfoActive, setIsModalInfoActive] = useState(false)
  const [isModalTrashActive, setIsModalTrashActive] = useState(false)

  const handleModalAction = () => {
    setIsModalInfoActive(false)
    setIsModalTrashActive(false)
  }

  const viewDetailCoffee = (id)=>{
 
    router.push(`/coffeeformpage?id=${id}`);
    
  }

  const delCoffee = ({id}) =>{
      deleteCoffee({id:id})
      .then((data) =>{
        alert(data)
        window.location.reload
      }).catch((error) =>{
        alert(error)
      });
  }
    useEffect(() => {
      getAllCoffee()
        .then((data) => {
          setCoffees(data);
        })
        .catch((error) => {
          // Handle any errors that occur during data fetching
          console.error('Error fetching coffee data:', error);
        });
    }, []);


  return (
    <>


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
            <th>Supplier</th>
            <th>Status</th>
            <th>Price</th>
            <th>Created</th>
            <th />
          </tr>
        </thead>
        <tbody>
          {coffeesPaginated.map((coffee) => (
            <tr key={coffee.id}>
              <td className="border-b-0 lg:w-6 before:hidden">
                <UserAvatar api={coffee.imageLink} username={"Howell Hand"} className="w-24 h-24 mx-auto lg:w-6 lg:h-6" />
              </td>
              <td data-label="Name">{coffee.name}</td>
              <td data-label="Supplier">{coffee.supplier.name}</td>
              <td data-label="Status">{coffee.status}</td>
              <td data-label="Price">{coffee.price}</td>
              <td data-label="Created" className="lg:w-1 whitespace-nowrap">
                <small className="text-gray-500 dark:text-slate-400">{new Date(coffee.created_at).toDateString()}</small>
              </td>
              <td className="before:hidden lg:w-1 whitespace-nowrap">
                <Buttons type="justify-start lg:justify-end" noWrap>
                       
                  <Button
                    color="info"
                    icon={ mdiEye}
                    onClick={() => viewDetailCoffee(coffee.id)}
                    small
                  />
                  <Button
                    color="danger"
                    icon={mdiTrashCan}
                    onClick={() => delCoffee({id:coffee.id})}
                    small
                  />
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

export default TableSampleProducts
