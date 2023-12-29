import { mdiBallotOutline, mdiMail,mdiPlus,mdiCloud,mdiHandCoin,mdiAlphabetLatin,mdiPhone, mdiEye, mdiTrashCan } from '@mdi/js'
import { Field, Form, Formik } from 'formik'
import Head from 'next/head'
import { ReactElement, useState,useEffect } from 'react'
import Button from '../components/Button'
import Buttons from '../components/Buttons'
import Divider from '../components/Divider'
import CardBox from '../components/CardBox'

import FormField from '../components/Form/Field'
import FormFilePicker from '../components/Form/FilePicker'
import LayoutAuthenticated from '../layouts/Authenticated'
import SectionMain from '../components/Section/Main'

import SectionTitleLineWithButton from '../components/Section/TitleLineWithButton'
import { getPageTitle } from '../config'
import { useRouter } from 'next/router'
import { addOrder, getAllCoffee, getAllSupplier, getCoffeeById, getOrderDetailById, updateCoffee } from '../hooks/sampleData'
import UserAvatar from '../components/UserAvatar'
import TableSampleProducts from '../components/Table/SampleProducts'
import CoffeeAdd from './coffeeadd'

const OrderAdd = () => {
  const router = useRouter();

  // Lấy giá trị của tham số 'id' từ URL
  const { id } = router.query;
  const [o,setO] = useState({
        "id": 0,
        "name": null,
        "user_id":null,
        "phone": null,
        "password": null,
        "address": null,
        "email": null,
        "status": 0,
        "statusInt": 0,
        "totalPrice": 0,
        "type": 'Tại chỗ',
        "created_at": Date.now(),
        "statusBg": null
});
const [CoffeeDetail,setCoffeeDetail] = useState([])

const [coffees,setCoffees] = useState([]);
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
const handleChange =(e) => {
  setO(pre=> {return{...pre,[e.target.name]:e.target.value}})
};


const handleSubmit =() => {
  const data = {
    "orderDTO": o,
  "coffeeDTOS": CoffeeDetail
  }
  addOrder({order:data})
  .then((data) =>{
  router.push('/orderpage');alert(data)})
  .catch((error) => alert(error));
}


//   updateCoffee({coffee:data})
//   .then((data) => { 
//     alert(data)
//     router.push(`/coffeeformpage?id=${c.id}`);
//   })
//   .catch((err) => {
//     alert(err)

//     });
// };

const addCoffee = ({coffee})=>{
  let oldQuantity = 0
    setCoffeeDetail((pre) => {
      const newC = pre.filter((c)=>{
        if(c.id === coffee.id) {
          oldQuantity = c.quantity
        }
        return c.id !== coffee.id
      })
      const c =  {
        "id": coffee.id,
        "name": coffee.name,
        "imageLink": coffee.imageLink,
        "supplierName": coffee.supplier.name,
        "quantity": 1 + oldQuantity,
        "price": coffee.price,
    }
      return [...newC,c]
    })
  
  
}

const removeCoffee = ({coffee}) =>{
  let cof = {"id": coffee.id,
  "name": null,
  "imageLink": null,
  "supplierName": null,
  "quantity": 0,
  "price": null,};

  setCoffeeDetail((pre) => {
    const newC = pre.filter((c)=>{
      if(c.id === coffee.id) {
        c.quantity--
        cof = c
      }
      return c.id !== coffee.id
    })
    if (cof.quantity === 0) return [...newC]
    return [...newC,cof]
  })

}

console.log(o)
useEffect(() =>{
  setO(pre=> {
    let oldTotalPrice = 0
    CoffeeDetail.map((coffee) =>{
      oldTotalPrice += (coffee.price*coffee.quantity)
    })
    return{...pre,["totalPrice"]:oldTotalPrice}
  })
},[CoffeeDetail]);

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
  return (
    <>
      <Head>
        <title>{getPageTitle('Order Form')}</title>
      </Head>

      <SectionMain>
        <SectionTitleLineWithButton icon={mdiBallotOutline} title="Order detail forms" main>

        <img
        src={o.type ==='Đặt hàng'?'http://localhost:3000/admin-one-react-tailwind/delivery.png':'http://localhost:3000/admin-one-react-tailwind/ontable.png'}
        alt={o.type}
        title={o.type}
        style={{width: "60px", height:"60px"}}
        className="rounded-full block h-auto w-full max-w-full bg-gray-100 dark:bg-slate-800"
      />

        </SectionTitleLineWithButton>
        <small className="text-gray-500 dark:text-slate-400">{new Date().toDateString()}</small>

        <CardBox>

          <Formik
            initialValues={o}
            onSubmit={(values) => alert(JSON.stringify(values, null, 2))}
          >
            <Form>
                
              <FormField label="UserID - Name - Email" icons={[ mdiMail,mdiAlphabetLatin,mdiMail]}>
              <Field type="text" name="user_id" placeholder="user_id" value={o.user_id} onChange={handleChange} />
                <Field type="text" name="name" placeholder="name" value={o.name}  onChange={handleChange} />
                <Field type="text" name="email" placeholder="Email" value={o.email}   onChange={handleChange}/>
              </FormField>
              <FormField label="Total Price - Status - Phone" icons={[mdiHandCoin, mdiCloud,mdiPhone]}>
              <Field type="text"  name="price" placeholder="price" value={o.totalPrice}  />
                <Field type="text" name="status" placeholder="status" value={o.status} onChange={handleChange} />
                <Field type="text" name="phone" placeholder="phone" value={o.phone}onChange={handleChange} />
              </FormField>
              <Divider />
              <FormField label="Type" labelFor="type">
                <select name="type" id="type" onChange={handleChange}>
                        <option value={"Tại chỗ"}>{"Tại chỗ"}</option>
                        <option value={"Đặt hàng"}>{"Đặt hàng"}</option>
                </select>
              </FormField>
              <Divider />

              <FormField label="Address" hasTextareaHeight>
                <Field name="address" type="text"  placeholder="address" value ={o.address} onChange={handleChange} />
              </FormField>
              <Divider />
              <FormField>
              <table>
        <thead>
          <tr>
            <th />
            <th>Name</th>
            <th>Supplier</th>
        
            <th>Price</th>
            <th>Quantity</th>
            <th />
          </tr>
        </thead>
        <tbody>
          {CoffeeDetail.map((coffee) => (
            <tr key={coffee.id}>
              <td className="border-b-0 lg:w-6 before:hidden">
                <UserAvatar api={coffee.imageLink} username={"Howell Hand"} className="w-24 h-24 mx-auto lg:w-6 lg:h-6" />
              </td>
              <td data-label="Name">{coffee.name}</td>
              <td data-label="Supplier">{coffee.supplierName}</td>
              <td data-label="Price">{coffee.price}</td>
              <td data-label="Quantity">{coffee.quantity}</td>
              <td className="before:hidden lg:w-1 whitespace-nowrap">
                <Buttons type="justify-start lg:justify-end" noWrap>
              
                  <Button
                    color="danger"
                    icon={mdiTrashCan}
                    onClick={() => removeCoffee({coffee:coffee})}
                    small
                  />
                </Buttons>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
              </FormField>
              <Divider />
              <CardBox className="mb-6" hasTable>
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
                    icon={ mdiPlus}
                    onClick={() => addCoffee({coffee:coffee})}
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
        </CardBox>
      
              
            </Form>
          </Formik>
        </CardBox>
      </SectionMain>

      <SectionMain>
      <Buttons>
                <Button type="submit"  color="info"  label="Submit" onClick={handleSubmit} />
                <Button type="reset" color="info" outline label="Cancel" />
              </Buttons>
      </SectionMain>

    
    </>
  )
}

OrderAdd.getLayout = function getLayout(page: ReactElement) {
  return <LayoutAuthenticated>{page}</LayoutAuthenticated>
}

export default OrderAdd
