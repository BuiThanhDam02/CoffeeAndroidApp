import {
  mdiBallotOutline,
  mdiMail,
  mdiCellphoneMessage,
  mdiStar,
  mdiCloud,
  mdiHomeMapMarker,
  mdiAlphabetLatin,
} from '@mdi/js'
import { Field, Form, Formik } from 'formik'
import Head from 'next/head'
import { ReactElement, useState, useEffect } from 'react'
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
import { getAllRoles, getClientById, updateClient } from '../hooks/sampleData'

const UserFormPage = () => {
  const router = useRouter()

  const [roles, setRoles] = useState([
    {
      id: 1,
      name: '',
    },
  ])
  // Lấy giá trị của tham số 'id' từ URL
  const { id } = router.query
  const [c, setC] = useState({
    id: 1,
    name: '',
    email: '',
    phone: 0,
    address: 0,
    username: null,
    roles: {
      id: 1,
      name: '',
    },
  })

  const handleChange = (e) => {
    setC((pre) => {
      return { ...pre, [e.target.name]: e.target.value }
    })
  }

  const handleRoleChange = (e) => {
    setC((pre) => {
      return { ...pre, [e.target.name]: { ['id']: e.target.value } }
    })
  }

  const handleSubmit = () => {
    const data = {
      id: c.id,
      name: c.name,
      email: c.email,
      phone: c.phone,
      address: c.address,
      rolesId: c.roles.id,
    }

    updateClient({ client: data })
      .then((data) => {
        alert(data)
        router.push(`/userformpage?id=${c.id}`)
      })
      .catch((err) => {
        alert(err)
      })
  }
  console.log(c)
  useEffect(() => {
    getClientById({ id: id })
      .then((data) => {
        setC(data)
      })
      .catch((error) => {
        // Handle any errors that occur during data fetching
        console.error('Error fetching coffee data:', error)
      })
    getAllRoles()
      .then((role) => {
        setRoles(role)
      })
      .catch((error) => {
        console.error('Error fetching role fetchSupp ', error)
      })
  }, [])

  return (
    <>
      <Head>
        <title>{getPageTitle('User Form')}</title>
      </Head>

      <SectionMain>
        <SectionTitleLineWithButton
          icon={mdiBallotOutline}
          title="User detail forms"
          main
        ></SectionTitleLineWithButton>

        <CardBox>
          <Formik initialValues={c} onSubmit={(values) => alert(JSON.stringify(values, null, 2))}>
            <Form>
              <FormField label="ID - Name" icons={[mdiMail, mdiAlphabetLatin]}>
                <Field type="text" name="id" placeholder="id" value={c.id} />
                <Field
                  type="text"
                  name="name"
                  placeholder="Name"
                  value={c.name}
                  onChange={handleChange}
                />
              </FormField>

              <FormField label={'Role - ' + c.roles[0]?.name} labelFor="role">
                <Field name="role" id="role" component="select" onChange={handleRoleChange}>
                  {roles.map((value) => (
                    <option key={value.id} value={value.id}>
                      {value.name}
                    </option>
                  ))}
                </Field>
              </FormField>
              <FormField
                label="Address - Phone - Email"
                icons={[mdiHomeMapMarker, mdiCellphoneMessage, mdiMail]}
              >
                <Field
                  type="text"
                  name="address"
                  placeholder="address"
                  value={c.address}
                  onChange={handleChange}
                />
                <Field
                  type="phone"
                  name="phone"
                  placeholder="phone"
                  value={c.phone}
                  onChange={handleChange}
                />
                <Field type="email" name="email" placeholder="email" value={c.email} />
              </FormField>
              {/*              
              <Divider />

              <FormField label="Description" hasTextareaHeight>
                <Field name="description" type="text"  placeholder="description" value ={c.description} onChange={handleChange} />
              </FormField>

              <Divider /> */}
              {/* <img
        src={c.imageLink}
        alt={c.name}
        style={{width: "100px", height:"100px"}}
        className="rounded-full block h-auto w-full max-w-full bg-gray-100 dark:bg-slate-800"
      /> */}
              <Divider />
              {/* <FormField>
                <FormFilePicker label="Upload" color="info" icon={mdiUpload} />
              </FormField> */}
            </Form>
          </Formik>
        </CardBox>
      </SectionMain>

      <SectionMain>
        <Buttons>
          <Button type="submit" onClick={handleSubmit} color="info" label="Submit" />
          <Button type="reset" color="info" outline label="Cancel" />
        </Buttons>
      </SectionMain>
    </>
  )
}

UserFormPage.getLayout = function getLayout(page: ReactElement) {
  return <LayoutAuthenticated>{page}</LayoutAuthenticated>
}

export default UserFormPage
