import React, { useEffect, useState } from "react";
import Sidebar from "./sidebar";

function Items() {
  const [items, setItems] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/pantry/all")
      .then((res) => res.json())
      .then((data) => setItems(data))
      .catch((err) => console.error(err));
  }, []);

  return (
    <>
      <div className="flex">
        <Sidebar />
        <div className="p-7">
          <h1 className="text-3xl font-bold underline">Your Items</h1>
          <table>
            <thead>
              <tr>
                <th>Name</th>
                <th>Quantity</th>
                <th>Unit</th>
                <th>Category</th>
              </tr>
            </thead>
            <tbody>
              {items.map((item) => (
                <tr key={item.id}>
                  <td>{item.name}</td>
                  <td>{item.quantity}</td>
                  <td>{item.unit}</td>
                  <td>{item.category}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </>
  );
}

export default Items;
