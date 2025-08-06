"use client";
import { useEffect, useState } from "react";
import {
  getInteresados,
  createInteresado,
  updateInteresado,
  deleteInteresado,
} from "./services/interesadoService";
import InteresadoForm from "./components/InteresadoForm";

export default function InteresadosPage() {
  const [interesados, setInteresados] = useState([]);
  const [editId, setEditId] = useState(null);
  const [error, setError] = useState(null);

  const fetchInteresados = async () => {
    try {
      const data = await getInteresados();
      setInteresados(data);
      setError(null);
    } catch {
      setError("Error al obtener interesados");
    }
  };

  useEffect(() => {
    fetchInteresados();
  }, []);

  const handleSubmit = async (formData) => {
    try {
      if (editId) {
        await updateInteresado(editId, formData);
      } else {
        await createInteresado(formData);
      }
      setEditId(null);
      fetchInteresados();
    } catch {
      setError("Error al guardar interesado");
    }
  };

  const handleEdit = (interesado) => {
    setEditId(interesado.id);
  };

  const handleDelete = async (id) => {
    try {
      await deleteInteresado(id);
      fetchInteresados();
    } catch {
      setError("Error al eliminar interesado");
    }
  };

  return (
    <main className="min-h-screen bg-gray-900 text-white p-8">
      <h1 className="text-3xl font-bold mb-6">Gestión de Interesados</h1>

      {error && <div className="text-red-400 mb-4">{error}</div>}

      <InteresadoForm
        onSubmit={handleSubmit}
        interesadoEditar={editId ? interesados.find(i => i.id === editId) : null}
      />

      <div className="overflow-x-auto mt-6">
        <table className="w-full text-left border border-gray-700 rounded overflow-hidden">
          <thead className="bg-gray-800">
            <tr>
              <th className="px-4 py-2 border-b border-gray-600">ID</th>
              <th className="px-4 py-2 border-b border-gray-600">Nombre</th>
              <th className="px-4 py-2 border-b border-gray-600">Apellido</th>
              <th className="px-4 py-2 border-b border-gray-600">DNI</th>
              <th className="px-4 py-2 border-b border-gray-600">Acciones</th>
            </tr>
          </thead>
          <tbody>
            {interesados.map((i) => (
              <tr key={i.id} className="hover:bg-gray-800 transition">
                <td className="px-4 py-2 border-b border-gray-700">{i.id}</td>
                <td className="px-4 py-2 border-b border-gray-700">{i.nombre}</td>
                <td className="px-4 py-2 border-b border-gray-700">{i.apellido}</td>
                <td className="px-4 py-2 border-b border-gray-700">{i.documento}</td>
                <td className="px-4 py-2 border-b border-gray-700 space-x-2">
                  <button
                    onClick={() => handleEdit(i)}
                    className="bg-yellow-500 hover:bg-yellow-600 text-black px-3 py-1 rounded"
                  >
                    Editar
                  </button>
                  <button
                    onClick={() => handleDelete(i.id)}
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
