"use client";
import { useState, useEffect } from "react";
import {
    getIncidentes,
    createIncidente,
    updateIncidente,
    deleteIncidente,
} from "./services/incidenteService.js";
import { getTipoIncidentes } from "../tipos-incidente/services/tipoIncidenteService.js";
import IncidenteForm from "./components/IncidenteForm.js";

export default function IncidentesPage() {
    const [incidentes, setIncidentes] = useState([]);
    const [tipos, setTipos] = useState([]);
    const [editId, setEditId] = useState(null);
    const [error, setError] = useState(null);

    const fetchIncidentes = async () => {
        try {
            const data = await getIncidentes();
            setIncidentes(data);
            setError(null);
        } catch {
            setError("Error al obtener incidentes");
        }
    };

    const fetchTipos = async () => {
        try {
            const t = await getTipoIncidentes();
            setTipos(t);
        } catch {
            console.error("No pude bajar tipos de incidente");
        }
    };

    useEffect(() => {
        fetchIncidentes();
        fetchTipos();
    }, []);

    const handleSubmit = async (formData) => {
        try {
            if (editId) {
                await updateIncidente(editId, formData);
            } else {
                await createIncidente(formData);
            }
            setEditId(null);
            fetchIncidentes();
        } catch {
            setError("Error al guardar incidente");
        }
    };

    const handleEdit = (inc) => {
        setEditId(inc.id);
    };

    const handleDelete = async (id) => {
        try {
            await deleteIncidente(id);
            fetchIncidentes();
        } catch {
            setError("Error al eliminar incidente");
        }
    };

    return (
        <main className="min-h-screen bg-gray-900 text-white p-8">
            <h1 className="text-3xl font-bold mb-6">Gestión de Incidentes</h1>

            {error && <div className="text-red-400 mb-4">{error}</div>}

            <IncidenteForm
                tipos={tipos}
                onSubmit={handleSubmit}
                incidenteEditar={
                    editId
                        ? incidentes.find((i) => i.id === editId)
                        : null
                }
            />

            <div className="overflow-x-auto mt-6">
                <table className="w-full text-left border border-gray-700 rounded">
                    <thead className="bg-gray-800">
                    <tr>
                        <th className="px-4 py-2 border-b border-gray-600">ID</th>
                        <th className="px-4 py-2 border-b border-gray-600">Descripción</th>
                        <th className="px-4 py-2 border-b border-gray-600">Tipo</th>
                        <th className="px-4 py-2 border-b border-gray-600">Acciones</th>
                    </tr>
                    </thead>
                    <tbody>
                    {incidentes.map((i) => (
                        <tr key={i.id} className="hover:bg-gray-800 transition">
                            <td className="px-4 py-2 border-b border-gray-700">{i.id}</td>
                            <td className="px-4 py-2 border-b border-gray-700">{i.mensaje || "-"}</td>
                            <td className="px-4 py-2 border-b border-gray-700">{i.tipo || "-"}</td>
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
