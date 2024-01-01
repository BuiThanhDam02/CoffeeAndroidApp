import { mdiBallotOutline, mdiMail, mdiUpload,mdiStar,mdiCloud,mdiHandCoin,mdiAlphabetLatin, mdiPhone } from '@mdi/js'
import { Field, Form, Formik } from 'formik'
import Head from 'next/head'
import { ReactElement, useState,useEffect } from 'react'
import Button from '../components/Button'
import Buttons from '../components/Buttons'
import Divider from '../components/Divider'
import CardBox from '../components/CardBox'

import FormField from '../components/Form/Field'
import LayoutAuthenticated from '../layouts/Authenticated'
import SectionMain from '../components/Section/Main'

import SectionTitleLineWithButton from '../components/Section/TitleLineWithButton'
import { getPageTitle } from '../config'
import { useRouter } from 'next/router'
import {  createSupplier, uploadSupplierImage } from '../hooks/sampleData'
import { LocalAPI } from '../hooks/localapi'

const SupplierAdd = () => {
  const [file, setFile] = useState(null)

  const router = useRouter();

  const handleFileChange = (event) => {
    setFile(event.currentTarget.files[0])
    const formData = new FormData();
    formData.append('file', event.currentTarget.files[0]);
  
    uploadSupplierImage({formdata: formData})
    .then((data) => { 
      setS(pre => {return {...pre,imageLink: data}})})
    .catch((err) => {alert(err)});

  }

  const isRoundIcon  = null;
  const showFilename = !isRoundIcon && file


  // Lấy giá trị của tham số 'id' từ URL
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

  createSupplier({supplier:s})
  .then((data) => { 
    alert(data)
    router.push(`/supplierpage`);
  })
  .catch((err) => {
    alert(err)

    });
};



   
  return (
    <>
      <Head>
        <title>{getPageTitle('Supplier Form')}</title>
      </Head>

      <SectionMain>
        <SectionTitleLineWithButton icon={mdiBallotOutline} title="Supplier detail forms" main>
      
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
        src={LocalAPI+s.imageLink}
        alt={s.name}
        style={{width: "100px", height:"100px"}}
        className="rounded-full block h-auto w-full max-w-full bg-gray-100 dark:bg-slate-800"
      />
      <Divider />
              {/* <FormField> */}
              
              <div className="flex items-stretch justify-start relative">
              <label className="inline-flex">
                <Button
                  className={`${isRoundIcon ? 'w-12 h-12' : ''} ${showFilename ? 'rounded-r-none' : ''}`}
                  iconSize={isRoundIcon ? 24 : undefined}
                  label={isRoundIcon ? null : "Upload"}
                  icon={mdiUpload}
                  color={"info"}
                  roundedFull={isRoundIcon}
                  asAnchor
                />
                <input
                  type="file"
                  className="absolute top-0 left-0 w-full h-full opacity-0 outline-none cursor-pointer -z-1"
                  onChange={handleFileChange}
                  accept={"image/*"}
                />
              </label>
              {showFilename && (
                <div className="px-4 py-2 max-w-full flex-grow-0 overflow-x-hidden bg-gray-100 dark:bg-slate-800 border-gray-200 dark:border-slate-700 border rounded-r">
                  <span className="text-ellipsis max-w-full line-clamp-1">{file.name}</span>
                </div>
              )}
            </div>
              {/* </FormField> */}
              
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

SupplierAdd.getLayout = function getLayout(page: ReactElement) {
  return <LayoutAuthenticated>{page}</LayoutAuthenticated>
}

export default SupplierAdd
