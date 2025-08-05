"use client";
import { useEffect, useState } from "react";
import {
  getModelos,
  createModelo,
  updateModelo,
  deleteModelo,
} from "./services/modeloService";
import ModeloForm from "./components/ModeloForm";

export default function ModelosPage() {
  const [modelos, setModelos] = useState([]);
  const [editId, setEditId] = useState(null);
  const [error, setError] = useState(null);

  const fetchModelos = async () => {
    try {
      const data = await getModelos();
      setModelos(data);
      setError(null);
    } catch (err) {
      setError("Error al obtener modelos");
    }
  };

  useEffect(() => {
    fetchModelos();
  }, []);

  const handleSubmit = async (formData) => {
    try {
      if (editId) {
        await updateModelo(editId, formData);
      } else {
        await createModelo(formData);
      }
      setEditId(null);
      fetchModelos();
    } catch (err) {
      setError("Error al guardar modelo");
    }
  };

  const handleEdit = (modelo) => {
    setEditId(modelo.id);
  };

  const handleDelete = async (id) => {
    try {
      await deleteModelo(id);
      fetchModelos();
    } catch (err) {
      setError("Error al eliminar modelo");
    }
  };

  return (
    <main className="min-h-screen bg-gray-900 text-white p-8">
      <h1 className="text-3xl font-bold mb-6">Gestión de Modelos</h1>

      {error && <div className="text-red-400 mb-4">{error}</div>}

      <ModeloForm
        onSubmit={handleSubmit}
        modeloEditar={editId ? modelos.find(m => m.id === editId) : null}
      />

      <div className="overflow-x-auto mt-6">
        <table className="w-full text-left border border-gray-700 rounded overflow-hidden">
          <thead className="bg-gray-800">
            <tr>
              <th className="px-4 py-2 border-b border-gray-600">ID</th>
              <th className="px-4 py-2 border-b border-gray-600">Descripción</th>
              <th className="px-4 py-2 border-b border-gray-600">ID Marca</th>
              <th className="px-4 py-2 border-b border-gray-600">Acciones</th>
            </tr>
          </thead>
          <tbody>
            {modelos.map((m) => (
              <tr key={m.id} className="hover:bg-gray-800 transition">
                <td className="px-4 py-2 border-b border-gray-700">{m.id}</td>
                <td className="px-4 py-2 border-b border-gray-700">{m.descripcion}</td>
                <td className="px-4 py-2 border-b border-gray-700">{m.idMarca}</td>
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
