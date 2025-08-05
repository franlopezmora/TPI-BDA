"use client";
import { useState, useEffect } from "react";

export default function MarcaForm({ onSubmit, marcaEditar }) {
  const [form, setForm] = useState({ nombre: "" });

  useEffect(() => {
    if (marcaEditar) setForm({ nombre: marcaEditar.nombre || "" });
    else setForm({ nombre: "" });
  }, [marcaEditar]);

  const handleChange = (e) => {
    setForm({ nombre: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit(form);
    setForm({ nombre: "" });
  };

  return (
    <form
      onSubmit={handleSubmit}
      className="mb-8 flex gap-4 items-end bg-gray-800 p-4 rounded-lg shadow"
    >
      <div className="flex-1">
        <label className="block mb-1 text-sm">Nombre de Marca</label>
        <input
          name="nombre"
          value={form.nombre}
          onChange={handleChange}
          className="w-full bg-gray-700 border border-gray-600 rounded px-2 py-1 text-white"
          required
        />
      </div>
      <button
        type="submit"
        className="bg-blue-600 h-[33.8px] hover:bg-blue-700 text-white px-4 py-1 rounded shadow"
      >
        {marcaEditar ? "Actualizar" : "Crear"}
      </button>
    </form>
  );
}
