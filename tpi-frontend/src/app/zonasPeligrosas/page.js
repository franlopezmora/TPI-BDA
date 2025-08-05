"use client";
import { useState, useEffect } from "react";
import {
  getZonas,
  createZona,
  updateZona,
  deleteZona,
} from "./services/zonaPeligrosaService";
import ZonaPeligrosaForm from "./components/ZonaPeligrosaForm";

export default function ZonasPeligrosasPage() {
  const [zonas, setZonas] = useState([]);
  const [editId, setEditId] = useState(null);
  const [error, setError] = useState(null);

  const fetchZonas = async () => {
    try {
      const data = await getZonas();
      setZonas(data);
      setError(null);
    } catch {
      setError("Error al obtener zonas");
    }
  };

  useEffect(() => {
    fetchZonas();
  }, []);

  const handleSubmit = async (formData) => {
    try {
      if (editId) {
        await updateZona(editId, formData);
      } else {
        await createZona(formData);
      }
      setEditId(null);
      fetchZonas();
    } catch {
      setError("Error al guardar zona");
    }
  };

  const handleEdit = (zona) => {
    setEditId(zona.id);
  };

  const handleDelete = async (id) => {
    try {
      await deleteZona(id);
      fetchZonas();
    } catch {
      setError("Error al eliminar zona");
    }
  };

  return (
    <main className="min-h-screen bg-gray-900 text-white p-8">
      <h1 className="text-3xl font-bold mb-6">Gestión de Zonas Peligrosas</h1>

      {error && <div className="text-red-400 mb-4">{error}</div>}

      <ZonaPeligrosaForm
        onSubmit={handleSubmit}
        zonaEditar={editId ? zonas.find((z) => z.id === editId) : null}
      />

      <div className="overflow-x-auto mt-6">
        <table className="w-full text-left border border-gray-700 rounded overflow-hidden">
          <thead className="bg-gray-800">
            <tr>
              <th className="px-4 py-2 border-b border-gray-600">ID</th>
              <th className="px-4 py-2 border-b border-gray-600">Nombre</th>
              <th className="px-4 py-2 border-b border-gray-600">Radio</th>
              <th className="px-4 py-2 border-b border-gray-600">Acciones</th>
            </tr>
          </thead>
          <tbody>
            {zonas.map((z) => (
              <tr key={z.id} className="hover:bg-gray-800 transition">
                <td className="px-4 py-2 border-b border-gray-700">{z.id}</td>
                <td className="px-4 py-2 border-b border-gray-700">{z.nombre}</td>
                <td className="px-4 py-2 border-b border-gray-700">{z.radio}</td>
                <td className="px-4 py-2 border-b border-gray-700 space-x-2">
                  <button
                    onClick={() => handleEdit(z)}
                    className="bg-yellow-500 hover:bg-yellow-600 text-black px-3 py-1 rounded"
                  >
                    Editar
                  </button>
                  <button
                    onClick={() => handleDelete(z.id)}
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
