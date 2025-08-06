"use client";
import { useState, useEffect } from "react";
import {
  getPruebas,
  createPrueba,
  updatePrueba,
  deletePrueba,
} from "./services/pruebaService";
import PruebaForm from "./components/PruebaForm";

export default function PruebasPage() {
  const [pruebas, setPruebas] = useState([]);
  const [editId, setEditId] = useState(null);
  const [error, setError] = useState(null);

  const fetchPruebas = async () => {
    try {
      const data = await getPruebas();
      setPruebas(data);
      setError(null);
    } catch {
      setError("Error al obtener pruebas");
    }
  };

  useEffect(() => {
    fetchPruebas();
  }, []);

  const handleSubmit = async (formData) => {
    try {
      if (editId) {
        await updatePrueba(editId, formData);
      } else {
        await createPrueba(formData);
      }
      setEditId(null);
      fetchPruebas();
    } catch {
      setError("Error al guardar prueba");
    }
  };

  const handleEdit = (prueba) => {
    setEditId(prueba.id);
  };

  const handleDelete = async (id) => {
    try {
      await deletePrueba(id);
      fetchPruebas();
    } catch {
      setError("Error al eliminar prueba");
    }
  };

  return (
    <main className="min-h-screen bg-gray-900 text-white p-8">
      <h1 className="text-3xl font-bold mb-6">Gestión de Pruebas</h1>

      {error && <div className="text-red-400 mb-4">{error}</div>}

      <PruebaForm
        onSubmit={handleSubmit}
        pruebaEditar={editId ? pruebas.find((p) => p.id === editId) : null}
      />

      <div className="overflow-x-auto mt-6">
        <table className="w-full text-left border border-gray-700 rounded overflow-hidden">
          <thead className="bg-gray-800">
            <tr>
              <th className="px-4 py-2 border-b border-gray-600">ID</th>
              <th className="px-4 py-2 border-b border-gray-600">Inicio</th>
              <th className="px-4 py-2 border-b border-gray-600">Fin</th>
              <th className="px-4 py-2 border-b border-gray-600">Vehículo</th>
              <th className="px-4 py-2 border-b border-gray-600">Empleado</th>
              <th className="px-4 py-2 border-b border-gray-600">Interesado</th>
              <th className="px-4 py-2 border-b border-gray-600">Acciones</th>
            </tr>
          </thead>
          <tbody>
            {pruebas.map((p) => (
              <tr key={p.id} className="hover:bg-gray-800 transition">
                <td className="px-4 py-2 border-b border-gray-700">{p.id}</td>
                <td className="px-4 py-2 border-b border-gray-700">{p.fechaHoraInicio}</td>
                <td className="px-4 py-2 border-b border-gray-700">{p.fechaHoraFin}</td>
                <td className="px-4 py-2 border-b border-gray-700">
                  {p.vehiculo?.patente} <span className="text-gray-400">({p.vehiculo?.id})</span>
                </td>
                <td className="px-4 py-2 border-b border-gray-700">
                  {p.empleado?.nombreCompleto} <span className="text-gray-400">({p.empleado?.legajo})</span>
                </td>
                <td className="px-4 py-2 border-b border-gray-700">
                  {p.interesado?.nombreCompleto} <span className="text-gray-400">({p.interesado?.id})</span>
                </td>
                <td className="px-4 py-2 border-b border-gray-700 space-x-2">
                  <button
                    onClick={() => handleEdit(p)}
                    className="bg-yellow-500 hover:bg-yellow-600 text-black px-3 py-1 rounded"
                  >
                    Editar
                  </button>
                  <button
                    onClick={() => handleDelete(p.id)}
                    className="bg-red-600 hover:bg-red-700 text-white px-3 py-1 rounded"
                  >
                    Eliminar
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </main>
  );
}
