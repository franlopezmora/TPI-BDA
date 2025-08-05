"use client";
import { useEffect, useState } from "react";
import {
  getVehiculos,
  createVehiculo,
  updateVehiculo,
  deleteVehiculo,
} from "./services/vehiculoService.js";
import VehiculoForm from "./components/VehiculoForm.js";
import { getModelos } from "../modelos/services/modeloService";

export default function VehiculosPage() {
  const [vehiculos, setVehiculos] = useState([]);
  const [editId, setEditId] = useState(null);
  const [error, setError] = useState(null);
  const [modelos, setModelos]         = useState([]);

  const fetchVehiculos = async () => {
    try {
      const data = await getVehiculos();
      setVehiculos(data);
      setError(null);
    } catch (err) {
      setError("Error al obtener vehículos");
    }
  };

  useEffect(() => {
    fetchVehiculos();
    fetchModelos();
  }, []);

  const fetchModelos = async () => {
    try {
      const m = await getModelos();
      setModelos(m);
    } catch (e) {
      console.error("No pude bajar modelos", e);
    }
  };

  useEffect(() => {
    fetchVehiculos();
  }, []);

  const handleSubmit = async (formData) => {
    try {
      if (editId) {
        await updateVehiculo(editId, formData);
      } else {
        await createVehiculo(formData);
      }
      setEditId(null);
      fetchVehiculos();
    } catch (err) {
      setError("Error al guardar vehículo");
    }
  };

  const handleEdit = (vehiculo) => {
    setEditId(vehiculo.id);
  };

  const handleDelete = async (id) => {
    try {
      await deleteVehiculo(id);
      fetchVehiculos();
    } catch (err) {
      setError("Error al eliminar vehículo");
    }
  };

  return (
    <main className="min-h-screen bg-gray-900 text-white p-8">
      <h1 className="text-3xl font-bold mb-6">Gestión de Vehículos</h1>

      {error && <div className="text-red-400 mb-4">{error}</div>}

      <VehiculoForm
          modelos={modelos}
          onSubmit={handleSubmit}
          vehiculoEditar={
            editId
                ? vehiculos.find((v) => v.id === editId)
                : null
          }
      />

      <div className="overflow-x-auto mt-6">
        <table className="w-full text-left border border-gray-700 rounded overflow-hidden">
          <thead className="bg-gray-800">
            <tr>
              <th className="px-4 py-2 border-b border-gray-600">ID</th>
              <th className="px-4 py-2 border-b border-gray-600">Patente</th>
              <th className="px-4 py-2 border-b border-gray-600">Año</th>
              <th className="px-4 py-2 border-b border-gray-600">Modelo</th>
              <th className="px-4 py-2 border-b border-gray-600">Acciones</th>
            </tr>
          </thead>
          <tbody>
            {vehiculos.map((v) => (
              <tr key={v.id} className="hover:bg-gray-800 transition">
                <td className="px-4 py-2 border-b border-gray-700">{v.id}</td>
                <td className="px-4 py-2 border-b border-gray-700">{v.patente}</td>
                <td className="px-4 py-2 border-b border-gray-700">{v.anio}</td>
                <td className="px-4 py-2 border-b border-gray-700">{modelos.find((m) => m.id === v.idModelo)?.descripcion || "-"}</td>
                <td className="px-4 py-2 border-b border-gray-700 space-x-2">
                  <button
                    onClick={() => handleEdit(v)}
                    className="bg-yellow-500 hover:bg-yellow-600 text-black px-3 py-1 rounded"
                  >
                    Editar
                  </button>
                  <button
                    onClick={() => handleDelete(v.id)}
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
