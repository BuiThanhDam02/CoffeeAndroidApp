import { mdiBallotOutline, mdiMail, mdiUpload,mdiStar,mdiCloud,mdiHandCoin,mdiAlphabetLatin,mdiPhone, mdiTable, mdiEye, mdiTrashCan } from '@mdi/js'
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
import { getAllSupplier, getCoffeeById, getOrderDetailById, updateCoffee } from '../hooks/sampleData'
import UserAvatar from '../components/UserAvatar'

const OrderFormPage = () => {
  const router = useRouter();

  // Lấy giá trị của tham số 'id' từ URL
  const { id } = router.query;
  const [o,setO] = useState({
    "orderDTO": {
        "id": 0,
        "name": null,
        "phone": null,
        "password": null,
        "address": null,
        "email": null,
        "status": null,
        "statusInt": 0,
        "totalPrice": "",
        "type": null,
        "created_at": 1703386072000,
        "statusBg": null
    },
    "coffeeDTOS": [
        {
            "id": 0,
            "name": null,
            "imageLink": null,
            "supplierName": null,
            "quantity": 0,
            "price": ""
        }]
});

// const handleChange =(e) => {
//   setC(pre=> {return{...pre,[e.target.name]:e.target.value}})
// };

// const handleSupChange =(e) => {
//   setC(pre=> {return{...pre,[e.target.name]:{['id']:e.target.value}}})
// };

// const handleSubmit =() => {
//   const data = {
//     "id": c.id,
//     "supplierId":c.supplier.id,
//     "name":c.name,
//     "description":c.description,
//     "status":c.status,
//     "price":c.price,
//     "imageLink": c.imageLink,
//   }


//   updateCoffee({coffee:data})
//   .then((data) => { 
//     alert(data)
//     router.push(`/coffeeformpage?id=${c.id}`);
//   })
//   .catch((err) => {
//     alert(err)

//     });
// };


useEffect(() => {
    getOrderDetailById({id:id})
    .then((data) => {
      setO(data);

    })
    .catch((error) => {
      // Handle any errors that occur during data fetching
      console.error('Error fetching coffee data:', error);
    });
},[]);
   let Order = o.orderDTO
   let CoffeeDetail = o.coffeeDTOS
  return (
    <>
      <Head>
        <title>{getPageTitle('Coffee Form')}</title>
      </Head>

      <SectionMain>
        <SectionTitleLineWithButton icon={mdiBallotOutline} title="Coffee detail forms" main>

        <img
        src={Order.type ==='Đặt hàng'?'http://localhost:3000/admin-one-react-tailwind/delivery.png':'http://localhost:3000/admin-one-react-tailwind/ontable.png'}
        alt={Order.type}
        title={Order.type}
        style={{width: "60px", height:"60px"}}
        className="rounded-full block h-auto w-full max-w-full bg-gray-100 dark:bg-slate-800"
      />

        </SectionTitleLineWithButton>
        <small className="text-gray-500 dark:text-slate-400">{new Date(Order.created_at).toDateString()}</small>

        <CardBox>

          <Formik
            initialValues={o}
            onSubmit={(values) => alert(JSON.stringify(values, null, 2))}
          >
            <Form>
                
              <FormField label="ID - Name - Email" icons={[ mdiMail,mdiAlphabetLatin,mdiMail]}>
              <Field type="text" name="id" placeholder="id" value={Order.id} />
                <Field type="text" name="name" placeholder="Name" value={Order.name}   />
                <Field type="text" name="email" placeholder="Email" value={Order.email}   />
              </FormField>
              <FormField label="total Price - Status - Phone" icons={[mdiHandCoin, mdiCloud,mdiPhone]}>
              <Field type="text" name="price" placeholder="price" value={Order.totalPrice} />
                <Field type="text" name="status" placeholder="status" value={Order.status}   />
                <Field type="text" name="phone" placeholder="phone" value={Order.phone} />
              </FormField>
             
              <Divider />

              <FormField label="Address" hasTextareaHeight>
                <Field name="address" type="text"  placeholder="address" value ={Order.address} />
              </FormField>

              <Divider />
            
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
                    color="info"
                    icon={mdiEye}
                  
                    small
                  />
                  <Button
                    color="danger"
                    icon={mdiTrashCan}
              
                    small
                  />
                </Buttons>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
              </FormField>
              
            </Form>
          </Formik>
        </CardBox>
      </SectionMain>

      <SectionMain>
      <Buttons>
                <Button type="submit"  color="info"  label="Submit" />
                <Button type="reset" color="info" outline label="Cancel" />
              </Buttons>
      </SectionMain>

    
    </>
  )
}

OrderFormPage.getLayout = function getLayout(page: ReactElement) {
  return <LayoutAuthenticated>{page}</LayoutAuthenticated>
}

export default OrderFormPage
