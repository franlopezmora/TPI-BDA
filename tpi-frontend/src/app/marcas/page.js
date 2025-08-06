"use client";
import { useState, useEffect } from "react";
import {
  getMarcas,
  createMarca,
  updateMarca,
  deleteMarca,
} from "./services/marcaService";
import MarcaForm from "./components/MarcaForm";

export default function MarcaPage() {
  const [marcas, setMarcas] = useState([]);
  const [editId, setEditId] = useState(null);
  const [error, setError] = useState(null);

  const fetchMarcas = async () => {
    try {
      const data = await getMarcas();
      setMarcas(data);
      setError(null);
    } catch {
      setError("Error al obtener marcas");
    }
  };

  useEffect(() => {
    fetchMarcas();
  }, []);

  const handleSubmit = async (formData) => {
    try {
      if (editId) {
        await updateMarca(editId, formData);
      } else {
        await createMarca(formData);
      }
      setEditId(null);
      fetchMarcas();
    } catch {
      setError("Error al guardar marca");
    }
  };

  const handleEdit = (marca) => {
    setEditId(marca.id);
  };

  const handleDelete = async (id) => {
    try {
      await deleteMarca(id);
      fetchMarcas();
    } catch {
      setError("Error al eliminar marca");
    }
  };

  return (
    <main className="min-h-screen bg-gray-900 text-white p-8">
      <h1 className="text-3xl font-bold mb-6">Gestión de Marcas</h1>

      {error && <div className="text-red-400 mb-4">{error}</div>}

      <MarcaForm
        onSubmit={handleSubmit}
        marcaEditar={editId ? marcas.find((m) => m.id === editId) : null}
      />

      <div className="overflow-x-auto mt-6">
        <table className="w-full text-left border border-gray-700 rounded overflow-hidden">
          <thead className="bg-gray-800">
            <tr>
              <th className="px-4 py-2 border-b border-gray-600">ID</th>
              <th className="px-4 py-2 border-b border-gray-600">Nombre</th>
              <th className="px-4 py-2 border-b border-gray-600">Acciones</th>
            </tr>
          </thead>
          <tbody>
            {marcas.map((m) => (
              <tr key={m.id} className="hover:bg-gray-800 transition">
                <td className="px-4 py-2 border-b border-gray-700">{m.id}</td>
                <td className="px-4 py-2 border-b border-gray-700">{m.nombre}</td>
                <td className="px-4 py-2 border-b border-gray-700 space-x-2">
                  <button
                    onClick={() => handleEdit(m)}
                    className="bg-yellow-500 hover:bg-yellow-600 text-black px-3 py-1 rounded"
                  >
                    Editar
                  </button>
                  <button
                    onClick={() => handleDelete(m.id)}
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
