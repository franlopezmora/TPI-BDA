"use client";
import { useState, useEffect } from "react";

export default function ZonaPeligrosaForm({ onSubmit, zonaEditar }) {
  const [form, setForm] = useState({ nombre: "", radio: "" });

  useEffect(() => {
    if (zonaEditar) {
      setForm({
        nombre: zonaEditar.nombre || "",
        radio: zonaEditar.radio || "",
      });
    } else {
      setForm({ nombre: "", radio: "" });
    }
  }, [zonaEditar]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit(form);
    setForm({ nombre: "", radio: "" });
  };

  return (
    <form
      onSubmit={handleSubmit}
      className="mb-8 grid grid-cols-1 md:grid-cols-3 gap-4 items-end bg-gray-800 p-4 rounded-lg shadow"
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
        <label className="block mb-1 text-sm">Radio (km)</label>
        <input
          name="radio"
          type="number"
          value={form.radio}
          onChange={handleChange}
          className="w-full bg-gray-700 border border-gray-600 rounded px-2 py-1 text-white"
          required
        />
      </div>
      <button
        type="submit"
        className="bg-blue-600 h-[33.8px] hover:bg-blue-700 text-white px-4 py-1 rounded shadow w-full"
      >
        {zonaEditar ? "Actualizar" : "Crear"}
      </button>
    </form>
  );
}
