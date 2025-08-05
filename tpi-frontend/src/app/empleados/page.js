"use client";
import { useEffect, useState } from "react";
import {
  getEmpleados,
  createEmpleado,
  updateEmpleado,
  deleteEmpleado,
} from "./services/empleadoService.js";
import EmpleadoForm from "./components/EmpleadoForm.js";

export default function EmpleadosPage() {
  const [empleados, setEmpleados] = useState([]);
  const [form, setForm] = useState({ nombre: "", apellido: "", telefonoContacto: "" });
  const [editId, setEditId] = useState(null);
  const [error, setError] = useState(null);

  const fetchEmpleados = async () => {
    try {
      const data = await getEmpleados();
      setEmpleados(data);
      setError(null);
    } catch (err) {
      setError("Error al obtener empleados");
    }
  };

  useEffect(() => {
    fetchEmpleados();
  }, []);

  const handleSubmit = async (formData) => {
    try {
      if (editId) {
        await updateEmpleado(editId, formData);
      } else {
        await createEmpleado(formData);
      }
      setEditId(null);
      fetchEmpleados();
    } catch (err) {
      setError("Error al guardar empleado");
    }
  };


  const handleEdit = (empleado) => {
    setForm(empleado);
    setEditId(empleado.legajo);
  };

  const handleDelete = async (id) => {
    try {
      await deleteEmpleado(id);
      fetchEmpleados();
    } catch (err) {
      setError("Error al eliminar empleado");
    }
  };

  return (
    <main className="min-h-screen bg-gray-900 text-white p-8">
      <h1 className="text-3xl font-bold mb-6">Gestión de Empleados</h1>

      {error && <div className="text-red-400 mb-4">{error}</div>}

      <EmpleadoForm
        onSubmit={handleSubmit}
        empleadoEditar={editId ? empleados.find(e => e.legajo === editId) : null}
      />


      <div className="overflow-x-auto">
        <table className="w-full text-left border border-gray-700 rounded overflow-hidden">
          <thead className="bg-gray-800">
            <tr>
              <th className="px-4 py-2 border-b border-gray-600">Legajo</th>
              <th className="px-4 py-2 border-b border-gray-600">Nombre</th>
              <th className="px-4 py-2 border-b border-gray-600">Apellido</th>
              <th className="px-4 py-2 border-b border-gray-600">Teléfono</th>
              <th className="px-4 py-2 border-b border-gray-600">Acciones</th>
            </tr>
          </thead>
          <tbody>
            {empleados.map((e) => (
              <tr key={e.legajo} className="hover:bg-gray-800 transition">
                <td className="px-4 py-2 border-b border-gray-700">{e.legajo}</td>
                <td className="px-4 py-2 border-b border-gray-700">{e.nombre}</td>
                <td className="px-4 py-2 border-b border-gray-700">{e.apellido}</td>
                <td className="px-4 py-2 border-b border-gray-700">{e.telefonoContacto}</td>
                <td className="px-4 py-2 border-b border-gray-700 space-x-2">
                  <button
                    onClick={() => handleEdit(e)}
                    className="bg-yellow-500 hover:bg-yellow-600 text-black px-3 py-1 rounded"
                  >
                    Editar
                  </button>
                  <button
                    onClick={() => handleDelete(e.legajo)}
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
