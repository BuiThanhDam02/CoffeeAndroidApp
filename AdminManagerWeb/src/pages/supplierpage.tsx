import { mdiGithub, mdiMonitorCellphone, mdiTableBorder, mdiTableOff } from '@mdi/js'
import Head from 'next/head'
import React, { ReactElement } from 'react'
import Button from '../components/Button'
import CardBox from '../components/CardBox'
import CardBoxComponentEmpty from '../components/CardBox/Component/Empty'
import LayoutAuthenticated from '../layouts/Authenticated'
import NotificationBar from '../components/NotificationBar'
import SectionMain from '../components/Section/Main'
import SectionTitleLineWithButton from '../components/Section/TitleLineWithButton'
import { getPageTitle } from '../config'
import TableSampleSuppliers from '../components/Table/SampleSuppliers'

const SupplierPage = () => {
  return (
    <>
      <Head>
        <title>{getPageTitle('Supplier')}</title>
      </Head>
      <SectionMain>
        <SectionTitleLineWithButton
          icon={mdiTableBorder}
          title="Supplier Tables"
          main
        ></SectionTitleLineWithButton>

        <CardBox className="mb-6" hasTable>
          <TableSampleSuppliers />
        </CardBox>

        <SectionTitleLineWithButton icon={mdiTableOff} title="Empty variation" />

        <NotificationBar color="danger" icon={mdiTableOff}>
          <b>Empty card.</b> When there&apos;s nothing to show
        </NotificationBar>

        <CardBox>
          <CardBoxComponentEmpty />
        </CardBox>
      </SectionMain>
    </>
  )
}

SupplierPage.getLayout = function getLayout(page: ReactElement) {
  return <LayoutAuthenticated>{page}</LayoutAuthenticated>
}

export default SupplierPage
