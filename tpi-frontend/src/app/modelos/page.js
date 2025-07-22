"use client";
import { useEffect, useState } from "react";
import { getModelos } from "../services/modelosService";

export default function ModelosPage() {
  const [modelos, setModelos] = useState([]);
  const [error, setError] = useState(null);

useEffect(() => {
  fetch('http://localhost:8082/modelos')
    .then(res => {
      if (!res.ok) {
        throw new Error('Error al obtener modelos');
      }
      return res.json();
    })
    .then(data => setModelos(data))
    .catch(err => setError(err.message));
}, []);


  return (
    <div className="p-6">
      <h1 className="text-2xl font-bold mb-4">Modelos</h1>
      {error && <p className="text-red-500">{error}</p>}
      <table className="min-w-full border">
        <thead>
          <tr>
            <th className="border p-2">ID</th>
            <th className="border p-2">Descripción</th>
            <th className="border p-2">Marca</th>
          </tr>
        </thead>
        <tbody>
          {modelos.map((m) => (
            <tr key={m.id}>
              <td className="border p-2">{m.id}</td>
              <td className="border p-2">{m.descripcion}</td>
              <td className="border p-2">{m.idMarca}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
