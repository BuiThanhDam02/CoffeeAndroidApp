import { mdiBallotOutline, mdiMail, mdiUpload,mdiStar,mdiCloud,mdiHandCoin,mdiAlphabetLatin } from '@mdi/js'
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
import { getAllSupplier, getCoffeeById, updateCoffee } from '../hooks/sampleData'

const CoffeeFormPage = () => {
  const router = useRouter();

  const [s,setS]= useState([{
    "supplierID": 1,
    "name": "",
    "image": null
}])
  // Lấy giá trị của tham số 'id' từ URL
  const { id } = router.query;
  const [c,setC] = useState({
    "id": 1,
    "supplier": {
        "id": 1,
        "name": "",
        "phone": "",
        "password": "",
        "email": "",
        "status": 0
    },
    "name": "",
    "description": "",
    "status": 0,
    "price": 0,
    "imageLink": null,
    "star": 0
});

const handleChange =(e) => {
  setC(pre=> {return{...pre,[e.target.name]:e.target.value}})
};

const handleSupChange =(e) => {
  setC(pre=> {return{...pre,[e.target.name]:{['id']:e.target.value}}})
};

const handleSubmit =() => {
  const data = {
    "id": c.id,
    "supplierId":c.supplier.id,
    "name":c.name,
    "description":c.description,
    "status":c.status,
    "price":c.price,
    "imageLink": c.imageLink,
  }

  updateCoffee({coffee:data})
  .then((data) => { 
    alert(data)
    router.push(`/coffeeformpage?id=${c.id}`);
  })
  .catch((err) => {
    alert(err)

    });
};

console.log(c)
useEffect(() => {
  getCoffeeById({id:id})
    .then((data) => {
      setC(data);
      
    })
    .catch((error) => {
      // Handle any errors that occur during data fetching
      console.error('Error fetching coffee data:', error);
    });
    getAllSupplier()
    .then((supplier) => {
      setS(supplier)
    })
    .catch((error) => {console.error('Error fetching supplier fetchSupp ',error)})
},[]);
   
  return (
    <>
      <Head>
        <title>{getPageTitle('Coffee Form')}</title>
      </Head>

      <SectionMain>
        <SectionTitleLineWithButton icon={mdiBallotOutline} title="Coffee detail forms" main>
      
        </SectionTitleLineWithButton>

        <CardBox>
          <Formik
            initialValues={c}
            onSubmit={(values) => alert(JSON.stringify(values, null, 2))}
          >
            <Form>
              <FormField label="ID - Name" icons={[ mdiMail,mdiAlphabetLatin]}>
              <Field type="text" name="id" placeholder="id" value={c.id} onChange={handleChange} />
                <Field type="text" name="name" placeholder="Name" value={c.name} onChange={handleChange}  />
              
              </FormField>


              <FormField label="Supplier" labelFor="supplier">
                <Field name="supplier" id="supplier" component="select" onChange={handleSupChange}>
                  {s.map((value)=> (
                        <option value={value.supplierID}>{value.name}</option>
                  ))}
           
                </Field>
              </FormField>
              <FormField label="Price - Status - Star" icons={[mdiHandCoin, mdiCloud,mdiStar]}>
              <Field type="text" name="price" placeholder="price" value={c.price} onChange={handleChange} />
                <Field name="status" placeholder="status" value={c.status} onChange={handleChange}  />
                <Field type="number" name="star" placeholder="star" value={c.star} />
              </FormField>
             
              <Divider />

              <FormField label="Description" hasTextareaHeight>
                <Field name="description" type="text"  placeholder="description" value ={c.description} onChange={handleChange} />
              </FormField>

              <Divider />
              <img
        src={c.imageLink}
        alt={c.name}
        style={{width: "100px", height:"100px"}}
        className="rounded-full block h-auto w-full max-w-full bg-gray-100 dark:bg-slate-800"
      />
      <Divider />
              <FormField>
                <FormFilePicker label="Upload" color="info" icon={mdiUpload} />
              </FormField>
              
            </Form>
          </Formik>
        </CardBox>
      </SectionMain>

      <SectionMain>
      <Buttons>
                <Button type="submit" onClick={handleSubmit} color="info"  label="Submit" />
                <Button type="reset" color="info" outline label="Cancel" />
              </Buttons>
      </SectionMain>

    
    </>
  )
}

CoffeeFormPage.getLayout = function getLayout(page: ReactElement) {
  return <LayoutAuthenticated>{page}</LayoutAuthenticated>
}

export default CoffeeFormPage
