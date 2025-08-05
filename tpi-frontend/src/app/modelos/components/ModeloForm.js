"use client";
import { useState, useEffect } from "react";

export default function ModeloForm({ onSubmit, modeloEditar }) {
  const [form, setForm] = useState({
    descripcion: "",
    idMarca: "",
  });

  useEffect(() => {
    if (modeloEditar) {
      setForm({
        descripcion: modeloEditar.descripcion || "",
        idMarca: modeloEditar.idMarca || "",
      });
    } else {
      setForm({ descripcion: "", idMarca: "" });
    }
  }, [modeloEditar]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit(form);
    setForm({ descripcion: "", idMarca: "" });
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
        <label className="block mb-1 text-sm">ID Marca</label>
        <input
          name="idMarca"
          type="number"
          value={form.idMarca}
          onChange={handleChange}
          className="w-full bg-gray-700 border border-gray-600 rounded px-2 py-1 text-white"
          required
        />
      </div>
      <button
        type="submit"
        className="bg-blue-600 h-[33.8px] hover:bg-blue-700 text-white px-4 py-1 rounded shadow w-full"
      >
        {modeloEditar ? "Actualizar" : "Crear"}
      </button>
    </form>
  );
}
