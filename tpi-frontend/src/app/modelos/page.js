"use client";
import { useEffect, useState } from "react";
import { getModelos } from "../services/modelosService";

export default function ModelosPage() {
  const [modelos, setModelos] = useState([]);
  const [error, setError] = useState(null);

useEffect(() => {
  fetch('http://localhost:8080/vehiculos/modelos')
    .then(res => {
      if (!res.ok) throw new Error('Error al obtener modelos');
      return res.json();
    })
    .then(data => {
      console.log('Datos recibidos de modelos:', data);
      setError(null); // limpiamos el error
      setModelos(data); // data es el array directo
    })
    .catch(err => {
      console.error('Error al traer modelos:', err);
      setError(err.message);
    });
}, []);

  return (
    <div className="p-6">
      <h1 className="text-2xl font-bold mb-4">Modelos</h1>
      {error && <div style={{ color: 'red' }}>{error}</div>}
        <table>
            <thead>
                <tr>
                <th>ID</th>
                <th>Descripción</th>
                <th>ID Marca</th>
                </tr>
            </thead>
            <tbody>
                {modelos.map(m => (
                <tr key={m.id}>
                    <td>{m.id}</td>
                    <td>{m.descripcion}</td>
                    <td>{m.idMarca}</td>
                </tr>
                ))}
            </tbody>
        </table>
    </div>
  );
}
