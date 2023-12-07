import axios from "axios";

const baseUrl = "http://localhost:8080/api"

export function deleteOrders(primaryKey){

    return fetch(baseUrl + "/order/delete/" + primaryKey,{
        method: "delete",
    }).then(data => {return data});
}

export function getOrders(){
    return fetch(baseUrl + "/order/getAll").then(res => res.json());
}

/*export function getOrders() {
    return fetch(baseUrl + "/order/getAll")
        .then((res) => res.json())
        .then((data) => {
            return {
                count: data.length,
                result: data
            };
        });
}*/






