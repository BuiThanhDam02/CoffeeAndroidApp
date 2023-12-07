import React, { useState } from "react";
import {
  GridComponent,
  ColumnsDirective,
  ColumnDirective,
  Resize,
  Sort,
  ContextMenu,
  Filter,
  Page,
  ExcelExport,
  PdfExport,
  Edit,
  Inject,
} from "@syncfusion/ej2-react-grids";

import { ordersData, contextMenuItems, ordersGrid } from "../Data/dummy";
import { Header } from "../Components";
const Coffees = () => {
  // [coffeesData,setCoffeesData] = useState([])

  return (
    <div className="m-2 md:m-10 mt-24 p-2 md:p-10 bg-white rounded-3xl">
      <Header category="Page" title="Coffees" />
      <GridComponent
        id="gridcomp"
        // dataSource={}
        allowPaging
        allowSorting
      >
        <ColumnsDirective>
          {ordersGrid.map((item, index) => (
            <ColumnDirective key={index} {...item} />
          ))}
        </ColumnsDirective>
        <Inject
          services={[
            Resize,
            Sort,
            ContextMenu,
            Filter,
            Page,
            ExcelExport,
            Edit,
            PdfExport,
          ]}
        />
      </GridComponent>
    </div>
  );
};
export default Coffees;

// coffees data {
//   id: 10248,
//   supplier_id: 122,
//   name: "cafe ks",
//   description: 'USA',
//   status: 1,
//   price: 45999,
//   imagelink:"..."

// },

// [
//   {
//     headerText: 'Image',
//     template: gridOrderImage,
//     textAlign: 'Center',
//     width: '120',
//   },
//   {
//     field: 'id',
//     headerText: 'Id',
//     width: '150',
//     editType: 'dropdownedit',
//     textAlign: 'Center',
//   },
//   { field: 'name',
//     headerText: 'Name',
//     width: '150',
//     textAlign: 'Center',
//   },
//   {
//     field: 'price',
//     headerText: 'Price',
//     format: 'C2',
//     textAlign: 'Center',
//     editType: 'numericedit',
//     width: '150',
//   },
//   {
//     headerText: 'Status',
//     template: gridOrderStatus,
//     field: 'status',
//     textAlign: 'Center',
//     width: '120',
//   },
//   {
//     field: 'supplier_id',
//     headerText: 'Supplier ID',
//     width: '120',
//     textAlign: 'Center',
//   },

//   {
//     field: 'description',
//     headerText: 'Description',
//     width: '150',
//     textAlign: 'Center',
//   },
// ];

// export const gridCoffeeImage = (props) => (
//   <div>
//     <img
//       className="rounded-xl h-20 md:ml-3"
//       src={props.imagelink}
//       alt="order-item"
//     />
//   </div>
// );

// export const gridCoffeeStatus = (props) => (
//   <button
//     type="button"
//     style={{ background: '#8BE78B' }}
//     className="text-white py-1 px-2 capitalize rounded-2xl text-md"
//   >
//     {props.status}
//   </button>
// );
