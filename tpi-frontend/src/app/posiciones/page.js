"use client";
import { useEffect, useState } from "react";
import { getPosiciones, createPosicion, deletePosicion } from "./services/posicionService";
import PosicionForm from "./components/PosicionForm";

export default function PosicionesPage() {
  const [posiciones, setPosiciones] = useState([]);
  const [error, setError] = useState(null);

  const fetchAll = async () => {
    try {
      const data = await getPosiciones();
      setPosiciones(data);
      setError(null);
    } catch {
      setError("No se pudieron cargar las posiciones");
    }
  };

  useEffect(() => { fetchAll() }, []);

  const handleCreate = async (newPos) => {
    try {
      await createPosicion(newPos);
      fetchAll();
    } catch {
      setError("Error al crear posición");
    }
  };

  const handleDelete = async (id) => {
    try {
      await deletePosicion(id);
      fetchAll();
    } catch {
      setError("Error al eliminar posición");
    }
  };

  return (
    <main className="min-h-screen bg-gray-900 text-white p-8">
      <h1 className="text-3xl font-bold mb-6">Gestión de Posiciones</h1>

      {error && <div className="text-red-400 mb-4">{error}</div>}

      <PosicionForm onSubmit={handleCreate} />

      <div className="overflow-x-auto mt-6">
        <table className="w-full text-left border border-gray-700 rounded overflow-hidden">
          <thead className="bg-gray-800">
            <tr>
              <th className="px-4 py-2 border-b border-gray-600">ID</th>
              <th className="px-4 py-2 border-b border-gray-600">Vehículo</th>
              <th className="px-4 py-2 border-b border-gray-600">Latitud</th>
              <th className="px-4 py-2 border-b border-gray-600">Longitud</th>
              <th className="px-4 py-2 border-b border-gray-600">Fecha / Hora</th>
              <th className="px-4 py-2 border-b border-gray-600">Acciones</th>
            </tr>
          </thead>
          <tbody>
            {posiciones.map(p => (
              <tr key={p.id} className="hover:bg-gray-800 transition">
                <td className="px-4 py-2 border-b border-gray-700">{p.id}</td>
                <td className="px-4 py-2 border-b border-gray-700">{p.vehiculo?.id}</td>
                <td className="px-4 py-2 border-b border-gray-700">{p.latitud}</td>
                <td className="px-4 py-2 border-b border-gray-700">{p.longitud}</td>
                <td className="px-4 py-2 border-b border-gray-700">{p.fechaHora}</td>
                <td className="px-4 py-2 border-b border-gray-700">
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
