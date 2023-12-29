import { mdiBallotOutline, mdiMail, mdiUpload,mdiStar,mdiCloud,mdiPhone,mdiAlphabetLatin } from '@mdi/js'
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
import { getAllSupplier, getCoffeeById, getSupplierByID, updateCoffee, updateSupplier } from '../hooks/sampleData'

const SupplierFormPage = () => {
  const router = useRouter();

  // Lấy giá trị của tham số 'id' từ URL
  const { id } = router.query;
  const [s,setS] = useState({
        "id": 0,
        "name": "",
        "phone": "",
        "password": "",
        "email": "",
        "status": 0,
        "imageLink": null,

});

const handleChange =(e) => {
  setS(pre=> {return{...pre,[e.target.name]:e.target.value}})
};



const handleSubmit =() => {

  updateSupplier({supplier:s})
  .then((data) => { 
    alert(data)
    router.push(`/supplierformpage?id=${s.id}`);
  })
  .catch((err) => {
    alert(err)

    });
};

console.log(s)
useEffect(() => {
  getSupplierByID({id:id})
    .then((data) => {
      setS(data);
      
    })
    .catch((error) => {
      // Handle any errors that occur during data fetching
      console.error('Error fetching coffee data:', error);
    });
    
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
            initialValues={s}
            onSubmit={(values) => alert(JSON.stringify(values, null, 2))}
          >
            <Form>
              <FormField label="ID - Name" icons={[ mdiMail,mdiAlphabetLatin]}>
              <Field type="text" name="id" placeholder="id" value={s.id}  />
                <Field type="text" name="name" placeholder="Name" value={s.name} onChange={handleChange}  />
              
              </FormField>

              <FormField label="Email - Phone - Status" icons={[mdiMail, mdiPhone,mdiCloud]}>
              <Field type="text" name="email" placeholder="Email" value={s.email} onChange={handleChange} />
                <Field name="phone" placeholder="Phone" value={s.phone} onChange={handleChange}  />
                <Field type="number" name="status" placeholder="Status" value={s.status}onChange={handleChange}  />
              </FormField>
             
              <Divider />

              <img
        src={s.imageLink}
        alt={s.name}
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
                <Button type="submit" color="info"  label="Submit" onClick={handleSubmit} />
                <Button type="reset" color="info" outline label="Cancel" />
              </Buttons>
      </SectionMain>

    
    </>
  )
}

SupplierFormPage.getLayout = function getLayout(page: ReactElement) {
  return <LayoutAuthenticated>{page}</LayoutAuthenticated>
}

export default SupplierFormPage
