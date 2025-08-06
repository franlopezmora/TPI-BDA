"use client";
import { useState, useEffect } from "react";

export default function IncidenteForm({ tipos, onSubmit, incidenteEditar }) {
    const [form, setForm] = useState({
        descripcion: "",
        idTipoIncidente: "",
    });

    useEffect(() => {
        if (incidenteEditar) {
            setForm({
                descripcion: incidenteEditar.descripcion || "",
                idTipoIncidente: incidenteEditar.idTipoIncidente?.toString() || "",
            });
        } else {
            setForm({ descripcion: "", idTipoIncidente: "" });
        }
    }, [incidenteEditar]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setForm((prev) => ({ ...prev, [name]: value }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        onSubmit({
            ...form,
            idTipoIncidente: Number(form.idTipoIncidente),
        });
        setForm({ descripcion: "", idTipoIncidente: "" });
    };

    return (
        <form
            onSubmit={handleSubmit}
            className="mb-8 grid grid-cols-1 md:grid-cols-3 gap-4 items-end bg-gray-800 p-4 rounded-lg shadow"
        >
            <div>
                <label className="block mb-1 text-sm">Descripción</label>
                <input
                    name="descripcion"
                    value={form.descripcion}
                    onChange={handleChange}
                    className="w-full bg-gray-700 border border-gray-600 rounded px-2 py-1 text-white"
                    required
                />
            </div>
            <div>
                <label className="block mb-1 text-sm">Tipo de Incidente</label>
                <select
                    name="idTipoIncidente"
                    value={form.idTipoIncidente}
                    onChange={handleChange}
                    className="w-full h-[33.8px] bg-gray-700 border border-gray-600 rounded px-2 text-white text-sm"
                    required
                >
                    <option value="">Seleccione un tipo</option>
                    {tipos.map((t) => (
                        <option key={t.id} value={t.id.toString()}>
                            {t.descripcion}
                        </option>
                    ))}
                </select>
            </div>
            <button
                type="submit"
                className="bg-blue-600 h-[33.8px] hover:bg-blue-700 text-white px-4 py-1 rounded shadow"
            >
                {incidenteEditar ? "Actualizar" : "Crear"}
            </button>
        </form>
    );
}
