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
import { addCoffee, getAllSupplier, getCoffeeById, updateCoffee, uploadCoffeeImage } from '../hooks/sampleData'
import { LocalAPI } from '../hooks/localapi'

const CoffeeAdd = () => {
  const [file, setFile] = useState(null)

  const router = useRouter();

  const handleFileChange = (event) => {
    setFile(event.currentTarget.files[0])
    const formData = new FormData();
    formData.append('file', event.currentTarget.files[0]);
  
    uploadCoffeeImage({formdata: formData})
    .then((data) => { 
      setC(pre => {return {...pre,imageLink: data}})})
    .catch((err) => {alert(err)});

  }

  const isRoundIcon  = null;
  const showFilename = !isRoundIcon && file

  const [s,setS]= useState([{
    "supplierID": 1,
    "name": "",
    "image": null
}])

  // Lấy giá trị của tham số 'id' từ URL
  const [c,setC] = useState({
    "id": 0,
    "supplier": {
        "id": 1,
        "name": "",
        "phone": "",
        "password": "",
        "email": "",
        "status": 0
    },
    "name": null,
    "description": null,
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

const handleSubmit = () => {
  const data = {
    "id":0,
    "supplierId":c.supplier.id,
    "name":c.name,
    "description":c.description,
    "status":c.status,
    "price":c.price,
    "imageLink": c.imageLink,
  }


   addCoffee({coffee:data})
  .then((data) => { alert(data); router.push('/coffeepage') })
  .catch((err) => { alert(err)})
};

console.log(c)
useEffect(() => {
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
                <select name="supplier" id="supplier" onChange={handleSupChange}>
                  {s.map((value)=> (
                        <option value={value.supplierID}>{value.name}</option>
                  ))}
           
                </select>
              </FormField>
              <FormField label="Price - Status - Star" icons={[mdiHandCoin, mdiCloud,mdiStar]}>
              <Field type="text" name="price" placeholder="price" value={c.price} onChange={handleChange} />
                <Field name="status"  placeholder="status" value={c.status} onChange={handleChange}  />
                <Field type="number" name="star" placeholder="star" value={c.star} />
              </FormField>
             
              <Divider />

              <FormField label="Description" hasTextareaHeight>
                <Field name="description" type="text"  placeholder="description" value ={c.description} onChange={handleChange} />
              </FormField>

              <Divider />
              <img
        src={LocalAPI+c.imageLink}
        alt={c.name}
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

CoffeeAdd.getLayout = function getLayout(page: ReactElement) {
  return <LayoutAuthenticated>{page}</LayoutAuthenticated>
}

export default CoffeeAdd
