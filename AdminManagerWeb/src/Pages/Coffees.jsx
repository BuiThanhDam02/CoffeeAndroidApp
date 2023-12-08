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
import { getAllCoffee, deleteCoffee, editCoffee } from "../Api/CoffeeRequest";
import { Header } from "../Components";
const Coffees = () => {
  const [coffees, setCoffees] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await getAllCoffee();
        setCoffees(response);
      } catch (error) {
        console.error("Error fetching coffee data: ", error);
      }
    };
    fetchData();
  }, []);

  const handleDelete = async (args) => {
    // Lấy các dòng đã chọn
    // const selectedRecords = GridComponent.console.log(selectedRecords);

    if (args.requestType === "delete") {
      const deletedData = args.data;
      await Promise.all(deletedData.map((elememt) => deleteCoffee(elememt.id)));
    }
    if (args.requestType === "save" && args.action == "edit") {
      const coffeeData = args.data;
      await editCoffee(coffeeData);
    }
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
        actionComplete={handleDelete}
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
