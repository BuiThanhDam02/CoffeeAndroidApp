import React, { useState, useEffect } from "react";
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
  Search,
  Toolbar,
} from "@syncfusion/ej2-react-grids";
import axios from "axios";
import {
  ordersData,
  contextMenuItems,
  ordersGrid,
  ordersGrids,
  employeesData,
  employeesGrid,
} from "../Data/dummy";
import { Header } from "../Components";
// import $ from "jquery";
import { getOrders, deleteOrders } from "../service/orderService";

const Orders = () => {
  const [ordersData, setData] = useState([]);

  useEffect(() => {
    getOrders().then((data) => {
      setData(data);
    });
    //$("[aria-label='Delete']").on('click', handleClick);
  }, []);

  if (ordersData.length === 0) {
    return <div>Loading...</div>;
  }
  function handleActionComplete(args) {
    if (args.requestType === "delete") {
      deleteOrders(args.data[0].id);
    }
    console.log(args);
  }

  return (
    <div className="m-2 md:m-10 mt-24 p-2 md:p-10 bg-white rounded-3xl">
      <Header category="Page" title="Orders" />
      <GridComponent
        id="gridcomp"
        toolbar={["Delete", "Add", "Update", "Edit", "Cancel"]}
        dataSource={ordersData}
        allowPaging
        allowSorting
        editSettings={{
          allowDeleting: true,
          allowEditing: true,
          allowAdding: true,
        }}
        width="auto"
        pageSettings={{ pageSize: 6 }}
        actionComplete={handleActionComplete}
      >
        <ColumnsDirective>
          {ordersGrids.map((item, index) => (
            <ColumnDirective key={index} {...item} />
          ))}
          {/*    <ColumnDirective type={'checkbox'} width={'50'}/>
            <ColumnDirective filed={'id'}  headerText={'Id'} width={'150'} editType={'dropdownedit'} textAlign={'Center'}/>
            <ColumnDirective filed={'name'}  headerText={'Tên Khách Hàng'} width={'150'}  textAlign={'Center'}/>
            <ColumnDirective filed={'email'}  headerText={'Email'} width={'150'} editType={'numericedit'} textAlign={'Center'} fomat={'C2'}/>
            <ColumnDirective filed={'phone'}  headerText={'SĐT'} width={'150'}  textAlign={'Center'}/>
            <ColumnDirective filed={'status'}  headerText={'Trạng Thái'} width={'120'}  textAlign={'Center'}/>
            <ColumnDirective filed={'totalPrice'}  headerText={'Tổng Tiền'} width={'120'}  textAlign={'Center'}/>*/}
        </ColumnsDirective>
        <Inject services={[Page, Edit, Toolbar]} />
      </GridComponent>
    </div>
  );
};
export default Orders;
