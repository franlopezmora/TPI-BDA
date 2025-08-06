"use client";
import { useState, useEffect } from "react";
import {
    getTipoIncidentes,
    createTipoIncidente,
    updateTipoIncidente,
    deleteTipoIncidente,
} from "./services/tipoIncidenteService.js";
import TipoIncidenteForm from "./components/TipoIncidenteForm.js";

export default function TipoIncidentesPage() {
    const [tipos, setTipos] = useState([]);
    const [editId, setEditId] = useState(null);
    const [error, setError] = useState(null);

    const fetchTipos = async () => {
        try {
            const data = await getTipoIncidentes();
            setTipos(data);
            setError(null);
        } catch {
            setError("Error al obtener tipos de incidente");
        }
    };

    useEffect(() => {
        fetchTipos();
    }, []);

    const handleSubmit = async (formData) => {
        try {
            if (editId) {
                await updateTipoIncidente(editId, formData);
            } else {
                await createTipoIncidente(formData);
            }
            setEditId(null);
            fetchTipos();
        } catch {
            setError("Error al guardar tipo de incidente");
        }
    };

    const handleEdit = (tipo) => {
        setEditId(tipo.id);
    };

    const handleDelete = async (id) => {
        try {
            await deleteTipoIncidente(id);
            fetchTipos();
        } catch {
            setError("Error al eliminar tipo de incidente");
        }
    };

    return (
        <main className="min-h-screen bg-gray-900 text-white p-8">
            <h1 className="text-3xl font-bold mb-6">Gestión de Tipos de Incidente</h1>

            {error && <div className="text-red-400 mb-4">{error}</div>}

            <TipoIncidenteForm
                onSubmit={handleSubmit}
                tipoEditar={
                    editId ? tipos.find((t) => t.id === editId) : null
                }
            />

            <div className="overflow-x-auto mt-6">
                <table className="w-full text-left border border-gray-700 rounded">
                    <thead className="bg-gray-800">
                    <tr>
                        <th className="px-4 py-2 border-b border-gray-600">ID</th>
                        <th className="px-4 py-2 border-b border-gray-600">Descripción</th>
                        <th className="px-4 py-2 border-b border-gray-600">Acciones</th>
                    </tr>
                    </thead>
                    <tbody>
                    {tipos.map((t) => (
                        <tr key={t.id} className="hover:bg-gray-800 transition">
                            <td className="px-4 py-2 border-b border-gray-700">{t.id}</td>
                            <td className="px-4 py-2 border-b border-gray-700">{t.descripcion}</td>
                            <td className="px-4 py-2 border-b border-gray-700 space-x-2">
                                <button
                                    onClick={() => handleEdit(t)}
                                    className="bg-yellow-500 hover:bg-yellow-600 text-black px-3 py-1 rounded"
                                >
                                    Editar
                                </button>
                                <button
                                    onClick={() => handleDelete(t.id)}
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
