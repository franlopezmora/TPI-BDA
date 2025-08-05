"use client";
import { useState, useEffect } from "react";

export default function InteresadoForm({ onSubmit, interesadoEditar }) {
  const [form, setForm] = useState({ nombre: "", apellido: "", documento: "" });

  useEffect(() => {
    if (interesadoEditar) {
      setForm({
        nombre: interesadoEditar.nombre || "",
        apellido: interesadoEditar.apellido || "",
        documento: interesadoEditar.documento || "",
      });
    } else {
      setForm({ nombre: "", apellido: "", documento: "" });
    }
  }, [interesadoEditar]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit(form);
    setForm({ nombre: "", apellido: "", documento: "" });
  };

  return (
    <form
      onSubmit={handleSubmit}
      className="mb-8 grid grid-cols-1 md:grid-cols-4 gap-4 items-end bg-gray-800 p-4 rounded-lg shadow"
    >
      <div>
        <label className="block mb-1 text-sm">Nombre</label>
        <input
          name="nombre"
          value={form.nombre}
          onChange={handleChange}
          className="w-full bg-gray-700 border border-gray-600 rounded px-2 py-1 text-white"
          required
        />
      </div>
      <div>
        <label className="block mb-1 text-sm">Apellido</label>
        <input
          name="apellido"
          value={form.apellido}
          onChange={handleChange}
          className="w-full bg-gray-700 border border-gray-600 rounded px-2 py-1 text-white"
          required
        />
      </div>
      <div>
        <label className="block mb-1 text-sm">DNI</label>
        <input
          name="documento"
          value={form.documento}
          onChange={handleChange}
          className="w-full bg-gray-700 border border-gray-600 rounded px-2 py-1 text-white"
          required
        />
      </div>
      <button
        type="submit"
        className="bg-blue-600 h-[33.8px] hover:bg-blue-700 text-white px-4 py-1 rounded shadow w-full"
      >
        {interesadoEditar ? "Actualizar" : "Crear"}
      </button>
    </form>
  );
}
