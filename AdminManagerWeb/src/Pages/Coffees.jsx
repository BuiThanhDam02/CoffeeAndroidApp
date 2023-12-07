import React, { useEffect, useState } from "react";
import {
  GridComponent,
  ColumnsDirective,
  ColumnDirective,
  Resize,
  Sort,
  Toolbar,
  ContextMenu,
  Filter,
  Page,
  ExcelExport,
  PdfExport,
  Edit,
  Inject,
} from "@syncfusion/ej2-react-grids";

import { coffeesGrid } from "../Data/dummy";
import { getAllCoffee, deleteCoffee } from "../Api/CoffeeRequest";
import { Header } from "../Components";
const Coffees = () => {
  const [coffees, setCoffees] = useState([]);

  useEffect(() => {
    getAllCoffee().then((data) => {
      setCoffees(data.data);
    });
    console.log(coffees);
  }, []);

  const handleDelete = () => {
    // Lấy các dòng đã chọn
    const selectedRecords = GridComponent.console.log(selectedRecords);

    // Gọi API để xóa dữ liệu từ backend
    // Sử dụng thư viện như axios để thực hiện yêu cầu HTTP
    // Ví dụ: axios.delete('/api/data', { data: selectedRecords });
  };

  return (
    <div className="m-2 md:m-10 mt-24 p-2 md:p-10 bg-white rounded-3xl">
      <Header category="Page" title="Coffees" />
      <GridComponent
        id="gridcomp"
        toolbar={["Delete", "Add", "Edit", "ExcelExport", "Search", "Update"]}
        editSettings={{
          allowDeleting: true,
          allowEditing: true,
          allowAdding: true,
        }}
        width="auto"
        dataSource={coffees}
        allowPaging
        allowSorting
        actionComplete={(args) => {
          if (args.requestType === "delete") {
            const deletedData = args.data;
            console.log("Deleted Data:", deletedData);
            deletedData.forEach((element) => {
              // deleteCoffee(element.id);
            });
          }
          if (args.requestType === "add") {
            console.log("a");
          }
        }}
      >
        <ColumnsDirective>
          {coffeesGrid.map((item, index) => (
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
            Toolbar,
          ]}
        />
      </GridComponent>
    </div>
  );
};
export default Coffees;
